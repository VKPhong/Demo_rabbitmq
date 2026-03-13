package com.example.response.consumer;

import com.example.response.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ValidationFailedConsumer {

    public static class ValidationFailedEvent {
        private String requestId;
        private String reason;

        public ValidationFailedEvent() {
        }

        public String getRequestId() {
            return requestId;
        }

        public String getReason() {
            return reason;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

    @RabbitListener(queues = RabbitConfig.ACCOUNT_VALIDATION_FAILED_QUEUE)
    public void consume(ValidationFailedEvent event) {
        System.out.println("[response-service] FINAL FAILED => requestId="
                + event.getRequestId()
                + ", reason="
                + event.getReason());
    }
}