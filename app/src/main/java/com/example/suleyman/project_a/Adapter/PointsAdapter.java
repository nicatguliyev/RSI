package com.example.suleyman.project_a.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.suleyman.project_a.Model.DailyTransport;
import com.example.suleyman.project_a.Model.Points;
import com.example.suleyman.project_a.R;

import java.util.ArrayList;

/**
 * Created by Art Servis on 3/7/2018.
 */

public class PointsAdapter  extends BaseAdapter{

    ArrayList<Points> objects;
    Context context;

    public PointsAdapter(ArrayList<Points> objects, Context context) {
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);

        view = inflater.inflate(R.layout.point_list_item, viewGroup, false);

        ((TextView) view.findViewById(R.id.idxalDolu)).setText(objects.get(i).getIdxalDolu());
        ((TextView)view.findViewById(R.id.idxalBos)).setText(objects.get(i).getIdxalBos());
        ((TextView)view.findViewById(R.id.idxalKont)).setText(objects.get(i).getIdxalKont());
        ((TextView)view.findViewById(R.id.idxalCem)).setText(objects.get(i).getIdxalCem());
        ((TextView)view.findViewById(R.id.ixracDolu)).setText(objects.get(i).getIxracDolu());
        ((TextView)view.findViewById(R.id.ixracBos)).setText(objects.get(i).getIxracBos());
        ((TextView)view.findViewById(R.id.ixracKont)).setText(objects.get(i).getIxracKont());
        ((TextView)view.findViewById(R.id.ixracCem)).setText(objects.get(i).getIxracCem());
        ((TextView)view.findViewById(R.id.tranzitDolu)).setText(objects.get(i).getTranzitDolu());
        ((TextView)view.findViewById(R.id.tranzitBos)).setText(objects.get(i).getTranzitBos());
        ((TextView)view.findViewById(R.id.tranzitKont)).setText(objects.get(i).getTranzitKont());
        ((TextView)view.findViewById(R.id.tranzitCem)).setText(objects.get(i).getTranzitCem());


        return view;
    }
}
