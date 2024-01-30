package com.app.client;

import java.util.List;

import com.app.decoder.Response;
import com.app.entity.Etudiant;
import com.app.service.IService;
import com.app.service.RESTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class RESTClient {

	private IService rest;
	
	public RESTClient() {
		rest = new RESTService ();
	}
	
	public static void main(String[] args) {
		RESTClient client = new RESTClient();					
		// client.testerGET();
		// client.testerGET(13);
		// client.testerDELETE(13);
		client.testerPUT(new Etudiant(13, "pp", "rty", 19));
		// client.testerPOST(new Etudiant("azertyuiop", "lol", 15));
	}
	
	public void testerGET () {
		try {
			Response response = rest.doGet();
			Integer code = response.getCode();
			
			if (code == 200) {
				List<Etudiant> etudiants = response.getEntities();
				
				for (Etudiant item : etudiants) {
					System.out.println(item);
				}
			} else {
				System.out.println("Error code : " + code);

				String message = response.getMessage();
				System.out.println("Error Message : '" + message + "'");
			}
			
		} catch (JsonProcessingException e) {
			System.err.println("Error Class : " + e.getClass() + "\nError Message : " + e.getMessage());
		}
	}
	
	// TODO
	public void testerGET(Integer id) {
		try {
			Response response = rest.doGet(id);
			Integer code = response.getCode();
			
			if (code == 200) {
				Etudiant etudiant = response.getEntity();
				System.out.println(etudiant);
			} else {
				System.out.println("Error code : " + code);

				String message = response.getMessage();
				System.out.println("Error Message : '" + message + "'");
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// TODO
	public void testerDELETE (Integer id) {
		try {
			Response response = rest.doDelete(id);
			Integer code = response.getCode();
			
			if (code == 200) {
				Etudiant etudiant = response.getEntity();
				if(etudiant != null) 
					System.out.println(etudiant);
				else 
					System.out.println("Etudiant supprimé avecc succès");
			} else {
				System.out.println("Error code : " + code);

				String message = response.getMessage();
				System.out.println("Error Message : '" + message + "'");
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// TODO
	public void testerPOST (Etudiant etudiant) {
		try {
			Response response = rest.doPost(etudiant);
			Integer code = response.getCode();
			
			if (code == 200) {
				Etudiant etudiant2 = response.getEntity();
				if(etudiant != null) 
					System.out.println(etudiant2);
				else 
					System.out.println("Etudiant crée avecc succès");
			} else {
				System.out.println("Error code : " + code);

				String message = response.getMessage();
				System.out.println("Error Message : '" + message + "'");
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// TODO
	public void testerPUT (Etudiant etudiant) {
		try {
			Response response = rest.doPut(etudiant);
			Integer code = response.getCode();
			
			if (code == 200) {
				Etudiant etudiant2 = response.getEntity();
				if(etudiant != null) 
					System.out.println(etudiant2);
				else 
					System.out.println("Etudiant modifié avecc succès");
				
			} else {
				System.out.println("Error code : " + code);

				String message = response.getMessage();
				System.out.println("Error Message : '" + message + "'");
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
