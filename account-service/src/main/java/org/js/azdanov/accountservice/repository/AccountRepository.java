package org.js.azdanov.accountservice.repository;

import org.js.azdanov.accountservice.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
