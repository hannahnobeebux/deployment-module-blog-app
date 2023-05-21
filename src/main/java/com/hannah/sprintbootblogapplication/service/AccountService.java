package com.hannah.sprintbootblogapplication.service;

import com.hannah.sprintbootblogapplication.model.Account;
import com.hannah.sprintbootblogapplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    //Persisting the "Account" entry if it's valid
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findOneByEmail(email);
    }
}
