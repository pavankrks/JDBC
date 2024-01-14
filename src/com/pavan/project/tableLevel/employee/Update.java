package com.pavan.project.tableLevel.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		PreparedStatement pStat = null;
		Scanner scan = null;

		String loader = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jdbc01";
		String username = "root";
		String password = "root";

		try {
			Class.forName(loader);
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
			scan = new Scanner(System.in);

			updateRow(connection, statement, pStat, result, scan);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {

			if (scan != null) {
				scan.close();
			}

			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (pStat != null) {
					pStat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (result != null) {
					result.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void updateRow(Connection connection, Statement statement, PreparedStatement pStat, ResultSet result,
			Scanner scan) throws SQLException {
		String choice;
		String option;
		String sql = null;
		boolean b = true;
		
		System.out.println("===================================================================");
		System.out.println("Before update: ");

		Display.showTable(connection, statement, result);
		System.out.println();
	
		do {
			System.out.println();
			System.out.println("Based on what you want to update: ");
			
			System.out.println("1.id\n2.name\n3.email\n4.dept\n5.salary");
			System.out.print("choose option : ");
			option = scan.next();
			
			switch(option) {
			
			
			case "1": 	
						sql = "update employee set id = ? where id = ?";
						pStat = connection.prepareStatement(sql);
						System.out.println("enter new id: ");
						pStat.setInt(1, scan.nextInt());
						System.out.println("enter current id: ");
						pStat.setInt(2, scan.nextInt());
						break;
			case "2": 
						sql = "update employee set name = ? where name = ?";
						pStat = connection.prepareStatement(sql);
						System.out.println("enter new name: ");
						pStat.setString(1, scan.next());
						System.out.println("enter current name: ");
						pStat.setString(2, scan.next());
						break;
			case "3": 	
						sql = "update employee set email = ? where email = ?";
						pStat = connection.prepareStatement(sql);
						System.out.println("enter new email: ");
						pStat.setString(1, scan.next());
						System.out.println("enter current email: ");
						pStat.setString(2, scan.next());
						break;
			case "4": 
						sql = "update employee set dept = ? where dept = ?";
						pStat = connection.prepareStatement(sql);
						System.out.println("enter new dept: ");
						pStat.setString(1, scan.next());
						System.out.println("enter current dept: ");
						pStat.setString(2, scan.next());
						break;
			case "5": 
						sql = "update employee set salary = ? where salary = ?";
						pStat = connection.prepareStatement(sql);
						System.out.println("enter new salary: ");
						pStat.setInt(1, scan.nextInt());
						System.out.println("enter current salary: ");
						pStat.setInt(2, scan.nextInt());
						break;
						
			default : 
						System.out.println("Invalid entry, Try again... ");
						System.out.println();
						b = false;
					
			}
			
			if(b) {
				pStat.executeUpdate();
			}
			
			System.out.println("Do you want to update more....? (yes/no)");

			choice = scan.next();
			System.out.println();
			System.out.println("After update: ");
			System.out.println("===================================================");

			Display.showTable(connection, statement, result);
			if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
				return;
			}
		} while (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"));
	}
}
