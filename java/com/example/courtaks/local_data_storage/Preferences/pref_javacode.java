package com.example.courtaks.local_data_storage.Preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.courtaks.local_data_storage.R;
import com.example.courtaks.local_data_storage.utils.cHelpers;

public class pref_javacode extends AppCompatActivity {

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref_javacode);
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


/*
way 1 to store locally Prefernces:
        - Preferences Are Not Encrypted, it's stored in plain text...
        - the user can delete preference from the settings of the app

there are two ways to share an preference:-
        way 1: through an Preference Activity:-
            - it will be accessible across all the application
            - Limited Values to (boolean,String,list of strings)
            - must navigate to it with an intent (like normal)

        Way 2: in java code
            - will be accessible in this activity only.
            - doesn't have to navigate to it,
*/

        settings = getPreferences(MODE_PRIVATE);
    }


    public void setPreference(View v){
//        Log.d("pref", "pref set clicked");
        SharedPreferences.Editor editor = settings.edit();
        String prefValue = cHelpers.getText(this, R.id.editText);
        editor.putString("key1",prefValue);
        editor.commit();
        cHelpers.show_toast(this, "Preference Saved");
    }
    public void showPreference(View v){
//        Log.d("pref", "pref show clicked");
        String prefvalue = settings.getString("key1","Not Found");
        cHelpers.show_toast(this,"key1 was "+prefvalue);
    }





}
