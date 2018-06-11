package com.festivalapp.service.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.festivalapp.service.datamodel.StageTime;

public class StandardDaoJdbcImpl {
	
	// Local db settings
//	String dbUrl = "jdbc:mysql://localhost:3306/festival";
//	String dbUsername = "root";
//	String dbPassword = "password";
	
	// Openshift db settings
	private static final String DB_HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	private static final String DB_PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
	private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/jbossews";
	private static final String DB_USERNAME = "adminSvb4x5j";
	private static final String DB_PASSWORD = "ZCrrXCB652HH";

	public <R> List<R> queryForListBySqlStmt(String sqlStmt, RowMapper<R> rowMapper) {
		
		ResultSet rs = null;
		Connection conn = null;
		List<R> listOfObjects = new ArrayList<R>();

		// Ensure mysql jdbc connector jar is available, build path etc
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading database driver");
			e.printStackTrace();
		}

		// Connect to the db		
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (SQLException e) {
			System.out.println("Error connecting to database");
			e.printStackTrace();
		}

		// Query the db
		try {
			PreparedStatement statement = conn.prepareStatement(sqlStmt);
			rs = statement.executeQuery();
			listOfObjects = rowMapper.mapRow(rs);
		} catch (SQLException e1) {
			System.out.println("Error executing query");
			e1.printStackTrace();
		} finally {
	        if (conn != null) {
	        	try {
					conn.close();
				} catch (SQLException e) {
					System.out.println("Error closing connection");
					e.printStackTrace();
				}
	        }
	    }		
		
		return listOfObjects;
	}
	
}
