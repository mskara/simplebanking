package com.eteration.simplebanking.services.impl;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void initAccount() {

        Account account = new Account("Kerem Karaca", "669-7788");

        accountRepository.save(account);

    }


    @Override
    public Account findAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found : " + accountNumber));
    }

    @Override
    public Account saveTx(Account account) {
        return accountRepository.save(account);
    }
}
