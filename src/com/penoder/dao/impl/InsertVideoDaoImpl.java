package com.penoder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.penoder.bean.AVideoBean;
import com.penoder.bean.VideoBean;
import com.penoder.dao.InsertVideoDao;
import com.penoder.utils.ConnectDatabase;

public class InsertVideoDaoImpl implements InsertVideoDao {

	@Override
	public String insert(VideoBean videoBean) {
		// TODO Auto-generated method stub
		int insertID = 0;

		String insertSQL = "INSERT INTO video(video.title, video.add_time, video.video_url) VALUES(?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, videoBean.title);
			ps.setLong(2, videoBean.addTime);
			ps.setString(3, videoBean.videoUrl);

			ps.executeUpdate();

			// 执行完上面的插入操作之后，返回刚刚插入的订单Id，
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			if (rs.next()) {
				insertID = (int) rs.getLong(1);
				System.out.println("当前往 video 数据表中插入的是第 " + insertID + " 条数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		if (insertID == 0) {
			return "视频为 " + videoBean.title + " 的视频插入失败";
		}
		return "视频为 " + videoBean.title + " 的视频插入成功";
	}

	/**
	 * 用于往数据库中插入数据的时候，先判断数据库中是不是已经存在了相应的视频数据
	 * 
	 * @return
	 */
	public boolean isExitVideoInDB(String videoURL) {
		boolean isExist = false;

		String selectSQL = "SELECT * FROM video WHERE video_url = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(selectSQL);
			ps.setString(1, videoURL);

			rs = ps.executeQuery();
			if (rs.next()) {
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		return isExist;
	}

}
