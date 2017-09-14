package com.penoder.bean;

public class VideoBean {
	public int id; // 视频ID
	public String title; // 视频标题
	public long addTime; // 加入时间
	public String previewUrl; // 视频预览图片地址
	public String videoUrl; // 视频播放地址

	public VideoBean(int videoId, String title, int addtime, String previewUrl, String video_url) {
		id = videoId;
		this.title = title;
		this.addTime = addtime;
		this.previewUrl = previewUrl;
		videoUrl = video_url;
	}
}
