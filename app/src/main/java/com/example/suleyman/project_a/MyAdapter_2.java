package com.example.suleyman.project_a;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

/**
 * Created by shalala.aghalarova on 7/30/2017.
 */

public class MyAdapter_2 extends ArrayAdapter<Daily_R> {


    Context context;
    List<Daily_R> myList;

    public MyAdapter_2(Context context, int resource, List<Daily_R> objects) {
        super(context, resource, objects);

        this.context = context;
        this.myList = objects;
    }


    @Override
    public int getCount() {
        if(myList != null)
            return myList.size();
        return 0;
    }

    @Override
    public Daily_R getItem(int position) {
        if(myList != null)
            return myList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(myList != null)
            return myList.get(position).hashCode();
        return 0;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;

        //If the listview does not have an xml layout ready set the layout
        if (convertView == null){

            //we need a new holder to hold the structure of the cell
            holder = new Holder();

            //get the XML inflation service
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Inflate our xml cell to the convertView

            if ((context.getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK) ==
                    Configuration.SCREENLAYOUT_SIZE_XLARGE
                    ||
                    (context.getResources().getConfiguration().screenLayout &
                            Configuration.SCREENLAYOUT_SIZE_MASK) ==
                            Configuration.SCREENLAYOUT_SIZE_LARGE
                    ){

                convertView = inflater.inflate(R.layout.new_dailylist_forlarge, null);


            }
            else
            {
                convertView = inflater.inflate(R.layout.daily_list, null);

            }

            //Get xml components into our holder class
            holder.txtday = (TextView)convertView.findViewById(R.id.txt_day);

            holder.txt2016 = (TextView)convertView.findViewById(R.id.total2016);
            holder.txt2017 = (TextView)convertView.findViewById(R.id.total2017);
            holder.txtdiff = (TextView)convertView.findViewById(R.id.txt_diff);
        //    holder.txtper = (TextView)convertView.findViewById(R.id.txt_per);

            holder.imageView = (ImageView)convertView.findViewById(R.id.country_icon);

            //Attach our holder class to this particular cell
            convertView.setTag(holder);

        }else{

            holder = (Holder)convertView.getTag();

        }

        Daily_R Daily_R = getItem(position);

        holder.txtday.setText(Daily_R.getDay_());
        holder.txt2016.setText(Daily_R.getT2016_());
        holder.txt2017.setText(Daily_R.getT2017_());
        holder.txtdiff.setText(Daily_R.getDiff_());


       /* if(Daily_R.getMakeGreen().equals("ok1"))
        {
            holder.txt2016.setBackgroundColor(Color.GREEN);
        }
        else if(Daily_R.getMakeGreen().equals("no1"))
        {
            holder.txt2016.setBackgroundColor(Color.TRANSPARENT);
        }
        else if(Daily_R.getMakeGreen().equals("ok2"))
        {
            holder.txt2017.setBackgroundColor(Color.GREEN);
        }
        else if(Daily_R.getMakeGreen().equals("no2"))
        {
            holder.txt2017.setBackgroundColor(Color.TRANSPARENT);
        }
*/

       if(position == 0)
       {
           convertView.setVisibility(View.GONE);
       }

       if(position!=0)
       {
           convertView.setVisibility(View.VISIBLE);
       }

if (Daily_R.getImageUrl().toString().equals("-1")   ) {

    Picasso.with(context).load(R.drawable.down_arrow).fit().into(holder.imageView);
}
else
{
    Picasso.with(context).load(R.drawable.up_arrow).fit().into(holder.imageView);
}

        return convertView;
    }

    private class Holder{

        TextView txtday;
        TextView txt2016;
        TextView txt2017;
        TextView txtdiff;
        ImageView imageView;

    }
}