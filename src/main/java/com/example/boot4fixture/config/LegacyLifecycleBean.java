package com.example.boot4fixture.config;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class LegacyLifecycleBean {

    private boolean initialized;

    @PostConstruct
    void initialize() {
        initialized = true;
    }

    public boolean isInitialized() {
        return initialized;
    }
}