package com.gjn.gamequery.mvp;

import android.app.Activity;
import android.content.Context;

/**
 * @author gjn
 * @time 2018/8/1 16:49
 */

public interface IMvpPresenter<V extends IMvpView, M extends IMvpModel<V>> {
    void onAttached(Activity activity, V view);
    void onDetached();
    boolean isAttached();
    Activity getActivity();
    M getModel();
    V getMvpView();
}
