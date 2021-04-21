package org.js.azdanov.accountservice.controller;

import lombok.AllArgsConstructor;
import org.js.azdanov.accountservice.controller.dto.AccountRequestDto;
import org.js.azdanov.accountservice.controller.dto.AccountResponseDto;
import org.js.azdanov.accountservice.service.AccountService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountResponseDto getAccount(@PathVariable Long id) {
        return new AccountResponseDto(accountService.getAccountById(id));
    }

    @PostMapping("/")
    public Long createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.createAccount(
                accountRequestDto.getName(),
                accountRequestDto.getEmail(),
                accountRequestDto.getPhone(),
                accountRequestDto.getBills());
    }

    @PutMapping("/{id}")
    public AccountResponseDto updateAccount(
            @PathVariable Long id, @RequestBody AccountRequestDto accountRequestDto) {
        return new AccountResponseDto(
                accountService.updateAccount(
                        id,
                        accountRequestDto.getName(),
                        accountRequestDto.getEmail(),
                        accountRequestDto.getPhone(),
                        accountRequestDto.getBills()));
    }

    @DeleteMapping("/{id}")
    public AccountResponseDto deleteAccount(@PathVariable Long id) {
        return new AccountResponseDto(accountService.deleteAccount(id));
    }
}
