package com.example.gateway.dto;

import jakarta.validation.constraints.NotBlank;

public class AccountCreateRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    public AccountCreateRequest() {
    }

    public AccountCreateRequest(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
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