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
    GetListedStockResponse getListedStock(@PathVariable("itemCodeNumber") String itemCodeNumber);
    @GetMapping(path="/v1/listedStock/latestPrice/{itemCodeNumber}")
    GetListedStockLatestPriceResponse getListedStockLatestPrice(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path="/v1/listedStock/outline/{itemCodeNumber}")
    GetListedStockOutlineResponse getListedStockOutline(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path = "/v1/listedStockPrices")
    GetListedStockPricesResponse getListedStockPrices(@SpringQueryMap GetListedStockPricesRequest request);
    @GetMapping(path= "/v1/listedStock/financial/ratio/{itemCodeNumber}")
    GetListedStockFinancialRatioResponse getListedStockFinancialRatio(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path= "/v1/listedStock/financial/statement/latest/{itemCodeNumber}")
    GetListedStockFinancialStatementResponse getListedStockFinancialStatement(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path="/v1/listedStock/summary/{itemCodeNumber}")
    GetListedStockSummaryResponse getListedStockSummary(@PathVariable("itemCodeNumber")String itemCodeNumber);
    @GetMapping(path="/v1/listedStock/financial/statement/past/{itemCodeNumber}/{targetFinancialStatement}")
    GetListedStockPastFinancialStatementsResponse getListedStockPastFinancialStatements(@PathVariable("itemCodeNumber") String itemCodeNumber
            , @PathVariable("targetFinancialStatement") String targetFinancialStatement);
}
