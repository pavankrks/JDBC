package com.pavan.properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MoneyTransfer {

	static Connection connection = null;
	static Statement statement = null;
	static ResultSet result = null;
	static PreparedStatement pStat = null;
	static Scanner scan = null;

	static String loader = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/jdbc01";
	static String username = "root";
	static String password = "root";

	public static void main(String[] args) {

		try {
			Class.forName(loader);
			connection = DriverManager.getConnection(url, username, password);
			scan = new Scanner(System.in);

			System.out.println("This program is focussed on ACID Properties");
			
			
			Display.showTable();
			
			transaction();

			Display.showTable();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	static void transaction() throws SQLException {

		System.out.println("Enter the sender id: ");
		int senderID = scan.nextInt();
		System.out.println("Enter the receiver id: ");
		int receiverID = scan.nextInt();
		System.out.println("Enter the Amount: ");
		int amount = scan.nextInt();
		
		
		
		statement = connection.createStatement();
		String sql = "select salary from employee where id = "+senderID;
		
		result = statement.executeQuery(sql);
		
		if(result.next()) {
			
			if(result.getInt("salary")>=amount) {
				
				int i = updateAmount(senderID,-amount);
				int j =updateAmount(receiverID,amount);
				
				
				connection.setAutoCommit(false);
				
				if(isConfirm(i,j)) {
					
					connection.commit();
					System.out.println("Transation successful");
				}
				else {
					connection.rollback();
					System.out.println("Transaction failed");
				}
			}
			else {
				System.out.println("No sufficient amount...");
				System.out.println("Transaction failed");
			}
		}

		
	}

	 static boolean isConfirm(int i, int j) {
		
		
		System.out.println("Do you want to confirm the tranfer...?(yes/no)");
		String choice = scan.next();
		
		return ((choice.equalsIgnoreCase("yes")||choice.equalsIgnoreCase("y")) && i==1 &&j==1);
		
	}

	static int updateAmount(int userID, int amount) throws SQLException {
		
		String sql = "update employee set salary = salary + ? where id = ?";
		pStat = connection.prepareStatement(sql);
		
		pStat.setInt(1, amount);
		pStat.setInt(2, userID);
		
		return pStat.executeUpdate();
		

	}
}
