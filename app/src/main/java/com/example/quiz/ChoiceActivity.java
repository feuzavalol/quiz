package com.example.quiz;

import static android.text.TextUtils.substring;

import static com.example.quiz.LaunchActivity.KEY_NAME_FILE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.quiz.widget.Bouton;
import com.example.quiz.widget.Dropdown;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ChoiceActivity extends AppCompatActivity {

    Dropdown dropdownFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choice);


        LinearLayout mainLayout = findViewById(R.id.layout_training);
        String[] arrChoice = fillChoice();
        List<String> list = List.of(arrChoice);
        dropdownFile = addDropdown(list,R.string.hint_choice);

        Bouton startTrainingButton = new Bouton(this,mainLayout,this::goToTrainingPage,"S'entrainer !",R.drawable.bg_button_gold);
        LinearLayout.LayoutParams layoutParamsTrainingButton = (LinearLayout.LayoutParams) startTrainingButton.getLayoutParams();
        layoutParamsTrainingButton.width = GridLayout.LayoutParams.WRAP_CONTENT;
        layoutParamsTrainingButton.gravity  = Gravity.CENTER;
        layoutParamsTrainingButton.setMargins(50, 75, 50, 75);
        startTrainingButton.setLayoutParams(layoutParamsTrainingButton);
    }

    public Dropdown addDropdown(List<String> choiceList,int idHint) {
        LinearLayout grid = findViewById(R.id.grid);
        Dropdown bou = new Dropdown(this,choiceList, getString(idHint));
        grid.addView(bou);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bou.getLayoutParams();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.setMargins(0, 20, 0, 0);
        bou.setLayoutParams(params);
        return bou;
    }
    public void goToTrainingPage(View view){
        String fillName = dropdownFile.getItem();
        if (!fillName.isEmpty()){
            Intent intentTraining = new Intent(getApplicationContext(), TrainingActivity.class);
            intentTraining.putExtra(KEY_NAME_FILE, dropdownFile.getItem());
            startActivity(intentTraining);
        }
    }
    private String[] fillChoice(){
        Field[] fields = R.raw.class.getFields();
        String [] arr = new String[fields.length];
        for (int i=0;i<fields.length;i++){
            try {
                arr[i] = fields[i].getName(); // e.g., my_csv_file
                // int resId = fields[i].getInt(fields[i]);
                // InputStream is = getResources().openRawResource(resId);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arr;
    }
}
