package com.gjn.gamequery.fragment.home;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.gjn.bannerlibrary.Banner;
import com.gjn.bannerlibrary.LoopViewPager;
import com.gjn.gamequery.R;
import com.gjn.gamequery.adapter.HomeListAdapter;
import com.gjn.gamequery.base.BaseGQFragment;
import com.gjn.gamequery.net.data.WanBannerData;
import com.gjn.gamequery.net.data.WanHomeData;
import com.gjn.gamequery.utils.Constants;
import com.gjn.gamequery.utils.GlideUtils;
import com.gjn.gamequery.utils.RxBus;
import com.gjn.indicatorlibrary.Indicator;
import com.gjn.mvpannotationlibrary.utils.BindPresenters;
import com.gjn.toolbarlibrary.TitleBar;
import com.gjn.universaladapterlibrary.BaseRecyclerAdapter;

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
    @BindView(R.id.tb_fh)
    TitleBar toolbar;

    int page = 0;
    private boolean isLoading = false;

    private HomeListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        setToolbar();
        setBanner();

        rvListFh.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new HomeListAdapter(mActivity, null);
        rvListFh.setAdapter(adapter);
    }

    private void setToolbar() {
        toolbar.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.postString(Constants.RX_OPEN_MENU);
            }
        });

        toolbar.setLeftText("未登录!");
    }

    private void setBanner() {
        List<WanBannerData> datas = new ArrayList<>();
        datas.add(new WanBannerData());

        banner.getIndicatorLinearLayout().setBackgroundResource(R.color.banner_indicator_bg);
        banner.setIndicatorLoader(Banner.defaultTextIndicator(0, Color.WHITE))
                .setImageLoader(new Banner.BannerImageLoader() {
                    @Override
                    public void imageLoader(Context context, Object o, ImageView imageView) {
                        WanBannerData data = (WanBannerData) o;
                        GlideUtils.loadImg(data.getImagePath(), imageView);
                    }
                })
                .setOnItemClickListener(new LoopViewPager.onClickListener() {
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

        rvListFh.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int last = manager.findLastVisibleItemPosition();
                int count = recyclerView.getAdapter().getItemCount();
                int visi = recyclerView.getChildCount();
                if ( count > visi && visi > 0 && last >= count - 1) {
                    if (!isLoading) {
                        page++;
                        getPresenter().list(page);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > getResources().getDisplayMetrics().heightPixels * 1.5) {

                }else {

                }
            }
        });
    }

    @Override
    public void setList(List<WanHomeData.DatasBean> json, int pageCount) {
        adapter.add(json);
    }

    @Override
    public void setbanner(List<WanBannerData> datas) {
        List<String> titles = new ArrayList<>();
        for (WanBannerData data : datas) {
            titles.add(data.getTitle());
        }
        banner.updataView(datas, titles);
        banner.changeIndicatorType(Indicator.TYPE_TEXT);
    }

    @Override
    public void showProgressUI(boolean isShow) {
        isLoading = isShow;
        adapter.setShowEnd(!isShow);
        if (isShow) {
            showLoadingDialog();
        }else {
            dismissDialog();
        }
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
