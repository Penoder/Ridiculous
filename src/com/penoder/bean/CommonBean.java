package com.penoder.bean;

/**
 * 该类用作返回数据的格式：	//
 * 	int 类型的状态码	//
 * 	message 对应每种状态以及每次返回的数据的提示性信息	//
 * 	datas 即表示需要的数据类型，可以是基本类型，也可以是数组、集合等对象。	//
 * 
 * @author Penoder
 *
 * @param <T>
 */
public class CommonBean<T> {

	private int status;
	private String message;
	private T datas;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}
}
