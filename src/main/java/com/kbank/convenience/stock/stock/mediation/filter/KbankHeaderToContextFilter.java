package com.kbank.convenience.stock.stock.mediation.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@Slf4j
public class KbankHeaderToContextFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return chain.filter(exchange)
                .contextWrite(e -> {
                    //Webflux 기반 컨트롤러로 들어온 요청의 헤더를 context write 함
                    Map<String, String> singleValueMap = exchange.getRequest().getHeaders().toSingleValueMap();
                    log.debug("chain Header from stock-gateway {}",singleValueMap.get("kbank_standard_header"));
                    return e.put("kbank_standard_header", singleValueMap.get("kbank_standard_header"));
                });
    }
}
