package com.kbank.convenience.stock.stock.mediation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StockMediationApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockMediationApplication.class, args);
    }

}
