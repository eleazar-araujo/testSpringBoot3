package com.example.boot4fixture.legacy;

import com.example.boot4fixture.customer.CustomerService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

@Disabled("Compilation fixture only: useful for migration rewrite checks without making the normal build brittle.")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LegacySpringBootTestPatterns {

    @MockBean
    private CustomerService customerService;

    private MockMvc mockMvc;

    private TestRestTemplate testRestTemplate;

    @Test
    void oldTestSupportTypesArePresentInBoot35() {
    }
}