package com.penoder.operate;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.penoder.bean.FunGifBean;
import com.penoder.bean.FunImgBean;
import com.penoder.bean.FunTxtBean;
import com.penoder.bean.ImgBean;
import com.penoder.bean.TxtBean;
import com.penoder.dao.InsertFunDao;
import com.penoder.dao.impl.InsertFunDaoImpl;

public class GetFunData {

	private static String gifJson, imgJson, txtJson;
	private static String currentDate, currentTime, beforeDay;
	private static int year, month, day;

	private static List<ImgBean> gifBeans = new ArrayList<ImgBean>();
	private static List<ImgBean> imgBeans = new ArrayList<ImgBean>();
	private static List<TxtBean> txtBeans = new ArrayList<TxtBean>();

	public static void main(String[] args) {
		GetFunData gfd = new GetFunData();
		gfd.operate(gfd);
	}
	
	public void operate(GetFunData gfd) {
		String format = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		while (true) {
			String currentDateTime = sdf.format(new Date(System.currentTimeMillis()));
			currentDate = currentDateTime.substring(0, 10);
			year = Integer.parseInt(currentDate.substring(0, 4));
			month = Integer.parseInt(currentDate.substring(5, 7));
			day = Integer.parseInt(currentDate.substring(8, 10));
			beforeDay = printCalendar(getBeforeDay(setCalendar(year, month, day)));
			beforeDay = "2017-09-11";	// 手动插入某天之后的数据
			currentTime = currentDateTime.substring(11, 19);
			System.out.println("现在的时间是： -------------- " + beforeDay + " " + currentTime);
			currentTime = currentDateTime.substring(11, 13);
			// 设置成每天的凌晨、六点、十二点、十八点四个时间点用于更新数据
			if (currentTime.contains("20") || currentTime.contains("00") || currentTime.contains("06") || currentTime.contains("12") || currentTime.contains("18")) {
				gfd.insertGifData(gfd);
				gfd.insertImgData(gfd);
				gfd.insertTxtData(gfd);
			}

			try {
				Thread.sleep(60* 60 * 1000);	// 既然是6个小时一更，那么我最多可以休1个小时，才能保证不错过每一个时间点
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void insertTxtData(GetFunData getFun) {
		// TODO Auto-generated method stub
		try {
			txtJson = getFun.getFunTxt("1", "20", beforeDay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		FunTxtBean txtFun = gson.fromJson(txtJson, FunTxtBean.class);
		int allPage = txtFun.getShowapi_res_body().getAllPages();
		if (allPage < 1) {
			return;
		}
		InsertFunDao fgd = new InsertFunDaoImpl();
		
		for (int i = 0; i < allPage; i++) {
			try {
				txtJson = getFun.getFunTxt(i + "", "20", beforeDay);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			txtFun = gson.fromJson(txtJson, FunTxtBean.class);
			txtBeans.clear();
			if (txtFun.getShowapi_res_body().getContentlist() != null && txtFun.getShowapi_res_body().getContentlist().size() > 0) {
				txtBeans.addAll(txtFun.getShowapi_res_body().getContentlist());
				fgd.insertTxt(txtBeans);
				System.out.println("插入的是第 " + i + " 页20条数据");
			}
		}
		
		System.out.println("文本TXT终于完了？？？？？？？？？？？？？？？？？？？？？？");
	}

	private void insertImgData(GetFunData getFun) {
		// TODO Auto-generated method stub
		try {
			imgJson = getFun.getFunImg("1", "20", beforeDay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		FunImgBean imgFun = gson.fromJson(imgJson, FunImgBean.class);
		int allPage = imgFun.getShowapi_res_body().getAllPages();
		if (allPage < 1) {
			return;
		}
		InsertFunDao fgd = new InsertFunDaoImpl();
		
		for (int i = 0; i < allPage; i++) {
			try {
				imgJson = getFun.getFunImg(i + "", "20", beforeDay);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			imgFun = gson.fromJson(imgJson, FunImgBean.class);
			imgBeans.clear();
			if (imgFun.getShowapi_res_body().getContentlist() != null && imgFun.getShowapi_res_body().getContentlist().size() > 0) {
				imgBeans.addAll(imgFun.getShowapi_res_body().getContentlist());
				fgd.insertImg(imgBeans);
				System.out.println("插入的是第 " + i + " 页20条数据");
			}
		}
		
		System.out.println("静态图终于完了？？？？？？？？？？？？？？？？？？？？？？");
		
	}

	private void insertGifData(GetFunData getFun) {
		// TODO Auto-generated method stub

		try {
			gifJson = getFun.getFunGif("1", "20");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();
		FunGifBean gifFun = gson.fromJson(gifJson, FunGifBean.class);
		if(gifFun == null && gifFun.getShowapi_res_body() == null) {
			return;
		}
		int allPage = gifFun.getShowapi_res_body().getAllPages();
		if (allPage < 1) {
			return;
		}
		InsertFunDao fgd = new InsertFunDaoImpl();

		for (int i = 1; i <= allPage; i++) {
			try {
				gifJson = getFun.getFunGif(i + "", "20");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gifFun = gson.fromJson(gifJson, FunGifBean.class);
			gifBeans.clear();
			if (gifFun.getShowapi_res_body().getContentlist() != null && gifFun.getShowapi_res_body().getContentlist().size() > 0) {
				gifBeans.addAll(gifFun.getShowapi_res_body().getContentlist());
				fgd.insertGif(gifBeans);
				System.out.println("插入的是第 " + i + " 页20条数据");
			}
		}

		System.out.println("动态图终于完了？？？？？？？？？？？？？？？？？？？？？？？？？？？");
	}

	// 获取搞笑动态图数据
	public String getFunGif(String pageNum, String pageSize) throws Exception {
		String funGifUrl = "http://route.showapi.com/341-3?showapi_appid=38000&showapi_sign=fd8ec15ee4444eae9b5fbcf30cadf0e4&page="
				+ pageNum + "&maxResult=" + pageSize;
		URL u = new URL(funGifUrl);
		InputStream in = u.openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte buf[] = new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[] = out.toByteArray();
		return new String(b, "utf-8");
	}

	// 获取 搞笑静态图数据(其中的time参数为该时间点之后的数据)
	public String getFunImg(String pageNum, String pageSize, String time) throws Exception {
		String funImgUrl = "http://route.showapi.com/341-2?showapi_appid=38000&showapi_sign=fd8ec15ee4444eae9b5fbcf30cadf0e4"
				+ "&time=" + time + "&page=" + pageNum + "&maxResult=" + pageSize;
		URL u = new URL(funImgUrl);
		InputStream in = u.openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte buf[] = new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[] = out.toByteArray();
		return new String(b, "utf-8");
	}

	public String getFunTxt(String pageNum, String pageSize, String time) throws Exception {
		String funTxtUrl = "http://route.showapi.com/341-1?showapi_appid=38000&showapi_sign=fd8ec15ee4444eae9b5fbcf30cadf0e4"
				+ "&time=" + time + "&page=" + pageNum + "&maxResult=" + pageSize;
		URL u = new URL(funTxtUrl);
		InputStream in = u.openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte buf[] = new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[] = out.toByteArray();
		return new String(b, "utf-8");
	}

	
	public static Calendar setCalendar(int year,int month,int date){  
        Calendar cl = Calendar.getInstance();  
        cl.set(year, month-1, date);  
        return cl;  
    }
	
    private static Calendar getBeforeDay(Calendar cl){  
        //使用roll方法进行向前回滚  
        //cl.roll(Calendar.DATE, -1);  
        //使用set方法直接进行设置  
        int day = cl.get(Calendar.DATE);  
        cl.set(Calendar.DATE, day-1);  
        return cl;  
    }  
    
    public static String printCalendar(Calendar cl){  
        int year = cl.get(Calendar.YEAR);  
        int month = cl.get(Calendar.MONTH)+1;  
        int day = cl.get(Calendar.DATE);
        return year+"-"+month+"-"+day;
    } 
	/**
	 * 然后将数据保存到数据库中，根据allPages字段，进行循环插入到数据库
	 * 
	 * 上面是手动插入，然后每天定时插入前一天的数据，将时间改为今天之前的一天就好
	 * 为了更加准确，进行插入时去重判断。当然，为了数据的及时更新，每天可以做多次操作。
	 * 
	 * 然后前端手机端还是通过自己的Servlet接口来调用数据，所有的数据交给后台统一管理。
	 */

}