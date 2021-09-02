package com.Merchant.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Emily.Model.ReportDAO;

//merchant.do
public class MerchantServlet_chart extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		System.out.println(action);
		if("reportMerchant".equals(action)) {
			String reportdate1=req.getParameter("reportdate1");
			int account=new Integer(req.getParameter("account"));
			System.out.println(reportdate1+account);
			//查詢
			ReportDAO reportVO = new ReportDAO();
			//For圖表 1. 查每日訂單總額 2.group by oderDate
			ArrayList orderamount=reportVO.report_ForOrderAmountM2(reportdate1, account);	
			ArrayList orderduring=reportVO.report_ForOrderDuringM2(reportdate1, account);
			System.out.println(1111111);
			System.out.println(orderamount);
			System.out.println(orderduring);
			//For圖表 1. 熱門餐點 2. 點餐次數
			ArrayList itemName=reportVO.report_ForPopMealM2(reportdate1, account);		
			ArrayList popItemTotal=reportVO.report_ForPopMealTotalM2(reportdate1, account);		
			System.out.println(itemName);
			System.out.println( popItemTotal);
			//陣列For圖表 1. 年月2. 當日訂餐總額3. 日期4.餐點名稱5.點餐次數
			req.setAttribute("reportdate1", reportdate1); 
			req.setAttribute("orderamount", orderamount); 
			req.setAttribute("orderduring", orderduring); 
			req.setAttribute("itemName", itemName); 
			req.setAttribute("popItemTotal", popItemTotal); 
			//轉交
			String url = "/merchant/merchant_reportAfter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;	
			
		}
		
	}

}
