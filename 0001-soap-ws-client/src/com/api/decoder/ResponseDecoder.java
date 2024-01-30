package com.api.decoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

public class ResponseDecoder {
	private Response response;
	private ObjectMapper decoder;		
	
	public ResponseDecoder(ClientResponse clientResponse) throws JsonMappingException, JsonProcessingException {
		decoder = new ObjectMapper();
		decoder.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		setClientResponse (clientResponse);
	}
	
	public void setClientResponse (ClientResponse clientResponse) throws JsonMappingException, JsonProcessingException {
		String json = clientResponse.getEntity(String.class);
		response = decoder.readValue(json, Response.class);
	}
	public Response getResponse() {
		return response;
	}
}
