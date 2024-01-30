package com.javaws.interfaces;

import com.javaws.entities.User;
import com.javaws.exceptions.DAOException;

public interface IUserRepository extends IDao<User> {
	/**
	 * @param login
	 * @param password
	 * @return
	 * @throws RepositoryException
	 */
	User login(String login, String password) throws DAOException;
}
