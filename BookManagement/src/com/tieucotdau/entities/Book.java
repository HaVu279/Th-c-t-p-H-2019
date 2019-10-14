package com.tieucotdau.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tieucotdau.dao.AuthorDao;
import com.tieucotdau.dao.CategoryDao;
import com.tieucotdau.dao.impl.AuthorDaoImpl;
import com.tieucotdau.dao.impl.CategoryDaoImpl;

public class Book {
	private long id;
	private String name;
	private double price;
	private long soldNumber;
	private ArrayList<Author> author;
	private Category category;
	private static AuthorDao authorDAO = new AuthorDaoImpl();
	private static CategoryDao categoryDAO = new CategoryDaoImpl();

	public Book() {

	}

	public Book(ResultSet resultSet) throws SQLException {
		this.setId(resultSet.getLong("id"));
		this.setName(resultSet.getString("name"));
		this.setPrice(resultSet.getDouble("price"));
		this.setSoldNumber(resultSet.getLong("sold_number"));
		this.setCategory(categoryDAO.findCategoryOfBook(resultSet.getLong("category_id")));
		ArrayList<Author> authorList = authorDAO.findAuthorOfBook(resultSet.getLong("id"));
		this.setAuthor(authorList);
	}

	public Book(long id, String name, double priceDouble, long soldNumberDouble, Category selectedCategory) {
		super();
		this.id = id;
		this.name = name;
		this.price = priceDouble;
		this.soldNumber = soldNumberDouble;
		this.category = selectedCategory;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(long soldNumber) {
		this.soldNumber = soldNumber;
	}

	public ArrayList<Author> getAuthor() {
		return author;
	}

	public void setAuthor(ArrayList<Author> author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
