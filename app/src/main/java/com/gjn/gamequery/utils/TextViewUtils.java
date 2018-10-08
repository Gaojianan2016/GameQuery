package com.gjn.gamequery.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

/**
 * @author gjn
 * @time 2018/10/8 14:25
 */

public class TextViewUtils {

    public static String getTextString(TextView textView){
        return textView.getText().toString().trim();
    }

    public static void setViewsMonitor(final View view, TextView... textViews) {
        view.setEnabled(false);
        final boolean[] booleans = new boolean[textViews.length];
        Watcher.Listener listener = new Watcher.Listener() {
            @Override
            public void isEmpty(String who, boolean b) {
                int i = Integer.parseInt(who);
                booleans[i] = b;
                int s = 0;
                for (boolean aBoolean : booleans) {
                    if (aBoolean) {
                        s++;
                    }
                }
                if (s == booleans.length) {
                    view.setEnabled(true);
                } else {
                    view.setEnabled(false);
                }
            }
        };
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].addTextChangedListener(new Watcher(String.valueOf(i), listener));
        }
    }

    private static class Watcher implements TextWatcher {

        private String who;
        private Listener listener;

        Watcher(String who, Listener listener) {
            this.who = who;
            this.listener = listener;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (listener != null) {
                if (s.toString().isEmpty()) {
                    listener.isEmpty(who, false);
                } else {
                    listener.isEmpty(who, true);
                }
            }
        }

        public interface Listener {
            void isEmpty(String who, boolean b);
        }
    }
}
