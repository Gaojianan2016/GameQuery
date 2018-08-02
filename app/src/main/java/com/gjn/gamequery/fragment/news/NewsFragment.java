package com.gjn.gamequery.fragment.news;

import com.gjn.gamequery.R;
import com.gjn.gamequery.annotation.BindPresenter;
import com.gjn.gamequery.annotation.BindPresenters;
import com.gjn.gamequery.mvp.BaseMvpFragment;
import com.gjn.gamequery.ui.ITest2View;
import com.gjn.gamequery.ui.ITestView;
import com.gjn.gamequery.ui.TestPresenter;
import com.gjn.gamequery.ui.TestPresenter2;

import butterknife.OnClick;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */
@BindPresenters(presenters = {TestPresenter.class, TestPresenter2.class})
public class NewsFragment extends BaseMvpFragment implements ITestView, ITest2View {

    @BindPresenter
    TestPresenter testPresenter;

    @BindPresenter
    TestPresenter2 testPresenter2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        testPresenter2.test();
    }


    @OnClick(R.id.tv_testfm)
    public void click() {
        showToast("我被点击....");
        testPresenter.success();
    }

    @Override
    public void success() {
        showToast("success 出现了");
    }

    @Override
    public void test() {
        showToast("sssss");
    }
}
