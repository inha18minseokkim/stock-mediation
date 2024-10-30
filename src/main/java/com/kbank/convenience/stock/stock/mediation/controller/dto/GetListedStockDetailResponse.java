package com.kbank.convenience.stock.stock.mediation.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetListedStockDetailResponse(
        //종목상세정보
        String stockKoreanName,
        String itemCodeNumber,
        Long latestPrice,
        Double latestRatio,
        //일별시세
        Long priceListCount,
        List<PriceElement> priceList,
        //시세정보
        Long previousDayMinPrice,
        Long previousDayMaxPrice,
        Long yearlyMinPrice,
        Long yearlyMaxPrice,
        /*기업정보*/
        //재무정보
        Long sales,
        Long businessProfit,
        Long netIncome,
        Long asset,
        Long debit,
        Long capital,
        Long employeeNumber,
        Double dividendEarningRate,
        Long salesListCount,
        List<FinancialStatementElement> salesList,
        Long businessProfitListCount,
        List<FinancialStatementElement> businessProfitList,
        Long netIncomeListCount,
        List<FinancialStatementElement> netIncomeList,
        Long assetListCount,
        List<FinancialStatementElement> assetList,
        //시장정보
        Long marketPriceTotal,
        Double changeRate,
        Long closePrice,
        Long volume,
        Double per,
        Double pbr,
        Double psr,
        Double roe,
        //기업개요
        String representativeName,
        LocalDate establishDate,
        String companyDetail,
        String companyScale,
        Boolean supervised,
        Boolean exists,
        String businessRegistrationNumber,
        String corporationNumber,
        String homePageUrl,
        String telephoneNumber,
        String landAddress,
        String mainTransactionBank,
        String industryName,
        String businessScope,

        //사업개요
        String summaryTitle,
        Long summaryContentsCount,
        List<String> summaryContents,
        String statusTitle,
        Long statusContentsCount,
        List<String> statusContents,
        LocalDateTime summaryUpdatedAt

) {
    @Builder
    public record PriceElement(
            @JsonFormat(pattern = "yyyyMMdd")
            @DateTimeFormat(pattern = "yyyyMMdd")
            LocalDate baseDate,
            Long closePrice,
            Long changePrice,
            Double changeRate
    ) {}
    @Builder
    public record FinancialStatementElement(
            LocalDate baseDate,
            Long amount
    ) { }
}
