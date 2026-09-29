package com.github.fernvndomatos.NotifyBank.messaging;

import com.github.fernvndomatos.NotifyBank.config.RabbitMQConfig;
import com.github.fernvndomatos.NotifyBank.dto.response.TransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TransactionConsumer {

    @RabbitListener(queues = RabbitMQConfig.TRANSACTION_QUEUE)
    public void handleTransactionCreated(TransactionResponse transaction) {
        log.info("Transação recebida via evento: id={}, accountId={}, amount={}",
                transaction.id(), transaction.accountId(), transaction.amount());
    }
}