package com.javaws.services;

import java.util.List;

import com.javaws.dao.EtudiantDaoImpl;
import com.javaws.entities.Etudiant;
import com.javaws.exceptions.DAOException;
import com.javaws.interfaces.IEtudiantRepository;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(name="EtudiantWS")
public class WSEtudiant {
	
	private IEtudiantRepository etudiantDao;
	public WSEtudiant() {
        this.etudiantDao = new EtudiantDaoImpl();
    }
	
	@WebMethod(operationName="getSize")
	public int size () {
		try {
			return etudiantDao.list().size();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@WebMethod
	public double getMoyenne (@WebParam(name="nom")String nom, @WebParam(name="prenom")String prenom){
		List<Etudiant> etudiants;
		try {
			etudiants = etudiantDao.list();
			double sommeMoyennes = 0;
			int count = 0;
			
			for (Etudiant etudiant : etudiants) {
				if (etudiant.getNom().equalsIgnoreCase(nom) && etudiant.getPrenom().equalsIgnoreCase(prenom)) {
					sommeMoyennes += etudiant.getMoyenne();
					count++;
				}
			}
			
			if (count > 0) {
				return sommeMoyennes / count;
			} else {
				return 0.0;
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@WebMethod
	public List<Etudiant> getEtudiants () {
		try {
			return etudiantDao.list();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@WebMethod
	public void newStudent (@WebParam(name="nom")String nom, 
			@WebParam(name="prenom")String prenom, 
			@WebParam(name="moyenne")double moyenne) {
		
		try {
			etudiantDao.create(new Etudiant(nom, prenom, moyenne));
		} catch (DAOException e) {
			System.err.println("WSEtudiantService Error : newStudent web method" + e.getMessage());
		}
	}
}
