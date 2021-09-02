package com.OrderDetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utility.Utility;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface {

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	// 新增orderDetail時，使用者尚未評分，所這這邊沒有Grade
	private static final String INSERT_STMT = 
			"INSERT INTO OrderDetail ("
			+ "OrderNumber,ItemNumber,ItemName,ItemPrice,ItemAmount," 
			+ "Memo" 
			+ ") " 
			+ "VALUES (?, ?, ?, ?, ?," 
			+ "?)";

	// 取得全部
	private static final String GET_ALL_STMT = 
			"SELECT OrderNumber,ItemNumber,ItemName,ItemPrice,ItemAmount,"
			+ "Grade,Memo" 
			+ " FROM OrderDetail order by OrderNumber";

	// 依OrderNumber 取得相關內容
	private static final String GET_BY_ORDERNUMBER_STMT = 
			"SELECT OrderNumber,ItemNumber,ItemName,ItemPrice,ItemAmount,"
			+ "Grade,Memo" 
			+ " FROM OrderDetail where OrderNumber = ?";

	// 依ItemNumber 取得相關內容
	private static final String GET_BY_ItemNumber_STMT = 
			"SELECT OrderNumber,ItemNumber,ItemName,ItemPrice,ItemAmount,"
			+ "Grade,Memo" 
			+ " FROM OrderDetail where ItemNumber = ?";

	// 刪除(基本上我們不會刪除
	private static final String DELETE = 
			"DELETE FROM OrderDetail where OrderNumber = ?";

	// 更新單筆資料
	private static final String UPDATE = 
			"UPDATE OrderDetail set ItemName=?, ItemPrice=?, ItemAmount=?, Grade=? ,"
			+ "Memo=?" 
			+ " where OrderNumber =? and ItemNumber = ?";

	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

//			+ "OrderNumber,ItemNumber,ItemName,ItemPrice,ItemAmount,"
//			+ "Memo"

			pstmt.setInt(1, orderDetailVO.getOrderNumber());
			pstmt.setInt(2, orderDetailVO.getItemNumber());
			pstmt.setString(3, orderDetailVO.getItemName());
			pstmt.setInt(4, orderDetailVO.getItemPrice());
			pstmt.setInt(5, orderDetailVO.getItemAmount());
			
			pstmt.setString(6, orderDetailVO.getMemo());

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
	public void update(OrderDetailVO orderDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

//			"UPDATE OrderDetail set ItemName=?, ItemPrice=?, ItemAmount=?, Grade=? ,"
//			+ "Memo=?" 
//			+ " where OrderNumber =? and ItemNumber = ?";
			
			pstmt.setString(1, orderDetailVO.getItemName());
			pstmt.setInt(2, orderDetailVO.getItemPrice());
			pstmt.setInt(3, orderDetailVO.getItemAmount());
			pstmt.setInt(4, orderDetailVO.getGrade());
			
			pstmt.setString(5, orderDetailVO.getMemo());
			pstmt.setInt(6, orderDetailVO.getOrderNumber());
			pstmt.setInt(7, orderDetailVO.getItemNumber());
	

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer OrderNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, OrderNumber);

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
	public List<OrderDetailVO> findByItemNumber(Integer itemNumber) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_BY_ItemNumber_STMT);
			pstmt.setInt(1, itemNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderDetailVO.setItemNumber(rs.getInt("ItemNumber"));
				orderDetailVO.setItemName(rs.getString("ItemName"));
				orderDetailVO.setItemPrice(rs.getInt("ItemPrice"));
				orderDetailVO.setItemAmount(rs.getInt("ItemAmount"));
				
				orderDetailVO.setGrade(rs.getInt("Grade"));
				orderDetailVO.setMemo(rs.getString("Memo"));
				
				list.add(orderDetailVO); // Store the row in the list
	
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
	public List<OrderDetailVO> findByOrderNumber(Integer orderNumber) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_BY_ORDERNUMBER_STMT);
			pstmt.setInt(1, orderNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderDetailVO.setItemNumber(rs.getInt("ItemNumber"));
				orderDetailVO.setItemName(rs.getString("ItemName"));
				orderDetailVO.setItemPrice(rs.getInt("ItemPrice"));
				orderDetailVO.setItemAmount(rs.getInt("ItemAmount"));
				
				orderDetailVO.setGrade(rs.getInt("Grade"));
				orderDetailVO.setMemo(rs.getString("Memo"));
				
				list.add(orderDetailVO); // Store the row in the list
	
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
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"SELECT OrderNumber,ItemNumber,ItemName,ItemPrice,ItemAmount,"
//						+ "Grade,Memo" 
//						+ " FROM OrderDetail order by OrderNumber";
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderDetailVO.setItemNumber(rs.getInt("ItemNumber"));
				orderDetailVO.setItemName(rs.getString("ItemName"));
				orderDetailVO.setItemPrice(rs.getInt("ItemPrice"));
				orderDetailVO.setItemAmount(rs.getInt("ItemAmount"));
				
				orderDetailVO.setGrade(rs.getInt("Grade"));
				orderDetailVO.setMemo(rs.getString("Memo"));
				
				list.add(orderDetailVO); // Store the row in the list
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
		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
		//insert
//		dao.insert(dao);
		
		//update
//		dao.update(dao);
		
		//getall
		List<OrderDetailVO> list = dao.getAll();
		dao.listPrintAll(list);
		
		//findByOrderNumber
		System.out.println("print-findByOrderNumber");
		list = dao.findByOrderNumber(1);
		dao.listPrintAll(list);
		
		//findByItemNumber
		System.out.println("print-findByItemNumber");
		list = dao.findByItemNumber(1);
		dao.listPrintAll(list);
	}

	
	
	private void insert(OrderDetailJDBCDAO dao) {

//		+ "OrderNumber,ItemNumber,ItemName,ItemPrice,ItemAmount,"
//		+ "Memo"

		OrderDetailVO orderDetailVO = new OrderDetailVO();

		orderDetailVO.setOrderNumber(1);
		orderDetailVO.setItemNumber(2);
		orderDetailVO.setItemName("ItemName");
		orderDetailVO.setItemPrice(50);
		orderDetailVO.setItemAmount(100);
		orderDetailVO.setMemo("Memo");

		dao.insert(orderDetailVO);
	}

	private void update(OrderDetailJDBCDAO dao) {
		
		OrderDetailVO orderDetailVO = new OrderDetailVO();

		orderDetailVO.setOrderNumber(1);
		orderDetailVO.setItemNumber(2);
		orderDetailVO.setItemName("ItemName");
		orderDetailVO.setItemPrice(501);
		orderDetailVO.setItemAmount(1001);
		orderDetailVO.setMemo("Memo1");
		orderDetailVO.setGrade(5);

		dao.update(orderDetailVO);
		
	}
	
	
	private void listPrintAll(List<OrderDetailVO> list) {
		for (OrderDetailVO orderDetailVO : list) {
			System.out.print(orderDetailVO.getOrderNumber() + ",");
			System.out.print(orderDetailVO.getItemNumber() + ",");
			System.out.print(orderDetailVO.getItemName() + ",");
			System.out.print(orderDetailVO.getItemPrice() + ",");
			System.out.print(orderDetailVO.getItemAmount()  + ",");
			System.out.print(orderDetailVO.getMemo() + ",");

			System.out.println();
		}
	}

}
