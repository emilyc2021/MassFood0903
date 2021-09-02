package com.Friend.model;

import com.utility.FriendStatus;

public class FriendService {

	private FriendDAO_interface dao;

	public FriendService() {
		dao = new FriendJDBCDAO();
	}

	
	public FriendVO addFriend(Integer accountNumber, Integer friendAccountNumber, Integer status) {

		FriendVO friendVO = new FriendVO();

		friendVO.setAccountNumber(accountNumber);
		friendVO.setFriendAccountNumber(friendAccountNumber);
		friendVO.setStatus(FriendStatus.STRANGER.getCode());

		dao.insert(friendVO);

		return friendVO;
	}

	public FriendVO agree(Integer accountNumber, Integer friendAccountNumber, Integer status) {

		FriendVO friendVO = new FriendVO();

		friendVO.setAccountNumber(accountNumber);
		friendVO.setFriendAccountNumber(friendAccountNumber);
		friendVO.setStatus(FriendStatus.FRIEND.getCode());
		
		dao.updateStatusByFriend(friendVO);
		
		return friendVO;
	}
	
	public FriendVO dismiss(Integer accountNumber, Integer friendAccountNumber, Integer status) {

		FriendVO friendVO = new FriendVO();

		friendVO.setAccountNumber(accountNumber);
		friendVO.setFriendAccountNumber(friendAccountNumber);
		friendVO.setStatus(FriendStatus.STRANGER.getCode());

		dao.deleteStatusByFriend(friendVO);
		
		return friendVO;
	}

}