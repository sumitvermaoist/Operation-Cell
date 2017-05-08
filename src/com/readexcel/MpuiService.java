package com.readexcel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.postgresql.util.PSQLException;

public class MpuiService {

	
	public void addAll (List <MpuiData> allData)
	{
		Statement st=null;	
		Connection connection=getConnection();
		try {
	
			for(MpuiData mpui: allData)
			{
				String sql="INSERT INTO mpui(date,blocknumber,frequency) values(";
				
				sql+="'"+mpui.getDate()+"'"+","+mpui.getBlockNumber()+","+mpui.getFrequency()+")";	
			
			st=connection.createStatement();
			
			
			try{
			
			st.executeUpdate(sql);
			}
			catch(PSQLException e)
			{
				if(e.getMessage().substring(7, 8).equals("d"))
				{
					System.out.println("Duplicate Value exists for entry Records..");
				
					st.close();
					connection.commit();
					continue;	
				}
				else{
					System.out.println("In else of inner if...");
					throw e;
				}	
				
			}
			catch(Exception e)
			{
				System.out.println(e);
				
			}
			//sql="INSERT INTO mpui(date,blocknumber,frequency) values(";
			
			st.close();
			
			connection.commit();
			
			}
			
			//st.close();
			//connection.commit();
			connection.close();
		
		
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("In Exception...");
			e.printStackTrace();
		}
		//String sql2="set DateStyle='ISO,DMY' ";
	
		
		//st.executeUpdate(sql2);
		
		
		
		
	}
	
	
	
	public Connection getConnection()
	{
		
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			//return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;
		
		
		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/operationcell", "operationcell",
					"1010");
			
			connection.setAutoCommit(false);
			
			
			return connection;

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			e.getMessage();
			//return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		return connection;

	}
	
	
	
}
