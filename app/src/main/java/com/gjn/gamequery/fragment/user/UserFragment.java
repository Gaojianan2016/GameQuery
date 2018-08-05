package com.gjn.gamequery.fragment.user;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gjn.gamequery.R;
import com.gjn.gamequery.adapter.UserListAdapter;
import com.gjn.gamequery.base.BaseGQFragment;
import com.gjn.gamequery.net.JumpUrl;
import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.net.data.JumpMatchData;
import com.gjn.gamequery.net.data.JumpRoleData;
import com.gjn.mvpannotationlibrary.utils.BindPresenters;

import java.util.List;

import butterknife.BindView;

/**
 * HomeFragment
 * Created by gjn
 * on 2018-07-28 19:56.
 */
@BindPresenters(presenters = {UserPresenter.class})
public class UserFragment extends BaseGQFragment<UserPresenter> implements IUserView {
    @BindView(R.id.iv_img_fu)
    ImageView iv_img_fu;
    @BindView(R.id.tv_name_fu)
    TextView tv_name_fu;
    @BindView(R.id.rv_list_fu)
    RecyclerView rv_list_fu;
    @BindView(R.id.rv_users_fu)
    RecyclerView rv_users_fu;

    private UserListAdapter listAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {

        rv_list_fu.setLayoutManager(new LinearLayoutManager(mContext));
        listAdapter = new UserListAdapter(mContext, null);
        rv_list_fu.setAdapter(listAdapter);
    }

    @Override
    protected void initData() {
        getPresenter().getUser("MD肉便祈");
        getPresenter().getList("MD肉便祈");
    }

    @Override
    public void setRole(JumpRoleData.RoleBean role) {
        Glide.with(mContext)
                .load(JumpUrl.IMGURL+"herohead/chara_0204.png")
                .into(iv_img_fu);

        tv_name_fu.setText(role.getRoleName());
    }

    @Override
    public void setList(List<JumpListData.ListBean> list) {
        listAdapter.setData(list);
    }

    @Override
    public void setMatch(JumpMatchData.MatchBean match) {

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
