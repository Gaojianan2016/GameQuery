package com.gjn.gamequery.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gjn
 * @time 2018/8/13 16:33
 */

public class JumpDao {
    private static final String TAG = "JumpDao";
    private JumpDBHelper dbHelper;
    private SQLiteDatabase db;

    public JumpDao(Context context) {
        if (dbHelper == null) {
            dbHelper = new JumpDBHelper(context);
        }
    }

    public SQLiteDatabase getWritableDB(){
        return dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDB(){
        return dbHelper.getReadableDatabase();
    }

    public void insert(long matchId, String json) {
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JumpDBHelper.TB_NAME_MATCHID, matchId);
        values.put(JumpDBHelper.TB_NAME_JSON, json);
        db.insert(JumpDBHelper.TB_NAME, null, values);
        db.close();
    }

    public void delete(long matchId) {
        if (!hasMatchId(matchId)) {
            return;
        }
        db = dbHelper.getWritableDatabase();
        db.delete(JumpDBHelper.TB_NAME, JumpDBHelper.TB_NAME_MATCHID + "=?",
                new String[]{String.valueOf(matchId)});
        db.close();
    }

    public void updata(long matchId, String json){
        if (!hasMatchId(matchId)) {
            return;
        }
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(JumpDBHelper.TB_NAME_JSON, json);
        db.update(JumpDBHelper.TB_NAME, values, JumpDBHelper.TB_NAME_MATCHID + "=?",
                new String[]{String.valueOf(matchId)});
        db.close();
    }

    public List<JumpDBModel> query(long matchId){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        if (matchId == -1) {
            cursor = db.query(JumpDBHelper.TB_NAME, null, null,
                    null, null, null, null);
        }else {
            cursor = db.query(JumpDBHelper.TB_NAME, null,
                    JumpDBHelper.TB_NAME_MATCHID + "=?", new String[]{String.valueOf(matchId)},
                    null, null, null);
        }
        if (cursor == null) {
            return null;
        }
        List<JumpDBModel> models = null;
        if (cursor.getCount() > 0) {
            models = new ArrayList<>(cursor.getCount());
            while (cursor.moveToNext()) {
                JumpDBModel model = new JumpDBModel();
                model.setMatchId(parseLong(cursor, JumpDBHelper.TB_NAME_MATCHID));
                model.setJson(parseString(cursor, JumpDBHelper.TB_NAME_JSON));
                models.add(model);
            }
        }
        cursor.close();
        db.close();
        return models;
    }

    public List<JumpDBModel> queryAll(){
        return query(-1);
    }

    public String queryString(long matchId){
        if (!hasMatchId(matchId)) {
            return "";
        }
        List<JumpDBModel> models = query(matchId);
        return models.get(0).getJson();
    }

    public void add(long matchId, String json){
        if (hasMatchId(matchId)) {
            Log.d(TAG, "更新 = " + matchId);
            Log.d(TAG, "更新 = " + json);
            updata(matchId, json);
        }else {
            Log.d(TAG, "新增 = " + matchId);
            Log.d(TAG, "新增 = " + json);
            insert(matchId, json);
        }
    }

    public boolean hasMatchId(long matchId){
        return query(matchId) != null;
    }

    private int parseInt(Cursor cursor, String tbName) {
        return cursor.getInt(cursor.getColumnIndex(tbName));
    }

    private double parseDouble(Cursor cursor, String tbName) {
        return cursor.getDouble(cursor.getColumnIndex(tbName));
    }

    private long parseLong(Cursor cursor, String tbName) {
        return cursor.getLong(cursor.getColumnIndex(tbName));
    }

    private String parseString(Cursor cursor, String tbName) {
        return cursor.getString(cursor.getColumnIndex(tbName));
    }

}
