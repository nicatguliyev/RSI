package com.example.suleyman.project_a;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suleyman.project_a.Adapter.DailyTransportAdapter;
import com.example.suleyman.project_a.Common.DailyList;
import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Model.DailyTransport;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import layout.Plan;


public class Daily extends Fragment {
 //

    ListView list;
    ProgressBar prg;
    ArrayList<HashMap<String, String>> array_list_ = new ArrayList<HashMap<String, String>>();
    JSONArray Plan = null;
    TextView txt_day, sutkaliq20116, sutkaliq2017, sutkaliq_Diff;
    TextView cem_2016Txt, cem_2017Txt, cem_Diff;
    TextView firstYearTxt, secondYearTxt;
    ImageView sutkaliq_icon, cem_Icon;
    int userInteraction = 0;
    private static String url = "https://ady.express/plan.asmx/Daily_Report";
    JSONParse yearJson;
    JSONParse monthJson;

    ImageView closeImg;
    ProgressBar progressBar;
    ImageView doneImd;
    TextView sendingInformationTxt;
    RelativeLayout progressLyt ;
    RelativeLayout okLyt;
    ImageView errorImage;
    RelativeLayout gonderLyt;
    TextView gonderTxt;

    //JSON Node Names
    private static final String TAG_PLANTRACK = "data";
    private static final String T_MONTH = "M";
    private static final String T_DAY="D";
    private static final String T_ADY_CURR="ADY_CURR";
    private static final String T_ADY_PRW="ADY_PRW";
    private static final String T_DIF="DIF";
    private static final String T_PR="PR";
    public static String GR_AD;
    public static int month_;
    public static int year_;
    Spinner cmb_year;
    public static Spinner spinner;
    Spinner spinner2;

    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String user_="";
    String pass_="";
    String name_="";
    String userId_ = "";

    private OnFragmentInteractionListener mListener;

    public Daily() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Daily newInstance(String param1, String param2) {
        Daily fragment = new Daily();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            user_ = getArguments().getString("sess_user");
            pass_ =  getArguments().getString("sess_pass");
            name_ =  getArguments().getString("sess_name");
            userId_ =  getArguments().getString("sess_id");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_daily, container, false);

        list=(ListView) view.findViewById(R.id.listview);

        firstYearTxt = (TextView) view.findViewById(R.id.firstYearTxt);
        secondYearTxt = (TextView) view.findViewById(R.id.secondYearTxt);


       final java.util.Calendar calendar = java.util.Calendar.getInstance();
       year_ = calendar.get(Calendar.YEAR);
       int currentMonth = calendar.get(Calendar.MONTH);
       month_ = currentMonth + 1;


      Log.i("Name", name_);
        Log.i("Id", userId_);

        ArrayAdapter<String> SpinerAdapter,SpinerAdapter2;
        String[] arrayItems_month = {"Yanvar","Fevral","Mart","Aprel","May","Iyun","Iyul","Avqust","Sentyabr","Oktyabr","Noyabr","Dekabr"};
        final int[] actualValues_month={0,1,2,3,4,5,6,7,8,9,10,11,12};

        SpinerAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, arrayItems_month)
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
        spinner= (Spinner)  view.findViewById(R.id.spinner2);
        spinner.setAdapter(SpinerAdapter);

        DateFormat dateFormat = new SimpleDateFormat("M");
        Date date = new Date();

      //  Toast.makeText(getContext(), String.valueOf(year_), Toast.LENGTH_SHORT).show();

          selectSpinnerValue(spinner,Integer.valueOf(String.valueOf(dateFormat.format(date)))-1);

        String[] arrayItems_year = {"2017", "2018"};
        final int[] actualValues_year={2017, 2018};

        SpinerAdapter2 = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, arrayItems_year){

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
        spinner2= (Spinner)  view.findViewById(R.id.spinner);
        spinner2.setAdapter(SpinerAdapter2);
        spinner2.setSelection(SpinerAdapter2.getPosition("2018"));

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
              //  year_=actualValues_year[arg2];
                    if(userInteraction==0 || userInteraction==1)
                    {
                        userInteraction++;
                        Log.i("Sagopa", "Sagopa");
                    }
                    else {
                        year_=actualValues_year[arg2];
                       // new JSONParse().execute();
                        yearJson = new JSONParse();
                        if(monthJson!=null)
                        {
                        monthJson.cancel(true);}
                        yearJson.execute();
                    }
                    secondYearTxt.setText(String.valueOf(year_));
                    firstYearTxt.setText(String.valueOf(year_-1));

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
              //  month_=actualValues_month[arg2]+1;
                if(userInteraction==0 || userInteraction==1)
                {
                    userInteraction++;
                    Log.i("Sagopa", "Sagopa");
                }
                else {
                    month_=actualValues_month[arg2]+1;
                   /* JSONParse fff = new JSONParse();
                    fff.execute();*/
                   monthJson = new JSONParse();
                   if(yearJson!=null)
                   {
                   yearJson.cancel(true);}
                   monthJson.execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

      //year_ = spinner2.getSelectedItem().toString();

      new JSONParse().execute();

    /*    new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                new JSONParse().execute();
            }
        },100);*/
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        ProgressBar prg=(ProgressBar) view.findViewById(R.id.progressBar);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prg.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("year",String.valueOf(year_)));
            params.add(new BasicNameValuePair("month",String.valueOf(month_)));
            params.add(new BasicNameValuePair("userid",userId_));
            params.add(new BasicNameValuePair("type", "0"));

            JSONObject json = jParser.getJSONFromUrl(url,params);
            return json;
        }
        //@Override
        protected void onPostExecute(JSONObject json) {
          super.onPostExecute(json);

          Log.i("gunparam", String.valueOf(json));
          if (!isCancelled())
          {

            txt_day = (TextView) view.findViewById(R.id.txt_day);
          sutkaliq20116 = (TextView) view.findViewById(R.id.sutkaliq2016);
          sutkaliq2017 = (TextView) view.findViewById(R.id.sutkaliq2017);
          sutkaliq_icon = (ImageView) view.findViewById(R.id.sutkaliq_icon);
          sutkaliq_Diff = (TextView) view.findViewById(R.id.sutkaliq_diff);
          cem_2016Txt = (TextView) view.findViewById(R.id.cem2016);
          cem_2017Txt = (TextView) view.findViewById(R.id.cem2017);
          cem_Diff = (TextView) view.findViewById(R.id.cem_diff);
          cem_Icon = (ImageView) view.findViewById(R.id.cem_icon);
          LinearLayout sutkaliqList = (LinearLayout) view.findViewById(R.id.cccf);
          LinearLayout cemLyt = (LinearLayout) view.findViewById(R.id.cccm);
          cemLyt.setVisibility(View.VISIBLE);
          try {
            DailyList.personList = new ArrayList<>();
            array_list_.clear();
            Plan = json.getJSONArray(TAG_PLANTRACK);
            Daily_R sum = new Daily_R();
            long sum_2017 = 0;
            long sum_2016 = 0;
            for (int i = 0; i < Plan.length(); i++) {
              JSONObject c = Plan.getJSONObject(i);
              String v_day = c.getString(T_DAY);
              String v_curr_year = c.getString(T_ADY_CURR);
              String v_prw_year = c.getString(T_ADY_PRW);

              java.text.DecimalFormat formatter, formatter1, formatter2;
              formatter = new java.text.DecimalFormat("#,###,###");

              String v_curr_year2 = formatter.format(Float.parseFloat(v_curr_year));
              String v_prw_year2 = formatter.format(Float.parseFloat(v_prw_year));

              Daily_R dr = new Daily_R();

              sum.setDay_("Cəm");

              dr.setDay_(v_day);
              dr.setT2016_(changeFormat(v_prw_year2));
              dr.setT2017_(changeFormat(v_curr_year2));
              dr.setSecondYear(String.valueOf(year_));

              //  sum_2016 = sum_2016 + Integer.parseInt(v_prw_year);
              sum_2016 = sum_2016 + Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(v_prw_year))));
              // sum_2017 = sum_2017 + Integer.parseInt(v_curr_year);
              sum_2017 = sum_2017 + Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(v_curr_year))));

              sum.setT2016_(changeFormat(formatter.format(sum_2016)));
              sum.setT2017_(changeFormat(formatter.format(sum_2017)));

              cem_2016Txt.setText(changeFormat(formatter.format(sum_2016)));
              cem_2017Txt.setText(changeFormat(formatter.format(sum_2017)));


              int sutkaliq_2016 = Math.round(sum_2016 / Plan.length());
              int sutkaliq_2017 = Math.round(sum_2017 / Plan.length());

              int sutkaliq_diff = sutkaliq_2017 - sutkaliq_2016;

              DailyList.firstSutkaliq = changeFormat(formatter.format(sutkaliq_2016));
              DailyList.secondSutkaliq = changeFormat(formatter.format(sutkaliq_2017));
              DailyList.diffSutkaliq = changeFormat(formatter.format(sutkaliq_diff)) + "/" +
                String.valueOf(Math.round(Float.parseFloat(String.valueOf(sutkaliq_diff)) / sutkaliq_2016 * 100) + " %");

              txt_day.setText("Sutkalıq ortalama");
              sutkaliq20116.setText(changeFormat(formatter.format(sutkaliq_2016)));
              sutkaliq2017.setText(changeFormat(formatter.format(sutkaliq_2017)));
              sutkaliq_Diff.setText(changeFormat(formatter.format(sutkaliq_diff)) + "/" +
                String.valueOf(Math.round(Float.parseFloat(String.valueOf(sutkaliq_diff)) / sutkaliq_2016 * 100) + " %"));

              if (sutkaliq_diff > 0) {
                sutkaliq_icon.setImageResource(R.drawable.up_arrow);
                sutkaliqList.setBackgroundColor(Color.parseColor("#186b0d"));
                txt_day.setBackgroundColor(Color.parseColor("#186b0d"));
                txt_day.setVisibility(View.VISIBLE);
              } else {
                sutkaliq_icon.setImageResource(R.drawable.down_arrow);
                sutkaliqList.setBackgroundColor(Color.parseColor("#701d1d"));
                txt_day.setBackgroundColor(Color.parseColor("#701d1d"));
                txt_day.setVisibility(View.VISIBLE);

              }


              String sum_diff = String.valueOf(sum_2017 - sum_2016);
              String sum_diffInt = changeFormat(formatter.format(Integer.parseInt(sum_diff)));
              String sumPr = String.valueOf(Math.round(Float.parseFloat(sum_diff) / sum_2016 * 100));

              String sum_diff2 = sum_diffInt + "/" + sumPr + " %";
              sum.setDiff_(sum_diff2);
              cem_Diff.setText(sum_diff2);

              if (Float.valueOf(sum_diff) < 0) {
                sum.setImageUrl("-1");
                cem_Icon.setImageResource(R.drawable.down_arrow);
              } else {
                sum.setImageUrl("1");
                cem_Icon.setImageResource(R.drawable.up_arrow);
              }

              //String v_diff = String.valueOf(Integer.parseInt(v_curr_year)- Integer.parseInt(v_prw_year));
              String v_diff = String.valueOf(Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(v_curr_year)))) - Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(v_prw_year)))));
              //String v_diffInt = changeFormat(formatter.format(Integer.parseInt(v_diff)));
              String v_diffInt = changeFormat(formatter.format(Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(v_diff))))));
              // String pr = String.valueOf(Math.round(Float.parseFloat(v_diff)/Integer.parseInt(v_prw_year)*100));
              String pr = String.valueOf(Math.round(Float.parseFloat(v_diff) / Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(v_prw_year)))) * 100));

              String v_diff2 = v_diffInt + "/" + pr + " %";
              dr.setDiff_(v_diff2);

              if (Float.valueOf(v_diff) < 0) {
                dr.setImageUrl("-1");
              } else {
                dr.setImageUrl("1");
              }

              DailyList.personList.add(dr);

              prg.setVisibility(View.INVISIBLE);
              sutkaliqList.setVisibility(View.VISIBLE);
            }

            DailyList.personList.add(0, sum);


            if (getActivity() != null) {


                if ((getResources().getConfiguration().screenLayout &
                        Configuration.SCREENLAYOUT_SIZE_MASK) ==
                        Configuration.SCREENLAYOUT_SIZE_XLARGE
                        ||
                        (getResources().getConfiguration().screenLayout &
                                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                                Configuration.SCREENLAYOUT_SIZE_LARGE
                        ){
                    MyAdapter_2 adapter2 = new MyAdapter_2(getActivity(), R.layout.new_dailylist_forlarge, DailyList.personList);
                    list.setAdapter(adapter2);

                }
                else
                {
                    MyAdapter_2 adapter2 = new MyAdapter_2(getActivity(), R.layout.daily_list, DailyList.personList);
                    list.setAdapter(adapter2);
                }

            }
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
        }

        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
        }


    }


    private void selectSpinnerValue(Spinner spinner, int i)
    {

                spinner.setSelection(i);
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
                    // handle back button's click listener

                    Log.i("Alindi", "alindi");
                    Intent intent = new Intent(getActivity(), navi_menu3.class);
                    intent.putExtra("sess_user", user_);
                    intent.putExtra("sess_pass", pass_);
                    intent.putExtra("sess_id", userId_);
                    intent.putExtra("sess_name", name_);
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
                        SendMail sendMail = new SendMail(spinner2.getSelectedItem().toString(), String.valueOf(spinner.getSelectedItemPosition() + 1), emailEdt.getText().toString());
                        sendMail.execute();
                    }

                    else
                    {
                        dialog.dismiss();
                    }

                }
            });
        }
        return true;

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_send).setVisible(true);
        //super.onPrepareOptionsMenu(menu);
    }

    public String changeFormat(String value){

        String formattedNumber = value.replaceAll(",", " ");
        return formattedNumber;
    }

    public class SendMail extends  AsyncTask<String, String, String>{

        String year;
        String month;
        String email;

        public SendMail(String year, String month, String email){
            this.year = year;
            this.month = month;
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
                URL url = new URL("https://ady.express/plan.asmx/GetDailyReport?year=" + year + "&month=" +
                        month + "&email=" + email + "&userid=" + userId_);
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
