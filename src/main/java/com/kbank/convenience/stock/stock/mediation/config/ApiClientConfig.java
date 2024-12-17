package com.kbank.convenience.stock.stock.mediation.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kbank.convenience.stock.stock.mediation.client.ListedStockService;
import feign.jackson.JacksonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.webclient.WebReactiveFeign;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
@Slf4j
public class ApiClientConfig {
    @Bean
    public JacksonDecoder jacksonDecoder(){
        JacksonDecoder jacksonDecoder = new JacksonDecoder(List.of(new JavaTimeModule()));
        return jacksonDecoder;
    }
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .filter(ExchangeFilterFunction
                        .ofRequestProcessor(
                        request -> Mono.deferContextual(context -> {
                            //HeaderPropagateFilter 에서 ContextWrite 한 헤더 값을 여기서 Context get 함
                            log.debug("WebClient header from Context {}",context.get("kbank_standard_header").toString());
                            log.debug("{}",request.url());
                            //ClientRequest를 새로 만들어서 헤더갑을 propagate 함. 이러면 종단 파드에 헤더 전달 가능.
                            ClientRequest build = ClientRequest.from(request)
                                    .header("kbank_standard_header",context.get("kbank_standard_header").toString())
                                    .build();
                            return Mono.just(build);
                        }
                            )
                        )
                );
    }
    @Bean
    public ListedStockService listedStockService() {
        ListedStockService target =  WebReactiveFeign
                .<ListedStockService>builder(webClientBuilder())
                .target(ListedStockService.class,"http://127.0.0.1:8088/listed-stock-service")
                ;
        return target;
    }
}
