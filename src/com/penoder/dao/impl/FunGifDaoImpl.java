package com.penoder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.penoder.bean.CommonBean;
import com.penoder.bean.ImgHomePageBean;
import com.penoder.bean.TxtHomePageBean;
import com.penoder.dao.FunGifDao;
import com.penoder.operate.LikeOrCollectCheckOperate;
import com.penoder.utils.ConnectDatabase;
import com.penoder.utils.TimeUtil;

public class FunGifDaoImpl implements FunGifDao {

	/**
	 * 按时间排序分页获取GifFun的数据 SELECT * FROM gifFun ORDER BY time DESC LIMIT 0, 10
	 */
	@Override
	public String selectGif(String pageNum, String pageSize, String userID) {
		// TODO Auto-generated method stub
		String result = null;

		List<ImgHomePageBean> imgs = new ArrayList<ImgHomePageBean>();

		CommonBean<List<ImgHomePageBean>> commonBean = new CommonBean<List<ImgHomePageBean>>();
		commonBean.setStatus(1);
		commonBean.setMessage("查询失败");
		commonBean.setDatas(null);

		int number, size; // 默认值
		number = Integer.parseInt(pageNum);
		size = Integer.parseInt(pageSize);
		String sql = "SELECT * FROM gifFun ORDER BY time DESC LIMIT ?, ?";

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
				ImgHomePageBean ihpb = new ImgHomePageBean();

				int id = rs.getInt("id");
				String gifID = rs.getString("gifID");
				String title = rs.getString("title");
				String imgUrl = rs.getString("imgUrl");
				String time = rs.getString("time");
				int thumbNum = getGifIDNum(
						"SELECT COUNT(gifID) FROM gifThumb WHERE gifID = ?",
						gifID);
				int collectNum = getGifIDNum(
						"SELECT COUNT(gifID) FROM gifCollect WHERE gifID = ?",
						gifID);
				int commentNum = getGifIDNum(
						"SELECT COUNT(gifID) FROM `comment` WHERE gifID = ?",
						gifID);
				boolean isThumb = getIsThumbOrCollect(
						"SELECT thumbType FROM gifThumb WHERE gifID = '"
								+ gifID + "' AND userID = '" + userID + "'", 1);
				boolean isCollect = getIsThumbOrCollect(
						"SELECT collectType FROM gifcollect WHERE gifID = '"
								+ gifID + "' AND userID = '" + userID + "'", 2);

				ihpb.id = id;
				ihpb.gifID = gifID;
				ihpb.title = title;
				ihpb.imgUrl = imgUrl;
				ihpb.time = time;

				ihpb.thumbNum = thumbNum;
				ihpb.collectNum = collectNum;
				ihpb.commentNum = commentNum;

				ihpb.isThumb = isThumb;
				ihpb.isCollect = isCollect;

				imgs.add(ihpb);
			}

