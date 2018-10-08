package com.gjn.gamequery.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.TextView;

import com.gjn.bottombarlibrary.BarTab;
import com.gjn.bottombarlibrary.BottomBarV4View;
import com.gjn.bottombarlibrary.IBarTab;
import com.gjn.bottombarlibrary.OnBindBarDateListener;
import com.gjn.gamequery.MyTinkerApplicationLike;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseGQActivity;
import com.gjn.gamequery.fragment.home.HomeFragment;
import com.gjn.gamequery.fragment.news.NewsFragment;
import com.gjn.gamequery.fragment.tool.ToolFragment;
import com.gjn.gamequery.fragment.user.UserFragment;
import com.gjn.gamequery.activity.login.LoginActivity;
import com.gjn.gamequery.utils.Constants;
import com.gjn.gamequery.utils.RxBus;
import com.gjn.statusbarlibrary.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseGQActivity implements View.OnClickListener {

    public static final String BAR_HOME = "首页";
    public static final String BAR_NEWS = "项目";
    public static final String BAR_TOOL = "工具";
    public static final String BAR_USER = "我";

    @BindView(R.id.bbv_main)
    BottomBarV4View bbvMain;
    @BindView(R.id.nv_main)
    NavigationView nvMain;
    @BindView(R.id.dl_main)
    DrawerLayout dlMain;
    private CircleImageView img;
    private TextView name;

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
    protected void setStatusbar() {
        StatusBarUtils.statusBarMode(mActivity, true);
        StatusBarUtils.setContentViewFitsSystemWindows(mActivity, true);
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

        setNavigationView();
    }

    private void setNavigationView() {
        View header = nvMain.getHeaderView(0);
        img = header.findViewById(R.id.civ_img_hm);
        name = header.findViewById(R.id.tv_name_hm);
        TextView history = header.findViewById(R.id.tv_history_hm);
        TextView collection = header.findViewById(R.id.tv_collection_hm);
        TextView setting = header.findViewById(R.id.tv_setting_hm);

        img.setOnClickListener(this);
        name.setOnClickListener(this);
        history.setOnClickListener(this);
        collection.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        RxBus.getStringMainThread(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (s.equals(Constants.RX_OPEN_MENU)) {
                    dlMain.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.civ_img_hm:
            case R.id.tv_name_hm:
                showToast("登录");
                showNextActivity(LoginActivity.class);
                break;
            case R.id.tv_history_hm:
                showToast("历史");
                break;
            case R.id.tv_collection_hm:
                showToast("收藏");
                break;
            case R.id.tv_setting_hm:
                showToast("设置");
                break;
            default:
                break;
        }
        dlMain.closeDrawers();
    }
}
