package com.gjn.gamequery.net;

import com.gjn.gamequery.utils.GsonUtils;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
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

    public static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private OkHttpManager() {
        okHttpClient = new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
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

    public OkHttpManager setOkHttpClient(OkHttpClient client){
        okHttpClient = client;
        return this;
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

    public void postString(String url, Object string, Callback callback){
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (string instanceof String) {
            builder.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, (String) string));
        }else if (string instanceof File){
            builder.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, (File) string));
        }
        post(builder.build(), callback);
    }

    public void post(Request request, Callback callback){
        okHttpClient.newCall(request).enqueue(callback);
    }
}
