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

import com.Consumer.model.ConsumerService;
import com.Consumer.model.ConsumerVO;
import com.Courior.model.CouriorService;
import com.Courior.model.CouriorVO;
import com.Emily.Model.MemberDAO;
import com.Merchant.model.MerchantService;
import com.Merchant.model.MerchantVO;

//member.do1
public class MemberServlet_search extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		/*=====會員複合查詢for Admin_Search=====*/
		String type = req.getParameter("memberType");
		String action = req.getParameter("action");

//		String accountStatus = req.getParameter("accountStatus");
//		String memberNum = req.getParameter("memberNum");
//		String memberName = req.getParameter("memberName");
//		String email = req.getParameter("email");
		
		// M1 消費者
		if ("M1".equals(type) && "memberSearch".equals(action)) {

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
					MemberDAO M1  = new MemberDAO ();
					List<ConsumerVO> list  = M1.getAll_M1(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.getSession().setAttribute("list_m1", list); // 資料庫取出的list物件,存入request
					String url = "/admin/admin_memberSearch01.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/admin_memberSearch.jsp");
					failureView.forward(req, res);
				}
			}		
			
		// M2 商家
		if ("M2".equals(type) && "memberSearch".equals(action)) {

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
					List<MerchantVO> list  = M2.getAll_M2(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.getSession().setAttribute("list_m2", list); // 資料庫取出的list物件,存入request
					String url = "/admin/admin_memberSearch02.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/admin_memberSearch.jsp");
					failureView.forward(req, res);
				}
			}		
		// M3 外送員
		if ("M3".equals(type) && "memberSearch".equals(action)) {

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
					List<CouriorVO> list  = M3.getAll_M3(map);
					
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.getSession().setAttribute("list_m3", list); // 資料庫取出的list物件,存入request
					String url = "/admin/admin_memberSearch03.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/admin_memberSearch.jsp");
					failureView.forward(req, res);
				}
			}		
		
		
		
		}
	}


