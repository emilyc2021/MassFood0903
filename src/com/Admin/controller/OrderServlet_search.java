package com.Admin.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Consumer.model.ConsumerVO;
import com.Emily.Model.MemberDAO;
import com.OrderDetail.model.OrderDetailJDBCDAO;
import com.OrderMaster.model.OrderMasterJDBCDAO;
import com.OrderMaster.model.OrderMasterVO;

//order.do
public class OrderServlet_search extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");//orderList
		System.out.println(action);
		//查訂單明細
		if ("orderList".equals(action)) {
			/*************************** 開始查詢資料 *****************************************/
			int orderNum= Integer.parseInt(req.getParameter("orderNumber"));
			OrderMasterJDBCDAO dao=new OrderMasterJDBCDAO();
			OrderMasterVO list=dao.findByOrderNumber(orderNum);
			/*************************** 查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderList", list); // 資料庫取出的物件,存入req
			String url = "/admin/admin_orderList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
			successView.forward(req, res);
			return;
		}
		//訂單複合查詢-消費者
		String type =req.getParameter("memberType");
		System.out.println(type);
		if ("orderSearch".equals(action)&&"M1".equals(type)) {			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				String OrderNumber =req.getParameter("OrderNumber").trim();;
				String Status =req.getParameter("Status");
				//區分會員帳號forConsumer
				String AccountNumber =req.getParameter("AccountNumber").trim();
				String ConsumerAccountNumber="";
				String IsNeedDelivery=req.getParameter("IsNeedDelivery");
				String OrderDate1 =req.getParameter("Orderdate1");
				String Orderdate2 =req.getParameter("Orderdate2");
				String OrderDate2="";
				System.out.println(Orderdate2);
				if(AccountNumber!="") {
					ConsumerAccountNumber=AccountNumber;
				}
				if(Orderdate2!="") {
					//先將字串日期轉成日期格式
					 SimpleDateFormat  dateFormatter= new SimpleDateFormat("yyyy-MM-dd");
				      Date date = dateFormatter.parse(Orderdate2);
//				       System.out.println(dateFormatter.format(date));
					//再將日期加一天			      
				       Date  date2 =new Date(date.getTime() + (1000 * 60 * 60 * 24));  
				       OrderDate2=dateFormatter.format(date2);
//					System.out.println(OrderDate2);
				}
				//將資料塞入map中 
				Map<String, String> map = new TreeMap<String, String>();
				  map.put("OrderNumber", OrderNumber);
				  map.put("Status", Status);
				  map.put("ConsumerAccountNumber", ConsumerAccountNumber);
				  map.put("IsNeedDelivery", IsNeedDelivery);
				  map.put("OrderDate1", OrderDate1);
				  map.put("OrderDate2", OrderDate2);
				/***************************2.開始複合查詢***************************************/
				MemberDAO M1  = new MemberDAO ();
				List<OrderMasterVO> list  = M1.order_search(map);				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.getSession().setAttribute("list", list); // 資料庫取出的list物件,存入request
				String url = "/admin/admin_orderSearchAfter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admin/admin_orderSearch.jsp");
				failureView.forward(req, res);
			}
			
		}
			
			//訂單複合查詢-商家
			if ("orderSearch".equals(action)&&"M2".equals(type)) {
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/***************************1.將輸入資料轉為Map**********************************/ 
					//採用Map<String,String[]> getParameterMap()的方法 
					//注意:an immutable java.util.Map 
					String OrderNumber =req.getParameter("OrderNumber").trim();;
					String Status =req.getParameter("Status");
					//區分會員帳號forMerchant
					String AccountNumber =req.getParameter("AccountNumber").trim();
					String MerchantAccountNumber="";
					String IsNeedDelivery=req.getParameter("IsNeedDelivery");
					String OrderDate1 =req.getParameter("Orderdate1");
					String Orderdate2 =req.getParameter("Orderdate2");
					String OrderDate2="";
					System.out.println(Orderdate2);
					if(AccountNumber!="") {
						MerchantAccountNumber=AccountNumber;
					}
					if(Orderdate2!="") {
						//先將字串日期轉成日期格式
						 SimpleDateFormat  dateFormatter= new SimpleDateFormat("yyyy-MM-dd");
					      Date date = dateFormatter.parse(Orderdate2);
//					       System.out.println(dateFormatter.format(date));
						//再將日期加一天			      
					       Date  date2 =new Date(date.getTime() + (1000 * 60 * 60 * 24));  
					       OrderDate2=dateFormatter.format(date2);
//						System.out.println(OrderDate2);
					}
					//將資料塞入map中 
					Map<String, String> map = new TreeMap<String, String>();
					  map.put("OrderNumber", OrderNumber);
					  map.put("Status", Status);
					  map.put("MerchantAccountNumber", MerchantAccountNumber);
					  map.put("IsNeedDelivery", IsNeedDelivery);
					  map.put("OrderDate1", OrderDate1);
					  map.put("OrderDate2", OrderDate2);
					/***************************2.開始複合查詢***************************************/
					MemberDAO M1  = new MemberDAO ();
					List<OrderMasterVO> list  = M1.order_search(map);				
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.getSession().setAttribute("list", list); // 資料庫取出的list物件,存入request
					String url = "/admin/admin_orderSearchAfter.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/admin_orderSearch.jsp");
					failureView.forward(req, res);
				}
				
				
			}
			//訂單複合查詢-外送員
			if ("orderSearch".equals(action)&&"M3".equals(type)) {	
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/***************************1.將輸入資料轉為Map**********************************/ 
					//採用Map<String,String[]> getParameterMap()的方法 
					//注意:an immutable java.util.Map 
					String OrderNumber =req.getParameter("OrderNumber").trim();;
					String Status =req.getParameter("Status");
					//區分會員帳號forCourior
					String AccountNumber =req.getParameter("AccountNumber").trim();
					String CouriorAccountNumber="";
					String IsNeedDelivery=req.getParameter("IsNeedDelivery");
					String OrderDate1 =req.getParameter("Orderdate1");
					String Orderdate2 =req.getParameter("Orderdate2");
					String OrderDate2="";
					System.out.println(Orderdate2);
					if(AccountNumber!="") {
						CouriorAccountNumber=AccountNumber;
					}
					if(Orderdate2!="") {
						//先將字串日期轉成日期格式
						 SimpleDateFormat  dateFormatter= new SimpleDateFormat("yyyy-MM-dd");
					      Date date = dateFormatter.parse(Orderdate2);
//					       System.out.println(dateFormatter.format(date));
						//再將日期加一天			      
					       Date  date2 =new Date(date.getTime() + (1000 * 60 * 60 * 24));  
					       OrderDate2=dateFormatter.format(date2);
//						System.out.println(OrderDate2);
					}
					//將資料塞入map中 
					Map<String, String> map = new TreeMap<String, String>();
					  map.put("OrderNumber", OrderNumber);
					  map.put("Status", Status);
					  map.put("CouriorAccountNumber", CouriorAccountNumber);
					  map.put("IsNeedDelivery", IsNeedDelivery);
					  map.put("OrderDate1", OrderDate1);
					  map.put("OrderDate2", OrderDate2);
					/***************************2.開始複合查詢***************************************/
					MemberDAO M1  = new MemberDAO ();
					List<OrderMasterVO> list  = M1.order_search(map);				
					/***************************3.查詢完成,準備轉交(Send the Success view)************/
					req.getSession().setAttribute("list", list); // 資料庫取出的list物件,存入request
					String url = "/admin/admin_orderSearchAfter.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/admin_orderSearch.jsp");
					failureView.forward(req, res);
				}
				
			}
			
			
		}
		
			
	}

