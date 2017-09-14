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
import com.penoder.dao.FunImgDao;
import com.penoder.utils.ConnectDatabase;

public class FunImgDaoImpl implements FunImgDao {

	/**
	 * 按时间排序分页获取ImgFun的数据 SELECT * FROM imgFun ORDER BY time DESC LIMIT 0, 10
	 */
	@Override
	public String selectImg(String pageNum, String pageSize, String userID) {
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
		String sql = "SELECT * FROM imgFun ORDER BY time DESC LIMIT ?, ?";

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
				String imgID = rs.getString("imgID");
				String title = rs.getString("title");
				String imgUrl = rs.getString("imgUrl");
				String time = rs.getString("time");
				int thumbNum = getImgIDNum("SELECT COUNT(imgID) FROM imgThumb WHERE imgID = ?", imgID);
				int collectNum = getImgIDNum("SELECT COUNT(imgID) FROM imgCollect WHERE imgID = ?", imgID);
				int commentNum = getImgIDNum("SELECT COUNT(imgID) FROM `comment` WHERE imgID = ?", imgID);
				boolean isThumb = getIsThumbOrCollect("SELECT thumbType FROM imgThumb WHERE imgID = '" + imgID + "' AND userID = '" + userID + "'", 1);
				boolean isCollect = getIsThumbOrCollect("SELECT collectType FROM imgcollect WHERE imgID = '" + imgID + "' AND userID = '" + userID + "'", 2);
				
				ihpb.id = id;
				ihpb.imgID = imgID;
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

	// 查询imgID对应的段子的点赞、收藏、评论数量
	private int getImgIDNum(String sql, String imgID) {
		int num = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectDatabase.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, imgID);
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

	// 获取用户对imgID该条段子是不是点赞或者收藏了
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
	 * 对 img 进行点赞或取消点赞操作，时间获取当前系统时间
	 */
	@Override
	public String likeOrUnLikeImg(String userID, String imgID,
			boolean likeStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
