package com.fantasy.dbmanager.config;

import org.bson.codecs.IntegerCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fantasy.dbmanager.model.PerkTree;
import com.fantasy.dbmanager.model.Player;
import com.fantasy.dbmanager.model.Team;
import com.fantasy.dbmanager.model.User;
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
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Team.class), 
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(PerkTree.class),
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(User.class),
				new IntegerCodec(),
				MongoClient.getDefaultCodecRegistry().get(String.class)
		);
	}
	
	@Bean(name="playerDBCollection")
	public MongoCollection<Player> getPlayerDBConnection(MongoDatabase mongoClient, CodecRegistry codecRegistry) {
		return mongoClient.getCollection("players", Player.class).withCodecRegistry(codecRegistry);
	}
	
	@Bean(name="teamDBCollection")
	public MongoCollection<Team> getTeamDBConnection(MongoDatabase mongoClient, CodecRegistry codecRegistry) {
		return mongoClient.getCollection("teams", Team.class).withCodecRegistry(codecRegistry);
	}
	
	@Bean(name="userDBCollection")
	public MongoCollection<User> getUserDBConnection(MongoDatabase mongoClient, CodecRegistry codecRegistry) {
		return mongoClient.getCollection("users", User.class).withCodecRegistry(codecRegistry);
	}
	
}
