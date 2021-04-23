package org.js.azdanov.billservice.controller;

import lombok.AllArgsConstructor;
import org.js.azdanov.billservice.controller.dto.BillRequestDto;
import org.js.azdanov.billservice.controller.dto.BillResponseDto;
import org.js.azdanov.billservice.service.BillService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class BillController {
    private final BillService billService;

    @GetMapping("/{id}")
    public BillResponseDto getBill(@PathVariable Long id) {
        return new BillResponseDto(billService.getBillById(id));
    }

    @PostMapping("/")
    public Long createBill(@RequestBody BillRequestDto billRequestDto) {
        return billService.createBill(
                billRequestDto.getAccountId(),
                billRequestDto.getAmount(),
                billRequestDto.getIsDefault(),
                billRequestDto.getOverdraftEnabled());
    }

    @PutMapping("/{id}")
    public BillResponseDto updateBill(
            @PathVariable Long id, @RequestBody BillRequestDto billRequestDto) {
        return new BillResponseDto(
                billService.updateBill(
                        id,
                        billRequestDto.getAccountId(),
                        billRequestDto.getAmount(),
                        billRequestDto.getIsDefault(),
                        billRequestDto.getOverdraftEnabled()));
    }

    @DeleteMapping("/{id}")
    public BillResponseDto deleteBill(@PathVariable Long id) {
        return new BillResponseDto(billService.deleteBill(id));
    }

    @GetMapping("/account/{id}")
    public List<BillResponseDto> getBillsByAccountId(@PathVariable Long id) {
        return billService.getBillsByAccountId(id).stream()
                .map(BillResponseDto::new)
                .collect(Collectors.toList());
    }
}
