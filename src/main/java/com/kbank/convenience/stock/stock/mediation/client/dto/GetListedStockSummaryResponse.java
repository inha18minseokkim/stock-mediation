package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GetListedStockSummaryResponse(
        String itemCodeNumber,
        String branchEnglishName,
        String branchKoreanName,
        String businessRegistrationNumber,
        LocalDate employeeBaseDate,
        Long employeeNumber,
        Boolean exists,
        Boolean close,
        Boolean externalAuditExisting,
        Boolean supervised,
        String listedMarket,
        String companyEnglishName,
        String companyKoreanName,
        String companyContractionName,
        String mainTransactionBank,
        String status,
        String homePageUrl
) {
}
