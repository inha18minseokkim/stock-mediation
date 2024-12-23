package com.kbank.convenience.stock.stock.mediation.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Builder
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@Builder
@JvmRecord
data class GetListedStockPriceDetailResponse( //종목상세정보
        val stockKoreanName: String,
        val itemCodeNumber: String,
        val latestPrice: Long,
        val latestRatio: Double,  //일별시세
        val pricesCount: Long,
        val prices: List<PriceElement>,  //시세정보
        val previousDayMinPrice: Long,
        val previousDayMaxPrice: Long,
        val yearlyMinPrice: Long,
        val yearlyMaxPrice: Long

) {
    @Builder
    @JvmRecord
    data class PriceElement(
            @JsonFormat(pattern = "yyyyMMdd")
            @DateTimeFormat(pattern = "yyyyMMdd")
            val baseDate: LocalDate,
            val closePrice: Long,
            val changePrice: Long,
            val changeRate: Double
    )
}
