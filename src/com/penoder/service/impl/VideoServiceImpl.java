package com.penoder.service.impl;

import com.penoder.bean.VideoBean;
import com.penoder.dao.GetVideoDao;
import com.penoder.dao.InsertVideoDao;
import com.penoder.dao.impl.GetVideoDaoImpl;
import com.penoder.dao.impl.InsertVideoDaoImpl;
import com.penoder.service.VideoService;

public class VideoServiceImpl implements VideoService {

	@Override
	public String insertVideo(VideoBean videoBean) {
		InsertVideoDao insertVideoDao = new InsertVideoDaoImpl();
		if (!((InsertVideoDaoImpl) insertVideoDao).isExitVideoInDB(videoBean.videoUrl)) {
			return insertVideoDao.insert(videoBean);
		}
		return videoBean.title + " 已经往数据库中插过了！";
	}

	@Override
	public String selectVideo(String pageNum, String pageSize) {
		// TODO Auto-generated method stub
		GetVideoDao getVideoDao = new GetVideoDaoImpl();
		return getVideoDao.selectVideo(pageNum, pageSize);
	}

}
