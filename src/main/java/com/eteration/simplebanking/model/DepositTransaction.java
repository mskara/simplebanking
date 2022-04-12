package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
public class DepositTransaction extends Transaction {
    public DepositTransaction(Double amount) {
        super(amount);
    }

    public DepositTransaction() {
    }

    @Override
    protected Double execute(Double balance) {
        return balance + getAmount();
    }
}
