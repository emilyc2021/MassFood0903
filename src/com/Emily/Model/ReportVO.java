package com.Emily.Model;

import java.sql.Date;
import java.sql.Timestamp;

public class ReportVO {
	private Integer count;//訂單總數
	private Integer payableaccount;//月報表應付帳款
	private Integer MerchantAccountNumber;//商家帳號
	private Integer CouriorAccountNumber;//外送員帳號
	private Integer payable;//單筆訂單應付款
	private Integer orderNumber; //訂單編號
	private Date orderDate; //訂單日期
	private Integer mealFee; //訂單金額
	private Integer profitSharingAmount; //平台分潤
	private Integer deliverFee;//外送費
	
	public Integer getDeliverFee() {
		return deliverFee;
	}
	public void setDeliverFee(Integer deliverFee) {
		this.deliverFee = deliverFee;
	}
	public Integer getCouriorAccountNumber() {
		return CouriorAccountNumber;
	}
	public void setCouriorAccountNumber(Integer couriorAccountNumber) {
		CouriorAccountNumber = couriorAccountNumber;
	}
	public Integer getProfitSharingAmount() {
		return profitSharingAmount;
	}
	public void setProfitSharingAmount(Integer profitSharingAmount) {
		this.profitSharingAmount = profitSharingAmount;
	}
	public Integer getMealFee() {
		return mealFee;
	}
	public void setMealFee(Integer mealFee) {
		this.mealFee = mealFee;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date date) {
		this.orderDate = date;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getCount() {
		return count;
	}
	public Integer getPayableaccount() {
		return payableaccount;
	}
	public Integer getMerchantAccountNumber() {
		return MerchantAccountNumber;
	}
	public Integer getPayable() {
		return payable;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setPayableaccount(Integer payableaccount) {
		this.payableaccount = payableaccount;
	}
	public void setMerchantAccountNumber(Integer merchantAccountNumber) {
		MerchantAccountNumber = merchantAccountNumber;
	}
	public void setPayable(Integer payable) {
		this.payable = payable;
	}
	@Override
	public String toString() {
		return "ReportVO [count=" + count + ", payableaccount=" + payableaccount + ", MerchantAccountNumber="
				+ MerchantAccountNumber + ", payable=" + payable + "]";
	}

	
	
}
