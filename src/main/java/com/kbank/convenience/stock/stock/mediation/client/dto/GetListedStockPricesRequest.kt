package com.kbank.convenience.stock.stock.mediation.client.dto

import lombok.Builder
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Builder
@JvmRecord
data class GetListedStockPricesRequest(@field:DateTimeFormat(pattern = "yyyyMMddHHmmss")
                                       @param:DateTimeFormat(pattern = "yyyyMMddHHmmss")
                                       val baseDateTime: LocalDateTime,
                                       val deltaDay: Long
)
