package com.fantasy.dbmanager.config;

import org.bson.codecs.Codec;
import org.bson.codecs.IntegerCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fantasy.dbmanager.model.Player;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Configuration
public class DatabaseBeanConfig {

	@Bean
	public MongoClient getMongoClient() {
		return new MongoClient();
	}
	
	@Bean
	public MongoDatabase getMongoDatabase(MongoClient mongoClient) {
		return mongoClient.getDatabase("fantasyDB");
	}
	
	@Bean
	public CodecRegistry getCodecRegistry() {
		return CodecRegistries.fromCodecs(CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), 
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Player.class), new IntegerCodec());
	}
	
	@Bean(name="playerDBCollection")
	public MongoCollection<Player> getPlayerDBConnection(MongoDatabase mongoClient, CodecRegistry codecRegistry) {
		return mongoClient.getCollection("players", Player.class).withCodecRegistry(codecRegistry);
	}
	
}
