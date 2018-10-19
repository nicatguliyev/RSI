package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.suleyman.project_a.R;

import java.util.ArrayList;

/**
 * Created by Art Servis on 12/3/2017.
 */

public class FirmAdapter extends ArrayAdapter<String> {

    private ArrayList<String> objects1, objects2;
    Context context;

    public FirmAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<String> objects1, ArrayList<String> objects2) {
        super(context, resource);
        this.context = context;
        this.objects1 = objects1;
        this.objects2 = objects2;
    }


    @Override
    public int getCount() {
        return objects1.size();
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    class MyViewHolder
    {
        TextView textView1;
        TextView textView2;

        public MyViewHolder(View view)
        {
            textView1 = (TextView) view.findViewById(R.id.marsrutTxt);
            textView2 = (TextView) view.findViewById(R.id.valueTxt);
        }

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View currenView = view;
        MyViewHolder holder = null;
        LayoutInflater inflater = LayoutInflater.from(context);

        if(currenView == null)
        {
            currenView = inflater.inflate(R.layout.sirket_list_item, viewGroup, false);
            holder = new MyViewHolder(currenView);
            currenView.setTag(holder);
        }
        else
        {
            holder = (MyViewHolder) currenView.getTag();
        }

        holder.textView1.setText(objects1.get(i));
        holder.textView2.setText(objects2.get(i));

        return  currenView;

    }
}
