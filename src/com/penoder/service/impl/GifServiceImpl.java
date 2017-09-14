package com.penoder.service.impl;

import com.penoder.dao.FunGifDao;
import com.penoder.dao.impl.FunGifDaoImpl;
import com.penoder.service.GifService;

public class GifServiceImpl implements GifService {

	@Override
	public String selectGif(String pageNum, String pageSize, String userID) {
		// TODO Auto-generated method stub
		FunGifDao sgd = new FunGifDaoImpl();
		return sgd.selectGif(pageNum, pageSize, userID);
	}

}
