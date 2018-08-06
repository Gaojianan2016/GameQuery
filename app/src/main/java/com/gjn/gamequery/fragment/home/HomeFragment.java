package com.gjn.gamequery.fragment.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gjn.bannerlibrary.Banner;
import com.gjn.bannerlibrary.LoopViewPager;
import com.gjn.gamequery.R;
import com.gjn.gamequery.adapter.HomeListAdapter;
import com.gjn.gamequery.base.BaseGQFragment;
import com.gjn.gamequery.base.BaseRecyclerAdapter;
import com.gjn.gamequery.net.data.WanBannerData;
import com.gjn.gamequery.net.data.WanHomeData;
import com.gjn.gamequery.utils.TimeUtils;
import com.gjn.indicatorlibrary.Indicator;
import com.gjn.mvpannotationlibrary.utils.BindPresenters;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */

@BindPresenters({HomePresenter.class})
public class HomeFragment extends BaseGQFragment<HomePresenter> implements IHomeView {

    @BindView(R.id.rv_list_fh)
    RecyclerView rvListFh;
    @BindView(R.id.banner_fh)
    Banner banner;

    private HomeListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        setBanner();

        rvListFh.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new HomeListAdapter(mContext, null);
        rvListFh.setAdapter(adapter);
    }

    private void setBanner() {
        List<WanBannerData> datas = new ArrayList<>();
        datas.add(new WanBannerData());
        banner.setImageLoader(new Banner.BannerImageLoader() {
            @Override
            public void imageLoader(Context context, Object o, ImageView imageView) {
                WanBannerData data = (WanBannerData) o;
                Glide.with(context).load(data.getImagePath()).into(imageView);
            }
        }).setOnItemClickListener(new LoopViewPager.onClickListener() {
            @Override
            public void onClick(View view, int i, Object o) {
                WanBannerData data = (WanBannerData) o;
                openUrl(data.getUrl());
            }
        }).updataView(datas);
    }

    @Override
    protected void initData() {
        getPresenter().list();
        getPresenter().banner();

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                openUrl(adapter.getItem(pos).getLink());
            }
        });
    }

    @Override
    public void setList(List<WanHomeData.DatasBean> json) {
        adapter.setData(json);
    }

    @Override
    public void setbanner(List<WanBannerData> datas) {
        List<String> titles = new ArrayList<>();
        for (WanBannerData data : datas) {
            titles.add(data.getTitle());
        }
        banner.updataView(datas, titles);
        banner.setType(Indicator.TYPE_TEXT);
    }

    @Override
    public void fail() {
        showToast("数据获取失败");
    }

    @Override
    public void error(Throwable t) {
        showToast("数据获取异常");
    }
}
