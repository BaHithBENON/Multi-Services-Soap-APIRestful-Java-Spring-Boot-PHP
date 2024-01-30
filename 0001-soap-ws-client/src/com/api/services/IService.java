package com.api.services;

import com.api.decoder.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.javaws.services.Etudiant;

public interface IService {
	/**
	 * @param etudiant
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Response doPut(Etudiant etudiant) throws JsonMappingException, JsonProcessingException;

	/**
	 * @param id
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Response doDelete(Integer id) throws JsonMappingException, JsonProcessingException;

	/**
	 * @param etudiant
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Response doPost(Etudiant etudiant) throws JsonMappingException, JsonProcessingException;

	/**
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Response doGet() throws JsonMappingException, JsonProcessingException;

	/**
	 * @param id
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public Response doGet(Integer id) throws JsonMappingException, JsonProcessingException;
}
