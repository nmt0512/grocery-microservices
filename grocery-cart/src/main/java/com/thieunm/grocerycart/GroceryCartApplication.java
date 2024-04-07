package com.thieunm.grocerycart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication(scanBasePackages = "com.thieunm")
@EnableMongoAuditing
public class GroceryCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryCartApplication.class, args);
	}

}
