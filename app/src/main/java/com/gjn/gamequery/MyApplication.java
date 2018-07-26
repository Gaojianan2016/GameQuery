package com.gjn.gamequery;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * @author gjn
 * @time 2018/7/25 18:12
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Bugly.init(getApplicationContext(), "9eeaeaf8a6", BuildConfig.DEBUG);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }
}
