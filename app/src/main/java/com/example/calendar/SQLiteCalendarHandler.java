package com.example.calendar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteCalendarHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "calendar.db";
    public static final String TABLE_NAME = "EventCalendar";
    public static final String COL_1 = "Date";
    public static final String COL_2 = "Event";

    public SQLiteCalendarHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


//    public SQLiteCalendarHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE EventCalendar (ID INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Event TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
