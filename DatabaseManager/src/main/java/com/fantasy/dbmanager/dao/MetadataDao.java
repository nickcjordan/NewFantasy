package com.fantasy.dbmanager.dao;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Metadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

@Component
public class MetadataDao {
	
	@Autowired
	@Qualifier("metadataDBCollection")
	MongoCollection<Metadata> metadataDBCollection;
	

	public boolean removeAll() {
		return metadataDBCollection.deleteMany(eq("id", null)).wasAcknowledged();
	}

	public long getCount() {
		return metadataDBCollection.count();
	}

	public Metadata get(String metadataId) {
		return metadataDBCollection.find(eq("metadataId", metadataId)).first();
	}

	public boolean updateMetadata(Metadata metadata) {
		try {
			Metadata res = metadataDBCollection.findOneAndReplace(eq("metadataId", metadata.getMetadataId()), metadata);
			if (res == null) {
				metadataDBCollection.insertOne(metadata);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public FindIterable<Metadata> getAll() {
		return metadataDBCollection.find();
	}
}
