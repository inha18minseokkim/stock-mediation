package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GetListedStockFinancialStatementResponse(
        LocalDate baseDate,
        Long sales,
        Long businessProfit,
        Long netIncome,
        Long asset,
        Long debit,
        Long capital
) {
}
