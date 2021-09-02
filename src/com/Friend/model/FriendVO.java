package com.Friend.model;

import java.sql.Timestamp;

import com.Consumer.model.ConsumerVO;

public class FriendVO {

	private Integer SerialNumber;
	private Integer AccountNumber;
	private Integer FriendAccountNumber;
	private Integer Status;
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

	public Integer getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		AccountNumber = accountNumber;
	}

	public Integer getFriendAccountNumber() {
		return FriendAccountNumber;
	}

	public void setFriendAccountNumber(Integer friendAccountNumber) {
		FriendAccountNumber = friendAccountNumber;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public Timestamp getDateTime() {
		return DateTime;
	}

	public void setDateTime(Timestamp dateTime) {
		DateTime = dateTime;
	}

	@Override
	public String toString() {
		return "FriendVO [SerialNumber=" + SerialNumber + ", AccountNumber=" + AccountNumber + ", FriendAccountNumber="
				+ FriendAccountNumber + ", Status=" + Status + ", DateTime=" + DateTime + ", name=" + name + ", email="
				+ email + "]";
	}

}
