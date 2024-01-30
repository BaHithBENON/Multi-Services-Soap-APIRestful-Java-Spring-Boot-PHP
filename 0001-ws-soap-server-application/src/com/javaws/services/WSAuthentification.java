package com.javaws.services;

import com.javaws.dao.UserDaoImpl;
import com.javaws.entities.User;
import com.javaws.exceptions.DAOException;
import com.javaws.interfaces.IUserRepository;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class WSAuthentification {
	
	private IUserRepository userDao;
	public WSAuthentification() {
        this.userDao = new UserDaoImpl();
    }
	
	@WebMethod
    public boolean login(
            @WebParam(name = "login") String login,
            @WebParam(name = "password") String password) {
		
		User user;
		try {
			user = userDao.login(login, password);
			if(user != null) {
				return true;
			} else {
				return false;
			}
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
	}
}
