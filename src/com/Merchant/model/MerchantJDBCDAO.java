package com.Merchant.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


import com.utility.EnrollmentStatus;
import com.utility.Utility;
import com.utility.GetAutoIncrement;
import com.utility.MassFoodTableName;

public class MerchantJDBCDAO implements MerchantDAO_interface {

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	//新增註冊基本資料。
	private static final String INSERT_BASE_INFORMATION_STMT = 
		"INSERT INTO Merchant ("
		+ "Email,Password,IdentityNumber,Name,"
		+ "BankCode,BankAccount,BankAccountName,ContactPersonName,ContactPersonEmail,"
		+"ContactPersonIdentityNumber,IsEnable,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
		+ "Status"
		+ ") "
		+ "VALUES (?, ?, ?, ?,"
		+ "?, ?, ?, ?, ?,"
		+ "?, ?, ?, ?, ?,"
		+ "?)";
	

	//新增(更新)營業資訊，因為新增 營業資訊時，前提一定會有基本資料，這樣才有AccountNumber
	
	//取得全部
	private static final String GET_ALL_STMT = 
		"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
		+ "BankCode,BankAccount,BankAccountName,ContactPersonName,ContactPersonEmail,"
		+ "ContactPersonIdentityNumber,IsEnable,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
		+ "Status,IsReleaseToMarket,Address,ZipCode,FoodType,"
		+ "Picture,Description,Weekday,BusinessHour,LandlinePhone,"
		+ "ClosedDate"
		+ " FROM Merchant order by AccountNumber";
	
	//依AccountNumber 取得相關內容
	private static final String FIND_BY_ACCOUNTNUMBER_STMT = 
		"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
		+ "BankCode,BankAccount,BankAccountName,ContactPersonName,ContactPersonEmail,"
		+ "ContactPersonIdentityNumber,IsEnable,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
		+ "Status,IsReleaseToMarket,Address,ZipCode,FoodType,"
		+ "Picture,Description,Weekday,BusinessHour,LandlinePhone,"
		+ "ClosedDate"
		+ " FROM Merchant where AccountNumber = ?";
	
	//依Email 取得相關內容
	private static final String FIND_BY_EMAIL_STMT = 
		"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
		+ "BankCode,BankAccount,BankAccountName,ContactPersonName,ContactPersonEmail,"
		+ "ContactPersonIdentityNumber,IsEnable,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
		+ "Status,IsReleaseToMarket,Address,ZipCode,FoodType,"
		+ "Picture,Description,Weekday,BusinessHour,LandlinePhone,"
		+ "ClosedDate"
		+ " FROM Merchant where EMAIL = ?";
	
	
	//刪除(基本上我們不會刪除
	private static final String DELETE_STMT = 
		"DELETE FROM Merchant where AccountNumber = ?";
	
	//更新全部
	private static final String UPDATE_STMT = 
			"UPDATE Merchant set Email=?, Password=?, IdentityNumber=?, Name=? ,"
			+ "BankCode=?, BankAccount=?, BankAccountName=?, ContactPersonName=?, ContactPersonEmail=? ,"
			+ "ContactPersonIdentityNumber=?, IsEnable=?, EnrollmentDate = ? ,LastUpdateAccountNumber=?, LastUpdateDatetime=?, "
			+ "Status=?,IsReleaseToMarket=?, Address=?, ZipCode=?, FoodType=?,"
			+ "Picture=?,Description=?, Weekday=?, BusinessHour=?, LandlinePhone=?,"
			+ "ClosedDate=?"
			+ " where AccountNumber = ?";
	
	//更新基本資料
	private static final String UPDATE_BASE_INFORMATION_STMT = 
		"UPDATE Merchant set Password=?, IdentityNumber=?, Name=? ,"
		+ "BankCode=?, BankAccount=?, BankAccountName=?, ContactPersonName=?, ContactPersonEmail=? ,"
		+ "ContactPersonIdentityNumber=?, IsEnable=?, LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=?"
		+ " where AccountNumber = ?";
	
	//更新營業資訊
	private static final String UPDATE_BUSINESS_INFORMATION_STMT = 
			"UPDATE Merchant set LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=?,"
			+ "IsReleaseToMarket=?, Address=?, ZipCode=?, FoodType=?, Picture=?,"
			+ "Description=?, Weekday=?, BusinessHour=?, LandlinePhone=?, ClosedDate=?"
			+ " where AccountNumber = ?";
	
