package com.tieucotdau.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tieucotdau.dao.BookDao;
import com.tieucotdau.dbconnection.DBConnection;
import com.tieucotdau.entities.Book;

public class BookDaoImpl implements BookDao {

	private Connection conn;

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}

	@Override
	public ArrayList<Book> findAllBook(String name) throws SQLException {
		String query = "select * from bs_book  ";
		if (name != null) {
			query += " where name like '%" + name + "%'";
		}
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Book> bookList = new ArrayList<Book>();

		while (resultSet.next()) {
			Book book = new Book(resultSet);

			bookList.add(book);
		}

		return bookList;
	}

	@Override
	public ArrayList<Book> findBook(String name, int start, int end) throws SQLException {
		String query = "SELECT * FROM bs_book ";
		if (name != null) {
			query += " where name like '%" + name + "%'";
		}
		query += " limit " + start + "," + end;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Book> bookList = new ArrayList<Book>();

		while (resultSet.next()) {
			Book book = new Book(resultSet);
			bookList.add(book);
		}

		return bookList;
	}

	@Override
	public boolean addNewBook(Book book) throws SQLException {
		String query = "insert into bs_book(id,name,price,category_id,sold_number) values (" + book.getId() + ",'"
				+ book.getName() + "'," + book.getPrice() + "," + book.getCategory().getId() + ","
				+ book.getSoldNumber() + ")";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}

	@Override
	public boolean modifyBook(Book book) throws SQLException {
		String query = "update bs_book set name='" + book.getName() + "' , price='" + book.getPrice()
			+ "' , sold_number='" + book.getSoldNumber() + "' , category_id='" + book.getCategory().getId() + "' where id=" + book.getId();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteBookById(long id) throws SQLException {
		String query = "delete from bs_book where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		int n = stmt.executeUpdate();
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	@Override
	public Book findBookByName(String name) throws SQLException {
		String query = "select * from bs_book where name='" + name + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Book book = new Book(resultSet);
			return book;
		} else
			return null;
	}
	
	@Override
	public Book findBookById(long id) throws SQLException {
		String query = "select * from bs_book where id=" + id;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Book book = new Book(resultSet);
			return book;
		} else
			return null;
	}

	@Override
	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_book";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;

		}
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
