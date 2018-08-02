package com.gjn.gamequery.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.gjn.gamequery.base.BaseFragment;

/**
 * @author gjn
 * @time 2018/8/2 10:40
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements IMvpView {

    protected MvpBindAnnotations mvpBindAnnotations;

    @Override
    protected void init() {
        super.init();
        mvpBindAnnotations = MvpBindAnnotations.getInstance(mActivity, mFragment);
    }

    protected P getPresenter() {
        return getPresenter(0);
    }

    protected P getPresenter(int i){
        return mvpBindAnnotations.getPresenter(i);
    }

    @Override
    public void onDestroy() {
        mvpBindAnnotations.detachedAll();
        super.onDestroy();
    }

    @Override
    public void showProgressUI(boolean isShow) {

    }

    @Override
    public void error(Throwable t) {

    }
}
