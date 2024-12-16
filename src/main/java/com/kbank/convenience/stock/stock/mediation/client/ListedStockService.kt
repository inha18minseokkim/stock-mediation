package com.kbank.convenience.stock.stock.mediation.client

import com.kbank.convenience.stock.stock.mediation.client.dto.*
import feign.Param
import feign.QueryMap
import feign.RequestLine


interface ListedStockService {
    @RequestLine("GET /v1/listedStock/{itemCodeNumber}")
    fun getListedStock(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockResponse

    @RequestLine("GET /v1/listedStock/{itemCodeNumber}/price/latest")
    fun getListedStockLatestPrice(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockLatestPriceResponse

    @RequestLine("GET /v1/listedStock/{itemCodeNumber}/prices")
    fun getListedStockPrices(@Param("itemCodeNumber") itemCodeNumber: String?, @QueryMap request: GetListedStockPricesRequest?): GetListedStockPricesResponse

    @RequestLine("GET /v1/listedStock/financial/ratio/{itemCodeNumber}")
    fun getListedStockFinancialRatio(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockFinancialRatioResponse

    @RequestLine("GET /v1/listedStock/financial/statement/latest/{itemCodeNumber}")
    fun getListedStockFinancialStatement(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockFinancialStatementResponse

    @RequestLine("GET /v1/listedStock/summary/{itemCodeNumber}")
    fun getListedStockSummary(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockSummaryResponse

    @RequestLine("GET /v1/listedStock/financial/statement/past/{itemCodeNumber}/{targetFinancialStatement}")
    fun getListedStockPastFinancialStatements(@Param("itemCodeNumber") itemCodeNumber: String?, @Param("targetFinancialStatement") targetFinancialStatement: String?): GetListedStockPastFinancialStatementsResponse
}
