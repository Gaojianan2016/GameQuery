package com.gjn.gamequery.net;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gjn.gamequery.BuildConfig;

import java.io.IOException;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
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
        RequestBody body;
        Request request = chain.request();
        if (onChangeHeader != null) {
            Map<String, String> heads = onChangeHeader.addRequestHeader(String.valueOf(request.url()));
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
        log("START--> HTTP URL: " + request.url() + " TYPE: " + request.method());
        headers = request.headers();
        if (headers.size() > 0) {
            log("START--> HTTP REQUEST HEAD: ");
            for (int i = 0; i < headers.size(); i++) {
                log("START--> " + headers.name(i) + " = " + headers.value(i));
            }
        }
        body = request.body();
        if (body != null) {
            log("START--> HTTP BODY LENGTH: " + body.contentLength());
            if (body instanceof FormBody) {
                log("START--> HTTP REQUEST BODY: ");
                for (int i = 0; i < ((FormBody) body).size(); i++) {
                    log("START--> " + ((FormBody) body).encodedName(i) + "=" + ((FormBody) body).encodedValue(i));
                }
            }
        }
        log("============ HTTP REQUEST END ============");
        Response response = chain.proceed(request);
        long endTime = System.nanoTime();
        if (isLinkTime) {
            Log.d(TAG, String.format("END--> %s : %.1fms", response.request().url(), (endTime - startTime) / 1e6d));
        }
        log("END--> HTTP SUCCESS: " + response.code());
        headers = response.headers();
        if (headers.size() > 0) {
            log("END--> HTTP RESPONSE HEAD: ");
            for (int i = 0; i < headers.size(); i++) {
                log("END--> " + headers.name(i) + " = " + headers.value(i));
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
        Map<String, String> addRequestHeader(String url);

        void getResponseHeader(String url, Headers headers);
    }
}
