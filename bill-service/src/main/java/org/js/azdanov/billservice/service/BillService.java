package org.js.azdanov.billservice.service;

import lombok.AllArgsConstructor;
import org.js.azdanov.billservice.entity.Bill;
import org.js.azdanov.billservice.exception.BillNotFoundException;
import org.js.azdanov.billservice.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BillService {
    private final BillRepository billRepository;

    public Bill getBillById(Long id) {
        return billRepository
                .findById(id)
                .orElseThrow(() -> new BillNotFoundException("Unable to find bill with id: " + id));
    }

    public Long createBill(
            Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled) {
        Bill bill = new Bill(accountId, amount, isDefault, OffsetDateTime.now(), overdraftEnabled);
        return billRepository.save(bill).getId();
    }

    public Bill updateBill(
            Long id, Long accountId, BigDecimal amount, Boolean isDefault, Boolean overdraftEnabled) {
        Bill bill = new Bill(accountId, amount, isDefault, overdraftEnabled);
        bill.setId(id);
        return billRepository.save(bill);
    }

    public Bill deleteBill(Long id) {
        Bill deletedBill = getBillById(id);
        billRepository.deleteById(id);
        return deletedBill;
    }

    public List<Bill> getBillsByAccountId(Long accountId) {
        return billRepository.getBillsByAccountId(accountId);
    }
}
