package com.kbank.convenience.stock.stock.mediation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kbank.convenience.stock.stock.mediation.client.ListedStockService;
import feign.jackson.JacksonDecoder;
import feign.reactive.ReactorDecoder;
import feign.reactive.ReactorFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiClientConfig {
    @Bean
    public JacksonDecoder jacksonDecoder(){
        JacksonDecoder jacksonDecoder = new JacksonDecoder();
        return jacksonDecoder;
    }
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
    @Bean
    public ListedStockService listedStockService() {
        ListedStockService target = ReactorFeign.builder()
                .decoder(new ReactorDecoder(jacksonDecoder()))
                .target(ListedStockService.class, "http://127.0.0.1:8088/listed-stock-service");
        return target;
    }
}
