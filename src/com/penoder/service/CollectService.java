package com.penoder.service;

/**
 * 用户收藏、或者取消收藏FunID的接口
 * 
 * @author Penoder
 *
 */
public interface CollectService {
	
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
	
	/**
	 * 用户 userID 对某条 IMG数据 imgID 执行收藏（isCollect = true）或取消收藏（isCollect = false）操作
	 * imgThumb表中还有一个 thumbTime字段，时间由实现类插入到数据库是获取当前的时间，而不是作为参数传递
	 * 
	 * @param userID
	 * @param imgID
	 * @param isCollect
	 * @return
	 */
	public String collectOrCancelImg(String userID, String imgID, boolean isCollect);
	
	/**
	 * 用户 userID 对某条 TXT 数据 txtID 执行收藏（isCollect = true）或取消收藏（isCollect = false）操作
	 * txtThumb表中还有一个 thumbTime字段，时间由实现类插入到数据库是获取当前的时间，而不是作为参数传递
	 * 
	 * @param userID
	 * @param txtID
	 * @param isCollect
	 * @return
	 */
	public String collectOrCancelTxt(String userID, String txtID, boolean isCollect);

}
