package com.util.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {
	public static  String URL;
	public static String User;
	public static  String Password;
	public static  String Driver;
	public static ResourceBundle resourceBundle=ResourceBundle.getBundle("dbConfig");
	private DBUtils(){}
	static {
		try {
			URL=resourceBundle.getString("jdbc.url");
			User=resourceBundle.getString("jdbc.username");
			Password=resourceBundle.getString("jdbc.password");
			Driver=resourceBundle.getString("jdbc.driver");
			Class.forName(Driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(URL, User, Password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public static void close(ResultSet resultSet,Statement statement,Connection connection){
		try {
			if(resultSet!=null)resultSet.close();
			if(statement!=null)statement.close();
			if(connection!=null)connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
