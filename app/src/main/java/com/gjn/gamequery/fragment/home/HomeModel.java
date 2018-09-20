package com.gjn.gamequery.fragment.home;

import com.gjn.gamequery.net.RetrofitManager;
import com.gjn.gamequery.net.WanandroidUrl;
import com.gjn.gamequery.net.response.WanBannerResponse;
import com.gjn.gamequery.net.response.WanHomeResponse;
import com.gjn.mvpannotationlibrary.base.BaseModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author gjn
 * @time 2018/8/3 16:11
 */

public class HomeModel extends BaseModel<IHomeView> {

    void getHomeList(int page){
        RetrofitManager.getInstance()
                .url(WanandroidUrl.BASE)
                .create(WanandroidUrl.class)
                .home(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WanHomeResponse>() {
                    @Override
                    public void accept(WanHomeResponse wanHomeResponse) throws Exception {
                        if (wanHomeResponse != null) {
                            getMvpView().setList(wanHomeResponse.getData().getDatas());
                        }else {
                            getMvpView().fail();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().error(throwable);
                    }
                });
    }

    void getBanner(){
        RetrofitManager.getInstance()
                .url(WanandroidUrl.BASE)
                .create(WanandroidUrl.class)
                .banner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WanBannerResponse>() {
                    @Override
                    public void accept(WanBannerResponse wanBannerResponse) throws Exception {
                        if (wanBannerResponse != null) {
                            getMvpView().setbanner(wanBannerResponse.getData());
                        }else {
                            getMvpView().fail();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().error(throwable);
                    }
                });
    }
}
