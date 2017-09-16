package com.penoder.service.impl;

import com.penoder.dao.FunGifDao;
import com.penoder.dao.FunImgDao;
import com.penoder.dao.FunTxtDao;
import com.penoder.dao.impl.FunGifDaoImpl;
import com.penoder.dao.impl.FunImgDaoImpl;
import com.penoder.dao.impl.FunTxtDaoImpl;
import com.penoder.service.CollectService;

public class CollectServiceImpl implements CollectService {

	@Override
	public String collectOrCancelGif(String userID, String gifID, boolean isCollect) {
		// TODO Auto-generated method stub
		FunGifDao funGifDao = new FunGifDaoImpl();
		return funGifDao.collectOrCancelGif(userID, gifID, isCollect);
	}

	@Override
	public String collectOrCancelImg(String userID, String imgID, boolean isCollect) {
		// TODO Auto-generated method stub
		FunImgDao funImgDao = new FunImgDaoImpl();
		return funImgDao.collectOrCancelImg(userID, imgID, isCollect);
	}

	@Override
	public String collectOrCancelTxt(String userID, String txtID, boolean isCollect) {
		// TODO Auto-generated method stub
		FunTxtDao funTxtDao = new FunTxtDaoImpl();
		return funTxtDao.collectOrCancelTxt(userID, txtID, isCollect);
	}

}
