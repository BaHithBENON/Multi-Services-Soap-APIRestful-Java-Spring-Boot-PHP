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
import javax.swing.JTextField;

import com.app.controllers.ClientAppController;
import com.javaws.services.Etudiant;

public class CreateEtudiantUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ClientAppController clientAppController;

    private JLabel titleLabel;
    private JLabel subtitleLabel;

    private JLabel nameLabel;
    private JTextField nameTextField;

    private JLabel surnameLabel;
    private JTextField surnameTextField;

    private JLabel averageLabel;
    private JTextField averageTextField;

    private JButton createButton;
    private JButton resetButton;

    public CreateEtudiantUI(ClientAppController clientAppController) {
        super();
        this.clientAppController = clientAppController;

        JPanel container = new JPanel();
        container.setLayout(null);

        titleLabel = new JLabel("Création d'Étudiant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(10, 10, 250, 30);
        container.add(titleLabel);

        subtitleLabel = new JLabel("Entrez les informations ci-dessous");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setBounds(10, 40, 250, 20);
        container.add(subtitleLabel);

        nameLabel = new JLabel("Nom");
        nameLabel.setBounds(10, 80, 80, 25);
        container.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(100, 80, 160, 25);
        container.add(nameTextField);

        surnameLabel = new JLabel("Prénom");
        surnameLabel.setBounds(10, 110, 80, 25);
        container.add(surnameLabel);

        surnameTextField = new JTextField();
        surnameTextField.setBounds(100, 110, 160, 25);
        container.add(surnameTextField);

        averageLabel = new JLabel("Moyenne");
        averageLabel.setBounds(10, 140, 80, 25);
        container.add(averageLabel);

        averageTextField = new JTextField();
        averageTextField.setBounds(100, 140, 160, 25);
        container.add(averageTextField);

        createButton = new JButton("Créer");
        createButton.setBounds(10, 180, 100, 30);
        createButton.addActionListener(this);
        createButton.setBackground(new Color(138, 43, 226)); // Violet color
        createButton.setForeground(Color.WHITE); // White text color
        container.add(createButton);

        resetButton = new JButton("Réinitialiser");
        resetButton.setBounds(120, 180, 140, 30);
        resetButton.addActionListener(this);
        container.add(resetButton);

        container.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        this.add(container);
        this.setTitle("Création Étudiant");
        this.setSize(300, 250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        addWindowListener(new MyWindowAdapter(clientAppController));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String name = nameTextField.getText();
            String surname = surnameTextField.getText();
            String averageText = averageTextField.getText();

            if (name.isEmpty() || surname.isEmpty() || averageText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            } else {
                try {
                    double average = Double.parseDouble(averageText);
                    Etudiant newEtudiant = new Etudiant();
                    newEtudiant.setNom(name);
                    newEtudiant.setPrenom(surname);
                    newEtudiant.setMoyenne(average);
                    
                    boolean created = clientAppController.createStudent(newEtudiant);

                    if (created) {
                        JOptionPane.showMessageDialog(null, "Étudiant créé avec succès.");
                        // this.dispose(); // Ferme la fenêtre de création d'étudiant après la création réussie.
                        ereaseInputs();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la création de l'étudiant.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir une moyenne valide.");
                }
            }
        } else if (e.getSource() == resetButton) {
        	ereaseInputs();
        }
    }
    
    public void ereaseInputs() {
    	nameTextField.setText("");
        surnameTextField.setText("");
        averageTextField.setText("");
    }
    
    public void closeUI() {
        this.dispose(); // Ferme la fenêtre
    }
}
