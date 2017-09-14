package com.penoder.service;

public interface GifService {

	/**
	 * 查询gif数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String selectGif(String pageNum, String pageSize, String userID);

}
