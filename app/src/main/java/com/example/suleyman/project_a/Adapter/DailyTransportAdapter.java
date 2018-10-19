package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suleyman.project_a.Model.DailyTransport;
import com.example.suleyman.project_a.R;

import java.util.ArrayList;

public class DailyTransportAdapter extends BaseAdapter{

    ArrayList<DailyTransport> objects;
    Context context;

    public DailyTransportAdapter(ArrayList<DailyTransport> objects, Context context) {
        this.objects = objects;
        this.context = context;
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class  MyViewHolder
    {
        TextView cargoName;
        TextView transport2016;
        TextView transport2017;
        TextView difference;
        ImageView imageView;

        public MyViewHolder(View v)
        {
            cargoName = (TextView) v.findViewById(R.id.mehsul);
            transport2016  = (TextView) v.findViewById(R.id.txt_2016);
            transport2017 = (TextView) v.findViewById(R.id.txt_2017);
            difference = (TextView) v.findViewById(R.id.ferqTxt);
            imageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        MyViewHolder holder2 = null;
        LayoutInflater inflater = LayoutInflater.from(context);


        if(row == null) {
            row = inflater.inflate(R.layout.popup_listview_item, viewGroup, false);
            holder2 = new MyViewHolder(row);
            row.setTag(holder2);
        }

        else
        {
            holder2 = (MyViewHolder) row.getTag();
        }

        holder2.cargoName.setText(objects.get(i).getCargoName());
        holder2.transport2016.setText(String.valueOf(objects.get(i).getTransport2016()));
        holder2.transport2017.setText(String.valueOf(objects.get(i).getTransport2017()));
        holder2.difference.setText(String.valueOf(objects.get(i).getDifference()));

        if(objects.get(i).getDifference()>0)
        {
            holder2.imageView.setImageResource(R.drawable.up_arrow);
        }

        else
        {
            holder2.imageView.setImageResource(R.drawable.down_arrow);
        }



        return  row;
    }
}
