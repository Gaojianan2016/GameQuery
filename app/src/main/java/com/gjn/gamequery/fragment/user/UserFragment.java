package com.gjn.gamequery.fragment.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gjn.gamequery.R;
import com.gjn.gamequery.adapter.UserListAdapter;
import com.gjn.gamequery.base.BaseGQFragment;
import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.net.data.JumpMatchData;
import com.gjn.gamequery.net.data.JumpRoleData;
import com.gjn.gamequery.sql.JumpDBModel;
import com.gjn.gamequery.sql.JumpDao;
import com.gjn.gamequery.utils.StringUtils;
import com.gjn.gamequery.utils.TimeUtils;
import com.gjn.mvpannotationlibrary.utils.BindPresenters;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */
@BindPresenters({UserPresenter.class})
public class UserFragment extends BaseGQFragment<UserPresenter> implements IUserView {
    @BindView(R.id.iv_img_fu)
    ImageView ivImgFu;
    @BindView(R.id.tv_name_fu)
    TextView tvNameFu;
    @BindView(R.id.rv_list_fu)
    RecyclerView rvListFu;
    @BindView(R.id.rv_users_fu)
    RecyclerView rvUsersFu;
    @BindView(R.id.tv_zj_fu)
    TextView tvZjFu;
    @BindView(R.id.tv_lv_fu)
    TextView tvLvFu;
    @BindView(R.id.tv_elo_fu)
    TextView tvEloFu;
    @BindView(R.id.tv_time_fu)
    TextView tvTimeFu;

    private UserListAdapter listAdapter;
    private long RoleId = -1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {

        rvListFu.setLayoutManager(new LinearLayoutManager(mActivity));
        listAdapter = new UserListAdapter(mActivity, null);
        rvListFu.setAdapter(listAdapter);
    }

    @Override
    protected void initData() {
        getPresenter().getUser("从零开始辣鸡哲");
        getPresenter().getList("从零开始辣鸡哲");

    }

    @Override
    public void setRole(JumpRoleData.RoleBean role) {
        RoleId = role.getRoleID();
        tvLvFu.setText("Lv."+ role.getRoleLevel());
        tvZjFu.setText(role.getWinCount()+ "/" + role.getMatchCount()
                + " ("+StringUtils.doubleFormat(role.getWinCount() * 100.0 / role.getMatchCount())+"%)");
        tvNameFu.setText(role.getRoleName());
        try {
            tvTimeFu.setText("更新时间："+ TimeUtils.getTime(role.getUpdateTime()));
        } catch (ParseException e) {}
    }

    @Override
    public void setList(List<JumpListData.ListBean> list) {
        if (list.size() > 0) {
            for (JumpListData.ListBean bean : list) {
                getPresenter().getMatch(bean.getMatchID(), true);
            }
        }
        listAdapter.setData(list);
    }

    @Override
    public void setMatch(JumpMatchData.MatchBean match) {
        if (RoleId != -1) {
            if (match.getWinSide() != null) {
                for (JumpMatchData.MatchBean.SideBean bean : match.getWinSide()) {
                    if (bean.getRoleID() == RoleId) {
                        String zj = bean.getKillCount()+"/"+bean.getDeathCount()+"/"+bean.getAssistCount();
                        listAdapter.setZj(match.getMatchID(), zj);
                    }
                }
            }
            if (match.getLoseSide() != null) {
                for (JumpMatchData.MatchBean.SideBean bean : match.getLoseSide()) {
                    if (bean.getRoleID() == RoleId) {
                        String zj = bean.getKillCount()+"/"+bean.getDeathCount()+"/"+bean.getAssistCount();
                        listAdapter.setZj(match.getMatchID(), zj);
                    }
                }
            }
        }
    }

    @Override
    public void fail(String s) {
        showToast(s);
    }

    @Override
    public void error(Throwable t) {
        showToast("网络异常");
        t.printStackTrace();
    }
}
