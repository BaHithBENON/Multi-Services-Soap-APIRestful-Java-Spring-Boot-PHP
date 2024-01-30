package com.api.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	private static final String DEFAULT_DATABASE = "etudiants_ws_db";
	
	public static Connection getConnection (String database) throws Exception {
		try {
			Class.forName ("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection ("jdbc:mysql://localhost:3306/" + database + "?serverTimezone=UTC", "root", "");

		} catch (ClassNotFoundException e) {
			throw new Exception ("Driver Class not found : '" + e.getMessage () + "' ");
		} catch (SQLException e) {
			throw new Exception ("Error : Unable to open connection with database : " + e.getMessage());
		}
	}
	
	public static Connection getConnection () throws Exception {
		return getConnection(DEFAULT_DATABASE);
	}
}