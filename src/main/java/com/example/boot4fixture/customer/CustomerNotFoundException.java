package com.example.boot4fixture.customer;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {

    CustomerNotFoundException(UUID id) {
        super("Customer %s was not found".formatted(id));
    }
}