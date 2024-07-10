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
import java.util.concurrent.atomic.AtomicReference;

@RequestMapping(path="/stock/listed-stock")
@RequiredArgsConstructor
@Slf4j
@RestController
public class ListedStockController {
    private final ListedStockService listedStockService;

    @GetMapping("/v1/detail")
    public ResponseEntity<GetListedStockPriceDetailResponse> getListedStockPriceDetail(GetListedStockPriceDetailRequest request) throws InterruptedException {
        log.info("###");
        CompletableFuture<GetListedStockResponse> listedStock = listedStockService.getListedStock(request.itemCodeNumber());
        CompletableFuture<GetListedStockLatestPriceResponse> latestPrice = listedStockService.getListedStockLatestPrice(request.itemCodeNumber());
        CompletableFuture<GetListedStockPricesResponse> prices = listedStockService.getListedStockPrices(GetListedStockPricesRequest.builder()
                .baseDateTime(request.baseDateTime())
                .itemCodeNumber(request.itemCodeNumber())
                .deltaDay(360L)
                .build());

        CompletableFuture<GetListedStockFinancialStatementResponse> financialStatement = listedStockService.getListedStockFinancialStatement(request.itemCodeNumber());
        CompletableFuture<GetListedStockSummaryResponse> summary = listedStockService.getListedStockSummary(request.itemCodeNumber());
        CompletableFuture<GetListedStockFinancialRatioResponse> financialRatio = listedStockService.getListedStockFinancialRatio(request.itemCodeNumber());
        CompletableFuture<GetListedStockOutlineResponse> outline = listedStockService.getListedStockOutline(request.itemCodeNumber());
        CompletableFuture<GetListedStockPastFinancialStatementsResponse> 매출액 = listedStockService.getListedStockPastFinancialStatements(request.itemCodeNumber(), "매출액");
        CompletableFuture<GetListedStockPastFinancialStatementsResponse> 영업이익 = listedStockService.getListedStockPastFinancialStatements(request.itemCodeNumber(), "영업이익");
        CompletableFuture<GetListedStockPastFinancialStatementsResponse> 당기순이익 = listedStockService.getListedStockPastFinancialStatements(request.itemCodeNumber(), "당기순이익");
        CompletableFuture<GetListedStockPastFinancialStatementsResponse> 자산 = listedStockService.getListedStockPastFinancialStatements(request.itemCodeNumber(), "자산");
        AtomicReference<GetListedStockPriceDetailResponse.GetListedStockPriceDetailResponseBuilder> builder = new AtomicReference<>(GetListedStockPriceDetailResponse.builder());
        listedStock.thenAccept((response) -> {
            log.info("{}",response);
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
                    .businessScope(response.businessScope())
            );
        });
        latestPrice.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                            .latestPrice(response.closePrice())
                            .latestRatio(response.changeRate())
                            .marketPriceTotal(response.marketPriceTotal())
                            .changeRate(response.changeRate())
                            .closePrice(response.closePrice())
                            .volume(response.volume())
                    );
        });
        prices.thenAccept((response)  -> {
            log.info("{}",response);
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
                    .yearlyMaxPrice(response.maxPrice())
           );
        });
        financialStatement.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                    .sales(response.sales())
                    .businessProfit(response.businessProfit())
                    .netIncome(response.netIncome())
                    .asset(response.asset())
                    .debit(response.debit())
                    .capital(response.capital()));
        });
        summary.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                .employeeNumber(response.employeeNumber())
                .supervised(response.externalAuditExisting())
                .exists(response.exists())
                .mainTransactionBank(response.mainTransactionBank())
                .homePageUrl(response.homePageUrl()));
        });
        outline.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                    .summaryTitle(response.summaryTitle())
                    .summaryContentsCount(response.summaryContents().stream().count())
                    .summaryContents(response.summaryContents())
                    .statusTitle(response.summaryTitle())
                    .statusContentsCount(response.statusContents().stream().count())
                    .statusContents(response.statusContents())
                    .summaryUpdatedAt(response.updatedAt()));
        });
        financialRatio.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                    .dividendEarningRate(response.dividendEarningRate())
                    .per(response.per())
                    .pbr(response.pbr()));
        });
        매출액.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                    .salesListCount(response.list().stream().count())
                    .salesList(response.list().stream().map(element-> GetListedStockPriceDetailResponse
                            .FinancialStatementElement
                            .builder()
                            .baseDate(element.baseDate())
                            .amount(element.amount())
                            .build()).toList())
            );
        });
        영업이익.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                    .salesListCount(response.list().stream().count())
                    .salesList(response.list().stream().map(element-> GetListedStockPriceDetailResponse
                            .FinancialStatementElement
                            .builder()
                            .baseDate(element.baseDate())
                            .amount(element.amount())
                            .build()).toList())
            );
        });
        당기순이익.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                    .salesListCount(response.list().stream().count())
                    .salesList(response.list().stream().map(element-> GetListedStockPriceDetailResponse
                            .FinancialStatementElement
                            .builder()
                            .baseDate(element.baseDate())
                            .amount(element.amount())
                            .build()).toList())
            );
        });
        자산.thenAccept((response) -> {
            log.info("{}",response);
            builder.set(builder.get()
                    .salesListCount(response.list().stream().count())
                    .salesList(response.list().stream().map(element-> GetListedStockPriceDetailResponse
                            .FinancialStatementElement
                            .builder()
                            .baseDate(element.baseDate())
                            .amount(element.amount())
                            .build()).toList())
            );
        });

        log.info("@@@");
        return ResponseEntity.ok()
                .body(
                        builder.get()
                                //.stockKoreanName(listedStock.stockKoreanName())
                                //.itemCodeNumber(listedStock.itemCodeNumber())
                                //.latestPrice(latestPrice.closePrice())
                                //.latestRatio(latestPrice.changeRate())
//                                .priceListCount(prices.list().stream().count())
//                                .priceList(prices.list().stream()
//                                        .map(element -> GetListedStockPriceDetailResponse.PriceElement.builder()
//                                                .closePrice(element.closePrice())
//                                                .changePrice(element.changePrice())
//                                                .changeRate(element.changeRate())
//                                                .baseDateTime(element.baseDateTime())
//                                                .build()).toList())
//                                .previousDayMinPrice(lowPrice)
//                                .previousDayMaxPrice(highPrice)
//                                .yearlyMinPrice(prices.minPrice())
//                                .yearlyMaxPrice(prices.maxPrice())
//                                .sales(financialStatement.sales())
//                                .businessProfit(financialStatement.businessProfit())
//                                .netIncome(financialStatement.netIncome())
//                                .asset(financialStatement.asset())
//                                .debit(financialStatement.debit())
//                                .capital(financialStatement.capital())
//                                .salesListCount(매출액.list().stream().count())
//                                .salesList(매출액.list().stream().map(element-> GetListedStockPriceDetailResponse
//                                        .FinancialStatementElement
//                                        .builder()
//                                        .baseDate(element.baseDate())
//                                        .amount(element.amount())
//                                        .build()).toList())
//                                .businessProfitListCount(영업이익.list().stream().count())
//                                .businessProfitList(영업이익.list().stream().map(element-> GetListedStockPriceDetailResponse
//                                        .FinancialStatementElement
//                                        .builder()
//                                        .baseDate(element.baseDate())
//                                        .amount(element.amount())
//                                        .build()).toList())
//                                .netIncomeListCount(당기순이익.list().stream().count())
//                                .netIncomeList(당기순이익.list().stream().map(element-> GetListedStockPriceDetailResponse
//                                        .FinancialStatementElement
//                                        .builder()
//                                        .baseDate(element.baseDate())
//                                        .amount(element.amount())
//                                        .build()).toList())
//                                .assetListCount(자산.list().stream().count())
//                                .assetList(자산.list().stream().map(element-> GetListedStockPriceDetailResponse
//                                        .FinancialStatementElement
//                                        .builder()
//                                        .baseDate(element.baseDate())
//                                        .amount(element.amount())
//                                        .build()).toList())
//                                .marketPriceTotal(latestPrice.marketPriceTotal())
//                                .changeRate(latestPrice.changeRate())
//                                .closePrice(latestPrice.closePrice())
//                                .volume(latestPrice.volume())
//                                .dividendEarningRate(financialRatio.dividendEarningRate())
//                                .per(financialRatio.per())
//                                .pbr(financialRatio.pbr())
                                //.psr(financialRatio.psr())
                                //.roe(financialRatio.roe())
//                                .representativeName(listedStock.representativeName())
//                                .establishDate(listedStock.establishDate())
//                                .companyDetail(listedStock.companyDetail())
//                                .companyScale(listedStock.companyScale())
//                                .employeeNumber(summary.employeeNumber())
//                                .supervised(summary.externalAuditExisting())
//                                .exists(summary.exists())
//                                .mainTransactionBank(summary.mainTransactionBank())
//                                .homePageUrl(summary.homePageUrl())
//                                .businessRegistrationNumber(listedStock.businessRegistrationNumber())
//                                .corporationNumber(listedStock.corporationNumber())
//                                .telephoneNumber(listedStock.telephoneNumber())
//                                .landAddress(listedStock.landAddress())
//                                .industryName(listedStock.industryName())
//                                .businessScope(listedStock.businessScope())
//                                .summaryTitle(outline.summaryTitle())
//                                .summaryContentsCount(outline.summaryContents().stream().count())
//                                .summaryContents(outline.summaryContents())
//                                .statusTitle(outline.summaryTitle())
//                                .statusContentsCount(outline.statusContents().stream().count())
//                                .statusContents(outline.statusContents())
//                                .summaryUpdatedAt(outline.updatedAt())
                                .build()
                );
    }

}
