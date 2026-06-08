package com.example.boot4fixture.customer;

import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(CustomerGraphQlController.class)
class CustomerGraphQlControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private CustomerService service;

    @Test
    void listsCustomers() {
        when(service.list(PageRequest.of(0, 20))).thenReturn(new PageImpl<>(List.of(new Customer("Linus Torvalds", "linus@example.com"))));

        graphQlTester.document("{ customers { name email openTickets } }")
                .execute()
                .path("customers[0].name")
                .entity(String.class)
                .isEqualTo("Linus Torvalds");
    }
}