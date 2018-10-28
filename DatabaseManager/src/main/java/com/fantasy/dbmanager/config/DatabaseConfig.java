package com.fantasy.dbmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
public class DatabaseConfig {

//	@Bean
//	public MongoClient getMongoClient() {
//		return new MongoClient();
//	}
//	
//	@Bean
//	public MongoDatabase getMongoDatabase(MongoClient mongoClient) {
//		return mongoClient.getDatabase("fantasyDB");
//	}
//	
//	@Bean(name="userCodecRegistry")
//	public CodecRegistry getUserCodecRegistry() {
//		return CodecRegistries.fromCodecs(
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(MatchupSchedule.class),
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(TeamTO.class), 
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(UserTO.class),
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
//				new IntegerCodec(),
//				MongoClient.getDefaultCodecRegistry().get(String.class)
//		);
//	}
//	
//	@Bean(name="playerCodecRegistry")
//	public CodecRegistry getPlayerCodecRegistry() {
//		return CodecRegistries.fromCodecs(
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Player.class), 
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
//				new IntegerCodec(),
//				MongoClient.getDefaultCodecRegistry().get(String.class)
//		);
//	}
//	
//	@Bean(name="modifierCodecRegistry")
//	public CodecRegistry getModifierCodecRegistry() {
//		return CodecRegistries.fromCodecs(
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Modifier.class), 
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(NflTeam.class),
//				new IntegerCodec(),
//				MongoClient.getDefaultCodecRegistry().get(String.class)
//		);
//	}
//	
//	@Bean(name="metadataCodecRegistry")
//	public CodecRegistry getMetadataCodecRegistry() {
//		return CodecRegistries.fromCodecs(
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
//				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Metadata.class),
//				new IntegerCodec(),
//				MongoClient.getDefaultCodecRegistry().get(String.class)
//		);
//	}
//	
//	@Bean(name="playerDBCollection")
//	public MongoCollection<Player> getPlayerDBConnection(MongoDatabase mongoClient, @Qualifier("playerCodecRegistry") CodecRegistry codecRegistry) {
//		return mongoClient.getCollection("players", Player.class).withCodecRegistry(codecRegistry);
//	}
//	
//	@Bean(name="modifierDBCollection")
//	public MongoCollection<Modifier> getModifierDBConnection(MongoDatabase mongoClient, @Qualifier("modifierCodecRegistry") CodecRegistry codecRegistry) {
//		return mongoClient.getCollection("modifiers", Modifier.class).withCodecRegistry(codecRegistry);
//	}
//	
//	@Bean(name="userDBCollection")
//	public MongoCollection<UserTO> getUserTODBConnection(MongoDatabase mongoClient, @Qualifier("userCodecRegistry") CodecRegistry codecRegistry) {
//		return mongoClient.getCollection("users", UserTO.class).withCodecRegistry(codecRegistry);
//	}
//	
//	@Bean(name="metadataDBCollection")
//	public MongoCollection<Metadata> getMetadataDBConnection(MongoDatabase mongoClient, @Qualifier("metadataCodecRegistry") CodecRegistry codecRegistry) {
//		return mongoClient.getCollection("metadata", Metadata.class).withCodecRegistry(codecRegistry);
//	}

	@Autowired
	protected AWSCredentialsProvider credProvider;

	// use for connecting to web client 
//	@Bean
//	public AmazonDynamoDB getAmazonDynamoDb() {
//		return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_2).withCredentials(credProvider).build();
//	}
	
	// use for local
	@Bean
	public AmazonDynamoDB getAmazonDynamoDb() {
		return AmazonDynamoDBClientBuilder.standard().withCredentials(credProvider).withEndpointConfiguration(new EndpointConfiguration("http://localhost:8000", Regions.US_EAST_2.getName())).build();
	}

	@Bean(name = "dynamoDb")
	public DynamoDB getDynamoDb(AmazonDynamoDB db) {
		return new DynamoDB(db);
	}

	@Bean(name = "dynamoDbMapper")
	public DynamoDBMapper getDynamoDbMapper(AmazonDynamoDB db) {
		return new DynamoDBMapper(db);
	}
}
