package com.tieucotdau.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private Connection conn;
	public static DBConnection db;

	private DBConnection() {
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "book_store?useUnicode=yes&characterEncoding=UTF-8";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		try {
			Class.forName(driver).newInstance();
			this.conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (conn != null && conn.isClosed()) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized DBConnection getDbCon() {
		if (db == null) {
			db = new DBConnection();
		}
		return db;

	}

	public Connection getConn() {
		return conn;
	}
}
