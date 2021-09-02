package com.Emily.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Admin.model.AdminVO;
import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorVO;
import com.Merchant.model.MerchantVO;
import com.utility.Utility;

public class Admin1DAO implements Admin1DAO_interface {

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	// 登入比對帳密
	private static final String FIND_FOR_LOGIN_STMT = "SELECT AccountNumber,Email,Password FROM Admin where Email = ? and Password=?";

	@Override
	public AdminVO findLogin(String email, String password) {
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_FOR_LOGIN_STMT);

			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adminVO = new AdminVO();

				adminVO.setAccountNumber(rs.getInt("AccountNumber"));
				adminVO.setEmail(rs.getString("Email"));
				adminVO.setPassword(rs.getString("Password"));

			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return adminVO;
	}
	
	private static final String FIND_M1Status1_STMT = "Select Count(*) AS count from Consumer where status=1";
	//查詢消費者待審核人數
	@Override
	public Integer findM1Status1() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_M1Status1_STMT );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				count =rs.getInt("count");
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return count;
	}
	
	private static final String FIND_findM1Status2_STMT = "Select Count(*)  AS count from Consumer where status=2";
	// 查詢消費者通過人數
	@Override
	public Integer findM1Status2() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_findM1Status2_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				count =rs.getInt("count");
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return count;
	}
	
	private static final String FIND_findM2Status1_STMT = "Select Count(*) AS count from merchant where status=1";
	//查詢商家待審核人數
	@Override
	public Integer findM2Status1() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_findM2Status1_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				count =rs.getInt("count");
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return count;
	}
	
	private static final String FIND_findM2Status2_STMT = "Select Count(*) AS count from merchant where status=2";
	//查詢商家通過人數
	@Override
	public Integer findM2Status2() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_findM2Status2_STMT );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				count =rs.getInt("count");
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return count;
	}
	
	private static final String FIND_findM3Status1_STMT = "Select Count(*) AS count from Courior where status=1";
	//查詢外送員待審核人數
	@Override
	public Integer findM3Status1() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_findM3Status1_STMT );
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				count =rs.getInt("count");
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return count;
	}

	
	private static final String FIND_findM3Status2_STMT = "Select Count(*)  AS count from Courior where status=2";
	//查詢外送員通過人數
	@Override
	public Integer findM3Status2() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count=0;
		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_findM3Status2_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {

				count =rs.getInt("count");
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		return count;
	}

	


//更新消費者資訊(密碼、身分證、前次修改時間、前次修改人員、是否啟用、帳號狀態)
	private static final String UPDATE_Consumer_STMT =
			"UPDATE Consumer set Password=?,IdentityNumber=?, LastUpdateDatetime=?,LastUpdateAccountNumber=?,IsEnable=?,Status=?"
			+ " where AccountNumber = ?";
	@Override
	public void updateM1(ConsumerVO consumerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_Consumer_STMT);
			
			pstmt.setString(1, consumerVO.getPassword());
			pstmt.setString(2, consumerVO.getIdentityNumber());
			pstmt.setTimestamp(3, consumerVO.getLastUpdateDatetime());
			pstmt.setInt(4, consumerVO.getLastUpdateAccountNumber());
			pstmt.setBoolean(5, consumerVO.getIsEnable());
			pstmt.setInt(6, consumerVO.getStatus());
			pstmt.setInt(7, consumerVO.getAccountNumber());
						
			pstmt.executeUpdate();
	
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
	}
	
	//更新商家資訊(密碼、統編、身分證、前次修改時間、前次修改人員、是否啟用、帳號狀態)
	private static final String UPDATE_Merchant_STMT =
			"UPDATE Merchant set Password=?,IdentityNumber=?,ContactPersonIdentityNumber=?, LastUpdateDatetime=?,LastUpdateAccountNumber=?,IsEnable=?,Status=?"
			+ " where AccountNumber = ?";
	@Override
	public void updateM2(MerchantVO merchantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_Merchant_STMT);
			
			pstmt.setString(1, merchantVO.getPassword());
			pstmt.setString(2, merchantVO.getIdentityNumber());
			pstmt.setString(3, merchantVO.getContactPersonIdentityNumber());
			pstmt.setTimestamp(4, merchantVO.getLastUpdateDatetime());
			pstmt.setInt(5, merchantVO.getLastUpdateAccountNumber());
			pstmt.setBoolean(6, merchantVO.getIsEnable());
			pstmt.setInt(7, merchantVO.getStatus());
			pstmt.setInt(8, merchantVO.getAccountNumber());
						
			pstmt.executeUpdate();
	
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
		
	}
	//更新外送員資訊(密碼、身分證、前次修改時間、前次修改人員、是否啟用、帳號狀態)
		private static final String UPDATE_Courior_STMT =
				"UPDATE Courior set Password=?,IdentityNumber=?, LastUpdateDatetime=?,LastUpdateAccountNumber=?,IsEnable=?,Status=?"
				+ " where AccountNumber = ?";
	@Override
	public void updateM3(CouriorVO couriorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement( UPDATE_Courior_STMT);
			
			pstmt.setString(1, couriorVO.getPassword());
			pstmt.setString(2, couriorVO.getIdentityNumber());
			pstmt.setTimestamp(3, couriorVO.getLastUpdateDatetime());
			pstmt.setInt(4, couriorVO.getLastUpdateAccountNumber());
			pstmt.setBoolean(5, couriorVO.getIsEnable());
			pstmt.setInt(6, couriorVO.getStatus());
			pstmt.setInt(7, couriorVO.getAccountNumber());
						
			pstmt.executeUpdate();
	
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
	}

}
