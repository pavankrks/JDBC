package com.pavan.operations.tableLevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadOperation {

	public static void main(String[] args) {		
	
		
		Connection connection = null;
		Statement statement = null;
		ResultSet res = null;

		String url = "jdbc:mysql://localhost:3306/jdbc01";
		String username = "root";
		String password = "root";

		try {
			connection = DriverManager.getConnection(url, username, password);
			display(connection,statement,res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(res!=null) {
				try {
					res.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void display(Connection connection, Statement statement, ResultSet res) {

		try {
			String sql = "select * from employee";
			statement = connection.createStatement();
			res = statement.executeQuery(sql);
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String dept = res.getString("dept");
				int salary = res.getInt("salary");
				System.out.println(id +" "+name+" "+dept+" "+salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
