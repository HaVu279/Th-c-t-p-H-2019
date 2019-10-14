package com.tieucotdau.dao;

import java.sql.SQLException;

import com.tieucotdau.entities.User;

public interface UserDao {
	
	public User findUser(String email, String password) throws SQLException;

	public long generateId() throws SQLException;

	public boolean addNewUser(User user) throws SQLException;
}
