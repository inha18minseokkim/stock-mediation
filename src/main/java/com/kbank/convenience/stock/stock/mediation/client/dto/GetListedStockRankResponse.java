package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetListedStockRankResponse(
        List<GetListedStockRankSubResponse> list
) {
    @Builder
    public record GetListedStockRankSubResponse(
            LocalDateTime baseDateTime,
            String itemCodeNumber,
            String stockKoreanName,
            Long openPrice,
            Long highPrice,
            Long lowPrice,
            Long closePrice,
            Long volume,
            Long value,
            Long changePrice,
            Double changeRate,
            Long alreadyIssuedStock,
            Long marketPriceTotal,
            Boolean tradingStop
    ) {}
}
