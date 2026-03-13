package com.example.gateway.messaging;

import com.example.gateway.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountProducer {

    private final RabbitTemplate rabbitTemplate;

    public AccountProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(AccountRequestedEvent event) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ACCOUNT_REQUESTED,
                event
        );
        System.out.println("[gateway-service] Published account.requested: " + event.getRequestId());
    }
}