package com.penoder.dao;

import java.util.List;

import com.penoder.bean.ImgBean;
import com.penoder.bean.TxtBean;

public interface InsertFunDao {

	/**
	 * 往数据库中插入GIF数据
	 * 
	 * @param imgBean
	 * @return
	 */
	public String insertGif(List<ImgBean> imgBeans);
	
	/**
	 * 往数据库中插入IMG数据
	 * 
	 * @param imgBeans
	 * @return
	 */
	public String insertImg(List<ImgBean> imgBeans);
	
	/**
	 * 往数据库中插入TXT数据
	 * 
	 * @param txtBeans
	 * @return
	 */
	public String insertTxt(List<TxtBean> txtBeans);
	
}
