package com.Courior.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Consumer.model.ConsumerJDBCDAO;
import com.Consumer.model.ConsumerVO;
import com.utility.EnrollmentStatus;
import com.utility.GetAutoIncrement;
import com.utility.MassFoodTableName;
import com.utility.Utility;

public class CouriorJDBCDAO implements CouriorDAO_interface{
	
	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	//新增基本資料。
		private static final String INSERT_STMT = 
			"INSERT INTO Courior ("
			+ "Email,Password,IdentityNumber,Name,"
			+ "Photo,MobilePhone,Address,ZipCode,BankCode,"
			+ "BankAccount,BankAccountName,LastUpdateAccountNumber,LastUpdateDatetime,IsEnable,"
			+ "EnrollmentDate,Status"
			+ ") "
			+ "VALUES (?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?,"
			+ "?,?)";


		//取得全部
		private static final String GET_ALL_STMT = 
			"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
			+ "Photo,MobilePhone,Address,ZipCode,BankCode,"
			+ "BankAccount,BankAccountName,LastUpdateAccountNumber,LastUpdateDatetime,IsEnable,"
			+ "EnrollmentDate,Status"
			+ " FROM Courior order by AccountNumber";
		

		
		//依AccountNumber 取得相關內容
		private static final String FIND_BY_ACCOUNTNUMBER_STMT = 
			"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
			+ "Photo,MobilePhone,Address,ZipCode,BankCode,"
			+ "BankAccount,BankAccountName,LastUpdateAccountNumber,LastUpdateDatetime,IsEnable,"
			+ "EnrollmentDate,Status"
			+ " FROM Courior where AccountNumber = ?";
		
		
		
		
		//依Email 取得相關內容
		private static final String FIND_BY_EMAIL_STMT = 
			"SELECT AccountNumber,Email,Password,IdentityNumber,Name,"
			+ "Photo,MobilePhone,Address,ZipCode,BankCode,"
			+ "BankAccount,BankAccountName,LastUpdateAccountNumber,LastUpdateDatetime,IsEnable,"
			+ "EnrollmentDate,Status"
			+ " FROM Courior where Email = ?";
		
		//這邊應該還差一個要依ZIPCode取得外送員信箱的資料，這邊要study一下在mysql 中以json進行搜尋方式。
		
		
		
		//刪除(基本上我們不會刪除
		private static final String DELETE_STMT = 
			"DELETE FROM Courior where AccountNumber = ?";
		
		//更新單筆
		private static final String UPDATE_STMT = 
			"UPDATE Courior set Password=?, IdentityNumber=?, Name=? ,"
			+ "Photo=?, MobilePhone=?, Address=?, ZipCode=?, BankCode=? ,"
			+ "BankAccount=?, BankAccountName=?, LastUpdateAccountNumber=?, LastUpdateDatetime=?, IsEnable=?,"
			+ "EnrollmentDate =?,Status=?"
			+ " where AccountNumber = ?";
		
		//更新狀態及時間(管理員)
		private static final String UPDATE_ADMIN_AUTHENTICATION_STMT =
			"UPDATE Consumer set LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=? ,EnrollmentDate=?"
			+ " where AccountNumber = ?";
		
