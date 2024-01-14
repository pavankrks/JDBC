package com.pavan.operations.tableLevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteOperation {

public static void main(String[] args) {
		
		//prerequisities
		
		//for loading and setting up connection to database
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jdbc01";
		String username = "root";
		String password = "root";
		
		
		//initial values for the mysql classes
		Connection connection = null;
		Statement statement = null;
		
		String sql1 = "DELETE from `employee` where `id` = 20";
		String sql2 = "DELETE from `employee` where `id` = 21";
		String sql3 = "DELETE from `employee` where `id` = 22";
		String sql4 = "DELETE from `employee` where `id` = 23";
		String sql5 = "DELETE from `employee` where `id` = 24";
		
		
		try {
			
			//loading the driver
			Class.forName(driver);
			
			//connecting to the database
			connection = DriverManager.getConnection(url,username,password);
			
			//creating the statement
			statement = connection.createStatement();
			
			
			//adding the delete statements to batch
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			statement.addBatch(sql3);
			statement.addBatch(sql4);
			statement.addBatch(sql5);
			
			//displaying the content of table
			ReadOperation.display(connection, statement, null);
			
			System.out.println();
			
			//executing the batch and it returns the updated row array values
			int[] arr = statement.executeBatch();
			
			
			//printing the no of rows updated at each time of insertion
			for(int i = 0;i<arr.length;i++) {
				
				System.out.print(arr[i]+" ");
			}
			System.out.println();
			
			//displaying the content of table
			ReadOperation.display(connection, statement, null);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
