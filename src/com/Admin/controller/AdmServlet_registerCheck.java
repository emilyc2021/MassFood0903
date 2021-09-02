package com.Admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Consumer.model.ConsumerJDBCDAO;
import com.Consumer.model.ConsumerService;
import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorJDBCDAO;
import com.Courior.model.CouriorVO;
import com.Merchant.model.MerchantJDBCDAO;
import com.Merchant.model.MerchantVO;
import com.SystemParameter.model.SystemParameterJDBCDAO;
import com.SystemParameter.model.SystemParameterVO;
import com.google.gson.Gson;
import com.utility.SystemParameterMember;

//register.do1
public class AdmServlet_registerCheck extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		/* =====註冊表單審核for會員通過不通過===== */
		// 單筆會員資料
		String name = req.getParameter("name");
		int account = Integer.parseInt(req.getParameter("account"));
		int status = Integer.parseInt(req.getParameter("status"));
		System.out.println(name + account);
		System.out.println(status);
		// 消費者-不通過
		if ("consumer".equals(name) && status == 0) {
			/*************************** 開始查詢資料 *****************************************/
			ConsumerJDBCDAO m1 = new ConsumerJDBCDAO();
			ConsumerVO consumerVO = m1.findByAccountNumber(account);

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("consumerVO", consumerVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/admin_registerForm01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		// 消費者-待審核
		if ("consumer".equals(name) && status == 1) {
			/*************************** 開始查詢資料 *****************************************/
			ConsumerJDBCDAO m1 = new ConsumerJDBCDAO();
			ConsumerVO consumerVO = m1.findByAccountNumber(account);

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("consumerVO", consumerVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/admin_registerCheck01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		// 消費者-通過
		if ("consumer".equals(name) && status == 2) {
			/*************************** 開始查詢資料 *****************************************/
			ConsumerJDBCDAO m1 = new ConsumerJDBCDAO();
			ConsumerVO consumerVO = m1.findByAccountNumber(account);

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("consumerVO", consumerVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/admin_registerForm01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}

		// 商家-不通過
		if ("merchant".equals(name) && status == 0) {
			/*************************** 開始查詢資料 *****************************************/
			MerchantJDBCDAO m2 = new MerchantJDBCDAO();
			MerchantVO merchantVO = m2.findByAccountNumber(account);
			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("merchantVO", merchantVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/admin_registerForm02.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		// 商家-待審核
		if ("merchant".equals(name) && status == 1) {
			/*************************** 開始查詢資料 *****************************************/
			MerchantJDBCDAO m2 = new MerchantJDBCDAO();
			MerchantVO merchantVO = m2.findByAccountNumber(account);
			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("merchantVO", merchantVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/admin_registerCheck02.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		// 商家-通過
		if ("merchant".equals(name) && status == 2) {
			/*************************** 開始查詢資料 *****************************************/
			MerchantJDBCDAO m2 = new MerchantJDBCDAO();
			MerchantVO merchantVO = m2.findByAccountNumber(account);
			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("merchantVO", merchantVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/admin_registerForm02.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}

		// 外送員-不通過
		if ("courior".equals(name) && status == 0) {
			/*************************** 開始查詢資料 *****************************************/
			CouriorJDBCDAO m3 = new CouriorJDBCDAO();
			CouriorVO couriorVO = m3.findByAccountNumber(account);
			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couriorVO", couriorVO); // 資料庫取出的empVO物件,存入req
			
			//ZipCode by 修哥
			Gson myGson = new Gson();
			SystemParameterJDBCDAO mySystemParameterJDBCDAO =new SystemParameterJDBCDAO();
			String displayZipCodeDescription = "";
			ArrayList<String> couriorZipCodeList= myGson.fromJson(couriorVO.getZipCode(), ArrayList.class);
			List<SystemParameterVO> zipCodeList=mySystemParameterJDBCDAO.findByTypeName(SystemParameterMember.ZIPCODE.getTypeName());
				
			if(couriorZipCodeList.size()>0) {
				for(SystemParameterVO zipCode:zipCodeList ) {
					for(String couriorZipCode:couriorZipCodeList) {
						if(zipCode.getCode().equals(couriorZipCode)) {
							if(displayZipCodeDescription == "") {
								displayZipCodeDescription=zipCode.getDescription();
							} else {
								displayZipCodeDescription+="，"+zipCode.getDescription();
							}
						}
					}
				}
			}			
			req.setAttribute("displayZipCodeDescription",displayZipCodeDescription);
			
			String url = "/admin/admin_registerForm03.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		// 外送員-待審核
		if ("courior".equals(name) && status == 1) {
			/*************************** 開始查詢資料 *****************************************/
			CouriorJDBCDAO m3 = new CouriorJDBCDAO();
			CouriorVO couriorVO = m3.findByAccountNumber(account);
			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couriorVO", couriorVO); // 資料庫取出的empVO物件,存入req
			//ZipCode by 修哥
			Gson myGson = new Gson();
			SystemParameterJDBCDAO mySystemParameterJDBCDAO =new SystemParameterJDBCDAO();
			String displayZipCodeDescription = "";
			ArrayList<String> couriorZipCodeList= myGson.fromJson(couriorVO.getZipCode(), ArrayList.class);
			List<SystemParameterVO> zipCodeList=mySystemParameterJDBCDAO.findByTypeName(SystemParameterMember.ZIPCODE.getTypeName());
				
			if(couriorZipCodeList.size()>0) {
				for(SystemParameterVO zipCode:zipCodeList ) {
					for(String couriorZipCode:couriorZipCodeList) {
						if(zipCode.getCode().equals(couriorZipCode)) {
							if(displayZipCodeDescription == "") {
								displayZipCodeDescription=zipCode.getDescription();
							} else {
								displayZipCodeDescription+="，"+zipCode.getDescription();
							}
						}
					}
				}
			}			
			req.setAttribute("displayZipCodeDescription",displayZipCodeDescription);		
			
			String url = "/admin/admin_registerCheck03.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		// 外送員-通過
		if ("courior".equals(name) && status == 2) {
			/*************************** 開始查詢資料 *****************************************/
			CouriorJDBCDAO m3 = new CouriorJDBCDAO();
			CouriorVO couriorVO = m3.findByAccountNumber(account);
			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couriorVO", couriorVO); // 資料庫取出的empVO物件,存入req
			
			//ZipCode by 修哥
			Gson myGson = new Gson();
			SystemParameterJDBCDAO mySystemParameterJDBCDAO =new SystemParameterJDBCDAO();
			String displayZipCodeDescription = "";
			ArrayList<String> couriorZipCodeList= myGson.fromJson(couriorVO.getZipCode(), ArrayList.class);
			List<SystemParameterVO> zipCodeList=mySystemParameterJDBCDAO.findByTypeName(SystemParameterMember.ZIPCODE.getTypeName());
				
			if(couriorZipCodeList.size()>0) {
				for(SystemParameterVO zipCode:zipCodeList ) {
					for(String couriorZipCode:couriorZipCodeList) {
						if(zipCode.getCode().equals(couriorZipCode)) {
							if(displayZipCodeDescription == "") {
								displayZipCodeDescription=zipCode.getDescription();
							} else {
								displayZipCodeDescription+="，"+zipCode.getDescription();
							}
						}
					}
				}
			}			
			req.setAttribute("displayZipCodeDescription",displayZipCodeDescription);
			String url = "/admin/admin_registerForm03.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}

	}

}
