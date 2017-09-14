package com.penoder.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 此类用来判断一些文件等是属于什么格式的，例如照片、音频、视频等
 * 以及对中文文件名进行编码，解码操作
 * 
 * @author Penoder
 * 
 */
public class FormatUtil {
	/**
	 * 用于判断文件是不是图片类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isPicture(String fileName) {
		return false;
	}

	/**
	 * 用于判断文件是不是音频类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isAudio(String fileName) {
		fileName = fileName.toLowerCase();	// 转为小写字母
		if (fileName.contains(".mp3") || fileName.contains(".wav") || fileName.contains(".ape")) { 
			return true;
		}
		return false;
	}

	/**
	 * 用于判断文件是不是视频类型
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isVideo(String fileName) {
		fileName = fileName.toLowerCase();	// 转为小写字母
		if (fileName.contains(".flv") || fileName.contains(".mp4") || fileName.contains(".avi")) { 
			return true;
		}
		return false;
	}


    /**
     * 采用 URLEncoder 编码，将中文以 UTF-8 的形式编码
     *
     * @param decodeStr
     * @return
     */
    public static String encodeUTF8(String decodeStr) {
        if (decodeStr == null || "".equals(decodeStr)) {
            return null;
        }
        String encodeStr = null;
        try {
            encodeStr = URLEncoder.encode(decodeStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 采用 URLEncoder 编码，将中文以 gb2312(GBK) 的形式编码
     *
     * @param decodeStr
     * @return
     */
    public static String encodeGBK(String decodeStr) {
        if (decodeStr == null || "".equals(decodeStr)) {
            return null;
        }
        String encodeStr = null;
        try {
            encodeStr = URLEncoder.encode(decodeStr, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 采用 URLDecoder 解码，将 UTF-8 的形式解码成中文
     *
     * @param encodeStr
     * @return
     */
    public static String decodeUTF8(String encodeStr) {
        if (encodeStr == null || "".equals(encodeStr)) {
            return null;
        }
        String decodeStr = null;
        try {
            decodeStr = URLDecoder.decode(encodeStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeStr;
    }

    /**
     * 采用 URLDecoder 编码，将 gb2312 的形式解码成中文
     *
     * @param encodeStr
     * @return
     */
    public static String decodeGBK(String encodeStr) {
        if (encodeStr == null || "".equals(encodeStr)) {
            return null;
        }
        String decodeStr = null;
        try {
            decodeStr = URLDecoder.decode(encodeStr, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeStr;
    }
    
    /**
     * 获取文件的时长信息
     * 
     * @param fileName
     * @return
     */
    public static Integer getFileTime(String fileName) {
    	return null;
    }
}
