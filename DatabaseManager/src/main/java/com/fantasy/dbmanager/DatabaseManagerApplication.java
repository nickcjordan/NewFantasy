package com.fantasy.dbmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.fantasy" })
public class DatabaseManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseManagerApplication.class, args);
	}
}
