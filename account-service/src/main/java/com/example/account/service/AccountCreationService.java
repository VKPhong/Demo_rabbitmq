package com.example.account.service;

import org.springframework.stereotype.Service;

@Service
public class AccountCreationService {

    public String createAccount(String requestId, String fullName, String email, String phone) {
        try {
            System.out.println("[account-service] Processing create account for: " + requestId);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted", e);
        }

        return "ACC-" + System.currentTimeMillis();
    }
}