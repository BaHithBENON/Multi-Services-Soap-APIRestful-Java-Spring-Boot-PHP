package com.app.service;

import com.app.decoder.Response;
import com.app.entity.Etudiant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

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
