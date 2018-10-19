package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.suleyman.project_a.Model.QnqModel;
import com.example.suleyman.project_a.R;

import java.util.ArrayList;

/**
 * Created by Art Servis on 6/25/2018.
 */

public class QnqAdapter extends BaseAdapter {

    ArrayList<QnqModel> myQnqList;
    Context context;


    public QnqAdapter(ArrayList<QnqModel> myQnqList, Context context)
    {
        this.myQnqList = myQnqList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return myQnqList.size();
    }

    @Override
    public Object getItem(int position) {
        return myQnqList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        convertView  = inflater.inflate(R.layout.custom_qnq_list_item, parent, false);

        TextView codeTxt = (TextView) convertView.findViewById(R.id.codeTxt);
        TextView nameTxt = (TextView) convertView.findViewById(R.id.nameTxt);
        RelativeLayout layout = (RelativeLayout) convertView.findViewById(R.id.itemView);


        codeTxt.setText(myQnqList.get(position).getQnqCode());
        nameTxt.setText(myQnqList.get(position).getQnqName());

        convertView.setTag(String.valueOf(position));

        return convertView;
    }
}