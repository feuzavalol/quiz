package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz.widget.Word;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Vector;

public class TrainingActivity extends AppCompatActivity {
    Word[] fileContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_training);

        Intent caller = getIntent();
        String fileName = caller.getStringExtra(LaunchActivity.KEY_NAME_FILE);
        InputStream csvData = getResources().openRawResource(LaunchActivity.getResId(fileName,R.raw.class));
        try {
            fileContent = readCSV(csvData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assert(fileContent != null);

    }
    protected Word[] readCSV(InputStream csvData) throws IOException {
        String[] line;
        CSVReader reader = new CSVReader(new InputStreamReader(csvData));
        Vector<Word> temp = new Vector<>();
        while ((line = reader.readNext()) != null) {
            temp.add(new Word(line[0],line[1],line[2]));
        }
        return Arrays.copyOf(temp.toArray(), temp.toArray().length, Word[].class);
    }
}
