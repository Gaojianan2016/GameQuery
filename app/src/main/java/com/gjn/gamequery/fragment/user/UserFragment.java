package com.gjn.gamequery.fragment.user;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.adapter.UserMatchListAdapter;
import com.gjn.gamequery.adapter.UserNameListAdapter;
import com.gjn.gamequery.base.BaseGQFragment;
import com.gjn.gamequery.fragment.user.jumpMatch.JumpMatchActivity;
import com.gjn.gamequery.net.JumpUrl;
import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.net.data.JumpMatchData;
import com.gjn.gamequery.net.data.JumpRoleData;
import com.gjn.gamequery.utils.Constants;
import com.gjn.gamequery.utils.GlideUtils;
import com.gjn.gamequery.utils.GsonUtils;
import com.gjn.gamequery.utils.SharedPreferencesUtil;
import com.gjn.gamequery.utils.StringUtils;
import com.gjn.gamequery.utils.TimeUtils;
import com.gjn.mvpannotationlibrary.utils.BindPresenters;
import com.gjn.popupviewlibrary.PopupWindowUtils;
import com.gjn.toolbarlibrary.TitleBar;
import com.gjn.universaladapterlibrary.BaseRecyclerAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */
@BindPresenters({UserPresenter.class})
public class UserFragment extends BaseGQFragment<UserPresenter> implements IUserView {
    @BindView(R.id.tb_fu)
    TitleBar tbFu;
    @BindView(R.id.fl_fail_fu)
    FrameLayout flFailFu;
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

    private UserMatchListAdapter listAdapter;
    private UserNameListAdapter nameListAdapter;
    private long roleId = -1;
    private String roleName = "";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        tbFu.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataJump();
            }
        });

        rvListFu.setLayoutManager(new LinearLayoutManager(mActivity));
        listAdapter = new UserMatchListAdapter(mActivity, null);
        rvListFu.setAdapter(listAdapter);

        rvUsersFu.setLayoutManager(new GridLayoutManager(mActivity, 4));
        nameListAdapter = new UserNameListAdapter(mActivity, null);
        rvUsersFu.setAdapter(nameListAdapter);
    }

    @Override
    protected void initData() {
        updataJump();
        updataNames();
        String defaultJson = SharedPreferencesUtil.getString(Constants.SP_JUMP_DEFAULT);
        if (!defaultJson.isEmpty()) {
            setRole(GsonUtils.json2obj(defaultJson, JumpRoleData.RoleBean.class));
        }

        nameListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                if (nameListAdapter.chechAdd(pos)) {
                    showAdd();
                }else {
                    getPresenter().getUser(nameListAdapter.getItem(pos));
                }
            }
        });

        listAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                Bundle bundle = new Bundle();
                bundle.putLong("matchid", listAdapter.getItem(pos).getMatchID());
                bundle.putLong("roleid", roleId);
                showNextActivity(JumpMatchActivity.class, bundle);
            }
        });
    }

    private void showAdd() {
        new PopupWindowUtils(mActivity, R.layout.popup_add_user,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            public void bindView(Activity activity, View view) {
                setIdOnClickListener(R.id.btn_pau, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText et = findViewById(R.id.et_name_pau);
                        String name = et.getText().toString();
                        if (!name.isEmpty()) {
                            String names = SharedPreferencesUtil.getString(Constants.SP_JUMP_NAMES);
                            if (!names.contains(name)) {
                                names += ","+name;
                            }
                            SharedPreferencesUtil.setString(Constants.SP_JUMP_NAMES, names);
                            getPresenter().getUser(name);
                        }
                        dismiss();
                    }
                });
            }
        }.show();
    }

    private void updataNames() {
        String names = SharedPreferencesUtil.getString(Constants.SP_JUMP_NAMES);
        List<String> ns = new ArrayList<>();
        if (!names.isEmpty()) {
            String[] s = names.split(",");
            for (String name : s) {
                if (!name.isEmpty()) {
                    ns.add(name);
                }
            }
        }
        nameListAdapter.setData(ns);
    }

    private void updataJump() {
        roleName = SharedPreferencesUtil.getString(Constants.SP_JUMP_NAME);
        if (!roleName.isEmpty()) {
            getPresenter().getUser(roleName);
        }
    }

    @Override
    public void setRole(JumpRoleData.RoleBean role) {
        //保存数据
        SharedPreferencesUtil.setString(Constants.SP_JUMP_DEFAULT, GsonUtils.obj2json(role));
        SharedPreferencesUtil.setString(Constants.SP_JUMP_NAME, role.getRoleName());
        //更新数据
        roleName = SharedPreferencesUtil.getString(Constants.SP_JUMP_NAME);
        updataNames();

        roleId = role.getRoleID();
        tvLvFu.setText("Lv." + role.getRoleLevel());
        tvZjFu.setText(role.getWinCount() + "/" + role.getMatchCount()
                + " (" + StringUtils.doubleFormat(role.getWinCount() * 100.0 / role.getMatchCount()) + "%)");
        tvNameFu.setText(role.getRoleName());
        try {
            tvTimeFu.setText("更新时间：" + TimeUtils.getTime(role.getUpdateTime()));
        } catch (ParseException e) {
        }
        getPresenter().getList(roleName);
        flFailFu.setVisibility(View.GONE);
    }

    @Override
    public void setList(List<JumpListData.ListBean> list) {
        if (list.size() > 0) {
            for (JumpListData.ListBean bean : list) {
                getPresenter().getMatch(bean.getMatchID());
            }
        }
        GlideUtils.loadImg(JumpUrl.IMGURL + list.get(0).getHero().getIconFile(), ivImgFu);
        listAdapter.setData(list);
    }

    @Override
    public void setMatch(JumpMatchData.MatchBean match) {
        if (roleId != -1) {
            if (match.getWinSide() != null) {
                for (JumpMatchData.MatchBean.SideBean bean : match.getWinSide()) {
                    if (bean.getRoleID() == roleId) {
                        String zj = bean.getKillCount() + "/" + bean.getDeathCount() + "/" + bean.getAssistCount();
                        listAdapter.setZj(match.getMatchID(), zj);
                    }
                }
            }
            if (match.getLoseSide() != null) {
                for (JumpMatchData.MatchBean.SideBean bean : match.getLoseSide()) {
                    if (bean.getRoleID() == roleId) {
                        String zj = bean.getKillCount() + "/" + bean.getDeathCount() + "/" + bean.getAssistCount();
                        listAdapter.setZj(match.getMatchID(), zj);
                    }
                }
            }
        }
    }

    @Override
    public void fail(String s) {
        flFailFu.setVisibility(View.VISIBLE);
        showToast(s);
    }

    @Override
    public void error(Throwable t) {
        flFailFu.setVisibility(View.VISIBLE);
        showToast("网络异常");
        t.printStackTrace();
    }
}
