package com.penoder.dao;

public interface FunGifDao {
	
	/**
	 * 查询gif数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String selectGif(String pageNum, String pageSize, String userID);
	
	/**
	 * 用户 userID 对某条 GIF数据 gifID 执行点赞（likeStatus = true）或取消点赞（likeStatus = false）操作
	 * gifThumb表中还有一个 thumbTime字段，时间由实现类插入到数据库是获取当前的时间，而不是作为参数传递
	 * 
	 * @param userID
	 * @param gifID
	 * @param likeStatus
	 * @return
	 */
	public String likeOrUnLikeGif(String userID, String gifID, boolean likeStatus);
	
	/**
	 * 用户 userID 对某条 GIF数据 gifID 执行收藏（isCollect = true）或取消收藏（isCollect = false）操作
	 * gifThumb表中还有一个 thumbTime字段，时间由实现类插入到数据库是获取当前的时间，而不是作为参数传递
	 * 
	 * @param userID
	 * @param gifID
	 * @param isCollect
	 * @return
	 */
	public String collectOrCancelGif(String userID, String gifID, boolean isCollect);
}
