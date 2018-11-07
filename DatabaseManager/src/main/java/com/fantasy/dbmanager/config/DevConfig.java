package com.fantasy.dbmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
@Profile("dev")
public class DevConfig {

	@Bean
	public AmazonDynamoDB getAmazonDynamoDb() {
		return AmazonDynamoDBClientBuilder.standard().withCredentials(new ProfileCredentialsProvider("dev"))
				.withEndpointConfiguration(new EndpointConfiguration("http://localhost:8000", Regions.US_EAST_2.getName())).build();
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
		return "dev";
	}

}
