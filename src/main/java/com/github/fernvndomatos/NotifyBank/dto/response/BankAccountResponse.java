package com.github.fernvndomatos.NotifyBank.dto.response;

import com.github.fernvndomatos.NotifyBank.enums.AccountType;
import com.github.fernvndomatos.NotifyBank.enums.Bank;
import com.github.fernvndomatos.NotifyBank.enums.CurrencyType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record BankAccountResponse(Long id,
                                  Bank bank,
                                  String accountNumber,
                                  AccountType accountType,
                                  CurrencyType currencyType,
                                  BigDecimal accountBalance,
                                  String ownerEmail,
                                  LocalDateTime createdAt) {
}
