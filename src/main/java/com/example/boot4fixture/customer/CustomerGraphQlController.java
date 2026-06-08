package com.example.boot4fixture.customer;

import com.example.boot4fixture.customer.CustomerDtos.CustomerResponse;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerGraphQlController {

    private final CustomerService service;

    public CustomerGraphQlController(CustomerService service) {
        this.service = service;
    }

    @QueryMapping
    List<CustomerResponse> customers() {
        return service.list(PageRequest.of(0, 20)).map(CustomerResponse::from).toList();
    }
}