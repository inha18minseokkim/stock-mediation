package com.kbank.convenience.stock.stock.mediation.controller

import com.kbank.convenience.stock.stock.mediation.client.ListedStockServiceHolder
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockLatestPriceResponse
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesRequest
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesResponse
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockResponse
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailRequest
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailResponse
import com.kbank.convenience.stock.stock.mediation.logger
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.boot.task.SimpleAsyncTaskSchedulerBuilder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(path = ["/stock/listed-stock"])
@RequiredArgsConstructor
@Slf4j
@RestController
class ListedStockController(
    private val listedStockService: ListedStockServiceHolder,

) {
    val log = logger()
    @GetMapping("/v1/detail/price")
    suspend fun getListedStockPriceDetail(request: GetListedStockPriceDetailRequest): GetListedStockPriceDetailResponse {
        val listedStock: GetListedStockResponse = listedStockService.getListedStock(request.itemCodeNumber)
        log.info("A")
        val latestPrice: GetListedStockLatestPriceResponse = listedStockService.getListedStockLatestPrice(request.itemCodeNumber)
        log.info("B")
        val prices: GetListedStockPricesResponse = listedStockService.getListedStockPrices(request.itemCodeNumber, GetListedStockPricesRequest(request.baseDateTime, 360L))
        log.info("C")
        return GetListedStockPriceDetailResponse(
                stockKoreanName = listedStock.stockKoreanName,
                itemCodeNumber = listedStock.itemCodeNumber,
                latestPrice = latestPrice.closePrice,
                latestRatio = latestPrice.changeRate,
                pricesCount = prices.list.stream().count(),
                prices = prices.list.stream().map {
                    GetListedStockPriceDetailResponse.PriceElement(
                            baseDate = it.baseDate,
                            closePrice = it.closePrice,
                            changePrice = it.changePrice,
                            changeRate = it.changeRate
                    )
                }.toList(),
                previousDayMinPrice = latestPrice.lowPrice,
                previousDayMaxPrice = latestPrice.highPrice,
                yearlyMinPrice = prices.minPrice,
                yearlyMaxPrice = prices.maxPrice
        )
    }
}
