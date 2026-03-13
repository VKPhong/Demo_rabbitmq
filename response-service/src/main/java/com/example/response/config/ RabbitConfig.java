package com.example.response.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "account.exchange";

    public static final String ACCOUNT_REQUESTED = "account.requested";
    public static final String ACCOUNT_VALIDATED = "account.validated";
    public static final String ACCOUNT_CREATED = "account.created";
    public static final String ACCOUNT_VALIDATION_FAILED = "account.validation.failed";

    public static final String ACCOUNT_REQUESTED_QUEUE = "account.requested.q";
    public static final String ACCOUNT_VALIDATED_QUEUE = "account.validated.q";
    public static final String ACCOUNT_CREATED_QUEUE = "account.created.q";
    public static final String ACCOUNT_VALIDATION_FAILED_QUEUE = "account.validation.failed.q";

    @Bean
    public DirectExchange accountExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue accountRequestedQueue() {
        return QueueBuilder.durable(ACCOUNT_REQUESTED_QUEUE).build();
    }

    @Bean
    public Queue accountValidatedQueue() {
        return QueueBuilder.durable(ACCOUNT_VALIDATED_QUEUE).build();
    }

    @Bean
    public Queue accountCreatedQueue() {
        return QueueBuilder.durable(ACCOUNT_CREATED_QUEUE).build();
    }

    @Bean
    public Queue accountValidationFailedQueue() {
        return QueueBuilder.durable(ACCOUNT_VALIDATION_FAILED_QUEUE).build();
    }

    @Bean
    public Binding requestedBinding() {
        return BindingBuilder.bind(accountRequestedQueue()).to(accountExchange()).with(ACCOUNT_REQUESTED);
    }

    @Bean
    public Binding validatedBinding() {
        return BindingBuilder.bind(accountValidatedQueue()).to(accountExchange()).with(ACCOUNT_VALIDATED);
    }

    @Bean
    public Binding createdBinding() {
        return BindingBuilder.bind(accountCreatedQueue()).to(accountExchange()).with(ACCOUNT_CREATED);
    }

    @Bean
    public Binding validationFailedBinding() {
        return BindingBuilder.bind(accountValidationFailedQueue()).to(accountExchange()).with(ACCOUNT_VALIDATION_FAILED);
    }

    @Bean
    public MessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            MessageConverter messageConverter
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}