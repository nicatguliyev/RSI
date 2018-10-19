package com.example.suleyman.project_a;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import layout.Plan;
import java.util.Iterator;

/**
 * Created by Suleyman on 7/1/2017.
 */

public class dialog extends Dialog {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    ListView list_popup;

    TextView gr_head;


    ProgressBar prg;
    ArrayList<HashMap<String, String>> idxallist = new ArrayList<HashMap<String, String>>();
    JSONArray Plan = null;
    private static String url = "https://ady.express/Plan.asmx/All_Report_1";

    String user_="";
    String pass_="";
    String name_="";

    //JSON Node Names
    private static final String TAG_PLANTRACK = "data";
    private static final String V_GR="GR";

    private static final String NAME="NAME";
    private static final String TYPE="T";
    private static final String V_FAKT="FAKT";
    private static final String V_FAKT_16="FAKT_16";
    private static final String V_PLAN="PLAN_";
    private static final String V_FAIZ="FAIZ";
    private static final String V_REJIM="REJIM";

    public dialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    Plan pl=new Plan();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        c.setTheme(R.style.MyAlertDialogTheme);
        setContentView(R.layout.dialog_test);
        list_popup=(ListView)findViewById(R.id.list_popoup);
        prg=(ProgressBar) findViewById(R.id.progressBar2);


        gr_head=(TextView) findViewById(R.id.gr_head);

        gr_head.setText(pl.GR_AD);

        Typeface tf_gr_head = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/Cera PRO Medium.ttf");
        Typeface tf_lbl = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/Cera PRO Bold.ttf");

        gr_head.setTypeface(tf_gr_head);


        yes = (Button) findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        new JSONParse().execute();


      /*  txt_fakt_=(TextView) findViewById(R.id.txt_fakt_16);
        txt_fakt_16_=(TextView) findViewById(R.id.txt_fakt_16);
        txt_plan_=(TextView) findViewById(R.id.txt_plan);
        txt_faiz_=(TextView) findViewById(R.id.txt_faiz);
        txt_fakt_.setTypeface(tf_lbl);
        txt_fakt_16_.setTypeface(tf_lbl);
        txt_plan_.setTypeface(tf_lbl);
        txt_faiz_.setTypeface(tf_lbl);*/

    }
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prg.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            // List params = new ArrayList();
            params.add(new BasicNameValuePair("type","LW"));
            params.add(new BasicNameValuePair("user","admin"));
            params.add(new BasicNameValuePair("pass","123uniser456"));
                    //,"admin","123express"

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url,params);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);
            prg.setVisibility(View.INVISIBLE);
            try {
                // Getting JSON Array from URL
                String currentKey="";
                Plan = json.getJSONArray(TAG_PLANTRACK);
                for (int i = 0; i < Plan.length(); i++) {
                    JSONObject c = Plan.getJSONObject(i);
//pl.GR_AD

                    String gr=c.getString(V_GR);
                    String type_=c.getString(TYPE);
                    String fakt =c.getString(V_FAKT);
                    String fakt_16 =c.getString(V_FAKT_16);
                    String plan = c.getString(V_PLAN);
                    String faiz = c.getString(V_FAIZ);
                    String rejim ="";

                    java.text.DecimalFormat formatter,formatter1;
                    formatter = new java.text.DecimalFormat("#,###,###.00");
                    formatter1 = new java.text.DecimalFormat(" #,##0.00 %");


                    if (type_.toString().equals("1")   )
                    {
                        rejim="İDXAL";
                    }
                    else if (type_.toString().equals("2"))
                    {
                        rejim="İXRAC";
                    }
                    else if (type_.toString().equals("3"))
                    {
                        rejim="TRANZİT";
                    }
                    else if (type_.toString().equals("4"))
                    {
                        rejim="DAXİLİ";
                    }
                    else if (type_.toString().equals("5"))
                    {
                        rejim="YEKUN";
                    }
                    fakt = formatter.format(Float.parseFloat(fakt));
                    fakt_16 = formatter.format(Float.parseFloat(fakt_16));
                    plan = formatter.format(Float.parseFloat(plan));
                    faiz = formatter1.format(Float.parseFloat(faiz));



                  //  Toast.makeText(getContext(), ""+fakt, Toast.LENGTH_SHORT).show();


                    HashMap<String, String> map = new HashMap<String, String>();

                    if(pl.GR_AD.toString().equals(gr)) {
                    map.put(V_FAKT,fakt);
                    map.put(V_FAKT_16,fakt_16);
                    map.put(V_PLAN,plan);
                    map.put(V_FAIZ,faiz);
                    map.put(V_REJIM,rejim);
                    idxallist.add(map);




                        ListAdapter adapter = new SimpleAdapter(getContext(),
            idxallist, R.layout.popup_list_desg, new String[]{V_REJIM,
            V_FAKT,V_FAKT_16, V_PLAN, V_FAIZ},
            new int[]{R.id.button5, R.id.txt_fakt, R.id.txt_fakt_16, R.id.txt_plan, R.id.txt_faiz});


    list_popup.setAdapter(adapter);
}

                }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
            }

        }
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
        }


    }


}
