package com.gjn.gamequery.ui;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.annotation.AnnotationsUtils;
import com.gjn.gamequery.annotation.BindPresenter;
import com.gjn.gamequery.annotation.BindPresenters;
import com.gjn.gamequery.mvp.BaseMvpActivity;
import com.gjn.gamequery.mvp.BasePresenter;
import com.gjn.gamequery.mvp.IMvpPresenter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * @author gjn
 * @time 2018/8/1 17:03
 */

@BindPresenters(presenters = {TestPresenter.class, TestPresenter2.class})
public class TestActivity extends BaseMvpActivity<TestPresenter> implements ITestView, ITest2View{
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

//        try {
//            BindPresenters ps = AnnotationsUtils.getAnnotations(this, BindPresenters.class);
//            for (Class<?> aClass : ps.presenters()) {
//                String name = aClass.getCanonicalName();
//                Log.e("-s-", "name = " + name);
//                presentersMap.put(name, (BasePresenter) aClass.newInstance());
//            }
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//
//        List<Field> fields = AnnotationsUtils.getField(this, BindPresenter.class);
//
//        for (Field field : fields) {
//            String name = field.getType().getName();
//            Log.e("-s-", "---name = " + name);
//            BasePresenter bp = presentersMap.get(name);
//            if (bp != null) {
//                try {
//                    field.setAccessible(true);
//                    field.set(this, bp);
//                    bp.onAttached(this);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        if (testPresenter2 != null) {
            testPresenter2.test();
        }


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    testPresenter.success();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected TestPresenter getPresenter() {
        return new TestPresenter();
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
