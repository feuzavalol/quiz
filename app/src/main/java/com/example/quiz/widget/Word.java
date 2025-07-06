package com.example.quiz.widget;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Word {
    protected String french;
    protected String other;
    int score;
    public Word(String first,String second,String score){
        french = first;
        other = second;
        this.score = parseInt(score);
    }
    public String getFrench(){return french;}
    public String getOther(){return other;}
    public int getScore(){return score;}
    public void setScore(int newScore){
        this.score = newScore;
    }
    public void format(){
        french = french.replace(';',',');
        other = other.replace(';',',');
    }
    static public Word[] shuffle(Word[] arr){

        List<Word> wordList = Arrays.asList(arr);

        Collections.shuffle(wordList);

        return wordList.toArray(new Word[0]);

    }
}
