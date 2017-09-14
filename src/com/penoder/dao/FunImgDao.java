package com.penoder.dao;

public interface FunImgDao {
	
	/**
	 * 查询img数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public String selectImg(String pageNum, String pageSize, String userID);
	
	/**
	 * 用户 userID 对某条 IMG数据 imgID 执行点赞（likeStatus = true）或取消点赞（likeStatus = false）操作
	 * imgThumb表中还有一个 thumbTime字段，时间由实现类插入到数据库是获取当前的时间，而不是作为参数传递
	 * 
	 * @param userID
	 * @param imgID
	 * @param likeStatus
	 * @return
	 */
	public String likeOrUnLikeImg(String userID, String imgID, boolean likeStatus);
}
