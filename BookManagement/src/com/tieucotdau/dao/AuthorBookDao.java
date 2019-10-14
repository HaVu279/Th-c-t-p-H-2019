package com.tieucotdau.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tieucotdau.entities.Author;
import com.tieucotdau.entities.Book;

public interface AuthorBookDao {
	
	public boolean addNewBookAuthor(Author author, Book book, double revenueShareDouble) throws SQLException;

	public boolean deleteAuthorBookByBook(long book_id) throws SQLException;

	public ArrayList<Author> findAuthors4EachBook(Book book) throws SQLException;

	public long generateId() throws SQLException;
}
