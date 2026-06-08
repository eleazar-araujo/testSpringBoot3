package com.example.boot4fixture.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration(proxyBeanMethods = false)
public class HttpClientConfiguration {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.rootUri("https://example.invalid").build();
    }

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder.baseUrl("https://example.invalid").build();
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("https://example.invalid").build();
    }
}