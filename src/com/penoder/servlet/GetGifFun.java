package com.penoder.servlet;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.penoder.service.GifService;
import com.penoder.service.impl.GifServiceImpl;

/**
 * 获取GIF糗事数据，由于前段是采用分页的方式来取数据的，所以这边加载数据也需要分页处理 
 * 
 * @author Penoder
 */
public class GetGifFun extends HttpServlet {

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

		System.out.println("GetGifFun ------> " + pageNum + " ---- " + pageSize + " ---- " + userID);

		GifService gifService = new GifServiceImpl();
		String result = gifService.selectGif(pageNum, pageSize, userID);
		
		System.out.println("GetGifFun 返回结果 ------> " + result);

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
