package com.kbank.convenience.stock.stock.mediation.client;

import com.kbank.convenience.stock.stock.mediation.client.dto.*;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.SpringQueryMap;
import reactor.core.publisher.Mono;

//@FeignClient(value = "listed-stock-web-service", url = "http://127.0.0.1:8088/listed-stock-web-service")
public interface ListedStockService {
    @RequestLine("GET /v1/listedStock/{itemCodeNumber}")
    Mono<GetListedStockResponse> getListedStock(@Param("itemCodeNumber") String itemCodeNumber);
    @RequestLine("GET /v1/listedStock/{itemCodeNumber}/price/latest")
    Mono<GetListedStockLatestPriceResponse> getListedStockLatestPrice(@Param("itemCodeNumber")String itemCodeNumber);
    @RequestLine("GET /v1/listedStock/{itemCodeNumber}/prices")
    Mono<GetListedStockPricesResponse> getListedStockPrices(@Param("itemCodeNumber") String itemCodeNumber,@QueryMap GetListedStockPricesRequest request);
    @RequestLine("GET /v1/listedStock/financial/ratio/{itemCodeNumber}")
    Mono<GetListedStockFinancialRatioResponse> getListedStockFinancialRatio(@Param("itemCodeNumber")String itemCodeNumber);
    @RequestLine("GET /v1/listedStock/financial/statement/latest/{itemCodeNumber}")
    Mono<GetListedStockFinancialStatementResponse> getListedStockFinancialStatement(@Param("itemCodeNumber")String itemCodeNumber);
    @RequestLine("GET /v1/listedStock/summary/{itemCodeNumber}")
    Mono<GetListedStockSummaryResponse> getListedStockSummary(@Param("itemCodeNumber")String itemCodeNumber);
    @RequestLine("GET /v1/listedStock/financial/statement/past/{itemCodeNumber}/{targetFinancialStatement}")
    Mono<GetListedStockPastFinancialStatementsResponse> getListedStockPastFinancialStatements(@Param("itemCodeNumber") String itemCodeNumber
            , @Param("targetFinancialStatement") String targetFinancialStatement);
}
