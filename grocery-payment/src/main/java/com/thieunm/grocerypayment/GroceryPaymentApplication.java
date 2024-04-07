package com.thieunm.grocerypayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.thieunm")
@EnableJpaAuditing
public class GroceryPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceryPaymentApplication.class, args);
    }

}
