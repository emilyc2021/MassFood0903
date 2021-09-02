package com.utility;

public enum FriendStatus {
//	https://codertw.com/%E7%A8%8B%E5%BC%8F%E8%AA%9E%E8%A8%80/321073/

	STRANGER("�D�n��", 0),
	FRIEND("�n��", 1);
	// �Q�ܽЪ�Null

	FriendStatus(String description, int code){
        this.Description = description;
        this.Code = code;
    }

    private final String Description;
    private int Code;

    public static FriendStatus getFriendStatus(int i){
        for(FriendStatus friendStatus : values()){
            if(friendStatus.getCode() == i) {
                return friendStatus;
            }
        }
        return null;
    }


    public String getDescription() {
        return this.Description;
    }

    public int getCode() {
        return this.Code;
    }
	
//	FriendStatus.STRANGER.getCode();
//	
//	
//	FriendStatus.STRANGER.getDescription();
//	
//	System.out.println(FriendStatus.STRANGER.getCode());
//	System.out.println(FriendStatus.STRANGER.getDescription());
//	
//	
//	FriendStatus a =FriendStatus.getFriendStatus(1);
//	System.out.println(a.FRIEND.getCode());
//	System.out.println(a.FRIEND.getDescription());
    
}
