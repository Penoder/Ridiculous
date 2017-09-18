package com.penoder.service;

import com.penoder.bean.UserInfo;

public interface UserService {
	
	/**
	 * 注册用户信息到数据表中
	 * 
	 * @param userInfo
	 * @return
	 */
	public String insertUser(UserInfo userInfo);
}
