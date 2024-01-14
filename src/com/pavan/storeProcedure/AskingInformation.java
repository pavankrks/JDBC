package com.pavan.storeProcedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;
public class AskingInformation {

	static Connection connection = null;
	static Statement statement = null;
	static ResultSet result = null;
	static PreparedStatement pStat = null;
	static Scanner scan = null;
	static CallableStatement cStat = null;

	static String loader = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/jdbc01";
	static String username = "root";
	static String password = "root";

	public static void main(String[] args) {

		try {
			Class.forName(loader);
			connection = DriverManager.getConnection(url, username, password);
			scan = new Scanner(System.in);
			System.out.println("This program is focussed on store procedure");
				System.out.println();
			
			while(true) {
				System.out.println("Which information do you want: ");
				System.out.println("======================================");
				System.out.println("1.Employee count in department");
				System.out.println("2.Employee count greater than salary");
				System.out.println("3.List the employees in the department");
				System.out.println("4.Exit the process");
				System.out.println("======================================");
				System.out.print("choose one option : ");
				String option = scan.next();
				
				
				switch(option) {
				
				case "1" : deptCount();break;
				case "2" : salaryCount();break;
				case "3" : getEmployees();break;
				case "4" : System.out.println("Process ended...");
							System.exit(0);
				default : System.out.println("Invalid entry,try again..");
				
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void deptCount() throws SQLException {
		cStat = connection.prepareCall("{call dept_count(?,?)}");
		
		
		System.out.println("Enter the department name: ");
		String deptName = scan.next();
		
		cStat.setString(1, deptName);
		
		cStat.registerOutParameter(2,Types.INTEGER);
		
		cStat.execute();
		
		int i = cStat.getInt(2);
		System.out.println("employees in '"+deptName + "' department is : "+i);
		Display.showTable();
	}
	
	private static void salaryCount() throws SQLException {
		cStat = connection.prepareCall("{call salary_count(?)}");
		
		
		
		System.out.println("Enter the salary: ");
		int salary = scan.nextInt();
		
		cStat.setInt(1, salary);
		
		cStat.registerOutParameter(1,Types.INTEGER);
		
		cStat.execute();
		
		int i = cStat.getInt(1);
		System.out.println("employees greater than '"+salary + "' salary is : "+i);
		Display.showTable();
	}
	
	
	private static void getEmployees() throws SQLException {
		cStat = connection.prepareCall("{call get_employee(?)}");
		
		System.out.println("Enter the department name: ");
		String deptName = scan.next();
		
		cStat.setString(1, deptName);
		
		cStat.execute();
		
		result = cStat.getResultSet();
		
		System.out.println("=======================================================================");
		while(result.next()) {
			
			int id = result.getInt("id");
			String name = result.getString("name");
			String email = result.getString("email");
			String dept = result.getString("dept");
			int salary = result.getInt("salary");
			System.out.printf("%-4d | %-12s | %-25s | %-10s | %-6d\n",id,name,email,dept,salary);
			
		}
		System.out.println("=======================================================================");

	}
}
