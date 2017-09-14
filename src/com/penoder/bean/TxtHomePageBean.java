package com.penoder.bean;

/**
 * 首页要用到的TxtFun的相关字段
 * 
 * @author asus
 * 
 */
public class TxtHomePageBean {

	// txtFun表中的数据
	public int id;
	public String txtID;
	public String title;
	public String content;
	public String time;

	// 关联的表中的数据
	public int thumbNum;
	public int collectNum;
	public int commentNum;

	public boolean isThumb;
	public boolean isCollect;

}
