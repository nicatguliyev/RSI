package com.example.suleyman.project_a;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import layout.Plan;

/**
 * Created by shalala.aghalarova on 8/19/2017.
 */

public class Dialog_Search  extends Dialog {

    public Activity c_search;
    public Button yes;
    ListView list_popup_search;

    View view;


    ProgressBar prg;
    ArrayList<HashMap<String, String>> browselist_search = new ArrayList<HashMap<String, String>>();
    JSONArray Search_ac = null;
    private static String url_search = "https://ady.express/Plan.asmx/Search";

    private static String filter_c;
    private static String type_c;
    private static String user_c;
    private static String pass_c;
    private  static  int searchType;


    OnMyDialogResult_search mDialogResult_search;

    //JSON Node Names
    private static final String TAG_PLANTRACK_search = "data";


    private static final String CODE_S="CODE";
    private static final String NAME_S="NAME";
    private static final String TYPE_S="TYPE";


    public Dialog_Search(Activity a,String user_c,String pass_c,String filter_c, String type_c, int SearchType) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c_search = a;
        this.filter_c = filter_c;
        this.type_c = type_c;
        this.user_c = user_c;
        this.pass_c = pass_c;
        this.searchType = SearchType;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        c_search.setTheme(R.style.MyAlertDialogTheme);
        setContentView(R.layout.dialog_srch);
        list_popup_search=(ListView)findViewById(R.id.listview);
        prg=(ProgressBar) findViewById(R.id.progressBar2);

        SearchView simpleSearchView = (SearchView) findViewById(R.id.search); // inititate a search view

        simpleSearchView.setQuery(filter_c, false);

        simpleSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
// do something when the focus of the query text field changes
            }
        });

        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                filter_c=query;

                new Dialog_Search.JSONParse_Search(searchType).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
// do something when text changes
                return false;
            }
        });



        yes = (Button) findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        list_popup_search.setAdapter(null);

        new Dialog_Search.JSONParse_Search(searchType).execute();

        list_popup_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView txt=(TextView) view.findViewById(R.id.txt_qnqcode);
                String  Code=txt.getText().toString();
                if( mDialogResult_search != null ){
                    mDialogResult_search.finish(String.valueOf(Code),type_c);
                }
                Dialog_Search.this.dismiss();

            }
        });

    }
    public void setDialogResult_search(OnMyDialogResult_search dialogResult){
        mDialogResult_search = dialogResult;
    }

    public interface OnMyDialogResult_search{
        void finish(String result,String type);
    }
    private class JSONParse_Search  extends AsyncTask<String, String, JSONObject> {

        // Yoxlayir ki, Begins with yoxsa Contains ile axtaris olunacaq
        int type;

        public JSONParse_Search(int type)
        {
            this.type = type;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            prg.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            JSONParser jParser_search = new JSONParser();

            List<BasicNameValuePair> params_search = new ArrayList<BasicNameValuePair>();


            params_search.add(new BasicNameValuePair("filter",filter_c));
            params_search.add(new BasicNameValuePair("type",type_c));
            params_search.add(new BasicNameValuePair("user",user_c));
            params_search.add(new BasicNameValuePair("pass",pass_c));

            JSONObject json_search = jParser_search.getJSONFromUrl(url_search,params_search);
            return json_search;
        }
        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);
            //Log.i("myJson", json.toString());
            prg.setVisibility(View.INVISIBLE);
            browselist_search.clear();
            try {

                if(json!=null) {
                    Search_ac = json.getJSONArray(TAG_PLANTRACK_search);
                    for (int i = 0; i < Search_ac.length(); i++) {
                        JSONObject cs_search = Search_ac.getJSONObject(i);


                        String _code_s = cs_search.getString(CODE_S);
                        String _name_s = cs_search.getString(NAME_S);

                        HashMap<String, String> map_;

                        if (type == 1 && !filter_c.equals("without filter")) {
                            if (_code_s.startsWith(filter_c)) {
                                map_ = new HashMap<String, String>();

                                map_.put(CODE_S, _code_s);
                                map_.put(NAME_S, _name_s);

                                browselist_search.add(map_);
                            }
                        }
                        else {

                            map_ = new HashMap<String, String>();

                            map_.put(CODE_S, _code_s);
                            map_.put(NAME_S, _name_s);
                            browselist_search.add(map_);
                        }


                        //   browselist_search.add(map_);


                        ListAdapter adapter_c = new SimpleAdapter(getContext(),
                                browselist_search, R.layout.search_list, new String[]{CODE_S,
                                NAME_S},
                                new int[]{R.id.txt_qnqcode, R.id.txt_qnqname});


                        list_popup_search.setAdapter(adapter_c);

                    }

                }
                else
                {
                    Toast.makeText(getContext(), "Məhsul Tapılmadı", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("JsonError", e.toString());
              //  Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
            }

        }
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
        }


    }


}
