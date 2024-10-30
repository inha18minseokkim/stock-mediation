package com.kbank.convenience.stock.stock.mediation.client.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record GetListedStockResponse(
        String itemCodeNumber,
        String stockKoreanName,
        String exchangeMarketCode,
        String marketCode,
        String niceCodeNumber,
        String representativeName,
        String businessRegistrationNumber,
        String corporationNumber,
        String telephoneNumber,
        String faxNumber,
        String email,
        String zipCode,
        String landAddress,
        String roadNameAddress,
        String groupId,
        String companyType,
        String companyDetail,
        String companyScale,
        String conglomerateId,
        String industryId,
        String industryName,
        String accountType,
        String fiscalEndYear,
        String businessScope,
        LocalDate establishDate,
        LocalDate listingDate,
        Long outStandingStockQuantity,
        String tradingStop
) {
}
