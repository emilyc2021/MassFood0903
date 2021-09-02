package com.Comment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.utility.Utility;


public class CommentJDBCDAO implements CommentDAO_interface{

	
	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	// 新增資料
	private static final String INSERT_STMT = 
			"INSERT INTO Comment ("
			+ "OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer," 
			+ "Content, CommentDateTime"
			+ ") " 
			+ "VALUES (?, ?, ?, ?," 
			+ "?,?)";
	
	// 取得全部
	private static final String GET_ALL_STMT = 
			"SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
			+"Content,CommentDateTime"	
			+ " FROM Comment order by CommentNumber";
	
	// 刪除(基本上我們不會刪除
	private static final String DELETE_STMT = 
			"DELETE FROM Comment where CommentNumber = ?";

	// 更新單筆資料
	private static final String UPDATE_STMT = 
			"UPDATE Comment set OrderNumber=?, MerchantAccountNumber=?, AccountNumber=?, IsFromConsumer=? ,"
			+ "Content=? , CommentDateTime=?" 
			+ " where CommentNumber =? ";
	
	
	//依訂單編號(orderNumber)取得評論。
	private static final String FIND_BY_ORDERNUMBER_STMT = 
			"SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
			+"Content,CommentDateTime"	
			+ " FROM Comment where OrderNumber = ? order by OrderNumber";
	
	//依MerchantAccountNumber取得相關內容-這邊供商家資訊那邊顯示，所以全部取得，再用IsFromConsumer判別是商家或是消費者評論，進行顯示於廠商頁面。
	private static final String FIND_BY_MERCHANTACCOUNTNUMBER_STMT = 
		"SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
		+"Content,CommentDateTime"	
		+ " FROM Comment where OrderNumber = ? order by CommentNumber";
	
	//	https://ithelp.ithome.com.tw/articles/10190257
	//消費者專區查詢使用，依AccountNumber 可查詢最近消費者(廠商)最近有評論的資料，取第一筆。  //IsFromConsumer 0是廠商，1是消費者。這邊先保留給消費者專區使用
	
	private static final String FIND_BY_ACCOUNTNUMBER_FOR_LASTEST_STMT = 
		"select * from "
		+ "("
				+ "SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
					+ "Content,CommentDateTime,ROW_NUMBER() OVER(PARTITION BY OrderNumber ORDER BY CommentDateTime desc) as rowNumber "
					+ "FROM Comment where AccountNumber =? and IsFromConsumer =?"
		+ ")as t where rowNumber =1";
	
	//findByMerchantAccountNumberForLastest
	//廠商專區查詢使用，先列出所有與廠商有關的評論，挑最新的一筆。
	private static final String FIND_BY_MERCHANTACCOUNTNUMBER_FOR_lASTEST_STMT = 
		"select * from "
		+ "("
				+ "SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
					+ "Content,CommentDateTime,ROW_NUMBER() OVER(PARTITION BY OrderNumber ORDER BY CommentDateTime desc) as rowNumber "
					+ "FROM Comment where MerchantAccountNumber = ?"
		+ ") as t where rowNumber =1";
	

