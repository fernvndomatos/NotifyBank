package com.github.fernvndomatos.NotifyBank.service;

import com.github.fernvndomatos.NotifyBank.entity.BankAccount;
import com.github.fernvndomatos.NotifyBank.entity.Transaction;
import com.github.fernvndomatos.NotifyBank.exception.BankAccountNotFoundException;
import com.github.fernvndomatos.NotifyBank.exception.TransactionNotFoundException;
import com.github.fernvndomatos.NotifyBank.repository.BankAccountRepository;
import com.github.fernvndomatos.NotifyBank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    public Transaction createTransaction(Long accountId, Transaction transaction) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Conta não encontrada: " + accountId));
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    public Transaction findByTransactionId(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transação não encontrada: " + id));
    }

    public List<Transaction> transactionList() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findTransactionByAccountId(Long accountId) {
        if (!bankAccountRepository.existsById(accountId)) {
            throw new BankAccountNotFoundException("Conta não encontrada: " + accountId);
        }
        return transactionRepository.findByAccountId(accountId);
    }

    public void deleteTransactionById(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new TransactionNotFoundException("Transação não encontrada: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
