package com.eteration.simplebanking.services;


import com.eteration.simplebanking.model.Account;
import org.springframework.stereotype.Service;

// This class is a place holder you can change the complete implementation
public interface AccountService {

    Account findAccount(String accountNumber);
    Account saveTx(Account account);
}
