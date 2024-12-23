package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record GetListedStockPastFinancialStatementsResponse(
        String itemCodeNumber,
        String targetStatement,
        List<PastFinancialStatement> list

) {
    @Builder
    public record PastFinancialStatement(
            LocalDate baseDate,
            Long amount
    ) {}
}
