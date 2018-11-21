package com.fantasy.dbmanager.auth;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fantasy.dbmanager.auth.model.UserCredential;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;


public class UserCredentialDeserializer extends JsonDeserializer<UserCredential> {
	
	private static Logger log = Logger.getLogger(UserCredentialDeserializer.class);
	
	private JsonNode root;

	@Override
	public UserCredential deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		root = p.getCodec().readTree(p);
        return new UserCredential(get("id"), get("firstName"), get("lastName"), get("username"), get("password"), get("token"));
	}

	private String get(String field) {
		try {
			return root.get(field).asText();
		} catch (Exception e) {
			log.error("Null value for field " + field + " in incoming request");
			return null;
		}
	}

}
