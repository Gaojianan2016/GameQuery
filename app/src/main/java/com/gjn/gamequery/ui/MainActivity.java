package com.gjn.gamequery.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gjn.bottombarlibrary.BarTab;
import com.gjn.bottombarlibrary.BottomBarView;
import com.gjn.bottombarlibrary.OnBindBarDateListener;
import com.gjn.gamequery.BuildConfig;
import com.gjn.gamequery.MyTinkerApplicationLike;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseActivity;
import com.gjn.gamequery.fragment.home.HomeFragment;
import com.gjn.gamequery.fragment.news.NewsFragment;
import com.gjn.gamequery.fragment.tool.ToolFragment;
import com.gjn.gamequery.fragment.user.UserFragment;
import com.gjn.gamequery.net.OkHttpManager;
import com.gjn.gamequery.utils.GsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Headers;

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
//        OkHttpManager.getInstance().postJson("", map, callback);

//        OkHttpManager.setOkHttpClient(OkHttpManager.getOkHttpClient().newBuilder()
//                .addInterceptor(new LoggingInterceptor())
//                .build());
//
//        Request request = new Request.Builder()
//                .url("http://www.publicobject.com/helloworld.txt")
//                .header("User-Agent", "OkHttp Example")
//                .build();
//
//        OkHttpManager.getInstance().post(request, callback);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("-s-logging", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://v1.gumiss.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(IService.class)
                .splash()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Log.e("-s-splash", responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("-s-splash", "fail", throwable);
                    }
                });

        retrofit.create(IService.class)
                .cartList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Log.e("-s-cartList", responseBody.string());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("-s-cartList", "fail", throwable);
                    }
                });

    }

    public interface IService{

//        @Body("application/json; charset=UTF-8")
        @GET("front/ad/splash")
        Observable<ResponseBody> splash();

        @GET("cart/list")
        Observable<ResponseBody> cartList();

    }
}
