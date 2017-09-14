package com.penoder.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.penoder.utils.FormatUtil;

/**
 * 用来获取视频地址列表 然后返回给前端
 * 
 * @author Penoder
 * 
 */
public class GetVideoList extends HttpServlet {

	private List<String> fileNames = new ArrayList<String>();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("utf-8");

		/**
		 * 我要返回视频列表给前端，那么我需要获取的是视频的文件名，然后前面添加统一的服务器地址就交由前端了！
		 * 
		 * 然后现在要做的就是访问本地服务器中的文件路径，获取文件名
		 */

		fileNames.clear();	// 避免文件改名之后还有原先的名字在里面

		String realPhysicalPath = request.getSession().getServletContext()
				.getRealPath("/");
		// System.out.println("真实物理路径是： " + realPhysicalPath); //
		// D:\development\apache_tomcat\webapps\Ridiculous\

		readFileName(realPhysicalPath + "videos");

		PrintWriter out = response.getWriter();
		if (fileNames != null && fileNames.size() != 0) {
			String fileName = "";
			for (String name : fileNames) {
				fileName += name + "$#$";
			}
			fileName = fileName.substring(0, fileName.length() - 3); // 移除最后的三个分隔符
			System.out.println("获取到的视频文件名合集： ---> " + fileName);
			out.println(fileName);
		}
		out.flush();
		out.close();
	}

	private void readFileName(String realPhysicalPath) {
		// TODO Auto-generated method stub

		// 先获取文件路径，然后判断文件是文件夹还是文件
		File file = new File(realPhysicalPath);
		if (!file.isDirectory()) { // 如果是文件
			System.out.println("path=" + file.getPath());
			System.out.println("absolutepath=" + file.getAbsolutePath());
			System.out.println("name=" + file.getName()); // 输出文件名
			if (FormatUtil.isVideo(file.getName()) && !fileNames.contains(file.getName())) {	// 去重
				fileNames.add(file.getName());
			}
		} else if (file.isDirectory()) { // 如果是文件夹
			String[] filelist = file.list(); // 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(realPhysicalPath + "/" + filelist[i]); // 该文件该下一层文件路径（又判断是文件还是文件夹）
				if (!readfile.isDirectory()) {
					// System.out.println("path=" + readfile.getPath());
					// System.out.println("absolutepath=" +
					// readfile.getAbsolutePath());
					// System.out.print("name=" + readfile.getName());
					if (FormatUtil.isVideo(readfile.getName()) && !fileNames.contains(readfile.getName())) {	// 去重
						fileNames.add(readfile.getName());
					}
				} else if (readfile.isDirectory()) {
					readFileName(realPhysicalPath + "/" + filelist[i]);
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
