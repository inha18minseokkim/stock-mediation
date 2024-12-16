package com.kbank.convenience.stock.stock.mediation.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ObjectMapperConfig {
    @Bean
    open fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper().registerKotlinModule();
        objectMapper.registerModule(JavaTimeModule())
        return objectMapper
    }
}
