package com.kbank.convenience.stock.stock.mediation.client

import com.kbank.convenience.stock.stock.mediation.client.dto.*
import feign.Param
import feign.QueryMap
import feign.RequestLine
import org.springframework.stereotype.Component


@Component
class ListedStockServiceHolder(val listedStockService: ListedStockService) {
    suspend fun getListedStock(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockResponse {
        return listedStockService.getListedStock(itemCodeNumber)
    }

    suspend fun getListedStockLatestPrice(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockLatestPriceResponse {
        return listedStockService.getListedStockLatestPrice(itemCodeNumber)
    }

    suspend fun getListedStockPrices(@Param("itemCodeNumber") itemCodeNumber: String?, @QueryMap request: GetListedStockPricesRequest?): GetListedStockPricesResponse {
        return listedStockService.getListedStockPrices(itemCodeNumber,request)
    }

    suspend fun getListedStockFinancialRatio(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockFinancialRatioResponse {
        return listedStockService.getListedStockFinancialRatio(itemCodeNumber)
    }

    suspend fun getListedStockFinancialStatement(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockFinancialStatementResponse {
        return listedStockService.getListedStockFinancialStatement(itemCodeNumber)
    }

    suspend fun getListedStockSummary(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockSummaryResponse {
        return listedStockService.getListedStockSummary(itemCodeNumber)
    }

    suspend fun getListedStockPastFinancialStatements(@Param("itemCodeNumber") itemCodeNumber: String?, @Param("targetFinancialStatement") targetFinancialStatement: String?): GetListedStockPastFinancialStatementsResponse {
        return listedStockService.getListedStockPastFinancialStatements(itemCodeNumber,targetFinancialStatement)
    }
}
