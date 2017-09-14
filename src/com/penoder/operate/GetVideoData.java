package com.penoder.operate;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.penoder.bean.AVideoBean;
import com.penoder.bean.VideoOutBean;
import com.penoder.service.AVideoService;
import com.penoder.service.impl.AVideoServiceImpl;

public class GetVideoData {

	/**
	 * 保存获取到的全部Video数据
	 */
	private static List videoDatas = new ArrayList();
	
	/**
	 * 根据接口返回的数据，判断是不是还有数据，有就继续分页加载，没有就终止
	 */
	private static boolean hasMoreData = true;
	
	private static int pageNum = 998;

	public static void main(String[] args) {
		GetVideoData gvd = new GetVideoData();
		while(hasMoreData) {
			System.out.println("现在加载的是第 "+ pageNum +" 页的数据");
			String videoJson = "";
			try {
				videoJson = gvd.getVideos(pageNum + "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Gson gson = new Gson();
			if (!"".equals(videoJson)) {
				VideoOutBean videoOutBean = gson.fromJson(videoJson, VideoOutBean.class);
				if (videoOutBean.isSuccess()) {	// API接口调用请求成功
					pageNum++;
					List<AVideoBean> videos = new ArrayList<AVideoBean>();
					if (videoOutBean.getResponse().getVideos() != null && videoOutBean.getResponse().getVideos().size() > 0) {
						videos = videoOutBean.getResponse().getVideos();
					}
					
					for (AVideoBean videoBean : videos) {
						String title = videoBean.getTitle();	// 视频标题
						String keyword = videoBean.getKeyword();	// 视频关键字
						double duration = videoBean.getDuration();	// 持续时间（单位：秒）
						double framerate = videoBean.getFramerate();	// 帧速率（和下面的HD字段说明视频质量的）
						boolean hd = videoBean.isHd();		// 是否是HD类型视频
						int addtime = videoBean.getAddtime();
						String video_url = videoBean.getVideo_url();	// 视频播放的URL
						String embedded_url = videoBean.getEmbedded_url();	// 嵌入浏览器的URL（没搞明白是干什么的，先留着）
						String preview_url = videoBean.getPreview_url();	// 预加载图片的URL
						String preview_video_url = videoBean.getPreview_video_url();	// 预加载视频的URL
						
						AVideoBean video = new AVideoBean(title, keyword, duration, framerate, hd, addtime, video_url, embedded_url, preview_url, preview_video_url);
						
						AVideoService videoService = new AVideoServiceImpl();
						videoService.insertVideo(video);
					}
					
					if (videoOutBean.getResponse().isHas_more()) {	// 还有更多的数据
						hasMoreData = true;
					} else {
						hasMoreData = false;
					}
				}
			}
//			System.out.println("返回的视频相关数据： " + videoJson);
		}
		
		System.out.println("总共的数据有多少条--------- " + videoDatas.size());
	}

	/**
	 * 根据API返回Json数据
	 * 
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public String getVideos(String pageNum) throws Exception {
		String videoAPI = "https://api.avgle.com/v1/videos/" + (Integer.parseInt(pageNum) < 0 ? "0" : pageNum) + "?public";
		URL u = new URL(videoAPI);
		InputStream in = u.openStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte buf[] = new byte[1024];
			int read = 0;
			while ((read = in.read(buf)) > 0) {
				out.write(buf, 0, read);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
		byte b[] = out.toByteArray();
		return new String(b, "utf-8");
	}
}
