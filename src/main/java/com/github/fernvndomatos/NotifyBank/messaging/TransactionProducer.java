package com.github.fernvndomatos.NotifyBank.messaging;

import com.github.fernvndomatos.NotifyBank.config.RabbitMQConfig;
import com.github.fernvndomatos.NotifyBank.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishTransactionCreated(TransactionResponse transaction) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.TRANSACTION_EXCHANGE,
                RabbitMQConfig.TRANSACTION_CREATED_ROUTING_KEY,
                transaction
        );
        log.info("transaction created");
    }
}