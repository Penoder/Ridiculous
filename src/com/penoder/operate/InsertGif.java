package com.penoder.operate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.penoder.utils.ConnectDatabase;

public class InsertGif {

	public boolean selectGif(String id) {
		// TODO Auto-generated method stub
		boolean isExist = false;
		String sql = "SELECT gifID FROM gifFun WHERE gifID = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);

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

	public boolean selectGifUrl(String imgUrl) {
		// TODO Auto-generated method stub
		boolean isExist = false;
		String sql = "SELECT gifID FROM gifFun WHERE imgUrl = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, imgUrl);

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

	public String insert(String id, String title, String img, String ct) {
		// TODO Auto-generated method stub
		int insertID = 0;
		
		String sql = "INSERT INTO gifFun(gifID, title, imgUrl, time) VALUES(?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, title);
			ps.setString(3, img);
			ps.setString(4, ct);

			ps.executeUpdate();

			// 执行完上面的插入操作之后，返回刚刚插入的订单Id，
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			if (rs.next()) {
				insertID = (int) rs.getLong(1);
				System.out.println("当前往GIFFun数据表中插入的ID是"+ id + " ；其所在数据表中的ID是： " + insertID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		if (insertID == 0) {
			return "ID为 " + id + " 的动图插入失败";			
		}
		return "ID为 " + id + " 的动图插入成功";
	}
}
