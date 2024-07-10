package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record GetListedStockPricesRequest(
        @DateTimeFormat(pattern = "yyyyMMddHHmmss")
        LocalDateTime baseDateTime,
        String itemCodeNumber,
        Long deltaDay
) {
}
