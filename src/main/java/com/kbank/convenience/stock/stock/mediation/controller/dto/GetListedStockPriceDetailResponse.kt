package com.kbank.convenience.stock.stock.mediation.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetListedStockPriceDetailResponse(
        //종목상세정보
        String stockKoreanName,
        String itemCodeNumber,
        Long latestPrice,
        Double latestRatio,
        //일별시세
        Long pricesCount,
        List<PriceElement> prices,
        //시세정보
        Long previousDayMinPrice,
        Long previousDayMaxPrice,
        Long yearlyMinPrice,
        Long yearlyMaxPrice

) {
    @Builder
    public record PriceElement(
            @JsonFormat(pattern = "yyyyMMdd")
            @DateTimeFormat(pattern = "yyyyMMdd")
            LocalDate baseDate,
            Long closePrice,
            Long changePrice,
            Double changeRate
    ) {}
}
