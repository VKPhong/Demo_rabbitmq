package com.example.gateway.controller;

import com.example.gateway.dto.AccountCreateRequest;
import com.example.gateway.dto.AccountCreateResponse;
import com.example.gateway.messaging.AccountProducer;
import com.example.gateway.messaging.AccountRequestedEvent;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountProducer accountProducer;

    public AccountController(AccountProducer accountProducer) {
        this.accountProducer = accountProducer;
    }

    @PostMapping
    public ResponseEntity<AccountCreateResponse> createAccount(@Valid @RequestBody AccountCreateRequest request) {
        String requestId = "REQ-" + System.currentTimeMillis();

        AccountRequestedEvent event = new AccountRequestedEvent(
                requestId,
                request.getFullName(),
                request.getEmail(),
                request.getPhone()
        );

        accountProducer.send(event);

        AccountCreateResponse response = new AccountCreateResponse(
                requestId,
                "RECEIVED",
                "Request accepted for async processing"
        );

        return ResponseEntity.ok(response);
    }
}