package com.pavan.project.tableLevel.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create {
	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		

		String loader = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jdbc01";
		String username = "root";
		String password = "root";

		try {
			Class.forName(loader);
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			String sql = "create table employee"
					+"(id int,"
					+ "name varchar(20),"
					+ "email varchar(20),"
					+ "dept varchar(20),"
					+ "salary int);";
			
			statement.executeUpdate(sql);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
