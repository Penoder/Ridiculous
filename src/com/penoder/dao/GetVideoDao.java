package com.penoder.dao;

public interface GetVideoDao {

	/**
	 * 从数据表 video 中获取相应的Video数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String selectVideo(String pageNum, String pageSize);

}
