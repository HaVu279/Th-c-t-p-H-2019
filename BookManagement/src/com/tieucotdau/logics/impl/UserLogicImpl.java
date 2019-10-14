package com.tieucotdau.logics.impl;

import java.sql.SQLException;

import com.tieucotdau.dao.UserDao;
import com.tieucotdau.dao.impl.UserDaoImpl;
import com.tieucotdau.entities.User;
import com.tieucotdau.logics.UserLogic;

public class UserLogicImpl implements UserLogic {

	private UserDao userDao = new UserDaoImpl();

	@Override
	public User findUser(String email, String password) throws SQLException {
		return userDao.findUser(email, password);
	}

	@Override
	public long generateId() throws SQLException {
		return userDao.generateId();
	}

	@Override
	public boolean addNewUser(User user) throws SQLException {
		return userDao.addNewUser(user);
	}

}
