package com.gjn.gamequery.utils;

import android.net.ParseException;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

/**
 * @author gjn
 * @time 2018/10/8 15:59
 */

public class ExceptionEngine {

    public static String handleException(Throwable e){
        if (e instanceof HttpException) {
            return "网络错误";
        }else if (e instanceof JsonParseException || e instanceof JSONException
                || e instanceof ParseException || e instanceof MalformedJsonException) {
            return "解析错误";
        }else if (e instanceof ConnectException) {
            return "连接失败";
        }else if (e instanceof SocketTimeoutException) {
            return "网络超时";
        }
        return e.getMessage();
    }
}
