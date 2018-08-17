package com.gjn.gamequery.utils;

import android.content.Context;
import android.widget.ImageView;

import com.gjn.gamequery.GlideApp;
import com.gjn.gamequery.GlideRequests;

/**
 * @author gjn
 * @time 2018/8/16 10:16
 */

public class GlideUtils {
    private static GlideRequests requests;

    public static GlideRequests getGlide() {
        return requests;
    }

    public static void setRequests(Context context) {
        requests = GlideApp.with(context.getApplicationContext());
    }

    public static void loadImg(Context context, Object obj, ImageView view) {
        if (requests == null) {
            setRequests(context);
        }
        loadImg(obj, view);
    }

    public static void loadImg(Object obj, ImageView view) {
        if (requests != null) {
            requests.load(obj).into(view);
        }
    }

    public static void loadImg(Object obj, int size, ImageView view) {
        if (requests != null) {
            requests.load(obj).override(size).into(view);
        }
    }

}
