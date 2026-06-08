package com.example.boot4fixture.customer;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.boot4fixture.customer.CustomerDtos.CustomerRequest;
import com.example.boot4fixture.customer.CustomerDtos.CustomerResponse;
import com.example.boot4fixture.customer.CustomerDtos.TicketRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EntityModel<CustomerResponse> create(@Valid @RequestBody CustomerRequest request) {
        return toModel(service.create(request));
    }

    @GetMapping
    CollectionModel<EntityModel<CustomerResponse>> list(@RequestParam(defaultValue = "0") int page) {
        return CollectionModel.of(service.list(PageRequest.of(page, 20)).map(this::toModel));
    }

    @GetMapping("/{id}")
    EntityModel<CustomerResponse> get(@PathVariable UUID id) {
        return toModel(service.get(id));
    }

    @PostMapping("/{id}/tickets")
    EntityModel<CustomerResponse> openTicket(@PathVariable UUID id, @Valid @RequestBody TicketRequest request) {
        return toModel(service.openTicket(id, request));
    }

    private EntityModel<CustomerResponse> toModel(Customer customer) {
        CustomerResponse response = CustomerResponse.from(customer);
        return EntityModel.of(response,
                linkTo(methodOn(CustomerController.class).get(response.id())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).list(0)).withRel("customers"));
    }
}