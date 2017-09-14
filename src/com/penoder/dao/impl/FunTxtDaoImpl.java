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
import com.penoder.utils.ConnectDatabase;

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

	// 查询txtID对应的段子的点赞、收藏、评论数量
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

	// 获取用户对txtID该条段子是不是点赞或者收藏了
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
	public String likeOrUnLikeTxt(String userID, String txtID,
			boolean likeStatus) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
