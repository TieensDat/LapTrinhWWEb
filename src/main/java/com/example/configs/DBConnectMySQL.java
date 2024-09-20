package com.example.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {
//	private static Connection con = null;
	   private static String USERNAME = "root";
	   private static String PASSWORD = "tiendat@123";
	   private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	   private static String URL = "jdbc:mysql://localhost:3306/newdb";

	   public static Connection getDatabaseConnection() throws SQLException ,Exception{

	       try {
	    	Class.forName(DRIVER);
			return DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	   }
	   
	   public static void main(String [] args) {
		   try {
			   new DBConnectMySQL();
			   System.out.print(DBConnectMySQL.getDatabaseConnection());
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	   
}
