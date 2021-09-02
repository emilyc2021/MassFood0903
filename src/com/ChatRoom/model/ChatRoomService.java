package com.ChatRoom.model;

import com.utility.MessageType;
import com.utility.RoleType;

public class ChatRoomService {

	private ChatRoomDAO_interface dao;

	public ChatRoomService() {
		dao = new ChatRoomJDBCDAO();
	}

	public void sendmessage(int adminAccountNumber, int accountNumber, int roleType, String messageContent) {

		ChatRoomVO chatRoomVO = new ChatRoomVO();

		chatRoomVO.setSenderAccountNumber(accountNumber);
		chatRoomVO.setReceiverAccountNumber(adminAccountNumber);

		if (roleType == RoleType.MERCHANT.getCode()) {
			chatRoomVO.setMessageContent(messageContent);
			chatRoomVO.setMessageType(MessageType.MERCHANT_TO_ADMIN.getCode());

		} else if (roleType == RoleType.CONSUMER.getCode()) {
			chatRoomVO.setMessageContent(messageContent);
			chatRoomVO.setMessageType(MessageType.CONSUMER_TO_ADMIN.getCode());

		} else if (roleType == RoleType.COURIOR.getCode()) {
			chatRoomVO.setMessageContent(messageContent);
			chatRoomVO.setMessageType(MessageType.COURIOR_TO_ADMIN.getCode());

		} else {
			return;
		}
		dao.insert(chatRoomVO);
	}

	public void responsemessage(int adminAccountNumber, int accountNumber, int roleType, String messageContent) {
		
		ChatRoomVO chatRoomVO = new ChatRoomVO();
		chatRoomVO.setSenderAccountNumber(adminAccountNumber);
		chatRoomVO.setReceiverAccountNumber(accountNumber);
		System.out.println("roleType-below");
		System.out.println(roleType);
		if (roleType == RoleType.MERCHANT.getCode()) {
			chatRoomVO.setMessageContent(messageContent);
			chatRoomVO.setMessageType(MessageType.ADMIN_TO_MERCHANT.getCode());

		} else if (roleType == RoleType.CONSUMER.getCode()) {
			System.out.println("responsemessage-message to CONSUMER");
			chatRoomVO.setMessageContent(messageContent);
			chatRoomVO.setMessageType(MessageType.ADMIN_TO_CONSUMER.getCode());

		} else if (roleType == RoleType.COURIOR.getCode()) {
			chatRoomVO.setMessageContent(messageContent);
			chatRoomVO.setMessageType(MessageType.ADMIN_TO_CONSUMER.getCode());

		} else {

			return;
		}

		dao.insert(chatRoomVO);
	}

	public void findMessageContent(int accountNumber, int roleType) {
		ChatRoomVO chatRoomVO = new ChatRoomVO();
		chatRoomVO.setSenderAccountNumber(accountNumber);
	}
}