package com.gjn.gamequery.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gjn.bottombarlibrary.BarTab;
import com.gjn.bottombarlibrary.BottomBarView;
import com.gjn.bottombarlibrary.OnBindBarDateListener;
import com.gjn.gamequery.MyTinkerApplicationLike;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseActivity;
import com.gjn.gamequery.fragment.home.HomeFragment;
import com.gjn.gamequery.fragment.news.NewsFragment;
import com.gjn.gamequery.fragment.tool.ToolFragment;
import com.gjn.gamequery.fragment.user.UserFragment;
import com.gjn.gamequery.net.OkHttpManager;
import com.gjn.gamequery.utils.FileUtils;
import com.gjn.gamequery.utils.GsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends BaseActivity {

    private List<BarTab> list;
    public static final String BAR_HOME = "首页";
    public static final String BAR_NEWS = "新闻";
    public static final String BAR_TOOL = "工具";
    public static final String BAR_USER = "我";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        MyTinkerApplicationLike.setTinkerListener(new MyTinkerApplicationLike.TinkerListener() {
            @Override
            public void applyResult(boolean result) {
                if (result) {
                    showToast("app有热更新，可以重启app获取新功能!");
                }
            }
        });
    }

    @Override
    protected void initView() {
        BottomBarView bottomBarView = findViewById(R.id.bbv_main);

        list = new ArrayList<>();
        BarTab barTab;
        Bundle bundle;

        barTab = new BarTab();
        barTab.setTitle(BAR_HOME);
        barTab.setCls(HomeFragment.class);
        bundle = new Bundle();
        bundle.putString("title", BAR_HOME);
        barTab.setBundle(bundle);
        list.add(barTab);

        barTab = new BarTab();
        barTab.setTitle(BAR_NEWS);
        barTab.setCls(NewsFragment.class);
        bundle = new Bundle();
        bundle.putString("title", BAR_NEWS);
        barTab.setBundle(bundle);
        list.add(barTab);

        barTab = new BarTab();
        barTab.setTitle(BAR_TOOL);
        barTab.setCls(ToolFragment.class);
        bundle = new Bundle();
        bundle.putString("title", BAR_TOOL);
        barTab.setBundle(bundle);
        list.add(barTab);

        barTab = new BarTab();
        barTab.setTitle(BAR_USER);
        barTab.setCls(UserFragment.class);
        bundle = new Bundle();
        bundle.putString("title", BAR_USER);
        barTab.setBundle(bundle);
        list.add(barTab);

        bottomBarView.setOnBindBarDateListener(new OnBindBarDateListener() {
            @Override
            public void onBindBarView(View view, int i, BarTab barTab) {
                TextView textView = view.findViewById(R.id.tv_bar_item);
                textView.setText(barTab.getTitle());
            }
        }).updataView(list);
    }

    @Override
    protected void initData() {
        Callback callback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("-s-", "fail", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("-s-", response.body().string());
            }
        };


        Map<String, Object> map = new HashMap<>();
        map.put("a", 111);
        map.put("b", true);
        map.put("c", "你好");

        String json = GsonUtils.map2json(map);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new TestInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .header("User-Agent", "OkHttp Example")
                .build();

        okHttpClient.newCall(request).enqueue(callback);

    }

    class TestInterceptor implements Interceptor {
        private static final String TAG = "TestInterceptor";
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.nanoTime();
            Log.e(TAG, String.format("request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);
            long endTime = System.nanoTime();
            Log.e(TAG, String.format("response for %s in %.1fms%n%s",
                    response.request().url(), (endTime - startTime) / 1e6d, response.headers()));
            return response;
        }
    }
}
