package com.penoder.operate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.penoder.utils.ConnectDatabase;

/**
 * 该类用作与点赞或者是取消点赞操作的 一些检查：
 * 例如：检查点赞用户是不是存在，点赞的funID是不是存在在点赞表中，
 * 毕竟有过点赞记录的funID需要执行的是UPDATE数据表
 * 而没有存在过的funID需要执行的则是INSERT数据表
 * 
 * @author Penoder
 *
 */
public class LikeOrCollectCheckOperate {
	
	/**
	 * 判断用户是不是存在
	 * 
	 * @param userID
	 * @return
	 */
	public static boolean isExitUser(String userID) {
		boolean isExitUserID = false;
		
		String SELECT_SQL = "SELET * FROM user WHERE id = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(SELECT_SQL);
			ps.setString(1, userID);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				isExitUserID = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return isExitUserID;
	}
	
	/**
	 * 判断对应的点赞表中是不是有相应的点赞记录
	 * 
	 * @param funID
	 * @return
	 */
	public static boolean isExitFunIDFromThumb(String funID, int funType) {
		boolean isExitFunID = false;
		
		String SELECT_SQL = "";
		switch (funType) {
		case 1: // GIF
			SELECT_SQL = "SELECT * FROM gifThumb WHERE gifID = ?";
			break;
		case 2: // IMG
			SELECT_SQL = "SELECT * FROM imgThumb WHERE imgID = ?";
			break;
		case 3: // Txt
			SELECT_SQL = "SELECT * FROM txtThumb WHERE txtID = ?";
			break;
		default:
			break;	
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(SELECT_SQL);
			ps.setString(1, funID);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				isExitFunID = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return isExitFunID;
	}
	
	/**
	 * 对应的收藏表中是不是有相应的FUN数据
	 * 
	 * @param funID
	 * @param funType
	 * @return
	 */
	public static boolean isExitFunIDFromCollect(String funID, int funType) {
		boolean isExitFunID = false;
		
		String SELECT_SQL = "";
		switch (funType) {
		case 1: // GIF
			SELECT_SQL = "SELECT * FROM gifCollect WHERE gifID = ?";
			break;
		case 2: // IMG
			SELECT_SQL = "SELECT * FROM imgCollect WHERE imgID = ?";
			break;
		case 3: // Txt
			SELECT_SQL = "SELECT * FROM txtCollect WHERE txtID = ?";
			break;
		default:
			break;	
		}

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(SELECT_SQL);
			ps.setString(1, funID);

			rs = ps.executeQuery();
			
			if (rs.next()) {
				isExitFunID = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return isExitFunID;
	}
}
