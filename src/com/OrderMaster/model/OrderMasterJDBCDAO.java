package com.OrderMaster.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Courior.model.CouriorVO;
import com.utility.Utility;

public class OrderMasterJDBCDAO implements OrderMaster_interface {

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	private static String ORDERNUMBER_FIELD = "OrderNumber";
	private static String OTHER_FIELD_NAMES = "MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
			+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
			+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
			+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime";

	private static final String INSERT_STMT = "INSERT INTO OrderMaster ("
			+ "MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
			+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
			+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
			+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime" + ") " + "VALUES " + "(?, ?, ?, ?,"
			+ "?, ?, ?, ?, ?," + "?, ?, ?, ?, ?," + "?, ?, ?, ?)";

	// 更新單筆資料(update)
	private static final String UPDATE_STMT = "UPDATE OrderMaster set MerchantAccountNumber=?, ConsumerAccountNumber=?, IsSocial=?, DiningAccountNumber=?,"
			+ "IsNeedDelivery=?, RecipientName=?, RecipientPhoneNumber=?,CouriorAccountNumber=?,DeliveryAddress=?,"
			+ "Status=?, MealFee=?, ProfitSharingAmount=?,DeliverFee=?,OrderDate=?,"
			+ "DeliverDateTime=?, Memo=?, IsCalculationGrade=?,LastUpdateDatetime=?" + " where OrderNumber = ?";

	// 刪除(基本上我們不會刪除
	private static final String DELETE_STMT = "DELETE FROM OrderMaster where OrderNumber = ?";

	// 取得全部
	private static final String GET_ALL_STMT = "SELECT " + ORDERNUMBER_FIELD + "," + OTHER_FIELD_NAMES
			+ " FROM OrderMaster order by OrderNumber";

	// 依訂單編號取得單筆資料
	private static final String FIND_BY_ORDERNUNBER_STMT = "SELECT " + ORDERNUMBER_FIELD + "," + OTHER_FIELD_NAMES
			+ " FROM OrderMaster where OrderNumber = ?";

	// 依商家編號取得所有訂單資料。
	private static final String FIND_BY_MERCHANTACCOUNTNUMBER_STMT = "SELECT " + ORDERNUMBER_FIELD + ","
			+ OTHER_FIELD_NAMES + " FROM OrderMaster where MerchantAccountNumber= ? ";

	// 依訂購人員取得所有訂單資料。
	private static final String FIND_BY_CONSUMERACCOUNTNUMBER_STMT = "SELECT " + ORDERNUMBER_FIELD + ","
			+ OTHER_FIELD_NAMES + " FROM OrderMaster  where ConsumerAccountNumber= ? ";

	// 依訂餐人員取得所有訂單資料。 動態牆(DiningAccountNumber) 這邊看後續要不要加上送餐日期
	private static final String FIND_BY_DININGACCOUNTNUMBER_STMT = "SELECT " + ORDERNUMBER_FIELD + ","
			+ OTHER_FIELD_NAMES + " FROM OrderMaster  where ConsumerAccountNumber= ? ";

	// 依用餐人員取得所有訂單資料。 動態牆(DiningAccountNumber) 這邊看後續要不要加上送餐日期
	private static final String FIND_BY_COURIORACCOUNTNUMBER_STMT = "Select " + ORDERNUMBER_FIELD + ","
			+ OTHER_FIELD_NAMES + " FROM OrderMaster  where CouriorAccountNumber= ? ";

	@Override
	public void insert(OrderMasterVO orderMasterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

//			"MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
//			+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
//			+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
//			+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime";

			pstmt.setInt(1, orderMasterVO.getMerchantAccountNumber());
			pstmt.setInt(2, orderMasterVO.getConsumerAccountNumber());
			pstmt.setBoolean(3, orderMasterVO.getIsSocial());
			pstmt.setInt(4, orderMasterVO.getDiningAccountNumber());

			pstmt.setBoolean(5, orderMasterVO.getIsNeedDelivery());
			pstmt.setString(6, orderMasterVO.getRecipientName());
			pstmt.setString(7, orderMasterVO.getRecipientPhoneNumber());
			pstmt.setInt(8, orderMasterVO.getCouriorAccountNumber());
			pstmt.setString(9, orderMasterVO.getDeliveryAddress());

			pstmt.setInt(10, orderMasterVO.getStatus());
			pstmt.setInt(11, orderMasterVO.getMealFee());
			pstmt.setDouble(12, orderMasterVO.getProfitSharingAmount());
			pstmt.setInt(13, orderMasterVO.getDeliverFee());
			pstmt.setTimestamp(14, new java.sql.Timestamp(orderMasterVO.getOrderDate().getTime()));

			pstmt.setTimestamp(15, new java.sql.Timestamp(orderMasterVO.getDeliverDateTime().getTime()));
			pstmt.setString(16, orderMasterVO.getMemo());
			pstmt.setBoolean(17, orderMasterVO.getIsCalculationGrade());
			pstmt.setTimestamp(18, new java.sql.Timestamp(new java.util.Date().getTime()));
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
	public void update(OrderMasterVO orderMasterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

//			"UPDATE OrderMaster set MerchantAccountNumber=?, ConsumerAccountNumber=?, IsSocial=?, DiningAccountNumber=?,"
//			+ "IsNeedDelivery=?, RecipientName=?, RecipientPhoneNumber=?,CouriorAccountNumber=?,DeliveryAddress=?,"
//			+ "Status=?, MealFee=?, ProfitSharingAmount=?,DeliverFee=?,OrderDate=?,"
//			+ "DeliverDateTime=?, Memo=?, IsCalculationGrade=?,LastUpdateDatetime=?" + " where OrderNumber = ?";

			pstmt.setInt(1, orderMasterVO.getMerchantAccountNumber());
			pstmt.setInt(2, orderMasterVO.getConsumerAccountNumber());
			pstmt.setBoolean(3, orderMasterVO.getIsSocial());
			pstmt.setInt(4, orderMasterVO.getDiningAccountNumber());

			pstmt.setBoolean(5, orderMasterVO.getIsNeedDelivery());
			pstmt.setString(6, orderMasterVO.getRecipientName());
			pstmt.setString(7, orderMasterVO.getRecipientPhoneNumber());
			pstmt.setInt(8, orderMasterVO.getCouriorAccountNumber());
			pstmt.setString(9, orderMasterVO.getDeliveryAddress());

			pstmt.setInt(10, orderMasterVO.getStatus());
			pstmt.setInt(11, orderMasterVO.getMealFee());
			pstmt.setDouble(12, orderMasterVO.getProfitSharingAmount());
			pstmt.setInt(13, orderMasterVO.getDeliverFee());
			pstmt.setTimestamp(14, new java.sql.Timestamp(orderMasterVO.getOrderDate().getTime()));

			pstmt.setTimestamp(15, new java.sql.Timestamp(orderMasterVO.getDeliverDateTime().getTime()));
			pstmt.setString(16, orderMasterVO.getMemo());
			pstmt.setBoolean(17, orderMasterVO.getIsCalculationGrade());
			pstmt.setTimestamp(18, new java.sql.Timestamp(new java.util.Date().getTime()));

			pstmt.setInt(19, orderMasterVO.getOrderNumber());
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
	public List<OrderMasterVO> getAll() {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO orderMasterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

//				"OrderNumber,MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
//						+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
//						+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
//						+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime";

				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderMasterVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				orderMasterVO.setConsumerAccountNumber(rs.getInt("ConsumerAccountNumber"));
				orderMasterVO.setIsSocial(rs.getBoolean("IsSocial"));
				orderMasterVO.setDiningAccountNumber(rs.getInt("DiningAccountNumber"));

				orderMasterVO.setIsNeedDelivery(rs.getBoolean("IsNeedDelivery"));
				orderMasterVO.setRecipientName(rs.getString("RecipientName"));
				orderMasterVO.setRecipientPhoneNumber(rs.getString("RecipientPhoneNumber"));
				orderMasterVO.setCouriorAccountNumber(rs.getInt("CouriorAccountNumber"));
				orderMasterVO.setDeliveryAddress(rs.getString("DeliveryAddress"));

				orderMasterVO.setStatus(rs.getInt("Status"));
				orderMasterVO.setMealFee(rs.getInt("MealFee"));
				orderMasterVO.setProfitSharingAmount(rs.getInt("ProfitSharingAmount"));
				orderMasterVO.setDeliverFee(rs.getInt("DeliverFee"));

				orderMasterVO.setOrderDate(new Timestamp(rs.getDate("OrderDate").getTime()));

				orderMasterVO.setDeliverDateTime(rs.getDate("DeliverDateTime"));
				orderMasterVO.setMemo(rs.getString("Memo"));
				orderMasterVO.setIsCalculationGrade(rs.getBoolean("IsCalculationGrade"));

				orderMasterVO.setLastUpdateDatetime(rs.getDate("LastUpdateDatetime"));
				list.add(orderMasterVO); // Store the row in the list
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

	public List<OrderMasterVO> findByMerchantAccountnumber(Integer accountnumber) {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO orderMasterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_MERCHANTACCOUNTNUMBER_STMT);

			pstmt.setInt(1, accountnumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {

//				"OrderNumber,MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
//				+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
//				+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
//				+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime";

				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderMasterVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				orderMasterVO.setConsumerAccountNumber(rs.getInt("ConsumerAccountNumber"));
				orderMasterVO.setIsSocial(rs.getBoolean("IsSocial"));
				orderMasterVO.setDiningAccountNumber(rs.getInt("DiningAccountNumber"));

				orderMasterVO.setIsNeedDelivery(rs.getBoolean("IsNeedDelivery"));
				orderMasterVO.setRecipientName(rs.getString("RecipientName"));
				orderMasterVO.setRecipientPhoneNumber(rs.getString("RecipientPhoneNumber"));
				orderMasterVO.setCouriorAccountNumber(rs.getInt("CouriorAccountNumber"));
				orderMasterVO.setDeliveryAddress(rs.getString("DeliveryAddress"));

				orderMasterVO.setStatus(rs.getInt("Status"));
				orderMasterVO.setMealFee(rs.getInt("MealFee"));
				orderMasterVO.setProfitSharingAmount(rs.getInt("ProfitSharingAmount"));
				orderMasterVO.setDeliverFee(rs.getInt("DeliverFee"));

				orderMasterVO.setOrderDate(new Timestamp(rs.getDate("OrderDate").getTime()));

				orderMasterVO.setDeliverDateTime(rs.getDate("DeliverDateTime"));
				orderMasterVO.setMemo(rs.getString("Memo"));
				orderMasterVO.setIsCalculationGrade(rs.getBoolean("IsCalculationGrade"));

				orderMasterVO.setLastUpdateDatetime(rs.getDate("LastUpdateDatetime"));
				list.add(orderMasterVO); // Store the row in the list
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

	public List<OrderMasterVO> findByConsumerAccountnumber(Integer accountnumber) {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO orderMasterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_CONSUMERACCOUNTNUMBER_STMT);

			pstmt.setInt(1, accountnumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {

//				"OrderNumber,MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
//				+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
//				+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
//				+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime";

				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderMasterVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				orderMasterVO.setConsumerAccountNumber(rs.getInt("ConsumerAccountNumber"));
				orderMasterVO.setIsSocial(rs.getBoolean("IsSocial"));
				orderMasterVO.setDiningAccountNumber(rs.getInt("DiningAccountNumber"));

				orderMasterVO.setIsNeedDelivery(rs.getBoolean("IsNeedDelivery"));
				orderMasterVO.setRecipientName(rs.getString("RecipientName"));
				orderMasterVO.setRecipientPhoneNumber(rs.getString("RecipientPhoneNumber"));
				orderMasterVO.setCouriorAccountNumber(rs.getInt("CouriorAccountNumber"));
				orderMasterVO.setDeliveryAddress(rs.getString("DeliveryAddress"));

				orderMasterVO.setStatus(rs.getInt("Status"));
				orderMasterVO.setMealFee(rs.getInt("MealFee"));
				orderMasterVO.setProfitSharingAmount(rs.getInt("ProfitSharingAmount"));
				orderMasterVO.setDeliverFee(rs.getInt("DeliverFee"));

				orderMasterVO.setOrderDate(new Timestamp(rs.getDate("OrderDate").getTime()));

				orderMasterVO.setDeliverDateTime(rs.getDate("DeliverDateTime"));
				orderMasterVO.setMemo(rs.getString("Memo"));
				orderMasterVO.setIsCalculationGrade(rs.getBoolean("IsCalculationGrade"));

				orderMasterVO.setLastUpdateDatetime(rs.getDate("LastUpdateDatetime"));
				list.add(orderMasterVO); // Store the row in the list
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

	public List<OrderMasterVO> findByDinigAccountnumber(Integer accountnumber) {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO orderMasterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_DININGACCOUNTNUMBER_STMT);

			pstmt.setInt(1, accountnumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {

//				"OrderNumber,MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
//				+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
//				+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
//				+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime";

				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderMasterVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				orderMasterVO.setConsumerAccountNumber(rs.getInt("ConsumerAccountNumber"));
				orderMasterVO.setIsSocial(rs.getBoolean("IsSocial"));
				orderMasterVO.setDiningAccountNumber(rs.getInt("DiningAccountNumber"));

				orderMasterVO.setIsNeedDelivery(rs.getBoolean("IsNeedDelivery"));
				orderMasterVO.setRecipientName(rs.getString("RecipientName"));
				orderMasterVO.setRecipientPhoneNumber(rs.getString("RecipientPhoneNumber"));
				orderMasterVO.setCouriorAccountNumber(rs.getInt("CouriorAccountNumber"));
				orderMasterVO.setDeliveryAddress(rs.getString("DeliveryAddress"));

				orderMasterVO.setStatus(rs.getInt("Status"));
				orderMasterVO.setMealFee(rs.getInt("MealFee"));
				orderMasterVO.setProfitSharingAmount(rs.getInt("ProfitSharingAmount"));
				orderMasterVO.setDeliverFee(rs.getInt("DeliverFee"));

				orderMasterVO.setOrderDate(new Timestamp(rs.getDate("OrderDate").getTime()));

				orderMasterVO.setDeliverDateTime(rs.getDate("DeliverDateTime"));
				orderMasterVO.setMemo(rs.getString("Memo"));
				orderMasterVO.setIsCalculationGrade(rs.getBoolean("IsCalculationGrade"));

				orderMasterVO.setLastUpdateDatetime(rs.getDate("LastUpdateDatetime"));
				list.add(orderMasterVO); // Store the row in the list
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

	public List<OrderMasterVO> findByCouriorAccountnumber(Integer accountnumber) {
		List<OrderMasterVO> list = new ArrayList<OrderMasterVO>();
		OrderMasterVO orderMasterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_COURIORACCOUNTNUMBER_STMT);

			pstmt.setInt(1, accountnumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {

//				"OrderNumber,MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
//				+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
//				+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
//				+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime";

				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderMasterVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				orderMasterVO.setConsumerAccountNumber(rs.getInt("ConsumerAccountNumber"));
				orderMasterVO.setIsSocial(rs.getBoolean("IsSocial"));
				orderMasterVO.setDiningAccountNumber(rs.getInt("DiningAccountNumber"));

				orderMasterVO.setIsNeedDelivery(rs.getBoolean("IsNeedDelivery"));
				orderMasterVO.setRecipientName(rs.getString("RecipientName"));
				orderMasterVO.setRecipientPhoneNumber(rs.getString("RecipientPhoneNumber"));
				orderMasterVO.setCouriorAccountNumber(rs.getInt("CouriorAccountNumber"));
				orderMasterVO.setDeliveryAddress(rs.getString("DeliveryAddress"));

				orderMasterVO.setStatus(rs.getInt("Status"));
				orderMasterVO.setMealFee(rs.getInt("MealFee"));
				orderMasterVO.setProfitSharingAmount(rs.getInt("ProfitSharingAmount"));
				orderMasterVO.setDeliverFee(rs.getInt("DeliverFee"));

				orderMasterVO.setOrderDate(new Timestamp(rs.getDate("OrderDate").getTime()));

				orderMasterVO.setDeliverDateTime(rs.getDate("DeliverDateTime"));
				orderMasterVO.setMemo(rs.getString("Memo"));
				orderMasterVO.setIsCalculationGrade(rs.getBoolean("IsCalculationGrade"));

				orderMasterVO.setLastUpdateDatetime(rs.getDate("LastUpdateDatetime"));
				list.add(orderMasterVO); // Store the row in the list
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

	public OrderMasterVO findByOrderNumber(Integer orderNumber) {

		OrderMasterVO orderMasterVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_ORDERNUNBER_STMT);

			pstmt.setInt(1, orderNumber);
			rs = pstmt.executeQuery();

			while (rs.next()) {

//				"OrderNumber,MerchantAccountNumber,ConsumerAccountNumber,IsSocial,DiningAccountNumber,"
//				+ "IsNeedDelivery,RecipientName,RecipientPhoneNumber,CouriorAccountNumber,DeliveryAddress,"
//				+ "Status,MealFee,ProfitSharingAmount,DeliverFee,OrderDate,"
//				+ "DeliverDateTime,Memo,IsCalculationGrade,LastUpdateDatetime";

				orderMasterVO = new OrderMasterVO();
				orderMasterVO.setOrderNumber(rs.getInt("OrderNumber"));
				orderMasterVO.setMerchantAccountNumber(rs.getInt("MerchantAccountNumber"));
				orderMasterVO.setConsumerAccountNumber(rs.getInt("ConsumerAccountNumber"));
				orderMasterVO.setIsSocial(rs.getBoolean("IsSocial"));
				orderMasterVO.setDiningAccountNumber(rs.getInt("DiningAccountNumber"));

				orderMasterVO.setIsNeedDelivery(rs.getBoolean("IsNeedDelivery"));
				orderMasterVO.setRecipientName(rs.getString("RecipientName"));
				orderMasterVO.setRecipientPhoneNumber(rs.getString("RecipientPhoneNumber"));
				orderMasterVO.setCouriorAccountNumber(rs.getInt("CouriorAccountNumber"));
				orderMasterVO.setDeliveryAddress(rs.getString("DeliveryAddress"));

				orderMasterVO.setStatus(rs.getInt("Status"));
				orderMasterVO.setMealFee(rs.getInt("MealFee"));
				orderMasterVO.setProfitSharingAmount(rs.getInt("ProfitSharingAmount"));
				orderMasterVO.setDeliverFee(rs.getInt("DeliverFee"));

				orderMasterVO.setOrderDate(new Timestamp(rs.getDate("OrderDate").getTime()));

				orderMasterVO.setDeliverDateTime(rs.getDate("DeliverDateTime"));
				orderMasterVO.setMemo(rs.getString("Memo"));
				orderMasterVO.setIsCalculationGrade(rs.getBoolean("IsCalculationGrade"));

				orderMasterVO.setLastUpdateDatetime(rs.getDate("LastUpdateDatetime"));

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
		return orderMasterVO;

	}

	@Override
	public void delete(Integer orderNumber) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, orderNumber);

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

		OrderMasterJDBCDAO dao = new OrderMasterJDBCDAO();
		dao.insert(dao);

		// update
		dao.update(dao);
		System.out.println("-----getAll_Print-----");
		List<OrderMasterVO> list = dao.getAll();
		dao.listPrintAll(list);

		System.out.println("-----findByMerchantAccountnumber_Print-----");
		list = dao.findByMerchantAccountnumber(2);
		dao.listPrintAll(list);

		System.out.println("-----findByConsumerAccountnumber_Print-----");
		list = dao.findByConsumerAccountnumber(1);
		dao.listPrintAll(list);

		System.out.println("-----findByDinigAccountnumber_Print-----");
		list = dao.findByDinigAccountnumber(1);
		dao.listPrintAll(list);

		System.out.println("-----findByOrderNumber_Print-----");
		list.clear();
		list.add(dao.findByOrderNumber(1));
		dao.listPrintAll(list);

		System.out.println("-----delete_-----");
//		dao.delete(1);
	}

	private void insert(OrderMasterJDBCDAO dao) {
		OrderMasterVO orderMasterVO_Insert = new OrderMasterVO();
		orderMasterVO_Insert.setMerchantAccountNumber(1);
		orderMasterVO_Insert.setConsumerAccountNumber(1);
		orderMasterVO_Insert.setIsSocial(false);
		orderMasterVO_Insert.setDiningAccountNumber(1);

		orderMasterVO_Insert.setIsNeedDelivery(true);
		orderMasterVO_Insert.setRecipientName("RecipientName");
		orderMasterVO_Insert.setRecipientPhoneNumber("0932111233");
		orderMasterVO_Insert.setCouriorAccountNumber(1);
		orderMasterVO_Insert.setDeliveryAddress("外送地址");

		orderMasterVO_Insert.setStatus(2);
		orderMasterVO_Insert.setMealFee(1000);
		orderMasterVO_Insert.setProfitSharingAmount(2);
		orderMasterVO_Insert.setDeliverFee(50);
////	https://stackoverflow.com/questions/20235692/how-to-convert-string-to-datetime-in-java

		Date orderDate = null;
		try {
			orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-08-02 14:12:25");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		orderMasterVO_Insert.setOrderDate(new Timestamp(orderDate.getTime()));

		Date deliverDateTime = null;
		try {
			deliverDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-08-03 14:12:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		orderMasterVO_Insert.setDeliverDateTime(new java.sql.Date(deliverDateTime.getTime()));
		orderMasterVO_Insert.setMemo("Memo");
		orderMasterVO_Insert.setIsCalculationGrade(false);
		dao.insert(orderMasterVO_Insert);

	}

	private void update(OrderMasterJDBCDAO dao) {
		OrderMasterVO orderMasterVO_Update = new OrderMasterVO();
		orderMasterVO_Update.setOrderNumber(2);
		orderMasterVO_Update.setMerchantAccountNumber(1);
		orderMasterVO_Update.setConsumerAccountNumber(1);
		orderMasterVO_Update.setIsSocial(false);
		orderMasterVO_Update.setDiningAccountNumber(1);

		orderMasterVO_Update.setIsNeedDelivery(true);
		orderMasterVO_Update.setRecipientName("RecipientName");
		orderMasterVO_Update.setRecipientPhoneNumber("0932111233");
		orderMasterVO_Update.setCouriorAccountNumber(1);
		orderMasterVO_Update.setDeliveryAddress("外送地址");

		orderMasterVO_Update.setStatus(2);
		orderMasterVO_Update.setMealFee(1000);
		orderMasterVO_Update.setProfitSharingAmount(2);
		orderMasterVO_Update.setDeliverFee(50);

		Date orderDate = null;
		try {
			orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-08-02 14:12:25");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		orderMasterVO_Update.setOrderDate(new Timestamp(orderDate.getTime()));

		Date deliverDateTime = null;
		try {
			deliverDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-08-03 14:12:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		orderMasterVO_Update.setDeliverDateTime(new java.sql.Date(deliverDateTime.getTime()));

		orderMasterVO_Update.setDeliverDateTime(new java.sql.Date(deliverDateTime.getTime()));
		orderMasterVO_Update.setMemo("Memo");
		orderMasterVO_Update.setIsCalculationGrade(false);
		dao.update(orderMasterVO_Update);

	}

	private void listPrintAll(List<OrderMasterVO> list) {
		for (OrderMasterVO orderMasterVO : list) {
			System.out.print(orderMasterVO.getOrderNumber() + ",");
			System.out.print(orderMasterVO.getMerchantAccountNumber() + ",");
			System.out.print(orderMasterVO.getConsumerAccountNumber() + ",");
			System.out.print(orderMasterVO.getIsSocial() + ",");
			System.out.print(orderMasterVO.getDiningAccountNumber() + ",");

			System.out.print(orderMasterVO.getIsNeedDelivery() + ",");
			System.out.print(orderMasterVO.getRecipientName() + ",");
			System.out.print(orderMasterVO.getRecipientPhoneNumber() + ",");
			System.out.print(orderMasterVO.getCouriorAccountNumber() + ",");
			System.out.print(orderMasterVO.getDeliveryAddress() + ",");

			System.out.print(orderMasterVO.getStatus() + ",");
			System.out.print(orderMasterVO.getMealFee() + ",");
			System.out.print(orderMasterVO.getProfitSharingAmount() + ",");
			System.out.print(orderMasterVO.getDeliverFee() + ",");

			System.out.print(orderMasterVO.getOrderDate() + ",");

			System.out.print(orderMasterVO.getDeliverDateTime() + ",");
			System.out.print(orderMasterVO.getMemo() + ",");
			System.out.print(orderMasterVO.getIsCalculationGrade() + ",");
			System.out.print(orderMasterVO.getLastUpdateDatetime() + ",");
			System.out.println();
		}
	}
}
