package com.Courior.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class CouriorVO implements Serializable{
	
	private Integer AccountNumber; //編號
	private String Email; //電子信箱
	private String Password; //密碼
	private String Name; //商家名稱
	private String IdentityNumber; //身分證字號
	private String Photo; //相片
	private String MobilePhone;//行動電話
	private String Address; //地址
	private String ZipCode;//外送區域
	private String BankCode; //銀行代號
	private String BankAccount; //銀行帳號
	private String BankAccountName; //銀行戶名
	private Integer LastUpdateAccountNumber ;//前次修改人員編號
	private Timestamp LastUpdateDatetime ;//前次修改時間
	private Boolean IsEnable ;//是否啟用
	private Date EnrollmentDate;
	private Integer Status;//狀態
	
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
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getIdentityNumber() {
		return IdentityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		IdentityNumber = identityNumber;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getMobilePhone() {
		return MobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	public String getBankCode() {
		return BankCode;
	}
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	public String getBankAccount() {
		return BankAccount;
	}
	public void setBankAccount(String bankAccount) {
		BankAccount = bankAccount;
	}
	public String getBankAccountName() {
		return BankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		BankAccountName = bankAccountName;
	}
	public Integer getLastUpdateAccountNumber() {
		return LastUpdateAccountNumber;
	}
	public void setLastUpdateAccountNumber(Integer lastUpdateAccountNumber) {
		LastUpdateAccountNumber = lastUpdateAccountNumber;
	}
	public Timestamp getLastUpdateDatetime() {
		return LastUpdateDatetime;
	}
	public void setLastUpdateDatetime(Timestamp lastUpdateDatetime) {
		LastUpdateDatetime = lastUpdateDatetime;
	}
	public Boolean getIsEnable() {
		return IsEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		IsEnable = isEnable;
	}
	public Date getEnrollmentDate() {
		return EnrollmentDate;
	}
	public void setEnrollmentDate(Date enrollmentDate) {
		EnrollmentDate = enrollmentDate;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	
}
