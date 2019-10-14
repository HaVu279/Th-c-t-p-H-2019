package com.tieucotdau.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Author {
	private long id;
	private String name;
	private Date dob;
	private long soldNumber;
	private double revenue;

	public Author(long id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}

	public Author(ResultSet resultSet) throws SQLException {
		this.setId(resultSet.getLong("id"));
		this.setName(resultSet.getString("name"));
		this.setDob(resultSet.getDate("dob"));
	}

	public Author() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDobString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(dob);
	}

	public void setDobString(String dob) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			this.dob = sdf.parse(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public int calculateAge() {
		Date now = new Date();
		// 01-01-1970
		long nowInMilisecond = now.getTime();
		long dobMiliSecond = dob.getTime();
		long dif = nowInMilisecond - dobMiliSecond;
		long age = dif / 1000l / (60 * 60 * 24 * 365);
		return (int) age;

	}

	public static Comparator<Author> compare = new Comparator<Author>() {
		public int compare(Author a1, Author a2) {
			return (int) -(a1.getRevenue() - a2.getRevenue());
		}
	};
}