package com.example.suleyman.project_a;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;

/**
 * Created by Art Servis on 2/6/2018.
 */

public class IdxalFragment extends Fragment{

    // public static GraphView graphView;
    public  static LineChart graphView;
    public  static ProgressBar idxalPrg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_idxal, container, false);

        graphView = (LineChart) view.findViewById(R.id.idxalGraph);
        idxalPrg = (ProgressBar) view.findViewById(R.id.idxalPrg);

        graphView.setVisibility(View.INVISIBLE);
        idxalPrg.setVisibility(View.INVISIBLE);

       /* StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(IdxalFragment.graphView);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Yan", "Fev", "Mart", "Apr", "May", "Iyn",
                "Iyl", "Avq", "Sent", "Okt", "Noy", "Dek"});
        //staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high", "koko"});
        IdxalFragment.graphView.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);*/

        graphView.setDragEnabled(true);
        graphView.setScaleEnabled(false);

        String[] months = new String[] {"Yan", "Fev", "Mart", "Apr", "May", "Iyun", "Iyul", "Avq", "Sent", "Okt", "Noy", "Dek"};

        XAxis xAxis = graphView.getXAxis();
        xAxis.setValueFormatter(new Formatter(months));
        xAxis.setGranularity(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(12);
        xAxis.setGridColor(Color.parseColor("#FF494949"));
        xAxis.setAxisMinimum(-0.4f);

        graphView.getAxisLeft().setTextColor(Color.parseColor("#FFAFAFAF"));
        graphView.getAxisRight().setEnabled(false);
        graphView.getAxisLeft().setTextSize(13f);
        graphView.getAxisLeft().setSpaceBottom(0);
        graphView.getAxisLeft().setGridColor(Color.parseColor("#FF494949"));
        graphView.getLegend().setTextColor(Color.WHITE);
        graphView.getLegend().setForm(Legend.LegendForm.LINE);
        graphView.getLegend().setFormSize(20f);
        graphView.setScaleEnabled(true);
        graphView.setHighlightPerTapEnabled(false);
        graphView.setHighlightPerDragEnabled(false);
        xAxis.setTextColor(Color.parseColor("#FFAFAFAF"));
        graphView.getDescription().setEnabled(false);
        graphView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        graphView.getRenderer().getPaintRender().setShadowLayer(10,1,1, Color.parseColor("#FF89B4FF"));

        return view;
    }

    public  class Formatter implements IAxisValueFormatter {
        private String[] values;
        public Formatter(String[] values)
        {
            this.values = values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return values[(int) value];
        }
    }
}
