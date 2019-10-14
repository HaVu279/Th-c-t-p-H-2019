package com.tieucotdau.logics;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tieucotdau.entities.Book;

public interface BookLogic {

	public ArrayList<Book> findAllBook(String name) throws SQLException;

	public ArrayList<Book> findBook(String name, int start, int end) throws SQLException;

	public boolean addNewBook(Book book) throws SQLException;

	public boolean modifyBook(Book book) throws SQLException;

	public boolean deleteBookById(long id) throws SQLException;

	public Book findBookByName(String name) throws SQLException;

	public Book findBookById(long id) throws SQLException;

	public long generateId() throws SQLException;
}
