package com.example.courtaks.local_data_storage.sqliteStorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by courtaks on 2/2/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String LOGTAG = "sqlite";
    private static final String DB_NAME = "persons.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME  = "persons_table";
    public static final String COL1 = "id";
    public static final String COL2 = "name";
    public static final String COL3 = "age";
    public static final String COL4 = "photo";
    public static final String COL5 = "person_info";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL2 + " TEXT, " +
                    COL3 + " NUMERIC, " +
                    COL4 + " NUMERIC, " +
                    COL5 + " TEXT " +
                    ")";

    public static final String[] COLS_STRING_ARRAY = {
            COL1,COL2,COL3,COL4,COL5
    };


//    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    public DBOpenHelper(Context context) {
//        super(context, name, factory, version);
        //context: context of the calling activity
        //name: the name of the db
        //factory: not needed so we put null
        //version: version of the db

        super(context, DB_NAME, null , DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE); //create the table
        Log.d(LOGTAG,"table has been created");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db); //create it again
    }

}
