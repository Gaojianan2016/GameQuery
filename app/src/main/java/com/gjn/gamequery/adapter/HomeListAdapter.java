package com.gjn.gamequery.adapter;

import android.content.Context;
import android.util.Log;

import com.gjn.gamequery.R;
import com.gjn.gamequery.base.BaseRecyclerAdapter;
import com.gjn.gamequery.base.RecyclerViewHolder;
import com.gjn.gamequery.net.data.WanHomeData;

import java.util.List;

/**
 * @author gjn
 * @time 2018/8/3 17:54
 */

public class HomeListAdapter extends BaseRecyclerAdapter<WanHomeData.DatasBean> {

    public HomeListAdapter(Context ctx, List<WanHomeData.DatasBean> list) {
        super(ctx, R.layout.adapter_home_list, list);
    }

    @Override
    public void bindData(RecyclerViewHolder holder, WanHomeData.DatasBean item, int position) {
        holder.setTextViewText(R.id.tv_title_ahl, item.getTitle());
        holder.setTextViewText(R.id.tv_author_ahl, item.getAuthor());
    }
}
