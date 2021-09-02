package com.Merchandise.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utility.Utility;

public class MerchandiseJDBCDAO implements Merchandise_interface{

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	//新增商品資料。
	private static final String INSERT_STMT = 
		"INSERT INTO Merchandise ("
		+ "AccountNumber,ItemName,ItemPrice,Photo,"
		+ "Description,GradeAmount,AverageGrade,IsEnable"
		+") "
		+ "VALUES (?, ?, ?, ?,"
		+ "?, ?, ?, ?)";

	//取得全部
	private static final String GET_ALL_STMT = 
		"SELECT ItemNumber,AccountNumber,ItemName,ItemPrice,Photo,"
		+"Description,GradeAmount,AverageGrade,IsEnable"	
		+ " FROM Merchandise order by ItemNumber";
	
	//依ItemNumber 取得相關內容
	private static final String FIND_BY_ITEMNUMBER_STMT = 
		"SELECT ItemNumber,AccountNumber,ItemName,ItemPrice,Photo,"
		+"Description,GradeAmount,AverageGrade,IsEnable"	
		+ " FROM Merchandise where ItemNumber = ?";
	
	//依AccountNumber 取得相關內容
	private static final String FIND_BY_ACCOUNTNUMBER_STMT = 
		"SELECT ItemNumber,AccountNumber,ItemName,ItemPrice,Photo,"
		+"Description,GradeAmount,AverageGrade,IsEnable"
		+ " FROM Merchandise where AccountNumber = ?";
	
	//刪除(基本上我們不會刪除
	private static final String DELETE_STMT = 
		"DELETE FROM Merchandise where ItemNumber = ?";
	
	//更新單筆資料
	private static final String UPDATE_STMT = 
		"UPDATE Merchandise set AccountNumber=?, ItemName=?, ItemPrice=?, Photo=? ,"
		+ "Description=?, GradeAmount=?, AverageGrade=?,IsEnable=?"
		+ " where ItemNumber = ?";
	
