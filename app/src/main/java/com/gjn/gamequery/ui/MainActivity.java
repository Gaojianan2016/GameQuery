package com.gjn.gamequery.ui;

import android.view.View;
import android.widget.TextView;

import com.gjn.bottombarlibrary.BarTab;
import com.gjn.bottombarlibrary.BottomBarView;
import com.gjn.bottombarlibrary.IBarTab;
import com.gjn.bottombarlibrary.OnBindBarDateListener;
import com.gjn.gamequery.MyTinkerApplicationLike;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseGQActivity;
import com.gjn.gamequery.fragment.home.HomeFragment;
import com.gjn.gamequery.fragment.news.NewsFragment;
import com.gjn.gamequery.fragment.tool.ToolFragment;
import com.gjn.gamequery.fragment.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseGQActivity {

    public static final String BAR_HOME = "首页";
    public static final String BAR_NEWS = "项目";
    public static final String BAR_TOOL = "工具";
    public static final String BAR_USER = "我";

    @BindView(R.id.bbv_main)
    BottomBarView bbvMain;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        MyTinkerApplicationLike.setTinkerListener(new MyTinkerApplicationLike.TinkerListener() {
            @Override
            public void applyResult(boolean result) {
                if (result) {
                    showToast("app有热更新，可以重启app获取新功能!");
                }
            }
        });
    }

    @Override
    protected void initView() {

        List<IBarTab> list = new ArrayList<>();
        BarTab barTab;

        barTab = new BarTab();
        barTab.setTitle(BAR_HOME);
        barTab.setCls(HomeFragment.class);
        list.add(barTab);

        barTab = new BarTab();
        barTab.setTitle(BAR_NEWS);
        barTab.setCls(NewsFragment.class);
        list.add(barTab);

        barTab = new BarTab();
        barTab.setTitle(BAR_TOOL);
        barTab.setCls(ToolFragment.class);
        list.add(barTab);

        barTab = new BarTab();
        barTab.setTitle(BAR_USER);
        barTab.setCls(UserFragment.class);
        list.add(barTab);

        bbvMain.setOnBindBarDateListener(new OnBindBarDateListener() {
            @Override
            public void onBindBarView(View view, int i, IBarTab iBarTab) {
                TextView textView = view.findViewById(R.id.tv_bar_item);
                textView.setText(iBarTab.getTitle());

            }
        }).updataView(list);
    }

    @Override
    protected void initData() {

    }
}
