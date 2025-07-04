package com.example.quiz;

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

import java.util.List;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choice);


        LinearLayout mainLayout = findViewById(R.id.layout_training);
        List<String> listChoice;
        Dropdown dropdownFile = addDropdown(listChoice,R.string.hint_choice);

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
        Intent intentTraining = new Intent(getApplicationContext(), TrainingActivity.class);
        startActivity(intentTraining);
    }
}
