package com.Admin.controller;

import java.io.IOException;
import java.sql.Timestamp;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.Consumer.model.ConsumerJDBCDAO;
import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorJDBCDAO;
import com.Courior.model.CouriorVO;
import com.Emily.Model.Admin1DAO;
import com.Merchant.model.MerchantJDBCDAO;
import com.Merchant.model.MerchantVO;

//member.do
public class MemberServlet_ADU extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");//ConsumerUpdate MerchantUpdate CouriorUpdate
		Integer account = new Integer(req.getParameter("account"));//會員編號

		// 修改會員資料--消費者
		if ("ConsumerUpdate".equals(action)) {
			String password = req.getParameter("password").trim();
			String id = req.getParameter("id").trim();
			Long datetime = System.currentTimeMillis();	
			Timestamp timestamp = new Timestamp(datetime);	
			int admin= new Integer(req.getParameter("updateAdmin"));
			boolean enable = Boolean.parseBoolean(req.getParameter("enable"));
			int status= new Integer(req.getParameter("status"));
			  //更新資料
			Admin1DAO dao = new Admin1DAO();
			ConsumerVO consumerVO = new ConsumerVO();
			consumerVO.setPassword(password);
			consumerVO.setIdentityNumber(id);
			consumerVO.setLastUpdateDatetime(timestamp);
			consumerVO.setLastUpdateAccountNumber(admin);
			consumerVO.setIsEnable(enable);
			consumerVO.setStatus(status);
			consumerVO.setAccountNumber(account);
			dao.updateM1(consumerVO);
			
			//查詢更新後的資料再回傳
			ConsumerJDBCDAO m1 = new ConsumerJDBCDAO();
			ConsumerVO list =m1.findByAccountNumber(account);			
			/*************************** 更新完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("consumerVO", list); 
			String url = "/admin/admin_memberEdit01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
			return;
		}

		// 修改會員資料--商家
				if ("MerchantUpdate".equals(action)) {
					String password = req.getParameter("password").trim();
					String id = req.getParameter("id").trim();//統編
					String pid = req.getParameter("pid").trim();//聯絡人身分證字號
					Long datetime = System.currentTimeMillis();	
					Timestamp timestamp = new Timestamp(datetime);	
					int admin= new Integer(req.getParameter("updateAdmin"));
					boolean enable = Boolean.parseBoolean(req.getParameter("enable"));
					int status= new Integer(req.getParameter("status"));
					  //更新資料
					Admin1DAO dao = new Admin1DAO();
					MerchantVO merchantVO = new MerchantVO();
					merchantVO.setPassword(password);
					merchantVO.setIdentityNumber(id);
					merchantVO.setContactPersonIdentityNumber(pid);
					merchantVO.setLastUpdateDatetime(timestamp);
					merchantVO.setLastUpdateAccountNumber(admin);
					merchantVO.setIsEnable(enable);
					merchantVO.setStatus(status);
					merchantVO.setAccountNumber(account);
					dao.updateM2(merchantVO);
					
					//查詢更新後的資料再回傳
					MerchantJDBCDAO m2 = new MerchantJDBCDAO();
					MerchantVO list =m2.findByAccountNumber(account);			
					/*************************** 更新完成,準備轉交(Send the Success view) ***********/
					req.setAttribute("merchantVO", list); 
					String url = "/admin/admin_memberEdit02.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
					successView.forward(req, res);
					return;
				}
				
				// 修改會員資料--外送員
				if ("CouriorUpdate".equals(action)) {
					String password = req.getParameter("password").trim();
					String id = req.getParameter("id").trim();
					Long datetime = System.currentTimeMillis();	
					Timestamp timestamp = new Timestamp(datetime);	
					int admin= new Integer(req.getParameter("updateAdmin"));
					boolean enable = Boolean.parseBoolean(req.getParameter("enable"));
					int status= new Integer(req.getParameter("status"));
					  //更新資料
					Admin1DAO dao = new Admin1DAO();
					CouriorVO couriorVO = new CouriorVO();
					couriorVO.setPassword(password);
					couriorVO.setIdentityNumber(id);
					couriorVO.setLastUpdateDatetime(timestamp);
					couriorVO.setLastUpdateAccountNumber(admin);
					couriorVO.setIsEnable(enable);
					couriorVO.setStatus(status);
					couriorVO.setAccountNumber(account);
					dao.updateM3(couriorVO);
					
					//查詢更新後的資料再回傳
					CouriorJDBCDAO m3 = new CouriorJDBCDAO();
					CouriorVO list =m3.findByAccountNumber(account);			
					/*************************** 更新完成,準備轉交(Send the Success view) ***********/
					req.setAttribute("couriorVO", list); 
					String url = "/admin/admin_memberEdit03.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
					successView.forward(req, res);
					return;
				
	}
	
	
	}
}
