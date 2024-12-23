package com.kbank.convenience.stock.stock.mediation.controller.dto

import com.fasterxml.jackson.annotation.JsonFormat
import lombok.Builder
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime

@Builder
@JvmRecord
data class GetListedStockDetailResponse( //종목상세정보
        val stockKoreanName: String,
        val itemCodeNumber: String,
        val latestPrice: Long,
        val latestRatio: Double,  //일별시세
        val priceListCount: Long,
        val priceList: List<PriceElement>,  //시세정보
        val previousDayMinPrice: Long,
        val previousDayMaxPrice: Long,
        val yearlyMinPrice: Long,
        val yearlyMaxPrice: Long,  /*기업정보*/ //재무정보
        val sales: Long,
        val businessProfit: Long,
        val netIncome: Long,
        val asset: Long,
        val debit: Long,
        val capital: Long,
        val employeeNumber: Long,
        val dividendEarningRate: Double,
        val salesListCount: Long,
        val salesList: List<FinancialStatementElement>,
        val businessProfitListCount: Long,
        val businessProfitList: List<FinancialStatementElement>,
        val netIncomeListCount: Long,
        val netIncomeList: List<FinancialStatementElement>,
        val assetListCount: Long,
        val assetList: List<FinancialStatementElement>,  //시장정보
        val marketPriceTotal: Long,
        val changeRate: Double,
        val closePrice: Long,
        val volume: Long,
        val per: Double,
        val pbr: Double,
        val psr: Double,
        val roe: Double,  //기업개요
        val representativeName: String,
        val establishDate: LocalDate,
        val companyDetail: String,
        val companyScale: String,
        val supervised: Boolean,
        val exists: Boolean,
        val businessRegistrationNumber: String,
        val corporationNumber: String,
        val homePageUrl: String,
        val telephoneNumber: String,
        val landAddress: String,
        val mainTransactionBank: String,
        val industryName: String,
        val businessScope: String,  //사업개요

        val summaryTitle: String,
        val summaryContentsCount: Long,
        val summaryContents: List<String>,
        val statusTitle: String,
        val statusContentsCount: Long,
        val statusContents: List<String>,
        val summaryUpdatedAt: LocalDateTime

) {
    @Builder
    @JvmRecord
    data class PriceElement(
            @JsonFormat(pattern = "yyyyMMdd")
            @DateTimeFormat(pattern = "yyyyMMdd")
            val baseDate: LocalDate,
            val closePrice: Long,
            val changePrice: Long,
            val changeRate: Double
    )

    @Builder
    @JvmRecord
    data class FinancialStatementElement(val baseDate: LocalDate,
                                         val amount: Long

    )
}