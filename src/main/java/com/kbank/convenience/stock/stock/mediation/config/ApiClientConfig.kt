package com.kbank.convenience.stock.stock.mediation.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.kbank.convenience.stock.stock.mediation.client.impl.ListedStockServiceImpl
import com.kbank.convenience.stock.stock.mediation.client.service.ListedStockService
import com.kbank.convenience.stock.stock.mediation.logger
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.util.context.ContextView

@Configuration
open class ApiClientConfig(
        val log: Logger = logger()
) {
    @Bean
    open fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper().apply {
            registerModule(JavaTimeModule())
//            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
//            disable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
    }

    @Bean
    open fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
                .filter(kbankHeaderPropagationFilter()
                )
    }

    @Bean
    open fun listedStockService(): ListedStockService {
        return ListedStockServiceImpl(
            webClientBuilder()
                .baseUrl("http://127.0.0.1:38080/listed-stock-service")
//                .baseUrl("http://125.176.39.143:8880/convenience/stock/listed-stock-service")
            ,objectMapper()
        )
    }

    companion object {
        var log = logger()
        private fun kbankHeaderPropagationFilter(): ExchangeFilterFunction {
            return ExchangeFilterFunction
                    .ofRequestProcessor { request: ClientRequest ->
                        Mono.deferContextual<ClientRequest> { context: ContextView ->
                            //KbankHeaderToContextFilter 에서 ContextWrite 한 헤더 값을 여기서 Context get 함
                            log.debug("WebClient header from Context {}", context.get<Any>("kbank_standard_header").toString())
                            log.debug("{}", request.url())
                            //ClientRequest를 새로 만들어서 헤더갑을 propagate 함. 이러면 종단 파드에 헤더 전달 가능.
                            val build = ClientRequest.from(request)
                                    .header("kbank_standard_header", context.get<Any>("kbank_standard_header").toString())
                                    .build()
                            Mono.just<ClientRequest>(build)
                        }
                    }
        }
    }
}
