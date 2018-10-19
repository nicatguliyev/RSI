package com.example.suleyman.project_a;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ContentFrameLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suleyman.project_a.Adapter.IntellektualAdapter;
import com.example.suleyman.project_a.Common.Menular;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InSearch extends Fragment {

    private ViewGroup sldlay1;
    private boolean isPanelShown;
    RelativeLayout rel_lay1,s2, resultLyt;
    Button btn_result,btn_preprice,btn_addfilter;
    EditText yhn_code,m1,m2;
    TextView resultTxt;
    View view;
    ProgressBar searchPrg;
    FrameLayout rootLyt;
    Spinner actype;

    private static final String TAG_PLANTRACK = "data";


    private static final String CODE="CODE";
    private static final String NAME="NAME";
    private static final String TYPE="SC_TYPE";


    private static final String EXP_EXPENSE="EXP_EXPENSE";
    private static final String EXP_PAMOUNT="EXP_PAMOUNT";
    private static final String EXP_AMOUNT="EXP_AMOUNT";
    private static final String ORD_VGALLTONNAJ="ORD_VGALLTONNAJ";
    private static final String rev="rev";

    private static  String ptype_="0";
    private static  String qnqcode_="";
    private static  String ltype_="1";
    private static  String vgtype_="1";
    private static  String actype_="0";
    private static  String bp_="";
    private static  String ep_="";
    private static  String year_="0";
    private static  String qt_="0";
    private static  String type_="";
    private static String rtype_="0", origin_="0", owner_="0";
    ListView list_popup_;

    public static  List < SpinnerObject > labels = new ArrayList < SpinnerObject > ();
    public static List < SpinnerObject > labels2 = new ArrayList < SpinnerObject > ();
    public static List < SpinnerObject > lb_qttype = new ArrayList < SpinnerObject > ();
    public static List < SpinnerObject > lb_rtype = new ArrayList < SpinnerObject > ();
    public static List < SpinnerObject > lb_owner = new ArrayList < SpinnerObject > ();
    public static List < SpinnerObject > lb_origin = new ArrayList < SpinnerObject > ();
    public static List < SpinnerObject > lb_ptype = new ArrayList < SpinnerObject > ();

    public  static JSONParse2 jsonParse2 = null;

    public  static int index_labels = 0;
    public static int index_labels2 = 0;
    public static int index_qttype = 0;
    public static  int index_rtype = 0;
    public static int index_owner = 0;
    public static int index_origin = 0;
    public static int index_ptype = 0;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InSearch() {
        // Required empty public constructor
    }


    public static InSearch newInstance(String param1, String param2) {
        InSearch fragment = new InSearch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    String user_="";
    String pass_="";
    String name_="";
    String userId_="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        user_ = getArguments().getString("sess_user");
        pass_ =  getArguments().getString("sess_pass");
        name_ =  getArguments().getString("sess_name");
        userId_ = getArguments().getString("sess_id");

        yhn_code=(EditText) view.findViewById(R.id.txt_qnqcode);
        m1=(EditText) view.findViewById(R.id.txt_m1);
        m2=(EditText) view.findViewById(R.id.txt_m2);
        actype=(Spinner) view.findViewById(R.id.cmb_actype);
        btn_result=(Button) view.findViewById(R.id.btn_result);
        btn_preprice=(Button) view.findViewById(R.id.btn_preprice);
        btn_addfilter=(Button) view.findViewById(R.id.btn_addfilter);
        list_popup_=(ListView) view.findViewById(R.id.listview);
        searchPrg = (ProgressBar) view.findViewById(R.id.searchPrg);
        rootLyt = (FrameLayout) view.findViewById(R.id.rootLyt);
        resultLyt = (RelativeLayout) view.findViewById(R.id.resultLyt);
        resultTxt = (TextView) view.findViewById(R.id.resultTxt);


        index_labels = 0;
        index_labels2 = 0;
        index_qttype = 0;
        index_rtype = 0;
        index_owner = 0;
        index_origin = 0;
        index_ptype = 0;

        new testJson().execute();

        Log.i("kokoqt", qt_);

        //jsonParse2 = new JSONParse2();


        setupParent(rootLyt);

        final GestureDetector gestureDetector = new GestureDetector(getActivity().getApplicationContext(),
                new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {
                String filter="";

                if (yhn_code.getText().toString().equals("") )

                {
                    filter="without filter";
                }

                else {
                    filter=yhn_code.getText().toString();
                }

                final Dialog dialog =  new Dialog(getActivity());
                View child = getActivity().getLayoutInflater().inflate(R.layout.search_type, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(child);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                dialog.show();

                final RadioButton beginsRadio = (RadioButton) dialog.findViewById(R.id.beginRadio);
                final RadioButton containsRadio = (RadioButton) dialog.findViewById(R.id.containsRadio);
                Button searchButton = (Button) dialog.findViewById(R.id.search);

                final String filter2 = filter;
                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if(beginsRadio.isChecked())
                        {

                            Dialog_Search  dg=new Dialog_Search(getActivity(),user_,pass_,String.valueOf(filter2),"qnq",1);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                            dg.getWindow().setLayout(width, height);
                            dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dg.setDialogResult_search(new Dialog_Search.OnMyDialogResult_search(){
                                public void finish(String result,String type){
                                    //    if (  type.toString().equals("qnq" ) ) {
                                    yhn_code.setText(result.toString());
                                    qnqcode_= yhn_code.getText().toString();
                                }
                            });
                            dg.show();
                        }
                        if(containsRadio.isChecked())
                        {
                            Dialog_Search  dg=new Dialog_Search(getActivity(),user_,pass_,String.valueOf(filter2),"qnq",2);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                            dg.getWindow().setLayout(width, height);
                            dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dg.setDialogResult_search(new Dialog_Search.OnMyDialogResult_search(){
                                public void finish(String result,String type){
                                    //    if (  type.toString().equals("qnq" ) ) {
                                    yhn_code.setText(result.toString());
                                    qnqcode_= yhn_code.getText().toString();
                                }
                            });
                            dg.show();

                        }
                    }
                });


              /*  Dialog_Search  dg=new Dialog_Search(getActivity(),user_,pass_,String.valueOf(filter),"qnq");

                int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dg.getWindow().setLayout(width, height);
                dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dg.setDialogResult_search(new Dialog_Search.OnMyDialogResult_search(){
                    public void finish(String result,String type){
            //    if (  type.toString().equals("qnq" ) ) {
                    yhn_code.setText(result.toString());
                    qnqcode_= yhn_code.getText().toString();
                    }
                });
                dg.show();*/

                return true;
            }
        });

        yhn_code.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        yhn_code.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                qnqcode_= yhn_code.getText().toString();
            }
        });
