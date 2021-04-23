package org.js.azdanov.depositservice.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DepositRequestDto {
    private Long accountId;
    private Long billId;
    private BigDecimal amount;
}
