package com.example.suleyman.project_a;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shalala.aghalarova on 8/21/2017.
 */

public class dialog_addfilter extends Dialog {

    public Activity c_f;
    public Button yes, ok;

    Spinner ltype,vgtype,qttype,rtype,origin,owner,ptype, yearSpinner;
    CheckBox ch_year;
    ArrayAdapter<CharSequence> spinnerAdapter ;

    String   v_ltype,v_vgtype,v_qttype,v_rtype,v_origin,v_owner,v_year,v_ptype;

    private static final String CODE_f="CODE";
    private static final String NAME_f="NAME";
    private static final String TYPE_f="SC_TYPE";

    View view_filter;


    ProgressBar prg;
    JSONArray Filter_ary = null;
    private static String url_f = "https://ady.express/Plan.asmx/Search";
    private static String user_c;
    private static String pass_c;
    String user_="";
    String pass_="";
    ArrayAdapter<SpinnerObject> dataAdapter2, dataAdapter1, getDataAdapter3, getDataAdapter4, getDataAdapter5, getDataAdapter6, getDataAdapter7;


    dialog_addfilter.OnMyDialogResult_filter mDialogResult_filter;

    //JSON Node Names
    private static final String TAG_PLANTRACK_f = "data";



    public dialog_addfilter(Activity a,String user_c,String pass_c) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c_f = a;
        this.user_c = user_c;
        this.pass_c = pass_c;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        c_f.setTheme(R.style.MyAlertDialogTheme);
        setContentView(R.layout.add_filter_test);

        ltype=(Spinner)  findViewById(R.id.cmb_ltype);
        vgtype=(Spinner) findViewById(R.id.cmb_vgtype);
        qttype=(Spinner) findViewById(R.id.cmb_qttype);
        rtype=(Spinner)  findViewById(R.id.cmb_rtype);
        origin=(Spinner) findViewById(R.id.cmb_origin);
        owner=(Spinner)  findViewById(R.id.cmb_owner);
        ptype=(Spinner)  findViewById(R.id.cmb_ptype);
        ch_year = (CheckBox) findViewById(R.id.yearCheck);
        v_vgtype = "1";
        v_ltype = "1";
        v_qttype = "0";
        v_rtype = "0";
        v_owner = "0";
        v_origin = "0";
        v_ptype = "0";
        v_year = "0";

                ltype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        v_ltype=String.valueOf((((SpinnerObject) ltype.getSelectedItem()).getId()));
                        Toast.makeText(getContext(), v_ltype, Toast.LENGTH_SHORT).show();
                        InSearch.index_labels2 = i;
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
        /*        return false;
            }
        });*/

