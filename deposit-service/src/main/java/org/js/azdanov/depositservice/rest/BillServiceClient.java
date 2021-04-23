package org.js.azdanov.depositservice.rest;

import org.js.azdanov.depositservice.rest.dto.BillRequestDto;
import org.js.azdanov.depositservice.rest.dto.BillResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = "bill-service")
public interface BillServiceClient {
    @GetMapping(value = "bills/{id}")
    BillResponseDto getBillById(@PathVariable("id") Long id);

    @PutMapping(value = "bills/{id}")
    void update(@PathVariable("id") Long id, BillRequestDto billRequestDto);

    @GetMapping(value = "bills/account/{id}")
    List<BillResponseDto> getBillsByAccountId(@PathVariable("id") Long id);
}
