package com.gjn.gamequery.mvp;

import android.app.Activity;
import android.util.Log;

/**
 * @author gjn
 * @time 2018/8/2 14:31
 */

public class BaseModel<V extends IMvpView> implements IMvpModel<V> {

    protected Activity activity;
    private V v;

    @Override
    public void onBind(Activity activity, V view) {
        this.activity = activity;
        v = view;
    }

    @Override
    public void unBind(){
        activity = null;
        v = null;
    }

    @Override
    public V getMvpView() {
        return v;
    }
}
