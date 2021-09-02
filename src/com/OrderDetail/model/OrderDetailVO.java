package com.OrderDetail.model;

import java.io.Serializable;

public class OrderDetailVO implements Serializable{

	private Integer OrderNumber; // 訂單主檔編號
	private Integer ItemNumber;// 商品編號
	private String ItemName; // 品項名稱
	private Integer ItemPrice; // 單價
	private Integer ItemAmount; // 數量
	private Integer Grade; // 使用者評分
	private String Memo; // 備註
	public Integer getOrderNumber() {
		return OrderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		OrderNumber = orderNumber;
	}
	public Integer getItemNumber() {
		return ItemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		ItemNumber = itemNumber;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public Integer getItemPrice() {
		return ItemPrice;
	}
	public void setItemPrice(Integer itemPrice) {
		ItemPrice = itemPrice;
	}
	public Integer getItemAmount() {
		return ItemAmount;
	}
	public void setItemAmount(Integer itemAmount) {
		ItemAmount = itemAmount;
	}
	public Integer getGrade() {
		return Grade;
	}
	public void setGrade(Integer grade) {
		Grade = grade;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}

}
