package org.js.azdanov.accountservice.service;

import lombok.AllArgsConstructor;
import org.js.azdanov.accountservice.entity.Account;
import org.js.azdanov.accountservice.exception.AccountNotFoundException;
import org.js.azdanov.accountservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account getAccountById(Long id) {
        return accountRepository
                .findById(id)
                .orElseThrow(
                        () -> new AccountNotFoundException("Unable to find account with id: " + id));
    }

    public Long createAccount(String name, String email, String phone, List<Long> bills) {
        Account account = new Account(name, email, phone, OffsetDateTime.now(), bills);
        return accountRepository.save(account).getId();
    }

    public Account updateAccount(Long id, String name, String email, String phone, List<Long> bills) {
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);
        account.setBills(bills);
        return accountRepository.save(account);
    }

    public Account deleteAccount(Long id) {
        Account deletedAccount = getAccountById(id);
        accountRepository.deleteById(id);
        return deletedAccount;
    }
}
