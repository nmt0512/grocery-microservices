package com.thieunm.groceryproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = "com.thieunm")
@EnableJpaAuditing
public class GroceryProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceryProductApplication.class, args);
    }

}
