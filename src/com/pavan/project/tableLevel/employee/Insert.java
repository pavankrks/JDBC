package com.pavan.project.tableLevel.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Insert {

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

			insert(connection, statement, pStat, result, scan);

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

	public static void insert(Connection connection, Statement statement, PreparedStatement pStat, ResultSet result,
			Scanner scan) throws SQLException {
		String sql = "insert into `employee` " + "(`id`,`name`,`email`,`dept`,`salary`) " + "values(?,?,?,?,?);";

		pStat = connection.prepareStatement(sql);
		String choice;

		System.out.println("Before update: ");

		Display.showTable(connection, statement, result);
		System.out.println();
	
		do {
			System.out.println();
			System.out.println("Enter the employee details: ");
			System.out.println("id: ");
			pStat.setInt(1, scan.nextInt());
			System.out.println("name: ");
			pStat.setString(2, scan.next());
			System.out.println("email: ");
			pStat.setString(3, scan.next());
			System.out.println("dept: ");
			pStat.setString(4, scan.next());
			System.out.println("salary: ");
			pStat.setInt(5, scan.nextInt());

			pStat.executeUpdate();
			System.out.println("Do you want to insert more....? (yes/no)");

			choice = scan.next();
			System.out.println();
			System.out.println("After update: ");

			Display.showTable(connection, statement, result);
			if (choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")) {
				return;
				
			}
			else if(!(choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n") || choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"))) {
				
				System.out.println("Invalid choice, try again");
				System.out.println("Do you want to insert more....? (yes/no)");

				choice = scan.next();
				
			}
		} while (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"));
	}
}
