package com.api.invoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javaws.services.Etudiant;
import com.sun.jersey.api.client.ClientResponse;

public interface IRESTInvoker {

	/**
	 * @param id
	 * @return
	 */
	public ClientResponse get(Integer id);

	/**
	 * @return
	 */
	public ClientResponse get();

	/**
	 * @param id
	 * @return
	 */
	public ClientResponse delete(Integer id);

	/**
	 * @param etudiant
	 * @return
	 * @throws JsonProcessingException
	 */
	public ClientResponse post(Etudiant etudiant) throws JsonProcessingException;
	
	/**
	 * @param etudiant
	 * @return
	 * @throws JsonProcessingException
	 */
	public ClientResponse put(Etudiant etudiant) throws JsonProcessingException;
}

