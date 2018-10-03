package com.fantasy.dataaccessutility.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.dataaccessutility.access.DatabaseManagerDelegate;
import com.fantasy.dataaccessutility.model.Metadata;

@Component
public class MetadataDataAccessManager {
	
	private static final Logger log = LoggerFactory.getLogger(MetadataDataAccessManager.class);
	
	@Autowired
	private DatabaseManagerDelegate delegate;
	
	public Metadata getMetadataById(String metadataId) {
		log.info("Getting Metadata: " + metadataId);
		Metadata metadata = delegate.getMetadataById(metadataId);
		log.info("Got Metadata: " + metadata.getMetadataId() + "=" + metadata.getValue());
		return metadata;
	}

	public boolean updateMetadata(Metadata metadata) {
		log.info("Updating Metadata: " + metadata.getMetadataId() + "=" + metadata.getValue());
		boolean success = delegate.updateMetadata(metadata);;
		log.info("Metadata update: success=" + success + " :: " + metadata.getMetadataId() + "=" + metadata.getValue());
		return success;
	}

}
