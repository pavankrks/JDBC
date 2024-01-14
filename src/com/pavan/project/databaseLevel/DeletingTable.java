package com.pavan.project.databaseLevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeletingTable {
	
	
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
				statement = connection.createStatement();
				
				System.out.println("before dropping : ");
				ShowTables.showTables(connection,statement,res);
				
				System.out.println();
				System.out.println("enter the table name to delete: ");
				String tableName = scan.next();
				String sql = "drop table "+tableName;
				statement.executeUpdate(sql);
				
				System.out.println();
				System.out.println("After dropping : ");
				ShowTables.showTables(connection,statement,res);
				
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
}
