package com.tiancheng.chapter01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class JdbcExample {
	private Connection getConnection(){
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mybatis?zeroDataTimeBehavior=convertToNull";
			String user = "root";
			String password = "";
			connection = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger(JdbcExample.class.getName()).log(Level.ALL, null);
			return null;
		}
		
		return connection;
	}
	
	
}
