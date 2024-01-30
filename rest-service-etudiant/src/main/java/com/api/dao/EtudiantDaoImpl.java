package com.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.api.entities.Etudiant;
import com.api.jdbc.DBManager;

public class EtudiantDaoImpl implements IDao<Etudiant> {

	@Override
	public void create(Etudiant etudiant) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Insert Into etudiants (nom,prenom,moyenne) values (?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, etudiant.getNom());
		preparedStatement.setString(2, etudiant.getPrenom());
		preparedStatement.setDouble(3, etudiant.getMoyenne());

		preparedStatement.execute();
		
		connection.close();

	}

	@Override
	public Etudiant read(int id) throws Exception {
		Etudiant etudiant = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From etudiants Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	etudiant = new Etudiant (id, 
	        		resultSet.getString("nom"), 
	        		resultSet.getString("prenom"), 
	        		resultSet.getDouble("moyenne"));
	    }

	    connection.close();
	    
		return etudiant;
	}

	@Override
	public void update(Etudiant etudiant) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update etudiants Set nom=?, prenom=?, moyenne=? Where id=?";

	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	        
	    preparedStatement.setString(1, etudiant.getNom());
		preparedStatement.setString(2, etudiant.getPrenom());
		preparedStatement.setDouble(3, etudiant.getMoyenne());
	    preparedStatement.setInt(4, etudiant.getId());
	       
	    preparedStatement.execute();
	    
	    connection.close();
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Delete From etudiants Where id=?";
	       
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		
		connection.close();
	}

	@Override
	public List<Etudiant> list() throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<Etudiant> etudiants = new ArrayList<>();
		String query = "Select * From etudiants";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String login = resultSet.getString("nom");
			String password = resultSet.getString("prenom");
			double moyenne = resultSet.getDouble("moyenne");
			
			Etudiant etudiant = new Etudiant (id, login, password, moyenne);
			
			etudiants.add(etudiant);
		}
		
		connection.close();
		
		return etudiants;
	}
}
