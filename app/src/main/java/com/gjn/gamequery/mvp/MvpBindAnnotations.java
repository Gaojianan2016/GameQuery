package com.gjn.gamequery.mvp;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

import com.gjn.gamequery.annotation.AnnotationsUtils;
import com.gjn.gamequery.annotation.BindPresenter;
import com.gjn.gamequery.annotation.BindPresenters;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gjn
 * @time 2018/8/1 18:25
 */

public class MvpBindAnnotations {
    private static final String TAG = "MvpBindAnnotations";
    public Map<String, BasePresenter> presentersMap;
    private Activity activity;
    private Fragment fragment;
    private Class<?> clazz;
    private Object object;

    private MvpBindAnnotations(Activity activity, Fragment fragment) {
        if (activity != null) {
            this.activity = activity;
            clazz = this.activity.getClass();
            object = this.activity;
            Log.d(TAG, "bind activity");
        }
        if (fragment != null) {
            this.fragment = fragment;
            clazz = this.fragment.getClass();
            object = this.fragment;
            Log.d(TAG, "bind fragment");
        }
        presentersMap = new HashMap<>();
        bindPs();
        bindP();
    }

    public static MvpBindAnnotations getInstance(Activity activity){
        return new MvpBindAnnotations(activity, null);
    }

    public static MvpBindAnnotations getInstance(Fragment fragment){
        return new MvpBindAnnotations(null, fragment);
    }

    private void bindPs() {
        Log.d(TAG, "bindPs start.");
        try {
//            BindPresenters ps = AnnotationsUtils.getAnnotations(clazz, BindPresenters.class);
            BindPresenters ps = AnnotationsUtils.getAnnotations(object, BindPresenters.class);
            if (ps != null) {
                for (Class<?> aClass : ps.presenters()) {
                    String name = aClass.getCanonicalName();
                    presentersMap.put(name, (BasePresenter) aClass.newInstance());
                }
            }else {
                Log.d(TAG, "ps is null.");
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void bindP() {
        Log.d(TAG, "bindP start.");
//        List<Field> fields = AnnotationsUtils.getField(clazz, BindPresenter.class);
        List<Field> fields = AnnotationsUtils.getField(object, BindPresenter.class);
        for (Field field : fields) {
            String name = field.getType().getName();
            BasePresenter bp = presentersMap.get(name);
            if (bp != null) {
                try {
                    field.setAccessible(true);
//                    field.set(clazz, bp);
                    field.set(object, bp);
                    bp.onAttached((IMvpView) (fragment != null ? fragment : activity));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }else {
                Log.d(TAG, "bp is null.");
            }
        }
    }

    public Map<String, BasePresenter> getPresentersMap() {
        return presentersMap;
    }
}
