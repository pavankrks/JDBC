package com.pavan.operations.tableLevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateOperation {
	
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
		
		String sql1 = "UPDATE `employee` set `salary` = `salary`+5000 where `dept` = 'Sales'";
		
		
		try {
			
			//loading the driver
			Class.forName(driver);
			
			//connecting to the database
			connection = DriverManager.getConnection(url,username,password);
			
			//creating the statement
			statement = connection.createStatement();
			
			
			//adding the update statements to batch
			statement.addBatch(sql1);
			
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
