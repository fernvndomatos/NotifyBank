package com.github.fernvndomatos.NotifyBank.dto.request;

import com.github.fernvndomatos.NotifyBank.enums.Category;
import com.github.fernvndomatos.NotifyBank.enums.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionRequest(@NotNull
                                 Long accountId,
                                 @NotNull
                                 BigDecimal amount,
                                 @NotNull
                                 PaymentMethod paymentMethod,
                                 @NotNull
                                 Category category,
                                 @NotBlank @Pattern(regexp = "\\d{4}")
                                 String mcc) {
}
