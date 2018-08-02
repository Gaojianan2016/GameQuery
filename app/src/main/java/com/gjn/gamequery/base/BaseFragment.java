package com.gjn.gamequery.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjn.gamequery.utils.ToastUtils;
import com.gjn.gamequery.utils.ViewUtils;

import butterknife.ButterKnife;

/**
 * @author gjn
 * @time 2018/7/27 14:26
 */

public abstract class BaseFragment extends Fragment implements BaseEvent{

    protected Fragment mFragment;
    protected Context mContext;
    protected Activity mActivity;
    protected Bundle mBundle;
    protected View mView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        preCreate();
        super.onCreate(savedInstanceState);

        mFragment = this;
        mContext = getContext();
        mActivity = getActivity();
        mBundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(getLayoutId(), null);
            Log.e("-s-", "new view");
            init();
            initView();
            initData();
        }else {
            Log.e("-s-", "old view");
        }
        Log.e("-s-", "mView = " + mView);
        ViewUtils.checkParent(mView);
        return mView;
    }

    protected void preCreate() {

    }

    protected void init() {
        ButterKnife.bind(this, mView);
    }

    public final <T extends View> T findViewById(int id){
        return mView.findViewById(id);
    }

    @Override
    public void showNextActivity(Class<?> cls) {
        Intent intent = new Intent(mActivity, cls);
        startActivity(intent);
    }

    @Override
    public void showNextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(mActivity, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void toNextActivity(Class<?> cls) {
        showNextActivity(cls);
        mActivity.finish();
    }

    @Override
    public void toNextActivity(Class<?> cls, Bundle bundle) {
        showNextActivity(cls, bundle);
        mActivity.finish();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(mActivity, msg);
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();
}
