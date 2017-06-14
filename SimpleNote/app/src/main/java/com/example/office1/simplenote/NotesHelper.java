package com.example.office1.simplenote;

import static android.content.ContentValues.TAG;
import static android.provider.BaseColumns._ID;
import static com.example.office1.simplenote.Constants.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class NotesHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "simple_note.db";
    private static final int DATABASE_VERSION = 2;

    public NotesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCmd ="CREATE TABLE " + TABLE_NAME+ " ("+
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TIME + " INTEGER, "+
                CONTENT + " TEXT NOT NULL );";
        db.execSQL(sqlCmd);

        Log.d(TAG, "Database tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}
