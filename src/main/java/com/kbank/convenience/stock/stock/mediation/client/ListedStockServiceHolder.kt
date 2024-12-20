package com.kbank.convenience.stock.stock.mediation.client

import com.kbank.convenience.stock.stock.mediation.client.dto.*
import com.kbank.convenience.stock.stock.mediation.logger
import feign.Param
import feign.QueryMap
import feign.RequestLine
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import kotlin.coroutines.coroutineContext


@Component
@Slf4j
class ListedStockServiceHolder(val listedStockService: ListedStockService,
) {
    val log = logger()
    suspend fun getListedStock(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockResponse {
//        delay(1000L)
        log.info("getListedStock, {}",coroutineContext.isActive)
        return listedStockService.getListedStock(itemCodeNumber)
    }

    suspend fun getListedStockLatestPrice(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockLatestPriceResponse {
//        delay(1000L)
        log.info("getListedStockLatestPrice, {}",coroutineContext.isActive)
        return listedStockService.getListedStockLatestPrice(itemCodeNumber)
    }

    suspend fun getListedStockPrices(@Param("itemCodeNumber") itemCodeNumber: String?, @QueryMap request: GetListedStockPricesRequest?): GetListedStockPricesResponse {
//        delay(1000L)
        log.info("getListedStockPrices, {}",coroutineContext.isActive)
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
