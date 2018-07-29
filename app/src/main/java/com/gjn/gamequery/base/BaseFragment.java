package com.gjn.gamequery.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjn.gamequery.utils.ViewUtils;

/**
 * @author gjn
 * @time 2018/7/27 14:26
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected Activity mActivity;
    protected Bundle mBundle;
    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        preCreate();
        super.onCreate(savedInstanceState);

        mContext = getContext();
        mActivity = getActivity();
        mBundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), null);

            init();
            initView();
            initData();
        }
        ViewUtils.checkParent(mView);
        return mView;
    }

    protected void preCreate() {

    }

    protected void init() {

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();
}
