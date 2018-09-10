package com.fantasy.dbmanager.config;

import org.bson.Document;
import org.bson.codecs.IntegerCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.modifier.PerkTree;
import com.fantasy.dbmanager.model.TeamTO;
import com.fantasy.dbmanager.model.UserTO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Configuration
public class CommonDatabaseBeanConfig {

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
		return CodecRegistries.fromCodecs(
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Player.class), 
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(TeamTO.class), 
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(UserTO.class),
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
				new IntegerCodec(),
				MongoClient.getDefaultCodecRegistry().get(String.class)
		);
	}
	
	@Bean(name="playerDBCollection")
	public MongoCollection<Player> getPlayerDBConnection(MongoDatabase mongoClient, CodecRegistry codecRegistry) {
		return mongoClient.getCollection("players", Player.class).withCodecRegistry(codecRegistry);
	}
	
	@Bean(name="teamDBCollection")
	public MongoCollection<TeamTO> getTeamTODBConnection(MongoDatabase mongoClient, CodecRegistry codecRegistry) {
		return mongoClient.getCollection("teams", TeamTO.class).withCodecRegistry(codecRegistry);
	}
	
	@Bean(name="userDBCollection")
	public MongoCollection<UserTO> getUserTODBConnection(MongoDatabase mongoClient, CodecRegistry codecRegistry) {
		return mongoClient.getCollection("users", UserTO.class).withCodecRegistry(codecRegistry);
	}
	
}
