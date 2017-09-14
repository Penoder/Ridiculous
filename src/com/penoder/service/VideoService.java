package com.penoder.service;

import com.penoder.bean.VideoBean;

public interface VideoService {

	/**
	 * 用于往数据表  video 中插入视频数据
	 * 
	 * @param videoBean
	 * @return
	 */
	public String insertVideo(VideoBean videoBean);

	/**
	 * 从数据库中获取相应的Video数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String selectVideo(String pageNum, String pageSize);
}
