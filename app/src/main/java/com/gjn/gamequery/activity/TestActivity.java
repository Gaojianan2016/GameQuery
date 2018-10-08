package com.gjn.gamequery.activity;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseGQActivity;
import com.gjn.gamequery.activity.test.ITestView;
import com.gjn.mvpannotationlibrary.utils.BindPresenters;

/**
 * @author gjn
 * @time 2018/8/1 17:03
 */

public class TestActivity extends BaseGQActivity<TestPresenter> implements ITestView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        getPresenter().test();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void test() {
        showToast("测试");
    }
}
