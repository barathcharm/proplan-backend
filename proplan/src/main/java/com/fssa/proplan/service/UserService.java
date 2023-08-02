package com.fssa.proplan.service;

import java.sql.SQLException;

import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.UserValidator;

public class UserService {
	private UserDao userDao;
	private UserValidator userValidator;

	public UserService(UserDao userDao, UserValidator userValidator) {
		this.userDao = userDao;
		this.userValidator = userValidator;
	}

	public boolean addUser(User user) throws SQLException {

		if (userValidator.isValidUser(user)) {

			if (userDao.isUserExist(user)) {
				throw new IllegalArgumentException("User already exists");
			}
			userDao.addUser(user); 
			System.out.println("User is added to DB successfully!");
			return true; 
		}
		return false;
	}
}
