package com.cmr.affilieprojet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AffilieprojetApplication {
    public static void main(String[] args) {
        SpringApplication.run(AffilieprojetApplication.class, args);
    }
}
