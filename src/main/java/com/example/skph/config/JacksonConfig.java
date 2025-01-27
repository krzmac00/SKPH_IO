package com.example.skph.config;
import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JtsModule()); // Rejestracja modułu JTS
        mapper.registerModule(new JavaTimeModule()); // Rejestracja modułu JavaTime do obsługi LocalDateTime
        return mapper;
    }
}
