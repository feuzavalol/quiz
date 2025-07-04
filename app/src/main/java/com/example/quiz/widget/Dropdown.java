package com.example.quiz.widget;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.quiz.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class Dropdown extends GridLayout {

    EditText edittext;
    TextInputLayout textimputlayout;
    TextInputLayout liste_deroulante;
    AutoCompleteTextView auto_comp;
    ArrayAdapter<String> adapter;

    public Dropdown(Context context, List<String> liste_choix, String hint) {
        super(context);
        // setBackgroundResource();
        setColumnCount(2);
        setId(ViewGroup.generateViewId());

        // creation de la liste a choix
        liste_deroulante = new TextInputLayout(context);
        liste_deroulante.setEndIconActivated(true);
        liste_deroulante.setEndIconMode(TextInputLayout.END_ICON_DROPDOWN_MENU);
        liste_deroulante.setHint(hint);
        auto_comp = new AutoCompleteTextView(context);
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_dropdown_item_1line, liste_choix);
        auto_comp.setAdapter(adapter);
        auto_comp.setThreshold(0);

        // ajout des views
        addView(liste_deroulante);
        liste_deroulante.addView(auto_comp);

        // modification des parametres de layout
        GridLayout.LayoutParams params1 = (GridLayout.LayoutParams) liste_deroulante.getLayoutParams();
        params1.setMargins(50, 20, 0, 20);
        params1.width = 0;
        params1.rowSpec = GridLayout.spec(0);
        params1.columnSpec = GridLayout.spec(0, 3f);
        params1.setGravity(Gravity.FILL);
        liste_deroulante.setLayoutParams(params1);
    }

    public String getItem() {
        return liste_deroulante.getEditText().getText().toString();
    }

    public double getAmount() {
        return Double.parseDouble(edittext.getText().toString());
    }

    public void setItem(String humanName) {
        liste_deroulante.getEditText().setText(humanName);
    }

    public void setAmount(double amount) {
        textimputlayout.getEditText().setText(String.valueOf(amount));
    }
}