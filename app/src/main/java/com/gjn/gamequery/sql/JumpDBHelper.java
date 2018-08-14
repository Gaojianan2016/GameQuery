package com.gjn.gamequery.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author gjn
 * @time 2018/8/13 16:22
 */

public class JumpDBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "gamequery.db";
    public static final String TB_NAME = "jumpMatch";
    public static final String TB_NAME_MATCHID = "matchId";
    public static final String TB_NAME_JSON = "json";

    public JumpDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TB_NAME +
                "(" +
                "_id integer primary key autoincrement, " +
                TB_NAME_MATCHID + " integer, " +
                TB_NAME_JSON + " text " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            //删除旧table
            String sql = "drop table if exists " + TB_NAME;
            db.execSQL(sql);
            //重新创建新table
            onCreate(db);
        }
    }
}
