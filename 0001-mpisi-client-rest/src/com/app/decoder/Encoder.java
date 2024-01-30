package com.app.decoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Encoder {
	public static String toJson (Object entity) throws JsonProcessingException {
		ObjectMapper encoder = new ObjectMapper();
		return encoder.writeValueAsString(entity);
	}
}
