package com.example.boot4fixture.nativeimage;

import com.example.boot4fixture.customer.Customer;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Configuration(proxyBeanMethods = false)
@ImportRuntimeHints(FixtureRuntimeHints.Hints.class)
public class FixtureRuntimeHints {

    static class Hints implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.reflection().registerType(Customer.class, MemberCategory.DECLARED_FIELDS);
            hints.resources().registerPattern("/fixtures/*.json");
        }
    }
}