			if (imgs.size() > 0) {
				commonBean.setStatus(0);
				commonBean.setMessage("查询成功");
				commonBean.setDatas(imgs);
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
	 * 查询gifID对应的段子的点赞、收藏、评论数量
	 * 
	 * @param sql
	 * @param gifID
	 * @return
	 */
	private int getGifIDNum(String sql, String gifID) {
		int num = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, gifID);
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
	 * 获取用户对gifID该条段子是不是点赞或者收藏了
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
	 * 点赞或取消点赞操作，点赞时间获取系统当前时间，注意与数据表中的时间格式保持一致
	 */
	@Override
	public String likeOrUnLikeGif(String userID, String gifID, boolean likeStatus) {
		// TODO Auto-generated method stub

		CommonBean<String> commonBean = new CommonBean();
		commonBean.setStatus(1);
		commonBean.setMessage("查询失败");
		commonBean.setDatas("");

		Gson gson = new Gson();

		// 首先是不是需要判断用户ID是否存在
		if (!LikeOrCollectCheckOperate.isExitUser(userID)) {
			commonBean.setMessage("用户 " + userID + " 不存在");
			return gson.toJson(commonBean);
		}
		
		if (LikeOrCollectCheckOperate.isExitFunIDFromThumb(gifID, 1)) {	// 存在点赞记录 ———— UPDATE
			boolean updateIsSuccess = updateGifIDToGifThumb(userID, gifID, likeStatus);
			if (updateIsSuccess) {
				commonBean.setStatus(0);
				commonBean.setMessage(likeStatus ? "点赞成功" : "取消点赞成功");
			} else {
				commonBean.setStatus(1);
				commonBean.setMessage((likeStatus ? "点赞失败" : "取消点赞失败") + ", 请稍后重试");
			}
		} else {	// 数据表中不存在该条数据信息 ———— INSERT
			int insertID = insertGifIDToGifThumb(userID, gifID, likeStatus);
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
	 * 往 gifThumb 表中更新用户的点赞状态
	 * 
	 * @param userID
	 * @param gifID
	 * @param likeStatus
	 * @return
	 */
	private boolean updateGifIDToGifThumb(String userID, String gifID, boolean likeStatus) {
		boolean UPDATE_SUCCESS = false;
		
		String currentTime = TimeUtil.unixToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

		String sql = "UPDATE gifThumb SET thumbType = ? AND thumbTime = ? WHERE gifID = ? AND userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, likeStatus);
			ps.setString(2, currentTime);
			ps.setString(3, gifID);
			ps.setInt(4, Integer.parseInt(userID));

			int isSuccess = ps.executeUpdate();
			if (isSuccess > 0) { // 成功
				UPDATE_SUCCESS = true;
				System.out.println(userID + " 对 " + gifID + "更新点赞状态成功");
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
	 * @param gifID
	 * @param likeStatus
	 * @return
	 */
	private int insertGifIDToGifThumb(String userID, String gifID, boolean likeStatus) {
		int insertID = -1;

		String sql = "INSERT INTO gifThumb(gifThumb.thumbTime, gifThumb.thumbType, gifThumb.gifID, gifThumb.userID) VALUES(?, ?, ?, ?)";

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
			ps.setString(3, gifID);
			ps.setInt(4, Integer.parseInt(userID));

			ps.executeUpdate();

			// 执行完上面的插入操作之后，返回刚刚插入的订单Id，
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			if (rs.next()) {
				insertID = (int) rs.getLong(1);
				System.out.println("当前往 gifThumb 数据表中插入的是第 " + insertID + " 条数据");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return insertID;
	}

	/**
	 * 收藏或者是取消收藏GIF
	 */
	@Override
	public String collectOrCancelGif(String userID, String gifID, boolean isCollect) {
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
		
		if (LikeOrCollectCheckOperate.isExitFunIDFromCollect(gifID, 1)) {	// 存在收藏记录 ———— UPDATE
			boolean updateIsSuccess = updateGifIDToGifCollect(userID, gifID, isCollect);
			if (updateIsSuccess) {
				commonBean.setStatus(0);
				commonBean.setMessage(isCollect ? "收藏成功" : "取消收藏成功");
			} else {
				commonBean.setStatus(1);
				commonBean.setMessage((isCollect ? "收藏失败" : "取消收藏失败") + ", 请稍后重试");
			}
		} else {	// 数据表中不存在该条数据信息 ———— INSERT
			int insertID = insertGifIDToGifCollect(userID, gifID, isCollect);
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
	 * 往 gifCollect 表中更新用户的点赞状态
	 * 
	 * @param userID
	 * @param gifID
	 * @param isCollect
	 * @return
	 */
	private boolean updateGifIDToGifCollect(String userID, String gifID, boolean isCollect) {
		boolean UPDATE_SUCCESS = false;
		
		String currentTime = TimeUtil.unixToString(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

		String sql = "UPDATE gifCollect SET collectType = ? AND collectTime = ? WHERE gifID = ? AND userID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setBoolean(1, isCollect);
			ps.setString(2, currentTime);
			ps.setString(3, gifID);
			ps.setInt(4, Integer.parseInt(userID));

			int isSuccess = ps.executeUpdate();
			if (isSuccess > 0) { // 成功
				UPDATE_SUCCESS = true;
				System.out.println(userID + " 对 " + gifID + "更新收藏状态成功");
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
	 * @param gifID
	 * @param isCollect
	 * @return
	 */
	private int insertGifIDToGifCollect(String userID, String gifID, boolean isCollect) {
		int insertID = -1;

		String sql = "INSERT INTO gifCollect(collectTime, collectType, gifID, userID) VALUES(?, ?, ?, ?)";

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
			ps.setString(3, gifID);
			ps.setInt(4, Integer.parseInt(userID));

			ps.executeUpdate();

			// 执行完上面的插入操作之后，返回刚刚插入的订单Id，
			rs = ps.getGeneratedKeys();// 这一句代码就是得到插入的记录的id
			if (rs.next()) {
				insertID = (int) rs.getLong(1);
				System.out.println("当前往 gifCollect 数据表中插入的是第 " + insertID + " 条数据");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDatabase.close(conn, ps, rs);
		}
		
		return insertID;
	}
}
