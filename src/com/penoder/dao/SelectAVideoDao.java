package com.penoder.dao;

public interface SelectAVideoDao {

	/**
	 * 从数据表 avideo 中获取相应的Video数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String selectVideo(String pageNum, String pageSize);

}