	//廠商專區-找出廠商有回覆的訂單編號-供判斷廠商是否有回覆過。
	private static final String FIND_BY_MERCHANTACCOUNTNUMBER_FOR_HAD_RESPONSE_ORDERNUMBER_STMT=
		"SELECT distinct(OrderNumber) from Comment where AccountNumber=? and isFromConsumer =0; ";
	
	
	@Override
	public void insert(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

		
//			"INSERT INTO Comment ("
//			+ "OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer," 
//			+ "Content, CommentDateTime"
//			+ ") " 
//			+ "VALUES (?, ?, ?, ?, " 
//			+ "?,?)";

			pstmt.setInt(1, commentVO.getOrderNumber());
			pstmt.setInt(2, commentVO.getMerchantAccountNumber());
			pstmt.setInt(3, commentVO.getAccountNumber());
			pstmt.setBoolean(4, commentVO.getIsFromConsumer());
			
			pstmt.setString(5, commentVO.getContent());
			pstmt.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
			
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
	public void update(CommentVO commentVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

//			"UPDATE Comment set OrderNumber=?, MerchantAccountNumber=?, AccountNumber=?, IsFromConsumer=? ,"
//			+ "Content=? , CommentDateTime=?" 
//			+ " where CommentNumber =? ";

			pstmt.setInt(1, commentVO.getOrderNumber());
			pstmt.setInt(2, commentVO.getMerchantAccountNumber());
			pstmt.setInt(3, commentVO.getAccountNumber());
			pstmt.setBoolean(4, commentVO.getIsFromConsumer());
			
			pstmt.setString(5, commentVO.getContent());
			pstmt.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
			
			pstmt.setInt(7, commentVO.getCommentNumber());
			
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
	public void delete(Integer commentNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);

			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, commentNumber);

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
	

	@Override
	public List<CommentVO> findByOrderNumber(Integer orderNumber){
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

		
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ORDERNUMBER_STMT);
			
			pstmt.setInt(1, orderNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				commentVO = new CommentVO();
				
				commentVO.setCommentNumber(rs.getInt("CommentNumber"));
				commentVO.setOrderNumber(rs.getInt("OrderNumber"));
				commentVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				commentVO.setAccountNumber(rs.getInt("AccountNumber"));
				commentVO.setIsFromConsumer(rs.getBoolean("IsFromConsumer"));
				commentVO.setContent(rs.getString("Content"));
				commentVO.setCommentDateTime(new Timestamp(rs.getTimestamp("CommentDateTime").getTime()));
				//new java.sql.Timestamp(new java.util.Date().getTime())
				
				list.add(commentVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<CommentVO> findByMerchantAccountNumber(Integer merchantAccountNumber) {
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MERCHANTACCOUNTNUMBER_STMT);
			
			pstmt.setInt(1, merchantAccountNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"select * from "
//						+ "("
//								+ "SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
//									+ "Content,CommentDateTime,ROW_NUMBER() OVER(PARTITION BY OrderNumber ORDER BY CommentDateTime desc) as rowNumber "
//									+ "FROM Comment where AccountNumber =? and IsFromConsumer =?"
//						+ ")as t where rowNumber =1";
				commentVO = new CommentVO();
				
				commentVO.setCommentNumber(rs.getInt("CommentNumber"));
				commentVO.setOrderNumber(rs.getInt("OrderNumber"));
				commentVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				commentVO.setAccountNumber(rs.getInt("AccountNumber"));
				commentVO.setIsFromConsumer(rs.getBoolean("IsFromConsumer"));
				commentVO.setContent(rs.getString("Content"));
				commentVO.setCommentDateTime(new Timestamp(rs.getTimestamp("CommentDateTime").getTime()));
				//new java.sql.Timestamp(new java.util.Date().getTime())
				
				list.add(commentVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<CommentVO> findByMerchantAccountNumberForLastest(Integer merchantAccountNumber) {
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MERCHANTACCOUNTNUMBER_FOR_lASTEST_STMT);
			
			pstmt.setInt(1, merchantAccountNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"select * from "
//						+ "("
//								+ "SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
//									+ "Content,CommentDateTime,ROW_NUMBER() OVER(PARTITION BY OrderNumber ORDER BY CommentDateTime desc) as rowNumber "
//									+ "FROM Comment where AccountNumber =? and IsFromConsumer =?"
//						+ ")as t where rowNumber =1";
				commentVO = new CommentVO();
				
				commentVO.setCommentNumber(rs.getInt("CommentNumber"));
				commentVO.setOrderNumber(rs.getInt("OrderNumber"));
				commentVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				commentVO.setAccountNumber(rs.getInt("AccountNumber"));
				commentVO.setIsFromConsumer(rs.getBoolean("IsFromConsumer"));
				commentVO.setContent(rs.getString("Content"));
				commentVO.setCommentDateTime(new Timestamp(rs.getTimestamp("CommentDateTime").getTime()));
				//new java.sql.Timestamp(new java.util.Date().getTime())
				
				list.add(commentVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public List<Integer> findByMerchantAccountNumberForHadResponseOrderNumber(Integer merchantAccountNumber){
		List<Integer> list = new ArrayList<Integer>();
		CommentVO commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MERCHANTACCOUNTNUMBER_FOR_HAD_RESPONSE_ORDERNUMBER_STMT);
			
			pstmt.setInt(1, merchantAccountNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				list.add(rs.getInt("OrderNumber")); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<CommentVO> findByAccountNumberForLastest(Integer accountNumber , Boolean IsFromConsumer) {
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACCOUNTNUMBER_FOR_LASTEST_STMT);
			
			pstmt.setInt(1, accountNumber);
			pstmt.setBoolean(2, IsFromConsumer);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"select * from "
//						+ "("
//								+ "SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
//									+ "Content,CommentDateTime,ROW_NUMBER() OVER(PARTITION BY OrderNumber ORDER BY CommentDateTime desc) as rowNumber "
//									+ "FROM Comment where AccountNumber =? and IsFromConsumer =?"
//						+ ")as t where rowNumber =1";
				commentVO = new CommentVO();
				
				commentVO.setCommentNumber(rs.getInt("CommentNumber"));
				commentVO.setOrderNumber(rs.getInt("OrderNumber"));
				commentVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				commentVO.setAccountNumber(rs.getInt("AccountNumber"));
				commentVO.setIsFromConsumer(rs.getBoolean("IsFromConsumer"));
				commentVO.setContent(rs.getString("Content"));
				commentVO.setCommentDateTime(new Timestamp(rs.getTimestamp("CommentDateTime").getTime()));
				//new java.sql.Timestamp(new java.util.Date().getTime())
				
				list.add(commentVO); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<CommentVO> getAll() {
		List<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO commentVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {


			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
//						+"Content,CommentDateTime"	
//						+ " FROM Comment order by CommentNumber";
				commentVO = new CommentVO();
				
				commentVO.setCommentNumber(rs.getInt("CommentNumber"));
				commentVO.setOrderNumber(rs.getInt("OrderNumber"));
				commentVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				commentVO.setAccountNumber(rs.getInt("AccountNumber"));
				commentVO.setIsFromConsumer(rs.getBoolean("IsFromConsumer"));
				commentVO.setContent(rs.getString("Content"));
				commentVO.setCommentDateTime(new Timestamp(rs.getTimestamp("CommentDateTime").getTime()));
				//new java.sql.Timestamp(new java.util.Date().getTime())
				
				list.add(commentVO); // Store the row in the list
			}
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	
	public static void main(String[] args) {
				
		CommentJDBCDAO dao = new CommentJDBCDAO();
//		insert
		dao.insert(dao);
		
		//update
		dao.update(dao);
		
		//getall
		List<CommentVO> list = dao.getAll();
//		dao.listPrintAll(list);
		
		
		//findByAccountNumber
		System.out.println("---findByAccountNumberForLastest---");
		list = dao.findByAccountNumberForLastest(1,true);
		dao.listPrintAll(list);
		System.out.println("----------------");
		
		//findByAccountNumber
		System.out.println("---findByMerchantAccountNumber---");
		list = dao.findByMerchantAccountNumber(1);
		dao.listPrintAll(list);
		System.out.println("----------------");
		
		//findByAccountNumber
		System.out.println("---findByMerchantAccountNumberForLastest---");
		list = dao.findByMerchantAccountNumberForLastest(1);
		dao.listPrintAll(list);
		System.out.println("----------------");
		
		
		System.out.println("---findByMerchantAccountNumberForHadResponseOrderNumber---");
		List<Integer> listForInteger = dao.findByMerchantAccountNumberForHadResponseOrderNumber(1);
		dao.listPrintInteger(listForInteger);
		System.out.println("----------------");
		
	
		System.out.println("---findByMerchantAccountNumberForHadResponseOrderNumber---");
		list = dao.findByOrderNumber(1);
		dao.listPrintAll(list);
		System.out.println("----------------");
		
		System.out.println("---delete---");
//		dao.delete(10);
		System.out.println("----------------");
	}
	
	
	private void insert(CommentJDBCDAO dao) {

//		"INSERT INTO Comment ("
//		+ "OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer," 
//		+ "Content, CommentDateTime"
//		+ ") " 
//		+ "VALUES (?, ?, ?, ?," 
//		+ "?,?)";

		CommentVO commentVO = new CommentVO();

		commentVO.setOrderNumber(1);
		commentVO.setMerchantAccountNumber(1);
		commentVO.setAccountNumber(1);
		commentVO.setIsFromConsumer(true);
		commentVO.setContent("Content");
	
		dao.insert(commentVO);
	}
	
	private void update(CommentJDBCDAO dao) {
		

//		"UPDATE Comment set OrderNumber=?, MerchantAccountNumber=?, AccountNumber=?, IsFromConsumer=? ,"
//		+ "Content=? , CommentDateTime=?" 
//		+ " where CommentNumber =? ";
		CommentVO commentVO = new CommentVO();

		commentVO.setOrderNumber(1);
		commentVO.setMerchantAccountNumber(1);
		commentVO.setAccountNumber(1);
		commentVO.setIsFromConsumer(true);
		
		commentVO.setContent("Content");
		commentVO.setCommentNumber(6);

		dao.update(commentVO);
		
	}
	
	private void listPrintAll(List<CommentVO> list) {
//		"SELECT CommentNumber,OrderNumber,MerchantAccountNumber,AccountNumber,IsFromConsumer,"
//				+"Content,CommentDateTime"	
		for (CommentVO commentVO : list) {
			System.out.print(commentVO.getCommentNumber() + ",");
			System.out.print(commentVO.getOrderNumber() + ",");
			System.out.print(commentVO.getMerchantAccountNumber() + ",");
			System.out.print(commentVO.getAccountNumber() + ",");
			System.out.print(commentVO.getIsFromConsumer() + ",");
			System.out.print(commentVO.getContent() + ",");
			System.out.print(commentVO.getCommentDateTime() + ",");

			System.out.println();
		}
	}
	
	private void listPrintInteger(List<Integer> list) {
		for (Integer integer : list) {
			System.out.print(integer + ",");
			System.out.println();
		}
	}

	
}
