package com.kbank.convenience.stock.stock.mediation.controller;

import com.kbank.convenience.stock.stock.mediation.client.ListedStockService;
import com.kbank.convenience.stock.stock.mediation.client.dto.*;
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailRequest;
import com.kbank.convenience.stock.stock.mediation.controller.dto.GetListedStockPriceDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@RequestMapping(path="/stock/listed-stock")
@RequiredArgsConstructor
@Slf4j
@RestController
public class ListedStockController {
    private final ListedStockService listedStockService;

    @GetMapping("/v1/detail")
    public ResponseEntity<GetListedStockPriceDetailResponse> getListedStockPriceDetail(GetListedStockPriceDetailRequest request) throws InterruptedException, ExecutionException {
        AtomicReference<GetListedStockPriceDetailResponse.GetListedStockPriceDetailResponseBuilder> builder = new AtomicReference<>(GetListedStockPriceDetailResponse.builder());

        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() ->{
                GetListedStockResponse response = listedStockService.getListedStock(request.itemCodeNumber());
                builder.set(builder.get().stockKoreanName(response.stockKoreanName())
                        .itemCodeNumber(response.itemCodeNumber())
                        .representativeName(response.representativeName())
                        .establishDate(response.establishDate())
                        .companyDetail(response.companyDetail())
                        .companyScale(response.companyScale())
                        .businessRegistrationNumber(response.businessRegistrationNumber())
                        .corporationNumber(response.corporationNumber())
                        .telephoneNumber(response.telephoneNumber())
                        .landAddress(response.landAddress())
                        .industryName(response.industryName())
                        .businessScope(response.businessScope()));
                return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockLatestPriceResponse response = listedStockService.getListedStockLatestPrice(request.itemCodeNumber());
                    builder.set(builder.get()
                            .latestPrice(response.closePrice())
                            .latestRatio(response.changeRate())
                            .marketPriceTotal(response.marketPriceTotal())
                            .changeRate(response.changeRate())
                            .closePrice(response.closePrice())
                            .volume(response.volume())
                    );
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockPricesResponse response = listedStockService.getListedStockPrices(GetListedStockPricesRequest.builder()
                            .baseDateTime(request.baseDateTime())
                            .itemCodeNumber(request.itemCodeNumber())
                            .deltaDay(360L)
                            .build());
                    Long highPrice = response.list().stream().sorted(
                            Comparator.comparing(GetListedStockPricesResponse.GetListedStockPricesSubResponse::baseDateTime)
                    ).toList().get(0).highPrice();
                    Long lowPrice = response.list().stream().sorted(
                            Comparator.comparing(GetListedStockPricesResponse.GetListedStockPricesSubResponse::baseDateTime)
                    ).toList().get(0).lowPrice();
                    builder.set(builder.get()
                            .priceListCount(response.list().stream().count())
                            .priceList(response.list().stream()
                                    .map(element -> GetListedStockPriceDetailResponse.PriceElement.builder()
                                            .closePrice(element.closePrice())
                                            .changePrice(element.changePrice())
                                            .changeRate(element.changeRate())
                                            .baseDateTime(element.baseDateTime())
                                            .build()).toList())
                            .previousDayMaxPrice(lowPrice)
                            .previousDayMaxPrice(highPrice)
                            .yearlyMinPrice(response.minPrice())
                            .yearlyMaxPrice(response.maxPrice()));
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockFinancialStatementResponse response = listedStockService.getListedStockFinancialStatement(request.itemCodeNumber());
                    builder.set(builder.get()
                            .sales(response.sales())
                            .businessProfit(response.businessProfit())
                            .netIncome(response.netIncome())
                            .asset(response.asset())
                            .debit(response.debit())
                            .capital(response.capital()));
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockSummaryResponse response = listedStockService.getListedStockSummary(request.itemCodeNumber());
                    builder.set(builder.get()
                            .employeeNumber(response.employeeNumber())
                            .supervised(response.externalAuditExisting())
                            .exists(response.exists())
                            .mainTransactionBank(response.mainTransactionBank())
                            .homePageUrl(response.homePageUrl()));
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockFinancialRatioResponse response = listedStockService.getListedStockFinancialRatio(request.itemCodeNumber());
                    builder.set(builder.get()
                            .dividendEarningRate(response.dividendEarningRate())
                            .per(response.per())
                            .pbr(response.pbr()));
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockOutlineResponse response = listedStockService.getListedStockOutline(request.itemCodeNumber());
                    builder.set(builder.get()
                            .summaryTitle(response.summaryTitle())
                            .summaryContentsCount(response.summaryContents().stream().count())
                            .summaryContents(response.summaryContents())
                            .statusTitle(response.summaryTitle())
                            .statusContentsCount(response.statusContents().stream().count())
                            .statusContents(response.statusContents())
                            .summaryUpdatedAt(response.updatedAt()));
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockPastFinancialStatementsResponse response = listedStockService.getListedStockPastFinancialStatements(request.itemCodeNumber(), "매출액");
                    builder.set(builder.get()
                            .salesListCount(response.list().stream().count())
                            .salesList(response.list().stream().map(element-> GetListedStockPriceDetailResponse
                                    .FinancialStatementElement
                                    .builder()
                                    .baseDate(element.baseDate())
                                    .amount(element.amount())
                                    .build()).toList()));
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockPastFinancialStatementsResponse response = listedStockService.getListedStockPastFinancialStatements(request.itemCodeNumber(), "영업이익");
                    builder.set(builder.get()
                            .salesListCount(response.list().stream().count())
                            .salesList(response.list().stream().map(element-> GetListedStockPriceDetailResponse
                                    .FinancialStatementElement
                                    .builder()
                                    .baseDate(element.baseDate())
                                    .amount(element.amount())
                                    .build()).toList())
                    );
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockPastFinancialStatementsResponse response = listedStockService.getListedStockPastFinancialStatements(request.itemCodeNumber(), "당기순이익");
                    builder.set(builder.get()
                            .salesListCount(response.list().stream().count())
                            .salesList(response.list().stream().map(element-> GetListedStockPriceDetailResponse
                                    .FinancialStatementElement
                                    .builder()
                                    .baseDate(element.baseDate())
                                    .amount(element.amount())
                                    .build()).toList())
                    );
                    return response;
                }),
                CompletableFuture.supplyAsync(() -> {
                    GetListedStockPastFinancialStatementsResponse response = listedStockService.getListedStockPastFinancialStatements(request.itemCodeNumber(), "자산");

                    builder.set(builder.get()
                            .salesListCount(response.list().stream().count())
                            .salesList(response.list().stream().map(element-> GetListedStockPriceDetailResponse
                                    .FinancialStatementElement
                                    .builder()
                                    .baseDate(element.baseDate())
                                    .amount(element.amount())
                                    .build()).toList())
                    );
                    return response;
                })
        ).get();

        GetListedStockPriceDetailResponse build = builder.get().build();
        return ResponseEntity.ok()
                .body(build);
    }

}
