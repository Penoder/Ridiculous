package com.penoder.service;

/**
 * 执行点赞和取消点赞操作
 * 
 * @author Penoder
 *
 */
public interface LikeService {
	
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
	 * 用户 userID 对某条 IMG数据 imgID 执行点赞（likeStatus = true）或取消点赞（likeStatus = false）操作
	 * imgThumb表中还有一个 thumbTime字段，时间由实现类插入到数据库是获取当前的时间，而不是作为参数传递
	 * 
	 * @param userID
	 * @param imgID
	 * @param likeStatus
	 * @return
	 */
	public String likeOrUnLikeImg(String userID, String imgID, boolean likeStatus);
	
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
