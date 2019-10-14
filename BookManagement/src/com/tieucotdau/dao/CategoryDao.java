package com.tieucotdau.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tieucotdau.entities.Category;

public interface CategoryDao {
	public double calculateRevenueOfCategory(long categoryid) throws SQLException;

	public Category findCategoryOfBook(long categoryid) throws SQLException;
	
	public Category findCategoryById(long id) throws SQLException;
	
	public boolean addNewCategory(Category category) throws SQLException;

	public boolean modifyCategory(Category category) throws SQLException;

	public boolean deleteCategoryById(long id) throws SQLException;

	public long generateId() throws SQLException;

	public ArrayList<Category> findTopCategoryByRevenue() throws SQLException;

	public ArrayList<Category> findAllCategory(String name) throws SQLException;

	public ArrayList<Category> findCategory(String name, int start, int end) throws SQLException;
}
