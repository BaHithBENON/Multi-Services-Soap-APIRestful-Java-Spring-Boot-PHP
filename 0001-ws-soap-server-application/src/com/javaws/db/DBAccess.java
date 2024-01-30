package com.javaws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBAccess {
    private static final String URL = "jdbc:mysql://localhost:3306/etudiants_ws_db";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    private static DBAccess instance;
    private Connection connexion;

    private DBAccess() {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            this.connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);

            if (this.connexion != null) {
                System.out.println("Connexion à la base de données établie avec succès.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static synchronized DBAccess getInstance() {
        if (instance == null) {
            instance = new DBAccess();
        }
        return instance;
    }

    public Connection getConnexion() {
        return this.connexion;
    }

    
}

