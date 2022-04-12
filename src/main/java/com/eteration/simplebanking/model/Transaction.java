package com.eteration.simplebanking.model;


import org.springframework.transaction.TransactionStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

// This class is a place holder you can change the complete implementation
@Entity
@DiscriminatorColumn(name = "type")
public abstract class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime date;
    private Double amount;
    private String approvalCode;

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    public Transaction(Double amount) {
        this.date = LocalDateTime.now();
        this.amount = amount;
    }

    public Transaction() {

    }


    public LocalDateTime getDate() {
        return date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    protected abstract Double execute(Double balance) throws InsufficientBalanceException;

    @PrePersist
    private void onCreate() {
        this.date = LocalDateTime.now();
    }


    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
