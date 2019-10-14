package com.tieucotdau.logics.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.tieucotdau.dao.CategoryDao;
import com.tieucotdau.dao.impl.CategoryDaoImpl;
import com.tieucotdau.entities.Category;
import com.tieucotdau.logics.CategoryLogic;

public class CategoryLogicImpl implements CategoryLogic {

	private CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public double calculateRevenueOfCategory(long categoryid) throws SQLException {

		return categoryDao.calculateRevenueOfCategory(categoryid);
	}

	@Override
	public Category findCategoryOfBook(long categoryid) throws SQLException {

		return categoryDao.findCategoryOfBook(categoryid);
	}

	@Override
	public Category findCategoryById(long id) throws SQLException {

		return categoryDao.findCategoryById(id);
	}

	@Override
	public boolean addNewCategory(Category category) throws SQLException {

		return categoryDao.addNewCategory(category);
	}

	@Override
	public boolean modifyCategory(Category category) throws SQLException {

		return categoryDao.modifyCategory(category);
	}

	@Override
	public boolean deleteCategoryById(long id) throws SQLException {

		return categoryDao.deleteCategoryById(id);
	}

	@Override
	public long generateId() throws SQLException {

		return categoryDao.generateId();
	}

	@Override
	public ArrayList<Category> findTopCategoryByRevenue() throws SQLException {

		return categoryDao.findTopCategoryByRevenue();
	}

	@Override
	public ArrayList<Category> findAllCategory(String name) throws SQLException {

		return categoryDao.findAllCategory(name);
	}

	@Override
	public ArrayList<Category> findCategory(String name, int start, int end) throws SQLException {

		return categoryDao.findCategory(name, start, end);
	}
}
