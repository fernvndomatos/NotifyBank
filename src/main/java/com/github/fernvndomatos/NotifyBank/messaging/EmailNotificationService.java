package com.github.fernvndomatos.NotifyBank.messaging;

import com.github.fernvndomatos.NotifyBank.dto.response.TransactionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationService {

    private final JavaMailSender mailSender;

    public void sendTransactionNotification(TransactionResponse transaction, String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("🔔 NotifyBank - New Transaction");
            message.setText(buildTransactionMessage(transaction));

            mailSender.send(message);

            log.info("Email sent successfully to: {}", email);

        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", email, e.getMessage(), e);
        }
    }

    private String buildTransactionMessage(TransactionResponse transaction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return String.format("""
            🔔 NotifyBank - New Transaction

            💰 Amount: R$ %.2f
            💳 Method: %s
            📂 Category: %s
            🏷️ MCC: %s
            🏦 Account: %s
            🕐 Date and time: %s

            ✅ Transaction processed successfully!
            """,
                transaction.amount(),
                transaction.paymentMethod(),
                transaction.category(),
                transaction.mcc(),
                transaction.accountNumber(),
                transaction.createdAt().format(formatter)
        );
    }
}