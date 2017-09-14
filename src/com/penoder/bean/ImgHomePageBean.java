package com.penoder.bean;

/**
 * 首页要用到的GifFun（ImgFun）的相关字段
 * 
 * @author asus
 * 
 */
public class ImgHomePageBean {

	// gifFun（imgFun）表中的数据
	public int id;
	public String gifID;
	public String imgID;
	public String title;
	public String imgUrl;
	public String time;

	// 关联的表中的数据
	public int thumbNum;
	public int collectNum;
	public int commentNum;

	public boolean isThumb;
	public boolean isCollect;

}
