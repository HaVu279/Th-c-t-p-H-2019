package com.tieucotdau.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tieucotdau.dao.BookDao;
import com.tieucotdau.dao.impl.BookDaoImpl;
import com.tieucotdau.entities.Book;
import com.tieucotdau.logics.BookLogic;

public class BookLogicImpl implements BookLogic {

	private BookDao bookDao = new BookDaoImpl();

	@Override
	public ArrayList<Book> findAllBook(String name) throws SQLException {

		return bookDao.findAllBook(name);
	}

	@Override
	public ArrayList<Book> findBook(String name, int start, int end) throws SQLException {

		return bookDao.findBook(name, start, end);
	}

	@Override
	public boolean addNewBook(Book book) throws SQLException {
		
		return bookDao.addNewBook(book);
	}

	@Override
	public boolean modifyBook(Book book) throws SQLException {
		
		return bookDao.modifyBook(book);
	}

	@Override
	public boolean deleteBookById(long id) throws SQLException {
		
		return bookDao.deleteBookById(id);
	}

	@Override
	public Book findBookByName(String name) throws SQLException {
		
		return bookDao.findBookByName(name);
	}

	@Override
	public Book findBookById(long id) throws SQLException {
		
		return bookDao.findBookById(id);
	}

	@Override
	public long generateId() throws SQLException {
		
		return bookDao.generateId();
	}

}
