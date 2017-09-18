package com.penoder.service.impl;

import com.penoder.bean.UserInfo;
import com.penoder.dao.InsertUserDao;
import com.penoder.dao.impl.InsertUserDaoImpl;
import com.penoder.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public String insertUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		InsertUserDao insertUserDao = new InsertUserDaoImpl();
		return insertUserDao.insertUser(userInfo);
	}

}
