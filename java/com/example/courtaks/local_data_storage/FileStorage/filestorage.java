package com.example.courtaks.local_data_storage.FileStorage;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courtaks.local_data_storage.R;
import com.example.courtaks.local_data_storage.utils.cHelpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class filestorage extends AppCompatActivity {

    private File external_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filestorage);
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
FileStorage : means the data is saved as a file.
there is two types of the FileStorage:-
    - Internal Storage: means the file is internal to the app. (only accessible from this app)
    - External Storage: means the file is external to the app. (ca be accessed from other apps too)
*/


//Access Internal Files:-
        File internal_files = getFilesDir(); //means access the internal files directory
        String internal_files_path = internal_files.getAbsolutePath();

//Access External Files:-
        File external_files = getExternalFilesDir(null);
        String external_files_path = external_files.getAbsolutePath();
        this.external_file = new File(external_files, "external_file"); //file to save & read
// You will find the created file at: Device/mnt/SdCard/Android/data/{app_name}/files



        TextView tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("The Internal Path Location is: " + internal_files_path + " \nThe External " +
                "Path Location is: " + external_files_path);

    }

    public void CreateFile(View v) throws IOException, JSONException {
        int id = v.getId();


//inputText is a input data:-
        EditText input1 = (EditText) findViewById(R.id.input1);
        EditText input2 = (EditText) findViewById(R.id.input2);


//inputText can be a json string:-
//        inputText = GenerateJsonText();

        FileOutputStream fos;
        String inputText;

        if(id==R.id.create_exteranl_file) { //this is external file (external to the app)
            if (!CheckExternalStorage()) return;
            fos = new FileOutputStream(external_file);
            inputText = input2.getText().toString();
        }else{ //this is internal file (local to the app)
            fos = openFileOutput("myfile.txt", MODE_PRIVATE);
            inputText = input1.getText().toString();
        }

        fos.write(inputText.getBytes());
        fos.close();

        Toast.makeText(this,"File Written to the Disk & data was saved", Toast.LENGTH_SHORT).show();
    }


    public void ReadFile(View v) throws IOException {
        int id = v.getId();

        FileInputStream fis;

        if(id==R.id.read_external_file) { //this is external file (external to the app)
            if (!CheckExternalStorage()) return;
            fis = new FileInputStream(external_file);
        }else{ //internal
            fis = openFileInput("myfile.txt");
        }

        BufferedInputStream bis = new BufferedInputStream(fis);
        StringBuffer b = new StringBuffer();
        while(bis.available()!=0){
            char c = (char) bis.read();
            b.append(c);
        }
        TextView tv2 = (TextView) findViewById(R.id.tv2);
        tv2.setText(b.toString());
        bis.close();
        fis.close();
        Toast.makeText(this,"Data Was Read & outputed to the ", Toast.LENGTH_LONG).show();
    }





    public String GenerateJsonText() throws JSONException {
        JSONObject d = new JSONObject(); //create json object
        d.put("tour1","Saved Text one"); //add a json statement to the object
        d.put("tour2", "Saved Text two");
        d.put("tour3", "Saved Text three");
        JSONArray final_text = new JSONArray().put(d); //add the json object to a json array
        return final_text.toString();
    }
    public boolean CheckExternalStorage(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            Toast.makeText(this,"External Storage: read-only", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"External Storage Unavailable At All", Toast.LENGTH_LONG).show();
        }
        return false;
    }







}
