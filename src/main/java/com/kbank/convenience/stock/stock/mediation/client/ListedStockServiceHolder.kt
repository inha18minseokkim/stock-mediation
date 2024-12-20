package com.kbank.convenience.stock.stock.mediation.client

import com.kbank.convenience.stock.stock.mediation.client.dto.*
import com.kbank.convenience.stock.stock.mediation.logger
import feign.Param
import feign.QueryMap
import feign.RequestLine
import kotlinx.coroutines.*
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import kotlin.coroutines.coroutineContext


@Component
@Slf4j
class ListedStockServiceHolder(val listedStockService: ListedStockService,
) {
    val log = logger()
    fun getListedStock(@Param("itemCodeNumber") itemCodeNumber: String?): Deferred<GetListedStockResponse> {
        return CoroutineScope(Dispatchers.IO).async {  listedStockService.getListedStock(itemCodeNumber) }
    }

    fun getListedStockLatestPrice(@Param("itemCodeNumber") itemCodeNumber: String?): Deferred<GetListedStockLatestPriceResponse> {
//        log.info("getListedStockLatestPrice, {}",coroutineContext.isActive)
        return CoroutineScope(Dispatchers.IO).async { listedStockService.getListedStockLatestPrice(itemCodeNumber) }
    }

    fun getListedStockPrices(@Param("itemCodeNumber") itemCodeNumber: String?, @QueryMap request: GetListedStockPricesRequest?): Deferred<GetListedStockPricesResponse> {
//        log.info("getListedStockPrices, {}",coroutineContext.isActive)
        return CoroutineScope(Dispatchers.IO).async { listedStockService.getListedStockPrices(itemCodeNumber,request) }
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
