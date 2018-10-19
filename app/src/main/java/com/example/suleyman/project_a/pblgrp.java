package com.example.suleyman.project_a;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;


public class pblgrp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public pblgrp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pblgrp, container, false);
        String[] category = {"Last 24 hours"};
        String[] category1 = {"Line chart"};
        String[] category2 = {"Troy Ounce"};;
        RelativeLayout rel = (RelativeLayout) view.findViewById(R.id.relgraph);
        rel.setBackgroundColor(Color.rgb(23, 32, 42));
        TextView txt1 = (TextView) view.findViewById(R.id.textView4);
        txt1.setTextColor(Color.rgb(179, 182, 183));
        TextView txt2 = (TextView) view.findViewById(R.id.textView5);
        txt2.setTextColor(Color.rgb(179, 182, 183));
        TextView txt3 = (TextView) view.findViewById(R.id.textView6);
        txt3.setTextColor(Color.rgb(179, 182, 183));
        Button create = (Button) view.findViewById(R.id.crt);
        create.setBackgroundColor(Color.rgb(23, 32, 42));
        final Button btn1 = (Button) view.findViewById(R.id.btn1);
        final Button btn2 = (Button) view.findViewById(R.id.btn2);
        final Button btn3 = (Button) view.findViewById(R.id.btn3);
        final Button btn4 = (Button) view.findViewById(R.id.btn4);
        final Button btn5 = (Button) view.findViewById(R.id.btn5);
        final Button btn6 = (Button) view.findViewById(R.id.btn6);
        final Button btn7 = (Button) view.findViewById(R.id.btn7);
        final Button btn8 = (Button) view.findViewById(R.id.btn8);
        final Button btn9 = (Button) view.findViewById(R.id.btn9);
        final Button btn10 = (Button) view.findViewById(R.id.btn10);
        final Button btn11 = (Button) view.findViewById(R.id.btn11);
        final Button btn12 = (Button) view.findViewById(R.id.btn12);
        final Button btn13 = (Button) view.findViewById(R.id.btn13);
        final Button btn14 = (Button) view.findViewById(R.id.btn14);
        final Button btn15 = (Button) view.findViewById(R.id.btn15);
        final Button btn16 = (Button) view.findViewById(R.id.btn16);
        final Button btn17 = (Button) view.findViewById(R.id.btn17);
        final Button btn18 = (Button) view.findViewById(R.id.btn18);
        final Button btn19 = (Button) view.findViewById(R.id.btn19);
        final Button btn20 = (Button) view.findViewById(R.id.btn20);
        final Button btn21 = (Button) view.findViewById(R.id.btn21);
        final Button btn22 = (Button) view.findViewById(R.id.btn22);
        final Button btn23 = (Button) view.findViewById(R.id.btn23);
        final Button btn24 = (Button) view.findViewById(R.id.btn24);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setBackgroundColor(Color.rgb(61, 75, 75));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn2.setTextColor(Color.rgb(40, 180, 99));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn3.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn4.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn5.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn6.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn7.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn8.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn9.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn10.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn11.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn12.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn13.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn14.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn15.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn16.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn17.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn18.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn19.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn19.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn20.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn21.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn22.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn23.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));
                btn24.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        btn24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn24.setBackgroundColor(Color.rgb(61, 75, 75));
                btn2.setBackgroundColor(Color.rgb(23, 32, 42));
                btn3.setBackgroundColor(Color.rgb(23, 32, 42));
                btn4.setBackgroundColor(Color.rgb(23, 32, 42));
                btn5.setBackgroundColor(Color.rgb(23, 32, 42));
                btn6.setBackgroundColor(Color.rgb(23, 32, 42));
                btn7.setBackgroundColor(Color.rgb(23, 32, 42));
                btn8.setBackgroundColor(Color.rgb(23, 32, 42));
                btn9.setBackgroundColor(Color.rgb(23, 32, 42));
                btn10.setBackgroundColor(Color.rgb(23, 32, 42));
                btn11.setBackgroundColor(Color.rgb(23, 32, 42));
                btn12.setBackgroundColor(Color.rgb(23, 32, 42));
                btn13.setBackgroundColor(Color.rgb(23, 32, 42));
                btn14.setBackgroundColor(Color.rgb(23, 32, 42));
                btn15.setBackgroundColor(Color.rgb(23, 32, 42));
                btn16.setBackgroundColor(Color.rgb(23, 32, 42));
                btn17.setBackgroundColor(Color.rgb(23, 32, 42));
                btn18.setBackgroundColor(Color.rgb(23, 32, 42));
                btn20.setBackgroundColor(Color.rgb(23, 32, 42));
                btn21.setBackgroundColor(Color.rgb(23, 32, 42));
                btn22.setBackgroundColor(Color.rgb(23, 32, 42));
                btn23.setBackgroundColor(Color.rgb(23, 32, 42));
                btn1.setBackgroundColor(Color.rgb(23, 32, 42));
                btn24.setTextColor(Color.rgb(40, 180, 99));
                btn2.setTextColor(Color.rgb(179, 182, 183));
                btn3.setTextColor(Color.rgb(179, 182, 183));
                btn4.setTextColor(Color.rgb(179, 182, 183));
                btn5.setTextColor(Color.rgb(179, 182, 183));
                btn6.setTextColor(Color.rgb(179, 182, 183));
                btn7.setTextColor(Color.rgb(179, 182, 183));
                btn8.setTextColor(Color.rgb(179, 182, 183));
                btn9.setTextColor(Color.rgb(179, 182, 183));
                btn10.setTextColor(Color.rgb(179, 182, 183));
                btn11.setTextColor(Color.rgb(179, 182, 183));
                btn12.setTextColor(Color.rgb(179, 182, 183));
                btn13.setTextColor(Color.rgb(179, 182, 183));
                btn14.setTextColor(Color.rgb(179, 182, 183));
                btn15.setTextColor(Color.rgb(179, 182, 183));
                btn16.setTextColor(Color.rgb(179, 182, 183));
                btn17.setTextColor(Color.rgb(179, 182, 183));
                btn18.setTextColor(Color.rgb(179, 182, 183));
                btn19.setTextColor(Color.rgb(179, 182, 183));
                btn20.setTextColor(Color.rgb(179, 182, 183));
                btn21.setTextColor(Color.rgb(179, 182, 183));
                btn22.setTextColor(Color.rgb(179, 182, 183));
                btn23.setTextColor(Color.rgb(179, 182, 183));
                btn1.setTextColor(Color.rgb(179, 182, 183));

            }
        });
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 10),
                new DataPoint(1, 20),
                new DataPoint(2, 5),
                new DataPoint(5, 30),
                new DataPoint(6, 25),
                new DataPoint(7, 50),
                new DataPoint(8, 10),
                new DataPoint(20, 120),
        });
        series.setColor(Color.rgb(244, 208, 63));
        graph.addSeries(series);
        PointsGraphSeries<DataPoint> series1 = new PointsGraphSeries<>(new DataPoint[]{
                new DataPoint(20, 120)
        });
        series1.setShape(PointsGraphSeries.Shape.POINT);
        series1.setColor(Color.rgb(244, 208, 63));
        series1.setSize(10);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(150);
        // enable scaling and scrolling
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        graph.addSeries(series1);
        graph.setBackgroundColor(Color.rgb(23, 32, 42));
        graph.getViewport().setMinX(0d);
        graph.getViewport().setMaxX(20d);
        graph.getViewport().setScrollableY(true);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.VERTICAL);
        graph.getGridLabelRenderer().setGridColor(Color.BLACK);
        StaticLabelsFormatter staticLabelsFormatter1 = new StaticLabelsFormatter(graph);
        //staticLabelsFormatter1.setHorizontalLabels(new String[] {"Jan","Fev","Mart","Aprel","May","Iyun","Iyul","Avq","Sen","Okt","Noy","Dek"});
        staticLabelsFormatter1.setVerticalLabels(new String[]{"", "", ""});
        staticLabelsFormatter1.setHorizontalLabels(new String[]{"", "01:00", "", "03:00", "", "05:00", "", "07:00", "", "09:00", ""});
        graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.rgb(179, 182, 183));
        ///graph.getGridLabelRenderer().setVerticalAxisTitle("Ton");
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter1);
        // graph.setTitle("Gold In US Dollar per ounce-(XAUER)");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        return view;
    }
}
