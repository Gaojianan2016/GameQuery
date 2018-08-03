package com.gjn.gamequery.fragment.home;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.gjn.gamequery.R;
import com.gjn.gamequery.adapter.HomeListAdapter;
import com.gjn.gamequery.base.BaseGQFragment;
import com.gjn.gamequery.base.BaseRecyclerAdapter;
import com.gjn.gamequery.net.data.WanHomeData;
import com.gjn.mvpannotationlibrary.utils.BindPresenter;
import com.gjn.mvpannotationlibrary.utils.BindPresenters;

import java.util.List;

import butterknife.BindView;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */

@BindPresenters(presenters = {HomePresenter.class})
public class HomeFragment extends BaseGQFragment implements IHomeView {

    @BindView(R.id.rv_list_fh)
    RecyclerView rvListFh;
    @BindPresenter
    private HomePresenter presenter;

    private HomeListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        rvListFh.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new HomeListAdapter(mContext, null);
        rvListFh.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.list();

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
    public void fail() {
        adapter.clear();
        showToast("数据获取失败");
    }

    @Override
    public void error(Throwable t) {
        showToast("数据获取异常");
    }
}
