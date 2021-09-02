package com.utility;

public enum MessageType {

//	【00.參數設定檔】中「MessageType」 申訴聊天室使用，用來判斷傳送訊息發送及接收來源
//	12:管理員發送與商家
//	14:管理員發送與消費者
//	18:管理員發送與外送員
//	21:商家發送與管理員
//	41:消費者發送與管理員
//	81:外送員發送與管理員
	
	ADMIN_TO_MERCHANT("管理員發送與商家", 12),
	ADMIN_TO_CONSUMER("管理員發送與消費者", 14),
	ADMIN_TO_COURIOR("管理員發送與外送員", 18),
	MERCHANT_TO_ADMIN("商家發送與管理員", 21),
	CONSUMER_TO_ADMIN("消費者發送與管理員", 41),
	COURIOR_TO_ADMIN("外送員發送與管理員", 81);

	private final String Description;
	private int Code;
	
	MessageType(String description, int code){
        this.Description = description;
        this.Code = code;
    }
	
    public String getDescription() {
        return this.Description;
    }

    public int getCode() {
        return this.Code;
    }
	
    
	public static MessageType getMessageType(int i) {
		for(MessageType messageType : MessageType.values()) {
			if(messageType.getCode() == i) {
				return messageType;
			}
		}
		return null;
	}
		
}
