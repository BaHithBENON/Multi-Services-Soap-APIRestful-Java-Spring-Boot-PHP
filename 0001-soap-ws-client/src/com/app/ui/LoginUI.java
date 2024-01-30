package com.app.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.app.controllers.ClientAppController;

public class LoginUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ClientAppController clientAppController;

    private JLabel titleLabel;
    private JLabel subtitleLabel;

    private JLabel userLabel;
    private JTextField userTextField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton resetButton;

    public LoginUI(ClientAppController clientAppController) {
        super();
        this.clientAppController = clientAppController;

        JPanel container = new JPanel();
        container.setLayout(null);

        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(10, 10, 150, 30);
        container.add(titleLabel);

        subtitleLabel = new JLabel("Entrez vos informations ci-dessous");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setBounds(10, 40, 250, 20);
        container.add(subtitleLabel);

        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 80, 80, 25);
        container.add(userLabel);

        userTextField = new JTextField();
        userTextField.setBounds(100, 80, 160, 25);
        container.add(userTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 110, 80, 25);
        container.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 110, 160, 25);
        container.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 150, 100, 30);
        loginButton.addActionListener(this);
        loginButton.setBackground(new Color(138, 43, 226)); // Violet color
        loginButton.setForeground(Color.WHITE); // White text color
        container.add(loginButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(120, 150, 100, 30);
        resetButton.addActionListener(this);
        container.add(resetButton);

        container.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        this.add(container);
        this.setTitle("Login App");
        this.setSize(300, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userTextField.getText();
        String password = new String(passwordField.getPassword());

        if (e.getSource() == loginButton) {
            boolean isLogged = clientAppController.handleLogin(username, password);
            if (isLogged) {
                clientAppController.showStudentsListUI();
                closeUI();
            } else {
                JOptionPane.showMessageDialog(null, "Echec D'authentification");
            }
        } else if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
    }

    public void closeUI() {
        this.dispose(); // Ferme la fenêtre
    }
}
