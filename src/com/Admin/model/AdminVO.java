package com.Admin.model;

import java.io.Serializable;

public class AdminVO implements Serializable {
	
	private Integer AccountNumber;
	private String Email;
	private String Password;
	
	public Integer getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
