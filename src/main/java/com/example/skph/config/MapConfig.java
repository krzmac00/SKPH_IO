package com.example.skph.config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MapConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
