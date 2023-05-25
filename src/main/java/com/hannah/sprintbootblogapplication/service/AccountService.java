package com.hannah.sprintbootblogapplication.service;

import com.hannah.sprintbootblogapplication.model.Account;
import com.hannah.sprintbootblogapplication.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
//    passwordEncoder method defined in WebSecurityConfig is being injected here
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    //Persisting the "Account" entry if it's valid
    public Account save(Account account) {
//        Using the "passwordEncoder" method that is defined in WebSecurityConfig
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.saveAndFlush(account);
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findOneByEmail(email);
    }
}
