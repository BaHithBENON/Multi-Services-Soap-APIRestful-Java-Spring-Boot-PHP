package com.api.services;

import com.api.decoder.Response;
import com.api.decoder.ResponseDecoder;
import com.api.invoker.RESTInvoker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.javaws.services.Etudiant;
import com.sun.jersey.api.client.ClientResponse;

public class RESTService implements IService {
	
	private RESTInvoker invoker;
	
	public static final String RESOURCE_URI = "http://localhost:8084/api";
	
	public RESTService() {
		invoker = new RESTInvoker(RESOURCE_URI);
	}
	
	@Override
	public Response doGet (Integer id) throws JsonMappingException, JsonProcessingException {
		ClientResponse clientResponse = invoker.get(id);
		
		ResponseDecoder decoder = new ResponseDecoder(clientResponse);
		return decoder.getResponse();
	}
	
	@Override
	public Response doGet () throws JsonMappingException, JsonProcessingException {
		ClientResponse clientResponse = invoker.get();
		
		ResponseDecoder decoder = new ResponseDecoder(clientResponse);
		return decoder.getResponse();
	}
	
	@Override
	public Response doPost (Etudiant etudiant) throws JsonMappingException, JsonProcessingException {
		ClientResponse clientResponse = invoker.post(etudiant);
		
		ResponseDecoder decoder = new ResponseDecoder(clientResponse);
		return decoder.getResponse();
	}
	
	@Override
	public Response doDelete (Integer id) throws JsonMappingException, JsonProcessingException {
		ClientResponse clientResponse = invoker.delete(id);
		
		ResponseDecoder decoder = new ResponseDecoder(clientResponse);
		return decoder.getResponse();
	}
	
	@Override
	public Response doPut (Etudiant etudiant) throws JsonMappingException, JsonProcessingException {
		ClientResponse clientResponse = invoker.put(etudiant);
		
		ResponseDecoder decoder = new ResponseDecoder(clientResponse);
		return decoder.getResponse();
	}
}
