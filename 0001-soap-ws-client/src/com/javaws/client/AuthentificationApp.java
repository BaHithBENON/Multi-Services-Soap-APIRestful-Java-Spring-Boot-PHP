package com.javaws.client;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.javaws.services.WSAuthentification;
import com.javaws.services.WSAuthentificationService;

public class AuthentificationApp {
    
    private WSAuthentificationService serviceAuth = new WSAuthentificationService();
    private WSAuthentification stubAuth;
    
    AuthentificationApp() {
    	stubAuth = serviceAuth.getWSAuthentificationPort();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuthentificationApp app = new AuthentificationApp();
            app.createAndShowGUI();
        });
    }
    
    private void createAndShowGUI() {
        JFrame frame = new JFrame("Authentification App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel labelLogin = new JLabel("Login:");
        JTextField textFieldLogin = new JTextField();

        JLabel labelPassword = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> handleLogin(textFieldLogin.getText(), new String(passwordField.getPassword())));

        panel.add(labelLogin);
        panel.add(textFieldLogin);
        panel.add(labelPassword);
        panel.add(passwordField);
        panel.add(new JLabel()); // Placeholder for spacing
        panel.add(btnLogin);

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
    
    private void handleLogin(String login, String password) {
        boolean logged = stubAuth.login(login, password);

        if (logged) {
            JOptionPane.showMessageDialog(null, "Authentification Réussie. Bienvenue, " + login + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Echec D'authentification");
        }
    }
}
