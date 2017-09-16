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
import com.penoder.dao.FunTxtDao;
import com.penoder.operate.LikeOrCollectCheckOperate;
import com.penoder.utils.ConnectDatabase;
import com.penoder.utils.TimeUtil;

public class FunTxtDaoImpl implements FunTxtDao {

	/**
	 * 按时间排序分页获取TxtFun的数据 SELECT * FROM txtFun ORDER BY time DESC LIMIT 0, 10
	 */
	@Override
	public String selectTxtFun(String pageNum, String pageSize, String userID) {
		// TODO Auto-generated method stub
		String result = null;
		
		List<TxtHomePageBean> txts = new ArrayList<TxtHomePageBean>();
		
		CommonBean<List<TxtHomePageBean>> commonBean = new CommonBean<List<TxtHomePageBean>>();
		commonBean.setStatus(1);
		commonBean.setMessage("查询失败");
		commonBean.setDatas(null);
		
		int number, size; // 默认值
		number = Integer.parseInt(pageNum);
		size = Integer.parseInt(pageSize);
		String sql = "SELECT * FROM txtFun ORDER BY time DESC LIMIT ?, ?";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, size * (number - 1));
			ps.setInt(2, size);

			rs = ps.executeQuery();

			while (rs.next()) {
				TxtHomePageBean thpb = new TxtHomePageBean();
				
				int id = rs.getInt("id");
				String txtID = rs.getString("txtID");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String time = rs.getString("time");
				int thumbNum = getTxtIDNum("SELECT COUNT(txtID) FROM txtThumb WHERE txtID = ?", txtID);
				int collectNum = getTxtIDNum("SELECT COUNT(txtID) FROM txtCollect WHERE txtID = ?", txtID);
				int commentNum = getTxtIDNum("SELECT COUNT(txtID) FROM `comment` WHERE txtID = ?", txtID);
				boolean isThumb = getIsThumbOrCollect("SELECT thumbType FROM txtThumb WHERE txtID = '" + txtID + "' AND userID = '" + userID + "'", 1);
				boolean isCollect = getIsThumbOrCollect("SELECT collectType FROM txtcollect WHERE txtID = '" + txtID + "' AND userID = '" + userID + "'", 2);
				
				thpb.id = id;
				thpb.txtID = txtID;
				thpb.title = title;
				thpb.content = content;
				thpb.time = time;
				
				thpb.thumbNum = thumbNum;
				thpb.collectNum = collectNum;
				thpb.commentNum = commentNum;
				
				thpb.isThumb = isThumb;
				thpb.isCollect = isCollect;
				
				txts.add(thpb);
			}
			
