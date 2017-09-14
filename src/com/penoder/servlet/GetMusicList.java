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
 * 用来获取音频名字列表 然后返回给前端，具体的链接地址由前端去拼接
 * 
 * BUG: 音频播放有一个大坑，就是音频文件里面如果带有空格，那么前端时不能够成功加载出来的；
 * 其他的还有没有特殊符号暂时就不知道了。所以服务端在获取文件夹下音频文件的名字时候，
 * 所以说：如果发现该文件名包含空格，那么需要手动将文件名改掉，去掉空格
 * 
 * 另外服务端需要返回的数据并不仅仅是文件的名字，还有时长信息，文件大小等内容
 * 
 * @author Penoder
 * 
 */
public class GetMusicList extends HttpServlet {

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
		
		String realPhysicalPath = request.getSession().getServletContext().getRealPath("/");
		// D:\development\apache_tomcat\webapps\Ridiculous\

		readFileName(realPhysicalPath + "music");	// 获得该路径下所有的文件名

		PrintWriter out = response.getWriter();
		if (fileNames != null && fileNames.size() != 0) {
			String fileName = "";
			for (String name : fileNames) {
				fileName += name + "$#$";	// 
			}
			fileName = fileName.substring(0, fileName.length() - 3); // 移除最后的三个分隔符
			System.out.println("获取到的音乐文件名合集： ---> " + fileName);
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
			if (FormatUtil.isAudio(file.getName()) && !fileNames.contains(file.getName())) {
				File renameFile = new File(realPhysicalPath.replace(file.getName(), "") + file.getName().replace(" ", ""));
				file.renameTo(renameFile);
				fileNames.add(renameFile.getName());
			}
		} else if (file.isDirectory()) { // 如果是文件夹
			String[] filelist = file.list(); // 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(realPhysicalPath + "/" + filelist[i]); // 该文件该下一层文件路径（又判断是文件还是文件夹）
				if (!readfile.isDirectory()) {
					if (FormatUtil.isAudio(readfile.getName()) && !fileNames.contains(readfile.getName())) {
						/**
						 * 需要将文件名进行编码，避免中文字符的问题;解码编码还有点问题，暂时只将空格去掉
						 */
						System.out.println(realPhysicalPath + "/" + readfile.getName().replace(" ", ""));
						File renameFile = new File(realPhysicalPath + "/" + readfile.getName().replace(" ", ""));
						readfile.renameTo(renameFile);
						fileNames.add(renameFile.getName());
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
