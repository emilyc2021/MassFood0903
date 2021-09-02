package com.Consumer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.Merchant.model.MerchantJDBCDAO;
import com.Merchant.model.MerchantVO;
import com.utility.EnrollmentStatus;
import com.utility.GetAutoIncrement;
import com.utility.MassFoodTableName;
import com.utility.Utility;

public class ConsumerJDBCDAO implements ConsumerDAO_interface{
	
	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	//新增基本資料。
		private static final String INSERT_STMT = 
			"INSERT INTO Consumer ("
			+ "Email,Password,IdentityNumber,Name,"
			+ "Photo,MobilePhone,Address,DeliveryAddresses,CreditCardNumber,"
			+ "CreditCardExpirationDate,CreditSecurityCode,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
			+ "IsEnable,IsExposeMealInformation,Status"
			+ ") "
			+ "VALUES (?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?,"
			+ "?, ?, ?)";
		
		//取得全部
		private static final String GET_ALL_STMT = "Select * from Consumer";
//			"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
//			+ "Photo,MobilePhone,Address,DeliveryAddresses,CreditCardNumber,"
//			+ "CreditCardExpirationDate,CreditSecurityCode,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
//			+ "IsEnable,IsExposeMealInformation,Status"
//			+ " FROM Consumer order by AccountNumber";
		
		//依AccountNumber 取得相關內容
		private static final String FIND_BY_ACCOUNTNUMBER_STMT = 
			"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
			+ "Photo,MobilePhone,Address,DeliveryAddresses,CreditCardNumber,"
			+"CreditCardExpirationDate,CreditSecurityCode,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
			+ "IsEnable,IsExposeMealInformation,Status"
			+ " FROM Consumer where AccountNumber = ?";
		
		//依Email 取得相關內容
		private static final String FIND_BY_EMAIL_STMT = 
			"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
			+ "Photo,MobilePhone,Address,DeliveryAddresses,CreditCardNumber,"
			+ "CreditCardExpirationDate,CreditSecurityCode,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
			+ "IsEnable,IsExposeMealInformation,Status"
			+ " FROM Consumer where Email = ?";
		
		
		//刪除(基本上我們不會刪除
		private static final String DELETE_STMT = 
			"DELETE FROM Consumer where AccountNumber = ?";
		
		//更新全部
		private static final String UPDATE_STMT = 
			"UPDATE Consumer set Password=?, IdentityNumber=?, Name=? ,"
			+ "Photo=?, MobilePhone=?, Address=?, DeliveryAddresses=?, CreditCardNumber=? ,"
			+ "CreditCardExpirationDate=?, CreditSecurityCode=?, EnrollmentDate=?, LastUpdateAccountNumber=?, LastUpdateDatetime=?, "
			+ "IsEnable=?,IsExposeMealInformation=?, Status=?"
			+ " where AccountNumber = ?";
		
