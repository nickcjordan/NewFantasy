package com.fantasy.dbmanager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;

@Configuration
@Profile("prod")
public class ProdConfig {
	
	@Bean
	  AWSCredentialsProvider credProvider() {
	    return InstanceProfileCredentialsProvider.getInstance();
	  }

}
