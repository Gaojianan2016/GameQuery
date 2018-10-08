package com.gjn.gamequery.base;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.gjn.gamequery.activity.webview.WebActivity;
import com.gjn.gamequery.utils.DialogUtils;
import com.gjn.gamequery.utils.LogUtils;
import com.gjn.mvpannotationlibrary.base.BaseMvpFragment;
import com.gjn.mvpannotationlibrary.base.BasePresenter;

import butterknife.ButterKnife;

/**
 * @author gjn
 * @time 2018/8/3 16:04
 */

public abstract class BaseGQFragment<P extends BasePresenter> extends BaseMvpFragment<P> {

    private DialogFragment dialogFragment;
    private boolean isShowDialog = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void init() {
        super.init();
        ButterKnife.bind(this, mView);
    }

    protected void showLoadingDialog() {
        showDialog(BaseDialogFragment.newInstance(DialogUtils.newLoading(mContext), true));
    }

    protected void showDialog(DialogFragment dialogFragment) {
        if (!isShowDialog) {
            LogUtils.i(this.getClass().getSimpleName(), "显示dialog");
            isShowDialog = true;
            this.dialogFragment = dialogFragment;
            dialogFragment.show(getChildFragmentManager(), dialogFragment.getTag());
        }
    }

    protected void dismissDialog() {
        isShowDialog = false;
        if (dialogFragment != null) {
            LogUtils.i(this.getClass().getSimpleName(), "关闭dialog");
            dialogFragment.dismiss();
        }
    }

    protected void openUrl(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        showNextActivity(WebActivity.class, bundle);
    }

    protected String getUrl() {
        return mBundle.getString("url", "");
    }
}
