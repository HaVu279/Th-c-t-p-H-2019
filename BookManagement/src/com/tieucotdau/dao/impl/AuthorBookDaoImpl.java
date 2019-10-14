package com.tieucotdau.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tieucotdau.dao.AuthorBookDao;
import com.tieucotdau.dao.AuthorDao;
import com.tieucotdau.dbconnection.DBConnection;
import com.tieucotdau.entities.Author;
import com.tieucotdau.entities.Book;

public class AuthorBookDaoImpl implements AuthorBookDao {

	private Connection conn;
	private static AuthorDao authorDao = new AuthorDaoImpl();

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}

	@Override
	public boolean addNewBookAuthor(Author author, Book book, double revenueShareDouble) throws SQLException {

		String query = "insert into bs_author_book(id,book_id,author_id,revenue_share) values (" + generateId() + ",'"
				+ book.getId() + "','" + author.getId() + "'," + revenueShareDouble + ")";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteAuthorBookByBook(long book_id) throws SQLException {
		String query = "delete from bs_author_book where book_id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, book_id);
		int n = stmt.executeUpdate();
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	@Override
	public ArrayList<Author> findAuthors4EachBook(Book book) throws SQLException {
		String query = "select author_id from bs_author_book where book_id =" + book.getId();
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		if (resultSet.next()) {
			long authorId = resultSet.getLong("author_id");
			Author author = authorDao.findAuthorById(authorId);
			authorList.add(author);
		}
		return authorList;
	}

	@Override
	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_author_book ";
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
