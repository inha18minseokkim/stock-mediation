package com.kbank.convenience.stock.stock.mediation.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.kbank.convenience.stock.stock.mediation.client.ListedStockService
import feign.Feign
import feign.jackson.JacksonDecoder
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@RequiredArgsConstructor
open class ApiClientConfig(
        private val objectMapper: ObjectMapper
) {
    @Bean
    open fun jacksonDecoder(): JacksonDecoder {
        return JacksonDecoder(objectMapper)
    }

    @Bean
    open fun listedStockService(): ListedStockService {
        return Feign.builder()
                .decoder(jacksonDecoder())
                .target(ListedStockService::class.java, "http://127.0.0.1:8088/listed-stock-service")
    }
}
