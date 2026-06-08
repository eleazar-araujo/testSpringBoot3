package com.example.boot4fixture.customer;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.boot4fixture.config.JpaAuditingConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(JpaAuditingConfiguration.class)
class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository repository;

    @Test
    void savesCustomerWithTicket() {
        Customer customer = new Customer("Ada Lovelace", "ada@example.com");
        customer.openTicket("Login", "Cannot sign in");

        Customer saved = repository.saveAndFlush(customer);

        assertThat(repository.findDetailedByEmail(saved.getEmail()))
                .hasValueSatisfying(found -> assertThat(found.getTickets()).hasSize(1));
    }
}