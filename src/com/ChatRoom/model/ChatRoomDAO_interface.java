package com.ChatRoom.model;

import java.sql.Timestamp;
import java.util.List;

public interface ChatRoomDAO_interface {

    public void insert(ChatRoomVO chatRoomVO);
    
    public void update(ChatRoomVO chatRoomVO);
    
    public void delete(int serialNumber);
    
    //為申訴聊天室，因為管理員可能會有很多個，但都會對一個會員(消費者、商家、管理員)聊天。
//    public List<ChatRoomVO> findMessageContent(int accountNumber);
    
    public List<ChatRoomVO> findMessageContent(int accountNumber , int RoleType);
    
    public List<ChatRoomVO> findMessageContent(int accountNumber , int RoleType ,Timestamp timestamp);
    
    public List<ChatRoomVO> getAll();

    public List<ChatRoomVO> conversationList();
	
}
