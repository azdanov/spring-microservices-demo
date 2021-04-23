package org.js.azdanov.depositservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.js.azdanov.depositservice.controller.dto.DepositResponseDto;
import org.js.azdanov.depositservice.entity.Deposit;
import org.js.azdanov.depositservice.exception.DepositServiceException;
import org.js.azdanov.depositservice.repository.DepositRepository;
import org.js.azdanov.depositservice.rest.AccountServiceClient;
import org.js.azdanov.depositservice.rest.BillServiceClient;
import org.js.azdanov.depositservice.rest.dto.AccountResponseDto;
import org.js.azdanov.depositservice.rest.dto.BillRequestDto;
import org.js.azdanov.depositservice.rest.dto.BillResponseDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class DepositService {
    private static final String DEPOSIT_TOPIC_EXCHANGE = "deposit.notify.exchange";
    private static final String DEPOSIT_ROUTING_KEY = "deposit.key";

    private final DepositRepository depositRepository;
    private final AccountServiceClient accountServiceClient;
    private final BillServiceClient billServiceClient;
    private final RabbitTemplate rabbitTemplate;

    public DepositResponseDto deposit(Long accountId, Long billId, BigDecimal amount) {
        if (accountId == null && billId == null) {
            throw new DepositServiceException("Account is null and bill is null");
        }

        if (billId != null) {
            return depositToBill(billId, amount);
        }

        return depositToAccountDefaultBill(accountId, amount);
    }

    private DepositResponseDto depositToAccountDefaultBill(Long accountId, BigDecimal amount) {
        BillResponseDto defaultBill = getDefaultBill(accountId);
        BillRequestDto billRequestDto = createBillRequest(amount, defaultBill);

        billServiceClient.update(defaultBill.getId(), billRequestDto);

        AccountResponseDto account = accountServiceClient.getAccountById(accountId);
        depositRepository.save(new Deposit(amount, defaultBill.getId(), OffsetDateTime.now(), account.getEmail()));

        return createResponse(amount, account);
    }

    private DepositResponseDto depositToBill(Long billId, BigDecimal amount) {
        BillResponseDto billResponseDto = billServiceClient.getBillById(billId);
        BillRequestDto billRequestDto = createBillRequest(amount, billResponseDto);

        billServiceClient.update(billId, billRequestDto);

        AccountResponseDto accountResponseDto = accountServiceClient.getAccountById(billResponseDto.getAccountId());
        depositRepository.save(new Deposit(amount, billId, OffsetDateTime.now(), accountResponseDto.getEmail()));

        return createResponse(amount, accountResponseDto);
    }

    private DepositResponseDto createResponse(BigDecimal amount, AccountResponseDto accountResponseDto) {
        DepositResponseDto depositResponseDto = new DepositResponseDto(amount, accountResponseDto.getEmail());

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(
                    DEPOSIT_TOPIC_EXCHANGE,
                    DEPOSIT_ROUTING_KEY,
                    objectMapper.writeValueAsString(depositResponseDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new DepositServiceException("Can't send message to RabbitMQ");
        }
        return depositResponseDto;
    }

    private BillRequestDto createBillRequest(BigDecimal amount, BillResponseDto billResponseDto) {
        BillRequestDto billRequestDto = new BillRequestDto();
        billRequestDto.setAccountId(billResponseDto.getAccountId());
        billRequestDto.setCreationDate(billResponseDto.getCreationDate());
        billRequestDto.setIsDefault(billResponseDto.getIsDefault());
        billRequestDto.setOverdraftEnabled(billResponseDto.getOverdraftEnabled());
        billRequestDto.setAmount(billResponseDto.getAmount().add(amount));
        return billRequestDto;
    }

    private BillResponseDto getDefaultBill(Long accountId) {
        return billServiceClient.getBillsByAccountId(accountId).stream()
                .filter(BillResponseDto::getIsDefault)
                .findAny()
                .orElseThrow(
                        () -> new DepositServiceException("Unable to find default bill for account: " + accountId));
    }
}
