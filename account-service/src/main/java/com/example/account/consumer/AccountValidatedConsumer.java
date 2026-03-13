package com.example.account.consumer;

import com.example.account.config.RabbitConfig;
import com.example.account.messaging.AccountCreatedEvent;
import com.example.account.messaging.AccountValidatedEvent;
import com.example.account.service.AccountCreationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountValidatedConsumer {

    private final AccountCreationService accountCreationService;
    private final RabbitTemplate rabbitTemplate;

    public AccountValidatedConsumer(AccountCreationService accountCreationService, RabbitTemplate rabbitTemplate) {
        this.accountCreationService = accountCreationService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitConfig.ACCOUNT_VALIDATED_QUEUE)
    public void consume(AccountValidatedEvent event) {
        System.out.println("[account-service] Received account.validated: " + event.getRequestId());

        String accountId = accountCreationService.createAccount(
                event.getRequestId(),
                event.getFullName(),
                event.getEmail(),
                event.getPhone()
        );

        AccountCreatedEvent createdEvent = new AccountCreatedEvent(
                event.getRequestId(),
                accountId,
                "CREATED"
        );

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ACCOUNT_CREATED,
                createdEvent
        );

        System.out.println("[account-service] Published account.created: " + event.getRequestId() + ", accountId=" + accountId);
    }
}