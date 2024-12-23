package com.kbank.convenience.stock.stock.mediation.client

import com.kbank.convenience.stock.stock.mediation.client.dto.*
import com.kbank.convenience.stock.stock.mediation.logger
import feign.Param
import feign.QueryMap
import javafx.application.Application.launch
import kotlinx.coroutines.*
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder
import org.springframework.boot.task.SimpleAsyncTaskSchedulerBuilder
import org.springframework.stereotype.Component


@Component
@Slf4j
class ListedStockServiceAdapter(
        private val listedStockService: ListedStockService
) {
    private val log = logger()
    suspend fun getListedStock(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockResponse {
        log.info("getListedStock")
        return withContext(Dispatchers.IO){ listedStockService.getListedStock(itemCodeNumber)}
    }

    suspend fun getListedStockLatestPrice(@Param("itemCodeNumber") itemCodeNumber: String?): GetListedStockLatestPriceResponse {
        log.info("getListedStockLatestPrice")
        return withContext(Dispatchers.IO) {listedStockService.getListedStockLatestPrice(itemCodeNumber)}
    }

    suspend fun getListedStockPrices(@Param("itemCodeNumber") itemCodeNumber: String?, @QueryMap request: GetListedStockPricesRequest?):GetListedStockPricesResponse {
        log.info("getListedStockPrices")
        return withContext(Dispatchers.IO) {listedStockService.getListedStockPrices(itemCodeNumber,request)}
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
