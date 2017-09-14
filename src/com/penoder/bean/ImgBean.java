package com.penoder.bean;

public class ImgBean {
	private String id;
	private String title;
	private String img;
	private int type;
	private String ct;

	public ImgBean() {

	}

	public ImgBean(String id, String title, String img, int type, String ct) {
		super();
		this.id = id;
		this.title = title;
		this.img = img;
		this.type = type;
		this.ct = ct;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}
}
