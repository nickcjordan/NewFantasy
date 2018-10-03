package com.fantasy.dbmanager.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.model.Metadata;
import com.fantasy.dbmanager.dao.MetadataDao;

@Component
public class MetadataDatabaseManager {
	
	@Autowired
	private MetadataDao metadataDao;
	
	public long count() {
		return metadataDao.getCount();
	}

	public boolean clear() {
		return metadataDao.removeAll();
	}

	public List<Metadata> getAll() {
		List<Metadata> metadataList = new ArrayList<Metadata>();
		for (Metadata data : metadataDao.getAll()) {
			metadataList.add(data);
		}
		return metadataList;
	}

	public Metadata get(String metadataId) {
		return metadataDao.get(metadataId);
	}

	public boolean updateMetadata(Metadata metadata) {
		 return metadataDao.updateMetadata(metadata);
	}

	public boolean initializeDefaults() {
		return metadataDao.updateMetadata(buildMetadata("currentIdToUse", "1"));
	}
	
	private Metadata buildMetadata(String id, String val) {
		Metadata m = new Metadata();
		m.setMetadataId(id);
		m.setValue(val);
		return m;
	}

}
