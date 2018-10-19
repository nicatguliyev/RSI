package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.suleyman.project_a.Model.Expenses;
import com.example.suleyman.project_a.R;

import java.util.ArrayList;

/**
 * Created by Art Servis on 11/26/2017.
 */

public class CustomAdapter  extends BaseAdapter {

    ArrayList<Expenses> objects;
    int type;
    Context context;

    public CustomAdapter(Context context, ArrayList<Expenses> objects)
    {
        this.objects = objects;
        this.context = context;
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {

        if(objects.get(position).getExpenseName().equals("Ady Dasima"))
        {
            type = 0;
        }
        else
        {
            type = 1;
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    class  MyViewHolder
    {

        TextView nameTxt;
        TextView buyingPriceTxt;
        TextView sellingPriceTxt;

        public MyViewHolder(View v)
        {
            nameTxt = (TextView) v.findViewById(R.id.expenseNameTxt);
            buyingPriceTxt  = (TextView) v.findViewById(R.id.buyPriceTxt);
            sellingPriceTxt = (TextView) v.findViewById(R.id.sellPriceTxt);
        }

    }

    class  MyViewHolder2
    {

        TextView nameTxt;
        TextView buyingMpsTxt;
        TextView buyingSpsTxt;
        TextView sellingMpsTxt;
        TextView sellingSpsTxt;

        public MyViewHolder2(View v)
        {
            nameTxt = (TextView) v.findViewById(R.id.adyTxt);
            buyingMpsTxt  = (TextView) v.findViewById(R.id.AdyAlisCurrentMpsTxt);
            buyingSpsTxt = (TextView) v.findViewById(R.id.AdyAlisCurrentSpsTxt);
            sellingMpsTxt = (TextView) v.findViewById(R.id.AdySatisCurrentMpsTxt);
            sellingSpsTxt = (TextView) v.findViewById(R.id.AdySatisCurrentSpsTxt);
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        MyViewHolder holder = null;
        MyViewHolder2 holder2 = null;

        LayoutInflater inflater = LayoutInflater.from(context);
        int type = getItemViewType(position);

        if(type == 0)
        {
            if(row == null) {

                row = inflater.inflate(R.layout.new_ady_dasima_row, parent, false);
                holder2 = new MyViewHolder2(row);
                row.setTag(holder2);
            }

            else
            {
                holder2 = (MyViewHolder2) row.getTag();
            }

            holder2.nameTxt.setText(objects.get(position).getExpenseName());
            holder2.buyingMpsTxt.setText(objects.get(position).getBuyingMpsPrice());
            holder2.buyingSpsTxt.setText(objects.get(position).getBuyingSpsPrice());
            holder2.sellingMpsTxt.setText(objects.get(position).getSellingMpsPrice());
            holder2.sellingSpsTxt.setText(objects.get(position).getSellingSpsPrice());

        }

        else
        {
            if(row == null) {

                row = inflater.inflate(R.layout.result_listview_item, parent, false);
                holder = new MyViewHolder(row);
                row.setTag(holder);
            }

            else
            {
                holder = (MyViewHolder) row.getTag();
            }


            holder.nameTxt.setText(objects.get(position).getExpenseName());
            holder.buyingPriceTxt.setText(objects.get(position).getBuyyingPrice());
            holder.sellingPriceTxt.setText(objects.get(position).getSellingPrice());

        }


        return  row;
    }
}
