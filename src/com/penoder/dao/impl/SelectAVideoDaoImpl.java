package com.penoder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.penoder.bean.CommonBean;
import com.penoder.bean.TxtHomePageBean;
import com.penoder.bean.AVideoBean;
import com.penoder.dao.SelectAVideoDao;
import com.penoder.utils.ConnectDatabase;

public class SelectAVideoDaoImpl implements SelectAVideoDao {

	@Override
	public String selectVideo(String pageNum, String pageSize) {
		// TODO Auto-generated method stub
		String result = null;
		
		List<AVideoBean> videos = new ArrayList<AVideoBean>();
		
		CommonBean<List<AVideoBean>> commonBean = new CommonBean<List<AVideoBean>>();
		commonBean.setStatus(1);
		commonBean.setMessage("查询失败");
		commonBean.setDatas(null);
		
		int number, size; // 默认值
		number = Integer.parseInt(pageNum);
		size = Integer.parseInt(pageSize);
		String sql = "SELECT * FROM avideo ORDER BY addtime DESC LIMIT ?, ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, size * number);
			ps.setInt(2, size);

			rs = ps.executeQuery();

			while (rs.next()) {
				int videoId = rs.getInt("id");
				String title = rs.getString("title"); // 视频标题
				String keyword = rs.getString("keyword"); // 视频关键字
				double duration = rs.getDouble("duration"); // 持续时间（单位：秒）
				double framerate = rs.getDouble("framerate"); // 帧速率（和下面的HD字段说明视频质量的）
				boolean hd = rs.getBoolean("hd"); // 是否是HD类型视频
				int addtime = rs.getInt("addtime"); // 加入到服务器的时间
				String video_url = rs.getString("video_url"); // 视频播放的URL
				String embedded_url = rs.getString("embedded_url"); // 嵌入浏览器的URL（没搞明白是干什么的，先留着）
				String preview_url = rs.getString("preview_url"); // 预加载图片的URL
				String preview_video_url = rs.getString("preview_video_url"); // 预加载视频的URL
				
				AVideoBean videoBean = new AVideoBean(videoId, title, keyword, duration, framerate, hd, addtime, video_url, embedded_url, preview_url, preview_video_url);
				
				videos.add(videoBean);
			}
			
			if (videos.size() > 0) {
				commonBean.setStatus(0);
				commonBean.setMessage("查询成功");
				commonBean.setDatas(videos);
			}
			
			Gson gson = new Gson();
			result = gson.toJson(commonBean);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}

		return result;
	}

}
