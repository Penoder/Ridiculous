package com.penoder.service.impl;

import com.penoder.dao.FunGifDao;
import com.penoder.dao.FunImgDao;
import com.penoder.dao.FunTxtDao;
import com.penoder.dao.impl.FunGifDaoImpl;
import com.penoder.dao.impl.FunImgDaoImpl;
import com.penoder.dao.impl.FunTxtDaoImpl;
import com.penoder.service.LikeService;

public class LikeServiceImpl implements LikeService {

	@Override
	public String likeOrUnLikeGif(String userID, String gifID,
			boolean likeStatus) {
		// TODO Auto-generated method stub
		FunGifDao funGifDao = new FunGifDaoImpl();
		return funGifDao.likeOrUnLikeGif(userID, gifID, likeStatus);
	}

	@Override
	public String likeOrUnLikeImg(String userID, String imgID,
			boolean likeStatus) {
		// TODO Auto-generated method stub
		FunImgDao funImgDao = new FunImgDaoImpl();
		return funImgDao.likeOrUnLikeImg(userID, imgID, likeStatus);
	}

	@Override
	public String likeOrUnLikeTxt(String userID, String txtID,
			boolean likeStatus) {
		// TODO Auto-generated method stub
		FunTxtDao funTxtDao = new FunTxtDaoImpl();
		return funTxtDao.likeOrUnLikeTxt(userID, txtID, likeStatus);
	}

}
