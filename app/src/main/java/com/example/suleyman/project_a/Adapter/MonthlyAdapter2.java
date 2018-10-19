package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.suleyman.project_a.Daily_R;
import com.example.suleyman.project_a.Model.MonthlyPopupModel;
import com.example.suleyman.project_a.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Art Servis on 1/28/2018.
 */

public class MonthlyAdapter2 extends ArrayAdapter<MonthlyPopupModel> {


    Context context;
    List<MonthlyPopupModel> myList;

    public MonthlyAdapter2(Context context, int resource, List<MonthlyPopupModel> objects) {
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
    public MonthlyPopupModel getItem(int position) {
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

        MonthlyAdapter2.Holder holder;

        //If the listview does not have an xml layout ready set the layout
        if (convertView == null){

            //we need a new holder to hold the structure of the cell
            holder = new MonthlyAdapter2.Holder();

            //get the XML inflation service
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //Inflate our xml cell to the convertView
            convertView = inflater.inflate(R.layout.popup_listview_item2, null);

            //Get xml components into our holder class
            holder.qnqTxt = (TextView)convertView.findViewById(R.id.mehsul);
            holder.firstYearTxt = (TextView) convertView.findViewById(R.id.firstYear);
            holder.secondYearTxt = (TextView) convertView.findViewById(R.id.secondYear);
            holder.adyFirstYearTxt = (TextView) convertView.findViewById(R.id.adyFirstYear);
            holder.adySecondYearTxt = (TextView) convertView.findViewById(R.id.adySecondYear);
            holder.adyDiffTxt = (TextView) convertView.findViewById(R.id.adyDiff);
            holder.ttFirstYearTxt = (TextView) convertView.findViewById(R.id.ttFirstYear);
            holder.ttSecoondYearTxt = (TextView) convertView.findViewById(R.id.ttSecondYear);
            holder.ttDiffTxt = (TextView) convertView.findViewById(R.id.ttDiff);
            holder.cemFirstYearTxt = (TextView) convertView.findViewById(R.id.cemFirstYear);
            holder.cemSecondYearTxt = (TextView) convertView.findViewById(R.id.cemSecondYear);
            holder.cemDiffTxt = (TextView) convertView.findViewById(R.id.cemDiff);
            holder.rootLyt = (RelativeLayout) convertView.findViewById(R.id.rootLyt);

            holder.adyImage = (ImageView)convertView.findViewById(R.id.adyImage);
            holder.ttImage = (ImageView)convertView.findViewById(R.id.ttImage);
            holder.cemImage = (ImageView)convertView.findViewById(R.id.cemImage);
            holder.totalImage = (ImageView)convertView.findViewById(R.id.totalImage);
            //Attach our holder class to this particular cell
            convertView.setTag(holder);

        }else{

            holder = (MonthlyAdapter2.Holder)convertView.getTag();

        }

        MonthlyPopupModel monthlyPopupModel = getItem(position);

        holder.qnqTxt.setText(monthlyPopupModel.getQnq());
        holder.firstYearTxt.setText(monthlyPopupModel.getFirstYear());
        holder.secondYearTxt.setText(monthlyPopupModel.getSecondYear());
        holder.adyFirstYearTxt.setText(monthlyPopupModel.getAdyFirstYear());
        holder.adySecondYearTxt.setText(monthlyPopupModel.getAdySecondYear());
        holder.adyDiffTxt.setText(monthlyPopupModel.getAdyDiff());
        holder.ttFirstYearTxt.setText(monthlyPopupModel.getTtFirstYear());
        holder.ttSecoondYearTxt.setText(monthlyPopupModel.getTtSecondYear());
        holder.ttDiffTxt.setText(monthlyPopupModel.getTtDiff());
        holder.cemFirstYearTxt.setText(monthlyPopupModel.getCemFirstYear());
        holder.cemSecondYearTxt.setText(monthlyPopupModel.getCemSecondYear());
        holder.cemDiffTxt.setText(monthlyPopupModel.getCemDiff());

        if(position%2 == 0)
        {
            convertView.setBackgroundColor(Color.parseColor("#34935C22"));
        }
        if(position%2 !=0)
        {
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }

        if (monthlyPopupModel.getTotalImgUrl().toString().equals("-1")   ) {

            Picasso.with(context).load(R.drawable.down_arrow).fit().into(holder.totalImage);
        }
        if(monthlyPopupModel.getTotalImgUrl().toString().equals("1"))
        {
            Picasso.with(context).load(R.drawable.up_arrow).fit().into(holder.totalImage);
        }
        if(monthlyPopupModel.getTotalImgUrl().toString().equals("0"))
        {
            Picasso.with(context).load(R.drawable.neytral_icon).fit().into(holder.totalImage);
        }
        if (monthlyPopupModel.getAdyImgUrl().toString().equals("-1")   ) {

            Picasso.with(context).load(R.drawable.down_arrow).fit().into(holder.adyImage);
        }
        if(monthlyPopupModel.getAdyImgUrl().toString().equals("1"))
        {
            Picasso.with(context).load(R.drawable.up_arrow).fit().into(holder.adyImage);
        }
        if(monthlyPopupModel.getAdyImgUrl().toString().equals("0"))
        {
            Picasso.with(context).load(R.drawable.neytral_icon).fit().into(holder.adyImage);
        }

        if (monthlyPopupModel.getTtImgUrl().toString().equals("-1")   ) {

            Picasso.with(context).load(R.drawable.down_arrow).fit().into(holder.ttImage);
        }
        if(monthlyPopupModel.getTtImgUrl().toString().equals("1"))
        {
            Picasso.with(context).load(R.drawable.up_arrow).fit().into(holder.ttImage);
        }
        if (monthlyPopupModel.getTtImgUrl().toString().equals("0")   ) {

            Picasso.with(context).load(R.drawable.neytral_icon).fit().into(holder.ttImage);
        }
        if (monthlyPopupModel.getCemImgUrl().toString().equals("-1")   ) {

            Picasso.with(context).load(R.drawable.down_arrow).fit().into(holder.cemImage);
        }
        if(monthlyPopupModel.getCemImgUrl().toString().equals("1"))
        {
            Picasso.with(context).load(R.drawable.up_arrow).fit().into(holder.cemImage);
        }
        if (monthlyPopupModel.getCemImgUrl().toString().equals("0")   ) {

            Picasso.with(context).load(R.drawable.neytral_icon).fit().into(holder.cemImage);
        }

        return convertView;
    }

    private class Holder{

        TextView qnqTxt;
        TextView firstYearTxt;
        TextView secondYearTxt;
        TextView adyFirstYearTxt;
        TextView adySecondYearTxt;
        TextView adyDiffTxt;
        TextView ttFirstYearTxt;
        TextView ttSecoondYearTxt;
        TextView ttDiffTxt;
        TextView cemFirstYearTxt;
        TextView cemSecondYearTxt;
        RelativeLayout rootLyt;
        TextView cemDiffTxt;
        ImageView totalImage;
        ImageView adyImage;
        ImageView ttImage;
        ImageView cemImage;

    }

}
