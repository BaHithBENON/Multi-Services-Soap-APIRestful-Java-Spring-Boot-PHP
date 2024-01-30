package com.api.entities;

public class Etudiant {
	
	private int id;
	private String nom;
	private String prenom;
	private double moyenne;

	public Etudiant() {}
	
	public Etudiant(int id, String nom, String prenom, double moyenne) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.moyenne = moyenne;
	}
	
	public int getId() { return id; }
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() { return nom; }
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}
	
	public double getMoyenne() { return moyenne; }
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPrenom() {	return prenom; }
}
