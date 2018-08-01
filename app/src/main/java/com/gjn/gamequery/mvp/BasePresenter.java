package com.gjn.gamequery.mvp;

/**
 * @author gjn
 * @time 2018/8/1 16:51
 */

public class BasePresenter<V extends IMvpView> implements IMvpPresenter<V>{

    protected V v;

    @Override
    public void onAttached(V view) {
        v = view;
    }

    @Override
    public void onDetached() {
        v = null;
    }

    @Override
    public boolean isAttached() {
        return v != null;
    }

    @Override
    public V getMvpView() {
        return v;
    }
}
