package com.penoder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.penoder.bean.CommonBean;
import com.penoder.bean.UserInfo;
import com.penoder.dao.InsertUserDao;
import com.penoder.utils.ConnectDatabase;
import com.penoder.utils.TimeUtil;

public class InsertUserDaoImpl implements InsertUserDao {

	private CommonBean<UserInfo> commonBean = new CommonBean<UserInfo>();
	private Gson gson = new Gson();
	
	@Override
	public String insertUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		commonBean.setStatus(1);
		
		if (userInfo.openid == null && "".equals(userInfo.openid)) {
			commonBean.setMessage("UserInfo 的 openId 不存在");
			commonBean.setDatas(null);
			return gson.toJson(commonBean);
		}
		/**
		 * 先查询，有则更新，无则插入
		 */
		if (selectUserToDB(userInfo.openid) > 0) {
			return updateUserInfo(userInfo);
		} else {
			return insertUserInfo(userInfo);
		}
	}
	
	/**
	 * 查询 user 表中是不是存在 openID 该条用户数据
	 * @param openid
	 * @return
	 */
	private int selectUserToDB(String openid) {
		int userID = 0;

		String selectSQL = "SELECT * FROM user WHERE openid = ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(selectSQL);
			ps.setString(1, openid);

			rs = ps.executeQuery();
			if (rs.next()) {
				userID = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return userID;
	}

	/**
	 * 如果用户表中存在传递的用户记录，则更新用户信息
	 * @param userInfo
	 * @return
	 */
	private String updateUserInfo(UserInfo userInfo) {
		commonBean.setStatus(1);
		commonBean.setMessage("更新 " + userInfo.openid + " 到 user 表失败");
		commonBean.setDatas(userInfo);
		
		String currentTime = TimeUtil.unixToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

		String UPDATESQL = "UPDATE user SET access_token = ?, nickName = ?, gender = ?"
				+ ", avatar = ?, province = ?, city = ?, session = ?"
				+ ", loginTime = ? WHERE openid = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(UPDATESQL);
			ps.setString(1, userInfo.access_token != null ? userInfo.access_token : "");
			ps.setString(2, userInfo.nickname != null ? userInfo.nickname : "");
			ps.setString(3, userInfo.gender != null ? userInfo.gender : "");
			ps.setString(4, userInfo.figureurl_qq_2 != null ? userInfo.figureurl_qq_2 : "");
			ps.setString(5, userInfo.province != null ? userInfo.province : "");
			ps.setString(6, userInfo.city != null ? userInfo.city : "");
			ps.setString(7, userInfo.session != null ? userInfo.session : "");
			ps.setString(8, currentTime);
			ps.setString(9, userInfo.openid);

			int isSuccess = ps.executeUpdate();
			if (isSuccess > 0) { // 成功
				System.out.println(userInfo.openid + " 更新user信息成功");
				UserInfo user = userInfo;
				user.loginTime = currentTime;
				if (user.id < 1) {	// 更新状态 userID 应该是存在的，不至于小于1
					user.id = selectUserToDB(user.openid);
				}
				
				commonBean.setStatus(0);
				commonBean.setMessage("更新 " + user.openid + " 到 user 表成功");
				commonBean.setDatas(userInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return gson.toJson(commonBean);
	}
	
	/**
	 * 如果用户表中不存在传递的用户信息，则插入数据
	 * @param userInfo
	 * @return
	 */
	private String insertUserInfo(UserInfo userInfo) {
		commonBean.setStatus(1);
		commonBean.setMessage("UserInfo 插入到 user 表失败");
		commonBean.setDatas(userInfo);
		
		int insertID = 0;
		String CURRENTTIME = TimeUtil.unixToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
		String INSERTSQL = "INSERT INTO user(openid, access_token, nickName, gender, avatar, province, city, session, registerTime, loginTime) VALUE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(INSERTSQL);
			ps.setString(1, userInfo.openid);
			ps.setString(2, userInfo.access_token != null ? userInfo.access_token : "");
			ps.setString(3, userInfo.nickname != null ? userInfo.nickname : "");
			ps.setString(4, userInfo.gender != null ? userInfo.gender : "");
			ps.setString(5, userInfo.figureurl_qq_2 != null ? userInfo.figureurl_qq_2 : "");
			ps.setString(6, userInfo.province != null ? userInfo.province : "");
			ps.setString(7, userInfo.city != null ? userInfo.city : "");
			ps.setString(8, userInfo.session != null ? userInfo.session : "");
			ps.setString(9, CURRENTTIME);
			ps.setString(10, CURRENTTIME);

			ps.executeUpdate();

			// 执行完上面的插入操作之后，返回刚刚插入的订单Id，
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			if (rs.next()) {
				insertID = (int) rs.getLong(1);
				if (insertID > 0) {
					UserInfo user = userInfo;
					user.id = insertID;
					user.registerTime = CURRENTTIME;
					user.loginTime = CURRENTTIME;
					commonBean.setStatus(0);
					commonBean.setMessage("UserInfo 插入到 user 表中第 " + insertID + " 条记录");
					commonBean.setDatas(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return gson.toJson(commonBean);
	}
	
}
