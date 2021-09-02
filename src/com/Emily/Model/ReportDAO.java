package com.Emily.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.Consumer.model.ConsumerVO;
import com.OrderMaster.model.OrderMasterVO;
import com.utility.Utility;

public class ReportDAO implements ReportDAO_interface{
	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
		
	//商家訂單完成
		private static final String FIND__REPORT_FOR_OrderOK_STMT = " SELECT OrderNumber,OrderDate,mealFee,ProfitSharingAmount,mealFee-ProfitSharingAmount AS 'payable' FROM OrderMaster where orderdate like ? and merchantaccountnumber=? and Status NOT IN (0,99)";
	@Override
	public List<ReportVO> report_ForOrderOKM2(String date,int account) {
		
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_OrderOK_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVO orderVO = new ReportVO();
				orderVO.setOrderNumber(rs.getInt(1));
				orderVO.setOrderDate(rs.getDate(2));
				orderVO.setMealFee(rs.getInt(3));
				orderVO.setProfitSharingAmount(rs.getInt(4));
				orderVO.setPayable(rs.getInt(5));				
				list.add(orderVO); // Store the row in the list
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

	

	//商家訂單取消
	private static final String FIND__REPORT_FOR_OrderCancel_STMT = " SELECT OrderNumber,OrderDate,mealFee,ProfitSharingAmount,mealFee-ProfitSharingAmount AS 'payable' FROM OrderMaster where orderdate like ? and merchantaccountnumber=? and Status  IN (99)";
	@Override
	public List<ReportVO>  report_ForOrderCancelM2(String date,int account) {
	
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_OrderCancel_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVO orderVO = new ReportVO();
				orderVO.setOrderNumber(rs.getInt(1));
				orderVO.setOrderDate(rs.getDate(2));
				orderVO.setMealFee(rs.getInt(3));
				orderVO.setProfitSharingAmount(rs.getInt(4));
				orderVO.setPayable(rs.getInt(5));				
				list.add(orderVO); // Store the row in the list
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

	//計算商家訂單月份:訂單筆數 應付金額	
		private static final String FIND__REPORT_FOR_Payable_STMT = "select MerchantAccountNumber,count(*) as 'count',sum(mealFee-ProfitSharingAmount) AS 'payableaccount' FROM OrderMaster where orderdate like  ?  and merchantaccountnumber=? and Status  NOT IN (0,99)";
	@Override
	public List<ReportVO>  report_ForOrderPayableM2(String date,int account) {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_Payable_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVO orderVO = new ReportVO();
				orderVO.setMerchantAccountNumber(rs.getInt(1));
				orderVO.setCount(rs.getInt(2));
				orderVO.setPayableaccount(rs.getInt(3));
				list.add(orderVO); // Store the row in the list
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
	
	//外送員訂單完成
	private static final String FIND__REPORT_FOR_OrderOKm3_STMT = " SELECT OrderNumber,OrderDate,MerchantAccountNumber, DeliverFee FROM OrderMaster where orderdate like ? and courioraccountnumber=? and Status IN (5,6,7,8) and IsNeedDelivery = 1";
	public List<ReportVO> report_ForOrderOKM3(String date, int account) {
		
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_OrderOKm3_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVO orderVO = new ReportVO();
				orderVO.setOrderNumber(rs.getInt(1));
				orderVO.setOrderDate(rs.getDate(2));
				orderVO.setMerchantAccountNumber(rs.getInt(3));
				orderVO.setDeliverFee(rs.getInt(4));		
				list.add(orderVO); // Store the row in the list
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
	//計算外送員訂單月份:訂單筆數 應付金額
	private static final String FIND__REPORT_FOR_PayableM3_STMT = "select CouriorAccountNumber,count(*) as 'count',sum(DeliverFee) AS 'payableaccount' FROM OrderMaster where orderdate like  ?  and courioraccountnumber=? and Status  IN (5,6,7,8) and IsNeedDelivery = 1";
	@Override
	public List<ReportVO> report_ForOrderPayableM3(String date, int account) {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_PayableM3_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportVO orderVO = new ReportVO();
				orderVO.setCouriorAccountNumber(rs.getInt(1));
				orderVO.setCount(rs.getInt(2));
				orderVO.setPayableaccount(rs.getInt(3));
				list.add(orderVO); // Store the row in the list
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
	
	//圖表每日訂單數、訂單金額、訂單日期
	private static final String FIND__REPORT_FOR_OrderChartM2_STMT = 
			"SELECT sum(MealFee) as orderamount ,count(*) as orders ,CAST(OrderDate AS DATE) as orderdate" + 
			" FROM OrderMaster" + 
			" where orderdate like ?" + 
			" and merchantaccountnumber=? " + 
			" and Status NOT IN (0,99) " + 
			" group by CAST(OrderDate AS DATE)" + 
			" order by CAST(OrderDate AS DATE)";
	@Override
	public ArrayList report_ForOrderAmountM2(String date, int account) {
		ArrayList orderAmount = new ArrayList();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_OrderChartM2_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderAmount.add(rs.getInt(1)); // Store the row in the list
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

		return orderAmount ;
	}
	@Override
	public ArrayList report_ForOrdersM2(String date, int account) {
		ArrayList orders = new ArrayList();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_OrderChartM2_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orders.add(rs.getInt(2)); // Store the row in the list
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

		return orders ;
	}
	@Override
	public ArrayList report_ForOrderDuringM2(String date, int account) {
		ArrayList orderDuring = new ArrayList();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_OrderChartM2_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				DateFormat dateFormat = new SimpleDateFormat("MM-dd");
				Date date1 = rs.getDate(3);        
				String dateToStr = dateFormat.format(date1);				
				orderDuring.add(dateToStr); // Store the row in the list
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

		return orderDuring;
	}
	//圖表熱門餐點+數量
	private static final String FIND__REPORT_FOR_PoPMealM2_STMT = 
			"SELECT ItemName,sum(ItemAmount)as popItem" + 
			" FROM OrderMaster" + 
			" LEFT JOIN OrderDetail"+
			" ON OrderMaster.OrderNumber=OrderDetail.OrderNumber"+
			" where orderdate like ?" + 
			" and merchantaccountnumber=? " + 
			" and Status NOT IN (0,99) " + 
			" group by OrderDetail.ItemName" + 
			" order by popItem desc LIMIT 5";	
	
	@Override
	public ArrayList report_ForPopMealM2(String date, int account) {
		ArrayList itemName = new ArrayList();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_PoPMealM2_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				itemName.add(rs.getString(1)); // Store the row in the list
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

		return itemName ;
	}
	@Override
	public ArrayList report_ForPopMealTotalM2(String date, int account) {
		ArrayList popItemTotal = new ArrayList();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND__REPORT_FOR_PoPMealM2_STMT);
			//SQL查詢的字串
			String orderdate=date+ "%";
			pstmt.setString(1, orderdate);
			pstmt.setInt(2, account);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				popItemTotal.add(rs.getInt(2)); // Store the row in the list
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

		return popItemTotal ;
	}

}
