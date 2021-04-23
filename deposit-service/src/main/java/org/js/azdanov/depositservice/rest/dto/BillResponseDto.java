package org.js.azdanov.depositservice.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillResponseDto {
    private Long id;
    private Long accountId;
    private BigDecimal amount;
    private Boolean isDefault;
    private OffsetDateTime creationDate;
    private Boolean overdraftEnabled;
}
