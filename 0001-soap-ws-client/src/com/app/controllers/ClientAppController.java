package com.app.controllers;

import java.util.List;

import com.api.decoder.Response;
import com.api.services.IService;
import com.api.services.RESTService;
import com.app.ui.CreateEtudiantUI;
import com.app.ui.LoginUI;
import com.app.ui.StudentManagerUI;
import com.app.ui.StudentsListUI;
import com.app.ui.UpdateEtudiantUI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.javaws.services.Etudiant;
import com.javaws.services.WSAuthentification;
import com.javaws.services.WSAuthentificationService;

public class ClientAppController {
	
	/*Soap Services*/
	private WSAuthentificationService serviceAuth = new WSAuthentificationService();
    private WSAuthentification stubAuth;
    
    /*API RESTful Services */
    private IService rest;
    
    public ClientAppController() {
    	stubAuth = serviceAuth.getWSAuthentificationPort();
		rest = new RESTService ();
    }
    
    LoginUI loginUI;
    StudentManagerUI studentManagerUI;
    CreateEtudiantUI createEtudiantUI;
    StudentsListUI studentsList;
    UpdateEtudiantUI updateEtudiantUI;
    
    public void start() {
    	/*
    	this.loginUI = new LoginUI(this);
        this.loginUI.setVisible(true);
        */
    	showLoginUI();
    	// showCreateEtudiantUI();
    	// showStudentsListUI();
    }
    
    public void showLoginUI() {
		this.loginUI = new LoginUI(this);
        this.loginUI.setVisible(true);
	}
    
    public void closeLoginUI() { 
    	loginUI.closeUI();
    }
    
    public void showStudentManagerUI() {
		// Close LoginUI
    	if(loginUI != null)
    		closeLoginUI();
		this.studentManagerUI = new StudentManagerUI(this);
        this.studentManagerUI.setVisible(true);
	}
    
    public void closeStudentManagerUI() { 
    	this.studentManagerUI.closeUI();
    }
    
    public void showCreateEtudiantUI() {
    	this.createEtudiantUI = new CreateEtudiantUI(this);
    }
    
    public void showUpdateStudentUI(Etudiant student) {
    	this.updateEtudiantUI = new UpdateEtudiantUI(this, student);
    }
    
    public void closeUpdateUI() { 
    	this.updateEtudiantUI.closeUI();
    }
    
    public void showStudentsListUI() {
    	// Récupérez la liste des étudiants et affichez-la dans la vue
    	if(studentsList != null && !studentsList.isVisible()) {
    		studentsList.setVisible(true);
    	} else {
    		List<Etudiant> etudiants = getAllStudents();
    		studentsList = new StudentsListUI(this);
    		studentsList.afficherListeEtudiants(etudiants);
    		studentsList.setVisible(true);
    	}
    }
    
    public void closeStudentsListUI() {
    	studentsList.closeUI();
    }
    
    public void closeCreateEtudiantUI() { 
    	this.createEtudiantUI.closeUI();
    }
    
    public boolean handleLogin(String login, String password) {
        boolean logged = stubAuth.login(login, password);
        return logged;
    }
    
    public List<Etudiant> getAllStudents() {
    	List<Etudiant> students = null;
		try {
			Response response = rest.doGet();
			Integer code = response.getCode();
			
			if (code == 200) {
				students = response.getEntities();
			} else {
				System.out.println("Error code : " + code);

				String message = response.getMessage();
				System.out.println("Error Message : '" + message + "'");
			}
			
		} catch (JsonProcessingException e) {
			System.err.println("Error Class : " + e.getClass() + "\nError Message : " + e.getMessage());
		}
		return students;
	}
    
    public Etudiant getStudentById(Integer id) {
    	Etudiant student = null;
		try {
			Response response = rest.doGet(id);
			Integer code = response.getCode();
			
			if (code == 200) {
				student = response.getEntity();
				System.out.println(student);
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
		return student;
	}
    
    public boolean deleteStudentById (Integer id) {
    	boolean result = false;
		try {
			Response response = rest.doDelete(id);
			Integer code = response.getCode();
			
			if (code == 200) {
				Etudiant etudiant = response.getEntity();
				if(etudiant != null) {
					System.out.println(etudiant);
				} else {
					result = true;
					System.out.println("Etudiant supprimé avec succès");
				}
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
		return result;
	}
    
    public boolean createStudent (Etudiant etudiant) {
    	boolean result = false;
		try {
			Response response = rest.doPost(etudiant);
			Integer code = response.getCode();
			
			if (code == 200) {
				Etudiant etudiant2 = response.getEntity();
				result = true;
				if(etudiant2 != null) 
					System.out.println(etudiant2);
				else {
					System.out.println("Etudiant créer avec succès");
				}
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
		return result;
	}
    
    public boolean updateStudent (Etudiant etudiant) {
    	boolean result = false;
		try {
			Response response = rest.doPut(etudiant);
			Integer code = response.getCode();
			
			if (code == 200) {
				Etudiant etudiant2 = response.getEntity();
				result = true;
				if(etudiant2 != null) 
					System.out.println(etudiant2);
				else {
					result = true;
					System.out.println("Etudiant modifé avec succès");
				}
				
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
		return result;
	}
}
