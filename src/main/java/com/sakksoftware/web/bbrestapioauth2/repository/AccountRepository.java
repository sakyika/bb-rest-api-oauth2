package com.sakksoftware.web.bbrestapioauth2.repository;

import com.sakksoftware.web.bbrestapioauth2.model.Account;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepository extends Repository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Account save(Account account);
    int deleteAccountById(String id);
}