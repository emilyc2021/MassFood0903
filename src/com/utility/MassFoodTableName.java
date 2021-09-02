package com.utility;

public enum MassFoodTableName {

	SYSTEMPARAMETER("SystemParameter"),
	ADMIN("Admin"),
	MERCHANT("Merchant"),
	CONSUMER("Consumer"),
	COURIOR("Courior"),
	MERCHANDISE("Merchandise"),
	ORDER("OrderMaster"),
	ORDERDETAIL("OrderDetail"),
	COMMENT("Comment"),
	FRIEND("Friend"),
	CHATROOM("ChatRoom");
	
	
	
	private final String TableName;
	
	MassFoodTableName(String tableName){
        this.TableName = tableName;

    }

	public String getTableName() {
		return TableName;
	}
	
	
}
