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
        return obj2json(map);
    }

    public static String obj2json(Object obj){
        checkNull();
        return gson.toJson(obj);
    }

    public static Map json2map(@NonNull String json){
        return json2obj(json, Map.class);
    }

    public static <T> T json2obj(@NonNull String json, Class<T> cls){
        checkNull();
        if (json.isEmpty()) {
            return null;
        }
        return gson.fromJson(json, cls);
    }

    private static void checkNull(){
        if (gson == null) {
            gson = new Gson();
        }
    }
}
