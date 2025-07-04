package com.example.quiz.widget;

import static java.lang.Integer.parseInt;

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
}
