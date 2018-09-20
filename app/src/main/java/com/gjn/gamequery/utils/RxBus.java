package com.gjn.gamequery.utils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * RxBus
 * Author: gjn.
 * Time: 2017/7/14.
 */

public class RxBus {

    private static RxBus mRxBus;
    private final Subject<Object> mBus;

    private RxBus(){
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (mRxBus == null){
            synchronized (RxBus.class){
                if (mRxBus == null){
                    mRxBus = new RxBus();
                }
            }
        }
        return mRxBus;
    }

    public void post(Object o){
        mBus.onNext(o);
    }

    public <T> Observable<T> toObservable (Class<T> eventType){
        return mBus.ofType(eventType);
    }

    public static <T> void getMainThread(Class<T> eventType,
                                         Consumer<? super T> onNext){
        getInstance().toObservable(eventType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
    }

    public static <T> void getMainThread(Class<T> eventType,
                                         Consumer<? super T> onNext,
                                         Consumer<? super Throwable> onError){
        getInstance().toObservable(eventType)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext,onError);
    }

    public static void getStringMainThread(Consumer<String> onNext,
                                         Consumer<? super Throwable> onError){
        getInstance().toObservable(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext,onError);
    }

    public static void getStringMainThread(Consumer<String> onNext){
        getInstance().toObservable(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
    }

    public static void postString(String str){
        getInstance().post(str);
    }

}
