package com.example.account.messaging;

public class AccountCreatedEvent {

    private String requestId;
    private String accountId;
    private String status;

    public AccountCreatedEvent() {
    }

    public AccountCreatedEvent(String requestId, String accountId, String status) {
        this.requestId = requestId;
        this.accountId = accountId;
        this.status = status;
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