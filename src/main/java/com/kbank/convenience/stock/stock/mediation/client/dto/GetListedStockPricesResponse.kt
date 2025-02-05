package com.kbank.convenience.stock.stock.mediation.client.dto

import lombok.Builder
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@Builder
@JvmRecord
data class GetListedStockPricesResponse(val maxPrice: Long,
                                        val minPrice: Long,
                                        val meanPrice: Double,
                                        val list: List<GetListedStockPricesSubResponse>
) {
    @Builder
    @JvmRecord
    data class GetListedStockPricesSubResponse(
                                          val baseDate: LocalDate,
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
                                          val tradingStop: String
    )
}
