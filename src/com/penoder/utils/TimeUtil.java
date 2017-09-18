package com.penoder.utils;

import java.text.SimpleDateFormat;

/**
 * Created by asus on 2017/8/26.
 */

public class TimeUtil {

    /**
     * 将 long 类型的时间戳 转换成 String 类型的时间格式：如 yyyy-MM-dd HH:mm:ss
     *
     * @param unix
     * @param format
     * @return
     */
    public static String unixToString(long unix, String format) {
        String result = "";
        if (format == null || "".equals(format)) {
            format = "MM-dd";
        }
        long currentUnix = System.currentTimeMillis();  // 获取当前时间的时间戳
        long mistiming = currentUnix - unix;    // 计算时间差
        if (mistiming < 0) {    // 未来的某个时间（数据出错的情况才会走这吧）
            format = "yyyy-MM-dd HH:mm:ss";
//        } else if (mistiming < 60 * 1000) {     // 1 分钟之内
//            result = "刚刚";
//        } else if (mistiming < 60 * 60 * 1000) {    // 1 小时之内
//            result = (mistiming / 60000) + "分钟前";
//        } else if (mistiming < 24 * 60 * 60 * 1000) { // 1 天之内
//            result = (mistiming / 3600000) + "小时前";
//        } else if (transToString(unix, "yyyy").equals(transToString(currentUnix, "yyyy"))) { // 同一年
//            result = transToString(unix, format);
        } else {    // 不同年份
            if (!format.contains("y")) {    // 不同年份，如果时间格式不包含年，自动返回之前某年
                format = "yyyy-" + format;
            }
            result = transToString(unix, format);
        }
        return result;
    }

    private static String transToString(long unix, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(unix);
    }

}
