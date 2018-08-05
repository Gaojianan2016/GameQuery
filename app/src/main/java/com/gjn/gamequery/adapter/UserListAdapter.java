package com.gjn.gamequery.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseRecyclerAdapter;
import com.gjn.gamequery.base.RecyclerViewHolder;
import com.gjn.gamequery.net.JumpUrl;
import com.gjn.gamequery.net.data.JumpListData;

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
        }else if (item.getResult() == 2) {
            holder.setTextViewText(R.id.tv_result_aul, "失败");
        }else {
            holder.setTextViewText(R.id.tv_result_aul, "逃跑");
        }
        Glide.with(mContext).load(JumpUrl.IMGURL + item.getHero().getIconFile()).into(holder.getImageView(R.id.iv_img_aul));
    }
}
