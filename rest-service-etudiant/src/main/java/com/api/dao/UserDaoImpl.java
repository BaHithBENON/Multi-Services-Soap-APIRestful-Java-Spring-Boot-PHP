package com.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.api.entities.User;
import com.api.jdbc.DBManager;

public class UserDaoImpl implements IDaoUser{

	@Override
	public void create(User user) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Insert Into users (login,password) values (?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, user.getLogin());
		preparedStatement.setString(2, user.getPassword());

		preparedStatement.execute();
		
		connection.close();

	}

	@Override
	public User read(int id) throws Exception {
		User user = null;
		
		Connection connection = DBManager.getConnection() ;

	    String query = "Select * From users Where id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, id);
	    
	    ResultSet resultSet = preparedStatement.executeQuery();
	    	    	    
	    if (resultSet.next()) {
	    	user = new User (id, 
	        		resultSet.getString("login"), 
	        		resultSet.getString("password"));
	    }

	    connection.close();
	    
		return user;
	}

	@Override
	public void update(User user) throws Exception {
		Connection connection = DBManager.getConnection() ;

	    String query = "Update users Set login=?, password=? Where id=?";

	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	        
	    preparedStatement.setString(1, user.getLogin());
		preparedStatement.setString(2, user.getPassword());
	    preparedStatement.setInt(3, user.getId());
	       
	    preparedStatement.execute();
	    
	    connection.close();
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = DBManager.getConnection() ;

		String query = "Delete From users Where id=?";
	       
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		
		connection.close();
	}

	@Override
	public List<User> list() throws Exception {
		Connection connection = DBManager.getConnection() ;

		List<User> users = new ArrayList<>();
		String query = "Select * From users";

		PreparedStatement prepareStatement = connection.prepareStatement(query);
		ResultSet resultSet = prepareStatement.executeQuery();

		while (resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String login = resultSet.getString("login");
			String password = resultSet.getString("password");
			
			User user = new User (id, login, password);
			
			users.add(user);
		}
		
		connection.close();
		
		return users;
	}
	
	@Override
	public User login(String login, String password) throws Exception {
		// TODO Auto-generated method stub
		Connection connection = DBManager.getConnection() ;
		try {
			String query = "Select * From users where login=? and password=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, login);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int identifiant = rs.getInt("id");			

				return new User (identifiant, login, password);				
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return null;
	}
}
