package com.penoder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.penoder.bean.AVideoBean;
import com.penoder.dao.InsertAVideoDao;
import com.penoder.utils.ConnectDatabase;

public class InsertAVideoDaoImpl implements InsertAVideoDao {

	@Override
	public String insert(AVideoBean videoBean) {
		int insertID = 0;

		String insertSQL = "INSERT INTO avideo(avideo.title, avideo.keyword, avideo.duration, avideo.framerate, avideo.hd, avideo.addtime, avideo.video_url, avideo.embedded_url, avideo.preview_url, avideo.preview_video_url) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(insertSQL);
			ps.setString(1, videoBean.getTitle());
			ps.setString(2, videoBean.getKeyword());
			ps.setDouble(3, videoBean.getDuration());
			ps.setDouble(4, videoBean.getFramerate());
			ps.setBoolean(5, videoBean.isHd());
			ps.setInt(6, videoBean.getAddtime());
			ps.setString(7, videoBean.getVideo_url());
			ps.setString(8, videoBean.getEmbedded_url());
			ps.setString(9, videoBean.getPreview_url());
			ps.setString(10, videoBean.getPreview_video_url());

			ps.executeUpdate();

			// 执行完上面的插入操作之后，返回刚刚插入的订单Id，
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			if (rs.next()) {
				insertID = (int) rs.getLong(1);
				System.out.println("当前往 avideo 数据表中插入的是第 " + insertID + " 条数据");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		if (insertID == 0) {
			return "视频为 " + videoBean.getTitle() + " 的视频插入失败";
		}
		return "视频为 " + videoBean.getTitle() + " 的视频插入成功";
	}

	/**
	 * 用于往数据库中插入数据的时候，先判断数据库中是不是已经存在了相应的视频数据
	 * 
	 * @return
	 */
	public boolean isExitVideoInDB(String videoURL) {
		boolean isExist = false;

		String selectSQL = "SELECT * FROM avideo WHERE video_url = ?";

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
