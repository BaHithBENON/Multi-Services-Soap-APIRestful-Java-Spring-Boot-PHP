package com.javaws.client;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.javaws.services.EtudiantWS;
import com.javaws.services.WSAuthentification;
import com.javaws.services.WSAuthentificationService;
import com.javaws.services.WSEtudiantService;

public class ServiceWebClient {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
		try {
			// Instanciation du proxy : stub
			WSEtudiantService service = new WSEtudiantService();
			EtudiantWS stub = service.getEtudiantWSPort();
			
			WSAuthentificationService serviceAuth = new WSAuthentificationService();
			WSAuthentification stubAuth = serviceAuth.getWSAuthentificationPort();
			
			System.out.println("\n\nConnexion: ");
			System.out.print("Entrez le login : ");
	        String login = scanner.nextLine();

	        System.out.print("Entrez le password : ");
	        String password = scanner.nextLine();
	        
	        boolean logged = stubAuth.login(login, password);
	        
	        if(logged) {
	        	System.out.println("Auth réussie");
	        	JOptionPane.showMessageDialog(null, "Authentification Réussie. Bienvenue, " + login + "!");
	        } else {
	        	System.out.println("Auth echouée");
	        	JOptionPane.showMessageDialog(null, "Authentification Echoué. ");
	        }
			
			/*
			// Invocation des fonctionnalités offertes par le service web
			List<Etudiant> etudiants = stub.getEtudiants();
			for (Etudiant etudiant : etudiants) {
				System.out.println(etudiant.getNom() + " " + etudiant.getPrenom() + " " +
				etudiant.getMoyenne());
			}
			
			int etudiantsSize = stub.getSize();
			System.out.println("\n\nNombre d'etudiants : " + etudiantsSize);
			
			System.out.println("\n\nCreation d'un etudiant : ");
			System.out.print("Entrez le nom de l'étudiant : ");
	        String nom = scanner.nextLine();

	        System.out.print("Entrez le prénom de l'étudiant : ");
	        String prenom = scanner.nextLine();

	        System.out.print("Entrez la moyenne de l'étudiant : ");
	        double moyenne = scanner.nextDouble();
			stub.newStudent(nom, prenom, moyenne);
			
			etudiantsSize = stub.getSize();
			System.out.println("\n\nNombre d'etudiants : " + etudiantsSize);
			*/
			
		} catch (jakarta.xml.ws.WebServiceException e) {
			// Lorsque le service web n'est pas accesible : non publié par exemple !
			System.err.println("An error occurred while trying to access the web service : " +
				e.getMessage());
		}
		
		scanner.close();
	}
}
