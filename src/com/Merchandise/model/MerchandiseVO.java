package com.Merchandise.model;

import java.io.Serializable;

public class MerchandiseVO implements Serializable{

	private Integer ItemNumber;//商品編號
	private Integer AccountNumber;//商家編號
	private String ItemName;//商品名稱
	private String Photo;//圖片
	private Integer ItemPrice;//單價
	private String Description;//說明及描述	
	private Integer GradeAmount;//評分次數
	private Double AverageGrade;//平均評分
	private Boolean IsEnable ;//是否上架
	
	public Integer getItemNumber() {
		return ItemNumber;
	}
	public void setItemNumber(Integer itemNumber) {
		ItemNumber = itemNumber;
	}
	public Integer getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public Integer getItemPrice() {
		return ItemPrice;
	}
	public void setItemPrice(Integer itemPrice) {
		ItemPrice = itemPrice;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Integer getGradeAmount() {
		return GradeAmount;
	}
	public void setGradeAmount(Integer gradeAmount) {
		GradeAmount = gradeAmount;
	}
	public Double getAverageGrade() {
		return AverageGrade;
	}
	public void setAverageGrade(Double averageGrade) {
		AverageGrade = averageGrade;
	}
	public Boolean getIsEnable() {
		return IsEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		IsEnable = isEnable;
	}
	
	
}
