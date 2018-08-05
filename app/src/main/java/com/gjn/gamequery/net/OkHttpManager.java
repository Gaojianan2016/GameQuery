package com.gjn.gamequery.net;

import com.gjn.gamequery.utils.GsonUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * OkHttpManager
 * Created by gjn
 * on 2018-07-28 17:57.
 */
public class OkHttpManager {
    private static OkHttpClient okHttpClient;
    private static OkHttpManager okHttpManager;

    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private OkHttpManager() {
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }

    public static OkHttpManager getInstance() {
        if (okHttpManager == null) {
            synchronized (OkHttpManager.class){
                if (okHttpManager == null) {
                    okHttpManager = new OkHttpManager();
                }
            }
        }
        return okHttpManager;
    }

    public static void setOkHttpClient(OkHttpClient client){
        okHttpClient = client;
    }

    public static OkHttpClient getOkHttpClient(){
        getInstance();
        return okHttpClient;
    }

    public void get(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void postJson(String url, Map<String, Object> parmas, Callback callback){
        if (parmas == null) {
            parmas = new HashMap<>();
        }
        String json = GsonUtils.map2json(parmas);
        Request request = new Request.Builder()
                .post(RequestBody.create(MEDIA_TYPE_JSON, json))
                .url(url)
                .build();
        post(request, callback);
    }

    public void postString(String url, Object stringOrFile, Callback callback){
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (stringOrFile instanceof String) {
            builder.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, (String) stringOrFile));
        }else if (stringOrFile instanceof File){
            builder.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, (File) stringOrFile));
        }
        post(builder.build(), callback);
    }

    public void post(Request request, Callback callback){
        okHttpClient.newCall(request).enqueue(callback);
    }
}
