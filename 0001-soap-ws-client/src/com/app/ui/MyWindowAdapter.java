package com.app.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.app.controllers.ClientAppController;

public class MyWindowAdapter extends WindowAdapter {
	
	private ClientAppController clientAppController;
	
	public MyWindowAdapter(ClientAppController clientAppController) {
    	this.clientAppController = clientAppController;
	}
	
    public void windowClosing(WindowEvent we) {
        // Exécutez votre méthode ici
        System.out.println("Fenêtre fermée. Exécution de la méthode...");
        // Ajoutez ici le code de la méthode que vous souhaitez exécuter à la fermeture de la fenêtre
        clientAppController.showStudentsListUI();
    }
}
