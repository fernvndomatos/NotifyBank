package com.github.fernvndomatos.NotifyBank.mapper;

import com.github.fernvndomatos.NotifyBank.dto.request.TransactionRequest;
import com.github.fernvndomatos.NotifyBank.dto.response.TransactionResponse;
import com.github.fernvndomatos.NotifyBank.entity.Transaction;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransactionMapper {

    public static Transaction toTransaction(TransactionRequest request){

        return Transaction.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .category(request.category())
                .mcc(request.mcc())
                .build();
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction){

        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .paymentMethod(transaction.getPaymentMethod())
                .category(transaction.getCategory())
                .mcc(transaction.getMcc())
                .createdAt(transaction.getCreatedAt())
                .accountId(transaction.getAccount().getId())
                .accountNumber(transaction.getAccount().getAccountNumber())
                .build();
    }
}
