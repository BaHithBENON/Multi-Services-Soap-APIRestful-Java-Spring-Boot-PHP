package com.javaws.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.javaws.db.DBAccess;
import com.javaws.entities.User;
import com.javaws.exceptions.DAOException;
import com.javaws.interfaces.IUserRepository;

public class UserDaoImpl implements IUserRepository{
	
	private DBAccess dbAccess; // Ajoutez une référence à DBAccess
	
	public UserDaoImpl() {
        // Initialisez DBAccess lorsque le service est créé
        this.dbAccess = DBAccess.getInstance();
	}

	@Override
	public void create(User entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User read(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> list() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User entity) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User login(String login, String password) throws DAOException {
		// TODO Auto-generated method stub
		try {
			String query = "Select * From users where login=? and password=?";
			PreparedStatement ps = dbAccess.getConnexion().prepareStatement(query);			
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int identifiant = rs.getInt("id");			

				return new User (identifiant, login, password);				
			}

		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
		
		return null;
	}

}
