package com.kbank.convenience.stock.stock.mediation.client.dto

import lombok.Builder
import java.time.LocalDate

@Builder
@JvmRecord
data class GetListedStockFinancialStatementResponse(val baseDate: LocalDate,
                                                    val sales: Long,
                                                    val businessProfit: Long,
                                                    val netIncome: Long,
                                                    val asset: Long,
                                                    val debit: Long,
                                                    val capital: Long
)
