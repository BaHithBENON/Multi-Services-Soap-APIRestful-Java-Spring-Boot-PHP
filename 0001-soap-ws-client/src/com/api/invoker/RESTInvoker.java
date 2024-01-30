package com.api.invoker;

import javax.ws.rs.core.MediaType;

import com.api.decoder.Encoder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.javaws.services.Etudiant;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;

public class RESTInvoker  implements IRESTInvoker {

	private WebResource webResource;

	public RESTInvoker(String uri) {
		Client client = Client.create();
		webResource = client.resource(uri);
	}

	@Override
	public ClientResponse get (Integer id) {
		WebResource getWebResource = this.webResource.path("/etudiant/" + id);
		return get(getWebResource);
	}

	@Override
	public ClientResponse get () {
		WebResource getWebResource = this.webResource.path("/etudiants");
		return get(getWebResource);
	}

	@Override
	public ClientResponse delete (Integer id) {
		WebResource deleteWebResource = this.webResource.path("/etudiant/" + id);
		Builder builder = getBuilder (deleteWebResource);
		
		ClientResponse clientResponse = builder.delete(ClientResponse.class);
		return clientResponse;
	}

	@Override
	public ClientResponse post(Etudiant entity) throws JsonProcessingException {
		WebResource postWebResource = this.webResource.path("/etudiant");
		postWebResource = addQueryParam(postWebResource, entity);
		Builder builder = getBuilder (postWebResource);
		String jsonInput = Encoder.toJson(entity);
		
		ClientResponse clientResponse = builder.post(ClientResponse.class, jsonInput);

		return clientResponse;
	}

	@Override
	public ClientResponse put(Etudiant entity) throws JsonProcessingException {

		WebResource putWebResource = this.webResource.path("/etudiant/" + String.valueOf(entity.getId()));

		putWebResource = addQueryParam(putWebResource, entity);
		Builder builder = getBuilder (putWebResource);
		String jsonInput = Encoder.toJson(entity);
		
		ClientResponse clientResponse = builder.put(ClientResponse.class, jsonInput);
		
		return clientResponse;
	}

	private ClientResponse get (WebResource webResource) {
		Builder builder = getBuilder (webResource);
		ClientResponse clientResponse = builder.get(ClientResponse.class);

		return clientResponse;
	}

	private Builder getBuilder (WebResource webResource) {
		Builder builder = webResource.accept(MediaType.APPLICATION_JSON)				
				.header("content-type", MediaType.APPLICATION_JSON); 
		return builder;
	}
	
	private WebResource addQueryParam (WebResource webResource, Etudiant entity) {		
		webResource = webResource.queryParam("nom", entity.getNom());
		webResource = webResource.queryParam("prenom", entity.getPrenom());
		webResource = webResource.queryParam("moyenne", String.valueOf(entity.getMoyenne()));
		
		return webResource;
	}
}

