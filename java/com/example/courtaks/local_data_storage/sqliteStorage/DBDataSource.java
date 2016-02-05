package com.example.courtaks.local_data_storage.sqliteStorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by courtaks on 2/2/2016.
 */
public class DBDataSource {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;
    private static final String Logtag = "output";
    ArrayList<person_model> persons_list = new ArrayList<>();


    public DBDataSource(Context context){
        dbhelper = new DBOpenHelper(context);
        Log.d(Logtag, "calling db is done");
    }

    public void open(){
        Log.d(Logtag,"DB Opened");
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        Log.d(Logtag,"DB Closed");
        dbhelper.close();
    }


    public person_model create(person_model person){
/*there are two ways you can use here:-
    1- plain sql queres
    2- generate values then use the database.insert
*/

//way 2: generate values then use the database.insert

        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.COL2,person.Name);
        values.put(DBOpenHelper.COL3,person.Age);
        values.put(DBOpenHelper.COL4,person.Photo);
        values.put(DBOpenHelper.COL4,person.person_info);
//now we can insert a row in the
        long insertid = database.insert(DBOpenHelper.TABLE_NAME,null,values);
        person.Id = insertid;
        return person;
    }


    public ArrayList<person_model> Find(){
//        Cursor cursor = database.query(String table_name,String[] wanted_cols_names,selection,selectionArgs,groupBy,having,orderby);
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME, DBOpenHelper.COLS_STRING_ARRAY, null, null, null, null, null);
        Log.d(Logtag,"Retured "+cursor.getCount()+" Rows");
        if(cursor.getCount()>0){
            persons_list = new ArrayList<>();
            populate_person_list(cursor);
        }
        return persons_list;
    }

    public ArrayList<person_model> Find(String Selection, String orderBy){ //method overloading
//        Cursor cursor = database.query(String table_name,String[] wanted_cols_names,selection,selectionArgs,groupBy,having,orderby);
        Cursor cursor = database.query(DBOpenHelper.TABLE_NAME,DBOpenHelper.COLS_STRING_ARRAY,Selection,null,null,null,orderBy);
        Log.d(Logtag,"Retured "+cursor.getCount()+" Rows");
        if(cursor.getCount()>0){
            populate_person_list(cursor);
        }
        return persons_list;
    }

    public boolean remove(long id){
        String where_str = DBOpenHelper.COL1+" = "+id;
        int result = database.delete(DBOpenHelper.TABLE_NAME,where_str,null);//return 1 on success
        return (result == 1);
    }


    private ArrayList<person_model> populate_person_list( Cursor cursor) {
        while(cursor.moveToNext()){
            person_model person = new person_model();
            person.Id = cursor.getLong(cursor.getColumnIndex(DBOpenHelper.COL1));
            person.Name = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COL2));
            person.Age = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.COL3));
            person.Photo = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.COL4));
            person.person_info = cursor.getString(cursor.getColumnIndex(DBOpenHelper.COL5));
            persons_list.add(person);
        }
        return persons_list;
    }



}
