package com.kbank.convenience.stock.stock.mediation

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
open class StockMediationApplication
inline fun <reified T> T.logger() = LoggerFactory.getLogger(T::class.java)!!

    fun main(args: Array<String>) {
        SpringApplication.run(StockMediationApplication::class.java, *args)
    }
