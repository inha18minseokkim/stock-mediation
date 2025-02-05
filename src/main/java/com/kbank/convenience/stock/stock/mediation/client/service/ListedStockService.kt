package com.kbank.convenience.stock.stock.mediation.client.service

import com.kbank.convenience.stock.stock.mediation.client.dto.*
import reactor.core.publisher.Mono

interface ListedStockService {
    //    @RequestLine("GET /v1/listedStock/{itemCodeNumber}")
    suspend fun getListedStock(itemCodeNumber: String): GetListedStockResponse?

    //    @RequestLine("GET /v1/listedStock/{itemCodeNumber}/price/latest")
    suspend fun getListedStockLatestPrice(itemCodeNumber: String): GetListedStockLatestPriceResponse?

    //    @RequestLine("GET /v1/listedStock/{itemCodeNumber}/prices")
    suspend fun getListedStockPrices(
        itemCodeNumber: String,
        request: GetListedStockPricesRequest
    ): GetListedStockPricesResponse?

    //    @RequestLine("GET /v1/listedStock/financial/ratio/{itemCodeNumber}")
    suspend fun getListedStockFinancialRatio(itemCodeNumber: String): GetListedStockFinancialRatioResponse?

    //    @RequestLine("GET /v1/listedStock/financial/statement/latest/{itemCodeNumber}")
    suspend fun getListedStockFinancialStatement(itemCodeNumber: String): GetListedStockFinancialStatementResponse?

    //    @RequestLine("GET /v1/listedStock/summary/{itemCodeNumber}")
    suspend fun getListedStockSummary(itemCodeNumber: String): GetListedStockSummaryResponse?

    //    @RequestLine("GET /v1/listedStock/financial/statement/past/{itemCodeNumber}/{targetFinancialStatement}")
    suspend fun getListedStockPastFinancialStatements(
        itemCodeNumber: String, targetFinancialStatement: String
    ): GetListedStockPastFinancialStatementsResponse?
}
