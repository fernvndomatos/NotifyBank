package com.github.fernvndomatos.NotifyBank.controller;

import com.github.fernvndomatos.NotifyBank.dto.request.BankAccountRequest;
import com.github.fernvndomatos.NotifyBank.dto.response.BankAccountResponse;
import com.github.fernvndomatos.NotifyBank.entity.BankAccount;
import com.github.fernvndomatos.NotifyBank.mapper.BankAccountMapper;
import com.github.fernvndomatos.NotifyBank.service.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifybank/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity <BankAccountResponse> createBankAccount(@Valid @RequestBody BankAccountRequest request){
        BankAccount account = bankAccountService.createBankAccount(BankAccountMapper.toBankAccount(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(BankAccountMapper.toBankAccountResponse(account));
    }

    @GetMapping
    public ResponseEntity<List<BankAccountResponse>> bankAccountList(){
        return ResponseEntity.ok(bankAccountService.bankAccountList()
                .stream()
                .map(bankAccount -> BankAccountMapper.toBankAccountResponse(bankAccount))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> findBankAccountById(@PathVariable Long id){
        BankAccount account = bankAccountService.findBankAccountById(id);
        return ResponseEntity.ok(BankAccountMapper.toBankAccountResponse(account));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponse> updateBankAccount(@PathVariable Long id, @Valid @RequestBody BankAccountRequest request){
       BankAccount updatedAccount = bankAccountService.updateBankAccount(id, BankAccountMapper.toBankAccount(request));
       return ResponseEntity.ok(BankAccountMapper.toBankAccountResponse(updatedAccount));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankAccountById(@PathVariable Long id){
        bankAccountService.deleteBankAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
