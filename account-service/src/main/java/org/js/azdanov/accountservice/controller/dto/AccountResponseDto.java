package org.js.azdanov.accountservice.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.js.azdanov.accountservice.entity.Account;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class AccountResponseDto {
    private final Long id;
    private final String name;
    private final String email;
    private final List<Long> bills;
    private final String phone;
    private final OffsetDateTime creationDate;

    public AccountResponseDto(Account account) {
        id = account.getId();
        name = account.getName();
        email = account.getEmail();
        phone = account.getPhone();
        bills = account.getBills();
        creationDate = account.getCreationDate();
    }
}
