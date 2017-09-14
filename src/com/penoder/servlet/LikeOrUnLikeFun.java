package com.penoder.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.penoder.service.LikeService;
import com.penoder.service.impl.LikeServiceImpl;

/**
 * 点赞（取消点赞）接口： 用户传递的参数有 1. 用户ID、 2. 数据ID（可能是Gif、Txt、Img） 3.
 * 数据类型（是属于Gif、Txt、还是Img）、 4. 点赞状态（点赞还是取消点赞）
 * 
 * 另外，点赞的接口和收藏的接口其实是类似的逻辑，只是操作的数据表不一样而已
 * 
 * @author Penoder
 */
public class LikeOrUnLikeFun extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String userId = request.getParameter("userId");
		String funId = request.getParameter("funId");
		int funType = Integer.parseInt(request.getParameter("funType"));
		boolean funStatus = Boolean.parseBoolean(request.getParameter("funStatus"));

		LikeService likeService = new LikeServiceImpl();
		String resultStr = "{\"status\":0,\"message\":\"查询成功\",\"datas\":null}";

		switch (funType) {
		case 1: // GIF
			resultStr = likeService.likeOrUnLikeGif(userId, funId, funStatus);
			break;
		case 2: // IMG
			resultStr = likeService.likeOrUnLikeImg(userId, funId, funStatus);
			break;
		case 3: // Txt
			resultStr = likeService.likeOrUnLikeTxt(userId, funId, funStatus);
			break;
		}

		PrintWriter out = response.getWriter();
		out.println(resultStr);
		out.flush();
		out.close();
	}

}
