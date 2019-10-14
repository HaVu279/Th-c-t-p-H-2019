package com.tieucotdau.logics;

import java.sql.SQLException;

import com.tieucotdau.entities.User;

public interface UserLogic {
	
	public User findUser(String email, String password) throws SQLException;

	public long generateId()  throws SQLException;

	public boolean addNewUser(User user)  throws SQLException;
}
