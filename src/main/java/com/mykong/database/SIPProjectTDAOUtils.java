package com.mykong.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SIPProjectTDAOUtils {
	public static final String ATT_NAME_CONNECTION = "con";

	public static String connName() {
		return ATT_NAME_CONNECTION;
	}

	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

	public static Connection getStoredConnection() throws ClassNotFoundException, SQLException {
		
	 	Driver myDriver = new oracle.jdbc.driver.OracleDriver();
	 	DriverManager.registerDriver(myDriver);

		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@db-test.p.icpsr.umich.edu:1521:icpsr";
		String user = "archonnex";

		Connection con = DriverManager.getConnection(url, user, user);
		
		if(con!=null){
			System.out.println("SUCCESSFUL CONNECTION");
			return con;
		}
		else{
			System.out.println("FAILURE TO CONNECT TO DATABASE");
			
		}
		return con;
	}

	public static PreparedStatement getPreparedStatement(Connection con, String sql)
			throws ClassNotFoundException, SQLException {

		PreparedStatement ps = con.prepareStatement(sql);
		return ps;
	}
}
