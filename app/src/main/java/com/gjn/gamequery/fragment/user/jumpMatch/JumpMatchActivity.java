package com.gjn.gamequery.fragment.user.jumpMatch;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gjn.gamequery.R;
import com.gjn.gamequery.adapter.MatchAdapter;
import com.gjn.gamequery.base.BaseGQActivity;
import com.gjn.gamequery.fragment.user.IUserView;
import com.gjn.gamequery.fragment.user.UserPresenter;
import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.net.data.JumpMatchData;
import com.gjn.gamequery.net.data.JumpRoleData;
import com.gjn.gamequery.utils.TimeUtils;
import com.gjn.mvpannotationlibrary.utils.BindPresenters;
import com.gjn.toolbarlibrary.TitleBar;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author gjn
 * @time 2018/8/17 10:23
 */
@BindPresenters({UserPresenter.class})
public class JumpMatchActivity extends BaseGQActivity<UserPresenter> implements IUserView {
    @BindView(R.id.tb_amj)
    TitleBar tbAmj;
    @BindView(R.id.tv_overtime_amj)
    TextView tvOvertimeAmj;
    @BindView(R.id.tv_type_amj)
    TextView tvTypeAmj;
    @BindView(R.id.tv_time_amj)
    TextView tvTimeAmj;
    @BindView(R.id.iv_f_amj)
    ImageView ivFAmj;
    @BindView(R.id.rv_f_amj)
    RecyclerView rvFAmj;
    @BindView(R.id.iv_s_amj)
    ImageView ivSAmj;
    @BindView(R.id.rv_s_amj)
    RecyclerView rvSAmj;
    private long matchId;
    private long roleId;
    private boolean isResult;

    private MatchAdapter FAdapter;
    private MatchAdapter SAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_match_jump;
    }

    @Override
    protected void initView() {
        tbAmj.setLeftOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tbAmj.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataMatch(false);
            }
        });

        rvFAmj.setLayoutManager(new LinearLayoutManager(mActivity));
        FAdapter = new MatchAdapter(mActivity, null);
        rvFAmj.setAdapter(FAdapter);

        rvSAmj.setLayoutManager(new LinearLayoutManager(mActivity));
        SAdapter = new MatchAdapter(mActivity, null);
        rvSAmj.setAdapter(SAdapter);
    }

    @Override
    protected void initData() {
        matchId = mBundle.getLong("matchid", -1);
        roleId = mBundle.getLong("roleid", -1);

        updataMatch(true);
    }

    private void updataMatch(boolean b) {
        if (matchId != -1){
            getPresenter().getMatch(matchId, b);
        }
    }

    @Override
    public void setRole(JumpRoleData.RoleBean role) {}

    @Override
    public void fail(String s) {
        showToast(s);
    }

    @Override
    public void setList(List<JumpListData.ListBean> list) {}

    @Override
    public void setMatch(JumpMatchData.MatchBean match) {
        if (match.getMatchType() == 1) {
            tvTypeAmj.setText("竞技场");
        }else {
            tvTypeAmj.setText("战场");
        }
        try {
            tvOvertimeAmj.setText(TimeUtils.getTime(match.getMatchDate(), "HH:mm:ss"));
        } catch (ParseException e) {}
        tvTypeAmj.setText(TimeUtils.sec2minsec(match.getUsedTime()));

        List<JumpMatchData.MatchBean.SideBean> wins = new ArrayList<>();
        List<JumpMatchData.MatchBean.SideBean> loses = new ArrayList<>();
        for (JumpMatchData.MatchBean.SideBean bean : match.getWinSide()) {
            if (bean.getRoleID() == roleId) {
                isResult = true;
            }
            wins.add(bean);
        }
        for (JumpMatchData.MatchBean.SideBean bean : match.getLoseSide()) {
            if (bean.getRoleID() == roleId) {
                isResult = false;
            }
            loses.add(bean);
        }
        if (isResult) {
            ivFAmj.setImageResource(R.mipmap.win_logo);
            ivSAmj.setImageResource(R.mipmap.lose_logo);
            FAdapter.setData(wins);
            SAdapter.setData(loses);
        }else {
            ivFAmj.setImageResource(R.mipmap.lose_logo);
            ivSAmj.setImageResource(R.mipmap.win_logo);
            FAdapter.setData(loses);
            SAdapter.setData(wins);
        }
    }
}
