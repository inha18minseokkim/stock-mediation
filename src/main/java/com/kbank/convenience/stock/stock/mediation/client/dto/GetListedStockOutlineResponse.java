package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetListedStockOutlineResponse(
        String itemCodeNumber,
        String stockKoreanName,
        String summaryTitle,
        List<String> summaryContents,
        String statusTitle,
        List<String> statusContents,
        LocalDateTime updatedAt
) {
}
