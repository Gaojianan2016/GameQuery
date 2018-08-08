package com.gjn.gamequery.adapter;

import android.content.Context;
import android.graphics.Color;

import com.bumptech.glide.Glide;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseRecyclerAdapter;
import com.gjn.gamequery.base.RecyclerViewHolder;
import com.gjn.gamequery.net.JumpUrl;
import com.gjn.gamequery.net.data.JumpListData;
import com.gjn.gamequery.utils.TimeUtils;

import java.text.ParseException;
import java.util.List;

/**
 * UserListAdapter
 * Created by gjn
 * on 2018-08-06 0:08.
 */
public class UserListAdapter extends BaseRecyclerAdapter<JumpListData.ListBean> {

    public UserListAdapter(Context ctx, List<JumpListData.ListBean> list) {
        super(ctx, R.layout.adapter_user_list, list);
    }

    @Override
    public void bindData(RecyclerViewHolder holder, JumpListData.ListBean item, int position) {
        holder.setTextViewText(R.id.tv_hero_aul, item.getHero().getName());
        if (item.getResult() == 1) {
            holder.setTextViewText(R.id.tv_result_aul, "胜利");
            holder.getTextView(R.id.tv_result_aul).setTextColor(Color.GREEN);
        }else if (item.getResult() == 2) {
            holder.setTextViewText(R.id.tv_result_aul, "失败");
            holder.getTextView(R.id.tv_result_aul).setTextColor(Color.RED);
        }else {
            holder.setTextViewText(R.id.tv_result_aul, "逃跑");
            holder.getTextView(R.id.tv_result_aul).setTextColor(Color.YELLOW);
        }
        Glide.with(mContext).load(JumpUrl.IMGURL + item.getHero().getIconFile())
                .into(holder.getImageView(R.id.iv_img_aul));

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
