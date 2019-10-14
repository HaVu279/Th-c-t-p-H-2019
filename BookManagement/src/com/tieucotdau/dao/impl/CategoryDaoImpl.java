package com.tieucotdau.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tieucotdau.dao.CategoryDao;
import com.tieucotdau.dbconnection.DBConnection;
import com.tieucotdau.entities.Category;

public class CategoryDaoImpl implements CategoryDao {

	private Connection conn;

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}

	@Override
	public boolean addNewCategory(Category category) throws SQLException {
		String query = "insert into bs_category(id,name) values (" + category.getId() + ",'" + category.getName()
				+ "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);

		if (n != 0)
			return true;
		return false;
	}

	@Override
	public boolean modifyCategory(Category category) throws SQLException {
		String query = "update bs_category set name='" + category.getName() + "' where id=" + category.getId();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);

		if (n != 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteCategoryById(long id) throws SQLException {
		String query = "delete from bs_category where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		int n = stmt.executeUpdate();

		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	@Override
	public long generateId() throws SQLException {
		String query = "select max(id) as maxid from bs_category ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			return resultSet.getLong("maxid") + 1;
		} else {
			return 0;
		}
	}

	@Override
	public ArrayList<Category> findAllCategory(String name) throws SQLException {
		String query = "SELECT * FROM bs_category ";
		if (name != null) {
			query += " where name like '%" + name + "%'";
		}
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Category> categoryList = new ArrayList<Category>();

		while (resultSet.next()) {
			Category category = new Category(resultSet);
			category.setRevenue(calculateRevenueOfCategory(category.getId()));
			categoryList.add(category);
		}
		return categoryList;
	}

	@Override
	public ArrayList<Category> findCategory(String name, int start, int end) throws SQLException {
		String query = "SELECT * FROM bs_category ";
		if (name != null) {
			query += " where name like '%" + name + "%'";
		}
		query += " limit " + start + "," + end;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Category> categoryList = new ArrayList<Category>();

		while (resultSet.next()) {
			Category category = new Category(resultSet);
			categoryList.add(category);
		}

		return categoryList;
	}

	@Override
	public Category findCategoryById(long id) throws SQLException {
		String query = "select * from bs_category where id = " + id;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Category category = new Category(resultSet);
			return category;
		} else
			return null;
	}
	
	@Override
	public double calculateRevenueOfCategory(long categoryid) throws SQLException {
		long revenue = 0l;
		String query = "select bc.id, bc.name "
				+ ",ifnull(sum(bb.sold_number*bb.price),0) as revenue from bs_category bc"
				+ " left join bs_book bb on bb.category_id=bc.id where bc.id=" + categoryid + " group by bc.id "
				+ " order by revenue desc";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		while (resultSet.next()) {
			return resultSet.getDouble("revenue");
		}
		return revenue;
	}
	
	@Override
	public ArrayList<Category> findTopCategoryByRevenue() throws SQLException {
		String query = "select bc.id, bc.name "
				+ ",ifnull(sum(bb.sold_number*bb.price),0) as revenue from bs_category bc"
				+ " left join bs_book bb on bb.category_id=bc.id" + " group by bc.id " + " order by Revenue desc";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Category> list = new ArrayList<Category>();
		while (resultSet.next()) {
			Category category = new Category();
			category.setId(resultSet.getLong("bc.id"));
			category.setName(resultSet.getString("bc.name"));
			category.setRevenue(resultSet.getDouble("Revenue"));
			list.add(category);
		}
		return list;
	}

	@Override
	public Category findCategoryOfBook(long categoryid) throws SQLException {
		String query = "SELECT bs_category.id, bs_category.name FROM bs_book, bs_category WHERE  " + "bs_category.id = "
				+ categoryid;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		Category category = new Category();
		if (resultSet.next()) {
			category.setId(resultSet.getLong("bs_category.id"));
			category.setName(resultSet.getString("bs_category.name"));
		}
		return category;
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
