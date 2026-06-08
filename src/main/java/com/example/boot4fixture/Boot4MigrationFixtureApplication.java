package com.example.boot4fixture;

import com.example.boot4fixture.customer.Customer;
import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

@SpringBootApplication
@EntityScan(basePackageClasses = Customer.class)
@EnableCaching
public class Boot4MigrationFixtureApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot4MigrationFixtureApplication.class, args);
    }

    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.of("migration-fixture");
    }
}