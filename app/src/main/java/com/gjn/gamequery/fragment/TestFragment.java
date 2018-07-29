package com.gjn.gamequery.fragment;

import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseFragment;

/**
 * TestFragment
 * Created by gjn
 * on 2018-07-28 3:09.
 */
public class TestFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
        TextView textView = mView.findViewById(R.id.tv_testfm);
        textView.setText(mBundle.getString("title","111"));
    }

    @Override
    protected void initData() {

    }
}
