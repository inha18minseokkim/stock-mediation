package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record GetListedStockLatestPriceResponse(
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
        String tradingStop
) {
}
