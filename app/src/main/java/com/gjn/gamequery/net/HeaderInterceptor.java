package com.gjn.gamequery.net;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gjn.gamequery.BuildConfig;

import java.io.IOException;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author gjn
 * @time 2018/7/31 11:39
 */

public class HeaderInterceptor implements Interceptor {
    private static final String TAG = "HeaderInterceptor";

    public static boolean isDebug = BuildConfig.DEBUG;
    public static boolean isLinkTime = BuildConfig.DEBUG;

    private OnChangeHeader onChangeHeader;

    public HeaderInterceptor() {
    }

    public HeaderInterceptor(OnChangeHeader onChangeHeader) {
        this.onChangeHeader = onChangeHeader;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Headers headers;
        Request request = chain.request();
        if (onChangeHeader != null) {
            Map<String, String> heads = onChangeHeader.addRequestHeader();
            if (heads != null && heads.size() > 0) {
                Request.Builder builder = request.newBuilder()
                        .method(request.method(), request.body());

                for (Map.Entry<String, String> entry : heads.entrySet()) {
                    builder.header(entry.getKey(), entry.getValue());
                }
                request = builder.build();
            }
        }
        long startTime = System.nanoTime();
        log("--> HTTP URL: " + request.url() + " TYPE: " + request.method());
        headers = request.headers();
        if (headers.size() > 0) {
            log("--> HTTP REQUEST HEAD: ");
            for (int i = 0; i < headers.size(); i++) {
                log("--> " + headers.name(i) + " = " + headers.value(i));
            }
        }
        log("============ HTTP REQUEST END ============");
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            Log.e(TAG, "--> HTTP FAILED: " + e);
            return null;
        }
        long endTime = System.nanoTime();
        if (isLinkTime) {
            Log.d(TAG, String.format("--> %s : %.1fms", response.request().url(), (endTime - startTime) / 1e6d));
        }
        log("--> HTTP SUCCESS: " + response.code());
        headers = response.headers();
        if (headers.size() > 0) {
            log("--> HTTP RESPONSE HEAD: ");
            for (int i = 0; i < headers.size(); i++) {
                log("--> " + headers.name(i) + " = " + headers.value(i));
            }
            if (onChangeHeader != null) {
                onChangeHeader.getResponseHeader(String.valueOf(response.request().url()), headers);
            }
        }
        log("============ HTTP RESPONSE END ============");
        return response;
    }

    public void setOnChangeHeader(OnChangeHeader onChangeHeader) {
        this.onChangeHeader = onChangeHeader;
    }

    private void log(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public interface OnChangeHeader {
        Map<String, String> addRequestHeader();

        void getResponseHeader(String url, Headers headers);
    }
}
