package com.yy.diyidemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Administrator on 2015/5/18.
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper{

    private static final String TAG = "diary";
    //TODO 添加评论列表
    private static final String CREATE_DIARY = "create table Diary (" +
            "id integer primary key autoincrement, " +
            "title text, " +
            "time text, " +
            "data text, " +
            "classify text, " +
            "readTimes integer)";

    private Context mContext;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
        Log.d(TAG,"create diary table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public DataBaseOpenHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        mContext = context;
    }
}
