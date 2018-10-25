package com.fantasy.dbmanager.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fantasy.dataaccessutility.model.Metadata;
import com.fantasy.dataaccessutility.model.Player;

@Component
public class MetadataDao extends CommonDao {

	private static final String TABLE_NAME = "metadata-table";

	// @Autowired
	// @Qualifier("metadataDBCollection")
	// MongoCollection<Metadata> metadataDBCollection;

	public boolean removeAll() {
		// return metadataDBCollection.deleteMany(eq("id", null)).wasAcknowledged();
		createTable(deleteTable(TABLE_NAME));
		return true;
	}

	 public int getCount() {
//	 return metadataDBCollection.count();
		 return dbMapper.count(Metadata.class, new DynamoDBScanExpression());
	 }

	public Metadata get(String metadataId) {
//		return metadataDBCollection.find(eq("metadataId", metadataId)).first();
		return dbMapper.load(Metadata.class, metadataId);
	}

	public boolean updateMetadata(Metadata metadata) {
//		try {
//			Metadata res = metadataDBCollection.findOneAndReplace(eq("metadataId", metadata.getMetadataId()), metadata);
//			if (res == null) {
//				metadataDBCollection.insertOne(metadata);
//			}
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		dbMapper.save(metadata);
		return true;
	}

	public List<Metadata> getAll() {
//		return metadataDBCollection.find();
		return dbMapper.scan(Metadata.class, new DynamoDBScanExpression());
	}
}
