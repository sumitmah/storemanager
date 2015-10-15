package com.example.vjdhama.storemanager.sqlite.helper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vjdhama.storemanager.sqlite.model.Item;

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "storemanager";
    private static final String TABLE_ITEMS = "items";
    private static final String CREATE_TABLE_ITEMS = "CREATE TABLE "
            + TABLE_ITEMS + "(" + Item.KEY_ID + " INTEGER PRIMARY KEY," + Item.KEY_NAME
            + " TEXT," + Item.KEY_EXPIRY_DATE + " DATETIME," + Item.KEY_CREATED_AT
            + " DATETIME" + ")";

    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("database", "constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_ITEMS);
        Log.d("database", "oncreate called");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        Log.d("database", "upgrade called");
        onCreate(db);
    }
}