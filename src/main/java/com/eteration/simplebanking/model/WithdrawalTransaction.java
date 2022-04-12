package com.eteration.simplebanking.model;


import javax.persistence.Entity;

// This class is a place holder you can change the complete implementation
@Entity
public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(Double amount) {
        super(amount);
    }

    public WithdrawalTransaction() {
    }

    @Override
    protected Double execute(Double balance) throws InsufficientBalanceException {
        if (getAmount() > balance) {
            throw new InsufficientBalanceException();
        }
        return balance - getAmount();

    }


}


