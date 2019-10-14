package com.tieucotdau.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tieucotdau.dao.AuthorDao;
import com.tieucotdau.dao.impl.AuthorDaoImpl;
import com.tieucotdau.entities.Author;
import com.tieucotdau.logics.AuthorLogic;

public class AuthorLogicImpl implements AuthorLogic {

	private AuthorDao authorDao = new AuthorDaoImpl();

	@Override
	public double calculateRevenueOfAuthor(long authorId) throws SQLException {
		
		return authorDao.calculateRevenueOfAuthor(authorId);
	}

	@Override
	public ArrayList<Author> findTopAuthorByRevenue() throws SQLException {
		
		return authorDao.findTopAuthorByRevenue();
	}

	@Override
	public ArrayList<Author> findAllAuthor(String name) throws SQLException {

		return authorDao.findAllAuthor(name);
	}

	@Override
	public ArrayList<Author> findAuthor(String name, int start, int end) throws SQLException {

		return authorDao.findAuthor(name, start, end);
	}

	@Override
	public ArrayList<Author> findAuthorOfBook(long bookId) throws SQLException {
		
		return authorDao.findAuthorOfBook(bookId);
	}

	@Override
	public boolean addNewAuthor(Author author) throws SQLException {
		
		return authorDao.addNewAuthor(author);
	}

	@Override
	public boolean modifyAuthor(Author author) throws SQLException {
		
		return authorDao.modifyAuthor(author);
	}

	@Override
	public boolean deleteAuthorById(long id) throws SQLException {
		
		return authorDao.deleteAuthorById(id);
	}

	@Override
	public Author findAuthorByName(String name) throws SQLException {
		
		return authorDao.findAuthorByName(name);
	}

	@Override
	public Author findAuthorById(long id) throws SQLException {
		
		return authorDao.findAuthorById(id);
	}

	@Override
	public long generateId() throws SQLException {
		
		return authorDao.generateId();
	}
}
