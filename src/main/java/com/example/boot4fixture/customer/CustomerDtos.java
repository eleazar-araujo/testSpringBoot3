package com.example.boot4fixture.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public final class CustomerDtos {

    private CustomerDtos() {
    }

    public record CustomerRequest(
            @NotBlank @Size(max = 120) String name,
            @NotBlank @Email @Size(max = 180) String email) {
    }

    public record TicketRequest(
            @NotBlank @Size(max = 140) String subject,
            @NotBlank @Size(max = 2000) String description) {
    }

    public record CustomerResponse(UUID id, String name, String email, int openTickets) {
        static CustomerResponse from(Customer customer) {
            long openTickets = customer.getTickets().stream()
                    .filter(ticket -> ticket.getStatus() == TicketStatus.OPEN)
                    .count();
            return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail(), Math.toIntExact(openTickets));
        }
    }
}