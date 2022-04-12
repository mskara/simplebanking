package com.eteration.simplebanking.repository;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
