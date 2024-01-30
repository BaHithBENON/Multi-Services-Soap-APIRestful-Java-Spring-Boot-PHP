package com.javaws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javaws.db.DBAccess;
import com.javaws.entities.Etudiant;
import com.javaws.exceptions.DAOException;
import com.javaws.interfaces.IEtudiantRepository;

public class EtudiantDaoImpl implements IEtudiantRepository {
	
	private DBAccess dbAccess; // Ajoutez une référence à DBAccess
	
	public EtudiantDaoImpl() {
        // Initialisez DBAccess lorsque le service est créé
        this.dbAccess = DBAccess.getInstance();
	}
	
	@Override
	public void create(Etudiant entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
			String query = "Insert into etudiants (nom, prenom, moyenne) values (?,?,?)"; 
			PreparedStatement ps = dbAccess.getConnexion().prepareStatement(query);

			ps.setString(1, entity.getNom());
			ps.setString(2, entity.getPrenom());
			ps.setDouble(3, entity.getMoyenne());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new DAOException(e.getClass().getSimpleName() + ":" + 	e.getMessage());
		}
	}

	@Override
	public Etudiant read(int id) throws DAOException {
		// TODO Auto-generated method stub
		try {
			String query = "Select * From etudiants where id=?";
			PreparedStatement ps = dbAccess.getConnexion().prepareStatement(query);			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int identifiant = rs.getInt("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				double moyenne = rs.getDouble("moyenne");

				return new Etudiant (identifiant, nom, prenom, moyenne);				
			}

		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Etudiant> list() throws DAOException {
		// TODO Auto-generated method stub
		List<Etudiant> etudiants = new ArrayList<>();
        try (
        		//Connection connection = dbAccess.getConnexion();
            PreparedStatement preparedStatement = dbAccess.getConnexion().prepareStatement("SELECT * FROM etudiants");
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                double moyenne = resultSet.getDouble("moyenne");

                Etudiant etudiant = new Etudiant(id, nom, prenom, moyenne);
                etudiants.add(etudiant);
            }
            
        } catch (Exception e) {
            throw new DAOException("Erreur lors de la récupération de la liste des étudiants : " 
            			+ e.getMessage());
        }

        return etudiants;
	}

	@Override
	public void update(Etudiant entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
			String query = "Update etudiants Set nom=?, prenom=?, moyenne=? Where id=?";

			PreparedStatement ps = dbAccess.getConnexion().prepareStatement(query);
			ps.setString(1, entity.getNom());
			ps.setString(2, entity.getPrenom());
			ps.setDouble(3, entity.getMoyenne());
			ps.setInt(4, entity.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		try {
			String query = "Delete From etudiants Where id=?";

			PreparedStatement ps = dbAccess.getConnexion().prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Etudiant readByNomPrenom(String nom, String prenom) throws DAOException {
		// TODO Auto-generated method stub
		try {
			String query = "Select * From etudiants where nom=? and prenom=?";
			PreparedStatement ps = dbAccess.getConnexion().prepareStatement(query);			
			ps.setString(1, nom);
			ps.setString(2, prenom);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int identifiant = rs.getInt("id");				
				double moyenne = rs.getDouble("moyenne");

				return new Etudiant (identifiant, nom, prenom, moyenne);				
			}

		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		
		return null;
	}

}
