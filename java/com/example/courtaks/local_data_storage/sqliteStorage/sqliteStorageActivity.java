package com.example.courtaks.local_data_storage.sqliteStorage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.courtaks.local_data_storage.R;

import java.util.ArrayList;

public class sqliteStorageActivity extends AppCompatActivity {


    private static final String Logtag = "output";
    ArrayList<person_model> persons_list = new ArrayList<>();
    DBDataSource dbDataSourceObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_storage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        dbDataSourceObj = new DBDataSource(this);
        dbDataSourceObj.open();
//        insertData();
//        readAllData();
        readDataFiltered();


    }

    @Override
    protected void onResume() {
        super.onResume();
        dbDataSourceObj.open();
    }
    @Override
    protected void onPause() {
        super.onPause();
        dbDataSourceObj.close();
    }




    private void insertData(){
        person_model person1 = new person_model("ahmed",45,1,"person 1 info");
        persons_list.add(dbDataSourceObj.create(person1));

        person_model person2 = new person_model("mona",50,2,"person 2 info");
        persons_list.add(dbDataSourceObj.create(person2));

        person_model person3 = new person_model("mohamed",30,3,"person 3 info");
        persons_list.add(dbDataSourceObj.create(person3));

        Log.d(Logtag, "-------------Creating in db done---------------------");
        Log.d(Logtag, persons_list.toString());
    }
    private void readAllData(){
        persons_list = dbDataSourceObj.Find();
        Log.d(Logtag, "-------------Reading From the db---------------------");
        Log.d(Logtag, persons_list.toString());
    }
    private void readDataFiltered(){
        persons_list = dbDataSourceObj.Find("Age<50","Id DESC");
        Log.d(Logtag, "-------------Reading From the db (filtered)---------------------");
        Log.d(Logtag, persons_list.toString());
    }




}
