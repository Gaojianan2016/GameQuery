package com.gjn.gamequery.fragment.user;

import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.net.data.JumpMatchData;
import com.gjn.gamequery.net.data.JumpRoleData;
import com.gjn.mvpannotationlibrary.base.IMvpView;

import java.util.List;

/**
 * IUserView
 * Created by gjn
 * on 2018-08-05 23:40.
 */
public interface IUserView extends IMvpView {
    void setRole(JumpRoleData.RoleBean role);

    void fail(String s);

    void setList(List<JumpListData.ListBean> list);

    void setMatch(JumpMatchData.MatchBean match);
}
