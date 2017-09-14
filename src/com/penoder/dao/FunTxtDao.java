package com.penoder.dao;

public interface FunTxtDao {

	/**
	 * 获取TxtFun列表相关数据，以及判断当前用户是否收藏、点赞等等
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param userID
	 * @return
	 */
	public String selectTxtFun(String pageNum, String pageSize, String userID);
	
	/**
	 * 用户 userID 对某条 TXT 数据 txtID 执行点赞（likeStatus = true）或取消点赞（likeStatus = false）操作
	 * txtThumb表中还有一个 thumbTime字段，时间由实现类插入到数据库是获取当前的时间，而不是作为参数传递
	 * 
	 * @param userID
	 * @param txtID
	 * @param likeStatus
	 * @return
	 */
	public String likeOrUnLikeTxt(String userID, String txtID, boolean likeStatus);
}
