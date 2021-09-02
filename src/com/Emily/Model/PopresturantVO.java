package com.Emily.Model;

import java.sql.Date;

public class PopresturantVO {
	private Integer AccountNumber;//商家帳號
	private Integer count;//品項數
	private Double sumAverage;//平均分數總合
	private Double totalAverage;//商家平均
	
	
	public Integer getAccountNumber() {
		return AccountNumber;
	}
	public Integer getCount() {
		return count;
	}
	public Double getSumAverage() {
		return sumAverage;
	}
	public Double getTotalAverage() {
		return totalAverage;
	}
	public void setAccountNumber(Integer accountNumber) {
		AccountNumber = accountNumber;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setSumAverage(Double sumAverage) {
		this.sumAverage = sumAverage;
	}
	public void setTotalAverage(Double totalAverage) {
		this.totalAverage = totalAverage;
	}


}
