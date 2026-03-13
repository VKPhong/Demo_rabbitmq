package com.example.validate.consumer;

import com.example.validate.config.RabbitConfig;
import com.example.validate.messaging.AccountRequestedEvent;
import com.example.validate.messaging.AccountValidatedEvent;
import com.example.validate.messaging.ValidationFailedEvent;
import com.example.validate.service.ValidationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestedConsumer {

    private final ValidationService validationService;
    private final RabbitTemplate rabbitTemplate;

    public AccountRequestedConsumer(ValidationService validationService, RabbitTemplate rabbitTemplate) {
        this.validationService = validationService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitConfig.ACCOUNT_REQUESTED_QUEUE)
    public void consume(AccountRequestedEvent event) {
        System.out.println("[validate-service] Received account.requested: " + event.getRequestId());

        String error = validationService.validate(
                event.getFullName(),
                event.getEmail(),
                event.getPhone()
        );

        if (error != null) {
            ValidationFailedEvent failedEvent = new ValidationFailedEvent(event.getRequestId(), error);

            rabbitTemplate.convertAndSend(
                    RabbitConfig.EXCHANGE,
                    RabbitConfig.ACCOUNT_VALIDATION_FAILED,
                    failedEvent
            );

            System.out.println("[validate-service] Validation FAILED: " + event.getRequestId() + " - " + error);
            return;
        }

        AccountValidatedEvent validatedEvent = new AccountValidatedEvent(
                event.getRequestId(),
                event.getFullName(),
                event.getEmail(),
                event.getPhone()
        );

        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ACCOUNT_VALIDATED,
                validatedEvent
        );

        System.out.println("[validate-service] Validation PASSED: " + event.getRequestId());
    }
}