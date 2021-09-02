package com.Admin.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Consumer.model.ConsumerJDBCDAO;
import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorJDBCDAO;
import com.Courior.model.CouriorVO;
import com.Emily.Model.Admin1DAO;
import com.Emily.Model.MailService;
import com.Emily.Model.RegisterDAO;
import com.Merchant.model.MerchantJDBCDAO;
import com.Merchant.model.MerchantVO;

//register.do2
public class AdmServlet_registerUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("registerAdmin");//pass fail
		String type = req.getParameter("memberType"); //consumer merchant courior
		String email = req.getParameter("Email"); 
		int admin= new Integer(req.getParameter("updateAdmin"));
		
		//註冊表單審核-消費者pass
		if ("consumer".equals(type)&&"pass".equals(action)) {
			//註冊通過信
			MailService mailService = new MailService();
		      mailService.sendMailpass(email);
		      //更新資料
			Long datetime = System.currentTimeMillis();	
			Timestamp timestamp = new Timestamp(datetime);					
			RegisterDAO dao = new RegisterDAO();
			ConsumerVO consumerVO = new ConsumerVO();
			consumerVO.setLastUpdateDatetime(timestamp);
			consumerVO.setLastUpdateAccountNumber(admin);
			consumerVO.setIsEnable(true);
			consumerVO.setStatus(2);
			consumerVO.setEmail(email);
			dao.updateRegisterM1(consumerVO);
			//查詢更新後的資料再回傳
			ConsumerJDBCDAO m1 = new ConsumerJDBCDAO();
			ConsumerVO list =m1.findByEmail(email);
			/*************************** 更新完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("consumerVO", list); 
			String url = "/admin/admin_registerForm01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
			return;		
			
		}
		//註冊表單審核-消費者fail
		if ("consumer".equals(type)&&"fail".equals(action)) {
			//註冊通過信
			MailService mailService = new MailService();
		      mailService.sendMailfail(email);
		      //更新資料
			Long datetime = System.currentTimeMillis();	
			Timestamp timestamp = new Timestamp(datetime);					
			RegisterDAO dao = new RegisterDAO();
			ConsumerVO consumerVO = new ConsumerVO();
			consumerVO.setLastUpdateDatetime(timestamp);
			consumerVO.setLastUpdateAccountNumber(admin);
			consumerVO.setIsEnable(false);
			consumerVO.setStatus(0);
			consumerVO.setEmail(email);
			dao.updateRegisterM1(consumerVO);
//			//查詢更新後的資料再回傳
			ConsumerJDBCDAO m1 = new ConsumerJDBCDAO();
			ConsumerVO list =m1.findByEmail(email);
			/*************************** 更新完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("consumerVO", list); 
			String url = "/admin/admin_registerForm01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
			return;		
		}
		//註冊表單審核-商家pass
		if ("merchant".equals(type)&&"pass".equals(action)) {
			//註冊通過信
			MailService mailService = new MailService();
		      mailService.sendMailpass(email);
		      //更新資料
			Long datetime = System.currentTimeMillis();	
			Timestamp timestamp = new Timestamp(datetime);					
			RegisterDAO dao = new RegisterDAO();
			MerchantVO merchantVO = new MerchantVO();
			merchantVO.setLastUpdateDatetime(timestamp);
			merchantVO.setLastUpdateAccountNumber(admin);
			merchantVO.setIsEnable(true);
			merchantVO.setStatus(2);
			merchantVO.setEmail(email);
			dao.updateRegisterM2(merchantVO);
			//查詢更新後的資料再回傳
			MerchantJDBCDAO m2 = new MerchantJDBCDAO();
			MerchantVO list =m2.findByEmail(email);
			/*************************** 更新完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("merchantVO", list); 
			String url = "/admin/admin_registerForm02.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
			return;	
		}
		//註冊表單審核-商家fail
		if ("merchant".equals(type)&&"fail".equals(action)) {
			//註冊通過信
			MailService mailService = new MailService();
		      mailService.sendMailfail(email);
		      //更新資料
			Long datetime = System.currentTimeMillis();	
			Timestamp timestamp = new Timestamp(datetime);					
			RegisterDAO dao = new RegisterDAO();
			MerchantVO merchantVO = new MerchantVO();
			merchantVO.setLastUpdateDatetime(timestamp);
			merchantVO.setLastUpdateAccountNumber(admin);
			merchantVO.setIsEnable(false);
			merchantVO.setStatus(0);
			merchantVO.setEmail(email);
			dao.updateRegisterM2(merchantVO);
//			//查詢更新後的資料再回傳
			MerchantJDBCDAO m2 = new MerchantJDBCDAO();
			MerchantVO list =m2.findByEmail(email);
			/*************************** 更新完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("merchantVO", list); 
			String url = "/admin/admin_registerForm02.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
			return;		
		}
		//註冊表單審核-外送員pass
		if ("courior".equals(type)&&"pass".equals(action)) {
			//註冊通過信
			MailService mailService = new MailService();
		      mailService.sendMailpass(email);
		      //更新資料
			Long datetime = System.currentTimeMillis();	
			Timestamp timestamp = new Timestamp(datetime);					
			RegisterDAO dao = new RegisterDAO();
			CouriorVO couriorVO = new CouriorVO();
			couriorVO.setLastUpdateDatetime(timestamp);
			couriorVO.setLastUpdateAccountNumber(admin);
			couriorVO.setIsEnable(true);
			couriorVO.setStatus(2);
			couriorVO.setEmail(email);
			dao.updateRegisterM3(couriorVO);
			//查詢更新後的資料再回傳
			CouriorJDBCDAO m3 = new CouriorJDBCDAO();
			CouriorVO list =m3.findByEmail(email);
			/*************************** 更新完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("couriorVO", list); 
			String url = "/admin/admin_registerForm03.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
			return;	
		}
		//註冊表單審核-外送員fail
		if ("courior".equals(type)&&"fail".equals(action)) {
			//註冊通過信
			MailService mailService = new MailService();
		      mailService.sendMailfail(email);
		      //更新資料
			Long datetime = System.currentTimeMillis();	
			Timestamp timestamp = new Timestamp(datetime);					
			RegisterDAO dao = new RegisterDAO();
			CouriorVO couriorVO = new CouriorVO();
			couriorVO.setLastUpdateDatetime(timestamp);
			couriorVO.setLastUpdateAccountNumber(admin);
			couriorVO.setIsEnable(false);
			couriorVO.setStatus(0);
			couriorVO.setEmail(email);
			dao.updateRegisterM3(couriorVO);
//			//查詢更新後的資料再回傳
			CouriorJDBCDAO m3 = new CouriorJDBCDAO();
			CouriorVO list =m3.findByEmail(email);
			/*************************** 更新完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("couriorVO", list); 
			String url = "/admin/admin_registerForm03.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
			successView.forward(req, res);
			return;		
		}
		
		
	
		
	}

}
