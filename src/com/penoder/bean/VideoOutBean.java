package com.penoder.bean;

import java.util.List;

public class VideoOutBean {

	private boolean success;	// 判断是不是请求成功了
	private ResponseBean response;	// 响应的数据

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ResponseBean getResponse() {
		return response;
	}

	public void setResponse(ResponseBean response) {
		this.response = response;
	}

	public static class ResponseBean {

		private boolean has_more;	// 判断是不是还有数据
		private int total_videos;	// 总共的数据条数
		private int current_offset;
		private int limit;	// pageSize的大小，默认50，范围1到250
		private List<AVideoBean> videos;	// 真正的数据

		public boolean isHas_more() {
			return has_more;
		}

		public void setHas_more(boolean has_more) {
			this.has_more = has_more;
		}

		public int getTotal_videos() {
			return total_videos;
		}

		public void setTotal_videos(int total_videos) {
			this.total_videos = total_videos;
		}

		public int getCurrent_offset() {
			return current_offset;
		}

		public void setCurrent_offset(int current_offset) {
			this.current_offset = current_offset;
		}

		public int getLimit() {
			return limit;
		}

		public void setLimit(int limit) {
			this.limit = limit;
		}

		public List<AVideoBean> getVideos() {
			return videos;
		}

		public void setVideos(List<AVideoBean> videos) {
			this.videos = videos;
		}
	}
}
