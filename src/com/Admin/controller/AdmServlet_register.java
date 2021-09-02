package com.Admin.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorVO;
import com.Emily.Model.MemberDAO;
import com.Merchant.model.MerchantVO;

//register.do
public class AdmServlet_register extends HttpServlet {


	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		/*=====註冊表單複合查詢for register_Search=====*/
		String type = req.getParameter("memberType");
		String action = req.getParameter("action");
//		String date = req.getParameter("date");
//		System.out.println(type+action+date);
		
		// M1 消費者
				if ("M1".equals(type) && "registerSearch".equals(action)) {

						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);

						try {
							
							/***************************1.將輸入資料轉為Map**********************************/ 
							//採用Map<String,String[]> getParameterMap()的方法 
							//注意:an immutable java.util.Map 
							Map<String, String[]> map = req.getParameterMap();
							System.out.println(map);
							/***************************2.開始複合查詢***************************************/
							MemberDAO m1  = new MemberDAO ();
							List<ConsumerVO> list  = m1.register_M1(map);
							System.out.println(list);
							/***************************3.查詢完成,準備轉交(Send the Success view)************/
							req.getSession().setAttribute("list_m1", list); // 資料庫取出的list物件,存入request
							String url = "/admin/admin_registerSearch01.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
							successView.forward(req, res);
							
							/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/admin/admin_registerSearch.jsp");
							failureView.forward(req, res);
						}
					}		
		
				// M2 商家
				if ("M2".equals(type) && "registerSearch".equals(action)) {
			
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);

						try {
							
							/***************************1.將輸入資料轉為Map**********************************/ 
							//採用Map<String,String[]> getParameterMap()的方法 
							//注意:an immutable java.util.Map 
							Map<String, String[]> map = req.getParameterMap();
							System.out.println(map);
							/***************************2.開始複合查詢***************************************/
							MemberDAO M2  = new MemberDAO ();
							List<MerchantVO> list  = M2.register_M2(map);
							
							/***************************3.查詢完成,準備轉交(Send the Success view)************/
							req.getSession().setAttribute("list_m2", list); // 資料庫取出的list物件,存入request
							String url = "/admin/admin_registerSearch02.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
							successView.forward(req, res);
							
							/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/admin/admin_registerSearch.jsp");
							failureView.forward(req, res);
						}
					}		
				
				// M3 外送員
				if ("M3".equals(type) && "registerSearch".equals(action)) {
				
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);

						try {
							
							/***************************1.將輸入資料轉為Map**********************************/ 
							//採用Map<String,String[]> getParameterMap()的方法 
							//注意:an immutable java.util.Map 
							Map<String, String[]> map = req.getParameterMap();
							System.out.println(map);
							/***************************2.開始複合查詢***************************************/
							MemberDAO M3  = new MemberDAO ();
							List<CouriorVO> list  = M3.register_M3(map);
							
							/***************************3.查詢完成,準備轉交(Send the Success view)************/
							req.getSession().setAttribute("list_m3", list); // 資料庫取出的list物件,存入request
							String url = "/admin/admin_registerSearch03.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
							successView.forward(req, res);
							
							/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/admin/admin_registerSearch.jsp");
							failureView.forward(req, res);
						}
					}		
		
		
	}

}
