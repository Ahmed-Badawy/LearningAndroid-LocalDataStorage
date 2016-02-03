package com.example.courtaks.local_data_storage.utils;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by courtaks on 2/27/2015.
 */
public class cHelpers {

    public static void displayText(Activity activity, int id, String text) {
        TextView tv = (TextView) activity.findViewById(id);
        tv.setText(text);
    }
    public static String getText(Activity activity, int id) {
        EditText et = (EditText) activity.findViewById(id);
        return et.getText().toString();
    }
    public static boolean getCBChecked(Activity activity, int id) {
        CheckBox cb = (CheckBox) activity.findViewById(id);
        return cb.isChecked();
    }
    public static void setCBChecked(Activity activity, int id, boolean value) {
        CheckBox cb = (CheckBox) activity.findViewById(id);
        cb.setChecked(value);
    }

    public static void show_toast(Activity activity,String txt){
        Toast.makeText(activity,txt,Toast.LENGTH_SHORT).show();
    }

}
