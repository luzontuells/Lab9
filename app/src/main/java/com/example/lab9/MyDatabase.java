package com.example.lab9;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by A5Alumno on 25/11/2016.
 */

public class MyDatabase extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Table";
    private static final String KEY_WORD = "KeyWord";
    private static final String KEY_DESCRIPTION = "KeyDescription";
    private static final String DATABASE_NAME = "DatabaseName";
//    private static final String DATABASE_CREATE_COMMAND = "CREATE TABLE " +MyDatabase.TABLE_NAME
//            +" (" +MyDatabase.KEY_WORD+" TEXT, "
//            +MyDatabase.KEY_DESCRIPTION+ " TEXT);";

    private static final String DATABASE_CREATE_COMMAND = "CREATE TABLE "
            + MyDatabase.TABLE_NAME + " (" + MyDatabase.KEY_WORD + " TEXT, "
            + MyDatabase.KEY_DESCRIPTION + " TEXT);";

    private static final String DATABASE_CREATE = "CREATE TABLE "
            +MyDatabase.TABLE_NAME+" ("+MyDatabase.KEY_WORD+" TEXT, "
            +MyDatabase.KEY_DESCRIPTION+" TEXT);";

    public MyDatabase(Context context) {
        super(context, MyDatabase.DATABASE_NAME, null, MyDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MyDatabase.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
