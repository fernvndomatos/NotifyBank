package com.github.fernvndomatos.NotifyBank.mapper;

import com.github.fernvndomatos.NotifyBank.dto.request.BankAccountRequest;
import com.github.fernvndomatos.NotifyBank.dto.response.BankAccountResponse;
import com.github.fernvndomatos.NotifyBank.entity.BankAccount;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BankAccountMapper {

    public static BankAccount toBankAccount(BankAccountRequest request){

        return BankAccount.builder()
                .bank(request.bank())
                .accountNumber(request.accountNumber())
                .accountType(request.accountType())
                .currencyType(request.currencyType())
                .accountBalance(request.accountBalance())
                .ownerEmail(request.ownerEmail())
                .build();
    }

    public static BankAccountResponse toBankAccountResponse(BankAccount bankAccount){

        return BankAccountResponse.builder()
                .id(bankAccount.getId())
                .bank(bankAccount.getBank())
                .accountNumber(bankAccount.getAccountNumber())
                .accountType(bankAccount.getAccountType())
                .currencyType(bankAccount.getCurrencyType())
                .accountBalance(bankAccount.getAccountBalance())
                .ownerEmail(bankAccount.getOwnerEmail())
                .createdAt(bankAccount.getCreatedAt())
                .build();
    }
}
