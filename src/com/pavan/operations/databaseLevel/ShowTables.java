package com.pavan.operations.databaseLevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ShowTables {

	
public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Connection connection = null;
		Statement statement = null;
		ResultSet res = null;
		String loader = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jdbc01";
		String username = "root";
		String password = "root";
		
		
		
			try {
				Class.forName(loader);
				connection = DriverManager.getConnection(url,username,password);
				showTables(connection,statement,res);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			finally {
				
				try {
					if(scan!=null) {
						scan.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					if(connection!=null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					if(statement!=null) {
						statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
			}
	}

public static void showTables(Connection connection, Statement statement ,ResultSet res) throws SQLException {
	String sql = "show tables";
	statement = connection.createStatement();
	res = statement.executeQuery(sql);
	
	
	int count = 0;
	
	while(res.next()) {
		count++;
		
		System.out.println(res.getString("Tables_in_jdbc01"));
	}
	
	if(count==0) {
		System.out.println("Empty Database");
	}
}
}
