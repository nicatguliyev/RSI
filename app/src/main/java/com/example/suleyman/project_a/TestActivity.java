package com.example.suleyman.project_a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.suleyman.project_a.Adapter.YukInfoAdapter;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    ArrayList<String> Marsrutlar;
    ArrayList<String> MarsrutDeyerler;
    YukInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ListView listView = (ListView) findViewById(R.id.masrutList);

        Marsrutlar = new ArrayList<>();
        Marsrutlar.add("BoukKesik - Elet");
        Marsrutlar.add("BoukKedsfsik - Eldfget");
        Marsrutlar.add("BoukKdfgesik - Eldfgdfet");
        Marsrutlar.add("BoukKdfgdfesik - Edfgdfgdfglet");
        Marsrutlar.add("BoukKesdfgdfgik - Elet");

        MarsrutDeyerler = new ArrayList<>();
        MarsrutDeyerler.add("32.263,00 / 2fdg8%");
        MarsrutDeyerler.add("32.263dfg,00 / 28%");
        MarsrutDeyerler.add("32.2dfg63,00 / 2dfg8%");
        MarsrutDeyerler.add("32.26dfg3,00 / 2dfg8%");
        MarsrutDeyerler.add("32.2fdg63,00 / 2fd8%");

        adapter = new YukInfoAdapter(this, R.layout.yukinfo_listview_item, Marsrutlar, MarsrutDeyerler);

        listView.setAdapter(adapter);


    }
}
