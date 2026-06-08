package com.example.boot4fixture.customer;

import com.example.boot4fixture.customer.CustomerDtos.CustomerRequest;
import com.example.boot4fixture.customer.CustomerDtos.TicketRequest;
import java.util.UUID;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Customer create(CustomerRequest request) {
        return repository.save(new Customer(request.name(), request.email()));
    }

    @Transactional(readOnly = true)
    public Page<Customer> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Cacheable("customers")
    @Transactional(readOnly = true)
    public Customer get(UUID id) {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional
    public Customer openTicket(UUID customerId, TicketRequest request) {
        Customer customer = get(customerId);
        customer.openTicket(request.subject(), request.description());
        return customer;
    }
}