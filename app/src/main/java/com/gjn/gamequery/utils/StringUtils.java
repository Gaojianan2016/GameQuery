package com.gjn.gamequery.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * StringUtils
 * Created by gjn
 * on 2018-07-28 18:22.
 */
public class StringUtils {

    public static String deleteLast(String str, int i){
        if (i >= str.length()){
            return "";
        }
        return str.substring(0, str.length() - i);
    }

    public static String deleteStart(String str, int i){
        if (i >= str.length()){
            return "";
        }
        return str.substring(i, str.length());
    }

    public static String deleteLast(String str){
        return deleteLast(str, 1);
    }

    public static String deleteStart(String str){
        return deleteStart(str, 1);
    }

    public static String replaceStrLast(String str, String replace){
        if (replace.length() >= str.length()) {
            return replace;
        }
        return deleteLast(str, replace.length()) + replace;
    }

    public static String doubleFormat(double d){
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(d);
    }
}
