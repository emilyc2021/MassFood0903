package com.Emily.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorVO;
import com.Merchant.model.MerchantVO;
import com.OrderMaster.model.OrderMasterVO;
import com.utility.Utility;

public class MemberDAO implements MemberDAO_interface{

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	
	//多重查詢消費者
	@Override
	public List<ConsumerVO> getAll_M1(Map<String, String[]> map) {
		List<ConsumerVO> list = new ArrayList<ConsumerVO>();
		ConsumerVO M1VO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			
			String finalSQL = "select * from consumer "
		          + MultiSearch_Member.get_WhereCondition(map)
		          + "order by AccountNumber";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				ConsumerVO consumerVO = new ConsumerVO();
				consumerVO.setAccountNumber(rs.getInt("AccountNumber"));
				consumerVO.setName(rs.getString("Name"));
				consumerVO.setMobilePhone(rs.getString("MobilePhone"));
				consumerVO.setEmail(rs.getString("Email"));
				consumerVO.setStatus(rs.getInt("Status"));
				list.add(consumerVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}


	//多重查詢商家
	@Override
	public List<MerchantVO> getAll_M2(Map<String, String[]> map) {
		List<MerchantVO> list = new ArrayList<MerchantVO>();
		MerchantVO M2VO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			
			String finalSQL = "select * from merchant "
		          + MultiSearch_Member.get_WhereCondition(map)
		          + "order by AccountNumber";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				MerchantVO merchantVO = new MerchantVO();
				merchantVO.setAccountNumber(rs.getInt("AccountNumber"));
				merchantVO.setName(rs.getString("Name"));
				merchantVO.setIdentityNumber(rs.getString("IdentityNumber"));
				merchantVO.setEmail(rs.getString("Email"));
				merchantVO.setStatus(rs.getInt("Status"));
				list.add(merchantVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	//多重查詢外送員
	@Override
	public List<CouriorVO> getAll_M3(Map<String, String[]> map) {
		List<CouriorVO> list = new ArrayList<CouriorVO>();
		CouriorVO M3VO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			
			String finalSQL = "select * from courior "
		          + MultiSearch_Member.get_WhereCondition(map)
		          + "order by AccountNumber";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				CouriorVO couriorVO= new CouriorVO();
				couriorVO.setAccountNumber(rs.getInt("AccountNumber"));
				couriorVO.setName(rs.getString("Name"));
				couriorVO.setMobilePhone(rs.getString("MobilePhone"));
				couriorVO.setEmail(rs.getString("Email"));
				couriorVO.setStatus(rs.getInt("Status"));
				list.add(couriorVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

//複合查詢 註冊表單for消費者
	@Override
	public List<ConsumerVO> register_M1(Map<String, String[]> map) {
		List<ConsumerVO> list = new ArrayList<ConsumerVO>();
		ConsumerVO M1VO=new ConsumerVO();;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			
			String finalSQL = "select * from consumer "
		          + MultiSearch_Register.get_WhereCondition(map)
		          + "order by AccountNumber";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				ConsumerVO consumerVO = new ConsumerVO();
				consumerVO.setAccountNumber(rs.getInt("AccountNumber"));
				consumerVO.setName(rs.getString("Name"));
				consumerVO.setMobilePhone(rs.getString("MobilePhone"));
				consumerVO.setEmail(rs.getString("Email"));
				consumerVO.setStatus(rs.getInt("Status"));
				list.add(consumerVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	//複合查詢 註冊表單for商家
	@Override
	public List<MerchantVO> register_M2(Map<String, String[]> map) {
		List<MerchantVO> list = new ArrayList<MerchantVO>();
		MerchantVO M2VO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			
			String finalSQL = "select * from merchant "
		          + MultiSearch_Register.get_WhereCondition(map)
		          + "order by AccountNumber";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				MerchantVO merchantVO = new MerchantVO();
				merchantVO.setAccountNumber(rs.getInt("AccountNumber"));
				merchantVO.setName(rs.getString("Name"));
				merchantVO.setIdentityNumber(rs.getString("IdentityNumber"));
				merchantVO.setEmail(rs.getString("Email"));
				merchantVO.setStatus(rs.getInt("Status"));
				list.add(merchantVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	//複合查詢 註冊表單for外送員
	@Override
	public List<CouriorVO> register_M3(Map<String, String[]> map) {
		List<CouriorVO> list = new ArrayList<CouriorVO>();
		CouriorVO M3VO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			
			String finalSQL = "select * from courior "
		          + MultiSearch_Register.get_WhereCondition(map)
		          + "order by AccountNumber";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				CouriorVO couriorVO = new CouriorVO();
				couriorVO.setAccountNumber(rs.getInt("AccountNumber"));
				couriorVO.setName(rs.getString("Name"));
				couriorVO.setMobilePhone(rs.getString("MobilePhone"));
				couriorVO.setEmail(rs.getString("Email"));
				couriorVO.setStatus(rs.getInt("Status"));
				list.add(couriorVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	//複合查詢 訂單
	@Override
	public List<OrderMasterVO> order_search(Map<String, String> map) {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO M1VO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			
			String finalSQL = "select * from ordermaster "
		          + MultiSearch_Order.get_WhereCondition(map)
		          + "order by OrderNumber";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				OrderMasterVO orderVO = new OrderMasterVO();
				orderVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderVO.setOrderDate(new Timestamp(rs.getDate("OrderDate").getTime()));
				orderVO.setStatus(rs.getInt("Status"));
				orderVO.setConsumerAccountNumber(rs.getInt("ConsumerAccountNumber"));
				orderVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				orderVO.setCouriorAccountNumber(rs.getInt("CouriorAccountNumber"));
				orderVO.setMealFee(rs.getInt("MealFee"));
				list.add(orderVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	
	
	
	
	
	}


