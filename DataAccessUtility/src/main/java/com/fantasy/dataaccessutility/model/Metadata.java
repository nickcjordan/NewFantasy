package com.fantasy.dataaccessutility.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Metadata {
	
//	@JsonProperty
	private String metadataId;
//	@JsonProperty
	private String value;
	
//	public Metadata() {}
//	
//	public Metadata(String metadataId, String value) {
//		this.metadataId = metadataId;
//		this.value = value; 
//	}
//	
//	public Metadata(String metadataId, Number value) {
//		this.metadataId = metadataId;
//		this.value = String.valueOf(value); 
//	}
	
//	@JsonGetter
	public String getMetadataId() {
		return metadataId;
	}
//	@JsonSetter
	public void setMetadataId(String metadataId) {
		this.metadataId = metadataId;
	}
//	@JsonGetter
	public String getValue() {
		return value;
	}
//	@JsonSetter
	public void setValue(String value) {
		this.value = value;
	}
	
}