			if (txts.size() > 0) {
				commonBean.setStatus(0);
				commonBean.setMessage("查询成功");
				commonBean.setDatas(txts);
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

	/**
	 * 查询txtID对应的段子的点赞、收藏、评论数量
	 * 
	 * @param sql
	 * @param txtID
	 * @return
	 */
	private int getTxtIDNum(String sql, String txtID) {
		int num = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, txtID);
			rs = ps.executeQuery();

			while (rs.next()) {
				num = rs.getInt(1); // 获取该字段在数据表中的条数
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		return num;
	}

	/**
	 * 获取用户对txtID该条段子是不是点赞或者收藏了
	 * 
	 * @param sql
	 * @param type
	 * @return
	 */
	private boolean getIsThumbOrCollect(String sql, int type) {
		// TODO Auto-generated method stub
		boolean isThumbOrCollect = false;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (type == 1) {
					isThumbOrCollect = rs.getBoolean("thumbType");
				} else if (type == 2) {
					isThumbOrCollect = rs.getBoolean("collectType");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		return isThumbOrCollect;
	}

	/**
	 * 对 txt 进行点赞和取消点赞操作，点赞时间获取系统的时间
	 */
	@Override
	public String likeOrUnLikeTxt(String userID, String txtID, boolean likeStatus) {
		// TODO Auto-generated method stub

		CommonBean<String> commonBean = new CommonBean<String>();
		commonBean.setStatus(1);
		commonBean.setMessage("查询失败");
		commonBean.setDatas("");

		Gson gson = new Gson();

		// 首先是不是需要判断用户ID是否存在
		if (!LikeOrCollectCheckOperate.isExitUser(userID)) {
			commonBean.setMessage("用户 " + userID + " 不存在");
			return gson.toJson(commonBean);
		}
		
		if (LikeOrCollectCheckOperate.isExitFunIDFromThumb(txtID, 3)) {	// 存在点赞记录 ———— UPDATE
			boolean updateIsSuccess = updateTxtIDToTxtThumb(userID, txtID, likeStatus);
			if (updateIsSuccess) {
				commonBean.setStatus(0);
				commonBean.setMessage(likeStatus ? "点赞成功" : "取消点赞成功");
			} else {
				commonBean.setStatus(1);
				commonBean.setMessage((likeStatus ? "点赞失败" : "取消点赞失败") + ", 请稍后重试");
			}
		} else {	// 数据表中不存在该条数据信息 ———— INSERT
			int insertID = insertTxtIDToTxtThumb(userID, txtID, likeStatus);
			if (insertID == -1) {
				commonBean.setStatus(1);
				commonBean.setMessage((likeStatus ? "点赞失败" : "取消点赞失败") + ", 请稍后重试");
			} else {
				commonBean.setStatus(0);
				commonBean.setMessage(likeStatus ? "点赞成功" : "取消点赞成功");
			}
		}

		return gson.toJson(commonBean);
	}
	
	/**
	 * 往 txtThumb 表中更新用户的点赞状态
	 * 
	 * @param userID
	 * @param txtID
	 * @param likeStatus
	 * @return
	 */
	private boolean updateTxtIDToTxtThumb(String userID, String txtID, boolean likeStatus) {
		boolean UPDATE_SUCCESS = false;
		
		String currentTime = TimeUtil.unixToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

		String sql = "UPDATE txtThumb SET thumbType = ? AND thumbTime = ? WHERE txtID = ? AND userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, likeStatus);
			ps.setString(2, currentTime);
			ps.setString(3, txtID);
			ps.setInt(4, Integer.parseInt(userID));

			int isSuccess = ps.executeUpdate();
			if (isSuccess > 0) { // 成功
				UPDATE_SUCCESS = true;
				System.out.println(userID + " 对 " + txtID + "更新点赞状态成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return UPDATE_SUCCESS;
	}

	/**
	 * 往数据库中插入点赞数据
	 * 
	 * @param userID
	 * @param txtID
	 * @param likeStatus
	 * @return
	 */
	private int insertTxtIDToTxtThumb(String userID, String txtID, boolean likeStatus) {
		int insertID = -1;

		String sql = "INSERT INTO txtThumb(txtThumb.thumbTime, txtThumb.thumbType, txtThumb.txtID, txtThumb.userID) VALUES(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);

			long unixTime = System.currentTimeMillis();
			String currentTime = TimeUtil.unixToString(unixTime, "yyyy-MM-dd HH:mm:ss");

			ps.setString(1, currentTime);
			ps.setBoolean(2, likeStatus);
			ps.setString(3, txtID);
			ps.setInt(4, Integer.parseInt(userID));

			ps.executeUpdate();

			// 执行完上面的插入操作之后，返回刚刚插入的订单Id，
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			if (rs.next()) {
				insertID = (int) rs.getLong(1);
				System.out.println("当前往 txtThumb 数据表中插入的是第 " + insertID + " 条数据");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return insertID;
	}
	
	/**
	 * 收藏或者是取消收藏 Txt
	 */
	@Override
	public String collectOrCancelTxt(String userID, String txtID, boolean isCollect) {
		// TODO Auto-generated method stub

		CommonBean<String> commonBean = new CommonBean<String>();
		commonBean.setStatus(1);
		commonBean.setMessage("查询失败");
		commonBean.setDatas("");

		Gson gson = new Gson();

		// 首先是不是需要判断用户ID是否存在
		if (!LikeOrCollectCheckOperate.isExitUser(userID)) {
			commonBean.setMessage("用户 " + userID + " 不存在");
			return gson.toJson(commonBean);
		}
		
		if (LikeOrCollectCheckOperate.isExitFunIDFromCollect(txtID, 3)) {	// 存在收藏记录 ———— UPDATE
			boolean updateIsSuccess = updateTxtIDToTxtCollect(userID, txtID, isCollect);
			if (updateIsSuccess) {
				commonBean.setStatus(0);
				commonBean.setMessage(isCollect ? "收藏成功" : "取消收藏成功");
			} else {
				commonBean.setStatus(1);
				commonBean.setMessage((isCollect ? "收藏失败" : "取消收藏失败") + ", 请稍后重试");
			}
		} else {	// 数据表中不存在该条数据信息 ———— INSERT
			int insertID = insertTxtIDToTxtCollect(userID, txtID, isCollect);
			if (insertID == -1) {
				commonBean.setStatus(1);
				commonBean.setMessage((isCollect ? "收藏失败" : "取消收藏失败") + ", 请稍后重试");
			} else {
				commonBean.setStatus(0);
				commonBean.setMessage(isCollect ? "收藏成功" : "取消收藏成功");
			}
		}

		return gson.toJson(commonBean);
	}
	/**
	 * 往 txtCollect 表中更新用户的点赞状态
	 * 
	 * @param userID
	 * @param txtID
	 * @param isCollect
	 * @return
	 */
	private boolean updateTxtIDToTxtCollect(String userID, String txtID, boolean isCollect) {
		boolean UPDATE_SUCCESS = false;
		
		String currentTime = TimeUtil.unixToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

		String sql = "UPDATE txtCollect SET collectType = ? AND collectTime = ? WHERE txtID = ? AND userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, isCollect);
			ps.setString(2, currentTime);
			ps.setString(3, txtID);
			ps.setInt(4, Integer.parseInt(userID));

			int isSuccess = ps.executeUpdate();
			if (isSuccess > 0) { // 成功
				UPDATE_SUCCESS = true;
				System.out.println(userID + " 对 " + txtID + "更新收藏状态成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return UPDATE_SUCCESS;
	}

	/**
	 * 往数据库收藏表中插入点赞数据
	 * 
	 * @param userID
	 * @param txtID
	 * @param isCollect
	 * @return
	 */
	private int insertTxtIDToTxtCollect(String userID, String txtID, boolean isCollect) {
		int insertID = -1;

		String sql = "INSERT INTO txtCollect(collectTime, collectType, txtID, userID) VALUES(?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);

			long unixTime = System.currentTimeMillis();
			String currentTime = TimeUtil.unixToString(unixTime, "yyyy-MM-dd HH:mm:ss");

			ps.setString(1, currentTime);
			ps.setBoolean(2, isCollect);
			ps.setString(3, txtID);
			ps.setInt(4, Integer.parseInt(userID));

			ps.executeUpdate();

			// 执行完上面的插入操作之后，返回刚刚插入的订单Id，
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			if (rs.next()) {
				insertID = (int) rs.getLong(1);
				System.out.println("当前往 txtCollect 数据表中插入的是第 " + insertID + " 条数据");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return insertID;
	}
}
