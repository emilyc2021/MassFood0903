package com.Friend.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.Consumer.model.*;
import com.Friend.model.*;
import com.utility.FriendStatus;

public class FriendSelvet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
//		String mail = req.getParameter("mail");
//		System.out.println(action);
	
		
		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("email");
				if (str == null || str.trim().length() == 0) {
					res.setCharacterEncoding("UTF-8");
					res.setContentType("application/json;charset=UTF-8");
					PrintWriter out = res.getWriter();
			        out.print("沒有輸入Email");
			        out.flush();
			        return;
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/listAllFriend.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String email = null;
				try {
					email = new String(str);
				} catch (Exception e) {
//					errorMsgs.add("格式不正確");
					res.setCharacterEncoding("UTF-8");
					res.setContentType("application/json;charset=UTF-8");
					PrintWriter out = res.getWriter();
			        out.print("格式不正確");
			        out.flush();
			        return;
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/listAllFriend.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/************************* 2.開始查詢資料 ***************************/
				ConsumerJDBCDAO dao = new ConsumerJDBCDAO();
				ConsumerVO consumerVO = dao.findByEmail(email);
				if (consumerVO == null) {
//					errorMsgs.add("查無資料");
					res.setCharacterEncoding("UTF-8");
					res.setContentType("application/json;charset=UTF-8");
					PrintWriter out = res.getWriter();
			        out.print("查無資料");
			        out.flush();
			        return;
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/listAllFriend.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/************************ 3.AddFriend的部分 **********************************/
				FriendVO friendVO = new FriendVO();
				Integer accountNumber = 1;
			
				FriendService friendSvc = new FriendService();
				friendVO = friendSvc.addFriend(accountNumber, consumerVO.getAccountNumber(),
						FriendStatus.STRANGER.getCode());
				

				/********** 4.修改完成,準備轉交(Send the Success view) *************/
//				String url = "listAllFriend.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
				res.setCharacterEncoding("UTF-8");
				res.setContentType("application/json;charset=UTF-8");
				PrintWriter out = res.getWriter();
		        out.print("新增成功");
		        out.flush();
		        return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/listAllFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				FriendVO friendVO = new FriendVO();
				Integer accountNumber = new Integer(req.getParameter("deleteAccountNumber"));
				Integer friendAccountNumber = new Integer(req.getParameter("deletefriendAccountNumber"));
							
				/***************************2.開始刪除資料***************************************/
				FriendService friendSvc = new FriendService();
				friendVO = friendSvc.dismiss(accountNumber,friendAccountNumber,
						FriendStatus.STRANGER.getCode());
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "friendList/listAllFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/listAllFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				FriendVO friendVO = new FriendVO();
				Integer accountNumber = new Integer(req.getParameter("agreeAccountNumber"));
				Integer friendAccountNumber = new Integer(req.getParameter("agreefriendAccountNumber"));
//				System.out.println(friendAccountNumber);
				
				/***************************2.開始更新資料***************************************/
				FriendService friendSvc = new FriendService();
				friendVO = friendSvc.agree(accountNumber,friendAccountNumber,
						FriendStatus.FRIEND.getCode());
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "friendList/listAllFriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/listAllFriend.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}