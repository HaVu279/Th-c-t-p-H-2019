package com.tieucotdau.validates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tieucotdau.dbconnection.DBConnection;
import com.tieucotdau.entities.User;

public class ValidateUser {

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}

	public boolean validate(User user) {
		boolean status = false;
		try {
			PreparedStatement ps = getConnection()
					.prepareStatement("select * from bs_user where email=? and password=?");

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());

			ResultSet rs = ps.executeQuery();
			status = rs.next();

		} catch (Exception e) {
		}

		return status;
	}
	
	public List<String> validateLogin(User user) {
		List<String> array = new ArrayList<String>();
		if(user.getEmail().equals("")) {
			array.add("Email không được bỏ trống!");
		}
		
		if(user.getPassword().equals("")) {
			array.add("Password không được bỏ trống!");
		}
		
		if(!validate(user)) {
			array.add("Email or Password không đúng!");
		}
		return array;
	}
}
