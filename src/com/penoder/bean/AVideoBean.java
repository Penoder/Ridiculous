package com.penoder.bean;

public class AVideoBean {

	private int id;		// 视频ID
	private String title; // 视频标题
	private String keyword; // 视频关键字
	private double duration; // 持续时间（单位：秒）
	private double framerate; // 帧速率（和下面的HD字段说明视频质量的）
	private boolean hd; // 是否是HD类型视频
	private int addtime; // 加入到服务器的时间
	private String video_url; // 视频播放的URL
	private String embedded_url; // 嵌入浏览器的URL（没搞明白是干什么的，先留着）
	private String preview_url; // 预加载图片的URL
	private String preview_video_url; // 预加载视频的URL

	public AVideoBean() {

	}

	public AVideoBean(String title, String keyword, double duration,
			double framerate, boolean hd, int addtime, String video_url,
			String embedded_url, String preview_url, String preview_video_url) {
		super();
		this.title = title;
		this.keyword = keyword;
		this.duration = duration;
		this.framerate = framerate;
		this.hd = hd;
		this.addtime = addtime;
		this.video_url = video_url;
		this.embedded_url = embedded_url;
		this.preview_url = preview_url;
		this.preview_video_url = preview_video_url;
	}

	public AVideoBean(int id, String title, String keyword, double duration,
			double framerate, boolean hd, int addtime, String video_url,
			String embedded_url, String preview_url, String preview_video_url) {
		super();
		this.id = id;
		this.title = title;
		this.keyword = keyword;
		this.duration = duration;
		this.framerate = framerate;
		this.hd = hd;
		this.addtime = addtime;
		this.video_url = video_url;
		this.embedded_url = embedded_url;
		this.preview_url = preview_url;
		this.preview_video_url = preview_video_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getFramerate() {
		return framerate;
	}

	public void setFramerate(double framerate) {
		this.framerate = framerate;
	}

	public boolean isHd() {
		return hd;
	}

	public void setHd(boolean hd) {
		this.hd = hd;
	}

	public int getAddtime() {
		return addtime;
	}

	public void setAddtime(int addtime) {
		this.addtime = addtime;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	public String getEmbedded_url() {
		return embedded_url;
	}

	public void setEmbedded_url(String embedded_url) {
		this.embedded_url = embedded_url;
	}

	public String getPreview_url() {
		return preview_url;
	}

	public void setPreview_url(String preview_url) {
		this.preview_url = preview_url;
	}

	public String getPreview_video_url() {
		return preview_video_url;
	}

	public void setPreview_video_url(String preview_video_url) {
		this.preview_video_url = preview_video_url;
	}
}
