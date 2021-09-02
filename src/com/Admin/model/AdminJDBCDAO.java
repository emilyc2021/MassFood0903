package com.Admin.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.utility.Utility;

public class AdminJDBCDAO implements AdminDAO_interface {

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	// 新增
	private static final String INSERT_STMT = "INSERT INTO Admin (Email,Password) VALUES (?, ?)";

	// 取得全部
	private static final String GET_ALL_STMT = "SELECT AccountNumber,Email,Password FROM Admin order by AccountNumber";

	// 依AccountNumber 取得單筆資料
	private static final String FIND_BY_ACCOUNTNUMBER_STMT = "SELECT AccountNumber,Email,Password FROM Admin where AccountNumber = ?";

	// 依email 取得單筆資料
	private static final String FIND_BY_MAIL_STMT = "SELECT AccountNumber,Email,Password FROM Admin where Email = ?";

	// 刪除
	private static final String DELETE_STMT = "DELETE FROM Admin where AccountNumber = ?";

	// 更新-允許更新密碼，不更新信箱。
	private static final String UPDATE_STMT = "UPDATE Admin set Password= ? where AccountNumber = ?";

	@Override
	public void insert(AdminVO adminVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminVO.getEmail());
			pstmt.setString(2, adminVO.getPassword());

			pstmt.executeUpdate();

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

	}

	@Override
	public void update(AdminVO adminVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

//			pstmt.setString(1, adminVO.getEmail()); //因為Email是系統中的key不會讓使用者變更電子信箱，僅允許變更密碼。
			pstmt.setString(1, adminVO.getPassword());
			pstmt.setInt(2, adminVO.getAccountNumber());

			pstmt.executeUpdate();

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

	}

	@Override
	public void delete(Integer accountNumber) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, accountNumber);

			pstmt.executeUpdate();

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
	}

	@Override
	public AdminVO findByAccountNumber(Integer accountNumber) {

		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACCOUNTNUMBER_STMT);

			pstmt.setInt(1, accountNumber);

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

	@Override
	public AdminVO findByEmail(String email) {

		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MAIL_STMT);

			pstmt.setString(1, email);

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

	// getAllAccountNumber
	@Override
	public List<AdminVO> getAll() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				adminVO = new AdminVO();
				adminVO.setAccountNumber(rs.getInt("AccountNumber"));
				adminVO.setEmail(rs.getString("Email"));
				adminVO.setPassword(rs.getString("Password"));

				list.add(adminVO);

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
		return list;
	}

	public static void main(String[] args) {
		// test ok
		// 新增
		AdminJDBCDAO dao = new AdminJDBCDAO();

		List<AdminVO> list = null;

		list = dao.getAll();

		int amount = list.size();
		String pseudoEmail = "prefixwording" + amount + "@gmail.com";

//
		AdminVO adminVO_Insert = new AdminVO();
		adminVO_Insert.setEmail(pseudoEmail);
		adminVO_Insert.setPassword("123456");
		dao.insert(adminVO_Insert);

		// 修改密碼 --test OK
		AdminVO adminVO_Modify = new AdminVO();

		adminVO_Modify.setPassword("123456789");
		adminVO_Modify.setAccountNumber(amount + 1);
		dao.update(adminVO_Modify);

		// 刪除 --test Ok
//		dao.delete(2);

		// 查詢-byAccountNumber -- test ok
		System.out.println("-----------adminVO_FindByAccountNumber-start ----------");

		list.clear();
		list.add(dao.findByAccountNumber(amount + 1));
		dao.listPrintAll(list);

		System.out.printf("-----------adminVO_FindByAccountNumber-end ----------\n\n");

		// 查詢-byEmail --test ok
		System.out.println("-----------adminVO_FindByEmail-start ----------");

		list.clear();
		list.add(dao.findByEmail(pseudoEmail));
		dao.listPrintAll(list);
		System.out.printf("-----------adminVO_FindByEmail-end ----------\n\n");

		// 查詢-All -test ok
		System.out.println("-----------adminVO_getAll-Start----------");

		list = dao.getAll();
		dao.listPrintAll(list);
		System.out.println("-----------adminVO_getAll-End----------");
	}

	private void listPrintInteger(List<Integer> list) {
		for (Integer integer : list) {
			System.out.print(integer + ",");
			System.out.println();
		}
	}

	private void listPrintAll(List<AdminVO> list) {

		for (AdminVO adminVO : list) {
			System.out.print(adminVO.getAccountNumber() + ",");
			System.out.print(adminVO.getEmail() + ",");
			System.out.print(adminVO.getPassword() + ",");

			System.out.println();
		}
	}


}
