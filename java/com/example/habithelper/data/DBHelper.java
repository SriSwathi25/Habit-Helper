package com.example.habithelper.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.habithelper.addHabit;

public class DBHelper extends SQLiteOpenHelper {
    /** Name of the database file */
    public static final String DATABASE_NAME = "HABITHELPER.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     *
     *
     * @param context of the app
     */

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the activities table
        db.execSQL("create TABLE IF NOT EXISTS "+addHabit.TABLE_NAME1+" ( "+ addHabit.COL_1_1+" INTEGER PRIMARY KEY AUTOINCREMENT , "+addHabit.COL_1_2+ " TEXT, "+addHabit.COL_1_3+" TEXT, "+addHabit.COL_1_4+" INTEGER, "+addHabit.COL_1_5+" TEXT); ");
        db.execSQL("create TABLE IF NOT EXISTS "+addHabit.TABLE_NAME2+ " ( "+ addHabit.COL_2_1+" INTEGER PRIMARY KEY AUTOINCREMENT , " + addHabit.COL_2_2+" TEXT, "+addHabit.COL_2_3+ " INTEGER, "+addHabit.COL_2_4+" INTEGER, FOREIGN KEY ("+addHabit.COL_2_1+") REFERENCES "+addHabit.TABLE_NAME1+" ("+addHabit.COL_1_1+")); ");
        //db.execSQL("create TABLE IF NOT EXISTS "+TABLE_NAME2+ " (NAME TEXT, CURRENT_COUNT INTEGER, STREAK INTEGER,  FOREIGN KEY (NAME) REFERENCES HABITS (NAME)); ");
    }
    public void     streakUpdate(SQLiteDatabase db, int id, int streak){
        ContentValues newValues = new ContentValues();
        newValues.put(addHabit.COL_2_4, streak);

        db.update(addHabit.TABLE_NAME2, newValues, "id="+id, null);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}

