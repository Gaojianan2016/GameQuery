package com.gjn.gamequery.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseRecyclerAdapter;
import com.gjn.gamequery.base.RecyclerViewHolder;
import com.gjn.gamequery.net.JumpUrl;
import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.utils.TimeUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * UserListAdapter
 * Created by gjn
 * on 2018-08-06 0:08.
 */
public class UserListAdapter extends BaseRecyclerAdapter<JumpListData.ListBean> {

    private Map<Long, String> map;

    public UserListAdapter(Context ctx, List<JumpListData.ListBean> list) {
        super(ctx, R.layout.adapter_user_list, list);
        map = new HashMap<>();
    }

    public void setZj(long id, String zj){
        map.put(id, zj);
        notifyDataSetChanged();
    }

    @Override
    public void bindData(RecyclerViewHolder holder, JumpListData.ListBean item, int position) {
        holder.setTextViewText(R.id.tv_hero_aul, item.getHero().getName());
        int result;
        if (item.getResult() == 1) {
            result = R.mipmap.win;
        }else if (item.getResult() == 2) {
            result = R.mipmap.lose;
        }else {
            result = R.mipmap.run;
        }
        Glide.with(mContext).load(result).into(holder.getImageView(R.id.iv_result_aul));
        Glide.with(mContext).load(JumpUrl.IMGURL + item.getHero().getIconFile())
                .into(holder.getImageView(R.id.iv_img_aul));

        if (map.containsKey(item.getMatchID())) {
            holder.setTextViewText(R.id.tv_zj_aul, map.get(item.getMatchID()));
        }else {
            holder.setTextViewText(R.id.tv_zj_aul, "");
        }

        if (item.getMatchType() == 1) {
            holder.setTextViewText(R.id.tv_lx_aul, "竞技场");
        }else {
            holder.setTextViewText(R.id.tv_lx_aul, "战场");
        }
        try {
            holder.setTextViewText(R.id.tv_time_aul, TimeUtils.getTime(item.getMatchDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
