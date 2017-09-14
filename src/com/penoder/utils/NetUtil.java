package com.penoder.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

public class NetUtil {

	/**
	 * 通过API获取返回的相应数据
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String getDataByURL(String url) throws Exception {
		URL u = new URL(url);
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
