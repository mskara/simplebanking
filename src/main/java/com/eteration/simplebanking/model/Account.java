package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    private String accountNumber;
    private String owner;
    private Double balance;
    private LocalDateTime createdDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Account() {

    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {

        if (amount > balance) {
            throw new InsufficientBalanceException();
        }
        balance -= amount;

    }

    public Account post(Transaction tx) throws InsufficientBalanceException {
        transactions.add(tx);
        balance = tx.execute(balance);
        return this;
    }

    @PrePersist
    private void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}
