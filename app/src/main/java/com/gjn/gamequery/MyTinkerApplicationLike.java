package com.gjn.gamequery;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

/**
 * @author gjn
 * @time 2018/7/26 16:43
 */

public class MyTinkerApplicationLike extends DefaultApplicationLike {

    public static TinkerListener tinkerListener;

    public MyTinkerApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
                                   long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag,
                applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //是否自动检查补丁
        Beta.autoCheckUpgrade = false;
        //是否自动下载补丁
        Beta.canAutoDownloadPatch = false;
        //是否自动合成补丁
        Beta.canAutoPatch = false;
        //是否显示弹窗提示用户重启
        Beta.canNotifyUserRestart = false;

        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String s) {
                String str = "有新版本，下载地址为: " + s;
                Log.e("-s-", str);
                if (tinkerListener != null) {
                    tinkerListener.addText(str);
                }
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Log.e("-s-", "补丁下载ing");
                Log.e("-s-", "savedLength = " + savedLength);
                Log.e("-s-", "totalLength = " + totalLength);
            }

            @Override
            public void onDownloadSuccess(String s) {
                String str = "下载成功: " + s;
                Log.e("-s-", "下载成功: " + s);
                if (tinkerListener != null) {
                    tinkerListener.addText(str);
                }
            }

            @Override
            public void onDownloadFailure(String s) {
                String str = "下载失败: " + s;
                Log.e("-s-", "下载失败: " + s);
                if (tinkerListener != null) {
                    tinkerListener.addText(str);
                }
            }

            @Override
            public void onApplySuccess(String s) {
                String str = "合成成功: " + s;
                Log.e("-s-", "合成成功: " + s);
                if (tinkerListener != null) {
                    tinkerListener.addText(str);
                }
            }

            @Override
            public void onApplyFailure(String s) {
                String str = "合成失败: " + s;
                Log.e("-s-", "合成失败: " + s);
                if (tinkerListener != null) {
                    tinkerListener.addText(str);
                }
            }

            @Override
            public void onPatchRollback() {
                Log.e("-s-", "补丁回滚");
                if (tinkerListener != null) {
                    tinkerListener.addText("补丁回滚");
                }
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

    public static void setTinkerListener(TinkerListener tinkerListener) {
        MyTinkerApplicationLike.tinkerListener = tinkerListener;
    }

    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

    public interface TinkerListener{
        void addText(String str);
    }
}
