package com.fantasy.dbmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
@Profile("prod")
public class ProdConfig {

	// use for connecting to web client
	@Bean
	public AmazonDynamoDB getAmazonDynamoDb() {
		return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).withCredentials(InstanceProfileCredentialsProvider.getInstance()).build();
	}

	@Bean(name = "dynamoDb")
	public DynamoDB getDynamoDb(AmazonDynamoDB db) {
		return new DynamoDB(db);
	}

	@Bean(name = "dynamoDbMapper")
	public DynamoDBMapper getDynamoDbMapper(AmazonDynamoDB db) {
		return new DynamoDBMapper(db);
	}

	@Bean(name = "testString")
	public String testString() {
		return "prod";
	}
	
    @SuppressWarnings("deprecation")
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

}
