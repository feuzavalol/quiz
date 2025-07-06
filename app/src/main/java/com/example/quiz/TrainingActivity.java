package com.example.quiz;

import static com.example.quiz.LaunchActivity.KEY_FINAL;
import static com.example.quiz.LaunchActivity.KEY_LANGUAGE;
import static com.example.quiz.LaunchActivity.KEY_NB_CARDS;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.quiz.widget.Bouton;
import com.example.quiz.widget.Word;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Vector;

public class TrainingActivity extends AppCompatActivity {
    Word[] fileContent;
    ConstraintLayout mainLayout;
    int currentQuestion = 1;
    int nbCards;
    int[] permutation;
    String[] answers;
    ConstraintLayout questionLayout;
    TextView questionDisplay;
    TextView numberCardsDisplay;
    EditText userAnswer;
    Bouton recordAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_training);


        mainLayout = findViewById(R.id.main);
        Intent caller = getIntent();
        String fileName = caller.getStringExtra(LaunchActivity.KEY_NAME_FILE);
        nbCards = caller.getIntExtra(KEY_NB_CARDS,0);
        String language = caller.getStringExtra(KEY_LANGUAGE);
        assert(nbCards > 0 && !fileName.isEmpty() && !language.isEmpty());

        answers = new String[nbCards];

        InputStream csvData = getResources().openRawResource(LaunchActivity.getResId(fileName,R.raw.class));
        try {
            fileContent = readCSV(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert(fileContent != null);

        nbCards = min(nbCards,fileContent.length);
        fileContent = Word.shuffle(fileContent);

        Bouton accountButton = new Bouton(this,mainLayout,this::goToAccountPage,"Compte",R.drawable.bg_button_blue);
        ConstraintLayout.LayoutParams layoutParamsAccountButton = (ConstraintLayout.LayoutParams) accountButton.getLayoutParams();
        layoutParamsAccountButton.topToTop = R.id.main;
        layoutParamsAccountButton.endToEnd = R.id.main;
        layoutParamsAccountButton.setMargins(50, 75, 50, 75);
        accountButton.setLayoutParams(layoutParamsAccountButton);

        setQuestionLayout();
        setQuestionDisplay();
        setNumberDisplay();
        setUserAnswer();
        setRecordAnswer();
    }
    protected Word[] readCSV(InputStream csvData) throws IOException {
        String[] line;
        CSVReader reader = new CSVReader(new InputStreamReader(csvData));
        Vector<Word> temp = new Vector<>();
        while ((line = reader.readNext()) != null) {
            Word tempWord = new Word(line[0],line[1],line[2]);
            tempWord.format();
            temp.add(tempWord);
        }
        return Arrays.copyOf(temp.toArray(), temp.toArray().length, Word[].class);
    }
    public void goToAccountPage(View view){
        Intent intentAccount = new Intent(getApplicationContext(), AccountActivity.class);
        startActivity(intentAccount);
    }
    public void getNextQuestion(View view){
        String answer = userAnswer.getText().toString();
        answers[currentQuestion] = answer;
        if (currentQuestion < nbCards) {
            currentQuestion += 1;
            questionDisplay.setText(fileContent[currentQuestion].getFrench());
            String temp = "   " + currentQuestion + "/" + nbCards;
            numberCardsDisplay.setText(temp);
            userAnswer.setText("");
        }
        else {// There are no more questions left
            Intent intentFinal = new Intent(getApplicationContext(),FinalActivity.class);
            intentFinal.putExtra(KEY_FINAL,answers);
            startActivity(intentFinal);
        }
    }
    public void setQuestionLayout(){
        questionLayout = new ConstraintLayout(this);
        mainLayout.addView(questionLayout);
        questionLayout.setId(View.generateViewId());
        ConstraintLayout.LayoutParams layoutParamsQuestionLayout = (ConstraintLayout.LayoutParams) questionLayout.getLayoutParams();
        layoutParamsQuestionLayout.topToTop = R.id.main;
        layoutParamsQuestionLayout.endToEnd = R.id.main;
        layoutParamsQuestionLayout.bottomToBottom = R.id.main;
        layoutParamsQuestionLayout.startToStart = R.id.main;
        questionLayout.setLayoutParams(layoutParamsQuestionLayout);
    }
    public void setQuestionDisplay(){
        questionDisplay = new TextView(this);
        questionLayout.addView(questionDisplay);
        questionDisplay.setId(View.generateViewId());
        questionDisplay.setText(fileContent[currentQuestion].getFrench()); // A CHANGER POUR POUVOIR AFFICHER LA LANGUE ETRANGERE AUSSI
        questionDisplay.setTextSize(20);
        ConstraintLayout.LayoutParams layoutParamsQuestion = (ConstraintLayout.LayoutParams) questionDisplay.getLayoutParams();
        layoutParamsQuestion.startToStart = questionLayout.getId();
        layoutParamsQuestion.topToTop = questionLayout.getId();
        questionDisplay.setLayoutParams(layoutParamsQuestion);
    }
    public void setNumberDisplay(){
        numberCardsDisplay = new TextView(this);
        questionLayout.addView(numberCardsDisplay);
        numberCardsDisplay.setId(View.generateViewId());
        String temp = "   "+currentQuestion+"/"+nbCards;
        numberCardsDisplay.setText(temp);
        numberCardsDisplay.setTextSize(20);
        ConstraintLayout.LayoutParams layoutParamsNumberDisplay = (ConstraintLayout.LayoutParams) numberCardsDisplay.getLayoutParams();
        layoutParamsNumberDisplay.endToEnd = questionLayout.getId();
        layoutParamsNumberDisplay.topToTop = questionLayout.getId();
        layoutParamsNumberDisplay.startToEnd = questionDisplay.getId();
        numberCardsDisplay.setLayoutParams(layoutParamsNumberDisplay);
    }
    public void setUserAnswer(){
        userAnswer = new EditText(this);
        userAnswer.setId(View.generateViewId());
        userAnswer.setHint(R.string.hint_answer);
        questionLayout.addView(userAnswer);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) userAnswer.getLayoutParams();
        params.setMargins(0, 0, 0, 50);
        params.startToStart = questionLayout.getId();
        params.topToBottom = numberCardsDisplay.getId();
        userAnswer.setLayoutParams(params);
    }
    public void setRecordAnswer(){
        recordAnswer = new Bouton(this,questionLayout,this::getNextQuestion,"Valider",R.drawable.bg_button_gold);
        ConstraintLayout.LayoutParams layoutParamsAnswer = (ConstraintLayout.LayoutParams) recordAnswer.getLayoutParams();
        layoutParamsAnswer.endToEnd = questionLayout.getId();
        layoutParamsAnswer.topToBottom = userAnswer.getId();
        layoutParamsAnswer.startToStart = questionLayout.getId();
        layoutParamsAnswer.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParamsAnswer.setMargins(0,50,0,0);
        recordAnswer.setLayoutParams(layoutParamsAnswer);
    }

    public int min(int a, int b){
        if (a<b){return a;}
        else{return b;}
    }
}
