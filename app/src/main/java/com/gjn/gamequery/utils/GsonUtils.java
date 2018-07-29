package com.gjn.gamequery.utils;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Map;

/**
 * GsonUtils
 * Created by gjn
 * on 2018-07-28 18:50.
 */
public class GsonUtils {
    private static Gson gson;

    public static String map2json(@NonNull Map<String, Object> map){
        checkNull();
        return gson.toJson(map);
    }

    public static Map json2map(@NonNull String json){
        checkNull();
        if (json.isEmpty()) {
            return null;
        }
        return gson.fromJson(json, Map.class);
    }

    private static void checkNull(){
        if (gson == null) {
            gson = new Gson();
        }
    }
}
