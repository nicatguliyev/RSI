package com.example.suleyman.project_a;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*import com.example.suleyman.project_a.Adapter.DailyTransportAdapter;
import com.example.suleyman.project_a.Adapter.MonthlyAdapter;*/
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.suleyman.project_a.Adapter.DailyTransportAdapter;
import com.example.suleyman.project_a.Adapter.MonthlyAdapter;
import com.example.suleyman.project_a.Adapter.MonthlyAdapter2;
import com.example.suleyman.project_a.Common.DailyList;
import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Common.MothlyList;
import com.example.suleyman.project_a.Model.DailyTransport;
import com.example.suleyman.project_a.Model.MonthlyModel;
import com.example.suleyman.project_a.Model.MonthlyPopupModel;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import layout.Plan;

/**
 * Created by Art Servis on 11/28/2017.
 */

public class Monthly extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String name_="";
    String pass_="";
    String user_ = "";
    String userId_="";

    View view;
    ListView list;
    Spinner spinner1, spinner2;
    String url = "https://ady.express/PLan.asmx/Monthly_Report";
    String artimUrl = "https://ady.express/PLan.asmx/ayliq_artma";
    String azUrl = "https://ady.express/PLan.asmx/ayliq_azalma";
    TextView firstYearTxt, secondYearTxt;
    TextView cemFirst, cemSecond, diffC;
    TextView illikFirst, illikSecond, illikDiff;
    ImageView country_ioonC, country_ioonI;
    RelativeLayout cemLyt, yekunLyt;
    ProgressBar prg;
    JSONArray Plan = null;
    int userInteraction = 0;

    ImageView closeImg;
    ProgressBar progressBar;
    ImageView doneImd;
    TextView sendingInformationTxt;
    RelativeLayout progressLyt ;
    RelativeLayout okLyt;
    ImageView errorImage;
    RelativeLayout gonderLyt;
    TextView gonderTxt;


    ArrayList<MonthlyModel> monthList = new ArrayList<MonthlyModel>();

    final java.util.Calendar calendar = java.util.Calendar.getInstance();
    int mil_e = calendar.get(java.util.Calendar.YEAR);
    int may_e = calendar.get(Calendar.MONTH);
    int mday = calendar.get(Calendar.DAY_OF_MONTH);



    public static Daily newInstance(String param1, String param2) {
        Daily fragment = new Daily();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            name_ = getArguments().getString("sess_name");
            userId_ = getArguments().getString("sess_id");
            user_ = getArguments().getString("sess_user");
            pass_ = getArguments().getString("sess_pass");

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_monthly, container, false);

        list=(ListView) view.findViewById(R.id.listview);

        spinner1 = (Spinner) view.findViewById(R.id.spinner);
        spinner2 = (Spinner) view.findViewById(R.id.spinner2);


        firstYearTxt = (TextView) view.findViewById(R.id.firstYearTxt);
        secondYearTxt = (TextView) view.findViewById(R.id.secondYearTxt);

        cemFirst = (TextView) view.findViewById(R.id.total2016C);
        cemSecond = (TextView) view.findViewById(R.id.total2017C);
        diffC = (TextView) view.findViewById(R.id.txt_diffC);

        illikFirst = (TextView) view.findViewById(R.id.total2016I);
        illikSecond = (TextView) view.findViewById(R.id.total2017I);
        illikDiff = (TextView) view.findViewById(R.id.txt_diffI);

        country_ioonC = (ImageView) view.findViewById(R.id.country_iconC);
        country_ioonI = (ImageView) view.findViewById(R.id.country_iconI);

        cemLyt = (RelativeLayout) view.findViewById(R.id.cemLyt);
        yekunLyt = (RelativeLayout) view.findViewById(R.id.yekunLyt);

        prg = (ProgressBar) view.findViewById(R.id.monthPrg);

        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        int mil_e = calendar.get(java.util.Calendar.YEAR);


        ArrayAdapter<String> spinnerAdapter;
        final String[] arrayItems_year = {"2016","2017","2018"};

        spinnerAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, arrayItems_year)
        {

            @Override
            public View getDropDownView(int position, View convertView,ViewGroup parent) {
                // TODO Auto-generated method stub

                View view = super.getView(position, convertView, parent);

                TextView text = (TextView)view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub

                View view = super.getView(position, convertView, parent);

                TextView text = (TextView)view.findViewById(android.R.id.text1);
                text.setTextColor(Color.WHITE);

                return view;

            }
        };

        int spinnerPostion = spinnerAdapter.getPosition(String.valueOf(mil_e));

        int spinnerPostion2 = spinnerAdapter.getPosition(String.valueOf(mil_e-1));

        spinner1.setAdapter(spinnerAdapter);

        spinner1.setSelection(spinnerPostion2);

        spinner2.setAdapter(spinnerAdapter);

        spinner2.setSelection(spinnerPostion);

        JsonParse jsonParse = new JsonParse(spinner1.getSelectedItem().toString(), spinner2.getSelectedItem().toString());
        jsonParse.execute();


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(userInteraction==0 || userInteraction==1)
                {
                    userInteraction++;
                }
                else {

                    if (Integer.parseInt(arrayItems_year[i]) > Integer.parseInt(spinner2.getSelectedItem().toString())) {
                        firstYearTxt.setText(spinner2.getSelectedItem().toString());
                        secondYearTxt.setText(arrayItems_year[i]);
                        new JsonParse(spinner2.getSelectedItem().toString(), arrayItems_year[i]).execute();
                    } else {
                        firstYearTxt.setText(arrayItems_year[i]);
                        secondYearTxt.setText(spinner2.getSelectedItem().toString());
                        new JsonParse(arrayItems_year[i], spinner2.getSelectedItem().toString()).execute();
                    }
                }

            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(userInteraction==0 ||userInteraction==1)
                {
                    userInteraction++;
                }

                else {
                    if (Integer.parseInt(arrayItems_year[i]) > Integer.parseInt(spinner1.getSelectedItem().toString())) {
                        firstYearTxt.setText(spinner2.getSelectedItem().toString());
                        secondYearTxt.setText(arrayItems_year[i]);
                        new JsonParse(spinner1.getSelectedItem().toString(), arrayItems_year[i]).execute();
                    } else {
                        new JsonParse(arrayItems_year[i], spinner1.getSelectedItem().toString()).execute();
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        MothlyList.monthlyList = new ArrayList<MonthlyModel>();

        return view;
    }

    private class JsonParse extends AsyncTask<String, String, JSONObject>{

        String firstYear, secondYear;

        public JsonParse(String firstYear, String secondYear)
        {
            this.firstYear = firstYear;
            this.secondYear = secondYear;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prg.setVisibility(View.VISIBLE);
            cemLyt.setVisibility(View.INVISIBLE);
            yekunLyt.setVisibility(View.INVISIBLE);
            list.setVisibility(View.INVISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {


            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("userid",String.valueOf(userId_)));

            JSONObject json = jParser.getJSONFromUrl(url,params);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            prg.setVisibility(View.INVISIBLE);
            cemLyt.setVisibility(View.VISIBLE);
            yekunLyt.setVisibility(View.VISIBLE);
            list.setVisibility(View.VISIBLE);

            firstYearTxt.setText(firstYear);
            secondYearTxt.setText(secondYear);



            String frstPr, secondPr;
            float sumFirstYear=0;
            float sumSecondYear = 0;
            float illikFirstYear = 0;
            float illikSecondYear = 0;

            try {
                DailyList.personList = new ArrayList<>();
                Plan = jsonObject.getJSONArray("data");
                for (int i = 0; i<Plan.length(); i++)
                {
                    JSONObject c = Plan.getJSONObject(i);

                    String frst = c.getString(firstYear);
                    String scnd= c.getString(secondYear);
                    String mnth = c.getString("M");

                    int day = 0;
                    if(mnth.equals("1"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("2"))
                    {
                        if(mil_e%4==0)
                        {day = 29;}

                        else {day =28;}
                    }
                    if(mnth.equals("3"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("4"))
                    {
                        day = 30;
                    }
                    if(mnth.equals("5"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("6"))
                    {
                        day = 30;
                    }
                    if(mnth.equals("7"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("8"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("9"))
                    {
                        day = 30;
                    }
                    if(mnth.equals("10"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("11"))
                    {
                        day = 30;
                    }
                    if(mnth.equals("12"))
                    {
                        day = 31;
                    }

                    Daily_R monthly = new Daily_R();

                    monthly.setDay_(mnth);
                    monthly.setFirstYear(firstYear);
                    monthly.setSecondYear(secondYear);

                    if(firstYear.equals(String.valueOf(mil_e)))
                    {

                        if(mnth.equals(String.valueOf(may_e+1)))
                        {
                            frstPr = changeFormat(Math.round(Float.parseFloat(frst)/mday*day));
                            monthly.setT2016_(frstPr);
                            monthly.setT2017_(scnd);
                            monthly.setMakeGreen("ok1");
                        }
                        else
                        {
                            monthly.setT2016_(changeFormat(Math.round(Float.parseFloat(frst))));
                            monthly.setT2017_(scnd);
                            monthly.setMakeGreen("no1");
                        }
                    }
                    if(secondYear.equals(String.valueOf(mil_e)))
                    {
                        if(mnth.equals(String.valueOf(may_e+1)))
                        {
                            if(scnd.equals(""))
                            {
                                secondPr = changeFormat(Math.round(Float.parseFloat("0")/mday*day));
                            }
                            else
                            {
                                secondPr = changeFormat(Math.round(Float.parseFloat(scnd)/mday*day));
                            }
                            monthly.setT2016_(changeFormat(Math.round(Float.parseFloat(frst))));
                            monthly.setT2017_(secondPr);
                            monthly.setMakeGreen("ok2");
                        }
                        else
                        {
                            if(scnd.equals(""))
                            {
                                monthly.setT2017_(changeFormat(Math.round(Float.parseFloat("0"))));
                            }
                            else
                            {
                                monthly.setT2017_(changeFormat(Math.round(Float.parseFloat(scnd))));
                            }
                            monthly.setT2016_(changeFormat(Math.round(Float.parseFloat(frst))));
                            monthly.setMakeGreen("no2");
                        }

                    }
                    if(!secondYear.equals(String.valueOf(mil_e)) && !firstYear.equals(String.valueOf(mil_e)))
                    {
                        monthly.setMakeGreen("koko");
                        if(scnd.equals(""))
                        {
                            monthly.setT2017_(changeFormat(Math.round(Float.parseFloat("0"))));
                        }
                        if(!scnd.equals(""))
                        {
                            monthly.setT2017_(changeFormat(Math.round(Float.parseFloat(scnd))));
                        }

                        if(frst.equals(""))
                        {
                            monthly.setT2016_(changeFormat(Math.round(Float.parseFloat("0"))));
                        }
                        if(!frst.equals(""))
                        {
                            monthly.setT2016_(changeFormat(Math.round(Float.parseFloat(frst))));
                        }

                    }


                    if(!frst.equals("") && scnd.equals("")) {
                        int diff=0;
                        int diffPr=0;
                        if(firstYear.equals(String.valueOf(mil_e)))
                        {

                            if(mnth.equals(String.valueOf(may_e+1)))
                            {
                                frstPr = String.valueOf(Math.round(Float.parseFloat(frst)/mday*day));
                                diff = Math.round(Float.parseFloat("0") - Float.parseFloat(frstPr));
                            }
                            else
                            {
                               diff = Math.round(Float.parseFloat("0") - Float.parseFloat(frst));
                            }

                        }

                        if(secondYear.equals(String.valueOf(mil_e)))
                        {
                            diff = Math.round(Float.parseFloat("0") - Float.parseFloat(frst));
                        }


                        diffPr = Math.round(diff/ Float.parseFloat(frst)*100);
                        monthly.setDiff_(changeFormat(diff) + "/" + String.valueOf(diffPr)+"%");
                        if(diff>0)
                        {
                            monthly.setImageUrl("1");
                        }
                        else
                        {
                            monthly.setImageUrl("-1");
                        }
                    }

                    if(!frst.equals("") && !scnd.equals(""))
                    {
                        int  diff= 0;
                        int diffPr = 0;
                        if(secondYear.equals(String.valueOf(mil_e)))
                        {
                            if(mnth.equals(String.valueOf(may_e+1)))
                            {
                                secondPr = String.valueOf(Math.round(Float.parseFloat(scnd)/mday*day));
                                diff = Math.round(Float.parseFloat(secondPr)) - Math.round(Float.parseFloat(frst));

                            }
                            else
                            {
                                diff = Math.round(Float.parseFloat(scnd))- Math.round(Float.parseFloat(frst));
                            }
                        }
                        else
                        {
                            diff = Math.round(Float.parseFloat(scnd))- Math.round(Float.parseFloat(frst));
                        }

                        diffPr = Math.round((diff)/Float.parseFloat(frst)*100);
                        monthly.setDiff_(changeFormat(diff) + "/" + String.valueOf(diffPr)+ "%");
                        if(diff>0)
                        {
                            monthly.setImageUrl("1");
                        }
                        else
                        {
                            monthly.setImageUrl("-1");
                        }
                    }

                     if(frst.equals("") && scnd.equals("")) {

                        float diff = Float.parseFloat("0") - Float.parseFloat("0");
                        monthly.setDiff_(String.valueOf(diff));
                        if(diff>0)
                        {
                            monthly.setImageUrl("1");
                        }
                        else
                        {
                            monthly.setImageUrl("-1");
                        }

                    }

                    DailyList.personList.add(monthly);

                }

                for (int j=0;j<=may_e;j++)
                {
                    JSONObject c = Plan.getJSONObject(j);

                    String frst = c.getString(firstYear);
                    String scnd= c.getString(secondYear);
                    String mnth = c.getString("M");

                    int day = 0;
                    if(mnth.equals("1"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("2"))
                    {
                        if(mil_e%4==0)
                        {day = 29;}

                        else {day =28;}
                    }
                    if(mnth.equals("3"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("4"))
                    {
                        day = 30;
                    }
                    if(mnth.equals("5"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("6"))
                    {
                        day = 30;
                    }
                    if(mnth.equals("7"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("8"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("9"))
                    {
                        day = 30;
                    }
                    if(mnth.equals("10"))
                    {
                        day = 31;
                    }
                    if(mnth.equals("11"))
                    {
                        day = 30;
                    }
                    if(mnth.equals("12"))
                    {
                        day = 31;
                    }


                    if(firstYear.equals(String.valueOf(mil_e)))
                    {
                        cemFirst.setBackgroundColor(Color.parseColor("#FF045801"));
                        cemSecond.setBackgroundColor(Color.TRANSPARENT);
                        diffC.setBackgroundColor(Color.parseColor("#FF045801"));
                        if(!mnth.equals(String.valueOf(may_e+1)))
                        {
                            sumFirstYear = sumFirstYear + Float.parseFloat(frst);
                            if(!scnd.equals(""))
                            {
                            sumSecondYear = sumSecondYear + Float.parseFloat(scnd);}
                            else
                            {
                                sumSecondYear = sumSecondYear + Float.parseFloat("0");
                            }
                        }
                        else
                        {
                            sumFirstYear = sumFirstYear + Float.parseFloat(String.valueOf(Math.round(Float.parseFloat(frst)/mday*day)));
                            if(!scnd.equals(""))
                            {
                                sumSecondYear = sumSecondYear + Float.parseFloat(scnd);}
                            else
                            {
                                sumSecondYear = sumSecondYear + Float.parseFloat("0");
                            }
                        }
                    }
                    if(secondYear.equals(String.valueOf(mil_e)))
                    {
                        cemSecond.setBackgroundColor(Color.parseColor("#FF045801"));
                        cemFirst.setBackgroundColor(Color.TRANSPARENT);
                        diffC.setBackgroundColor(Color.parseColor("#FF045801"));
                        if(!mnth.equals(String.valueOf(may_e+1)))
                        {
                            sumSecondYear = sumSecondYear + Float.parseFloat(scnd);
                            if(!frst.equals(""))
                            {
                                sumFirstYear = sumFirstYear + Float.parseFloat(frst);}
                            else
                            {
                                sumFirstYear = sumFirstYear + Float.parseFloat("0");
                            }
                        }


                        if(mnth.equals(String.valueOf(may_e+1)))
                        {
                            sumSecondYear = sumSecondYear + Float.parseFloat(String.valueOf(Math.round(Float.parseFloat(scnd)/mday*day)));;
                            if(!scnd.equals(""))
                            {
                                sumFirstYear = sumFirstYear + Float.parseFloat(frst);}
                            else
                            {
                                sumFirstYear = sumFirstYear + Float.parseFloat("0");
                            }
                        }
                    }

                    if(!secondYear.equals(String.valueOf(mil_e)) && !firstYear.equals(String.valueOf(mil_e)))
                    {
                        cemSecond.setBackgroundColor(Color.TRANSPARENT);
                        cemFirst.setBackgroundColor(Color.TRANSPARENT);
                        diffC.setBackgroundColor(Color.TRANSPARENT);
                        if(!frst.equals("") && scnd.equals("")) {
                            sumSecondYear = sumSecondYear + Float.parseFloat("0");
                            sumFirstYear = sumFirstYear + Float.parseFloat(frst);
                        }
                        if(frst.equals("") && !scnd.equals(""))
                        {
                            sumSecondYear = sumSecondYear + Float.parseFloat(scnd);
                            sumFirstYear = sumFirstYear + Float.parseFloat("0");
                        }
                        if(!frst.equals("") && !scnd.equals(""))
                        {
                            sumSecondYear = sumSecondYear + Float.parseFloat(scnd);
                            sumFirstYear = sumFirstYear + Float.parseFloat(frst);
                        }
                        if(frst.equals("") && scnd.equals(""))
                        {
                            sumSecondYear = sumSecondYear + Float.parseFloat("0");
                            sumFirstYear = sumFirstYear + Float.parseFloat("0");
                        }
                    }

                   /* monthly.setSumFirst(String.valueOf(Math.round(sumFirstYear)));
                    monthly.setSumSecond(String.valueOf(Math.round(sumSecondYear)));*/
                    DailyList.sumFirstYear  = String.valueOf(changeFormat(Math.round(sumFirstYear)));
                    DailyList.sumSecondYear = String.valueOf(changeFormat(Math.round(sumSecondYear)));
                    int diffPrC = (int) (Math.round(Math.round(sumSecondYear-sumFirstYear))/sumFirstYear*100);
                    String diff = String.valueOf(Math.round(sumSecondYear-sumFirstYear));
                   // monthly.setSumDiff(diff + "/" + String.valueOf(diffPrC)+"%");
                    DailyList.sumDiff = changeFormat(Math.round(sumSecondYear-sumFirstYear)) + "/" + String.valueOf(diffPrC) + "%";

                    if(!diff.contains("-"))
                    {
                        country_ioonC.setImageResource(R.drawable.up_arrow);
                    }

                    cemFirst.setText(changeFormat(Math.round(sumFirstYear)));
                    cemSecond.setText(changeFormat(Math.round(sumSecondYear)));
                    diffC.setText(changeFormat(Math.round(sumSecondYear-sumFirstYear))+"/"  + String.valueOf(diffPrC) + "%");

                }

                if(firstYear.equals(String.valueOf(mil_e)))
                {
                    for(int j=0; j<=may_e; j++)
                    {
                        JSONObject c = Plan.getJSONObject(j);
                        String frst = c.getString(firstYear);
                        String scnd= c.getString(secondYear);
                        String mnth = c.getString("M");

                        int day = 0;
                        if(mnth.equals("1"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("2"))
                        {
                            if(mil_e%4==0)
                            {day = 29;}

                            else {day =28;}
                        }
                        if(mnth.equals("3"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("4"))
                        {
                            day = 30;
                        }
                        if(mnth.equals("5"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("6"))
                        {
                            day = 30;
                        }
                        if(mnth.equals("7"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("8"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("9"))
                        {
                            day = 30;
                        }
                        if(mnth.equals("10"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("11"))
                        {
                            day = 30;
                        }
                        if(mnth.equals("12"))
                        {
                            day = 31;
                        }


                        if(!mnth.equals(String.valueOf(may_e+1)))
                        {
                            illikFirstYear = illikFirstYear + Float.parseFloat(frst);
                            illikSecondYear = 0;
                        }
                        else
                        {
                            illikFirstYear = illikFirstYear + Float.parseFloat(String.valueOf(Math.round(Float.parseFloat(frst)/mday*day)));
                            illikSecondYear = 0;
                        }

                        int illikDiffPr = Math.round(Math.round(illikSecondYear-illikFirstYear)/illikFirstYear*100);

                        illikFirst.setText(changeFormat(Math.round(illikFirstYear)));
                        illikFirst.setBackgroundColor(Color.parseColor("#FF045801"));
                        illikSecond.setText(changeFormat(Math.round(illikSecondYear)));
                        illikSecond.setBackgroundColor(Color.TRANSPARENT);
                        illikDiff.setText(changeFormat(Math.round(illikSecondYear-illikFirstYear))+ "/" + String.valueOf(illikDiffPr)+ "%");
                        illikDiff.setBackgroundColor(Color.parseColor("#FF045801"));

                    }
                }
                if(secondYear.equals(String.valueOf(mil_e)))
                {
                    for(int j=0; j<=may_e; j++)
                    {
                        JSONObject c = Plan.getJSONObject(j);
                        String frst = c.getString(firstYear);
                        String scnd= c.getString(secondYear);
                        String mnth = c.getString("M");

                        int day = 0;
                        if(mnth.equals("1"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("2"))
                        {
                            if(mil_e%4==0)
                            {day = 29;}

                            else {day =28;}
                        }
                        if(mnth.equals("3"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("4"))
                        {
                            day = 30;
                        }
                        if(mnth.equals("5"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("6"))
                        {
                            day = 30;
                        }
                        if(mnth.equals("7"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("8"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("9"))
                        {
                            day = 30;
                        }
                        if(mnth.equals("10"))
                        {
                            day = 31;
                        }
                        if(mnth.equals("11"))
                        {
                            day = 30;
                        }
                        if(mnth.equals("12"))
                        {
                            day = 31;
                        }


                        if(!mnth.equals(String.valueOf(may_e+1)))
                        {
                            illikSecondYear = illikSecondYear + Float.parseFloat(scnd);
                            illikFirstYear = 0;
                        }
                        else
                        {
                            illikSecondYear = (illikSecondYear + Float.parseFloat(String.valueOf(Math.round(Float.parseFloat(scnd)/mday*day))))/Float.parseFloat(mnth)*12;
                            for(int k= 0; k<Plan.length(); k++)
                            {
                                JSONObject c2 = Plan.getJSONObject(k);
                                String frst2 = c2.getString(firstYear);
                                illikFirstYear = illikFirstYear + Float.parseFloat(frst2);
                            }
                        }

                    }

                    int illikDiffPr = Math.round(Math.round(illikSecondYear-illikFirstYear)/illikFirstYear*100);

                    DailyList.illikFirstYear = changeFormat(Math.round(illikFirstYear));
                    DailyList.illikSecondYear = changeFormat(Math.round(illikSecondYear));
                    DailyList.illikDiff = changeFormat(Math.round(illikSecondYear-illikFirstYear))+ "/" + String.valueOf(illikDiffPr)+ "%";

                    illikFirst.setText(changeFormat(Math.round(illikFirstYear)));
                    illikFirst.setBackgroundColor(Color.TRANSPARENT);
                    illikSecond.setText(changeFormat(Math.round(illikSecondYear)));
                    illikSecond.setBackgroundColor(Color.parseColor("#FF045801"));
                    illikDiff.setText(changeFormat(Math.round(illikSecondYear-illikFirstYear))+ "/" + String.valueOf(illikDiffPr)+ "%");
                    illikDiff.setBackgroundColor(Color.parseColor("#FF045801"));
                }

                if(!firstYear.equals(String.valueOf(mil_e)) && !secondYear.equals(String.valueOf(mil_e)))
                {
                    if(Integer.parseInt(firstYear)<mil_e && Integer.parseInt(secondYear)<mil_e)
                    {
                        for(int k= 0; k<Plan.length(); k++)
                        {
                            JSONObject c = Plan.getJSONObject(k);
                            String frst = c.getString(firstYear);
                            String scnd= c.getString(secondYear);
                            String mnth = c.getString("M");



                            illikFirstYear = illikFirstYear + Float.parseFloat(frst);
                            illikSecondYear = illikSecondYear + Float.parseFloat(scnd);
                        }
                    }

                    if(Integer.parseInt(firstYear)<mil_e && Integer.parseInt(secondYear)>mil_e)
                    {
                        for(int k= 0; k<Plan.length(); k++)
                        {
                            JSONObject c = Plan.getJSONObject(k);
                            String frst = c.getString(firstYear);
                            String scnd= c.getString(secondYear);
                            String mnth = c.getString("M");

                            illikFirstYear = illikFirstYear + Float.parseFloat(frst);
                            illikSecondYear = 0;
                        }
                    }

                    int illikDiffPr = Math.round(Math.round(illikSecondYear-illikFirstYear)/illikFirstYear*100);

                    illikFirst.setText(changeFormat(Math.round(illikFirstYear)));
                    illikFirst.setBackgroundColor(Color.TRANSPARENT);
                    illikSecond.setText(changeFormat(Math.round(illikSecondYear)));
                    illikSecond.setBackgroundColor(Color.TRANSPARENT);
                    illikDiff.setText(changeFormat(Math.round(illikSecondYear-illikFirstYear))+ "/" + String.valueOf(illikDiffPr)+ "%");
                    illikDiff.setBackgroundColor(Color.TRANSPARENT);

                    if(illikSecondYear>illikFirstYear)
                    {
                        country_ioonI.setImageResource(R.drawable.up_arrow);
                    }

                }

                if(getActivity()!=null) {


                    if ((getResources().getConfiguration().screenLayout &
                            Configuration.SCREENLAYOUT_SIZE_MASK) ==
                            Configuration.SCREENLAYOUT_SIZE_XLARGE
                            ||
                            (getResources().getConfiguration().screenLayout &
                                    Configuration.SCREENLAYOUT_SIZE_MASK) ==
                                    Configuration.SCREENLAYOUT_SIZE_LARGE
                            ) {
                        MonthlyAdapter adapter2 = new MonthlyAdapter(getActivity(), R.layout.new_dailylist_forxlarge, DailyList.personList);
                        list.setAdapter(adapter2);
                    }
                    else
                    {
                        MonthlyAdapter adapter2 = new MonthlyAdapter(getActivity(), R.layout.new_daily_list, DailyList.personList);
                        list.setAdapter(adapter2);
                    }


                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            if(!DailyList.personList.get(i).getDiff_().contains("-")){
                            PopupJsonArtma popupJsonArtma = new PopupJsonArtma(firstYear, secondYear, String.valueOf(i+1));
                            popupJsonArtma.execute();}
                            else
                            {
                                PopupJsonAzalma popupJsonAzalma = new PopupJsonAzalma(firstYear, secondYear, String.valueOf(i+1));
                                popupJsonAzalma.execute();
                            }

                        }
                    });

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    public class PopupJsonArtma extends AsyncTask<String, String, JSONObject>{

        String firstYear;
        String secondYear;
        String month;
        ListView popuupListView;
        ArrayList<MonthlyPopupModel> myList = new ArrayList<>();
        ProgressBar test;

       public PopupJsonArtma(String firstYear, String SecondYear, String month)
       {
           this.firstYear = firstYear;
           this.secondYear = SecondYear;
           this.month = month;
       }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String ay = null;

            if(month.equals("1"))
            {
                ay = "Yanvar";
            }

            else if(month.equals("2"))
            {
                ay = "Fevral";
            }

            else if(month.equals("3"))
            {
                ay = "Mart";
            }

            else if(month.equals("4"))
            {
                ay = "Aprel";
            }

            else if(month.equals("5"))
            {
                ay = "May";
            }

            else if(month.equals("6"))
            {
                ay = "Iyun";
            }

            else if(month.equals("7"))
            {
                ay = "Iyul";
            }

            else if(month.equals("8"))
            {
                ay = "Avqust";
            }

            else if(month.equals("9"))
            {
                ay = "Sentyabr";
            }

            else if(month.equals("10"))
            {
                ay = "Oktyabr";
            }

            else  if(month.equals("11"))
            {
                ay = "Noyabr";
            }

            else if(month.equals("12"))
            {
                ay = "Dekabr";
            }

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;


            final Dialog dialog =  new Dialog(getActivity());
            View child = getActivity().getLayoutInflater().inflate(R.layout.daily_listview_popup, null);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(child);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(width, height);
            dialog.show();

            TextView informationTxt = (TextView) dialog.findViewById(R.id.informationTxt);
            TextView dateTxt = (TextView) dialog.findViewById(R.id.dateTxt);
            ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
            test = (ProgressBar) dialog.findViewById(R.id.testBar);

            closeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dateTxt.setText(ay  + " ayı ərzində " );
            informationTxt.setText("artmaya səbəb olan");

            popuupListView = (ListView) dialog.findViewById(R.id.popUpListview);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {

            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("userid",userId_));
            params.add(new BasicNameValuePair("firstyear",firstYear));
            params.add(new BasicNameValuePair("secondyear",secondYear));
            params.add(new BasicNameValuePair("month",month));

            JSONObject json = jParser.getJSONFromUrl(artimUrl,params);
            return json;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            test.setVisibility(View.INVISIBLE);
            JSONArray dataArray = null;
            try {
                dataArray = jsonObject.getJSONArray("data");
                for(int i=0; i<dataArray.length(); i++)
                {
                    String qnq = dataArray.getJSONObject(i).getString("QNQ");
                    String firmaAdy = dataArray.getJSONObject(i).getString("Firma1");
                    String firstYear1 = dataArray.getJSONObject(i).getString("FirstYear1");
                    String firsTon1 = dataArray.getJSONObject(i).getString("FirstTon1");
                    String secondYear1 = dataArray.getJSONObject(i).getString("SecondYear1");
                    String secondTon1 = dataArray.getJSONObject(i).getString("SecondTon1");
                    String firmaTT = dataArray.getJSONObject(i).getString("Fimra2");
                    String firsTon2 = dataArray.getJSONObject(i).getString("FirstTon2");
                    String secondTon2 = dataArray.getJSONObject(i).getString("SecondTon2");
                    String qnqName = dataArray.getJSONObject(i).getString("Column1");

                    String cemFirst = String.valueOf(Integer.parseInt(firsTon1) + Integer.parseInt(firsTon2));
                    String cemSecond = String.valueOf(Integer.parseInt(secondTon1) + Integer.parseInt(secondTon2));
                    String cemDiff =  String.valueOf(Integer.parseInt(cemSecond)-Integer.parseInt(cemFirst));

                    String adyDiff = String.valueOf(Integer.parseInt(secondTon1) - Integer.parseInt(firsTon1));
                    String ttDiff = String.valueOf(Integer.parseInt(secondTon2) - Integer.parseInt(firsTon2));

                    String adyUrl = "1";
                    String ttUrl = "1";
                    String cemUrl = "1";
                    String totalUrl = "1";

                    if(cemDiff.contains("-"))
                    {
                         totalUrl = "-1";
                         cemUrl = "-1";
                    }
                    if(adyDiff.contains("-"))
                    {
                        adyUrl = "-1";
                    }
                    if(ttDiff.contains("-")){
                        ttUrl = "-1";
                    }

                    MonthlyPopupModel monthlyPopupModel = new MonthlyPopupModel(qnq + "-" + qnqName, firstYear1, secondYear1, firsTon1,
                            secondTon1, adyDiff, adyUrl, firsTon2, secondTon2, ttDiff, ttUrl,
                            cemFirst, cemSecond, cemDiff, cemUrl, totalUrl);
                    myList.add(monthlyPopupModel);
                }


                MonthlyAdapter2 monthlyAdapter2 = new MonthlyAdapter2(getActivity(), R.layout.popup_listview_item2, myList);
               // monthlyAdapter2.setNotifyOnChange(true);
                popuupListView.setAdapter(monthlyAdapter2);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    public class PopupJsonAzalma extends AsyncTask<String, String, JSONObject>{

        String firstYear;
        String secondYear;
        String month;
        ListView popuupListView;
        ArrayList<MonthlyPopupModel> myList = new ArrayList<>();
        ProgressBar test;

        public PopupJsonAzalma(String firstYear, String SecondYear, String month)
        {
            this.firstYear = firstYear;
            this.secondYear = SecondYear;
            this.month = month;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String ay = null;

            if(month.equals("1"))
            {
                ay = "Yanvar";
            }

            else if(month.equals("2"))
            {
                ay = "Fevral";
            }

            else if(month.equals("3"))
            {
                ay = "Mart";
            }

            else if(month.equals("4"))
            {
                ay = "Aprel";
            }

            else if(month.equals("5"))
            {
                ay = "May";
            }

            else if(month.equals("6"))
            {
                ay = "Iyun";
            }

            else if(month.equals("7"))
            {
                ay = "Iyul";
            }

            else if(month.equals("8"))
            {
                ay = "Avqust";
            }

            else if(month.equals("9"))
            {
                ay = "Sentyabr";
            }

            else if(month.equals("10"))
            {
                ay = "Oktyabr";
            }

            else  if(month.equals("11"))
            {
                ay = "Noyabr";
            }

            else if(month.equals("12"))
            {
                ay = "Dekabr";
            }

            DisplayMetrics metrics = getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;


            final Dialog dialog =  new Dialog(getActivity());
            View child = getActivity().getLayoutInflater().inflate(R.layout.daily_listview_popup, null);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(child);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setLayout(width, height);
            dialog.show();

            TextView informationTxt = (TextView) dialog.findViewById(R.id.informationTxt);
            TextView dateTxt = (TextView) dialog.findViewById(R.id.dateTxt);
            ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
            test = (ProgressBar) dialog.findViewById(R.id.testBar);

            closeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dateTxt.setText(ay  + " ayı ərzində " );
            informationTxt.setText("azalmaya səbəb olan");

            popuupListView = (ListView) dialog.findViewById(R.id.popUpListview);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {

            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("userid",userId_));
            params.add(new BasicNameValuePair("firstyear",firstYear));
            params.add(new BasicNameValuePair("secondyear",secondYear));
            params.add(new BasicNameValuePair("month",month));

            JSONObject json = jParser.getJSONFromUrl(azUrl,params);
            return json;
        }


        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            test.setVisibility(View.INVISIBLE);
            JSONArray dataArray = null;
            try {
                dataArray = jsonObject.getJSONArray("data");
                for(int i=0; i<dataArray.length(); i++)
                {
                    String qnq = dataArray.getJSONObject(i).getString("QNQ");
                    String firmaAdy = dataArray.getJSONObject(i).getString("Firma1");
                    String firstYear1 = dataArray.getJSONObject(i).getString("FirstYear1");
                    String firsTon1 = dataArray.getJSONObject(i).getString("FirstTon1");
                    String secondYear1 = dataArray.getJSONObject(i).getString("SecondYear1");
                    String secondTon1 = dataArray.getJSONObject(i).getString("SecondTon1");
                    String firmaTT = dataArray.getJSONObject(i).getString("Fimra2");
                    String firsTon2 = dataArray.getJSONObject(i).getString("FirstTon2");
                    String secondTon2 = dataArray.getJSONObject(i).getString("SecondTon2");
                    String qnqName = dataArray.getJSONObject(i).getString("Column1");

                    String cemFirst = String.valueOf(Integer.parseInt(firsTon1) + Integer.parseInt(firsTon2));
                    String cemSecond = String.valueOf(Integer.parseInt(secondTon1) + Integer.parseInt(secondTon2));
                    String cemDiff =  String.valueOf(Integer.parseInt(cemSecond)-Integer.parseInt(cemFirst));

                    String adyDiff = String.valueOf(Integer.parseInt(secondTon1) - Integer.parseInt(firsTon1));
                    String ttDiff = String.valueOf(Integer.parseInt(secondTon2) - Integer.parseInt(firsTon2));

                    String adyUrl = "0";
                    String ttUrl = "0";
                    String cemUrl = "0";
                    String totalUrl = "0";

                    if(cemDiff.contains("-"))
                    {
                        totalUrl = "-1";
                        cemUrl = "-1";
                    }
                    if(!cemDiff.contains("-") && !cemDiff.equals("0"))
                    {
                        totalUrl = "1";
                        cemUrl = "1";
                    }

                    if(adyDiff.contains("-"))
                    {
                        adyUrl = "-1";
                    }
                    if(!adyDiff.contains("-") && !adyDiff.equals("0"))
                    {
                        adyUrl = "1";
                    }
                    if(ttDiff.contains("-")){
                        ttUrl = "-1";
                    }
                    if(!ttDiff.contains("-") && !ttDiff.equals("0")){
                        ttUrl = "1";
                    }

                    MonthlyPopupModel monthlyPopupModel = new MonthlyPopupModel(qnq + "-" + qnqName,  firstYear1, secondYear1, firsTon1,
                            secondTon1, adyDiff, adyUrl, firsTon2, secondTon2, ttDiff, ttUrl,
                            cemFirst, cemSecond, cemDiff, cemUrl, totalUrl);
                    myList.add(monthlyPopupModel);
                }


                MonthlyAdapter2 monthlyAdapter2 = new MonthlyAdapter2(getActivity(), R.layout.popup_listview_item2, myList);
                // monthlyAdapter2.setNotifyOnChange(true);
                popuupListView.setAdapter(monthlyAdapter2);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Intent intent = new Intent(getActivity(), navi_menu3.class);
                    intent.putExtra("sess_name", name_);
                    intent.putExtra("sess_id", userId_);
                    intent.putExtra("sess_user", user_);
                    intent.putExtra("sess_pass" , pass_);
                    intent.putStringArrayListExtra("menular", Menular.menular);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_send)
        {
            final String year1;
            final String year2;

            if(Integer.parseInt(spinner1.getSelectedItem().toString()) > Integer.parseInt(spinner2.getSelectedItem().toString()))
            {
                year1 = spinner2.getSelectedItem().toString();
                year2 = spinner1.getSelectedItem().toString();
            }
            else
            {
                year2 = spinner2.getSelectedItem().toString();
                year1 = spinner1.getSelectedItem().toString();
            }

            final Dialog dialog = new Dialog(getContext());
            View child = getActivity().getLayoutInflater().inflate(R.layout.custom_email_dialog, null);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(child);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            dialog.show();




            final EditText emailEdt = (EditText) dialog.findViewById(R.id.emailEdt);
            gonderLyt = (RelativeLayout) dialog.findViewById(R.id.gonderBtn);
            closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
            progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
            doneImd = (ImageView) dialog.findViewById(R.id.doneImg);
            sendingInformationTxt = (TextView) dialog.findViewById(R.id.sendingInformationTxt);
            progressLyt = (RelativeLayout) dialog.findViewById(R.id.progressLyt);
            okLyt = (RelativeLayout) dialog.findViewById(R.id.okBtn);
            errorImage = (ImageView) dialog.findViewById(R.id.errorImg);
            gonderTxt = (TextView) dialog.findViewById(R.id.gonderTxt);

            closeImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            gonderLyt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!gonderTxt.getText().toString().equals("Bağla"))

                    {
                        SendMail sendMail = new SendMail(year1, year2, emailEdt.getText().toString());
                        sendMail.execute();
                    }

                    else
                    {
                        dialog.dismiss();
                    }

                }
            });

        }

        return  true;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_send).setVisible(true);
    }

    public String changeFormat(int value)
    {
        java.text.DecimalFormat formatter;
        formatter = new java.text.DecimalFormat("#,###,###");

        String formattedNumber = formatter.format(value);
        String newFormat = formattedNumber.replaceAll(",", " ");

        return newFormat;
    }

    public class SendMail extends  AsyncTask<String, String, String>{

        String year1;
        String year2;
        String email;

        public SendMail(String year1, String year2, String email){
            this.year1 = year1;
            this.year2 = year2;
            this.email = email;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            sendingInformationTxt.setText("Göndərilir");
            progressLyt.setVisibility(View.VISIBLE);
            sendingInformationTxt.setVisibility(View.VISIBLE);
            errorImage.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            gonderLyt.setVisibility(View.GONE);
            sendingInformationTxt.setTextColor(Color.GRAY);


        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection;
            BufferedReader reader;

            try {
                URL url = new URL("https://ady.express/plan.asmx/GetMonthlyReport?year1=" + year1 + "&year2=" +
                        year2 + "&email=" + email);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while((line = reader.readLine()) !=  null )
                {
                    buffer.append(line);
                }

               // Log.i("Alindi", buffer.toString());

                return  buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.i("Xeta1", e.toString());

                return "Xeta1";
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("Xeta2", e.toString());

                return  "Xeta2";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.contains("true")){

                doneImd.setVisibility(View.VISIBLE);
                sendingInformationTxt.setText("Göndərildi");
                gonderTxt.setText("Bağla");
                gonderLyt.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                sendingInformationTxt.setTextColor(Color.GREEN);
            }
            if(s.contains("false")){

                errorImage.setVisibility(View.VISIBLE);
                sendingInformationTxt.setText("Göndərilmədi");
                gonderTxt.setText("Yenidən Göndər");
                gonderLyt.setVisibility(View.VISIBLE);
                sendingInformationTxt.setTextColor(Color.RED);
                progressBar.setVisibility(View.INVISIBLE);

            }
        }
    }

}
