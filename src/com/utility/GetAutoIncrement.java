package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetAutoIncrement {
	
	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	
	private static final String GET_AUTO_AUTO_INCREMENT = 
			"select AUTO_INCREMENT from INFORMATION_SCHEMA.TABLES "
			+ "where TABLE_SCHEMA = \"MassFood\" "
			+ "and TABLE_NAME = ?";

	
	public Integer getAutoIncremenetNumber(String tableName) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer autoIncremenetNumber = null;
		
		
		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_AUTO_AUTO_INCREMENT);

			pstmt.setString(1, tableName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
		
	
				autoIncremenetNumber=(rs.getInt("AUTO_INCREMENT"));

			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return autoIncremenetNumber;
	}
	
	
	public static void main(String[] args) {
		GetAutoIncrement getAutoIncrement = new GetAutoIncrement();
		System.out.println(getAutoIncrement.getAutoIncremenetNumber("Merchant"));
		
	}
}
