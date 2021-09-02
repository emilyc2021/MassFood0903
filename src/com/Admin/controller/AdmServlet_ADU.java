package com.Admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Admin.model.AdminJDBCDAO;
import com.Admin.model.AdminService;
import com.Admin.model.AdminVO;



//adm.do1
public class AdmServlet_ADU extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//新增管理員
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action );
		   if ("insert".equals(action )) { 
								
			   String email = req.getParameter("addEmail").trim();
			   String password = req.getParameter("addPassword").trim();
					AdminVO admVO = new AdminVO();
					admVO.setEmail(email);
					admVO.setPassword(password);				
					/***************************2.開始新增資料***************************************/
					AdminService admSvc = new AdminService();
					admVO = admSvc.insert(email, password);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/admin/admin_list.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					return;
			}
//		   //修改管理員
		   if ("update".equals(action)) { 
				System.out.println(action);
				Integer amdno = new Integer(req.getParameter("admno").trim());
			   System.out.println(amdno);
			   String password = req.getParameter("changePassword").trim();
			   String email = req.getParameter("admemail").trim();
			   System.out.println(email+password);
			   AdminJDBCDAO dao = new AdminJDBCDAO();
					AdminVO admVO = new AdminVO();
					admVO.setAccountNumber(amdno);
					admVO.setEmail(email);	
					admVO.setPassword(password);	
					dao.update(admVO);					
					/***************************更新完成,準備轉交(Send the Success view)***********/
					String url = "/admin/admin_list.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					return;
			}
		   //刪除管理員
		   if ("delete".equals(action)) { 
			   System.out.println(action);
			   Integer amdno = new Integer(req.getParameter("admno"));
				/***************************2.開始刪除資料***************************************/
				AdminService admSvc = new AdminService();
				admSvc.delete(amdno);
				
	
						/***************************更新完成,準備轉交(Send the Success view)***********/
						String url = "/admin/admin_list.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
						successView.forward(req, res);				
						return;
				}
	}

}
