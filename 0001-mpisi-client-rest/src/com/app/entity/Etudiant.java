package com.app.entity;

import java.io.Serializable;

public class Etudiant implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nom;
	private String prenom;
	private double moyenne;
	
	public Etudiant() {}

	public Etudiant(String nom, String prenom, double moyenne) {
		this.nom = nom;
		this.prenom = prenom;
		this.moyenne = moyenne;
	}
	
	public Etudiant(int id, String nom, String prenom, double moyenne) {
		this (nom, prenom, moyenne);
		this.id = id;
	}

	public Integer getId() {
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

	@Override
	public String toString() {
		return "Etudiant [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", moyenne=" + moyenne + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Etudiant) {
			if (((Etudiant) obj).getId() == id) return true;
			else return false;
		} else return false;
	}
}
