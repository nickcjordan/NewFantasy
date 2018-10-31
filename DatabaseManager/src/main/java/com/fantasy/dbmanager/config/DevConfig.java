package com.fantasy.dbmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

@Configuration
@Profile("dev, default")
public class DevConfig {
	
	  @Bean
	  AWSCredentialsProvider credProvider() {
	    return new ProfileCredentialsProvider("dev");
	  }

}
