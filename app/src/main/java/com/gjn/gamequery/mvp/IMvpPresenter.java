package com.gjn.gamequery.mvp;

/**
 * @author gjn
 * @time 2018/8/1 16:49
 */

public interface IMvpPresenter<V extends IMvpView> {
    void onAttached(V view);
    void onDetached();
    boolean isAttached();
    V getMvpView();
}