	//管理員審核(管理員)
	private static final String UPDATE_ADMIN_AUTHENTICATION_STMT =
			"UPDATE Merchant set LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=? ,EnrollmentDate=?"
			+ " where AccountNumber = ?";
	
	@Override
	public void insert(MerchantVO merchantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(INSERT_BASE_INFORMATION_STMT);
//			"INSERT INTO Merchant ("
//			+ "Email,Password,IdentityNumber,Name,"
//			+ "BankCode,BankAccount,BankAccountName,ContactPersonName,ContactPersonEmail,"
//			+"ContactPersonIdentityNumber,IsEnable,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
//			+ "Status"
//			+ ") "
//			+ "VALUES (?, ?, ?, ?,"
//			+ "?, ?, ?, ?, ?,"
//			+ "?, ?, ?, ?, ?,"
//			+ "?)";
			pstmt.setString(1, merchantVO.getEmail());
			pstmt.setString(2, merchantVO.getPassword());
			pstmt.setString(3, merchantVO.getIdentityNumber());
			pstmt.setString(4, merchantVO.getName());

			pstmt.setString(5, merchantVO.getBankCode());
			pstmt.setString(6, merchantVO.getBankAccount());
			pstmt.setString(7, merchantVO.getBankAccountName());
			pstmt.setString(8, merchantVO.getContactPersonName());
			pstmt.setString(9, merchantVO.getContactPersonEmail());
			
			pstmt.setString(10, merchantVO.getContactPersonIdentityNumber());
			pstmt.setBoolean(11, merchantVO.getIsEnable());
			pstmt.setDate(12, merchantVO.getEnrollmentDate());
			pstmt.setInt(13, merchantVO.getLastUpdateAccountNumber());
			pstmt.setTimestamp(14, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setInt(15, merchantVO.getStatus());
			

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
	public void updateBusinessInformation(MerchantVO merchantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_BUSINESS_INFORMATION_STMT);
			
//			"UPDATE Merchant set LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=?,"
//			+ "IsReleaseToMarket=?, Address=?, ZipCode=?, FoodType=?, Picture=?,"
//			+ "Description=?, Weekday=?, BusinessHour=?, LandlinePhone=? ,ClosedDate=?"
//			+ " where AccountNumber = ?";

			
			pstmt.setInt(1, merchantVO.getLastUpdateAccountNumber());
			pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setInt(3, merchantVO.getStatus());
			
			pstmt.setBoolean(4, merchantVO.getIsReleaseToMarket());
			pstmt.setString(5, merchantVO.getAddress());
			pstmt.setString(6, merchantVO.getZipCode());
			pstmt.setInt(7, merchantVO.getFoodType());
			pstmt.setString(8, merchantVO.getPicture());
			
			pstmt.setString(9, merchantVO.getDescription());
			pstmt.setString(10, merchantVO.getWeekday());
			pstmt.setString(11, merchantVO.getBusinessHour());
			pstmt.setString(12, merchantVO.getLandlinePhone());
			pstmt.setString(13, merchantVO.getClosedDate());

			pstmt.setInt(14, merchantVO.getAccountNumber());
			
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
	public void updateBaseInformation(MerchantVO merchantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_BASE_INFORMATION_STMT);
			
//			"UPDATE Merchant set Password=?, IdentityNumber=?, Name=? ,"
//			+ "BankCode=?, BankAccount=?, BankAccountName=?, ContactPersonName=?, ContactPersonEmail=? ,"
//			+ "ContactPersonIdentityNumber=?, IsEnable=?, LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=?"
//			+ " where AccountNumber = ?";
			
//			pstmt.setString(1, merchantVO.getEmail()); //mail是後續使用的key值不允許更新
			pstmt.setString(1, merchantVO.getPassword());
			pstmt.setString(2, merchantVO.getIdentityNumber());
			pstmt.setString(3, merchantVO.getName());

			pstmt.setString(4, merchantVO.getBankCode());
			pstmt.setString(5, merchantVO.getBankAccount());
			pstmt.setString(6, merchantVO.getBankAccountName());
			pstmt.setString(7, merchantVO.getContactPersonName());
			pstmt.setString(8, merchantVO.getContactPersonEmail());
			
			pstmt.setString(9, merchantVO.getContactPersonIdentityNumber());
			pstmt.setBoolean(10, merchantVO.getIsEnable());
			pstmt.setInt(11, merchantVO.getLastUpdateAccountNumber());
			pstmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setInt(13, merchantVO.getStatus());
			
			pstmt.setInt(14, merchantVO.getAccountNumber());
			
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
	public void update(MerchantVO merchantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
//			"UPDATE Merchant set Email=?, Password=?, IdentityNumber=?, Name=? ,"
//			+ "BankCode=?, BankAccount=?, BankAccountName=?, ContactPersonName=?, ContactPersonEmail=? ,"
//			+ "ContactPersonIdentityNumber=?, IsEnable=?, EnrollmentDate = ? ,LastUpdateAccountNumber=?, LastUpdateDatetime=?, "
//			+ "Status=?,IsReleaseToMarket=?, Address=?, ZipCode=?, FoodType=?,"
//			+ "Picture=?,Description=?, Weekday=?, BusinessHour=?, LandlinePhone=?,"
//			+ "ClosedDate=?"
//			+ " where AccountNumber = ?";
			
			pstmt.setString(1, merchantVO.getEmail()); 
			pstmt.setString(2, merchantVO.getPassword());
			pstmt.setString(3, merchantVO.getIdentityNumber());
			pstmt.setString(4, merchantVO.getName());

			pstmt.setString(5, merchantVO.getBankCode());
			pstmt.setString(6, merchantVO.getBankAccount());
			pstmt.setString(7, merchantVO.getBankAccountName());
			pstmt.setString(8, merchantVO.getContactPersonName());
			pstmt.setString(9, merchantVO.getContactPersonEmail());
			
			pstmt.setString(10, merchantVO.getContactPersonIdentityNumber());
			pstmt.setBoolean(11, merchantVO.getIsEnable());
			pstmt.setInt(12, merchantVO.getLastUpdateAccountNumber());
			pstmt.setDate(13, merchantVO.getEnrollmentDate());
			pstmt.setTimestamp(14, new java.sql.Timestamp(new java.util.Date().getTime()));
			
			pstmt.setInt(15, merchantVO.getStatus());
			pstmt.setBoolean(16, merchantVO.getIsReleaseToMarket());
			pstmt.setString(17, merchantVO.getAddress());
			pstmt.setString(18, merchantVO.getZipCode());
			pstmt.setInt(19, merchantVO.getFoodType());
			pstmt.setString(20, merchantVO.getPicture());
			
			pstmt.setString(21, merchantVO.getDescription());
			pstmt.setString(22, merchantVO.getWeekday());
			pstmt.setString(23, merchantVO.getBusinessHour());
			pstmt.setString(24, merchantVO.getLandlinePhone());
			pstmt.setString(25, merchantVO.getClosedDate());
			
			pstmt.setInt(26, merchantVO.getAccountNumber());
			
			
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
	public void updateAdminAuthentication(MerchantVO merchantVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_ADMIN_AUTHENTICATION_STMT);
			
//			"UPDATE Merchant set LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=?,EnrollmentDate=?"
//			+ " where AccountNumber = ?";
			
			pstmt.setInt(1, merchantVO.getLastUpdateAccountNumber());
			pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setInt(3, merchantVO.getStatus());
			pstmt.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setInt(5, merchantVO.getAccountNumber());
			
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
	public List<MerchantVO> getAll() {
		List<MerchantVO> list = new ArrayList<MerchantVO>();
		MerchantVO merchantVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
//				"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
//						+ "BankCode,BankAccount,BankAccountName,ContactPersonName,ContactPersonEmail,"
//						+ "ContactPersonIdentityNumber,IsEnable,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
//						+ "Status,IsReleaseToMarket,Address,ZipCode,FoodType,"
//						+ "Picture,Description,Weekday,BusinessHour,LandlinePhone,"
//						+ "ClosedDate"
//						+ " FROM Merchant order by AccountNumber";
				
				merchantVO = new MerchantVO();
				merchantVO.setAccountNumber(rs.getInt("AccountNumber"));
				merchantVO.setEmail(rs.getString("Email"));
				merchantVO.setPassword(rs.getString("Password"));
				merchantVO.setIdentityNumber(rs.getString("IdentityNumber"));
				merchantVO.setName(rs.getString("Name"));
				
				merchantVO.setBankCode(rs.getString("BankCode"));
				merchantVO.setBankAccount(rs.getString("BankAccount"));
				merchantVO.setBankAccountName(rs.getString("BankAccountName"));
				merchantVO.setContactPersonName(rs.getString("ContactPersonName"));
				merchantVO.setContactPersonEmail(rs.getString("ContactPersonEmail"));
				
				merchantVO.setContactPersonIdentityNumber(rs.getString("ContactPersonIdentityNumber"));
				merchantVO.setIsEnable(rs.getBoolean("IsEnable"));
				merchantVO.setEnrollmentDate(rs.getDate("EnrollmentDate"));
				merchantVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
				merchantVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
				
				merchantVO.setStatus(rs.getInt("Status"));
				merchantVO.setIsReleaseToMarket(rs.getBoolean("IsReleaseToMarket"));
				merchantVO.setAddress(rs.getString("Address"));
				merchantVO.setZipCode(rs.getString("ZipCode"));
				merchantVO.setFoodType(rs.getInt("FoodType"));
				
				merchantVO.setPicture(rs.getString("Picture"));
				merchantVO.setDescription(rs.getString("Description"));
				merchantVO.setWeekday(rs.getString("Weekday"));
				merchantVO.setBusinessHour(rs.getString("BusinessHour"));
				merchantVO.setLandlinePhone(rs.getString("LandlinePhone"));
				
				merchantVO.setClosedDate(rs.getString("ClosedDate"));
				
				list.add(merchantVO); // Store the row in the list
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
	public MerchantVO findByAccountNumber(Integer accountNumber) {
		
		MerchantVO merchantVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ACCOUNTNUMBER_STMT);

			pstmt.setInt(1, accountNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				merchantVO = new MerchantVO();
				merchantVO.setAccountNumber(rs.getInt("AccountNumber"));
				merchantVO.setEmail(rs.getString("Email"));
				merchantVO.setPassword(rs.getString("Password"));
				merchantVO.setIdentityNumber(rs.getString("IdentityNumber"));
				merchantVO.setName(rs.getString("Name"));
				
				merchantVO.setBankCode(rs.getString("BankCode"));
				merchantVO.setBankAccount(rs.getString("BankAccount"));
				merchantVO.setBankAccountName(rs.getString("BankAccountName"));
				merchantVO.setContactPersonName(rs.getString("ContactPersonName"));
				merchantVO.setContactPersonEmail(rs.getString("ContactPersonEmail"));
				
				merchantVO.setContactPersonIdentityNumber(rs.getString("ContactPersonIdentityNumber"));
				merchantVO.setIsEnable(rs.getBoolean("IsEnable"));
				merchantVO.setEnrollmentDate(rs.getDate("EnrollmentDate"));
				merchantVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
				merchantVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
				
				merchantVO.setStatus(rs.getInt("Status"));
				merchantVO.setIsReleaseToMarket(rs.getBoolean("IsReleaseToMarket"));
				merchantVO.setAddress(rs.getString("Address"));
				merchantVO.setZipCode(rs.getString("ZipCode"));
				merchantVO.setFoodType(rs.getInt("FoodType"));
				
				merchantVO.setPicture(rs.getString("Picture"));
				merchantVO.setDescription(rs.getString("Description"));
				merchantVO.setWeekday(rs.getString("Weekday"));
				merchantVO.setBusinessHour(rs.getString("BusinessHour"));
				merchantVO.setLandlinePhone(rs.getString("LandlinePhone"));
				
				merchantVO.setClosedDate(rs.getString("ClosedDate"));

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
		return merchantVO;
	}
	
	@Override
	public MerchantVO findByEmail(String email) {

		MerchantVO merchantVO=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_EMAIL_STMT);

			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
		
				merchantVO = new MerchantVO();
				merchantVO.setAccountNumber(rs.getInt("AccountNumber"));
				merchantVO.setEmail(rs.getString("Email"));
				merchantVO.setPassword(rs.getString("Password"));
				merchantVO.setIdentityNumber(rs.getString("IdentityNumber"));
				merchantVO.setName(rs.getString("Name"));
				
				merchantVO.setBankCode(rs.getString("BankCode"));
				merchantVO.setBankAccount(rs.getString("BankAccount"));
				merchantVO.setBankAccountName(rs.getString("BankAccountName"));
				merchantVO.setContactPersonName(rs.getString("ContactPersonName"));
				merchantVO.setContactPersonEmail(rs.getString("ContactPersonEmail"));
				
				merchantVO.setContactPersonIdentityNumber(rs.getString("ContactPersonIdentityNumber"));
				merchantVO.setIsEnable(rs.getBoolean("IsEnable"));
				merchantVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
				merchantVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
				merchantVO.setStatus(rs.getInt("Status"));
				
				merchantVO.setIsReleaseToMarket(rs.getBoolean("IsReleaseToMarket"));
				merchantVO.setAddress(rs.getString("Address"));
				merchantVO.setZipCode(rs.getString("ZipCode"));
				merchantVO.setFoodType(rs.getInt("FoodType"));
				merchantVO.setPicture(rs.getString("Picture"));
				
				merchantVO.setDescription(rs.getString("Description"));
				merchantVO.setWeekday(rs.getString("Weekday"));
				merchantVO.setBusinessHour(rs.getString("BusinessHour"));
				merchantVO.setLandlinePhone(rs.getString("LandlinePhone"));
				merchantVO.setClosedDate(rs.getString("ClosedDate"));

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
		
		return merchantVO;
		
	}
	
	
	@Override
	public void delete(Integer accountNumber) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, accountNumber);

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
	
	
	
	public static void main(String[] args) {
		
		MerchantJDBCDAO dao= new MerchantJDBCDAO();
		List<MerchantVO> list = null;
		//insert --test ok-這邊自動增益的流水號後續再問一下老師
		
		list=dao.getAll();
		int amount = list.size() ;
		int serialNumber=amount + 1 ; 
		
		String pseudoEmail="Merchant"+serialNumber+"@gmail.com";
		String postFixWording="_"+serialNumber;
		
		GetAutoIncrement autoIncrement = new GetAutoIncrement();
		int autoIncrementNumber=autoIncrement.getAutoIncremenetNumber(MassFoodTableName.MERCHANT.getTableName());
		System.out.println(autoIncrementNumber);
		
		
		dao.insert(dao,autoIncrementNumber,pseudoEmail,postFixWording);
	
		//更新營業資訊 ，insert ok -->這邊還差json填入的方式
		postFixWording=postFixWording+"_u";
		dao.updateBusinessInformation(dao,autoIncrementNumber,postFixWording);
		
		
		//更新基本資料 -- test ok
		
		dao.updateBaseInformation(dao,autoIncrementNumber,postFixWording);
		
		//更新全部資料
		
		//update_管理員驗證資料updateAdminAuthentication --test ok
		MerchantVO merchantVO_Update_AdminAuthentication = new MerchantVO();
		merchantVO_Update_AdminAuthentication.setAccountNumber(3);
		merchantVO_Update_AdminAuthentication.setStatus(EnrollmentStatus.REJECT.getCode());
		merchantVO_Update_AdminAuthentication.setLastUpdateAccountNumber(1);
		merchantVO_Update_AdminAuthentication.setEnrollmentDate(null);
		dao.updateAdminAuthentication(merchantVO_Update_AdminAuthentication);
		
		
		//get all
		
		System.out.println("-----------getAll-Start----------");
		list = dao.getAll();
		dao.listPrintAll(list);
		System.out.println("-----------getAll-End----------");
		
		
		System.out.println("-----------merchantVOFindByEmail-start ----------");
		list.clear();
		list.add(dao.findByEmail(pseudoEmail)); 
		dao.listPrintAll(list);
		System.out.println("-----------merchantVOFindByEmail-end ----------");
		
		
		System.out.println("-----------merchantVOFindByAccountNumber-start ----------");
		list.clear();
		list.add(dao.findByAccountNumber(1));
		dao.listPrintAll(list);
		System.out.println("-----------merchantVOFindByAccountNumber-end ----------");
		
//		刪除 --test Ok
//		dao.delete(2);	
	}
	
	
	
	
	private void insert(MerchantJDBCDAO dao,int autoIncrementNumber,String pseudoEmail,String postFixWording) {

		MerchantVO merchantVO_Insert_Base_Information = new MerchantVO();
		merchantVO_Insert_Base_Information.setEmail(pseudoEmail);
		merchantVO_Insert_Base_Information.setIdentityNumber("01234567");
		merchantVO_Insert_Base_Information.setPassword("password");
		merchantVO_Insert_Base_Information.setName("setName"+postFixWording);

		merchantVO_Insert_Base_Information.setBankCode("808");
		merchantVO_Insert_Base_Information.setBankAccount("BankAccount"+postFixWording);
		merchantVO_Insert_Base_Information.setBankAccountName("BankAccountName");
		merchantVO_Insert_Base_Information.setContactPersonName("ContactPersonName");
		merchantVO_Insert_Base_Information.setContactPersonEmail("ContactPersonEmail");
		
		merchantVO_Insert_Base_Information.setContactPersonIdentityNumber("A123456789");
		merchantVO_Insert_Base_Information.setIsEnable(false);
		merchantVO_Insert_Base_Information.setLastUpdateAccountNumber(autoIncrementNumber);
		merchantVO_Insert_Base_Information.setStatus(EnrollmentStatus.WAITING_ADMIN_REVIEW.getCode());
		merchantVO_Insert_Base_Information.setEnrollmentDate(null);
		dao.insert(merchantVO_Insert_Base_Information);
	}
	
	
	private void updateBusinessInformation(MerchantJDBCDAO dao, int serialNumber,String postFixWording) {
		MerchantVO merchantVO_Update_Business_Information = new MerchantVO();
		
		merchantVO_Update_Business_Information.setAccountNumber(serialNumber);
		merchantVO_Update_Business_Information.setLastUpdateAccountNumber(1);
		merchantVO_Update_Business_Information.setStatus(3);
		merchantVO_Update_Business_Information.setIsReleaseToMarket(false);
		
		merchantVO_Update_Business_Information.setAddress("Address"+postFixWording);
		merchantVO_Update_Business_Information.setZipCode("101");
		merchantVO_Update_Business_Information.setFoodType(5);
		merchantVO_Update_Business_Information.setPicture("");
		
		merchantVO_Update_Business_Information.setPicture("Description"+postFixWording);
		merchantVO_Update_Business_Information.setWeekday(null);
		merchantVO_Update_Business_Information.setBusinessHour(null);
		merchantVO_Update_Business_Information.setLandlinePhone("0532123456");
		
		merchantVO_Update_Business_Information.setClosedDate(null);
		dao.updateBusinessInformation(merchantVO_Update_Business_Information);
		
	}
	
	private void updateBaseInformation(MerchantJDBCDAO dao,int autoIncrementNumber,String postFixWording) {
		MerchantVO merchantVO_Update_Base_Information = new MerchantVO();
		merchantVO_Update_Base_Information.setIdentityNumber("01234567");
		merchantVO_Update_Base_Information.setPassword("password");
		merchantVO_Update_Base_Information.setName("setName"+ postFixWording);

		merchantVO_Update_Base_Information.setBankCode("808");
		merchantVO_Update_Base_Information.setBankAccount("update"+ postFixWording);
		merchantVO_Update_Base_Information.setBankAccountName("update"+postFixWording);
		merchantVO_Update_Base_Information.setContactPersonName("update"+postFixWording);
		merchantVO_Update_Base_Information.setContactPersonEmail("update");
		
		merchantVO_Update_Base_Information.setContactPersonIdentityNumber("update");
		merchantVO_Update_Base_Information.setIsEnable(false);
		merchantVO_Update_Base_Information.setLastUpdateAccountNumber(1);
		merchantVO_Update_Base_Information.setStatus(EnrollmentStatus.WAITING_ADMIN_REVIEW.getCode());
		merchantVO_Update_Base_Information.setAccountNumber(autoIncrementNumber);
		dao.updateBaseInformation(merchantVO_Update_Base_Information);
	}
	
	private void update(MerchantJDBCDAO dao,int autoIncrementNumber,String pseudoEmail) {
		MerchantVO merchantVO_Update = new MerchantVO();
		
//		"UPDATE Merchant set Email=?, Password=?, IdentityNumber=?, Name=? ,"
//		+ "BankCode=?, BankAccount=?, BankAccountName=?, ContactPersonName=?, ContactPersonEmail=? ,"
//		+ "ContactPersonIdentityNumber=?, IsEnable=?, EnrollmentDate = ? ,LastUpdateAccountNumber=?, LastUpdateDatetime=?, "
//		+ "Status=?,IsReleaseToMarket=?, Address=?, ZipCode=?, FoodType=?,"
//		+ "Picture=?,Description=?, Weekday=?, BusinessHour=?, LandlinePhone=?,"
//		+ "ClosedDate=?"
//		+ " where AccountNumber = ?";
		
		merchantVO_Update.setAccountNumber(autoIncrementNumber);
		merchantVO_Update.setEmail(pseudoEmail);
		merchantVO_Update.setPassword("password");
		merchantVO_Update.setIdentityNumber("01234567");
		merchantVO_Update.setName("setName");

		merchantVO_Update.setBankCode("808");
		merchantVO_Update.setBankAccount("setAll");
		merchantVO_Update.setBankAccountName("setAll");
		merchantVO_Update.setContactPersonName("setAll");
		merchantVO_Update.setContactPersonEmail("setAll");
		
		merchantVO_Update.setContactPersonIdentityNumber("setAll");
		merchantVO_Update.setIsEnable(false);
		merchantVO_Update.setEnrollmentDate(null);
		merchantVO_Update.setLastUpdateAccountNumber(1);
		
		merchantVO_Update.setStatus(1);
		merchantVO_Update.setIsReleaseToMarket(false);
		merchantVO_Update.setAddress("Address");
		merchantVO_Update.setZipCode("101");
		merchantVO_Update.setFoodType(5);
		
		merchantVO_Update.setPicture("");
		merchantVO_Update.setPicture("Description");
		merchantVO_Update.setWeekday(null);
		merchantVO_Update.setBusinessHour(null);
		merchantVO_Update.setLandlinePhone("0532123456");
		
		merchantVO_Update.setClosedDate(null);
		
		dao.update(merchantVO_Update);
	}
	
	
	private void listPrintAll(List<MerchantVO> list) {

		
		for (MerchantVO merchantVO : list) {
			System.out.print(merchantVO.getAccountNumber() + ",");
			System.out.print(merchantVO.getEmail() + ",");
			System.out.print(merchantVO.getPassword() + ",");
			System.out.print(merchantVO.getIdentityNumber()+ ",");
			System.out.print(merchantVO.getName()+ ",");
			
			System.out.print(merchantVO.getBankCode()+ ",");
			System.out.print(merchantVO.getBankAccount()+ ",");
			System.out.print(merchantVO.getBankAccountName()+ ",");
			System.out.print(merchantVO.getContactPersonName()+ ",");
			System.out.print(merchantVO.getContactPersonEmail()+ ",");
			
			System.out.print(merchantVO.getContactPersonIdentityNumber()+ ",");
			System.out.print(merchantVO.getIsEnable()+ ",");
			System.out.print(merchantVO.getEnrollmentDate()+ ",");
			System.out.print(merchantVO.getLastUpdateAccountNumber()+ ",");
			System.out.print(merchantVO.getLastUpdateDatetime()+ ",");
			
			System.out.print(merchantVO.getStatus()+ ",");
			System.out.print(merchantVO.getIsReleaseToMarket()+ ",");
			System.out.print(merchantVO.getAddress()+ ",");
			System.out.print(merchantVO.getZipCode()+ ",");
			System.out.print(merchantVO.getFoodType()+ ",");
			
			System.out.print(merchantVO.getPicture()+ ",");
			System.out.print(merchantVO.getDescription()+ ",");
			System.out.print(merchantVO.getWeekday()+ ",");
			System.out.print(merchantVO.getBusinessHour()+ ",");
			System.out.print(merchantVO.getLandlinePhone()+ ",");
			
			System.out.print(merchantVO.getClosedDate()+ ",");
			System.out.println();

		}
	}
}
