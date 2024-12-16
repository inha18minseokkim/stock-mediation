package com.kbank.convenience.stock.stock.mediation.client.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.Builder
import lombok.Data
import java.time.LocalDate

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
data class GetListedStockResponse(val itemCodeNumber: String?,
                                  val stockKoreanName: String?,
                                  val exchangeMarketCode: String?,
                                  val marketCode: String?,
                                  val niceCodeNumber: String?,
                                  val representativeName: String?,
                                  val businessRegistrationNumber: String?,
                                  val corporationNumber: String?,
                                  val telephoneNumber: String?,
                                  val faxNumber: String?,
                                  val email: String?,
                                  val zipCode: String?,
                                  val landAddress: String?,
                                  val roadNameAddress: String?,
                                  val groupId: String?,
                                  val companyType: String?,
                                  val companyDetail: String?,
                                  val companyScale: String?,
                                  val conglomerateId: String?,
                                  val industryId: String?,
                                  val industryName: String?,
                                  val accountType: String?,
                                  val fiscalEndYear: String?,
                                  val businessScope: String?,
                                  val establishDate: LocalDate, val listingDate: LocalDate, val outStandingStockQuantity: Long, val tradingStop: String?)

