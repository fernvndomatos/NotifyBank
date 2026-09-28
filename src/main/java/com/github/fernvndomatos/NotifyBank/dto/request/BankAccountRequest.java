package com.github.fernvndomatos.NotifyBank.dto.request;

import com.github.fernvndomatos.NotifyBank.enums.AccountType;
import com.github.fernvndomatos.NotifyBank.enums.Bank;
import com.github.fernvndomatos.NotifyBank.enums.CurrencyType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BankAccountRequest(@NotNull
                                 Bank bank,
                                 @NotBlank
                                 String accountNumber,
                                 @NotNull
                                 AccountType accountType,
                                 @NotNull
                                 CurrencyType currencyType,
                                 @NotNull
                                 BigDecimal accountBalance,
                                 @NotBlank @Email
                                 String ownerEmail){
}
