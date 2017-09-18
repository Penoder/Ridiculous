package com.penoder.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.penoder.bean.UserInfo;
import com.penoder.service.UserService;
import com.penoder.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterUser
 */
//@WebServlet(name = "GetUser/RegisterUser", urlPatterns = { "/GetUser/RegisterUser" })
public class RegisterUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String userInfoJson = req.getParameter("USERINFO");
		
		System.out.println("RegisterUser   ---  需要注册的用户信息是:  \n" + userInfoJson);
		
		Gson gson = new Gson();
		UserInfo userInfo = gson.fromJson(userInfoJson, UserInfo.class);
		
		UserService userService = new UserServiceImpl();
		String result = userService.insertUser(userInfo);
		
		System.out.println("\n" + result + "\n");

		PrintWriter out = resp.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
}
