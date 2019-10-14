package com.tieucotdau.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.tieucotdau.dao.UserDao;
import com.tieucotdau.dbconnection.DBConnection;
import com.tieucotdau.entities.User;

public class UserDaoImpl implements UserDao {

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}
	
	@Override
	public User findUser(String email, String password) throws SQLException {
		String query = "select * from bs_user where email='" + email + "' and password='" + password + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getLong("id"));
			user.setName(resultSet.getString("name"));
			user.setDob(resultSet.getDate("dob"));
			user.setPassword(resultSet.getString("password"));
			return user;
		} else
			return null;

	}
	
	@Override
	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_user";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;

		}
	}
	
	@Override
	public boolean addNewUser(User user) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String query = "insert into bs_user(id, email, password, name, dob) values (" + user.getId() + ",'"
				+ user.getEmail() + "','" + user.getPassword() + "','" + user.getName() + "','"
				+ sdf.format(user.getDob()) + "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;

	}

}
