package com.sym.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {
	public Connection getConnection() 
	{ 
		Connection connection = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/PGSymWeb?characterEncoding=utf-8", "root", "123456");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {	
			e.printStackTrace();
		}   	
		return connection;
	}
	
	public void closeconn(Connection connection)
	{  	 
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
