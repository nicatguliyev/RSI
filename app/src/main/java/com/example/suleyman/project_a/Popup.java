package com.example.suleyman.project_a;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Popup extends AppCompatActivity {

    TextView txt_fakt_;
    TextView txt_fakt_16_;
    TextView txt_plan_;
    TextView txt_faiz_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_list_desg);
        Typeface tf_lbl = Typeface.createFromAsset(getAssets(), "fonts/Cera PRO.ttf");

        txt_fakt_=(TextView) findViewById(R.id.txt_fakt_16);
        txt_fakt_16_=(TextView) findViewById(R.id.txt_fakt_16);
        txt_plan_=(TextView) findViewById(R.id.txt_plan);
        txt_faiz_=(TextView) findViewById(R.id.txt_faiz);
        txt_fakt_.setTypeface(tf_lbl);
        txt_fakt_16_.setTypeface(tf_lbl);
        txt_plan_.setTypeface(tf_lbl);
        txt_faiz_.setTypeface(tf_lbl);
    }
}
