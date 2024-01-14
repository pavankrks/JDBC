package com.pavan.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BLOB {
	
	static Connection connection;
	static Statement statement;
	static PreparedStatement pStat;
	static Scanner scan;
	
	
	static String loader = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/jdbc01";
	static String username = "root";
	static String password = "root";
	
	public static void main(String[] args) {
		
		
		System.out.println("this program is about blob :");
		
		try {
			String sql = "update employee set dp = ? where id = ?";
			
			Class.forName(loader);
			connection = DriverManager.getConnection(url,username,password);
			pStat = connection.prepareStatement(sql);
			scan = new Scanner(System.in);
			
			
			FileInputStream file = new FileInputStream("C:\\Users\\K PAVAN KUMAR\\OneDrive\\Desktop\\New World\\CodingUniverse\\JDBC\\src\\com\\pavan\\objects\\images\\catimg.jpg");
			System.out.println("Enter the id: ");
			int n = scan.nextInt();
			
			
			pStat.setBinaryStream(1, file);
			pStat.setInt(2, n);
			
			pStat.executeUpdate();
			System.out.println("Process Complete...");
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
