package com.kbank.convenience.stock.stock.mediation.filter

import com.kbank.convenience.stock.stock.mediation.logger
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import reactor.util.context.Context

@Component
@Slf4j
class KbankHeaderToContextFilter : WebFilter {
    private var log = logger()
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        return chain.filter(exchange)
                .contextWrite { e: Context ->
                    //Webflux 기반 컨트롤러로 들어온 요청의 헤더를 context write 함
                    val singleValueMap = exchange.request.headers.toSingleValueMap()

                    log.debug("chain Header from stock-gateway {}", singleValueMap["kbank_standard_header"])
                    e.put("kbank_standard_header", singleValueMap["kbank_standard_header"]!!)
                }
    }
}
