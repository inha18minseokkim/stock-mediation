package com.kbank.convenience.stock.stock.mediation.client.impl

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.kbank.convenience.stock.stock.mediation.client.dto.*
import com.kbank.convenience.stock.stock.mediation.client.service.ListedStockService
import com.kbank.convenience.stock.stock.mediation.logger
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.Logger
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

class ListedStockServiceImpl(
    private val webClientBuilder: WebClient.Builder,
    private val objectMapper: ObjectMapper
): ListedStockService {
    val log: Logger = logger()

    // ObjectMapper 활용하여 Query String 변환
    fun toQueryParams(request: Any): String {
//        val objectMapper = ObjectMapper()
//            .registerKotlinModule()
//            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // ISO 대신 어노테이션 포맷 유지

        // JSON 직렬화 -> Map 변환
        val jsonString = objectMapper.writeValueAsString(request) // {"baseDateTime":"20240205123456","deltaDay":7}
        val jsonNode = objectMapper.readTree(jsonString) as ObjectNode
        // Query Parameter 생성
        return jsonNode.fields().asSequence()
            .joinToString("&") { "${it.key}=${it.value.asText()}" }
    }


//    @RequestLine("GET /v1/listedStock/{itemCodeNumber}")
    override suspend fun getListedStock(itemCodeNumber: String): GetListedStockResponse? {
        return webClientBuilder.build()
            .get()
            .uri("/v1/listedStock/${itemCodeNumber}")
            .retrieve().bodyToMono<GetListedStockResponse>()
            .awaitSingle()
    }

//    @RequestLine("GET /v1/listedStock/{itemCodeNumber}/price/latest")
    override suspend fun getListedStockLatestPrice(itemCodeNumber: String): GetListedStockLatestPriceResponse? {
        return webClientBuilder.build()
            .get()
            .uri("/v1/listedStock/${itemCodeNumber}/price/latest")
            .retrieve().bodyToMono<GetListedStockLatestPriceResponse>()
            .awaitSingle()
    }

//    @RequestLine("GET /v1/listedStock/{itemCodeNumber}/prices")
    override suspend fun getListedStockPrices(
        itemCodeNumber: String,
        request: GetListedStockPricesRequest
    ): GetListedStockPricesResponse? {
        return webClientBuilder.build()
            .get()
            .uri("/v1/listedStock/${itemCodeNumber}/prices/summary?${toQueryParams(request)}")
            .retrieve().bodyToMono<GetListedStockPricesResponse>()
            .awaitSingle()
    }

//    @RequestLine("GET /v1/listedStock/financial/ratio/{itemCodeNumber}")
    override suspend fun getListedStockFinancialRatio(itemCodeNumber: String): GetListedStockFinancialRatioResponse? {
        TODO("Not yet implemented")
    }

//    @RequestLine("GET /v1/listedStock/financial/statement/latest/{itemCodeNumber}")
    override suspend fun getListedStockFinancialStatement(itemCodeNumber: String): GetListedStockFinancialStatementResponse? {
        TODO("Not yet implemented")
    }

//    @RequestLine("GET /v1/listedStock/summary/{itemCodeNumber}")
    override suspend fun getListedStockSummary(itemCodeNumber: String): GetListedStockSummaryResponse? {
        TODO("Not yet implemented")
    }

//    @RequestLine("GET /v1/listedStock/financial/statement/past/{itemCodeNumber}/{targetFinancialStatement}")
    override suspend fun getListedStockPastFinancialStatements(
        itemCodeNumber: String,
        targetFinancialStatement: String
    ): GetListedStockPastFinancialStatementsResponse? {
        TODO("Not yet implemented")
    }
}