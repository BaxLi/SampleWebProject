package com.test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public static Connection getConnectionToDatabase() {
		Connection connection = null;
//                String url = "jdbc:mysql://remotemysql.com:3306/dNSKhiou4J?useSSL=false";

//               DB1
//                String url = "jdbc:mysql://remotemysql.com:3306/dNSKhiou4J";
//                String username = "dNSKhiou4J";
//                String password = "ZXAI7ufX3x!";
                
//              THIS IS DB2 at https://remotemysql.com/phpmyadmin/db_structure.php?server=1&db=aQX5wKjzrU

                String url = "jdbc:mysql://remotemysql.com:3306/aQX5wKjzrU";
                String username = "aQX5wKjzrU";
                String password = "d9eBRgNS0s";
                
		try  {
			// load the driver class
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");

			// get hold of the DriverManager
			connection = DriverManager.getConnection(url,username, password);
                        return connection;
		} 
                catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		}

		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("Connection made to DB!");
		}
		return connection;
	}

}
