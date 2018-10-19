package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.suleyman.project_a.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Art Servis on 2/2/2018.
 */

public class IntellektualAdapter2 extends SimpleAdapter {

  Context context;
  ArrayList<HashMap<String, String>> data;
  int resource;
  String[] from;
  int[] to;
  int type;

  public IntellektualAdapter2(Context context, List<? extends Map<String, ?>> data, int resource,
                             String[] from, int[] to, int type) {
    super(context, data, resource, from, to);
    this.context = context;
    this.type = type;
  }



  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(context);
    convertView = inflater.inflate(R.layout.preprice_list_item, parent, false);

    TextView tonnajTxt = (TextView) convertView.findViewById(R.id.text5);

    if(type == 1)
    {
      tonnajTxt.setText("CƏMİ TONNAJ: ");
    }
    else
    {
      tonnajTxt.setText("KONTEYNER SAYI: ");
    }

    return super.getView(position, convertView, parent);

    // return convertView;

  }
}
