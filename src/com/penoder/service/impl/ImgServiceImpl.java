package com.penoder.service.impl;

import com.penoder.dao.FunImgDao;
import com.penoder.dao.impl.FunImgDaoImpl;
import com.penoder.service.ImgService;

public class ImgServiceImpl implements ImgService {

	@Override
	public String selectImg(String pageNum, String pageSize, String userID) {
		// TODO Auto-generated method stub
		FunImgDao fid = new FunImgDaoImpl();
		return fid.selectImg(pageNum, pageSize, userID);
	}

}
