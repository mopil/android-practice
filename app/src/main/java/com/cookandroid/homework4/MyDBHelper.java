package com.cookandroid.homework4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context) { super(context, "movieLikesDB", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE movieTBL ( movieTitle CHAR(20) PRIMARY KEY , movieLikes INTEGER );" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movieTBL; ");
        onCreate(db);
    }

    public void updateMovieLikes(SQLiteDatabase db, String mt) {
        Cursor cursor = db.rawQuery("SELECT * FROM movieTBL",null);
        while (cursor.moveToNext()){
            String title = cursor.getString(0);
            if (title.equals(mt) == true) {
                int likes = cursor.getInt(1)+1;
                db.execSQL("UPDATE movieTBL SET movieLikes = '"+likes+"' WHERE movieTitle = '"+mt+"';");
                break;
            }
        }

        cursor.close();
    }

    public void init(SQLiteDatabase db, String[] posterTitle) {
        // DB 한번 초기화
        onUpgrade(db, 1, 2);

        for (int i = 0 ; i<20 ; i++) {
            String title = posterTitle[i];
            db.execSQL("INSERT INTO movieTBL VALUES ( '"+title+"' , 0 );");
        }
    }

    public int[] getAllLikes(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM movieTBL",null);
        int [] likes = new int [20];
        int i = 0;
        while (cursor.moveToNext()) {
            likes[i++] = cursor.getInt(1);
        }
        cursor.close();
        return likes;
    }

    public String[] getAllTitle(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM movieTBL",null);
        String titles []  = new String [20];
        int i = 0;
        while (cursor.moveToNext()) {
            titles[i++] = cursor.getString(0);
        }
        cursor.close();
        return titles;
    }

    public boolean checkEmpty(SQLiteDatabase db){
        Cursor cursor = db.rawQuery("SELECT * FROM movieTBL",null);
        while (cursor.moveToNext()) {
            try {
                cursor.getInt(1);
            } catch (NullPointerException e) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }
}
