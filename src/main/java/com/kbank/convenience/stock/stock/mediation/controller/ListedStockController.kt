package com.kbank.convenience.stock.stock.mediation.controller

import com.kbank.convenience.stock.stock.mediation.client.service.ListedStockService
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockLatestPriceResponse
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesRequest
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesResponse
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesResponse.GetListedStockPricesSubResponse
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockResponse
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailRequest
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailResponse
import com.kbank.convenience.stock.stock.mediation.mapper.ListedStockMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.util.function.Tuple3

@RequestMapping(path = ["/stock/listed-stock"])
@RequiredArgsConstructor
@Slf4j
@RestController
class ListedStockController(
    private val listedStockService: ListedStockService,
    private val mapper: ListedStockMapper
) {
    @GetMapping("/v1/detail/price")
    suspend fun getListedStockPriceDetail(request: GetListedStockPriceDetailRequest): GetListedStockPriceDetailResponse =
        coroutineScope {
            val listedStockDeferred = async { listedStockService.getListedStock(request.itemCodeNumber)!! }
            val latestPriceDeferred = async { listedStockService.getListedStockLatestPrice(request.itemCodeNumber)!! }
            val pricesDeferred = async {
                listedStockService.getListedStockPrices(
                    request.itemCodeNumber,
                    GetListedStockPricesRequest(baseDateTime = request.baseDateTime, deltaDay = 360L)
                )!!
            }

            val listedStock = listedStockDeferred.await()
            val latestPrice = latestPriceDeferred.await()
            val prices = pricesDeferred.await()

            GetListedStockPriceDetailResponse(
                stockKoreanName = listedStock.stockKoreanName,
                itemCodeNumber = listedStock.itemCodeNumber,
                latestPrice = latestPrice.closePrice,
                latestRatio = latestPrice.changeRate,
                pricesCount = prices.list.stream().count(),
                prices = prices.list.stream()
                    .map { getListedStockPricesSubResponse: GetListedStockPricesSubResponse? ->
                        mapper.toSubResponse(getListedStockPricesSubResponse)
                    }
                    .toList(),
                previousDayMinPrice = latestPrice.lowPrice,
                previousDayMaxPrice = latestPrice.highPrice,
                yearlyMinPrice = prices.minPrice,
                yearlyMaxPrice = prices.maxPrice,
            )
        }
}
