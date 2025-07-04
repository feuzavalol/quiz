package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quiz.widget.Bouton;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout mainLayout;
    LinearLayout sumUpLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mainLayout = findViewById(R.id.main);
        sumUpLayout = new LinearLayout(this); // Utile plus tard pour afficher des infos concernant les classements extérieurs et les résultats précédents

        Bouton accountButton = new Bouton(this,mainLayout,this::goToAccountPage,"Compte",R.drawable.bg_button_blue);
        ConstraintLayout.LayoutParams layoutParamsAccountButton = (ConstraintLayout.LayoutParams) accountButton.getLayoutParams();
        layoutParamsAccountButton.topToTop = R.id.main;
        layoutParamsAccountButton.endToEnd = R.id.main;
        layoutParamsAccountButton.setMargins(50, 75, 50, 75);
        accountButton.setLayoutParams(layoutParamsAccountButton);

        Bouton trainingButton = new Bouton(this,mainLayout,this::goToTrainingPage,"Entraînement",R.drawable.bg_button_purple);
        ConstraintLayout.LayoutParams layoutParamsTrainingButton = (ConstraintLayout.LayoutParams) trainingButton.getLayoutParams();
        layoutParamsTrainingButton.topToTop = R.id.main;
        layoutParamsTrainingButton.startToStart = R.id.main;
        layoutParamsTrainingButton.endToEnd = accountButton.getId();
        layoutParamsTrainingButton.setMargins(50, 75, 50, 75);
        trainingButton.setLayoutParams(layoutParamsTrainingButton);
    }
    public void goToAccountPage(View view){
        Intent intentAccount = new Intent(getApplicationContext(), AccountActivity.class);
        startActivity(intentAccount);
    }
    public void goToTrainingPage(View view){
        Intent intentTraining = new Intent(getApplicationContext(), TrainingActivity.class);
        startActivity(intentTraining);
    }
}