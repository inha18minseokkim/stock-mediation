package com.kbank.convenience.stock.stock.mediation.client.dto

import lombok.Builder
import java.time.LocalDate

@Builder
@JvmRecord
data class GetListedStockSummaryResponse(val itemCodeNumber: String,
                                         val companyContractionName: String,
                                         val stockKoreanName: String,
                                         val stockEnglishName: String,
                                         val companyEnglishName: String,
                                         val companyKoreanName: String,
                                         val representativeName: String,
                                         val representativeEnglishName: String,
                                         val mainTransactionBank: String,
                                         val englishRoadNameAddress: String,
                                         val roadNameAddress: String,
                                         val status: String,
                                         val businessStatus: SummaryElement,
                                         val email: String,
                                         val companyDetail: String,
                                         val isExternalAudit: String,
                                         val companyScale: String,
                                         val homePageUrl: String,
                                         val telephone: String,
                                         val country: String,
                                         val niceCodeNumber: String,
                                         val businessRegistrationNumber: String,
                                         val corporationNumber: String,
                                         val employeeNumber: Long,
                                         val employeeBaseDate: LocalDate,
                                         val listedDate: LocalDate,
                                         val listedMarketName: String,
                                         val companyFoundedDate: LocalDate,
                                         val businessOverview: SummaryElement,
                                         val industryName: String,
                                         val industryId: String,
                                         val businessScopeKorean: String,
                                         val businessScopeEnglish: String
) {
    @JvmRecord
    data class SummaryElement(val title: String,
                         val source: String,
                         val content: List<String>,
                         val refDate: String

    )
}
