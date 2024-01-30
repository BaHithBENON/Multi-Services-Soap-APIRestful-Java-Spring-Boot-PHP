package com.app.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.app.controllers.ClientAppController;
import com.javaws.services.Etudiant;

public class StudentsListUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Etudiant> etudiants;
    private ClientAppController controller;

    public StudentsListUI(ClientAppController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        // Initialisation du tableau
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nom", "Prénom", "Moyenne"}, 0);
        table = new JTable(tableModel);

        // Ajout d'un écouteur de clic sur les lignes du tableau
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < table.getRowCount()) {
                        // Obtenez l'étudiant sélectionné dans la ligne du tableau
                        Etudiant selectedStudent = etudiants.get(row);
                        
                        // Ouvrez une autre fenêtre avec les détails de l'étudiant
                        ouvrirFenetreDetails(selectedStudent);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Ajout d'une marge d'espacement autour du tableau
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(panel);

        // Ajout des boutons
        JButton createButton = new JButton("Create");
        JButton reloadButton = new JButton("Reload");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        // Ajout d'une marge entre les boutons et le tableau
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(createButton);
        buttonPanel.add(reloadButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Ajout des actions aux boutons
        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	goToCreateStudent();
            }
        });
        
        reloadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton Reload
                // (Exemple : Rafraîchir la liste en appelant la méthode appropriée du contrôleur)
            	List<Etudiant> studs = controller.getAllStudents();
            	afficherListeEtudiants(studs);
                // controller.reloadStudentsList();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton Update
                // (Exemple : Ouvrir la fenêtre UpdateUI avec les informations de l'étudiant sélectionné)
                // Vous pouvez appeler la méthode appropriée du contrôleur ici
                Etudiant selectedStudent = getSelectedStudent();
                if (selectedStudent != null) {
                    // controller.openUpdateUI(selectedStudent);
                	goToUpdateStudent(selectedStudent);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action à effectuer lors du clic sur le bouton Delete
                // (Exemple : Appeler la méthode deleteStudent du contrôleur avec l'ID de l'étudiant sélectionné)
                // Vous pouvez appeler la méthode appropriée du contrôleur ici
                Etudiant selectedStudent = getSelectedStudent();
                if (selectedStudent != null) {
                    boolean result = controller.deleteStudentById(selectedStudent.getId());
                    if(result) {
                    	JOptionPane.showMessageDialog(null, "Étudiant supprimé avec succès.");
                    	List<Etudiant> studs = controller.getAllStudents();
                    	afficherListeEtudiants(studs);
                    } else  {
                    	JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de l'étudiant.");
                    }
                }
            }
        });

        // Ajout du panneau de boutons en haut de la fenêtre
        add(buttonPanel, BorderLayout.NORTH);

        setTitle("Liste des étudiants");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void afficherListeEtudiants(List<Etudiant> etudiants) {
    	if(this.etudiants != null) {
    		this.etudiants.clear();
    	}
        this.etudiants = etudiants;
        tableModel.setRowCount(0);  // Efface toutes les lignes actuelles du tableau

        for (Etudiant etudiant : etudiants) {
            Object[] rowData = {etudiant.getId(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getMoyenne()};
            tableModel.addRow(rowData);
        }
    }
    
    private void goToCreateStudent() {
    	this.hide();
    	controller.showCreateEtudiantUI();
    }
    
    private void goToUpdateStudent(Etudiant student) {
    	this.hide();
    	controller.showUpdateStudentUI(student);
    }

    private void ouvrirFenetreDetails(Etudiant etudiant) {
        // Créez une nouvelle instance de la fenêtre détails avec l'étudiant sélectionné
        // StudentsListUI detailsEtudiant = new StudentsListUI(etudiant);
        // detailsEtudiant.setVisible(true);
    }

    private Etudiant getSelectedStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0 && selectedRow < etudiants.size()) {
            return etudiants.get(selectedRow);
        }
        return null;
    }

    public void closeUI() {
        this.dispose();
    }
}
