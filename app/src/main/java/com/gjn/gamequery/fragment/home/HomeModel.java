package com.gjn.gamequery.fragment.home;

import com.gjn.gamequery.net.RetrofitManager;
import com.gjn.gamequery.net.WanAndroidManager;
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
        getMvpView().showProgressUI(true);
        WanAndroidManager.home(page, new RetrofitManager.OnLinkListener<WanHomeResponse>() {
            @Override
            public void success(WanHomeResponse response) throws Exception {
                getMvpView().showProgressUI(false);
                if (response != null) {
                    getMvpView().setList(response.getData().getDatas(), response.getData().getPageCount());
                }else {
                    getMvpView().fail();
                }
            }

            @Override
            public void fail(Throwable throwable) {
                getMvpView().showProgressUI(false);
                getMvpView().error(throwable);
            }
        });
    }

    void getBanner(){
        WanAndroidManager.banner(new RetrofitManager.OnLinkListener<WanBannerResponse>() {
            @Override
            public void success(WanBannerResponse response) throws Exception {
                if (response != null) {
                    getMvpView().setbanner(response.getData());
                }else {
                    getMvpView().fail();
                }
            }

            @Override
            public void fail(Throwable throwable) {
                getMvpView().error(throwable);
            }
        });
    }
}
