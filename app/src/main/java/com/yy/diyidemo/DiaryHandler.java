package com.yy.diyidemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/18.
 */
public class DiaryHandler {
    private Context mContext;
    private DataBaseOpenHelper dbHelper;
    private SQLiteDatabase db;

    public DiaryHandler(Context context, int version) {
        mContext = context;
        dbHelper = new DataBaseOpenHelper(mContext, "Diary.db", null, version);
        db = dbHelper.getReadableDatabase();
    }

    public void addDiary(Diary diary) {
        ContentValues values = new ContentValues();
        values.put("title", diary.getTitle());
        values.put("time", diary.getTime());
        values.put("data", diary.getData());
        values.put("classify", diary.getClassify());
        values.put("readTimes", 0);
        db.insert("Diary", null, values);
    }

    public void updateDiary(Diary oldDiary, Diary newDiary) {
        ContentValues values = new ContentValues();
        values.put("title", newDiary.getTitle());
        values.put("time", newDiary.getTime());
        values.put("data", newDiary.getData());
        values.put("classify", newDiary.getClassify());
        db.update("Diary", values, "title = ? , data = ?", new String[]{oldDiary.getTitle(), oldDiary.getData()});
    }

    public void deleteDiary(Diary diary) {
        //可以用两个条件？
        db.delete("Diary", "data = ?", new String[]{diary.getData()});
    }

    public List<Diary> findDiaryByClassifyID(String classify) {
        List<Diary> diaryList= new ArrayList<Diary>();
        //取出分类为classify的所有Diary，并以Time排序，排序方式为默认
        //并不能以时间排序，因为我的时间是以天为单位，如果以时间排序会导致只读到一天的最后一条
        Cursor cursor;
        if (classify.equals("全部")) {
            cursor = db.query("Diary", null, "classify != ?", new String[]{"草稿箱"}, null, null, null);
        } else {
            cursor = db.query("Diary", null, "classify = ?", new String[]{classify}, null, null, null);
        }
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String data = cursor.getString(cursor.getColumnIndex("data"));
                diaryList.add(new Diary(title, time, data,classify));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return diaryList;
    }

    public Diary findDiaryByData(String data) {
        Cursor cursor = db.query("Diary", null, "data = ?", new String[]{data}, null, null, null);
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String time = cursor.getString(cursor.getColumnIndex("time"));
        String classifyID = cursor.getString(cursor.getColumnIndex("classify"));
        Diary diary = new Diary(title, time, data, classifyID);
        return diary;
    }
}
