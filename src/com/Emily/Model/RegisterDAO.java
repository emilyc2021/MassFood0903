package com.Emily.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorVO;
import com.Merchant.model.MerchantVO;
import com.utility.Utility;

public class RegisterDAO implements RegisterDAO_interface{

	
	//消費者-註冊表單更新(前次修改時間、前次修改人員、是否啟用、帳號狀態)
	private static final String UPDATE_RegisterConsumer_STMT =
			"UPDATE Consumer set LastUpdateDatetime=?,LastUpdateAccountNumber=?,IsEnable=?,Status=?"
			+ " where Email = ?";
	@Override
	public void updateRegisterM1(ConsumerVO consumerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_RegisterConsumer_STMT);
						
			pstmt.setTimestamp(1, consumerVO.getLastUpdateDatetime());
			pstmt.setInt(2, consumerVO.getLastUpdateAccountNumber());
			pstmt.setBoolean(3, consumerVO.getIsEnable());
			pstmt.setInt(4, consumerVO.getStatus());
			pstmt.setString(5, consumerVO.getEmail());
						
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
	//商家-註冊表單更新(前次修改時間、前次修改人員、是否啟用、帳號狀態)
	private static final String UPDATE_RegisterMarchant_STMT =
			"UPDATE Merchant set LastUpdateDatetime=?,LastUpdateAccountNumber=?,IsEnable=?,Status=?"
			+ " where Email = ?";
	@Override
	public void updateRegisterM2(MerchantVO merchantVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement( UPDATE_RegisterMarchant_STMT);
						
			pstmt.setTimestamp(1, merchantVO.getLastUpdateDatetime());
			pstmt.setInt(2, merchantVO.getLastUpdateAccountNumber());
			pstmt.setBoolean(3, merchantVO.getIsEnable());
			pstmt.setInt(4, merchantVO.getStatus());
			pstmt.setString(5, merchantVO.getEmail());
						
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
	//外送員-註冊表單更新(前次修改時間、前次修改人員、是否啟用、帳號狀態)
	private static final String UPDATE_RegisterCourior_STMT =
			"UPDATE courior set LastUpdateDatetime=?,LastUpdateAccountNumber=?,IsEnable=?,Status=?"
			+ " where Email = ?";
	@Override
	public void updateRegisterM3(CouriorVO couriorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement( UPDATE_RegisterCourior_STMT);
						
			pstmt.setTimestamp(1, couriorVO.getLastUpdateDatetime());
			pstmt.setInt(2, couriorVO.getLastUpdateAccountNumber());
			pstmt.setBoolean(3, couriorVO.getIsEnable());
			pstmt.setInt(4, couriorVO.getStatus());
			pstmt.setString(5, couriorVO.getEmail());
						
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
