package com.Friend.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MapFriendStatus extends HttpServlet {

	@Override
	public void init() throws ServletException {
		ServletContext context = this.getServletContext();
		// 好友狀態
		Map<String, String> omMap = new HashMap<String, String>();
		omMap.put("0", "非好友");
		omMap.put("1", "好友");
		omMap.put(null, "請同意對方的交友邀請");

		context.setAttribute("omMap", omMap);

//		for (Entry<String, String> xx : actMap.entrySet()) {
//			xx.getKey();
//			xx.getValue();
//		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}