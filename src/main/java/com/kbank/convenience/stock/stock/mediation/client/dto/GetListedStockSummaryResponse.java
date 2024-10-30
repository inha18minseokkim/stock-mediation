package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record GetListedStockSummaryResponse(
        String itemCodeNumber,
        String companyContractionName,
        String stockKoreanName,
        String stockEnglishName,
        String companyEnglishName,
        String companyKoreanName,
        String representativeName,
        String representativeEnglishName,
        String mainTransactionBank,
        String englishRoadNameAddress,
        String roadNameAddress,
        String status,
        SummaryElement businessStatus,
        String email,
        String companyDetail,
        String isExternalAudit,
        String companyScale,
        String homePageUrl,
        String telephone,
        String country,
        String niceCodeNumber,
        String businessRegistrationNumber,
        String corporationNumber,
        Long employeeNumber,
        LocalDate employeeBaseDate,
        LocalDate listedDate,
        String listedMarketName,
        LocalDate companyFoundedDate,
        SummaryElement businessOverview,
        String industryName,
        String industryId,
        String businessScopeKorean,
        String businessScopeEnglish
) {
    public record SummaryElement(
           String title,
           String source,
           List<String>content,
           String refDate
    ) {}
}
