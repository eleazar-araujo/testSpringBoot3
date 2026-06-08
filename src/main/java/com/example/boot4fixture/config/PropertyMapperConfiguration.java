package com.example.boot4fixture.config;

import java.util.concurrent.atomic.AtomicReference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class PropertyMapperConfiguration {

    @Bean
    ApplicationRunner propertyMapperRunner() {
        return args -> {
            AtomicReference<String> target = new AtomicReference<>();
            PropertyMapper.get().alwaysApplyingWhenNonNull().from(() -> "boot-3.5").to(target::set);
        };
    }
}