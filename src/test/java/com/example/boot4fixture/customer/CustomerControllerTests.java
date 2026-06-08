package com.example.boot4fixture.customer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.boot4fixture.customer.CustomerDtos.CustomerRequest;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CustomerController.class)
@Import(com.example.boot4fixture.config.SecurityConfiguration.class)
class CustomerControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService service;

    @Test
    @WithMockUser(roles = "SUPPORT")
    void createsCustomer() throws Exception {
        Customer customer = new Customer("Grace Hopper", "grace@example.com");
        setId(customer, UUID.fromString("11111111-1111-1111-1111-111111111111"));
        when(service.create(any(CustomerRequest.class))).thenReturn(customer);

        mvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Grace Hopper\",\"email\":\"grace@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Grace Hopper"));
    }

    private static void setId(Customer customer, UUID id) throws Exception {
        java.lang.reflect.Field field = Customer.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(customer, id);
    }
}