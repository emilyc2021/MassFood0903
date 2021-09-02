package com.Friend.model;

import java.util.List;

public interface FriendDAO_interface {

	public void insert(FriendVO friendVO);

	public void update(FriendVO friendVO);

	public void delete(int serialNumber);

	public void updateStatus(int accountNumber, int friendAccountNumber, int Status);

	public List<Integer> findEachOtherSerialNumber(int AccountNumer, int friendAccountNumer);

	public List<FriendVO> findByAccountNumber(int accountNumber);

	public List<FriendVO> getAll();

	public void updateStatusByFriend(FriendVO friendVO);

	public void deleteStatusByFriend(FriendVO friendVO);
}
