package com.app.decoder;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.Etudiant;

public class Response {
	private Integer code;
	private String status;
	private String message;
	
	private Etudiant entity;
	private List<Etudiant> entities = new ArrayList<>();
	
	public Response() {}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Etudiant getEntity() {
		return entity;
	}
	
	public void setEntities(List<Etudiant> entities) {
		this.entities = entities;
	}
	
	public List<Etudiant> getEntities() {
		return entities;
	}
}