    /*   ptype.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {*/
                ptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        v_ptype=String.valueOf((((SpinnerObject) ptype.getSelectedItem()).getId()));
                        Toast.makeText(getContext(), v_ptype, Toast.LENGTH_SHORT).show();
                        InSearch.index_ptype = i;

                    }
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
       /*      return false;
            }
        });*/


      /* vgtype.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View view, MotionEvent motionEvent) {*/
               vgtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       v_vgtype=String.valueOf((((SpinnerObject) vgtype.getSelectedItem()).getId()));
                       Toast.makeText(getContext(), v_vgtype, Toast.LENGTH_SHORT).show();
                       InSearch.index_labels = i;
                   }

                   public void onNothingSelected(AdapterView<?> adapterView) {
                       return;
                   }
               });
       /*        return false;
           }
       });
*/
       /* rtype.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {*/
                rtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        v_rtype=String.valueOf((((SpinnerObject) rtype.getSelectedItem()).getId()));
                        Toast.makeText(getContext(), v_rtype, Toast.LENGTH_SHORT).show();
                        InSearch.index_rtype = i;
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
        /*        return false;
            }
        });*/

       /* qttype.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {*/
                qttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        v_qttype=String.valueOf((((SpinnerObject) qttype.getSelectedItem()).getId()));
                        Toast.makeText(getContext(), v_qttype, Toast.LENGTH_SHORT).show();
                        InSearch.index_qttype = i;
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
        /*        return false;
            }
        });*/

      /*  origin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {*/
                origin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        v_origin=String.valueOf((((SpinnerObject) origin.getSelectedItem()).getId()));
                        Toast.makeText(getContext(), v_origin, Toast.LENGTH_SHORT).show();
                        InSearch.index_origin = i;

                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
      /*         return false;
            }
        });*/

       /* owner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {*/
                owner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        v_owner=String.valueOf((((SpinnerObject) owner.getSelectedItem()).getId()));
                        Toast.makeText(getContext(), v_owner, Toast.LENGTH_SHORT).show();
                        InSearch.index_owner = i;
                    }

                    public void onNothingSelected(AdapterView<?> adapterView) {
                        return;
                    }
                });
    /*            return false;
            }
        });
*/

        yes = (Button) findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ok = (Button) findViewById(R.id.btn_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ch_year.isChecked())
                {
                    v_year="1";
                }

                if( mDialogResult_filter != null ){
                    mDialogResult_filter.finish(v_ltype,v_vgtype,v_ptype,v_qttype,v_rtype,v_origin,v_owner,v_year);
                }
                dismiss();
                //new InSearch.jsonParse2.execute();
                //Toast.makeText(getContext(), v_vgtype, Toast.LENGTH_SHORT).show();
            }
        });




        if(InSearch.labels.isEmpty()) {
            new dialog_addfilter.JSONParse_filter().execute();
        }

        else
        {

            dataAdapter2 = new ArrayAdapter<SpinnerObject>(getContext(),
                    android.R.layout.simple_spinner_item, InSearch.labels2){

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
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ltype.setAdapter(dataAdapter2);
            ltype.setSelection(InSearch.index_labels2);

            dataAdapter1 = new ArrayAdapter<SpinnerObject>(getContext(),
                    android.R.layout.simple_spinner_item, InSearch.labels){

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
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            vgtype.setAdapter(dataAdapter1);
            vgtype.setSelection(InSearch.index_labels);


            getDataAdapter3 = new ArrayAdapter<SpinnerObject>(getContext(),
                    android.R.layout.simple_spinner_item, InSearch.lb_qttype){

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
            getDataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            qttype.setAdapter(getDataAdapter3);
            qttype.setSelection(InSearch.index_qttype);

            getDataAdapter4 = new ArrayAdapter<SpinnerObject>(getContext(),
                    android.R.layout.simple_spinner_item, InSearch.lb_rtype){

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
            getDataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            rtype.setAdapter(getDataAdapter4);
            rtype.setSelection(InSearch.index_rtype);


            getDataAdapter5 = new ArrayAdapter<SpinnerObject>(getContext(),
                    android.R.layout.simple_spinner_item, InSearch.lb_owner){

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
            getDataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            owner.setAdapter(getDataAdapter5);
            owner.setSelection(InSearch.index_owner);


            getDataAdapter6 = new ArrayAdapter<SpinnerObject>(getContext(),
                    android.R.layout.simple_spinner_item, InSearch.lb_origin){

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
            getDataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            origin.setAdapter(getDataAdapter6);
            origin.setSelection(InSearch.index_origin);



            getDataAdapter7 = new ArrayAdapter<SpinnerObject>(getContext(),
                    android.R.layout.simple_spinner_item, InSearch.lb_ptype){

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
            getDataAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ptype.setAdapter(getDataAdapter7);
            ptype.setSelection(InSearch.index_ptype);

        }



    }
    public void setDialogResult(dialog_addfilter.OnMyDialogResult_filter dialogResult){
        mDialogResult_filter = dialogResult;
    }

    public interface OnMyDialogResult_filter{
        void finish(String ltype,String vgtype,String ptype,String qttype,String rtype,String origin,String owner,String year);

    }
    private class JSONParse_filter  extends AsyncTask<String, String, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

//            prg.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser_filter = new JSONParser();

            List<BasicNameValuePair> params_filter = new ArrayList<BasicNameValuePair>();


            params_filter.add(new BasicNameValuePair("filter","0"));
            params_filter.add(new BasicNameValuePair("type","combo"));
            params_filter.add(new BasicNameValuePair("user",user_c));
            params_filter.add(new BasicNameValuePair("pass",pass_c));

            JSONObject json_filter = jParser_filter.getJSONFromUrl(url_f,params_filter);
            return json_filter;
        }
        @Override
        protected void onPostExecute(JSONObject json_filter) {
            super.onPostExecute(json_filter);

            //   prg.setVisibility(View.INVISIBLE);
            List < SpinnerObject > labels = new ArrayList < SpinnerObject > ();
            List < SpinnerObject > labels2 = new ArrayList < SpinnerObject > ();
            List < SpinnerObject > lb_qttype = new ArrayList < SpinnerObject > ();
            List < SpinnerObject > lb_rtype = new ArrayList < SpinnerObject > ();
            List < SpinnerObject > lb_owner = new ArrayList < SpinnerObject > ();
            List < SpinnerObject > lb_origin = new ArrayList < SpinnerObject > ();
            List < SpinnerObject > lb_ptype = new ArrayList < SpinnerObject > ();

            ArrayAdapter<SpinnerObject> dataAdapter ;

            try {


                Filter_ary = json_filter.getJSONArray(TAG_PLANTRACK_f);
                lb_owner.add(new SpinnerObject(0,0, "Hamısı"));
                lb_ptype.add(new SpinnerObject(0,0,"Seç"));
                InSearch.lb_owner.add(new SpinnerObject(0,0,"Hamısı"));
                InSearch.lb_ptype.add(new SpinnerObject(0,0,"Seç"));

                for (int i = 0; i < Filter_ary.length(); i++) {
                    String x= Filter_ary.getString(i);
                    JSONObject c_filter = Filter_ary.getJSONObject(i);



                    String _code=c_filter.getString(CODE_f);
                    String _name=c_filter.getString(NAME_f);
                    String _type=c_filter.getString(TYPE_f);



                    if ( _type.toString().equals("EXP_TYPE") ) {
                        labels2.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        InSearch.labels2.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );

                        dataAdapter = new ArrayAdapter<SpinnerObject>(getContext(),
                                android.R.layout.simple_spinner_item, labels2){

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
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ltype.setAdapter(dataAdapter);
                    }
            else    if ( _type.toString().equals("ORD_TRANSTYPE") ) {
                        labels.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        InSearch.labels.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        dataAdapter = new ArrayAdapter<SpinnerObject>(getContext(),
                                android.R.layout.simple_spinner_item, labels){

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
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        vgtype.setAdapter(dataAdapter);
                    }

                    else    if ( _type.toString().equals("QUARTER") ) {
                        if(_name.equals("Hamısı"))
                        {
                            _name = "Seç";
                        }
                        lb_qttype.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        InSearch.lb_qttype.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        dataAdapter = new ArrayAdapter<SpinnerObject>(getContext(),
                                android.R.layout.simple_spinner_item, lb_qttype){

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
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        qttype.setAdapter(dataAdapter);
                    }
                    else    if ( _type.toString().equals("VGN_OWNERTYPE") ) {
                        lb_owner.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        InSearch.lb_owner.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        dataAdapter = new ArrayAdapter<SpinnerObject>(getContext(),
                                android.R.layout.simple_spinner_item, lb_owner){

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
                        };;

                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        owner.setAdapter(dataAdapter);
                    }
                    else    if ( _type.toString().equals("RT") ) {
                        lb_rtype.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        InSearch.lb_rtype.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        dataAdapter = new ArrayAdapter<SpinnerObject>(getContext(),
                                android.R.layout.simple_spinner_item, lb_rtype){

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
                        };;
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        rtype.setAdapter(dataAdapter);
                    }
                    else    if ( _type.toString().equals("CT") ) {
                        lb_origin.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        InSearch.lb_origin.add ( new SpinnerObject ( Integer.valueOf(_code),Integer.valueOf(_code) , _name ) );
                        dataAdapter = new ArrayAdapter<SpinnerObject>(getContext(),
                                android.R.layout.simple_spinner_item, lb_origin){

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
                        };;
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        origin.setAdapter(dataAdapter);
                    }
                    else    if ( _type.toString().equals("CT_TYPE") ) {
                        if (!_name.equals("İcarə") && !_name.equals("Yuyulma")) {
                            lb_ptype.add(new SpinnerObject(Integer.valueOf(_code), Integer.valueOf(_code), _name));
                            InSearch.lb_ptype.add(new SpinnerObject(Integer.valueOf(_code), Integer.valueOf(_code), _name));
                            dataAdapter = new ArrayAdapter<SpinnerObject>(getContext(),
                                    android.R.layout.simple_spinner_item, lb_ptype) {

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
                            ;
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            ptype.setAdapter(dataAdapter);
                        }
                    }
               }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
            }

        }
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(getContext(), "onProgressUpdate"+user_c.toString(), Toast.LENGTH_SHORT).show();

        }


    }


}
