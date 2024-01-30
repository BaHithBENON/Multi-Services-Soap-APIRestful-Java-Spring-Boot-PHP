package com.app.invoker;

import javax.ws.rs.core.MediaType;

import com.app.decoder.Encoder;
import com.app.entity.Etudiant;
import com.fasterxml.jackson.core.JsonProcessingException;
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
		WebResource webResource = this.webResource.path("/etudiant/" + id);
		return get(webResource);
	}

	@Override
	public ClientResponse get () {
		webResource = this.webResource.path("/etudiants");
		return get(webResource);
	}

	@Override
	public ClientResponse delete (Integer id) {
		WebResource webResource = this.webResource.path("/etudiant/" + id);
		Builder builder = getBuilder (webResource);
		
		ClientResponse clientResponse = builder.delete(ClientResponse.class);
		return clientResponse;
	}

	@Override
	public ClientResponse post(Etudiant entity) throws JsonProcessingException {
		webResource = this.webResource.path("/etudiant");
		webResource = addQueryParam(entity);
		Builder builder = getBuilder (webResource);
		String jsonInput = Encoder.toJson(entity);
		
		ClientResponse clientResponse = builder.post(ClientResponse.class, jsonInput);

		return clientResponse;
	}

	@Override
	public ClientResponse put(Etudiant entity) throws JsonProcessingException {

		webResource = this.webResource.path("/etudiant/" + String.valueOf(entity.getId()));

		webResource = addQueryParam(entity);
		Builder builder = getBuilder (webResource);
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
	
	private WebResource addQueryParam (Etudiant entity) {		
		webResource = webResource.queryParam("nom", entity.getNom());
		webResource = webResource.queryParam("prenom", entity.getPrenom());
		webResource = webResource.queryParam("moyenne", String.valueOf(entity.getMoyenne()));
		
		return webResource;
	}
}

