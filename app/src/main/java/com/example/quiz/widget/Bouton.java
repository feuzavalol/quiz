package com.example.quiz.widget;

import android.content.Context;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;

import com.example.quiz.R;

public class Bouton extends AppCompatButton {
    public Bouton(Context context, ViewGroup layout, OnClickListener fonction, String texte, int idColor) {
        super(context);
        layout.addView(this);
        setTextColor(getResources().getColor(R.color.white, context.getTheme()));
        setAllCaps(false);
        setId(ViewGroup.generateViewId());
        setText(texte);
        setBackgroundResource(idColor);
        setOnClickListener(fonction);
        setPadding(50, 10, 50, 10);
    }
}
