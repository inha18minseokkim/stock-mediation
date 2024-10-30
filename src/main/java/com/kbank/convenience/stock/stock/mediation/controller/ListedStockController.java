package com.kbank.convenience.stock.stock.mediation.controller;

import com.kbank.convenience.stock.stock.mediation.client.ListedStockService;
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockLatestPriceResponse;
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesRequest;
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockPricesResponse;
import com.kbank.convenience.stock.stock.mediation.client.dto.GetListedStockResponse;
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailRequest;
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailResponse;
import com.kbank.convenience.stock.stock.mediation.mapper.ListedStockMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RequestMapping(path="/stock/listed-stock")
@RequiredArgsConstructor
@Slf4j
@RestController
public class ListedStockController {
    private final ListedStockService listedStockService;
    private final ListedStockMapper mapper;


    @GetMapping("/v1/detail/price")
    public ResponseEntity<GetListedStockPriceDetailResponse> getListedStockPriceDetail(GetListedStockPriceDetailRequest request){
        Mono<GetListedStockResponse> listedStock = listedStockService.getListedStock(request.itemCodeNumber());
        Mono<GetListedStockLatestPriceResponse> latestPrice = listedStockService.getListedStockLatestPrice(request.itemCodeNumber());
        Mono<GetListedStockPricesResponse> prices = listedStockService.getListedStockPrices(request.itemCodeNumber(),GetListedStockPricesRequest.builder()
                .baseDateTime(request.baseDateTime())
                .deltaDay(360L)
                .build());

        return ResponseEntity.ok().body(
                Mono.zip(listedStock,latestPrice,prices)
                        .map(it -> GetListedStockPriceDetailResponse.builder()
                                .stockKoreanName(it.getT1().stockKoreanName())
                                .itemCodeNumber(it.getT1().itemCodeNumber())
                                .latestPrice(it.getT2().closePrice())
                                .latestRatio(it.getT2().changeRate())
                                .pricesCount(it.getT3().list().stream().count())
                                .prices(it.getT3().list().stream().map(mapper::toSubResponse).collect(Collectors.toList()))
                                .previousDayMinPrice(it.getT2().lowPrice())
                                .previousDayMaxPrice(it.getT2().highPrice())
                                .yearlyMinPrice(it.getT3().minPrice())
                                .yearlyMaxPrice(it.getT3().maxPrice())
                                .build()).block()
        );


    }
}