////
        final GestureDetector gestureDetector1 = new GestureDetector(getActivity().getApplicationContext(),
                new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {

                String filter="";
                if (   m1.getText().toString().equals("") )
                {
                    filter="without filter";
                }
                else {
                    filter=m1.getText().toString();
                }


                final Dialog dialog =  new Dialog(getActivity());
                View child = getActivity().getLayoutInflater().inflate(R.layout.search_type, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(child);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                dialog.show();

                final RadioButton beginsRadio = (RadioButton) dialog.findViewById(R.id.beginRadio);
                final RadioButton containsRadio = (RadioButton) dialog.findViewById(R.id.containsRadio);
                Button searchButton = (Button) dialog.findViewById(R.id.search);

                final String filter2 = filter;
                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if(beginsRadio.isChecked())
                        {

                            Dialog_Search  dg=new Dialog_Search(getActivity(),user_,pass_,String.valueOf(filter2),"point",1);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                            dg.getWindow().setLayout(width, height);
                            dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dg.setDialogResult_search(new Dialog_Search.OnMyDialogResult_search(){
                                public void finish(String result,String type){
                                    //    if (  type.toString().equals("qnq" ) ) {
                                    m1.setText(result.toString());
                                    bp_= m1.getText().toString();
                                }
                            });
                            dg.show();
                        }
                        if(containsRadio.isChecked())
                        {
                            Dialog_Search  dg=new Dialog_Search(getActivity(),user_,pass_,String.valueOf(filter2),"point",2);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                            dg.getWindow().setLayout(width, height);
                            dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dg.setDialogResult_search(new Dialog_Search.OnMyDialogResult_search(){
                                public void finish(String result,String type){
                                    //    if (  type.toString().equals("qnq" ) ) {
                                    m1.setText(result.toString());
                                    bp_= m1.getText().toString();
                                }
                            });
                            dg.show();

                        }
                    }
                });

                return true;
            }
        });

        m1.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector1.onTouchEvent(event);
            }
        });
        m1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                bp_= m1.getText().toString();
            }
        });
        final GestureDetector gestureDetector2 = new GestureDetector(getActivity().getApplicationContext(),
                new GestureDetector.SimpleOnGestureListener() {
            public boolean onDoubleTap(MotionEvent e) {


                String filter="";
                if (   m2.getText().toString().equals("") )
                {
                    filter="without filter";
                }
                else {
                    filter=m2.getText().toString();
                }


                final Dialog dialog =  new Dialog(getActivity());
                View child = getActivity().getLayoutInflater().inflate(R.layout.search_type, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(child);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                dialog.show();

                final RadioButton beginsRadio = (RadioButton) dialog.findViewById(R.id.beginRadio);
                final RadioButton containsRadio = (RadioButton) dialog.findViewById(R.id.containsRadio);
                Button searchButton = (Button) dialog.findViewById(R.id.search);

                final String filter2 = filter;
                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        if(beginsRadio.isChecked())
                        {

                            Dialog_Search  dg=new Dialog_Search(getActivity(),user_,pass_,String.valueOf(filter2),"point",1);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                            dg.getWindow().setLayout(width, height);
                            dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dg.setDialogResult_search(new Dialog_Search.OnMyDialogResult_search(){
                                public void finish(String result,String type){
                                    //    if (  type.toString().equals("qnq" ) ) {
                                    m2.setText(result.toString());
                                    ep_= m2.getText().toString();
                                }
                            });
                            dg.show();
                        }
                        if(containsRadio.isChecked())
                        {
                            Dialog_Search  dg=new Dialog_Search(getActivity(),user_,pass_,String.valueOf(filter2),"point",2);

                            int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                            dg.getWindow().setLayout(width, height);
                            dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dg.setDialogResult_search(new Dialog_Search.OnMyDialogResult_search(){
                                public void finish(String result,String type){
                                    //    if (  type.toString().equals("qnq" ) ) {
                                    m2.setText(result.toString());
                                    ep_= m2.getText().toString();
                                }
                            });
                            dg.show();

                        }
                    }
                });

                return true;
            }
        });

        m2.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector2.onTouchEvent(event);
            }
        });
        m2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                ep_= m2.getText().toString();

            }
        });


        new JSONParse().execute();

       // actype.setSelection(1);
      //  actype_=String.valueOf((((SpinnerObject) actype.getSelectedItem()).getId()));
        actype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                actype_=String.valueOf((((SpinnerObject) actype.getSelectedItem()).getId()));

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        btn_result.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new JSONParse2().execute();

            }
        });
        btn_preprice.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Dialog_Preprice  dg=new Dialog_Preprice(getActivity(),user_,pass_,ptype_,qnqcode_,ltype_,vgtype_,actype_,bp_,ep_,year_,rtype_,owner_,origin_,qt_,type_);

                int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                dg.getWindow().setLayout(width, height);
                dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dg.show();
            }
        });
        btn_addfilter.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new JSONParse().isCancelled();

                dialog_addfilter  dg=new dialog_addfilter(getActivity(),user_,pass_);

                int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                int height = (int)(getResources().getDisplayMetrics().heightPixels/20);
                dg.getWindow().setLayout(width, 20);
                dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dg.setDialogResult(new dialog_addfilter.OnMyDialogResult_filter(){
                    public void finish(String v_ltype,String v_vgtype,String v_ptype,String v_qttype,String v_rtype,String v_origin,String v_owner,String v_year){
                        //    if (  type.toString().equals("qnq" ) ) {
                        ptype_=v_ptype;
                        ltype_=v_ltype;
                        vgtype_=v_vgtype;
                        qt_=v_qttype;
                        rtype_=v_rtype;
                        origin_=v_origin;
                        owner_=v_owner;
                        year_=v_year;
                    }
                });
                dg.show();
            }
        });

        return view;
    }


   public class testJson extends  AsyncTask<String , String , JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONParser jParser = new JSONParser();


            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("ptype","0"));
            params.add(new BasicNameValuePair("qnqcode","44041000"));
            params.add(new BasicNameValuePair("ltype","1"));
            params.add(new BasicNameValuePair("vgtype","1"));
            params.add(new BasicNameValuePair("actype","5"));
            params.add(new BasicNameValuePair("bp",""));
            params.add(new BasicNameValuePair("ep",""));
            params.add(new BasicNameValuePair("year", "0"));
            params.add(new BasicNameValuePair("qt", "0"));
            params.add(new BasicNameValuePair("type","result"));
            params.add(new BasicNameValuePair("rtype","0"));
            params.add(new BasicNameValuePair("owner","0"));
            params.add(new BasicNameValuePair("origin","0"));
            params.add(new BasicNameValuePair("user","prq_test"));
            params.add(new BasicNameValuePair("pass","12345"));
            params.add(new BasicNameValuePair("minweight",""));
            params.add(new BasicNameValuePair("maxweight",""));
            params.add(new BasicNameValuePair("searchtype","0"));

            JSONObject json = jParser.getJSONFromUrl(url2,params);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            Log.i("Netice", String.valueOf(jsonObject));

        }
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
    private void selectSpinnerValue(Spinner spinner, int i)
    {

        spinner.setSelection(i);

    }


    JSONArray Plan = null;
    private static String url = "https://ady.express/Plan.asmx/Search";
    private static String url2 = "https://ady.express/Plan.asmx/Result";

    private class JSONParse  extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  prg.setVisibility(View.VISIBLE);
        }


        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("filter","0"));
            params.add(new BasicNameValuePair("type","combo"));
            params.add(new BasicNameValuePair("user",user_));
            params.add(new BasicNameValuePair("pass",pass_));

            JSONObject json = jParser.getJSONFromUrl(url,params);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);

            List < SpinnerObject > labels = new ArrayList < SpinnerObject > ();
            List < SpinnerObject > labels2 = new ArrayList < SpinnerObject > ();

            ArrayAdapter<SpinnerObject> dataAdapter ;

            try {

              if (json != null){
                Plan = json.getJSONArray(TAG_PLANTRACK);
              for (int i = 0; i < Plan.length(); i++) {
                JSONObject c = Plan.getJSONObject(i);


                String _code = c.getString(CODE);
                String _name = c.getString(NAME);
                String _type = c.getString(TYPE);


                if (_type.toString().equals("ORD_TYPEACT")) {

                  if (getActivity() != null) {
                    labels2.add(new SpinnerObject(Integer.valueOf(_code), Integer.valueOf(_code), _name.replace("Hamısı", "Rejim")));
                    dataAdapter = new ArrayAdapter<SpinnerObject>(getActivity().getApplicationContext(),
                      android.R.layout.simple_spinner_item, labels2) {

                      @Override
                      public View getDropDownView(int position, View convertView, ViewGroup parent) {
                        // TODO Auto-generated method stub

                        View view = super.getView(position, convertView, parent);

                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.BLACK);

                        return view;

                      }

                      @Override
                      public View getView(int position, View convertView, ViewGroup parent) {
                        // TODO Auto-generated method stub

                        View view = super.getView(position, convertView, parent);

                        TextView text = (TextView) view.findViewById(android.R.id.text1);
                        text.setTextColor(Color.WHITE);

                        return view;

                      }
                    };
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    actype.setAdapter(dataAdapter);
                  }
                }


              }
            }

            else
              {
                Toast.makeText(getContext(), "Internet zeifdir yada yoxdur", Toast.LENGTH_SHORT).show();
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
    ArrayList<HashMap<String, String>> browselist = new ArrayList<HashMap<String, String>>();
    JSONArray Result_get = null;

    public class JSONParse2  extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            searchPrg.setVisibility(View.VISIBLE);
            resultLyt.setVisibility(View.INVISIBLE);
            list_popup_.setVisibility(View.INVISIBLE);
            //  prg.setVisibility(View.VISIBLE);
        }


        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser = new JSONParser();


            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("ptype",ptype_));
            params.add(new BasicNameValuePair("qnqcode",qnqcode_));
            params.add(new BasicNameValuePair("ltype",ltype_));
            params.add(new BasicNameValuePair("vgtype",vgtype_));
            params.add(new BasicNameValuePair("actype",actype_));
            params.add(new BasicNameValuePair("bp",bp_));
            params.add(new BasicNameValuePair("ep",ep_));
            params.add(new BasicNameValuePair("year",year_));
            params.add(new BasicNameValuePair("qt",qt_));
            params.add(new BasicNameValuePair("type","result"));
            params.add(new BasicNameValuePair("rtype",rtype_));
            params.add(new BasicNameValuePair("owner",owner_));
            params.add(new BasicNameValuePair("origin",origin_));
            params.add(new BasicNameValuePair("user",user_));
            params.add(new BasicNameValuePair("pass",pass_));

            JSONObject json = jParser.getJSONFromUrl(url2,params);
            return json;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);

            Log.i("kokoko", ptype_);
            Log.i("kokoko", qnqcode_);
            Log.i("kokoko", ltype_);
            Log.i("kokoko", vgtype_);
            Log.i("kokoko", actype_);
            Log.i("kokoko", bp_);
            Log.i("kokoko", ep_);
            Log.i("kokoko", year_);
            Log.i("kokoko", qt_);
            Log.i("kokoko", rtype_);
            Log.i("kokoko", owner_);
            Log.i("kokoko", origin_);
            Log.i("kokoko", user_);
            Log.i("kokoko", pass_);


            browselist.clear();
            searchPrg.setVisibility(View.INVISIBLE);
            String price = "";

           // Log.i("myJsom", json.toString());

            try {


                // Getting JSON Array from URL
                String currentKey="";
                if(json!=null) {
                    Result_get = json.getJSONArray(TAG_PLANTRACK);
                    for (int i = 0; i < Result_get.length(); i++) {
                        JSONObject cs = Result_get.getJSONObject(i);

                        if(String.valueOf(ptype_).equals("0"))
                        {
                           price = cs.getString("EXP_PEXPENSE");
                        }
                        if(!String.valueOf(ptype_).equals("0"))
                        {
                            price = cs.getString("EXP_PEXPENSE");
                        }

                        //String _expense = cs.getString(EXP_EXPENSE);
                        String _pamount = cs.getString(EXP_PAMOUNT);
                        String _amount = cs.getString(EXP_AMOUNT);
                        String _tonnaj = cs.getString(ORD_VGALLTONNAJ);
                        String _rev = cs.getString(rev);



                        HashMap<String, String> map_ = new HashMap<String, String>();


                        //map_.put(EXP_EXPENSE, _expense);
                        map_.put(EXP_EXPENSE, price);
                        map_.put(EXP_PAMOUNT, _pamount);
                        map_.put(EXP_AMOUNT, _amount);
                        map_.put(ORD_VGALLTONNAJ, _tonnaj);
                        map_.put(rev, _rev);

                        browselist.add(map_);

                        IntellektualAdapter adapter_c = new IntellektualAdapter(getContext()
                        , browselist, R.layout.result_list, new String[]{EXP_EXPENSE,
                        EXP_PAMOUNT, EXP_AMOUNT, ORD_VGALLTONNAJ}, new int[]{R.id.sNo, R.id.product,
                                R.id.category, R.id.price});

                        list_popup_.setAdapter(adapter_c);

                        resultTxt.setText("Bütün kriteriyalar üzrə en sərfəli qiymət " + browselist.get(0).get(EXP_EXPENSE).toString() + "-dir");

                        resultLyt.setVisibility(View.VISIBLE);
                        list_popup_.setVisibility(View.VISIBLE);

                    }
                }
                else {
                    Toast.makeText(getActivity(), "Bu dəyərlərə uyğun heç bir məlumat tapılmadı", Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
                //Toast.makeText(getContext(), "Qeyd edilmiş filterə uyğun məlumat tapılmadı.", Toast.LENGTH_SHORT).show();
               // Log.i("jsonError", e.toString());
            }

        }
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
        }

    }

    protected void setupParent(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard();
                    return false;
                }
            });
        }
        //If a layout container, iterate over children
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupParent(innerView);
            }
        }
    }


    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
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
                    Intent intent = new Intent(getActivity(), navi_menu3.class);
                    intent.putExtra("sess_user", user_);
                    intent.putExtra("sess_pass", pass_);
                    intent.putExtra("sess_name", name_);
                    intent.putExtra("sess_id", userId_);
                    intent.putStringArrayListExtra("menular", Menular.menular);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });



        class testJson extends  AsyncTask<String , String , JSONObject>{

            @Override
            protected JSONObject doInBackground(String... strings) {
                JSONParser jParser = new JSONParser();


                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
                params.add(new BasicNameValuePair("ptype","0"));
                params.add(new BasicNameValuePair("qnqcode","44041000"));
                params.add(new BasicNameValuePair("ltype","1"));
                params.add(new BasicNameValuePair("vgtype","1"));
                params.add(new BasicNameValuePair("actype","5"));
                params.add(new BasicNameValuePair("bp",""));
                params.add(new BasicNameValuePair("ep",""));
                params.add(new BasicNameValuePair("year", "0"));
                params.add(new BasicNameValuePair("qt", "0"));
                params.add(new BasicNameValuePair("type","result"));
                params.add(new BasicNameValuePair("rtype","0"));
                params.add(new BasicNameValuePair("owner","0"));
                params.add(new BasicNameValuePair("origin","0"));
                params.add(new BasicNameValuePair("user","prq_test"));
                params.add(new BasicNameValuePair("pass","12345"));
                params.add(new BasicNameValuePair("minweight",""));
                params.add(new BasicNameValuePair("maxweight",""));
                params.add(new BasicNameValuePair("searchtype","0"));

                JSONObject json = jParser.getJSONFromUrl(url2,params);
                return json;
            }

            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);

                Log.i("Netice", String.valueOf(jsonObject));

            }
        }


    }
}
