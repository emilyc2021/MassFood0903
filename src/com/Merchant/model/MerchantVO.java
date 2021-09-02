package com.Merchant.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class MerchantVO implements Serializable{

	private Integer AccountNumber; //商家編號
	private String Email; //電子信箱
	private String Password; //密碼
	private String IdentityNumber; //統編
	private String Name; //商家名稱
	private String BankCode; //銀行代號
	private String BankAccount; //銀行帳號
	private String BankAccountName; //銀行戶名
	private String ContactPersonName; //聯絡人姓名
	private String ContactPersonEmail; //聯絡人電子信箱
	private String ContactPersonIdentityNumber; //聯絡人身分證字號
	private Boolean IsEnable ;
	private Date EnrollmentDate;
	private Integer LastUpdateAccountNumber;
	private Timestamp LastUpdateDatetime;
	private Integer Status;
	private Boolean IsReleaseToMarket;
	private String Address;
	private String ZipCode;
	private Integer FoodType;
	private String Picture;
	private String Description;
	private String Weekday;
	private String BusinessHour;
	private String LandlinePhone;
	private String ClosedDate;
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
	public String getIdentityNumber() {
		return IdentityNumber;
	}
	public void setIdentityNumber(String identityNumber) {
		IdentityNumber = identityNumber;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	public String getContactPersonName() {
		return ContactPersonName;
	}
	public void setContactPersonName(String contactPersonName) {
		ContactPersonName = contactPersonName;
	}
	public String getContactPersonEmail() {
		return ContactPersonEmail;
	}
	public void setContactPersonEmail(String contactPersonEmail) {
		ContactPersonEmail = contactPersonEmail;
	}
	public String getContactPersonIdentityNumber() {
		return ContactPersonIdentityNumber;
	}
	public void setContactPersonIdentityNumber(String contactPersonIdentityNumber) {
		ContactPersonIdentityNumber = contactPersonIdentityNumber;
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
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public Boolean getIsReleaseToMarket() {
		return IsReleaseToMarket;
	}
	public void setIsReleaseToMarket(Boolean isReleaseToMarket) {
		IsReleaseToMarket = isReleaseToMarket;
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
	public Integer getFoodType() {
		return FoodType;
	}
	public void setFoodType(Integer foodType) {
		FoodType = foodType;
	}
	public String getPicture() {
		return Picture;
	}
	public void setPicture(String picture) {
		Picture = picture;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getWeekday() {
		return Weekday;
	}
	public void setWeekday(String weekday) {
		Weekday = weekday;
	}
	public String getBusinessHour() {
		return BusinessHour;
	}
	public void setBusinessHour(String businessHour) {
		BusinessHour = businessHour;
	}
	public String getLandlinePhone() {
		return LandlinePhone;
	}
	public void setLandlinePhone(String landlinePhone) {
		LandlinePhone = landlinePhone;
	}
	public String getClosedDate() {
		return ClosedDate;
	}
	public void setClosedDate(String closedDate) {
		ClosedDate = closedDate;
	}
}
