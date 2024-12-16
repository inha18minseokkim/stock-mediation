package com.kbank.convenience.stock.stock.mediation

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
open class StockMediationApplication
    fun main(args: Array<String>) {
        SpringApplication.run(StockMediationApplication::class.java, *args)
    }
