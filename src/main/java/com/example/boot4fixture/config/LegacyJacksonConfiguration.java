package com.example.boot4fixture.config;

import com.example.boot4fixture.customer.TicketStatus;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class LegacyJacksonConfiguration {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2Customizer() {
        return builder -> builder.indentOutput(true);
    }

    @JsonComponent
    static class TicketStatusSerializer extends JsonSerializer<TicketStatus> {

        @Override
        public void serialize(TicketStatus value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
            generator.writeString(value.name().toLowerCase());
        }
    }
}