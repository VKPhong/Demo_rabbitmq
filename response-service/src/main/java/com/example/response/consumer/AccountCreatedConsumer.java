package com.example.response.consumer;

import com.example.response.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AccountCreatedConsumer {

    public static class AccountCreatedEvent {
        private String requestId;
        private String accountId;
        private String status;

        public AccountCreatedEvent() {
        }

        public String getRequestId() {
            return requestId;
        }

        public String getAccountId() {
            return accountId;
        }

        public String getStatus() {
            return status;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    @RabbitListener(queues = RabbitConfig.ACCOUNT_CREATED_QUEUE)
    public void consume(AccountCreatedEvent event) {
        System.out.println("[response-service] FINAL SUCCESS => requestId="
                + event.getRequestId()
                + ", accountId="
                + event.getAccountId()
                + ", status="
                + event.getStatus());
    }
}