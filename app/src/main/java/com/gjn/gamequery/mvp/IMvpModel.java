package com.gjn.gamequery.mvp;

import android.app.Activity;

/**
 * @author gjn
 * @time 2018/8/1 16:49
 */

public interface IMvpModel<V extends IMvpView> {
    void onBind(Activity activity, V v);
    void unBind();
    V getMvpView();
}
