package com.kbank.convenience.stock.stock.mediation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableReactiveFeignClients
public class StockMediationApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockMediationApplication.class, args);
    }

}
