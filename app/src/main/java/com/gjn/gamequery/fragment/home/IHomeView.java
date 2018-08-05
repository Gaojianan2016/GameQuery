package com.gjn.gamequery.fragment.home;

import com.gjn.gamequery.net.data.WanBannerData;
import com.gjn.gamequery.net.data.WanHomeData;
import com.gjn.mvpannotationlibrary.base.IMvpView;

import java.util.List;

/**
 * @author gjn
 * @time 2018/8/3 17:27
 */

public interface IHomeView extends IMvpView {
    void setList(List<WanHomeData.DatasBean> json);

    void fail();

    void setbanner(List<WanBannerData> data);
}
