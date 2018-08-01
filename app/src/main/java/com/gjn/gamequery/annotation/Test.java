package com.gjn.gamequery.annotation;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gjn
 * @time 2018/8/1 10:51
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    @LayoutRes int value() default View.NO_ID;
}
