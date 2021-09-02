package com.Emily.Model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.utility.Utility;

public class PopresturantDAO implements Popresturant_interface{
	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
//	熱門餐廳
	private static final String Fond_Popresturant_STMT =
			"select AccountNumber,count(ItemNumber) as count, sum(AverageGrade) as sumAverage ,(sum(AverageGrade)/count(ItemNumber)) as totalAverage from Merchandise group by AccountNumber order by totalAverage desc  LIMIT 6";
	
	@Override
	public List<PopresturantVO> popular_restaurant() {
		List<PopresturantVO> list = new ArrayList<PopresturantVO>();
		ReportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(Fond_Popresturant_STMT);
			//SQL查詢的字串
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PopresturantVO popresturantVO = new PopresturantVO();
				popresturantVO.setAccountNumber(rs.getInt(1));
				popresturantVO.setCount(rs.getInt(2));
				popresturantVO.setSumAverage(rs.getDouble(3));
				//轉成四捨五入到小數點第一位
			    BigDecimal bd = new BigDecimal(rs.getDouble(4)).setScale(1, RoundingMode.HALF_UP);
			    double average = bd.doubleValue();
				popresturantVO.setTotalAverage(average);			
				list.add(popresturantVO); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

		return list ;
	}
	
}
