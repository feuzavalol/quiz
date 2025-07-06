package com.example.quiz;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

public class LaunchActivity extends AppCompatActivity {
    static String KEY_NAME_FILE = "FileName";
    static String KEY_NB_CARDS = "NbCards";
    static String KEY_LANGUAGE = "Language";
    static String KEY_FINAL = "Final";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_launch);
    }
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
