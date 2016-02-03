package com.example.courtaks.local_data_storage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.courtaks.local_data_storage.FileStorage.filestorage;
import com.example.courtaks.local_data_storage.Preferences.SettingsActivity;
import com.example.courtaks.local_data_storage.Preferences.pref_javacode;
import com.example.courtaks.local_data_storage.sqliteStorage.sqliteStorageActivity;
import com.example.courtaks.local_data_storage.utils.cHelpers;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

    public void goto_normal_pref(View v){
        Intent intent = new Intent(this,pref_javacode.class);
        this.startActivity(intent);
    }
    public void goto_settings_pref(View v){
        Intent intent = new Intent(this,SettingsActivity.class);
        this.startActivity(intent);
    }


    public void goto_fileStorage(View v){
        Intent intent = new Intent(this,filestorage.class);
        this.startActivity(intent);
    }

    public void goto_sqlite1(View v){
        Intent intent = new Intent(this,sqliteStorageActivity.class);
        this.startActivity(intent);
    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
