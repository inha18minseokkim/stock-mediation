package com.kbank.convenience.stock.stock.mediation.client.dto

import lombok.Builder
import java.time.LocalDateTime

@Builder
@JvmRecord
data class GetListedStockRankResponse(val list: List<GetListedStockRankSubResponse>
) {
    @Builder
    @JvmRecord
    data class GetListedStockRankSubResponse(
                                        val baseDateTime: LocalDateTime,
                                        val itemCodeNumber: String,
                                        val stockKoreanName: String,
                                        val openPrice: Long,
                                        val highPrice: Long,
                                        val lowPrice: Long,
                                        val closePrice: Long,
                                        val volume: Long,
                                        val value: Long,
                                        val changePrice: Long,
                                        val changeRate: Double,
                                        val alreadyIssuedStock: Long,
                                        val marketPriceTotal: Long,
                                        val tradingStop: Boolean
    )
}