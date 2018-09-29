package com.gjn.gamequery.adapter;

import android.content.Context;
import android.view.View;

import com.gjn.gamequery.R;
import com.gjn.gamequery.utils.GlideUtils;
import com.gjn.universaladapterlibrary.BaseRecyclerAdapter;
import com.gjn.universaladapterlibrary.RecyclerViewHolder;

import java.util.List;

/**
 * @author gjn
 * @time 2018/8/15 16:38
 */

public class UserNameListAdapter extends BaseRecyclerAdapter<String> {

    public final static String ADD_QC = "add...QC";

    public UserNameListAdapter(Context ctx, List<String> list) {
        super(ctx, R.layout.item_bar_main, list);
    }

    @Override
    public void setData(List<String> list) {
        super.setData(list);
        add(ADD_QC);
    }

    public boolean chechAdd(int pos){
        if (getItem(pos).equals(ADD_QC)) {
            return true;
        }
        return false;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, String item, int position) {
        if (ADD_QC.equals(item)) {
            GlideUtils.loadImg(R.mipmap.addition, 100, holder.getImageView(R.id.iv_bar_item));
            holder.getView(R.id.tv_bar_item).setVisibility(View.GONE);
        } else {
            GlideUtils.loadImg(R.mipmap.default_img, 100, holder.getImageView(R.id.iv_bar_item));
            holder.setTextViewText(R.id.tv_bar_item, item);
            holder.getView(R.id.tv_bar_item).setVisibility(View.VISIBLE);
        }
    }
}
