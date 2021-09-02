package com.Admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import com.Consumer.model.ConsumerVO;
import com.Emily.Model.ReportDAO;
import com.Emily.Model.ReportVO;


//report.do
public class ReportServlet_ajax extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		String type=req.getParameter("memberType");
		//報表查詢-商家
		if("reportSerach".equals(action)&&"M2".equals(type)) {
			String reportdate1=req.getParameter("reportdate1");
			int account=new Integer(req.getParameter("AccountNumber"));
			System.out.println(account);
			System.out.println(reportdate1);
			//查詢
			ReportDAO reportVO = new ReportDAO();
			//查應付帳款 訂單筆數
			List<ReportVO> orderList  = reportVO.report_ForOrderPayableM2(reportdate1, account);		
			//查完成訂單
			List<ReportVO> orderOK  = reportVO.report_ForOrderOKM2(reportdate1, account);	
			//查取消訂單
			List<ReportVO> orderCancel  = reportVO.report_ForOrderCancelM2(reportdate1, account);	
			//For圖表 1. 查每日訂單數 2. 查每日訂單總額 3.group by oderDate
			ArrayList orderamount=reportVO.report_ForOrderAmountM2(reportdate1, account);	
			ArrayList orders=reportVO.report_ForOrdersM2(reportdate1, account);	
			ArrayList orderduring=reportVO.report_ForOrderDuringM2(reportdate1, account);		
			
			//查詢完成
			req.setAttribute("reportdate1", reportdate1); 
			req.setAttribute("orderList", orderList); // 資料庫取出的list物件,存入request
			req.setAttribute("orderOK", orderOK); 
			req.setAttribute("orderCancel", orderCancel); 
			//陣列For圖表
			req.setAttribute("orderamount", orderamount); 
			req.setAttribute("orders", orders); 
			req.setAttribute("orderduring", orderduring); 

			String url = "/admin/admin_reportSearchAfterM2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			return;	
			
		}
		
		//報表查詢-外送員
				if("reportSerach".equals(action)&&"M3".equals(type)) {
					String reportdate1=req.getParameter("reportdate1");
					int account=new Integer(req.getParameter("AccountNumber"));
					System.out.println(account);
					System.out.println(reportdate1);
					//查詢
					ReportDAO reportVO = new ReportDAO();
					//查應付帳款 訂單筆數
					List<ReportVO> orderList  = reportVO.report_ForOrderPayableM3(reportdate1, account);		
					//查完成訂單
					List<ReportVO> orderOK  = reportVO.report_ForOrderOKM3(reportdate1, account);	
					//查取消訂單
//					List<ReportVO> orderCancel  = reportVO.report_ForOrderCancelM3(reportdate1, account);	
					
					//查詢完成
					req.setAttribute("reportdate1", reportdate1); 
					req.setAttribute("orderList", orderList); // 資料庫取出的list物件,存入request
					req.setAttribute("orderOK", orderOK); 
//					req.setAttribute("orderCancel", orderCancel); 
					String url = "/admin/admin_reportSearchAfterM3.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					return;	
					
				}		
		
	}

}
