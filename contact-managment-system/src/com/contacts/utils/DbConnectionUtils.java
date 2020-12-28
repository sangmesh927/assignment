package com.contacts.utils;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class DbConnectionUtils {

	public static Connection getMySqlConnection() {
		Connection con=null;
		String fileName="DbConnection.properties";
		try {
			InputStream inStream=DbConnectionUtils.class.getClassLoader().getResourceAsStream(fileName);
			System.out.println(inStream);
			Properties props=new Properties();
			props.load(inStream);
			con=DriverManager.getConnection(props.getProperty("datasource.url"),props.getProperty("datasource.username"),props.getProperty("datasource.password"));
		}catch(IOException  | SQLException e) {
            e.printStackTrace();
        }
		return con;
	}
	

}
