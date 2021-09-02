package com.OrderMaster.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class OrderMasterVO implements Serializable{

	private Integer OrderNumber;//訂單主檔編號
	private Integer MerchantAccountNumber;//商家編號
	private Integer ConsumerAccountNumber;//消費者(訂購人員)編號
	private Boolean IsSocial;//是否為好友送餐
	private Integer DiningAccountNumber;//消費者(用餐人員)編號
	private Boolean IsNeedDelivery;//取餐方式
	private String RecipientName ;//餐點送達後聯絡人員
	private String RecipientPhoneNumber ;//餐點送達後聯絡人員電話
	private Integer CouriorAccountNumber ; //外送員編號
	private String DeliveryAddress;//外送地點
	private Integer Status ; //訂單狀態
	private Integer MealFee ; //餐點金額
	private Integer ProfitSharingAmount;//分潤金額
	private Integer DeliverFee;//外送費
	private Timestamp OrderDate ;//訂單日期
	private Date DeliverDateTime ;//送店日期時段
	private String Memo;//備註
	private Boolean IsCalculationGrade ; //是否已統計過評分
	private Date LastUpdateDatetime ;//系統更新時間
	public Integer getOrderNumber() {
		return OrderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		OrderNumber = orderNumber;
	}
	public Integer getMerchantAccountNumber() {
		return MerchantAccountNumber;
	}
	public void setMerchantAccountNumber(Integer merchantAccountNumber) {
		MerchantAccountNumber = merchantAccountNumber;
	}
	public Integer getConsumerAccountNumber() {
		return ConsumerAccountNumber;
	}
	public void setConsumerAccountNumber(Integer consumerAccountNumber) {
		ConsumerAccountNumber = consumerAccountNumber;
	}
	public Boolean getIsSocial() {
		return IsSocial;
	}
	public void setIsSocial(Boolean isSocial) {
		IsSocial = isSocial;
	}
	public Integer getDiningAccountNumber() {
		return DiningAccountNumber;
	}
	public void setDiningAccountNumber(Integer diningAccountNumber) {
		DiningAccountNumber = diningAccountNumber;
	}
	public Boolean getIsNeedDelivery() {
		return IsNeedDelivery;
	}
	public void setIsNeedDelivery(Boolean isNeedDelivery) {
		IsNeedDelivery = isNeedDelivery;
	}
	public String getRecipientName() {
		return RecipientName;
	}
	public void setRecipientName(String recipientName) {
		RecipientName = recipientName;
	}
	public String getRecipientPhoneNumber() {
		return RecipientPhoneNumber;
	}
	public void setRecipientPhoneNumber(String recipientPhoneNumber) {
		RecipientPhoneNumber = recipientPhoneNumber;
	}
	public Integer getCouriorAccountNumber() {
		return CouriorAccountNumber;
	}
	public void setCouriorAccountNumber(Integer couriorAccountNumber) {
		CouriorAccountNumber = couriorAccountNumber;
	}
	public String getDeliveryAddress() {
		return DeliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		DeliveryAddress = deliveryAddress;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public Integer getMealFee() {
		return MealFee;
	}
	public void setMealFee(Integer mealFee) {
		MealFee = mealFee;
	}
	public Integer getProfitSharingAmount() {
		return ProfitSharingAmount;
	}
	public void setProfitSharingAmount(Integer profitSharingAmount) {
		ProfitSharingAmount = profitSharingAmount;
	}
	public Integer getDeliverFee() {
		return DeliverFee;
	}
	public void setDeliverFee(Integer deliverFee) {
		DeliverFee = deliverFee;
	}
	public Timestamp getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		OrderDate = orderDate;
	}
	public Date getDeliverDateTime() {
		return DeliverDateTime;
	}
	public void setDeliverDateTime(Date deliverDateTime) {
		DeliverDateTime = deliverDateTime;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public Boolean getIsCalculationGrade() {
		return IsCalculationGrade;
	}
	public void setIsCalculationGrade(Boolean isCalculationGrade) {
		IsCalculationGrade = isCalculationGrade;
	}
	public Date getLastUpdateDatetime() {
		return LastUpdateDatetime;
	}
	public void setLastUpdateDatetime(Date lastUpdateDatetime) {
		LastUpdateDatetime = lastUpdateDatetime;
	}

}
