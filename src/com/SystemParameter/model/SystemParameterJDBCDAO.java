package com.SystemParameter.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Admin.model.AdminJDBCDAO;
import com.Admin.model.AdminVO;
import com.utility.Utility;

public class SystemParameterJDBCDAO implements SystemParameterDAO_interface{

	
	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	//新增
	private static final String INSERT_STMT = 
		"INSERT INTO SystemParameter (TypeName,Code,Description) VALUES (?, ?, ?)";
	
	//取得全部
	private static final String GET_ALL_STMT = 
		"SELECT SerialNumber,TypeName,Code,Description FROM SystemParameter order by SerialNumber";
	
	//依typeName 取得相關內容
	private static final String FIND_BY_TYPENAME_STMT = 
		"SELECT SerialNumber,TypeName,Code,Description FROM SystemParameter where TypeName = ?";
	
	//刪除
	private static final String DELETE_STMT = 
		"DELETE FROM SystemParameter where SerialNumber = ?";
	
	//更新
	private static final String UPDATE_STMT = 
		"UPDATE SystemParameter set TypeName=?, Code=?, Description=? where SerialNumber = ?";
	
	@Override
	public void insert(SystemParameterVO systemParameterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);

			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, systemParameterVO.getTypeName());
			pstmt.setString(2, systemParameterVO.getCode());
			pstmt.setString(3, systemParameterVO.getDescription());
	
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
	public void update(SystemParameterVO systemParameterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, systemParameterVO.getTypeName());
			pstmt.setString(2, systemParameterVO.getCode());
			pstmt.setString(3, systemParameterVO.getDescription());
			pstmt.setInt(4,systemParameterVO.getSerialNumber());

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
	public void delete(int serialNumber) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, serialNumber);

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
	public List<SystemParameterVO> findByTypeName(String typeName) {
		List<SystemParameterVO> list = new ArrayList<SystemParameterVO>();
		SystemParameterVO systemParameterVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_TYPENAME_STMT);

			pstmt.setString(1, typeName);

			rs = pstmt.executeQuery();

			while (rs.next()) {
	
				systemParameterVO = new SystemParameterVO();
				systemParameterVO.setSerialNumber(rs.getInt("SerialNumber"));
				systemParameterVO.setTypeName(rs.getString("TypeName"));
				systemParameterVO.setCode(rs.getString("Code"));
				systemParameterVO.setDescription(rs.getString("Description"));
				list.add(systemParameterVO);
			}

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
		return list;
	}
	@Override
	public List<SystemParameterVO> getAll() {
		List<SystemParameterVO> list = new ArrayList<SystemParameterVO>();
		SystemParameterVO systemParameterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				systemParameterVO = new SystemParameterVO();
				systemParameterVO.setSerialNumber(rs.getInt("SerialNumber"));
				systemParameterVO.setCode(rs.getString("Code"));
				systemParameterVO.setTypeName(rs.getString("TypeName"));
				systemParameterVO.setDescription(rs.getString("Description"));	
				list.add(systemParameterVO);

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
		
		//insert --test ok
		SystemParameterJDBCDAO dao= new SystemParameterJDBCDAO();
		
		SystemParameterVO systemParameterVO_Insert = new SystemParameterVO();
		
		List<SystemParameterVO> list = null;
		
		String typeName ="Test";
		list = dao.findByTypeName("typeName");
		int amount = list.size();
		
		systemParameterVO_Insert.setTypeName(typeName);
		systemParameterVO_Insert.setCode("test"+amount);
		systemParameterVO_Insert.setDescription(systemParameterVO_Insert.getCode()+"Descritpion");
		
		dao.insert(systemParameterVO_Insert);
		
		//update -- test ok
		SystemParameterVO systemParameterVO_Update = new SystemParameterVO();
		systemParameterVO_Update.setSerialNumber(1);
		systemParameterVO_Update.setTypeName("ModifyTypeName");
		systemParameterVO_Update.setCode("ModifyCode");
		systemParameterVO_Update.setDescription("ModifyDescription");
		dao.update(systemParameterVO_Update);
		
		//delete  -- test ok
		
//		dao.delete(24);
		
		//查詢 by TypeName-test ok
	
		System.out.println("-----------查詢by TypeName-Start----------");
		
		dao.listPrintAll(dao.findByTypeName(typeName));

		System.out.println("-----------查詢by TypeName-End----------");
		
		//查詢 all test ok
		System.out.println("-----------getAll-Start----------");
		
		dao.listPrintAll(dao.getAll()); 

		System.out.println("-----------getAll-End----------");
	}
	
	private void listPrintAll(List<SystemParameterVO> list) {

		for (SystemParameterVO systemParameterVO : list) {
			System.out.print(systemParameterVO.getSerialNumber() + ",");
			System.out.print(systemParameterVO.getTypeName() + ",");
			System.out.print(systemParameterVO.getCode() + ",");
			System.out.print(systemParameterVO.getDescription()+ ",");

			System.out.println();
		}
	}
	
	
}
