package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.suleyman.project_a.R;

import java.util.ArrayList;

/**
 * Created by Art Servis on 3/29/2018.
 */

public class NewYukInfoAdapter extends ArrayAdapter<String>

{

  private ArrayList<String> objects1, objects2, objects3;
  Context context;

  public NewYukInfoAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<String> objects1, ArrayList<String> objects2, ArrayList<String> objects3) {
    super(context, resource);
    this.context = context;
    this.objects1 = objects1;
    this.objects2 = objects2;
    this.objects3 = objects3;
  }


  @Override
  public int getCount() {
    return objects1.size();
  }


  @Override
  public long getItemId(int i) {
    return 0;
  }

  class MyViewHolder {
    TextView textView1;
    TextView textView2;
    TextView textView3;

    public MyViewHolder(View view) {
      textView1 = (TextView) view.findViewById(R.id.name);
      textView2 = (TextView) view.findViewById(R.id.vagon);
      textView3 = (TextView) view.findViewById(R.id.kont);
    }

  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {

    View currenView = view;
    MyViewHolder holder = null;
    LayoutInflater inflater = LayoutInflater.from(context);

    if (currenView == null) {
      currenView = inflater.inflate(R.layout.yukinfo_list_item, viewGroup, false);
      holder = new MyViewHolder(currenView);
      currenView.setTag(holder);
    } else {
      holder = (MyViewHolder) currenView.getTag();
    }

    holder.textView1.setText(objects1.get(i));
    holder.textView2.setText(objects2.get(i));
    holder.textView3.setText(objects3.get(i));

    return currenView;

  }
}



