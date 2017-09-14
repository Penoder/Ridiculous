package com.penoder.service;

public interface ImgService {

	/**
	 * 查询img数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String selectImg(String pageNum, String pageSize, String userID);

}
