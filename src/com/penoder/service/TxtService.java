package com.penoder.service;

public interface TxtService {
	
	/**
	 * 按时间排序，分页获取TxtFun中的数据及相关的用户信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param userID
	 * @return
	 */
	public String getTxtFun(String pageNum, String pageSize, String userID);
	
}
