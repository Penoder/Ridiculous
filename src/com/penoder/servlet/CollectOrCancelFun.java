package com.penoder.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.penoder.service.CollectService;
import com.penoder.service.LikeService;
import com.penoder.service.impl.CollectServiceImpl;
import com.penoder.service.impl.LikeServiceImpl;

/**
 * Servlet implementation class CollectOrCancleFun
 */
//@WebServlet("/GetFun/CollectOrCancleFun")	// 这个注解相当于是web.xml文件中的路径配置
public class CollectOrCancelFun extends HttpServlet {
//	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String userId = request.getParameter("userId");
		String funId = request.getParameter("funId");
		int funType = Integer.parseInt(request.getParameter("funType"));
		boolean funStatus = Boolean.parseBoolean(request.getParameter("funStatus"));

		CollectService collectService = new CollectServiceImpl();
		String resultStr = "{\"status\":1,\"message\":\"请求失败\",\"datas\":null}";

		switch (funType) {
		case 1: // GIF
			resultStr = collectService.collectOrCancelGif(userId, funId, funStatus);
			break;
		case 2: // IMG
			resultStr = collectService.collectOrCancelImg(userId, funId, funStatus);
			break;
		case 3: // Txt
			resultStr = collectService.collectOrCancelTxt(userId, funId, funStatus);
			break;
		}

		PrintWriter out = response.getWriter();
		out.println(resultStr);
		out.flush();
		out.close();
	}
	
}
