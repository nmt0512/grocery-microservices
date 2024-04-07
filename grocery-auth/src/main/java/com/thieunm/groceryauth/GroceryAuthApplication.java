package com.thieunm.groceryauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.thieunm")
public class GroceryAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryAuthApplication.class, args);
	}

}
