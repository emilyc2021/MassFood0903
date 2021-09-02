package com.Comment.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentVO implements Serializable{
	
	private Integer CommentNumber;
	private Integer OrderNumber ;
	private Integer MerchantAccountNumber;
	private Integer AccountNumber;
	private Boolean IsFromConsumer;
	private String Content;
	private Timestamp CommentDateTime;
	
	
	public Integer getCommentNumber() {
		return CommentNumber;
	}
	public void setCommentNumber(Integer commentNumber) {
		CommentNumber = commentNumber;
	}
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
	public Integer getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		AccountNumber = accountNumber;
	}
	public Boolean getIsFromConsumer() {
		return IsFromConsumer;
	}
	public void setIsFromConsumer(Boolean isFromConsumer) {
		IsFromConsumer = isFromConsumer;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Timestamp getCommentDateTime() {
		return CommentDateTime;
	}
	public void setCommentDateTime(Timestamp commentDateTime) {
		CommentDateTime = commentDateTime;
	}
	

}
