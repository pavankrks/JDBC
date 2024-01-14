package com.pavan.objects;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CLOB {

	static Connection connection;
	static Statement statement;
	static PreparedStatement pStat;
	static Scanner scan;
	
	
	static String loader = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/jdbc01";
	static String username = "root";
	static String password = "root";
	
	public static void main(String[] args) {
		
		
		System.out.println("this program is about clob :");
		
		try {
			String sql = "update employee set message = ? where id = ?";
			
			Class.forName(loader);
			connection = DriverManager.getConnection(url,username,password);
			pStat = connection.prepareStatement(sql);
			scan = new Scanner(System.in);
			
			
			FileReader file = new FileReader("C:\\Users\\K PAVAN KUMAR\\OneDrive\\Desktop\\New World\\CodingUniverse\\JDBC\\src\\com\\pavan\\objects\\TextFiles\\kumar.txt");
			System.out.println("Enter the id: ");
			int n = scan.nextInt();
			
			
			pStat.setCharacterStream(1, file);
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
