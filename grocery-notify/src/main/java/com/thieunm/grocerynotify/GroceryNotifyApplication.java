package com.thieunm.grocerynotify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.thieunm")
public class GroceryNotifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryNotifyApplication.class, args);
	}

}
