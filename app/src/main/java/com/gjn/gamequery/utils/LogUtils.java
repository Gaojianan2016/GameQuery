package com.gjn.gamequery.utils;

import android.util.Log;

import com.gjn.gamequery.BuildConfig;

/**
 * @author gjn
 * @time 2018/10/8 16:27
 */

public class LogUtils {
    private static boolean isDebug = BuildConfig.DEBUG;

    private static final String TAG = "-s-";

    public static void i(String tag, String msg){
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg){
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg){
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void w(String tag, String msg){
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg){
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr){
        if (isDebug) {
            Log.e(tag, msg, tr);
        }
    }

    public static void i(String msg){
        i(TAG, msg);
    }

    public static void v(String msg){
        v(TAG, msg);
    }

    public static void d(String msg){
        d(TAG, msg);
    }

    public static void w(String msg){
        w(TAG, msg);
    }

    public static void e(String msg){
        e(TAG, msg);
    }

    public static void e(String msg, Throwable tr){
        e(TAG, msg, tr);
    }
}
