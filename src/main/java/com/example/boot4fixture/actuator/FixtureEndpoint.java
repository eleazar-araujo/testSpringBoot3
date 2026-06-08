package com.example.boot4fixture.actuator;

import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "fixture")
public class FixtureEndpoint {

    @ReadOperation
    Map<String, Object> inspect(@Selector String area, @Nullable String filter) {
        return Map.of("area", area, "filter", filter == null ? "none" : filter);
    }
}