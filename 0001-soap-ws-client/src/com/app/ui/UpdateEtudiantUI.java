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

public class UpdateEtudiantUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ClientAppController clientAppController;
    private Etudiant etudiant;

    private JLabel titleLabel;
    private JLabel subtitleLabel;

    private JLabel nameLabel;
    private JTextField nameTextField;

    private JLabel surnameLabel;
    private JTextField surnameTextField;

    private JLabel averageLabel;
    private JTextField averageTextField;

    private JButton updateButton;
    private JButton cancelButton;

    public UpdateEtudiantUI(ClientAppController clientAppController, Etudiant etudiant) {
        super();
        this.clientAppController = clientAppController;
        this.etudiant = etudiant;

        JPanel container = new JPanel();
        container.setLayout(null);

        titleLabel = new JLabel("Mise à jour de l'Étudiant");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(10, 10, 300, 30);
        container.add(titleLabel);

        subtitleLabel = new JLabel("Modifiez les informations ci-dessous");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setBounds(10, 40, 250, 20);
        container.add(subtitleLabel);

        nameLabel = new JLabel("Nom");
        nameLabel.setBounds(10, 80, 80, 25);
        container.add(nameLabel);

        nameTextField = new JTextField(etudiant.getNom());
        nameTextField.setBounds(100, 80, 160, 25);
        container.add(nameTextField);

        surnameLabel = new JLabel("Prénom");
        surnameLabel.setBounds(10, 110, 80, 25);
        container.add(surnameLabel);

        surnameTextField = new JTextField(etudiant.getPrenom());
        surnameTextField.setBounds(100, 110, 160, 25);
        container.add(surnameTextField);

        averageLabel = new JLabel("Moyenne");
        averageLabel.setBounds(10, 140, 80, 25);
        container.add(averageLabel);

        averageTextField = new JTextField(String.valueOf(etudiant.getMoyenne()));
        averageTextField.setBounds(100, 140, 160, 25);
        container.add(averageTextField);

        updateButton = new JButton("Mettre à jour");
        updateButton.setBounds(10, 180, 150, 30);
        updateButton.addActionListener(this);
        updateButton.setBackground(new Color(0, 128, 0)); // Green color
        updateButton.setForeground(Color.WHITE); // White text color
        container.add(updateButton);

        cancelButton = new JButton("Annuler");
        cancelButton.setBounds(170, 180, 90, 30);
        cancelButton.addActionListener(this);
        container.add(cancelButton);

        container.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        this.add(container);
        this.setTitle("Mise à jour Étudiant");
        this.setSize(300, 250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        addWindowListener(new MyWindowAdapter(clientAppController));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String name = nameTextField.getText();
            String surname = surnameTextField.getText();
            String averageText = averageTextField.getText();

            if (name.isEmpty() || surname.isEmpty() || averageText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
            } else {
                try {
                    double average = Double.parseDouble(averageText);
                    etudiant.setNom(name);
                    etudiant.setPrenom(surname);
                    etudiant.setMoyenne(average);
                    
                    boolean updated = clientAppController.updateStudent(etudiant);

                    if (updated) {
                        JOptionPane.showMessageDialog(null, "Étudiant mis à jour avec succès.");
                        ereaseInputs();
                        // this.dispose(); // Ferme la fenêtre de mise à jour d'étudiant après la mise à jour réussie.
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de l'étudiant.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir une moyenne valide.");
                }
            }
        } else if (e.getSource() == cancelButton) {
        	ereaseInputs();
            this.dispose(); // Ferme la fenêtre de mise à jour d'étudiant sans effectuer de mise à jour.
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
