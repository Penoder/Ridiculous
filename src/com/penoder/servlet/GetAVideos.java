package com.penoder.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.penoder.service.AVideoService;
import com.penoder.service.impl.AVideoServiceImpl;

/**
 * 该 Servlet 用于从 avideo 表中获取数据
 * 
 * @author Penoder
 */
public class GetAVideos extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String pageNum = request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize");

		if (pageNum == null || "".equals(pageNum.replace("\\s*", "")))
			pageNum = "1";
		if (pageSize == null || "".equals(pageSize.replace("\\s*", "")))
			pageSize = "10";

		System.out.println("GetAVideo ------> " + pageNum + " ---- " + pageSize);

		AVideoService videoService = new AVideoServiceImpl();
		String result = videoService.selectVideo(pageNum, pageSize);

		System.out.println("GetAVideo 返回结果 ------> " + result);

		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
}
