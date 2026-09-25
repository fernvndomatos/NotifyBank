package com.github.fernvndomatos.NotifyBank.service;

import com.github.fernvndomatos.NotifyBank.entity.BankAccount;
import com.github.fernvndomatos.NotifyBank.exception.AccountAlreadyExistsException;
import com.github.fernvndomatos.NotifyBank.exception.BankAccountNotFoundException;
import com.github.fernvndomatos.NotifyBank.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccount createBankAccount(BankAccount bankAccount) {
        if (bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber()).isPresent()) {
            throw new AccountAlreadyExistsException(
                    "Conta já cadastrada!"
            );
        }
        return bankAccountRepository.save(bankAccount);
    }

    public BankAccount findBankAccountById(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new BankAccountNotFoundException("Conta não encontrada: " + id));
    }

    public List<BankAccount> bankAccountList() {
        return bankAccountRepository.findAll();
    }

    public BankAccount updateBankAccount(Long id, BankAccount updatedBankAccount) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(() -> new BankAccountNotFoundException("Conta não encontrada: " + id));

        bankAccount.setAccountBalance(updatedBankAccount.getAccountBalance());
        bankAccount.setCurrencyType(updatedBankAccount.getCurrencyType());
        bankAccount.setAccountType(updatedBankAccount.getAccountType());
        bankAccount.setOwnerEmail(updatedBankAccount.getOwnerEmail());
        bankAccountRepository.save(bankAccount);
        return bankAccount;
    }

    public void deleteBankAccountById(Long id) {
        if (!bankAccountRepository.existsById(id)) {
            throw new BankAccountNotFoundException("Conta não encontrada: " + id);
        }
        bankAccountRepository.deleteById(id);
    }

}
