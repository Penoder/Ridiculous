package com.penoder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.penoder.bean.AVideoBean;
import com.penoder.bean.CommonBean;
import com.penoder.bean.TxtHomePageBean;
import com.penoder.bean.VideoBean;
import com.penoder.dao.GetVideoDao;
import com.penoder.utils.ConnectDatabase;

public class GetVideoDaoImpl implements GetVideoDao {

	@Override
	public String selectVideo(String pageNum, String pageSize) {
		// TODO Auto-generated method stub
		String result = null;
		
		List<VideoBean> videos = new ArrayList<VideoBean>();
		
		CommonBean<List<VideoBean>> commonBean = new CommonBean<List<VideoBean>>();
		commonBean.setStatus(1);
		commonBean.setMessage("查询失败");
		commonBean.setDatas(null);
		
		int number, size; // 默认值
		number = Integer.parseInt(pageNum);
		size = Integer.parseInt(pageSize);
		String sql = "SELECT * FROM video ORDER BY add_time DESC LIMIT ?, ?";

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
				int addtime = rs.getInt("add_time"); // 加入到服务器的时间
				String previewUrl = rs.getString("preview_url"); // 视频播放的URL
				String video_url = rs.getString("video_url"); // 视频播放的URL
				
				VideoBean videoBean = new VideoBean(videoId, title, addtime, previewUrl, video_url);
				
				videos.add(videoBean);
			}
			
			if (videos.size() > 0) {
				commonBean.setStatus(0);
				commonBean.setMessage("查询成功");
				commonBean.setDatas(videos);
			} else {
				commonBean.setStatus(0);
				commonBean.setMessage("没有更多数据");
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
