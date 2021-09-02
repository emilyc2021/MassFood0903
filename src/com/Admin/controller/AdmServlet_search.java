package com.Admin.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.SystemParameter.model.SystemParameterJDBCDAO;
import com.SystemParameter.model.SystemParameterVO;
import com.google.gson.Gson;
import com.mysql.cj.xdevapi.DbDoc;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.JsonString;
import com.utility.SystemParameterMember;

//adm.do
public class AdmServlet_search extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		/*=====單筆查詢for會員修改=====*/
		// 單筆會員資料
		String name = req.getParameter("name");
		int account = Integer.parseInt(req.getParameter("account"));
		System.out.println(name+account);
		// 消費者
		if ("consumer".equals(name)) {
			/*************************** 開始查詢資料 *****************************************/
			ConsumerService consumerSvc = new ConsumerService();
			ConsumerVO consumerVO = consumerSvc.findByAccountNumber(account);

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("consumerVO", consumerVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/admin_memberEdit01.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		// 商家
		if ("merchant".equals(name)) {
			/*************************** 開始查詢資料 *****************************************/
			MerchantService merchantSvc = new MerchantService();
			MerchantVO merchantVO = merchantSvc.findByAccountNumber(account);

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("merchantVO", merchantVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/admin_memberEdit02.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		// 外送員
		if ("courior".equals(name)) {
			/*************************** 開始查詢資料 *****************************************/
			CouriorService couriorSvc = new CouriorService();
			CouriorVO couriorVO = couriorSvc.findByAccountNumber(account);

			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couriorVO", couriorVO); // 資料庫取出的物件,存入req
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
			
			
			String url = "/admin/admin_memberEdit03.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		

		
	
	}
	

}
