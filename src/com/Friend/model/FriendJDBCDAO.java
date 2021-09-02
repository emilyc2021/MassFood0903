package com.Friend.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;


import com.utility.FriendStatus;
import com.utility.Utility;

public class FriendJDBCDAO implements FriendDAO_interface {

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "Insert Into Friend ("
			+ "AccountNumber,FriendAccountNumber,Status,DateTime" + ")" + "Values(?,?,?,?),(?,?,?,?)";

	private static final String UPDATE_STMT = "Update Friend Set AccountNumber= ? ,FriendAccountNumber = ? , Status =? ,DateTime =?"
			+ "where SerialNumber = ?";

	private static final String UPDATE_STATUS_STMT = "Update Friend Set Status =? ,DateTime =?"
			+ "where SerialNumber in(?,?)";

	// 刪除(基本上不會刪除)
	private static final String DELETE_STMT = "DELETE FROM Friend where CommentNumber = ?";

	// 找到彼此的serialNumber
	private static final String FIND_EACH_OTHER_SERIALNUMBER_STMT = "Select SerialNumber from Friend"
			+ " Where ((AccountNumber =? and FriendAccountNumber =?) or (AccountNumber =? and FriendAccountNumber =?) )";

	// findByAccountNumber - 狀態要篩選的話再用stream()
	private static final String FIND_BY_ACCOUNTNUMBER = "Select SerialNumber,AccountNumber,FriendAccountNumber,Status,DateTime "
			+ "From Friend where AccountNumber =?";

	private static final String GET_ALL = "SELECT f.SerialNumber,f.AccountNumber,f.FriendAccountNumber,f.Status,f.DateTime,c.Email,c.Name "
			+ "From Friend f " + "left join Consumer c on f.FriendAccountNumber = c.AccountNumber";

	private static final String UPDATE_STATUS_BY_FRIEND = "Update Friend Set Status =? ,DateTime =?"
			+ "where ((AccountNumber =? and FriendAccountNumber =?) or (AccountNumber =? and FriendAccountNumber =?) )";

	private static final String DELETE_STATUS_BY_FRIEND = "Update Friend Set Status =? ,DateTime =?"
			+ "where ((AccountNumber =? and FriendAccountNumber =?) or (AccountNumber =? and FriendAccountNumber =?) )";

	@Override
	public void insert(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			"Insert Into Friend ("
//					+ "AccountNumber,FriendAccountNumber,Ststus,DateTime"
//					+ ")"
//					+ "Values(?,?,?,?),(?,?,?,?)";

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			java.sql.Timestamp currentDateTimeStamp = new java.sql.Timestamp(new java.util.Date().getTime());

			pstmt.setInt(1, friendVO.getAccountNumber());
			pstmt.setInt(2, friendVO.getFriendAccountNumber());
			pstmt.setInt(3, friendVO.getStatus());
			pstmt.setTimestamp(4, currentDateTimeStamp);

			pstmt.setInt(5, friendVO.getFriendAccountNumber());
			pstmt.setInt(6, friendVO.getAccountNumber());
			pstmt.setNull(7, Types.INTEGER);
			pstmt.setTimestamp(8, currentDateTimeStamp);

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
	public void update(FriendVO friendVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

//			"Update Friend Set AccountNumber= ? ,FriendAccountNumber = ? , Status =? ,DateTime =?"
//			+ "where SerialNumber = ?";

			java.sql.Timestamp currentDateTimeStamp = new java.sql.Timestamp(new java.util.Date().getTime());

			pstmt.setInt(1, friendVO.getAccountNumber());
			pstmt.setInt(2, friendVO.getFriendAccountNumber());
			pstmt.setInt(3, friendVO.getStatus());
			pstmt.setTimestamp(4, currentDateTimeStamp);
			pstmt.setInt(5, friendVO.getSerialNumber());

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
	public void updateStatus(int accountNumber, int friendAccountNumber, int Status) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS_STMT);

//			"Update Friend Set Status =? ,DateTime =?"
//			+ "where SerialNumber in(?,?)";

			java.sql.Timestamp currentDateTimeStamp = new java.sql.Timestamp(new java.util.Date().getTime());

			pstmt.setInt(1, Status);
			pstmt.setTimestamp(2, currentDateTimeStamp);
			pstmt.setInt(3, accountNumber);
			pstmt.setInt(4, friendAccountNumber);
//			pstmt.setInt(3, friendVO.());
//			pstmt.setInt(3, friendVO.getSerialNumber());

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
	public void delete(int serialNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);

			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, serialNumber);

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
	public List<Integer> findEachOtherSerialNumber(int AccountNumer, int friendAccountNumer) {
		List<Integer> list = new ArrayList<Integer>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			"Select SerialNumber from Friend"
//					+ "Where ((AccountNumber =? and FriendAccountNumber =?) or (AccountNumber =? and FriendAccountNumber =?) )";

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_EACH_OTHER_SERIALNUMBER_STMT);

