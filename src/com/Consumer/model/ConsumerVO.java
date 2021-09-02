package com.Consumer.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ConsumerVO implements Serializable{

	private Integer AccountNumber; //商家編號
	private String Email; //電子信箱
	private String Password; //密碼
	private String Name; //商家名稱
	private String IdentityNumber; //統編
	private String MobilePhone;//行動電話
	private String Address; //地址
	private String Photo; //相片
	private String DeliveryAddresses ; //常用配送地址
	private String CreditCardNumber; //信用卡號
	private Date CreditCardExpirationDate;//信用卡有效日期
	private String CreditSecurityCode ;//信用卡安全碼
	private Integer LastUpdateAccountNumber ;//前次修改人員編號
	private Timestamp LastUpdateDatetime ;//前次修改時間
	private Boolean IsEnable ;//是否啟用
	private Date EnrollmentDate;//註冊日期
	private Boolean IsExposeMealInformation;//是否顯示餐點資訊
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
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getDeliveryAddresses() {
		return DeliveryAddresses;
	}
	public void setDeliveryAddresses(String deliveryAddresses) {
		DeliveryAddresses = deliveryAddresses;
	}
	public String getCreditCardNumber() {
		return CreditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		CreditCardNumber = creditCardNumber;
	}
	public Date getCreditCardExpirationDate() {
		return CreditCardExpirationDate;
	}
	public void setCreditCardExpirationDate(Date creditCardExpirationDate) {
		CreditCardExpirationDate = creditCardExpirationDate;
	}
	public String getCreditSecurityCode() {
		return CreditSecurityCode;
	}
	public void setCreditSecurityCode(String creditSecurityCode) {
		CreditSecurityCode = creditSecurityCode;
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
	public Boolean getIsExposeMealInformation() {
		return IsExposeMealInformation;
	}
	public void setIsExposeMealInformation(Boolean isExposeMealInformation) {
		IsExposeMealInformation = isExposeMealInformation;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	
}
