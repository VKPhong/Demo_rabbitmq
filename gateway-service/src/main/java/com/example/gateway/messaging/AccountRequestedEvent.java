package com.example.gateway.messaging;

public class AccountRequestedEvent {

    private String requestId;
    private String fullName;
    private String email;
    private String phone;

    public AccountRequestedEvent() {
    }

    public AccountRequestedEvent(String requestId, String fullName, String email, String phone) {
        this.requestId = requestId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}