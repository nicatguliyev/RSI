package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.example.suleyman.project_a.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Art Servis on 2/2/2018.
 */

public class IntellektualAdapter extends SimpleAdapter {

      Context context;
      ArrayList<HashMap<String, String>> data;
      int resource;
      String[] from;
      int[] to;

    public IntellektualAdapter(Context context, List<? extends Map<String, ?>> data, int resource,
                               String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.result_list, parent, false);
        if(position == 0)
        {
            convertView.findViewById(R.id.relativeLayout1).setBackgroundColor(Color.parseColor("#1b4011"));
        }
        return super.getView(position, convertView, parent);

      // return convertView;

    }
}
