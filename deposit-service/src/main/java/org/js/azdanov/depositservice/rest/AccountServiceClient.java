package org.js.azdanov.depositservice.rest;

import org.js.azdanov.depositservice.rest.dto.AccountResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service")
public interface AccountServiceClient {
    @GetMapping(value = "/accounts/{id}")
    AccountResponseDto getAccountById(@PathVariable("id") Long id);
}
