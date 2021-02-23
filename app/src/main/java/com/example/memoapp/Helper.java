package com.example.memoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MemoApp.db";    // database 생성
    public static final String DATABASE_TABLE_NAME = "MemoList";    // table 생성
    public static final String DATABASE_COLUMN_ID = "id";   // primary key
    public static final String DATABASE_COLUMN_TITLE_TEXT = "title_text";    // column 생성
    public static final String DATABASE_COLUMN_BODY_TEXT = "body_text";       // column 생성

    Helper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table MemoList" +
                        "(id integer primary key," +
                        "title_text text, " +
                        "body_text text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        sqLiteDatabase.execSQL("drop table if exists MemoList;");
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String title_text, String body_text){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DATABASE_COLUMN_TITLE_TEXT, title_text);
        contentValues.put(DATABASE_COLUMN_BODY_TEXT, body_text);

        sqLiteDatabase.insert(DATABASE_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        Log.d("get data what id?:", "=" + id);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select*from MemoList where id="+id,null);

        return cursor;
    }

    public int numberOfRows(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int rows = (int) DatabaseUtils.queryNumEntries(sqLiteDatabase, DATABASE_TABLE_NAME);

        return rows;
    }

    public boolean update(int id, String title_text, String body_text){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("title_text",title_text);
        contentValues.put("body_text",body_text);

        sqLiteDatabase.update("MemoList", contentValues,"id=?",new String[]
                {Integer.toString(id)});

        return true;
    }

    public Integer delete(Integer id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //return sqLiteDatabase.delete("MemoList", "id=?", new String []
          //              {Integer.toString(id)});
        return sqLiteDatabase.delete("MemoList","id"+"="+id, null);
    }

    public ArrayList getAll(){
        /* 객체
        *  id : ?
        *  title text : ?
        * */
        ArrayList arrayList = new ArrayList<Map<String, String>>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select*from "+ DATABASE_TABLE_NAME, null);

        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){

            HashMap<String, String> id_text_map = new HashMap<>();

            id_text_map.put(
                    cursor.getString(cursor.getColumnIndex(DATABASE_COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(DATABASE_COLUMN_TITLE_TEXT))
            );

            arrayList.add(id_text_map);
            cursor.moveToNext();
        }

        return arrayList;
    }
}
