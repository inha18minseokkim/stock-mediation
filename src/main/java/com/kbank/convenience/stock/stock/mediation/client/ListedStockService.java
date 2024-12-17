package com.kbank.convenience.stock.stock.mediation.client;

import com.kbank.convenience.stock.stock.mediation.client.dto.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "listed-stock-service", url = "http://127.0.0.1:8088/listed-stock-service")
public interface ListedStockService {
    @GetMapping("/v1/listedStock/{itemCodeNumber}")
    Mono<GetListedStockResponse> getListedStock(@PathVariable("itemCodeNumber") String itemCodeNumber);
    @GetMapping("/v1/listedStock/{itemCodeNumber}/price/latest")
    Mono<GetListedStockLatestPriceResponse> getListedStockLatestPrice(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping("/v1/listedStock/{itemCodeNumber}/prices")
    Mono<GetListedStockPricesResponse> getListedStockPrices(@PathVariable("itemCodeNumber") String itemCodeNumber,@SpringQueryMap GetListedStockPricesRequest request);
    @GetMapping("/v1/listedStock/financial/ratio/{itemCodeNumber}")
    Mono<GetListedStockFinancialRatioResponse> getListedStockFinancialRatio(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping("/v1/listedStock/financial/statement/latest/{itemCodeNumber}")
    Mono<GetListedStockFinancialStatementResponse> getListedStockFinancialStatement(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping("/v1/listedStock/summary/{itemCodeNumber}")
    Mono<GetListedStockSummaryResponse> getListedStockSummary(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping("/v1/listedStock/financial/statement/past/{itemCodeNumber}/{targetFinancialStatement}")
    Mono<GetListedStockPastFinancialStatementsResponse> getListedStockPastFinancialStatements(@PathVariable("itemCodeNumber") String itemCodeNumber
            , @PathVariable("targetFinancialStatement") String targetFinancialStatement);
}
