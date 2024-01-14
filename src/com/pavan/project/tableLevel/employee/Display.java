package com.pavan.project.tableLevel.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Display {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		
		String loader = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jdbc01";
		String username = "root";
		String password = "root";
		
		
		try {
			Class.forName(loader);
			connection = DriverManager.getConnection(url,username,password);
			showTable(connection,statement,result);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void showTable(Connection connection,Statement statement,ResultSet result) throws SQLException {

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
