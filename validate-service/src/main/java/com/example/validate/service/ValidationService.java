package com.example.validate.service;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public String validate(String fullName, String email, String phone) {
        if (fullName == null || fullName.isBlank()) {
            return "Full name must not be blank";
        }
        if (email == null || !email.contains("@")) {
            return "Invalid email format";
        }
        if (phone == null || phone.length() < 8) {
            return "Invalid phone number";
        }
        return null;
    }
}