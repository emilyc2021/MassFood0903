package com.ChatRoom.model;

import java.sql.Timestamp;

public class ChatRoomVO {
	
	private Integer SerialNumber;
	private Integer SenderAccountNumber;	
	private Integer ReceiverAccountNumber;	
	private String MessageContent;	
	private Integer MessageType;	
	private Timestamp DateTime;
	private String name;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getSerialNumber() {
		return SerialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		SerialNumber = serialNumber;
	}

	public Integer getSenderAccountNumber() {
		return SenderAccountNumber;
	}

	public void setSenderAccountNumber(Integer senderAccountNumber) {
		SenderAccountNumber = senderAccountNumber;
	}

	public Integer getReceiverAccountNumber() {
		return ReceiverAccountNumber;
	}

	public void setReceiverAccountNumber(Integer receiverAccountNumber) {
		ReceiverAccountNumber = receiverAccountNumber;
	}

	public String getMessageContent() {
		return MessageContent;
	}

	public void setMessageContent(String messageContent) {
		MessageContent = messageContent;
	}

	public Integer getMessageType() {
		return MessageType;
	}

	public void setMessageType(Integer messageType) {
		MessageType = messageType;
	}

	public Timestamp getDateTime() {
		return DateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		DateTime = dateTime;
	}
	
}
