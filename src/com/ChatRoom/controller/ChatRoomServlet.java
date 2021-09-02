package com.ChatRoom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ChatRoom.model.*;
import com.utility.RoleType;

public class ChatRoomServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String messageContent = req.getParameter("messageContent");
		
		if ("consumer-insert".equals(action)) {

			int adminAccountNumber = 1;
			int accountNumber = 7;
			
			/*************************** 2.輸入訊息 ***************************************/
			ChatRoomService chatRoomSvc = new ChatRoomService();
			chatRoomSvc.sendmessage(adminAccountNumber, accountNumber, RoleType.CONSUMER.getCode(), messageContent);
		}

		if ("consumer-response".equals(action)) {

			int adminAccountNumber = 1;
			int receiverAccountNumber = Integer.parseInt(req.getParameter("receiverAccountNumber"));
//			System.out.println(adminAccountNumber);
//			System.out.println(receiverAccountNumber);
//			System.out.println(messageContent);
			
			/*************************** 2.輸入訊息 ***************************************/
			ChatRoomService chatRoomSvc = new ChatRoomService();
			chatRoomSvc.responsemessage(adminAccountNumber, receiverAccountNumber, RoleType.CONSUMER.getCode(), messageContent);
		}
		
		if ("courior-insert".equals(action)) {

			int adminAccountNumber = 1;
			int accountNumber = 1;
			
			/*************************** 2.輸入訊息 ***************************************/
			ChatRoomService chatRoomSvc = new ChatRoomService();
			chatRoomSvc.sendmessage(adminAccountNumber, accountNumber, RoleType.COURIOR.getCode(), messageContent);
		}

		if ("courior-response".equals(action)) {

			int adminAccountNumber = 1;
			int receiverAccountNumber = Integer.parseInt(req.getParameter("receiverAccountNumber"));

			/*************************** 2.輸入訊息 ***************************************/
			ChatRoomService chatRoomSvc = new ChatRoomService();
			chatRoomSvc.responsemessage(adminAccountNumber, receiverAccountNumber,RoleType.COURIOR.getCode(), messageContent);
		}
		
		if ("merchant-insert".equals(action)) {

			int adminAccountNumber = 1;
			int accountNumber = 1;
			
			/*************************** 2.輸入訊息 ***************************************/
			ChatRoomService chatRoomSvc = new ChatRoomService();
			chatRoomSvc.sendmessage(adminAccountNumber, accountNumber, RoleType.MERCHANT.getCode(), messageContent);
		}

		if ("merchant-response".equals(action)) {

			int adminAccountNumber = 1;
			int receiverAccountNumber = Integer.parseInt(req.getParameter("receiverAccountNumber"));

			/*************************** 2.輸入訊息 ***************************************/
			ChatRoomService chatRoomSvc = new ChatRoomService();
			chatRoomSvc.responsemessage(adminAccountNumber, receiverAccountNumber,RoleType.MERCHANT.getCode(), messageContent);
		}
		
		if("findMessageContent".equals(action)) {
			int accountNumber = Integer.parseInt(req.getParameter("sender"));
			ChatRoomService chatRoomSvc = new ChatRoomService();
			chatRoomSvc.findMessageContent(accountNumber, RoleType.CONSUMER.getCode());
		}

	}
}
