package com.javaws.entities;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="etudiant")
public class Etudiant implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private String prenom;
	private double moyenne;
	
	public Etudiant() {}
	
	
	public Etudiant(String nom, String prenom, double moyenne) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.moyenne = moyenne;
	}


	public Etudiant(int id, String nom, String prenom, double moyenne) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.moyenne = moyenne;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

}
