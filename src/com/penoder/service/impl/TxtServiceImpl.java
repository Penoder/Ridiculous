package com.penoder.service.impl;

import com.penoder.dao.FunTxtDao;
import com.penoder.dao.impl.FunTxtDaoImpl;
import com.penoder.service.TxtService;

public class TxtServiceImpl implements TxtService {

	FunTxtDao ftd = new FunTxtDaoImpl();

	@Override
	public String getTxtFun(String pageNum, String pageSize, String userID) {
		// TODO Auto-generated method stub
		return ftd.selectTxtFun(pageNum, pageSize, userID);
	}
}