package com.penoder.bean;

public class TxtBean {
	private String id;
	private String title;
	private String text;
	private int type;
	private String ct;

	public TxtBean() {

	}

	public TxtBean(String id, String title, String text, int type, String ct) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
