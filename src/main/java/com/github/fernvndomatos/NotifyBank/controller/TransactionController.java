package com.github.fernvndomatos.NotifyBank.controller;

import com.github.fernvndomatos.NotifyBank.dto.request.TransactionRequest;
import com.github.fernvndomatos.NotifyBank.dto.response.TransactionResponse;
import com.github.fernvndomatos.NotifyBank.entity.Transaction;
import com.github.fernvndomatos.NotifyBank.mapper.TransactionMapper;
import com.github.fernvndomatos.NotifyBank.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifybank/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request.accountId(), TransactionMapper.toTransaction(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionMapper.toTransactionResponse(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findByTransactionId(@PathVariable Long id){
        Transaction transaction = transactionService.findByTransactionId(id);
        return ResponseEntity.ok(TransactionMapper.toTransactionResponse(transaction));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<List<TransactionResponse>> findTransactionByAccountId(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.findTransactionByAccountId(id)
                .stream()
                .map(TransactionMapper::toTransactionResponse)
                .toList());
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> transactionList(){
        return ResponseEntity.ok(transactionService.transactionList()
                .stream()
                .map(TransactionMapper::toTransactionResponse)
                .toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }
}
