package com.example.suleyman.project_a;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shalala.aghalarova on 8/21/2017.
 */

public class Dialog_Preprice extends Dialog {

    public Activity c1_;
    public Dialog_Preprice d;
    public Button yes, no;
    ListView list_popup_pr;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private static final String EXP_EXPENSE_pr="EXP_EXPENSE";
    private static final String EXP_PAMOUNT_pr="EXP_PAMOUNT";
    private static final String EXP_AMOUNT_pr="EXP_AMOUNT";
    private static final String ORD_VGALLTONNAJ_pr="ORD_VGALLTONNAJ";


    private static  String ptype_c="";
    private static  String qnqcode_c="";
    private static  String ltype_c="1";
    private static  String vgtype_c="1";
    private static  String actype_c="";
    private static  String bp_c="";
    private static  String ep_c="";
    private static  String year_c="0";
    private static  String qt_c="3";
    private static String type_c;
    private static String user_c;
    private static String pass_c;
    private static String rtype_c="0", origin_c="0", owner_c="0";




    View view;


    ProgressBar prg;
    ArrayList<HashMap<String, String>> browselist_pr = new ArrayList<HashMap<String, String>>();
    JSONArray Preprice_get = null;
    private static String url1 = "https://ady.express/Plan.asmx/Result";

    String user_="";
    String pass_="";



    //JSON Node Names
    private static final String TAG_PLANTRACK = "data";



    public Dialog_Preprice(Activity a,String user_c,String pass_c,String ptype_c,String qnqcode_c, String ltype_c,String vgtype_c, String actype_c,String bp_c,String ep_c,String year_c,String rtype_c,String origin_c, String owner_c,String qt_c,String type_c) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c1_ = a;
        this.user_c = user_c;
        this.pass_c = pass_c;
        this.ptype_c = ptype_c;
        this.qnqcode_c = qnqcode_c;
        this.ltype_c= ltype_c;
        this.vgtype_c = vgtype_c;
        this.actype_c = actype_c;
        this.bp_c = bp_c;
        this.ep_c = ep_c;
        this.year_c = year_c;
        this.qt_c = qt_c;
        this.type_c = type_c;
        this.owner_c = owner_c;
        this.rtype_c = rtype_c;
        this.origin_c = origin_c;



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        c1_.setTheme(R.style.MyAlertDialogTheme);
        setContentView(R.layout.dialog_preprice);
        list_popup_pr=(ListView)findViewById(R.id.listview);
        prg=(ProgressBar) findViewById(R.id.progressBar2);


        yes = (Button) findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        list_popup_pr.setAdapter(null);
        new Dialog_Preprice.JSONParse_pr().execute();


    }

    private class JSONParse_pr  extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prg.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser_pr = new JSONParser();

            List<BasicNameValuePair> params_pr = new ArrayList<BasicNameValuePair>();

            params_pr.add(new BasicNameValuePair("ptype",ptype_c));
            params_pr.add(new BasicNameValuePair("qnqcode",qnqcode_c));
            params_pr.add(new BasicNameValuePair("ltype",ltype_c));
            params_pr.add(new BasicNameValuePair("vgtype",vgtype_c));
            params_pr.add(new BasicNameValuePair("actype",actype_c));
            params_pr.add(new BasicNameValuePair("bp",bp_c));
            params_pr.add(new BasicNameValuePair("ep",ep_c));
            params_pr.add(new BasicNameValuePair("year",year_c));
            params_pr.add(new BasicNameValuePair("qt",qt_c));
            params_pr.add(new BasicNameValuePair("type","preprice"));
            params_pr.add(new BasicNameValuePair("rtype",rtype_c));
            params_pr.add(new BasicNameValuePair("owner",owner_c));
            params_pr.add(new BasicNameValuePair("origin",origin_c));
            params_pr.add(new BasicNameValuePair("user",user_c));
            params_pr.add(new BasicNameValuePair("pass",pass_c));

            JSONObject json_pr = jParser_pr.getJSONFromUrl(url1,params_pr);
            return json_pr;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);
            prg.setVisibility(View.INVISIBLE);
            String price = "";
            browselist_pr.clear();

            try {

                if(json!=null) {

                    Preprice_get = json.getJSONArray(TAG_PLANTRACK);
                    for (int i = 0; i < Preprice_get.length(); i++) {
                        JSONObject cs = Preprice_get.getJSONObject(i);


                        if(String.valueOf(ptype_c).equals("0"))
                        {
                            price =  cs.getString(EXP_EXPENSE_pr);
                        }

                        else
                        {
                            price = cs.getString("EXP_PEXPENSE");
                        }

                        //String _expense_pr = cs.getString(EXP_EXPENSE_pr);
                        String _pamount_pr = cs.getString(EXP_PAMOUNT_pr);
                        String _amount_pr = cs.getString(EXP_AMOUNT_pr);
                        String _tonnaj_pr = cs.getString(ORD_VGALLTONNAJ_pr);


                        HashMap<String, String> map_pr = new HashMap<String, String>();



                        map_pr.put(EXP_EXPENSE_pr, price);
                        map_pr.put(EXP_PAMOUNT_pr, _pamount_pr);
                        map_pr.put(EXP_AMOUNT_pr, _amount_pr);
                        map_pr.put(ORD_VGALLTONNAJ_pr, _tonnaj_pr);


                        browselist_pr.add(map_pr);


                        ListAdapter adapter_pr = new SimpleAdapter(getContext(),
                                browselist_pr, R.layout.result_list_white, new String[]{EXP_EXPENSE_pr,
                                EXP_PAMOUNT_pr, EXP_AMOUNT_pr, ORD_VGALLTONNAJ_pr},
                                new int[]{R.id.sNo, R.id.product, R.id.category, R.id.price});


                        list_popup_pr.setAdapter(adapter_pr);


                    }
                }
                else
                {
                    Toast.makeText(getContext(), "Qeyd edilmiş filterə uyğun məlumat tapılmadı.", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Qeyd edilmiş filterə uyğun məlumat tapılmadı.", Toast.LENGTH_SHORT).show();
            }

        }
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
        }


    }


}
