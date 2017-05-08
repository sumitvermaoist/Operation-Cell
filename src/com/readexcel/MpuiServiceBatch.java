package com.readexcel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.postgresql.util.PSQLException;

public class MpuiServiceBatch {

	

	public void addAll (List <MpuiData> allData)
	{
		
		try {
		
			Connection connection=getConnection();
			PreparedStatement ps=connection.prepareStatement("INSERT INTO mpui(date,blocknumber,frequency) values(?,?,?)");
			final int batchSize=1000;
			int count=0;
		
			for(MpuiData mpui: allData)
			{
				
				
				
			try {
				java.util.Date utilDate=new SimpleDateFormat("dd-MMM-yy").parse(mpui.getDate());
				ps.setDate(1,new java.sql.Date(utilDate.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps.setInt(2, mpui.getBlockNumber());
			ps.setDouble(3, mpui.getFrequency());	
			ps.addBatch();
			
			if(++count%batchSize==0)
			{
				ps.executeBatch();
				
			}
			
			}
			ps.executeBatch();
			ps.close();
			connection.close();
		
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.getNextException();
			System.out.println("In Exception...");
			e.printStackTrace();
		}
		
		
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
