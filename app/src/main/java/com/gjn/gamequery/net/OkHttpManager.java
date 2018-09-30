package com.gjn.gamequery.net;

import android.util.Log;

import com.gjn.gamequery.utils.FileUtils;
import com.gjn.gamequery.utils.GsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttpManager
 * Created by gjn
 * on 2018-07-28 17:57.
 */
public class OkHttpManager {
    private static final String TAG = "OkHttpManager";

    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    private static OkHttpClient okHttpClient;
    private static OkHttpManager okHttpManager;

    private OkHttpManager() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
    }

    public static OkHttpManager getInstance() {
        if (okHttpManager == null) {
            synchronized (OkHttpManager.class) {
                if (okHttpManager == null) {
                    okHttpManager = new OkHttpManager();
                }
            }
        }
        return okHttpManager;
    }

    public static OkHttpClient getOkHttpClient() {
        getInstance();
        return okHttpClient;
    }

    public static void setOkHttpClient(OkHttpClient client) {
        okHttpClient = client;
    }

    public void get(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void post(String url, Callback callback) {
        postJson(url, null, callback);
    }

    public void postJson(String url, String json, Callback callback){
        if (json == null) {
            json = "";
        }
        Request request = new Request.Builder()
                .post(RequestBody.create(MEDIA_TYPE_JSON, json))
                .url(url)
                .build();
        post(request, callback);
    }

    public void postBody(String url, Map<String, String> maps, Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        if (maps != null) {
            for (String key : maps.keySet()) {
                builder.add(key, maps.get(key));
            }
        }
        Request request = new Request.Builder()
                .post(builder.build())
                .url(url)
                .build();
        post(request, callback);
    }

    public void postFile(String url, File file, Callback callback){
        if (file == null || !file.exists()) {
            Log.e(TAG, "file is null.");
            return;
        }
        String type = FileUtils.getTypeFromSuffix(file);
        if (type == null || type.isEmpty()) {
            type = "text/x-markdown";
        }
        Log.d(TAG, "post file type: " + type);
        Request request = new Request.Builder()
                .post(RequestBody.create(
                        MediaType.parse(type + "; charset=utf-8"),
                        file))
                .url(url)
                .build();
        post(request, callback);
    }

    public void post(Request request, Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }

    public void download(String url, final File file){
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        FileUtils.writeFile(file, response.body().byteStream());
                    }
                });
    }
}
