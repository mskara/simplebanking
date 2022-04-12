package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// This class is a place holder you can change the complete implementation
@RestController
@RequestMapping("/account/v1")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.findAccount(accountNumber));
    }

    @PostMapping("/credit/{accountNumber}")
    public ResponseEntity<TransactionStatus> credit(@PathVariable String accountNumber,
                                                    @RequestBody DepositTransaction transaction)
            throws InsufficientBalanceException {
        Account post = accountService.findAccount(accountNumber).post(transaction);
        TransactionStatus okTransaction = getOkTransaction();
        transaction.setApprovalCode(okTransaction.getApprovalCode());
        accountService.saveTx(post);
        return ResponseEntity.ok(okTransaction);
    }

    @PostMapping("/debit/{accountNumber}")
    public ResponseEntity<TransactionStatus> debit(@PathVariable String accountNumber,
                                                   @RequestBody WithdrawalTransaction transaction)
            throws InsufficientBalanceException {
        Account post = accountService.findAccount(accountNumber).post(transaction);
        TransactionStatus okTransaction = getOkTransaction();
        transaction.setApprovalCode(okTransaction.getApprovalCode());
        accountService.saveTx(post);
        return ResponseEntity.ok(okTransaction);

    }

    private TransactionStatus getOkTransaction() {
        return new TransactionStatus("OK", String.valueOf(UUID.randomUUID()));
    }
}