package com.Admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Admin.model.AdminVO;
import com.Consumer.model.ConsumerJDBCDAO;
import com.Consumer.model.ConsumerService;
import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorJDBCDAO;
import com.Courior.model.CouriorService;
import com.Courior.model.CouriorVO;
import com.Emily.Model.Admin1DAO;
import com.Merchant.model.MerchantJDBCDAO;
import com.Merchant.model.MerchantService;
import com.Merchant.model.MerchantVO;
import com.mysql.cj.Session;

//adm.do2
public class AdmServlet_login extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		// 登入
		String name = req.getParameter("adminXX");
		
		if ("login".equals(name)) {
			/* 開始查詢資料 */
	
			String admEmail = req.getParameter("userEmail");
			String admPassword = req.getParameter("userPassword");
			Admin1DAO dao = new Admin1DAO();
			AdminVO login = dao.findLogin(admEmail, admPassword);
			/* 沒有符合回登入頁面 */
			if (login == null) {
				String url = "/admin/admin_login.jsp";
				String message="請輸入正確的帳號及密碼";
				req.setAttribute("message", message);
				RequestDispatcher failureView = req.getRequestDispatcher(url);// 回登入頁面
				failureView.forward(req, res);
				return;
			}
			/* 查詢完成準備轉交(Send the Success view) */
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
			System.out.println(login);
			// Send the Success view
			String url = "/admin/admin_index.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
			successView.forward(req, res);
			return;
		}
		if ("logout".equals(name)) {
			String url = "/admin/admin_login.html";
			HttpSession session = req.getSession();
			session.removeAttribute("login");//管理員物件
			session.invalidate();
			RequestDispatcher successView = req.getRequestDispatcher(url);// 轉登入頁面
			successView.forward(req, res);
			return;
		}
	}
}
