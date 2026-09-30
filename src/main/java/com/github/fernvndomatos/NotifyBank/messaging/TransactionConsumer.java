package com.github.fernvndomatos.NotifyBank.messaging;

import com.github.fernvndomatos.NotifyBank.config.RabbitMQConfig;
import com.github.fernvndomatos.NotifyBank.dto.response.TransactionResponse;
import com.github.fernvndomatos.NotifyBank.entity.BankAccount;
import com.github.fernvndomatos.NotifyBank.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionConsumer {

    private final BankAccountRepository bankAccountRepository;
    private final EmailNotificationService emailNotificationService;

    @RabbitListener(queues = RabbitMQConfig.TRANSACTION_QUEUE)
    public void handleTransactionCreated(TransactionResponse transaction) {
        log.info("Transaction received via event: id={}, accountId={}, amount={}",
                transaction.id(), transaction.accountId(), transaction.amount());

        bankAccountRepository.findById(transaction.accountId())
                .map(BankAccount::getOwnerEmail)
                .ifPresentOrElse(
                        email -> emailNotificationService.sendTransactionNotification(transaction, email),
                        () -> log.warn("Account {} has no registered email, notification not sent", transaction.accountId())
                );
    }
}