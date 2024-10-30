package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetListedStockPricesResponse(
    Long maxPrice,
    Long minPrice,
    Double meanPrice,
    List<GetListedStockPricesSubResponse> list
) {
    @Builder
    public record GetListedStockPricesSubResponse(
            LocalDate baseDate,
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
            String tradingStop
    ) {

    }
}