			pstmt.setInt(1, AccountNumer);
			pstmt.setInt(2, friendAccountNumer);
			pstmt.setInt(3, friendAccountNumer);
			pstmt.setInt(4, AccountNumer);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(rs.getInt("SerialNumber")); // Store the row in the list
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
		return list;

	}

	@Override
	public List<FriendVO> findByAccountNumber(int accountNumber) {
		List<FriendVO> list = new ArrayList<FriendVO>();
		FriendVO friendVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACCOUNTNUMBER);

			pstmt.setInt(1, accountNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {

//				"Select SerialNumber,AccountNumber,FriendAccountNumber,Status,DateTime "
//						+ "From Friend where AccountNumber =?";	
				friendVO = new FriendVO();

				friendVO.setSerialNumber(rs.getInt("SerialNumber"));
				friendVO.setAccountNumber(rs.getInt("AccountNumber"));
				friendVO.setFriendAccountNumber(rs.getInt("FriendAccountNumber"));
				friendVO.setStatus(rs.getInt("Status"));

				friendVO.setDateTime(new Timestamp(rs.getTimestamp("DateTime").getTime()));

				list.add(friendVO); // Store the row in the list
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
		return list;
	}

	@Override
	public List<FriendVO> getAll() {
		List<FriendVO> list = new ArrayList<FriendVO>();
		FriendVO friendVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {

//				"SELECT f.SerialNumber,f.AccountNumber,f.FriendAccountNumber,f.Status,f.DateTime,c.Email,c.Name "
//						+ "From Friend f " 
//						+ "right join Consumer c on f.FriendAccountNumber = c.AccountNumber";

				friendVO = new FriendVO();
				friendVO.setSerialNumber(rs.getInt("SerialNumber"));
				friendVO.setAccountNumber(rs.getInt("AccountNumber"));
				friendVO.setFriendAccountNumber(rs.getInt("FriendAccountNumber"));
				Object status = rs.getObject("Status");
				if (status == null) {
					friendVO.setStatus(null);
				} else {
					friendVO.setStatus(rs.getInt("Status"));
				}
				friendVO.setDateTime(new Timestamp(rs.getTimestamp("DateTime").getTime()));
				friendVO.setEmail(rs.getString("Email"));
				friendVO.setName(rs.getString("Name"));

				list.add(friendVO); // Store the row in the list
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
		return list;
	}

	@Override
	public void updateStatusByFriend(FriendVO friendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STATUS_BY_FRIEND);

//			"Update Friend Set Status =? ,DateTime =?" 
//			+ "where ((AccountNumber =? and FriendAccountNumber =?) or (AccountNumber =? and FriendAccountNumber =?) )";

			java.sql.Timestamp currentDateTimeStamp = new java.sql.Timestamp(new java.util.Date().getTime());

			pstmt.setInt(1, friendVO.getStatus());
			pstmt.setTimestamp(2, currentDateTimeStamp);
			pstmt.setInt(3, friendVO.getAccountNumber());
			pstmt.setInt(4, friendVO.getFriendAccountNumber());
			pstmt.setInt(5, friendVO.getFriendAccountNumber());
			pstmt.setInt(6, friendVO.getAccountNumber());

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
	public void deleteStatusByFriend(FriendVO friendVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STATUS_BY_FRIEND);

//			"Update Friend Set Status =? ,DateTime =?" 
//			+ "where ((AccountNumber =? and FriendAccountNumber =?) or (AccountNumber =? and FriendAccountNumber =?) )";

			java.sql.Timestamp currentDateTimeStamp = new java.sql.Timestamp(new java.util.Date().getTime());

			pstmt.setInt(1, friendVO.getStatus());
			pstmt.setTimestamp(2, currentDateTimeStamp);
			pstmt.setInt(3, friendVO.getAccountNumber());
			pstmt.setInt(4, friendVO.getFriendAccountNumber());
			pstmt.setInt(5, friendVO.getFriendAccountNumber());
			pstmt.setInt(6, friendVO.getAccountNumber());

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

	public static void main(String[] args) throws ParseException {
		FriendJDBCDAO dao = new FriendJDBCDAO();

//		System.out.println("---------insert----------");
//		dao.insert(dao);

//		System.out.println("---------update----------");
//		dao.update(dao);

//		System.out.println("---------updateStatus----------");
//		List<Integer> serialNumberList = null;
//		serialNumberList = dao.findEachOtherSerialNumber(3, 4);
//		dao.listPrintInteger(serialNumberList);
//		dao.updateStatus(6, 1, FriendStatus.STRANGER.getCode());

//		System.out.println("---------updateStatusbyfriend----------");
//		dao.updateStatusByFriend(dao);
		
		System.out.println("---------deltetStatusbyfriend----------");
		dao.deleteStatusByFriend(dao);

//		System.out.println("---------findByAccountNumber----------");
//		dao.listPrintAll(dao.findByAccountNumber(3));

		System.out.println("---------getAll----------");
		dao.listPrintAll(dao.getAll());

//		System.out.println("---------findFriendEachSerialNumber----------");
//		dao.listPrintInteger(dao.findFriendEachSerialNumber(dao));

	}

	private void insert(FriendJDBCDAO dao) {
//		"Insert Into Friend ("
//		+ "AccountNumber,FriendAccountNumber,Ststus,DateTime"
//		+ ")"
//		+ "Values(?,?,?,?),(?,?,?,?)";

		FriendVO friendVO = new FriendVO();

		friendVO.setAccountNumber(5);
		friendVO.setFriendAccountNumber(6);
		friendVO.setStatus(FriendStatus.FRIEND.getCode());

		dao.insert(friendVO);
	}

	private void update(FriendJDBCDAO dao) {
//		"Update Friend Set AccountNumber= ? ,FriendAccountNumber = ? , Status =? ,DateTime =?"
//		+ "where SerialNumber = ?";

		FriendVO friendVO = new FriendVO();

		friendVO.setSerialNumber(3);
		friendVO.setAccountNumber(7);
		friendVO.setFriendAccountNumber(8);
		friendVO.setStatus(FriendStatus.FRIEND.getCode());

		dao.update(friendVO);
	}

	private List<Integer> findFriendEachSerialNumber(FriendJDBCDAO dao) {
		return dao.findEachOtherSerialNumber(1, 6);
	}

	private void listPrintInteger(List<Integer> list) {
		for (Integer integer : list) {
			System.out.print(integer + ",");
			System.out.println();
		}
	}

	private void listPrintAll(List<FriendVO> list) {
//		"SELECT f.SerialNumber,f.AccountNumber,f.FriendAccountNumber,f.Status,f.DateTime,c.Email,c.Name "
//		+ "From Friend f " 
//		+ "right join Consumer c on f.FriendAccountNumber = c.AccountNumber"
//		+ "where f.AccountNumber = ?";

		for (FriendVO friendVO : list) {
			System.out.print(friendVO.getSerialNumber() + ",");
			System.out.print(friendVO.getAccountNumber() + ",");
			System.out.print(friendVO.getFriendAccountNumber() + ",");
			System.out.print(friendVO.getStatus() + ",");
			System.out.print(friendVO.getDateTime() + ",");
			System.out.print(friendVO.getEmail() + ",");
			System.out.print(friendVO.getName() + ",");

			System.out.println();
		}
	}

	private void updateStatusByFriend(FriendJDBCDAO dao) {

		FriendVO friendVO = new FriendVO();

		friendVO.setStatus(FriendStatus.FRIEND.getCode());
		friendVO.setAccountNumber(1);
		friendVO.setFriendAccountNumber(6);

		dao.updateStatusByFriend(friendVO);
	}
	
	private void deleteStatusByFriend(FriendJDBCDAO dao) {

		FriendVO friendVO = new FriendVO();

		friendVO.setStatus(FriendStatus.STRANGER.getCode());
		friendVO.setAccountNumber(1);
		friendVO.setFriendAccountNumber(6);

		dao.deleteStatusByFriend(friendVO);
	}
}
