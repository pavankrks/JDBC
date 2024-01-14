package com.pavan.project.tableLevel.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ALLOperationsInOne {
	
	
	

	public static void main(String[] args) {

		Scanner scan = null;
		Connection connection = null;
		Statement statement = null;
		PreparedStatement pStat = null;
		ResultSet result = null;
		
		
		String loader = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jdbc01";
		String username = "root";
		String password = "root";
		
		
		try {
			Class.forName(loader);
			connection = DriverManager.getConnection(url,username,password);
			scan = new Scanner(System.in);
			
			while(true) {
				
				System.out.println("=====================================================");
				System.out.println("Which operation you want to do ?");
				System.out.println("1.insert\n2.update\n3.delete\n4.display\n5.exit");
				System.out.print("choose one option: ");
				String option = scan.next();
				switch (option) {

				case "1": 
					System.out.println("================== Insert =======================");
					Insert.insert(connection, statement, pStat, result, scan);break;
				case "2": 
					System.out.println("================== Update =======================");
					Update.updateRow(connection, statement, pStat, result, scan);break;
				case "3": 
					System.out.println("================== Delete =======================");
					Delete.deleteRow(connection, statement, pStat, result, scan);break;
				case "4": 
					System.out.println("================== Display =======================");
					Display.showTable(connection, statement, result);break;
				case "5": 
					System.out.println("================== Exit =======================");
					System.out.println("Process ended...!");
							System.exit(0);
				default:  System.out.println("Invalid entry, Try again....");
				}
			
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
}
