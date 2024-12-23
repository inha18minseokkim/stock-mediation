package com.kbank.convenience.stock.stock.mediation.client.dto

import lombok.Builder
import java.time.LocalDate

@Builder
@JvmRecord
data class GetListedStockPastFinancialStatementsResponse(val itemCodeNumber: String,
                                                         val targetStatement: String,
                                                         val list: List<PastFinancialStatement>

) {
    @Builder
    @JvmRecord
    data class PastFinancialStatement(
                                    val baseDate: LocalDate,
                                    val amount: Long
    )
}
