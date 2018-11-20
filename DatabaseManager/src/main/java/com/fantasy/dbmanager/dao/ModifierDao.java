package com.fantasy.dbmanager.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.fantasy.dataaccessutility.model.modifier.Modifier;

@Component
public class ModifierDao extends CommonDao {
	
//	@Autowired
//	@Qualifier("modifierDBCollection")
//	MongoCollection<Modifier> modifierDBCollection;
	
	@Resource(name="modifierTableName")
	private String TABLE_NAME;
	
	@Autowired
	@Qualifier("modifierCreateTableRequest")
	private CreateTableRequest createTableRequest;
	
	public void initDefault() {
		createTable(createTableRequest);
	}

	public void put(List<Modifier> modifiers) {
//		modifierDBCollection.insertMany(modifiers);
		dbMapper.batchSave(modifiers);
	}

	public List<Modifier> getAll() {
//		return modifierDBCollection.find();
		return dbMapper.scan(Modifier.class, new DynamoDBScanExpression());
	}

	public boolean removeAll() {
//		return modifierDBCollection.deleteMany(eq("id", null)).wasAcknowledged();
		createTable(deleteTable(TABLE_NAME));
		return true;
	}

	public long getModifierCount() {
//		return modifierDBCollection.count();
		return dbMapper.count(Modifier.class, new DynamoDBScanExpression());
	}

	public Modifier get(String modifierId) {
//		return modifierDBCollection.find(eq("modifierId", modifierId)).first();
		return dbMapper.load(Modifier.class, modifierId);
	}

	public boolean update(Modifier modifier) {
//		try {
//			return (modifierDBCollection.updateOne(eq("modifierId", modifier.getModifierId()), new Document("$set", Document.parse(new ObjectMapper().writeValueAsString(modifier)))).getModifiedCount() > 0);
//		} catch (Exception e) {
//			e.printStackTrace(); // TODO
//			return false;
//		}
		dbMapper.save(modifier);
		return true;
	}

}
