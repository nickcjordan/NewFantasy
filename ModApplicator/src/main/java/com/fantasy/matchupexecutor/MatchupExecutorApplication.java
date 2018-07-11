package com.fantasy.matchupexecutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.fantasy" })
public class MatchupExecutorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchupExecutorApplication.class, args);
	}
}