	@Override
	public void insert(MerchandiseVO merchandiseVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
///			"INSERT INTO Merchandise ("
//			+ "AccountNumber,ItemName,ItemPrice,Photo,"
//			+ "Description,GradeAmount,AverageGrade,IsEnable,"
			
			pstmt.setInt(1, merchandiseVO.getAccountNumber());
			pstmt.setString(2, merchandiseVO.getItemName());
			pstmt.setInt(3, merchandiseVO.getItemPrice());
			pstmt.setString(4, merchandiseVO.getPhoto());

			pstmt.setString(5, merchandiseVO.getDescription());
			pstmt.setInt(6, merchandiseVO.getGradeAmount());
			pstmt.setDouble(7, merchandiseVO.getAverageGrade());
			pstmt.setBoolean(8, merchandiseVO.getIsEnable());
			
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
	public void update(MerchandiseVO merchandiseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

//			"UPDATE Merchandise set AccountNumber=?, ItemName=?, ItemPrice=?, Photo=? ,"
//			+ "Description=?, GradeAmount=?, AverageGrade=?,IsEnable=?"
//			+ " where ItemNumber = ?";
			
			pstmt.setInt(1, merchandiseVO.getAccountNumber());
			pstmt.setString(2, merchandiseVO.getItemName());
			pstmt.setInt(3, merchandiseVO.getItemPrice());
			pstmt.setString(4, merchandiseVO.getPhoto());

			pstmt.setString(5, merchandiseVO.getDescription());
			pstmt.setInt(6, merchandiseVO.getGradeAmount());
			pstmt.setDouble(7, merchandiseVO.getAverageGrade());
			pstmt.setBoolean(8, merchandiseVO.getIsEnable());
			
			pstmt.setInt(9, merchandiseVO.getItemNumber());

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
	public void delete(Integer itemNumber) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, itemNumber);

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
	public MerchandiseVO findByItemNumber(Integer itemNumber) {

		MerchandiseVO merchandiseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ITEMNUMBER_STMT);
			pstmt.setInt(1, itemNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"SELECT ItemNumber,AccountNumber,ItemName,ItemPrice,Photo,"
//						+"Description,GradeAmount,AverageGrade,IsEnable"	
//						+ " FROM Merchandise where ItemNumber = ?";
				merchandiseVO = new MerchandiseVO();
				merchandiseVO.setItemNumber(rs.getInt("ItemNumber"));
				merchandiseVO.setAccountNumber(rs.getInt("AccountNumber"));
				merchandiseVO.setItemName(rs.getString("ItemName"));
				merchandiseVO.setItemPrice(rs.getInt("ItemPrice"));
				merchandiseVO.setPhoto(rs.getString("Photo"));
				
				
				merchandiseVO.setDescription(rs.getString("Description"));
				merchandiseVO.setGradeAmount(rs.getInt("GradeAmount"));
				merchandiseVO.setAverageGrade(rs.getDouble("AverageGrade"));
				merchandiseVO.setIsEnable(rs.getBoolean("IsEnable"));
	
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
		return merchandiseVO;
	}

	@Override
	public List<MerchandiseVO> findByAccountNumber(Integer accountNumber) {
		List<MerchandiseVO> list = new ArrayList<MerchandiseVO>();
		MerchandiseVO merchandiseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACCOUNTNUMBER_STMT);
			pstmt.setInt(1, accountNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"SELECT ItemNumber,AccountNumber,ItemName,ItemPrice,Photo,"
//						+"Description,GradeAmount,AverageGrade,IsEnable"	
//						+ " FROM Merchandise where ItemNumber = ?";
				merchandiseVO = new MerchandiseVO();
				merchandiseVO.setItemNumber(rs.getInt("ItemNumber"));
				merchandiseVO.setAccountNumber(rs.getInt("AccountNumber"));
				merchandiseVO.setItemName(rs.getString("ItemName"));
				merchandiseVO.setItemPrice(rs.getInt("ItemPrice"));
				merchandiseVO.setPhoto(rs.getString("Photo"));
				
				
				merchandiseVO.setDescription(rs.getString("Description"));
				merchandiseVO.setGradeAmount(rs.getInt("GradeAmount"));
				merchandiseVO.setAverageGrade(rs.getDouble("AverageGrade"));
				merchandiseVO.setIsEnable(rs.getBoolean("IsEnable"));
	
				list.add(merchandiseVO); // Store the row in the list
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
	public List<MerchandiseVO> getAll() {
		List<MerchandiseVO> list = new ArrayList<MerchandiseVO>();
		MerchandiseVO merchandiseVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"SELECT ItemNumber,AccountNumber,ItemName,ItemPrice,Photo,"
//						+"Description,GradeAmount,AverageGrade,IsEnable"	
//						+ " FROM Merchandise where ItemNumber = ?";
				merchandiseVO = new MerchandiseVO();
				merchandiseVO.setItemNumber(rs.getInt("ItemNumber"));
				merchandiseVO.setAccountNumber(rs.getInt("AccountNumber"));
				merchandiseVO.setItemName(rs.getString("ItemName"));
				merchandiseVO.setItemPrice(rs.getInt("ItemPrice"));
				merchandiseVO.setPhoto(rs.getString("Photo"));
				
				
				merchandiseVO.setDescription(rs.getString("Description"));
				merchandiseVO.setGradeAmount(rs.getInt("GradeAmount"));
				merchandiseVO.setAverageGrade(rs.getDouble("AverageGrade"));
				merchandiseVO.setIsEnable(rs.getBoolean("IsEnable"));
	
				list.add(merchandiseVO); // Store the row in the list
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
		MerchandiseJDBCDAO dao = new MerchandiseJDBCDAO();
		
		List<MerchandiseVO> list = null;
		list=dao.getAll();
		int amount = list.size() ;
		int serialNumber=amount + 1 ; 
		
		String postFixWording="_"+serialNumber;
		
		//insert
		dao.insert(dao,postFixWording);
		
		//update
//		dao.updae(dao);
		
		//getall
		list = dao.getAll();
		dao.listPrintAll(list);
		
		//getByAcccountNumber
		System.out.println("-----getAllByAccountNumber_Print-----");
		list = dao.findByAccountNumber(serialNumber);
		dao.listPrintAll(list);
		
		//findByItemNumber
		System.out.println("-----findByItemNumber-----");
		list.clear();
		list.add(dao.findByItemNumber(1));
		dao.listPrintAll(list);
		
		System.out.println("-----delete_-----");
//		dao.delete(1);
	}

	private void insert(MerchandiseJDBCDAO dao,String postFixWording) {
		
///		"INSERT INTO Merchandise ("
//		+ "AccountNumber,ItemName,ItemPrice,Photo,"
//		+ "Description,GradeAmount,AverageGrade,IsEnable,"
		
		MerchandiseVO merchandiseVO = new MerchandiseVO();
		merchandiseVO.setAccountNumber(1);
		merchandiseVO.setItemName("ItemName"+postFixWording);
		merchandiseVO.setItemPrice(50);
		merchandiseVO.setPhoto("");

		merchandiseVO.setDescription("Description"+postFixWording);
		merchandiseVO.setGradeAmount(100);
		merchandiseVO.setAverageGrade(4.5);
		merchandiseVO.setIsEnable(false);

		dao.insert(merchandiseVO);
	}
	
	private void updae(MerchandiseJDBCDAO dao) {
		MerchandiseVO merchandiseVO = new MerchandiseVO();
		merchandiseVO.setItemNumber(1);
		merchandiseVO.setAccountNumber(1);
		merchandiseVO.setItemName("ItemName");
		merchandiseVO.setItemPrice(50);
		merchandiseVO.setPhoto("");

		merchandiseVO.setDescription("Description");
		merchandiseVO.setGradeAmount(100);
		merchandiseVO.setAverageGrade(4.5);
		merchandiseVO.setIsEnable(false);

		dao.update(merchandiseVO);
		
	}
	
	private void listPrintAll (List<MerchandiseVO> list) {
		for (MerchandiseVO merchandiseVO : list) {
//			+ "ItemNumber,AccountNumber,ItemName,ItemPrice,Photo,"
//			+ "Description,GradeAmount,AverageGrade,IsEnable,"
			System.out.print(merchandiseVO.getItemNumber() + ",");
			System.out.print(merchandiseVO.getAccountNumber() + ",");
			System.out.print(merchandiseVO.getItemName() + ",");
			System.out.print(merchandiseVO.getItemPrice() + ",");
			System.out.print(merchandiseVO.getPhoto() + ",");

			System.out.print(merchandiseVO.getDescription() + ",");
			System.out.print(merchandiseVO.getGradeAmount() + ",");
			System.out.print(merchandiseVO.getAverageGrade() + ",");
			System.out.print(merchandiseVO.getIsEnable() + ",");

			System.out.println();
		}
	}
	
}
