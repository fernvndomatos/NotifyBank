package com.github.fernvndomatos.NotifyBank.dto.response;

import com.github.fernvndomatos.NotifyBank.enums.Category;
import com.github.fernvndomatos.NotifyBank.enums.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionResponse(Long id,
                                  BigDecimal amount,
                                  PaymentMethod paymentMethod,
                                  Category category,
                                  String mcc,
                                  LocalDateTime createdAt,
                                  Long accountId,
                                  String accountNumber) {
}
