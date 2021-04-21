package org.js.azdanov.billservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.js.azdanov.billservice.entity.Bill;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class BillResponseDto {
    private final Long id;
    private final Long accountId;
    private final BigDecimal amount;
    private final Boolean isDefault;
    private final OffsetDateTime creationDate;
    private final Boolean overdraftEnabled;

    public BillResponseDto(Bill bill) {
        id = bill.getId();
        accountId = bill.getAccountId();
        amount = bill.getAmount();
        isDefault = bill.getIsDefault();
        creationDate = bill.getCreationDate();
        overdraftEnabled = bill.getOverdraftEnabled();
    }
}
