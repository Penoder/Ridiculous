package com.penoder.dao;

import com.penoder.bean.VideoBean;

public interface InsertVideoDao {
	
	/**
	 * 往数据表 video 中插入Video相关数据
	 * 
	 * @param videoBean
	 * @return
	 */
	public String insert(VideoBean videoBean);
}
