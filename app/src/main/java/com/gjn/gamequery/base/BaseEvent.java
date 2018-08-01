package com.gjn.gamequery.base;

import android.os.Bundle;

/**
 * @author gjn
 * @time 2018/8/1 11:34
 */

public interface BaseEvent {
    void showNextActivity(Class<?> cls);
    void showNextActivity(Class<?> cls, Bundle bundle);
    void toNextActivity(Class<?> cls);
    void toNextActivity(Class<?> cls, Bundle bundle);
    void showToast(String msg);
}
