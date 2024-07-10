package com.kbank.convenience.stock.stock.mediation.controller.dto;

import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record GetListedStockPriceDetailRequest(
        @DateTimeFormat(pattern = "yyyyMMddHHmmss")
        LocalDateTime baseDateTime,
        String itemCodeNumber
) {
}
