package com.example.validate.messaging;

public class ValidationFailedEvent {

    private String requestId;
    private String reason;

    public ValidationFailedEvent() {
    }

    public ValidationFailedEvent(String requestId, String reason) {
        this.requestId = requestId;
        this.reason = reason;
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