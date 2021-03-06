package com.gjn.gamequery.fragment.user;

import android.util.Log;

import com.gjn.gamequery.fragment.home.IHomeView;
import com.gjn.gamequery.net.JumpUrl;
import com.gjn.gamequery.net.RetrofitManager;
import com.gjn.gamequery.net.WanandroidUrl;
import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.net.data.JumpMatchData;
import com.gjn.gamequery.net.data.JumpRoleData;
import com.gjn.gamequery.net.response.WanBannerResponse;
import com.gjn.gamequery.net.response.WanHomeResponse;
import com.gjn.gamequery.sql.JumpDao;
import com.gjn.gamequery.utils.GsonUtils;
import com.gjn.mvpannotationlibrary.base.BaseModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author gjn
 * @time 2018/8/3 16:11
 */

public class UserModel extends BaseModel<IUserView> {

    void getRole(String name){
        RetrofitManager.getInstance()
                .url(JumpUrl.BASE)
                .create(JumpUrl.class)
                .getrole(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JumpRoleData>() {
                    @Override
                    public void accept(JumpRoleData jumpRoleData) throws Exception {
                        if (jumpRoleData != null) {
                            if ("OK".equals(jumpRoleData.getResult())) {
                                if (jumpRoleData.getRole() != null) {
                                    getMvpView().setRole(jumpRoleData.getRole());
                                }
                            }else {
                                getMvpView().fail("获取用户失败");
                            }
                        }else {
                            getMvpView().fail("获取用户失败");
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().error(throwable);
                    }
                });
    }

    void getList(String name, int page){
        RetrofitManager.getInstance()
                .url(JumpUrl.BASE)
                .create(JumpUrl.class)
                .getlist(name, page * 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JumpListData>() {
                    @Override
                    public void accept(JumpListData jumpListData) throws Exception {
                        if (jumpListData != null) {
                            if ("OK".equals(jumpListData.getResult())) {
                                getMvpView().setList(jumpListData.getList());
                            }else {
                                getMvpView().fail("获取用户失败");
                            }
                        }else {
                            getMvpView().fail("获取用户失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().error(throwable);
                    }
                });
    }

    void getMatch(final long id, boolean isCheck){
        final JumpDao dao = new JumpDao(getActivity());
        if (dao.hasMatchId(id) && isCheck) {
            //显示结果
            getMvpView().setMatch(GsonUtils.json2obj(dao.queryString(id), JumpMatchData.MatchBean.class));
            return;
        }
        RetrofitManager.getInstance()
                .url(JumpUrl.BASE)
                .create(JumpUrl.class)
                .getmatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JumpMatchData>() {
                    @Override
                    public void accept(JumpMatchData jumpMatchData) throws Exception {
                        if (jumpMatchData != null) {
                            if ("OK".equals(jumpMatchData.getResult())) {
                                if (jumpMatchData.getMatch() != null) {
                                    //设置战绩id
                                    JumpMatchData.MatchBean matchBean = jumpMatchData.getMatch();
                                    matchBean.setMatchID(id);
                                    //保存战绩
                                    dao.add(id, GsonUtils.obj2json(matchBean));
                                    //显示结果
                                    getMvpView().setMatch(matchBean);
                                }
                            }else {
                                getMvpView().fail("获取战绩失败");
                            }
                        }else {
                            getMvpView().fail("获取战绩失败");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getMvpView().error(throwable);
                    }
                });
    }
}
