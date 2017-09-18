package com.penoder.dao;

import com.penoder.bean.UserInfo;

public interface InsertUserDao {

	/**
	 * 注册用户信息到数据表中
	 * 
	 * @param userInfo
	 * @return
	 */
	public String insertUser(UserInfo userInfo);
	
}
