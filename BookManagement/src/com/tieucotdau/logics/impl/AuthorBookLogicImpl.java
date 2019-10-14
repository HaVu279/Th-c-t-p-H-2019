package com.tieucotdau.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tieucotdau.dao.AuthorBookDao;
import com.tieucotdau.dao.impl.AuthorBookDaoImpl;
import com.tieucotdau.entities.Author;
import com.tieucotdau.entities.Book;
import com.tieucotdau.logics.AuthorBookLogic;

public class AuthorBookLogicImpl implements AuthorBookLogic {

	private AuthorBookDao authorBookDao = new AuthorBookDaoImpl();

	@Override
	public boolean addNewBookAuthor(Author author, Book book, double revenueShareDouble) throws SQLException {
		return authorBookDao.addNewBookAuthor(author, book, revenueShareDouble);
	}

	@Override
	public boolean deleteAuthorBookByBook(long book_id) throws SQLException {
		return authorBookDao.deleteAuthorBookByBook(book_id);

	}

	@Override
	public ArrayList<Author> findAuthors4EachBook(Book book) throws SQLException {
		return authorBookDao.findAuthors4EachBook(book);

	}

	@Override
	public long generateId() throws SQLException {
		return authorBookDao.generateId();

	}

}
