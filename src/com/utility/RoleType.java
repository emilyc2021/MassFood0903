package com.utility;

public enum RoleType {

	ADMIN("管理員", 1),
	MERCHANT("廠商", 2),
	CONSUMER("消費者",4),
	COURIOR("外送員",8);
	
	private final String Description;
	private int Code;
	
	RoleType(String description, int code){
        this.Description = description;
        this.Code = code;
    }
	
	
    public String getDescription() {
        return this.Description;
    }

    public int getCode() {
        return this.Code;
    }
    
	public static RoleType getMessageType(int i) {
		for(RoleType roleType : RoleType.values()) {
			if(roleType.getCode() == i) {
				return roleType;
			}
		}
		return null;
	}
}
