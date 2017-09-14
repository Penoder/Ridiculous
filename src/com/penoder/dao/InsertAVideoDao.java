package com.penoder.dao;

import com.penoder.bean.AVideoBean;

public interface InsertAVideoDao {
	
	/**
	 * 往数据库中插入Video相关数据
	 * 
	 * @param videoBean
	 * @return
	 */
	public String insert(AVideoBean videoBean);
}
