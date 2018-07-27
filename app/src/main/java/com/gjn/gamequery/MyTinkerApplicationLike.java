package com.gjn.gamequery;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * @author gjn
 * @time 2018/7/26 16:43
 */

public class MyTinkerApplicationLike extends DefaultApplicationLike {

    private static TinkerListener tinkerListener;

    public MyTinkerApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                                   long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag,
                applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    public static void setTinkerListener(TinkerListener tinkerListener) {
        MyTinkerApplicationLike.tinkerListener = tinkerListener;
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        //是否自动检查补丁 默认true
//        Beta.autoCheckUpgrade = false;
//        //是否自动下载补丁 默认true
//        Beta.canAutoDownloadPatch = false;
//        //是否自动合成补丁 默认true
//        Beta.canAutoPatch = false;
//        //是否显示弹窗提示用户重启 默认false
//        Beta.canNotifyUserRestart = false;

        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String s) {

            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {

            }

            @Override
            public void onDownloadSuccess(String s) {

            }

            @Override
            public void onDownloadFailure(String s) {

            }

            @Override
            public void onApplySuccess(String s) {
                if (tinkerListener != null) {
                    tinkerListener.applyResult(true);
                }
            }

            @Override
            public void onApplyFailure(String s) {
                if (tinkerListener != null) {
                    tinkerListener.applyResult(false);
                }
            }

            @Override
            public void onPatchRollback() {

            }
        };

        Bugly.init(getApplication(), "9eeaeaf8a6", BuildConfig.DEBUG);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker(this);
    }

    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

    public interface TinkerListener {
        void applyResult(boolean result);
    }
}