		@Override
		public void insert(CouriorVO couriorVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(INSERT_STMT);
				
//				+ "Email,Password,IdentityNumber,Name,"
//				+ "Photo,MobilePhone,Address,ZipCode,BankCode,"
//				+ "BankAccount,BankAccountName,LastUpdateAccountNumber,LastUpdateDatetime,IsEnable,"
//				+ "EnrollmentDate,Status"
				
				pstmt.setString(1, couriorVO.getEmail());
				pstmt.setString(2, couriorVO.getPassword());
				pstmt.setString(3, couriorVO.getIdentityNumber());
				pstmt.setString(4, couriorVO.getName());

				pstmt.setString(5, couriorVO.getPhoto());
				pstmt.setString(6, couriorVO.getMobilePhone());
				pstmt.setString(7, couriorVO.getAddress());
				pstmt.setString(8, couriorVO.getZipCode());
				pstmt.setString(9, couriorVO.getBankCode());
				
				pstmt.setString(10, couriorVO.getBankAccount());
				pstmt.setString(11, couriorVO.getBankAccountName());
				pstmt.setInt(12, couriorVO.getLastUpdateAccountNumber());
				pstmt.setTimestamp(13, new java.sql.Timestamp(new java.util.Date().getTime()));
				pstmt.setBoolean(14, couriorVO.getIsEnable());
				
				pstmt.setDate(15, couriorVO.getEnrollmentDate());
				pstmt.setInt(16, couriorVO.getStatus());
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
		public void update(CouriorVO couriorVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(UPDATE_STMT);
				
//				"UPDATE Courior set Password=?, IdentityNumber=?, Name=? ,"
//				+ "Photo=?, MobilePhone=?, Address=?, ZipCode=?, BankCode=? ,"
//				+ "BankAccount=?, BankAccountName=?, LastUpdateAccountNumber=?, LastUpdateDatetime=?, IsEnable=?,"
//				+ "EnrollmentDate =?,Status=?"
//				+ " where AccountNumber = ?";
				
				pstmt.setString(1, couriorVO.getPassword());
				pstmt.setString(2, couriorVO.getIdentityNumber());
				pstmt.setString(3, couriorVO.getName());

				pstmt.setString(4, couriorVO.getPhoto());
				pstmt.setString(5, couriorVO.getMobilePhone());
				pstmt.setString(6, couriorVO.getAddress());
				pstmt.setString(7, couriorVO.getZipCode());
				pstmt.setString(8, couriorVO.getBankCode());
				
				pstmt.setString(9, couriorVO.getBankAccount());
				pstmt.setString(10, couriorVO.getBankAccountName());
				pstmt.setInt(11, couriorVO.getLastUpdateAccountNumber());
				pstmt.setTimestamp(12, new java.sql.Timestamp(new java.util.Date().getTime()));
				pstmt.setBoolean(13, couriorVO.getIsEnable());
				
				pstmt.setDate(14, couriorVO.getEnrollmentDate());
				pstmt.setInt(15, couriorVO.getStatus());
			
				pstmt.setInt(16, couriorVO.getAccountNumber());
				
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
		public void updateAdminAuthentication(CouriorVO couriorVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				
				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(UPDATE_ADMIN_AUTHENTICATION_STMT);
				
//				"UPDATE Consumer set LastUpdateAccountNumber=?, LastUpdateDatetime=?, Status=? ,Enrollment=?"
//				+ " where AccountNumber = ?";
				
				pstmt.setInt(1, couriorVO.getLastUpdateAccountNumber());
				pstmt.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
				pstmt.setInt(3, couriorVO.getStatus());
				pstmt.setDate(4, couriorVO.getEnrollmentDate());
				
				pstmt.setInt(5, couriorVO.getAccountNumber());
				
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
		public CouriorVO findByAccountNumber(Integer accountNumber) {
			
			CouriorVO couriorVO=null;
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
//					+ "Photo,MobilePhone,Address,ZipCode,BankCode,"
//					+ "BankAccount,BankAccountName,LastUpdateAccountNumber,LastUpdateDatetime,IsEnable,"
//					+ "EnrollmentDate,Status"
//					+ " FROM Courior where AccountNumber = ?";
					
					
					couriorVO = new CouriorVO();
					couriorVO.setAccountNumber(rs.getInt("AccountNumber"));
					couriorVO.setEmail(rs.getString("Email"));
					couriorVO.setPassword(rs.getString("Password"));
					couriorVO.setIdentityNumber(rs.getString("IdentityNumber"));
					couriorVO.setName(rs.getString("Name"));
					
					couriorVO.setPhoto(rs.getString("Photo"));
					couriorVO.setMobilePhone(rs.getString("MobilePhone"));
					couriorVO.setAddress(rs.getString("Address"));
					couriorVO.setZipCode(rs.getString("ZipCode"));
					couriorVO.setBankCode(rs.getString("BankCode"));
					
					couriorVO.setBankAccount(rs.getString("BankAccount"));
					couriorVO.setBankAccountName(rs.getString("BankAccountName"));
					couriorVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
					couriorVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
					couriorVO.setIsEnable(rs.getBoolean("IsEnable"));
					
					couriorVO.setEnrollmentDate(rs.getDate("EnrollmentDate"));
					couriorVO.setStatus(rs.getInt("Status"));
					
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
			return couriorVO;
		}
		
		
		@Override
		public CouriorVO findByEmail(String email) {
			
			CouriorVO couriorVO=null;
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
//							+ "Photo,MobilePhone,Address,ZipCode,BankCode,"
//							+ "BankAccount,BankAccountName,LastUpdateAccountNumber,LastUpdateDatetime,IsEnable,"
//							+ "EnrollmentDate,Status"
//							+ " FROM Courior where Email = ?";
					
					
					couriorVO = new CouriorVO();
					couriorVO.setAccountNumber(rs.getInt("AccountNumber"));
					couriorVO.setEmail(rs.getString("Email"));
					couriorVO.setPassword(rs.getString("Password"));
					couriorVO.setIdentityNumber(rs.getString("IdentityNumber"));
					couriorVO.setName(rs.getString("Name"));
					
					
					couriorVO.setPhoto(rs.getString("Photo"));
					couriorVO.setMobilePhone(rs.getString("MobilePhone"));
					couriorVO.setAddress(rs.getString("Address"));
					couriorVO.setZipCode(rs.getString("ZipCode"));
					couriorVO.setBankCode(rs.getString("BankCode"));
					
					couriorVO.setBankAccount(rs.getString("BankAccount"));
					couriorVO.setBankAccountName(rs.getString("BankAccountName"));
					couriorVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
					couriorVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
					couriorVO.setIsEnable(rs.getBoolean("IsEnable"));
					
					couriorVO.setEnrollmentDate(rs.getDate("EnrollmentDate"));
					couriorVO.setStatus(rs.getInt("Status"));
					
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
			return couriorVO;
		}
		
		@Override
		public List<CouriorVO> getAll() {
			List<CouriorVO> list = new ArrayList<CouriorVO>();
			CouriorVO couriorVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = DriverManager.getConnection(Utility.URL,Utility.USER,Utility.PASSWORD);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					couriorVO = new CouriorVO();
					couriorVO.setAccountNumber(rs.getInt("AccountNumber"));
					couriorVO.setEmail(rs.getString("Email"));
					couriorVO.setPassword(rs.getString("Password"));
					couriorVO.setIdentityNumber(rs.getString("IdentityNumber"));
					couriorVO.setName(rs.getString("Name"));
					
					
					couriorVO.setPhoto(rs.getString("Photo"));
					couriorVO.setMobilePhone(rs.getString("MobilePhone"));
					couriorVO.setAddress(rs.getString("Address"));
					couriorVO.setZipCode(rs.getString("ZipCode"));
					couriorVO.setBankCode(rs.getString("BankCode"));
					
					couriorVO.setBankAccount(rs.getString("BankAccount"));
					couriorVO.setBankAccountName(rs.getString("BankAccountName"));
					couriorVO.setLastUpdateAccountNumber(rs.getInt("LastUpdateAccountNumber"));
					couriorVO.setLastUpdateDatetime(rs.getTimestamp("LastUpdateDatetime"));
					couriorVO.setIsEnable(rs.getBoolean("IsEnable"));
					
					couriorVO.setEnrollmentDate(rs.getDate("EnrollmentDate"));
					couriorVO.setStatus(rs.getInt("Status"));

					list.add(couriorVO); // Store the row in the list
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
			CouriorJDBCDAO dao = new CouriorJDBCDAO();

			List<CouriorVO> list = null;
			//insert --test ok-這邊自動增益的流水號後續再問一下老師
		
			list=dao.getAll();
			int amount = list.size() ;
			int serialNumber=amount + 1 ; 
			
			String pseudoEmail="Courior"+serialNumber+"@gmail.com";
			String postFixWording="_"+serialNumber;
			
			
			GetAutoIncrement autoIncrement = new GetAutoIncrement();
			int autoIncrementNumber=autoIncrement.getAutoIncremenetNumber(MassFoodTableName.COURIOR.getTableName());
			
		
			//insert
			dao.insert(dao, autoIncrementNumber, pseudoEmail,postFixWording);
			
			//update
			postFixWording=postFixWording+"_u";
			dao.update(dao, autoIncrementNumber, postFixWording);
			
			
			//update_管理員驗證資料updateAdminAuthentication --test ok
			CouriorVO couriorVO_Update_AdminAuthentication= new CouriorVO();
			couriorVO_Update_AdminAuthentication.setAccountNumber(serialNumber);
			couriorVO_Update_AdminAuthentication.setStatus(2);
			couriorVO_Update_AdminAuthentication.setLastUpdateAccountNumber(1);
			dao.updateAdminAuthentication(couriorVO_Update_AdminAuthentication);
			
			
			System.out.println("-----------consumerVOFindByAccountNumber-start ----------");
			list.clear();
			list.add(dao.findByAccountNumber(serialNumber));
			dao.listPrintAll(list);
			System.out.println();

			System.out.println("-----------consumerVOFindByAccountNumber-end ----------");
			
			
			System.out.println("-----------consumerVOFindByEmail-start ----------");
			list.clear();
			list.add(dao.findByEmail(pseudoEmail));
			dao.listPrintAll(list);
			System.out.println();
			System.out.println("-----------consumerVOFindByEmail-end ----------");
			
			
			System.out.println("-----------getAll-Start----------");
			list = dao.getAll();
			dao.listPrintAll(list);
			System.out.println("-----------getAll-End----------");
			
			//刪除
//			dao.delete(2);
			
		}
		
		private void insert(CouriorJDBCDAO dao,int autoIncrementNumber,String pseudoEmail,String postFixWording) {

			
			CouriorVO couriorVO_Insert= new CouriorVO();
			couriorVO_Insert.setEmail(pseudoEmail);
			couriorVO_Insert.setIdentityNumber("0123456_"+postFixWording);
			couriorVO_Insert.setPassword("password"+postFixWording);
			couriorVO_Insert.setName("setName"+postFixWording);
			
			couriorVO_Insert.setPhoto("");
			couriorVO_Insert.setMobilePhone("0932"+postFixWording);
			couriorVO_Insert.setAddress("Address"+postFixWording);
			couriorVO_Insert.setZipCode("[\"A.101\",\"A.102\"]");
			couriorVO_Insert.setBankCode("808");
			
			couriorVO_Insert.setBankAccount("777");
			couriorVO_Insert.setBankAccountName("setBankAccountName");
			couriorVO_Insert.setLastUpdateAccountNumber(2);
			couriorVO_Insert.setIsEnable(false);
			
			couriorVO_Insert.setStatus(1);
			
			dao.insert(couriorVO_Insert);

		}
		
		
		private void update(CouriorJDBCDAO dao,int autoIncrementNumber,String postFixWording) {

//			"UPDATE Courior set Password=?, IdentityNumber=?, Name=? ,"
//			+ "Photo=?, MobilePhone=?, Address=?, ZipCode=?, BankCode=? ,"
//			+ "BankAccount=?, BankAccountName=?, LastUpdateAccountNumber=?, LastUpdateDatetime=?, IsEnable=?,"
//			+ "EnrollmentDate =?,Status=?"
//			+ " where AccountNumber = ?";
			
			CouriorVO couriorVO_Update= new CouriorVO();
			
			couriorVO_Update.setAccountNumber(2);

			couriorVO_Update.setIdentityNumber("01234567");
			couriorVO_Update.setPassword("password");
			couriorVO_Update.setName("setName"+postFixWording);
			
			couriorVO_Update.setPhoto("");
			couriorVO_Update.setMobilePhone("09322"+postFixWording);
			couriorVO_Update.setAddress("Address"+postFixWording);
			couriorVO_Update.setZipCode("[\"A.101\",\"A.102\"]");
			couriorVO_Update.setBankCode("808");
			
			couriorVO_Update.setBankAccount("777");
			couriorVO_Update.setBankAccountName("setBankAccountName");
			couriorVO_Update.setLastUpdateAccountNumber(2);
			couriorVO_Update.setIsEnable(false);
			
			couriorVO_Update.setStatus(1);
			
			dao.update(couriorVO_Update);
		}
		
		
		private void listPrintAll(List<CouriorVO> list) {	
			for (CouriorVO couriorVO : list) {
		
				System.out.print(couriorVO.getEmail() + ",");
				System.out.print(couriorVO.getAccountNumber() + ",");
				System.out.print(couriorVO.getPassword() + ",");
				System.out.print(couriorVO.getIdentityNumber()+ ",");
				System.out.print(couriorVO.getName()+ ",");
				
				System.out.print(couriorVO.getPhoto()+ ",");
				System.out.print(couriorVO.getMobilePhone()+ ",");
				System.out.print(couriorVO.getAddress()+ ",");
				System.out.print(couriorVO.getZipCode()+ ",");
				System.out.print(couriorVO.getBankCode()+ ",");
				
				System.out.print(couriorVO.getBankAccount()+ ",");
				System.out.print(couriorVO.getBankAccountName()+ ",");
				System.out.print(couriorVO.getLastUpdateAccountNumber()+ ",");
				System.out.print(couriorVO.getLastUpdateDatetime()+ ",");
				System.out.print(couriorVO.getIsEnable()+ ",");
				
				System.out.print(couriorVO.getEnrollmentDate()+ ",");
				System.out.print(couriorVO.getStatus()+ ",");
				System.out.println();

			}
		}
}
