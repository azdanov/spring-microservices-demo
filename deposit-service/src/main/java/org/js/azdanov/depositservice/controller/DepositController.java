package org.js.azdanov.depositservice.controller;

import lombok.AllArgsConstructor;

import org.js.azdanov.depositservice.controller.dto.DepositRequestDto;
import org.js.azdanov.depositservice.controller.dto.DepositResponseDto;
import org.js.azdanov.depositservice.service.DepositService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DepositController {
    private final DepositService depositService;

    @PostMapping("/deposits")
    public DepositResponseDto deposit(@RequestBody DepositRequestDto requestDto) {
        return depositService.deposit(
                requestDto.getAccountId(), requestDto.getBillId(), requestDto.getAmount());
    }
}
