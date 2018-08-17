package com.gjn.gamequery.adapter;

import android.content.Context;
import android.util.Log;

import com.gjn.gamequery.BuildConfig;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseRecyclerAdapter;
import com.gjn.gamequery.base.RecyclerViewHolder;
import com.gjn.gamequery.net.JumpUrl;
import com.gjn.gamequery.net.data.JumpMatchData;
import com.gjn.gamequery.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gjn
 * @time 2018/8/17 16:10
 */

public class MatchAdapter extends BaseRecyclerAdapter<JumpMatchData.MatchBean.SideBean> {

    public MatchAdapter(Context ctx, List<JumpMatchData.MatchBean.SideBean> list) {
        super(ctx, R.layout.adapter_match, list);
    }

    @Override
    public void bindData(RecyclerViewHolder holder, JumpMatchData.MatchBean.SideBean item, int position) {
        holder.setTextViewText(R.id.tv_name_am, item.getRoleName());
        holder.setTextViewText(R.id.tv_lv_am, String.valueOf(item.getRoleLevel()));
        holder.setTextViewText(R.id.tv_hero_lv_am, "Lv."+item.getHeroLevel());
        holder.setTextViewText(R.id.tv_zj_am, item.getKillCount() + "/" + item.getDeathCount() + "/" + item.getAssistCount());

        GlideUtils.loadImg(JumpUrl.IMGURL + item.getHero().getIconFile(), holder.getImageView(R.id.iv_img_am));

        GlideUtils.loadImg(JumpUrl.IMGURL + item.getSkill().get(0).getIconFile(), holder.getImageView(R.id.iv_d_am));
        GlideUtils.loadImg(JumpUrl.IMGURL + item.getSkill().get(1).getIconFile(), holder.getImageView(R.id.iv_f_am));

        List<String> equips = new ArrayList<>(6);
        for (JumpMatchData.MatchBean.SideBean.EquipBean bean : item.getEquip()) {
            if (bean.getID() != 0) {
                equips.add(JumpUrl.IMGURL + bean.getIconFile());
            }
        }

        for (int i = 0; i < equips.size(); i++) {
            int id = mActivity.getResources().getIdentifier("iv_"+(i+1)+"_am","id", mActivity.getPackageName());
            GlideUtils.loadImg(equips.get(i), holder.getImageView(id));
        }
    }
}
