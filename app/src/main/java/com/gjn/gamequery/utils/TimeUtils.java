package com.gjn.gamequery.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gjn
 * @time 2018/8/6 17:32
 */

public class TimeUtils {
    public static String formatTime(long time) {
        return formatTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatTime(long time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }

    public static long formatTime(String time) throws ParseException {
        return formatTime(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static long formatTime(String time, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = dateFormat.parse(time);
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

    public static String overTime(String oldTime) throws ParseException {
        return overTime(formatTime(oldTime), System.currentTimeMillis());
    }

    public static String overTime(long oldTime) {
        return overTime(oldTime, System.currentTimeMillis());
    }

    public static String overTime(long oldTime, long nowTime) {
        long delay = Math.abs(nowTime - oldTime) / 1000;
        if (delay < 60 * 60) {
            return delay / 60 + "分钟前";
        } else if (delay < 24 * 60 * 60) {
            return delay / 60 / 60 + "小时前";
        } else if (delay < 3 * 24 * 60 * 60) {
            return delay / 24 / 60 / 60 + "天前";
        }
        return formatTime(oldTime);
    }
}
