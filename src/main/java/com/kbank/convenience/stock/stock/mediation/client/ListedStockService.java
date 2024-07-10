package com.kbank.convenience.stock.stock.mediation.client;

import com.kbank.convenience.stock.stock.mediation.client.dto.*;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

@FeignClient(value = "listed-stock-web-service", url = "http://127.0.0.1:8088/listed-stock-web-service")
public interface ListedStockService {
    @GetMapping(path= "/v1/listedStock/{itemCodeNumber}")
    CompletableFuture<GetListedStockResponse> getListedStock(@PathVariable("itemCodeNumber") String itemCodeNumber);
    @GetMapping(path="/v1/listedStock/latestPrice/{itemCodeNumber}")
    CompletableFuture<GetListedStockLatestPriceResponse> getListedStockLatestPrice(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path="/v1/listedStock/outline/{itemCodeNumber}")
    CompletableFuture<GetListedStockOutlineResponse> getListedStockOutline(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path = "/v1/listedStockPrices")
    CompletableFuture<GetListedStockPricesResponse> getListedStockPrices(@SpringQueryMap GetListedStockPricesRequest request);
    @GetMapping(path= "/v1/listedStock/financial/ratio/{itemCodeNumber}")
    CompletableFuture<GetListedStockFinancialRatioResponse> getListedStockFinancialRatio(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path= "/v1/listedStock/financial/statement/latest/{itemCodeNumber}")
    CompletableFuture<GetListedStockFinancialStatementResponse> getListedStockFinancialStatement(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path="/v1/listedStock/summary/{itemCodeNumber}")
    CompletableFuture<GetListedStockSummaryResponse> getListedStockSummary(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path="/v1/listedStock/financial/statement/past/{itemCodeNumber}/{targetFinancialStatement}")
    CompletableFuture<GetListedStockPastFinancialStatementsResponse> getListedStockPastFinancialStatements(@PathVariable("itemCodeNumber") String itemCodeNumber
            , @PathVariable("targetFinancialStatement") String targetFinancialStatement);
}
