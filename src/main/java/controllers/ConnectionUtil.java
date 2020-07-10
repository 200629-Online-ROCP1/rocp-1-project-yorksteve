package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil 
{
	public static Connection GetConnection() throws SQLException
	{
		String url = "jdbc:postgresql://localhost:5432/project";
		String username = "postgres";
		String password = "darthmau1";
		
		return DriverManager.getConnection(url, username, password);
	}
	
//	public static void main(String[] args)
//	{
//		try (Connection conn = ConnectionUtil.GetConnection())
//		{
//			System.out.println("connection successful");
//		}
//		
//		catch (SQLException e)
//		{
//			System.out.println(e);
//		}
//	}
}
