package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GetListedStockFinancialRatioResponse(
        LocalDate baseDate,
        Double bps,
        Double eps,
        Double per,
        Double pbr,
        Double dps,
        Double dividendEarningRate
) {
}
