package com.gjn.gamequery.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gjn.gamequery.base.BaseActivity;

import java.util.Map;

/**
 * @author gjn
 * @time 2018/8/1 16:57
 */

public abstract class BaseMvpActivity<P extends  BasePresenter> extends BaseActivity implements IMvpView{

    protected P mPresenter;
    protected MvpBindAnnotations mvpBindAnnotations;

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvpBindAnnotations = MvpBindAnnotations.getInstance(mActivity);
    }

    protected abstract P getPresenter();

    @Override
    protected void onDestroy() {
        for (Map.Entry<String, BasePresenter> entry : mvpBindAnnotations.getPresentersMap().entrySet()) {
            if (entry.getValue() != null) {
                entry.getValue().onDetached();
            }
        }
        super.onDestroy();
    }

    @Override
    public void showProgressUI(boolean isShow) {

    }

    @Override
    public void error(Throwable t) {

    }
}
