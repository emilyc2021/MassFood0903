package com.ChatRoom.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.utility.MessageType;
import com.utility.RoleType;
import com.utility.Utility;

public class ChatRoomJDBCDAO implements ChatRoomDAO_interface {

	static {
		try {
			Class.forName(Utility.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "Insert Into ChatRoom ("
			+ "SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType," + "DateTime" + ")"
			+ "Values(?,?,?,?" + ",?)";

	private static final String UPDATE_STMT = "Update ChatRoom Set SenderAccountNumber= ? ,ReceiverAccountNumber = ? , MessageContent =? ,MessageType=? ,"
			+ "DateTime =?" + "where SerialNumber = ?";

	private static final String FIND_MESSAGE_CONTENT_STMT = "select SerialNumber,SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType,"
			+ "DateTime from ChatRoom where " + "(" + "(SenderAccountNumber= ? and MessageType=?)" + "or"
			+ "(ReceiverAccountNumber=? and MessageType=?)" + ") order by DateTime desc";

	private static final String FIND_MESSAGE_CONTENT_OVER_ASSIGNED_DATETIME_STMT = "select SerialNumber,SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType,"
			+ "DateTime from ChatRoom where " + "(" + "(SenderAccountNumber= ? and MessageType=?)" + "or"
			+ "(ReceiverAccountNumber=? and MessageType=?)" + ") and DateTime > ? order by DateTime";

	private static final String GET_ALL = "select SerialNumber, SenderAccountNumber, ReceiverAccountNumber, MessageContent, MessageType,"
			+ "DateTime From ChatRoom order by DateTime desc";

	private static final String DISTINCT_SENDERACCOUNTNUMBERS = "SELECT" + "	A.senderaccountnumber,\r\n"
			+ "    A.MessageContent,\r\n" + "    A.MessageType,\r\n" + "    A.DateTime,\r\n" + "    B.Email,\r\n"
			+ "    B.Name\r\n" + "FROM ChatRoom A LEFT JOIN Consumer B on A.senderaccountnumber = B.AccountNumber\r\n"
			+ "where (A.SenderAccountNumber, A.MessageType, A.DateTime) in(\r\n"
			+ "	select SenderAccountNumber, MessageType, MAX(DateTime)\r\n" + "	from ChatRoom\r\n"
			+ "	group by SenderAccountNumber, MessageType)";

	@Override
	public void insert(ChatRoomVO chatRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			"Insert Into ChatRoom ("
//			+ "SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType "
//			+ "DateTime"
//			+ ")"
//			+ "Values(?,?,?,?"
//			+ ",?)";

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			java.sql.Timestamp currentDateTimeStamp = new java.sql.Timestamp(new java.util.Date().getTime());

			pstmt.setInt(1, chatRoomVO.getSenderAccountNumber());
			pstmt.setInt(2, chatRoomVO.getReceiverAccountNumber());
			pstmt.setString(3, chatRoomVO.getMessageContent());
			pstmt.setInt(4, chatRoomVO.getMessageType());
			pstmt.setTimestamp(5, currentDateTimeStamp);

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
	public void update(ChatRoomVO chatRoomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

//			"Update Friend Set SenderAccountNumber= ? ,ReceiverAccountNumber = ? , MessageContent =? ,MessageType=? ,"
//			+ "DateTime =?"
//			+ "where SerialNumber = ?";

			java.sql.Timestamp currentDateTimeStamp = new java.sql.Timestamp(new java.util.Date().getTime());

			pstmt.setInt(1, chatRoomVO.getSenderAccountNumber());
			pstmt.setInt(2, chatRoomVO.getReceiverAccountNumber());
			pstmt.setString(3, chatRoomVO.getMessageContent());
			pstmt.setInt(4, chatRoomVO.getMessageType());

			pstmt.setTimestamp(5, currentDateTimeStamp);
			pstmt.setInt(6, chatRoomVO.getSerialNumber());

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
		// TODO Auto-generated method stub

	}

	@Override
	public List<ChatRoomVO> findMessageContent(int accountNumber, int roleType) {
		List<ChatRoomVO> list = new ArrayList<ChatRoomVO>();
		ChatRoomVO chatRoomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			"select SerialNumber,SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType,"
//			+ "DateTime from ChatRoom where "
//			+ "("
//			+ 	"(SenderAccountNumber= ? and MessageType=?)"
//			+ 	"or"
//			+ 	"(ReceiverAccountNumber=? and MessageType=?)"
//			+ ") order by DateTime";

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_MESSAGE_CONTENT_STMT);

			pstmt.setInt(1, accountNumber);
			pstmt.setInt(3, accountNumber);

			if (roleType == RoleType.MERCHANT.getCode()) {

				pstmt.setInt(2, MessageType.MERCHANT_TO_ADMIN.getCode());
				pstmt.setInt(4, MessageType.ADMIN_TO_MERCHANT.getCode());

			} else if (roleType == RoleType.CONSUMER.getCode()) {
				pstmt.setInt(2, MessageType.CONSUMER_TO_ADMIN.getCode());
				pstmt.setInt(4, MessageType.ADMIN_TO_CONSUMER.getCode());

			} else if (roleType == RoleType.COURIOR.getCode()) {
				pstmt.setInt(2, MessageType.COURIOR_TO_ADMIN.getCode());
				pstmt.setInt(4, MessageType.ADMIN_TO_COURIOR.getCode());

			} else {

				return null;
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				chatRoomVO = new ChatRoomVO();

				chatRoomVO.setSerialNumber(rs.getInt("SerialNumber"));
				chatRoomVO.setSenderAccountNumber(rs.getInt("SenderAccountNumber"));
				chatRoomVO.setReceiverAccountNumber(rs.getInt("ReceiverAccountNumber"));
				chatRoomVO.setMessageContent(rs.getString("MessageContent"));
				chatRoomVO.setMessageType(rs.getInt("MessageType"));
				chatRoomVO.setDateTime(new Timestamp(rs.getTimestamp("DateTime").getTime()));

				list.add(chatRoomVO); // Store the row in the list
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
	public List<ChatRoomVO> findMessageContent(int accountNumber, int roleType, Timestamp timestamp) {
		List<ChatRoomVO> list = new ArrayList<ChatRoomVO>();
		ChatRoomVO chatRoomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			"select SerialNumber,SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType,"
//					+ "DateTime from ChatRoom where "
//					+ "("
//					+ 	"(SenderAccountNumber= ? and MessageType=?)"
//					+ 	"or"
//					+ 	"(ReceiverAccountNumber=? and MessageType=?)"
//					+ ") and DateTime >= ? order by DateTime";

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(FIND_MESSAGE_CONTENT_OVER_ASSIGNED_DATETIME_STMT);

			pstmt.setInt(1, accountNumber);
			pstmt.setInt(3, accountNumber);

			if (roleType == RoleType.MERCHANT.getCode()) {

				pstmt.setInt(2, MessageType.MERCHANT_TO_ADMIN.getCode());
				pstmt.setInt(4, MessageType.ADMIN_TO_MERCHANT.getCode());

			} else if (roleType == RoleType.CONSUMER.getCode()) {
				pstmt.setInt(2, MessageType.CONSUMER_TO_ADMIN.getCode());
				pstmt.setInt(4, MessageType.ADMIN_TO_CONSUMER.getCode());

			} else if (roleType == RoleType.COURIOR.getCode()) {
				pstmt.setInt(2, MessageType.COURIOR_TO_ADMIN.getCode());
				pstmt.setInt(4, MessageType.ADMIN_TO_COURIOR.getCode());

			} else {

				return null;
			}

			pstmt.setTimestamp(5, timestamp);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				chatRoomVO = new ChatRoomVO();

				chatRoomVO.setSerialNumber(rs.getInt("SerialNumber"));
				chatRoomVO.setSenderAccountNumber(rs.getInt("SenderAccountNumber"));
				chatRoomVO.setReceiverAccountNumber(rs.getInt("ReceiverAccountNumber"));
				chatRoomVO.setMessageContent(rs.getString("MessageContent"));
				chatRoomVO.setMessageType(rs.getInt("MessageType"));
				chatRoomVO.setDateTime(new Timestamp(rs.getTimestamp("DateTime").getTime()));

				list.add(chatRoomVO); // Store the row in the list
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
	public List<ChatRoomVO> getAll() {
		List<ChatRoomVO> list = new ArrayList<ChatRoomVO>();
		ChatRoomVO chatRoomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			"select SerialNumber,SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType,"
//			+ "DateTime from ChatRoom where "
//			+ "("
//			+ 	"(SenderAccountNumber= ? and MessageType=?)"
//			+ 	"or"
//			+ 	"(ReceiverAccountNumber=? and MessageType=?)"
//			+ ") order by DateTime";

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				chatRoomVO = new ChatRoomVO();

				chatRoomVO.setSerialNumber(rs.getInt("SerialNumber"));
				chatRoomVO.setSenderAccountNumber(rs.getInt("SenderAccountNumber"));
				chatRoomVO.setReceiverAccountNumber(rs.getInt("ReceiverAccountNumber"));
				chatRoomVO.setMessageContent(rs.getString("MessageContent"));
				chatRoomVO.setMessageType(rs.getInt("MessageType"));
				chatRoomVO.setDateTime(new Timestamp(rs.getTimestamp("DateTime").getTime()));

				list.add(chatRoomVO); // Store the row in the list
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
	public List<ChatRoomVO> conversationList() {
		List<ChatRoomVO> list = new ArrayList<ChatRoomVO>();
		ChatRoomVO chatRoomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			"SELECT A.senderaccountnumber,MAX(A.DateTime)AS DateTime,MAX(A.MessageContent)AS MessageContent,B.Email,B.Name "
//					+ "FROM ChatRoom A LEFT JOIN Consumer B on A.senderaccountnumber = B.AccountNumber"
//					+ "GROUP BY A.senderaccountnumber,B.AccountNumber"
//					+ "ORDER BY MAX(A.DateTime) DESC";

			con = DriverManager.getConnection(Utility.URL, Utility.USER, Utility.PASSWORD);
			pstmt = con.prepareStatement(DISTINCT_SENDERACCOUNTNUMBERS);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				chatRoomVO = new ChatRoomVO();

				chatRoomVO.setSenderAccountNumber(rs.getInt("SenderAccountNumber"));
				chatRoomVO.setMessageContent(rs.getString("MessageContent"));
				chatRoomVO.setMessageType(rs.getInt("MessageType"));
				chatRoomVO.setDateTime(new Timestamp(rs.getTimestamp("DateTime").getTime()));
				chatRoomVO.setEmail(rs.getString("Email"));
				chatRoomVO.setName(rs.getString("Name"));

				list.add(chatRoomVO); // Store the row in the list
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

	public static void main(String[] args) {
		ChatRoomJDBCDAO dao = new ChatRoomJDBCDAO();

//		int adminAccountNumber = 3;
		int accountNumber = 6;
//		String messageContent = "63423849" + "外部變數的消費者";
//
//		System.out.println("---------insert----------");
//		dao.insert(dao, adminAccountNumber, accountNumber, RoleType.CONSUMER.getCode(), messageContent);
//
//		System.out.println("---------update----------");
//		dao.update(dao);
//
		System.out.println("---------findMessageContent----------");
		dao.listPrintAll(dao.findMessageContent(accountNumber, RoleType.CONSUMER.getCode()));
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		Timestamp timestamp = null;
//		try {
//			timestamp = new Timestamp(dateFormat.parse("2021-08-06 21:30:47.359").getTime());
//		} catch (ParseException e) {
//
//			e.printStackTrace();
//		}
//
//		System.out.println("---------findMessageContent-witdh Timestamp----------");
//		dao.listPrintAll(dao.findMessageContent(accountNumber, RoleType.CONSUMER.getCode(), timestamp));
//		System.out.printf("-------------------\n\n");
//
		
//		System.out.println("---------getAll----------");
//		dao.listPrintAll(dao.getAll());
//		System.out.printf("----------------------------------------------------------------------------------------\n");
//
//		System.out.println("---------getconversationList----------");
//		dao.conversationList(dao.conversationList());
//		System.out.printf("----------------------------------------------------------------------------------------");
	}

	private void insert(ChatRoomJDBCDAO dao, int adminAccountNumber, int accountNumber, int roleType,
			String messageContent) {

//		"Insert Into ChatRoom ("
//		+ "SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType "
//		+ "DateTime"
//		+ ")"
//		+ "Values(?,?,?,?"
//		+ ",?)";

		ChatRoomVO chatRoomVO = new ChatRoomVO();

		chatRoomVO.setSenderAccountNumber(accountNumber);
		chatRoomVO.setReceiverAccountNumber(adminAccountNumber);

		if (roleType == RoleType.MERCHANT.getCode()) {
			chatRoomVO.setMessageContent("我是商家" + MessageType.MERCHANT_TO_ADMIN.getDescription());
			chatRoomVO.setMessageType(MessageType.MERCHANT_TO_ADMIN.getCode());

		} else if (roleType == RoleType.CONSUMER.getCode()) {
			chatRoomVO.setMessageContent(messageContent);
			chatRoomVO.setMessageType(MessageType.CONSUMER_TO_ADMIN.getCode());

		} else if (roleType == RoleType.COURIOR.getCode()) {
			chatRoomVO.setMessageContent("我是外送員" + MessageType.COURIOR_TO_ADMIN.getDescription());
			chatRoomVO.setMessageType(MessageType.COURIOR_TO_ADMIN.getCode());

		} else {

			return;
		}

		dao.insert(chatRoomVO);

//		chatRoomVO.setSenderAccountNumber(adminAccountNumber);
//		chatRoomVO.setReceiverAccountNumber(accountNumber);
//
//		if (roleType == RoleType.MERCHANT.getCode()) {
//			chatRoomVO.setMessageContent("我是管理員" + MessageType.ADMIN_TO_MERCHANT.getDescription());
//			chatRoomVO.setMessageType(MessageType.ADMIN_TO_MERCHANT.getCode());
//
//		} else if (roleType == RoleType.CONSUMER.getCode()) {
//			chatRoomVO.setMessageContent("我是管理員" + MessageType.ADMIN_TO_CONSUMER.getDescription());
//			chatRoomVO.setMessageType(MessageType.ADMIN_TO_CONSUMER.getCode());
//
//		} else if (roleType == RoleType.COURIOR.getCode()) {
//			chatRoomVO.setMessageContent("我是管理員" + MessageType.ADMIN_TO_CONSUMER.getDescription());
//			chatRoomVO.setMessageType(MessageType.ADMIN_TO_CONSUMER.getCode());
//
//		} else {
//
//			return;
//		}
//
//		dao.insert(chatRoomVO);

	}

	private void update(ChatRoomJDBCDAO dao) {
//		"Update Friend Set SenderAccountNumber= ? ,ReceiverAccountNumber = ? , MessageContent =? ,MessageType=? ,"
//		+ "DateTime =?"
//		+ "where SerialNumber = ?";

		ChatRoomVO chatRoomVO = new ChatRoomVO();

		chatRoomVO.setSerialNumber(1);
		chatRoomVO.setSenderAccountNumber(7);
		chatRoomVO.setReceiverAccountNumber(3);
		chatRoomVO.setMessageContent("MessageContent-update");
		chatRoomVO.setMessageType(MessageType.CONSUMER_TO_ADMIN.getCode());

		dao.update(chatRoomVO);
	}

	private void listPrintAll(List<ChatRoomVO> list) {
//		"select SerialNumber,SenderAccountNumber,ReceiverAccountNumber,MessageContent,MessageType,"
//		+ "DateTime from ChatRoom where "

		for (ChatRoomVO chatRoomVO : list) {
			System.out.print(chatRoomVO.getSerialNumber() + ",");
			System.out.print(chatRoomVO.getSenderAccountNumber() + ",");
			System.out.print(chatRoomVO.getReceiverAccountNumber() + ",");
			System.out.print(chatRoomVO.getMessageContent() + ",");
			System.out.print(chatRoomVO.getMessageType() + ",");
			System.out.print(chatRoomVO.getDateTime() + ",");
			System.out.println();
		}
	}

	private void conversationList(List<ChatRoomVO> list) {

		for (ChatRoomVO chatRoomVO : list) {
			System.out.print(chatRoomVO.getSenderAccountNumber() + ",");
			System.out.print(chatRoomVO.getMessageContent() + ",");
			System.out.print(chatRoomVO.getMessageType() + ",");
			System.out.print(chatRoomVO.getDateTime() + ",");
			System.out.print(chatRoomVO.getEmail() + ",");
			System.out.print(chatRoomVO.getName() + ",");

			System.out.println();
		}
	}

}
