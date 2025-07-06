package com.example.quiz;

import static com.example.quiz.LaunchActivity.KEY_LANGUAGE;
import static com.example.quiz.LaunchActivity.KEY_NAME_FILE;
import static com.example.quiz.LaunchActivity.KEY_NB_CARDS;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.widget.Bouton;
import com.example.quiz.widget.Dropdown;

import java.lang.reflect.Field;
import java.util.List;

public class ChoiceActivity extends AppCompatActivity {

    Dropdown dropdownFile;
    Dropdown dropdownLanguage;
    EditText nbCardsEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choice);


        LinearLayout mainLayout = findViewById(R.id.layout_training);
        String[] arrChoice = fillChoice();
        List<String> list = List.of(arrChoice);
        dropdownFile = addDropdown(list,R.string.hint_choice);
        String[] languageChoice = {"Français","Autre"};
        dropdownLanguage = addDropdown(List.of(languageChoice),R.string.hint_language);
        nbCardsEditText = new EditText(this);
        nbCardsEditText.setHint(R.string.hint_nb);
        nbCardsEditText.setText(R.string.default_cards_number);
        mainLayout.addView(nbCardsEditText);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) nbCardsEditText.getLayoutParams();
        params.setMargins(50, 20, 0, 20);
        params.gravity  = Gravity.CENTER;
        nbCardsEditText.setLayoutParams(params);

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
        String fileName = dropdownFile.getItem();
        String languageChoosed = dropdownLanguage.getItem();
        String temp = nbCardsEditText.getText().toString();
        int nbCards;
        if (temp.isEmpty()) {
            nbCards = 0;
        }
        else{
            nbCards = Integer.parseInt(temp);
        }
        if (!fileName.isEmpty() && !languageChoosed.isEmpty() && nbCards != 0){
            Intent intentTraining = new Intent(getApplicationContext(), TrainingActivity.class);
            intentTraining.putExtra(KEY_NAME_FILE, fileName);
            intentTraining.putExtra(KEY_NB_CARDS,nbCards);
            intentTraining.putExtra(KEY_LANGUAGE,languageChoosed);
            startActivity(intentTraining);
        }
    }
    private String[] fillChoice(){
        Field[] fields = R.raw.class.getFields();
        String [] arr = new String[fields.length];
        for (int i=0;i<fields.length;i++){
            try {
                arr[i] = fields[i].getName(); // e.g., my_csv_file
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arr;
    }
}
