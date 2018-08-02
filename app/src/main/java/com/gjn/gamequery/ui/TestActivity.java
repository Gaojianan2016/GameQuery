package com.gjn.gamequery.ui;

import android.view.View;
import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.annotation.BindPresenter;
import com.gjn.gamequery.annotation.BindPresenters;
import com.gjn.gamequery.mvp.BaseMvpActivity;

import butterknife.BindView;

/**
 * @author gjn
 * @time 2018/8/1 17:03
 */

@BindPresenters(presenters = {TestPresenter.class, TestPresenter2.class})
public class TestActivity extends BaseMvpActivity implements ITestView, ITest2View {
    @BindView(R.id.tv_testfm)
    TextView textView;

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

        testPresenter2.test();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPresenter.success();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void success() {
        showToast("成功点击？");
    }

    @Override
    public void test() {
        showToast("TEST进入？");
    }
}
