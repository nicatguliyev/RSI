package layout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Common.PlanList;
import com.example.suleyman.project_a.JSONParser;
import com.example.suleyman.project_a.Login;
import com.example.suleyman.project_a.R;
import com.example.suleyman.project_a.dialog;
import com.example.suleyman.project_a.navi_menu;
import com.example.suleyman.project_a.navi_menu2;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Plan extends Fragment {
    ListView list;
    ProgressBar prg;
    JSONArray Plan = null;
    private static String url = "https://ady.express/Plan.asmx/All_Report";

    //JSON Node Names
    private static final String TAG_PLANTRACK = "data";
    private static final String T_GR = "GR";
    private static final String TT_TOTAL_F="TOTAL_F";
    private static final String TT_TOTAL_F_16="TOTAL_F_16";
    public static String GR_AD;
    // ArrayList<HashMap<String, String>> list11;
    View view;
    Button bt2016,bt2017;
    //private static final String T_GOS2 = "Göstəricilər2";

    public Plan() {
        // Required empty public constructor
    }

    String user_="";
    String pass_="";
    String name_="";
    String userId_;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    // TODO: Rename and change types and number of parameters
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_plan, container, false);
        list=(ListView) view.findViewById(R.id.listview);
        user_ = getArguments().getString("sess_user");
        pass_ =  getArguments().getString("sess_pass");
        name_ =  getArguments().getString("sess_name");
        userId_ = getArguments().getString("sess_id");


        PlanList.idxallist = new ArrayList<HashMap<String, String>>();
      //  bt2016=(Button) view.findViewById(R.id.button);
       // bt2016.setEnabled(false);
       // bt2017=(Button) view.findViewById(R.id.button4);
       //8 bt2017.setEnabled(false);


        new JSONParse().execute();
        return view;
    }

    public void onBackPressed(){
        Toast.makeText(getContext(), "hfkjhfjshkfldlkf", Toast.LENGTH_SHORT).show();

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
            // List params = new ArrayList();
            params.add(new BasicNameValuePair("type","LW"));
            params.add(new BasicNameValuePair("user",user_));
            params.add(new BasicNameValuePair("pass",pass_));


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
                Plan = json.getJSONArray(TAG_PLANTRACK);
                PlanList.idxallist = new ArrayList<HashMap<String, String>>();
                for (int i = 0; i < Plan.length(); i++) {
                    JSONObject c = Plan.getJSONObject(i);
                    String gr = c.getString(T_GR);
                    String tttotal = c.getString(TT_TOTAL_F);
                    String tttotal_16 = c.getString(TT_TOTAL_F_16);

                    java.text.DecimalFormat formatter,formatter1;
                    formatter = new java.text.DecimalFormat("#,###,###");


                    tttotal = formatter.format(Float.parseFloat(tttotal));
                    tttotal_16 = formatter.format(Float.parseFloat(tttotal_16));
// dblVariable is a number variable and not a String in this case
                    //  txtTextField.setText(precision.format(dblVariable));
                    // String.format("%.2f", ton1);
                    HashMap<String, String> map = new HashMap<String, String>();
                    // adding each child node to HashMap key => value
                    map.put(T_GR,gr);
                    map.put(TT_TOTAL_F,tttotal);
                    map.put(TT_TOTAL_F_16,tttotal_16);
                    PlanList.idxallist.add(map);
                    if(getActivity()!=null) {
                        ListAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(),
                                PlanList.idxallist, R.layout.plan__desg, new String[]{
                                T_GR, TT_TOTAL_F_16, TT_TOTAL_F},
                                new int[]{R.id.headerGR, R.id.total2016, R.id.total2017});
                        list.setAdapter(adapter);
                    }
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                TextView txt=(TextView) view.findViewById(R.id.headerGR);
                            GR_AD=txt.getText().toString();
                            //Toast.makeText(getContext(), ""+txt.getText().toString(), Toast.LENGTH_SHORT).show();
                            dialog dg=new dialog(getActivity());
                            int width = (int)(getResources().getDisplayMetrics().widthPixels*2);
                            int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);
                            dg.getWindow().setLayout(width, height);
                            dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dg.show();


                        }
                    });
                }

                //  lists.add(tno);
                //  lists.add();




            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
            }

        }
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
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
                    // handle back button's click listener
                    Intent intent = new Intent(getActivity(), navi_menu2.class);
                    intent.putExtra("sess_user", user_);
                    intent.putExtra("sess_pass", pass_);
                    intent.putExtra("sess_name", name_);
                    intent.putExtra("sess_id", userId_);
                    intent.putStringArrayListExtra("menular", Menular.menular);
                    getActivity().finish();
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.action_send).setVisible(true);
    }
}