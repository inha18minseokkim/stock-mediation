package com.kbank.convenience.stock.stock.mediation.client.dto

import lombok.Builder
import java.time.LocalDate

@Builder
@JvmRecord
data class GetListedStockFinancialRatioResponse(val baseDate: LocalDate,
                                                val bps: Double,
                                                val eps: Double,
                                                val per: Double,
                                                val pbr: Double,
                                                val dps: Double,
                                                val dividendEarningRate: Double
)
