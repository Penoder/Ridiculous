package com.penoder.service;

import com.penoder.bean.AVideoBean;

public interface AVideoService {

	/**
	 * 往数据表 avideo 中插入Video的相关数据
	 * 
	 * @param videoBean
	 * @return
	 */
	public String insertVideo(AVideoBean videoBean);

	/**
	 * 从数据库中获取相应的Video数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String selectVideo(String pageNum, String pageSize);
}
