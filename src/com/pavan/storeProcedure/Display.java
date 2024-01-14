package com.pavan.storeProcedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Display {
	
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet result = null;
	
	
	static String loader = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/jdbc01";
	static String username = "root";
	static String password = "root";

	public static void main(String[] args) {

		try {
			Class.forName(loader);
			
			showTable();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void showTable() throws SQLException {

		connection = DriverManager.getConnection(url,username,password);
		statement = connection.createStatement();
		
		String sql = "SELECT * from employee";
		
		result = statement.executeQuery(sql);
		
		System.out.println("=======================================================================");
		while(result.next()) {
			
			int id = result.getInt("id");
			String name = result.getString("name");
			String email = result.getString("email");
			String dept = result.getString("dept");
			int salary = result.getInt("salary");
			System.out.printf("%-4d | %-12s | %-25s | %-10s | %-6d\n",id,name,email,dept,salary);
			
		}
		System.out.println("=======================================================================");
	}
}
