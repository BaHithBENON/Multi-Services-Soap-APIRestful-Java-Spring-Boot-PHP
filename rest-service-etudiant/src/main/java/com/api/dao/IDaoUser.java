package com.api.dao;

import com.api.entities.User;

public interface IDaoUser extends IDao<User> {
	/**
	 * @param login
	 * @param password
	 * @return
	 * @throws RepositoryException
	 */
	User login(String login, String password) throws Exception;
}
