package com.github.fernvndomatos.NotifyBank.repository;

import com.github.fernvndomatos.NotifyBank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
}
