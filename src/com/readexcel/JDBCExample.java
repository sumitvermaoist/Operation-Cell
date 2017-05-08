package com.readexcel;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCExample {

	public static void main(String[] argv) {

		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;
		Statement st=null;
		
		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/operationcell", "operationcell",
					"1010");
			
			connection.setAutoCommit(false);
			st=connection.createStatement();
			//String sql2="set DateStyle='ISO,DMY' ";
			String sql="INSERT INTO mpui(date,blocknumber,frequency) values('28-Feb-17',1,50.0)";
			
			//st.executeUpdate(sql2);
			st.executeUpdate(sql);
			
			st.close();
			connection.commit();
			connection.close();
			
			

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			e.getMessage();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

}
