package com.app.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.app.controllers.ClientAppController;

public class StudentManagerUI extends JFrame implements ActionListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ClientAppController clientAppController;

	public StudentManagerUI(ClientAppController clientAppController) {
		super();
		this.clientAppController = clientAppController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void closeUI() {
        this.dispose(); // Ferme la fenêtre
    }

}
