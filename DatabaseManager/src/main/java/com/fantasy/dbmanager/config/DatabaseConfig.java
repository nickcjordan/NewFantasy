package com.fantasy.dbmanager.config;

import org.bson.Document;
import org.bson.codecs.IntegerCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fantasy.dataaccessutility.model.Metadata;
import com.fantasy.dataaccessutility.model.Player;
import com.fantasy.dataaccessutility.model.modifier.Modifier;
import com.fantasy.dataaccessutility.model.team.NflTeam;
import com.fantasy.dbmanager.model.TeamTO;
import com.fantasy.dbmanager.model.UserTO;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Configuration
public class DatabaseConfig {

	@Bean
	public MongoClient getMongoClient() {
		return new MongoClient();
	}
	
	@Bean
	public MongoDatabase getMongoDatabase(MongoClient mongoClient) {
		return mongoClient.getDatabase("fantasyDB");
	}
	
	@Bean(name="userCodecRegistry")
	public CodecRegistry getUserCodecRegistry() {
		return CodecRegistries.fromCodecs(
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Player.class), 
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(TeamTO.class), 
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(UserTO.class),
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
				new IntegerCodec(),
				MongoClient.getDefaultCodecRegistry().get(String.class)
		);
	}
	
	@Bean(name="playerCodecRegistry")
	public CodecRegistry getPlayerCodecRegistry() {
		return CodecRegistries.fromCodecs(
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Player.class), 
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
				new IntegerCodec(),
				MongoClient.getDefaultCodecRegistry().get(String.class)
		);
	}
	
	@Bean(name="modifierCodecRegistry")
	public CodecRegistry getModifierCodecRegistry() {
		return CodecRegistries.fromCodecs(
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Modifier.class), 
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(NflTeam.class),
				new IntegerCodec(),
				MongoClient.getDefaultCodecRegistry().get(String.class)
		);
	}
	
	@Bean(name="metadataCodecRegistry")
	public CodecRegistry getMetadataCodecRegistry() {
		return CodecRegistries.fromCodecs(
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Document.class),
				CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())).get(Metadata.class),
				new IntegerCodec(),
				MongoClient.getDefaultCodecRegistry().get(String.class)
		);
	}
	
	@Bean(name="playerDBCollection")
	public MongoCollection<Player> getPlayerDBConnection(MongoDatabase mongoClient, @Qualifier("playerCodecRegistry") CodecRegistry codecRegistry) {
		return mongoClient.getCollection("players", Player.class).withCodecRegistry(codecRegistry);
	}
	
	@Bean(name="modifierDBCollection")
	public MongoCollection<Modifier> getModifierDBConnection(MongoDatabase mongoClient, @Qualifier("modifierCodecRegistry") CodecRegistry codecRegistry) {
		return mongoClient.getCollection("modifiers", Modifier.class).withCodecRegistry(codecRegistry);
	}
	
	@Bean(name="userDBCollection")
	public MongoCollection<UserTO> getUserTODBConnection(MongoDatabase mongoClient, @Qualifier("userCodecRegistry") CodecRegistry codecRegistry) {
		return mongoClient.getCollection("users", UserTO.class).withCodecRegistry(codecRegistry);
	}
	
	@Bean(name="metadataDBCollection")
	public MongoCollection<Metadata> getMetadataDBConnection(MongoDatabase mongoClient, @Qualifier("metadataCodecRegistry") CodecRegistry codecRegistry) {
		return mongoClient.getCollection("metadata", Metadata.class).withCodecRegistry(codecRegistry);
	}
}
