package com.tieucotdau.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;

public class Category {
	private long id;
	private String name;
	private long soldNumber;
	private double revenue;

	public Category(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Category() {
	}

	public Category(ResultSet resultSet) throws SQLException {
		this.setId(resultSet.getLong("id"));
		this.setName(resultSet.getString("name"));
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

	public long getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(long soldNumber) {
		this.soldNumber = soldNumber;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public static Comparator<Category> compare = new Comparator<Category>() {
		public int compare(Category c1, Category c2) {
			return (int) -(c1.getRevenue() - c2.getRevenue());
		}
	};
}
