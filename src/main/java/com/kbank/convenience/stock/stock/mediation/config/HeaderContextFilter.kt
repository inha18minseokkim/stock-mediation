package com.kbank.convenience.stock.stock.mediation.config

import com.kbank.convenience.stock.stock.mediation.logger
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono


@Component
class HeaderContextFilter : WebFilter {
    private val  log = logger()
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        // 모든 헤더를 key-value 형태의 Map으로 가져옴 (첫 번째 값만 사용)
        val headersMap = exchange.request.headers.toSingleValueMap()

        return chain.filter(exchange)
                .contextWrite { context ->
                    // headersMap의 모든 엔트리를 Reactor Context에 추가
                    headersMap.entries.fold(context) { ctx, entry ->
                        log.info("${entry.key} ${entry.value}")
                        ctx.put(entry.key, entry.value)
                    }
                }
    }
}