		//更新狀態及時間(管理員)
		private static final String UPDATE_ADMIN_AUTHENTICATION_STMT =
				"UPDATE Consumer set LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=? ,EnrollmentDate=?"
				+ " where AccountNumber = ?";

		
		@Override
		public void insert(ConsumerVO consumerVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(INSERT_STMT);
				
//				"INSERT INTO Consumer ("
//				+ "Email,Password,IdentityNumber,Name,"
//				+ "Photo,MobilePhone,Address,DeliveryAddresses,CreditCardNumber,"
//				+ "CreditCardExpirationDate,CreditSecurityCode,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
//				+ "IsEnable,IsExposeMealInformation,Status"
				
				pstmt.setString(1, consumerVO.getEmail());
				pstmt.setString(2, consumerVO.getPassword());
				pstmt.setString(3, consumerVO.getIdentityNumber());
				pstmt.setString(4, consumerVO.getName());

				pstmt.setString(5, consumerVO.getPhoto());
				pstmt.setString(6, consumerVO.getMobilePhone());
				pstmt.setString(7, consumerVO.getAddress());
				pstmt.setString(8, consumerVO.getDeliveryAddresses());
				pstmt.setString(9, consumerVO.getCreditCardNumber());
				
				pstmt.setDate(10, consumerVO.getCreditCardExpirationDate());
				pstmt.setString(11, consumerVO.getCreditSecurityCode());
				pstmt.setDate(12, consumerVO.getEnrollmentDate());
				pstmt.setInt(13, consumerVO.getLastUpdateAccountNumber());
				pstmt.setTimestamp(14, new java.sql.Timestamp(new java.util.Date().getTime()));
				
				pstmt.setBoolean(15, consumerVO.getIsEnable());
				pstmt.setBoolean(16, consumerVO.getIsExposeMealInformation());
				pstmt.setInt(17, consumerVO.getStatus());
				
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
		public void update(ConsumerVO consumerVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(UPDATE_STMT);
				
//				"UPDATE Consumer set Password=?, IdentityNumber=?, Name=? ,"
//				+ "Photo=?, MobilePhone=?, Address=?, DeliveryAddresses=?, CreditCardNumber=? ,"
//				+ "CreditCardExpirationDate=?, CreditSecurityCode=?, EnrollmentDate=?, LastUpdateAccountNumber=?, LastUpdateDatetime=?, "
//				+ "IsEnable=?,IsExposeMealInformation=?, Status=?"
//				+ " where AccountNumber = ?";
				
				pstmt.setString(1, consumerVO.getPassword());
				pstmt.setString(2, consumerVO.getIdentityNumber());
				pstmt.setString(3, consumerVO.getName());

				pstmt.setString(4, consumerVO.getPhoto());
				pstmt.setString(5, consumerVO.getMobilePhone());
				pstmt.setString(6, consumerVO.getAddress());
				pstmt.setString(7, consumerVO.getDeliveryAddresses());
				pstmt.setString(8, consumerVO.getCreditCardNumber());
				
				pstmt.setDate(9, consumerVO.getCreditCardExpirationDate());
				pstmt.setString(10, consumerVO.getCreditSecurityCode());
				pstmt.setDate(11, consumerVO.getEnrollmentDate());
				pstmt.setInt(12, consumerVO.getLastUpdateAccountNumber());
				pstmt.setTimestamp(13, new java.sql.Timestamp(new java.util.Date().getTime()));
				
				pstmt.setBoolean(14, consumerVO.getIsEnable());
				pstmt.setBoolean(15, consumerVO.getIsExposeMealInformation());
				pstmt.setInt(16, consumerVO.getStatus());
				
				
				pstmt.setInt(17, consumerVO.getAccountNumber());
				
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
		public void updateAdminAuthentication(ConsumerVO consumerVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(UPDATE_ADMIN_AUTHENTICATION_STMT);
				
//				"UPDATE Consumer set LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=? ,EnrollmentDate=?"
//				+ " where AccountNumber = ?";
				

				pstmt.setInt(1, consumerVO.getLastUpdateAccountNumber());
				pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
				pstmt.setInt(3, consumerVO.getStatus());
				pstmt.setDate(4, consumerVO.getEnrollmentDate());
				pstmt.setInt(5, consumerVO.getAccountNumber());
				
				
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
		public ConsumerVO findByAccountNumber(Integer accountNumber) {
			
			ConsumerVO consumerVO=null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(FIND_BY_ACCOUNTNUMBER_STMT);

				pstmt.setInt(1, accountNumber);

				rs = pstmt.executeQuery();

				while (rs.next()) {
//					"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
//							+ "Photo,MobilePhone,Address,DeliveryAddresses,CreditCardNumber,"
//							+"CreditCardExpirationDate,CreditSecurityCode,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
//							+ "IsEnable,IsExposeMealInformation,Status"
//							+ " FROM Consumer where AccountNumber = ?";
					consumerVO = new ConsumerVO();
					consumerVO.setAccountNumber(rs.getInt("AccountNumber"));
					consumerVO.setEmail(rs.getString("Email"));
					consumerVO.setPassword(rs.getString("Password"));
					consumerVO.setIdentityNumber(rs.getString("IdentityNumber"));
					consumerVO.setName(rs.getString("Name"));
					
					
					consumerVO.setPhoto(rs.getString("Photo"));
					consumerVO.setMobilePhone(rs.getString("MobilePhone"));
					consumerVO.setAddress(rs.getString("Address"));
					consumerVO.setDeliveryAddresses(rs.getString("DeliveryAddresses"));
					consumerVO.setCreditCardNumber(rs.getString("CreditCardNumber"));
					
					consumerVO.setCreditCardExpirationDate(rs.getDate("CreditCardExpirationDate"));
					consumerVO.setCreditSecurityCode(rs.getString("CreditSecurityCode"));
					consumerVO.setEnrollmentDate(rs.getDate("EnrollmentDate"));
					consumerVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
					consumerVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
					
					consumerVO.setIsEnable(rs.getBoolean("IsEnable"));
					consumerVO.setIsExposeMealInformation(rs.getBoolean("IsExposeMealInformation"));
					consumerVO.setStatus(rs.getInt("Status"));
					
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
			return consumerVO;
		}
		
		@Override
		public ConsumerVO findByEmail(String email) {
			
			ConsumerVO consumerVO=null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(FIND_BY_EMAIL_STMT);

				pstmt.setString(1, email);

				rs = pstmt.executeQuery();

				while (rs.next()) {
//					"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
//							+ "Photo,MobilePhone,Address,DeliveryAddresses,CreditCardNumber,"
//							+ "CreditCardExpirationDate,CreditSecurityCode,EnrollmentDate,LastUpdateAccountNumber,LastUpdateDatetime,"
//							+ "IsEnable,IsExposeMealInformation,Status"
//							+ " FROM Consumer where Email = ?";
					consumerVO = new ConsumerVO();
					
					consumerVO.setAccountNumber(rs.getInt("AccountNumber"));
					consumerVO.setEmail(rs.getString("Email"));
					consumerVO.setPassword(rs.getString("Password"));
					consumerVO.setIdentityNumber(rs.getString("IdentityNumber"));
					consumerVO.setName(rs.getString("Name"));
					

					consumerVO.setPhoto(rs.getString("Photo"));
					consumerVO.setMobilePhone(rs.getString("MobilePhone"));
					consumerVO.setAddress(rs.getString("Address"));
					consumerVO.setDeliveryAddresses(rs.getString("DeliveryAddresses"));
					consumerVO.setCreditCardNumber(rs.getString("CreditCardNumber"));
					
					consumerVO.setCreditCardExpirationDate(rs.getDate("CreditCardExpirationDate"));
					consumerVO.setCreditSecurityCode(rs.getString("CreditSecurityCode"));
					consumerVO.setEnrollmentDate(rs.getDate("EnrollmentDate"));
					consumerVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
					consumerVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
					
					consumerVO.setIsEnable(rs.getBoolean("IsEnable"));
					consumerVO.setIsExposeMealInformation(rs.getBoolean("IsExposeMealInformation"));
					consumerVO.setStatus(rs.getInt("Status"));
					
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
			return consumerVO;
		}
		
		@Override
		public List<ConsumerVO> getAll() {
			List<ConsumerVO> list = new ArrayList<ConsumerVO>();
			ConsumerVO consumerVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					

					consumerVO = new ConsumerVO();
					consumerVO.setAccountNumber(rs.getInt("AccountNumber"));
					consumerVO.setEmail(rs.getString("Email"));
					consumerVO.setPassword(rs.getString("Password"));
					consumerVO.setIdentityNumber(rs.getString("IdentityNumber"));
					consumerVO.setName(rs.getString("Name"));
					
					
					consumerVO.setPhoto(rs.getString("Photo"));
					consumerVO.setMobilePhone(rs.getString("MobilePhone"));
					consumerVO.setAddress(rs.getString("Address"));
					consumerVO.setDeliveryAddresses(rs.getString("DeliveryAddresses"));
					consumerVO.setCreditCardNumber(rs.getString("CreditCardNumber"));
					
					consumerVO.setCreditCardExpirationDate(rs.getDate("CreditCardExpirationDate"));
					consumerVO.setCreditSecurityCode(rs.getString("CreditSecurityCode"));
					consumerVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
					consumerVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
					consumerVO.setIsEnable(rs.getBoolean("IsEnable"));
					
					consumerVO.setIsExposeMealInformation(rs.getBoolean("IsExposeMealInformation"));
					consumerVO.setStatus(rs.getInt("Status"));
					
					list.add(consumerVO); // Store the row in the list
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
			ConsumerJDBCDAO dao= new ConsumerJDBCDAO();
			
			List<ConsumerVO> list = null;
			//insert --test ok-這邊自動增益的流水號後續再問一下老師
			
			list=dao.getAll();
			int amount = list.size() ;
			int serialNumber=amount + 1 ; 
			
			String pseudoEmail="Consumer"+serialNumber+"@gmail.com";
			
			String postFixWording="_"+serialNumber;
			
			
			GetAutoIncrement autoIncrement = new GetAutoIncrement();
			int autoIncrementNumber=autoIncrement.getAutoIncremenetNumber(MassFoodTableName.CONSUMER.getTableName());
			System.out.println(autoIncrementNumber);
			
			dao.insert(dao,autoIncrementNumber,pseudoEmail,postFixWording);

			//test update -- ok
			postFixWording=postFixWording+"u";
			
			dao.update(dao,autoIncrementNumber,postFixWording);

			
			//update_管理員驗證資料updateAdminAuthentication --test ok
			ConsumerVO consumerVO_Update_AdminAuthentication = new ConsumerVO();
			consumerVO_Update_AdminAuthentication.setAccountNumber(3);
			consumerVO_Update_AdminAuthentication.setStatus(EnrollmentStatus.APPROVAL.getCode());
			consumerVO_Update_AdminAuthentication.setLastUpdateAccountNumber(1);
			dao.updateAdminAuthentication(consumerVO_Update_AdminAuthentication);
			
			
			System.out.println("-----------consumerVOFindByAccountNumber-start ----------");
			list.clear();
			list.add(dao.findByAccountNumber(1));
			dao.listPrintAll(list);
			System.out.println();
			System.out.println("-----------consumerVOFindByAccountNumber-end ----------");
			
			
			System.out.println("-----------consumerVOFindByEmail-start ----------");
			list.clear();
			list.add(dao.findByEmail(pseudoEmail));
			dao.listPrintAll(list);
			System.out.println();
			System.out.println("-----------ConsumerVOFindByEmail-end ----------");
			
			
			
			System.out.println("-----------getAll-Start----------");
			list = dao.getAll();
			dao.listPrintAll(list);
			System.out.println();
			System.out.println("-----------getAll-End----------");
			
//			dao.delete(3);
		}
	
		private void insert(ConsumerJDBCDAO dao,int autoIncrementNumber,String pseudoEmail,String postFixWording) {

			ConsumerVO consumerVO_Insert = new ConsumerVO();
			consumerVO_Insert.setEmail(pseudoEmail);
			consumerVO_Insert.setIdentityNumber("012345"+postFixWording);
			consumerVO_Insert.setPassword("password"+postFixWording);
			consumerVO_Insert.setName("setName"+postFixWording);
			
			consumerVO_Insert.setPhoto("");
			consumerVO_Insert.setMobilePhone("0932222111");
			consumerVO_Insert.setAddress("Address"+postFixWording);
			consumerVO_Insert.setDeliveryAddresses("[\"臺北市中正區xxx路2號\"]");
			consumerVO_Insert.setCreditCardNumber("Cred");
			
			consumerVO_Insert.setCreditSecurityCode("777");
			consumerVO_Insert.setCreditCardExpirationDate(java.sql.Date.valueOf("2005-01-01"));
			consumerVO_Insert.setLastUpdateAccountNumber(autoIncrementNumber);
			consumerVO_Insert.setEnrollmentDate(null);
			
			consumerVO_Insert.setIsEnable(false);
			consumerVO_Insert.setIsExposeMealInformation(false);
			consumerVO_Insert.setStatus(EnrollmentStatus.WAITING_ADMIN_REVIEW.getCode());
			
			dao.insert(consumerVO_Insert);

		}
		
		private void update(ConsumerJDBCDAO dao,int autoIncrementNumber,String postFixWording) {

//			"UPDATE Consumer set Password=?, IdentityNumber=?, Name=? ,"
//			+ "Photo=?, MobilePhone=?, Address=?, DeliveryAddresses=?, CreditCardNumber=? ,"
//			+ "CreditCardExpirationDate=?, CreditSecurityCode=?, EnrollmentDate=?, LastUpdateAccountNumber=?, LastUpdateDatetime=?, "
//			+ "IsEnable=?,IsExposeMealInformation=?, Status=?"
//			+ " where AccountNumber = ?";
			
			ConsumerVO consumerVO_Update = new ConsumerVO();
			consumerVO_Update.setAccountNumber(autoIncrementNumber);
			
			consumerVO_Update.setIdentityNumber("01234567");
			consumerVO_Update.setPassword("password");
			consumerVO_Update.setName("setName"+postFixWording);
			
			consumerVO_Update.setPhoto("");
			consumerVO_Update.setMobilePhone("0932222111");
			consumerVO_Update.setAddress("Address"+postFixWording);
			consumerVO_Update.setDeliveryAddresses("[\"臺北市中正區xxx路2號\"]");
			consumerVO_Update.setCreditCardNumber("Cred");
			
			consumerVO_Update.setCreditCardExpirationDate(java.sql.Date.valueOf("2005-01-01"));
			consumerVO_Update.setCreditSecurityCode("777");
			consumerVO_Update.setEnrollmentDate(java.sql.Date.valueOf("2021-08-07"));
			consumerVO_Update.setLastUpdateAccountNumber(2);
			
			consumerVO_Update.setIsEnable(false);
			consumerVO_Update.setIsExposeMealInformation(false);
			consumerVO_Update.setStatus(EnrollmentStatus.REJECT.getCode());
			
			dao.update(consumerVO_Update);
		}
		
		
		
		private void listPrintAll(List<ConsumerVO> list) {	
			for (ConsumerVO consumerVO : list) {
		
				System.out.print(consumerVO.getAccountNumber() + ",");
				System.out.print(consumerVO.getEmail() + ",");
				System.out.print(consumerVO.getPassword() + ",");
				System.out.print(consumerVO.getIdentityNumber()+ ",");
				System.out.print(consumerVO.getName()+ ",");
				
				System.out.print(consumerVO.getPhoto()+ ",");
				System.out.print(consumerVO.getMobilePhone()+ ",");
				System.out.print(consumerVO.getAddress()+ ",");
				System.out.print(consumerVO.getDeliveryAddresses()+ ",");
				System.out.print(consumerVO.getCreditCardNumber()+ ",");
				
				System.out.print(consumerVO.getCreditCardExpirationDate()+ ",");
				System.out.print(consumerVO.getCreditSecurityCode()+ ",");
				System.out.print(consumerVO.getEnrollmentDate()+ ",");
				System.out.print(consumerVO.getLastUpdateAccountNumber()+ ",");
				System.out.print(consumerVO.getLastUpdateDatetime()+ ",");
				
				System.out.print(consumerVO.getIsEnable()+ ",");
				System.out.print(consumerVO.getIsExposeMealInformation()+ ",");
				System.out.print(consumerVO.getStatus()+ ",");
				System.out.println();

			}
		}
}
