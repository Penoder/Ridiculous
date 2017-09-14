package com.penoder.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.penoder.service.TxtService;
import com.penoder.service.impl.TxtServiceImpl;

public class GetTxtFun extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");
		String userID = request.getParameter("userID");

		if (pageNum == null || "".equals(pageNum.replace("\\s*", "")))
			pageNum = "1";
		if (pageSize == null || "".equals(pageSize.replace("\\s*", "")))
			pageSize = "10";
		if (userID == null || "".equals(userID.replace("\\s*", "")))
			userID = "0";

		System.out.println("GetTxtFun ------> " + pageNum + " ---- " + pageSize + " ---- " + userID);

		TxtService txtService = new TxtServiceImpl();
		String result = txtService.getTxtFun(pageNum, pageSize, userID);
		
		System.out.println("GetTxtFun 返回结果 ------> " + result);

		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
