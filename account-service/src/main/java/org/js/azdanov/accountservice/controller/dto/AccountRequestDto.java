package org.js.azdanov.accountservice.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AccountRequestDto {
    private String name;
    private String email;
    private String phone;
    private List<Long> bills;
    private OffsetDateTime creationDate;
}
