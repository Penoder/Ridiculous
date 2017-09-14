package com.penoder.service.impl;

import com.penoder.bean.AVideoBean;
import com.penoder.dao.InsertAVideoDao;
import com.penoder.dao.SelectAVideoDao;
import com.penoder.dao.impl.InsertAVideoDaoImpl;
import com.penoder.dao.impl.SelectAVideoDaoImpl;
import com.penoder.service.AVideoService;

public class AVideoServiceImpl implements AVideoService {

	@Override
	public String insertVideo(AVideoBean videoBean) {
		InsertAVideoDao insertVideoDao = new InsertAVideoDaoImpl();
		if (!((InsertAVideoDaoImpl) insertVideoDao).isExitVideoInDB(videoBean.getVideo_url())) {
			return insertVideoDao.insert(videoBean);
		}
		return videoBean.getTitle() + " 已经往数据库中插过了！";
	}

	@Override
	public String selectVideo(String pageNum, String pageSize) {
		// TODO Auto-generated method stub
		SelectAVideoDao selectVideoDao = new SelectAVideoDaoImpl();
		return selectVideoDao.selectVideo(pageNum, pageSize);
	}

}
