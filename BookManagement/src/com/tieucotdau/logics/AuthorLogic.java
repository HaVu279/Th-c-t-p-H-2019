package com.tieucotdau.logics;

import java.sql.SQLException;
import java.util.ArrayList;
import com.tieucotdau.entities.Author;

public interface AuthorLogic {

	public double calculateRevenueOfAuthor(long authorId) throws SQLException;

	public ArrayList<Author> findTopAuthorByRevenue() throws SQLException;

	public ArrayList<Author> findAllAuthor(String name) throws SQLException;

	public ArrayList<Author> findAuthor(String name, int start, int end) throws SQLException;

	public ArrayList<Author> findAuthorOfBook(long bookId) throws SQLException;

	public boolean addNewAuthor(Author author) throws SQLException;

	public boolean modifyAuthor(Author author) throws SQLException;

	public boolean deleteAuthorById(long id) throws SQLException;

	public Author findAuthorByName(String name) throws SQLException;

	public Author findAuthorById(long id) throws SQLException;

	public long generateId() throws SQLException;
}
