package com.gjn.gamequery.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gjn.gamequery.R;

/**
 * @author gjn
 * @time 2018/9/29 10:37
 */

public class DialogUtils {

    public static AlertDialog.Builder newLoading(Context context, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView tvMsg = view.findViewById(R.id.tv_dialog_loading);
        if (!msg.isEmpty()) {
            tvMsg.setText(msg);
        }
        builder.setView(view);
        return builder;
    }

    public static AlertDialog.Builder newLoading(Context context){
        return newLoading(context, "");
    }
}
