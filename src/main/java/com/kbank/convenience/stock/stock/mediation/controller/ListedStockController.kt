package com.kbank.convenience.stock.stock.mediation.controller

import com.kbank.convenience.stock.stock.mediation.client.ListedStockService
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockLatestPriceResponse
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesRequest
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesResponse
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesResponse.GetListedStockPricesSubResponse
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockResponse
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailRequest
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailResponse
import com.kbank.convenience.stock.stock.mediation.mapper.ListedStockMapper
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.util.function.Tuple3
import java.util.stream.Collectors

@RequestMapping(path = ["/stock/listed-stock"])
@RequiredArgsConstructor
@Slf4j
@RestController
class ListedStockController(
        private val listedStockService: ListedStockService,
        private val mapper: ListedStockMapper
) {
    @GetMapping("/v1/detail/price")
    fun getListedStockPriceDetail(request: GetListedStockPriceDetailRequest): Mono<GetListedStockPriceDetailResponse> {
        val listedStock = listedStockService.getListedStock(request.itemCodeNumber)
        val latestPrice = listedStockService.getListedStockLatestPrice(request.itemCodeNumber)
        val prices = listedStockService.getListedStockPrices(request.itemCodeNumber, GetListedStockPricesRequest(
                baseDateTime = request.baseDateTime,
                deltaDay = 360L
        )
        )

        return Mono.zip(listedStock, latestPrice, prices)
                .map { it: Tuple3<GetListedStockResponse, GetListedStockLatestPriceResponse, GetListedStockPricesResponse> ->
                    GetListedStockPriceDetailResponse(
                            stockKoreanName = it.t1.stockKoreanName,
                            itemCodeNumber = it.t1.itemCodeNumber,
                            latestPrice = it.t2.closePrice,
                            latestRatio = it.t2.changeRate,
                            pricesCount = it.t3.list.stream().count(),
                            prices = it.t3.list.stream()
                                    .map { getListedStockPricesSubResponse: GetListedStockPricesSubResponse? ->
                                        mapper.toSubResponse(getListedStockPricesSubResponse) }
                                    .toList(),
                            previousDayMinPrice = it.t2.lowPrice,
                            previousDayMaxPrice = it.t2.highPrice,
                            yearlyMinPrice = it.t3.minPrice,
                            yearlyMaxPrice = it.t3.maxPrice,
                    )
                }
    }
}
