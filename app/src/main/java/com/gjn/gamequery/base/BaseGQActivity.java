package com.gjn.gamequery.base;

import android.graphics.Color;
import android.os.Bundle;

import com.gjn.gamequery.ui.webview.WebActivity;
import com.gjn.mvpannotationlibrary.base.BaseMvpActivity;
import com.gjn.mvpannotationlibrary.base.BasePresenter;
import com.gjn.statusbarlibrary.StatusBarUtils;

import butterknife.ButterKnife;

/**
 * @author gjn
 * @time 2018/8/3 16:03
 */

public abstract class BaseGQActivity<P extends BasePresenter> extends BaseMvpActivity<P> {

    @Override
    protected void init() {
        super.init();
        ButterKnife.bind(this);

        setStatusbar();
    }

    protected void setStatusbar() {
        StatusBarUtils.statusBarMode(mActivity, true, Color.WHITE);
        StatusBarUtils.setContentViewFitsSystemWindows(mActivity, true);
    }

    protected void openUrl(String url){
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        showNextActivity(WebActivity.class, bundle);
    }

    protected String getUrl(){
        return mBundle.getString("url", "");
    }
}
