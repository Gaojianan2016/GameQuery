package com.gjn.gamequery.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.gjn.gamequery.R;
import com.gjn.gamequery.net.data.WanHomeData;
import com.gjn.gamequery.utils.TimeUtils;
import com.gjn.universaladapterlibrary.BaseRecyclerAdapter;
import com.gjn.universaladapterlibrary.RecyclerViewHolder;

import java.util.List;

/**
 * @author gjn
 * @time 2018/8/3 17:54
 */

public class HomeListAdapter extends BaseRecyclerAdapter<WanHomeData.DatasBean> {

    private boolean isShowEnd = true;

    public HomeListAdapter(Context ctx, List<WanHomeData.DatasBean> list) {
        super(ctx, R.layout.adapter_home_list, list);
    }

    public void setShowEnd(boolean showEnd) {
        isShowEnd = showEnd;
        notifyDataSetChanged();
    }

    @Override
    public void bindData(RecyclerViewHolder holder, WanHomeData.DatasBean item, int position) {
        holder.setTextViewText(R.id.tv_title_ahl, item.getTitle());
        holder.setTextViewText(R.id.tv_author_ahl, item.getAuthor());
        holder.setTextViewText(R.id.tv_desc_ahl, item.getDesc());
        holder.setTextViewText(R.id.tv_time_ahl, TimeUtils.overTime(item.getPublishTime()));

        View bar = holder.getView(R.id.pb_ahl);
        if (position == getItemCount() - 1 && isShowEnd) {
            bar.setVisibility(View.VISIBLE);
        }else {
            bar.setVisibility(View.GONE);
        }
    }
}
