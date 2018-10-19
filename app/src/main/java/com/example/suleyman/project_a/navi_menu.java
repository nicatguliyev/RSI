package com.example.suleyman.project_a;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

import layout.Plan;

public class navi_menu extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = "THEMES";
    private boolean isLight;
    private boolean isChecked;
    private TextView tv;
    private int currentTheme;
    private int oldTheme;
    public static String sd;
    static String DATABASE_NAME = "DemoDataBase";

    public static final String KEY_ID = "id";

    public static final String TABLE_NAME = "demoTable";

    public static final String KEY_Langitem = "langval";

    public static final String KEY_Themeitem = "themeval";

    SQLiteDatabase SQLITEDATABASE;
    String GetSQliteQuery, UpdateRecordQuery, DeleteQuery;
    Cursor cursor;
    int langval, themeval;
    ListView list1, list2, list3, list4, list5, list_main;
    Animation slideUpAnimation, slideDownAnimation, animnull;
    private ViewGroup sldlay1, sldlay2, sldlay3, sldlay4, sldlay5;
    private boolean isPanelShown, ispanelshow2, ispanelshow3, ispanelshow4, ispanelshow5;
    Button sldbtn1, sldbtn2, sldbtn3, sldbtn4, sldbtn5;
    RelativeLayout rel_lay1, rel_lay2, rel_lay3, rel_lay4, rel_lay5;
    TextView txt, txt_tr_date, txt_in_date, txt_im_date, txt_ex_date, txt_total_date, lbl_im, lbl_ex, lbl_tr, lbl_in, lbl_total;
    Button datestart, dateend;
    DatePickerDialog datePickerDialog;
    int mYear, mYear2;
    int mMonth, mMonth2;
    int mDay, mDay2;
    ArrayList<HashMap<String, String>> idxallist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> ixraclist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> tranzitllist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> daxililist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> umumilist = new ArrayList<HashMap<String, String>>();
    JSONArray Plan = null;
    private static String url = "https://ady.express/Plan.asmx/Daily_Report2";

    //JSON Node Names
    private static final String TAG_PLANTRACK = "data";

    private static final String T_GOS1 = "Göstəricilər1";
    private static final String TT_TON1 = "TON1";
    private static final String T_GOS2 = "Göstəricilər2";
    private static final String TT_TON2 = "TON2";
    private static final String T_GOS3 = "Göstəricilər3";
    private static final String TT_TON3 = "TON3";
    private static final String T_GOS4 = "Göstəricilər4";
    private static final String TT_TON4 = "TON4";
    private static final String T_GOS5 = "Göstəricilər41";
    private static final String TT_TON5 = "TON5";

    private static String T_DAILY = "";
    private static String T_MONTHLY = "";
    private static String T_2016_M = "";
    private static String T_2016 = "";
    private static String T_2017 = "";
    private static String T_2017_PRW = "";

    private static final String T_FCTTYPE = "FCT_TYPE";
    private static final String T_MONTH = "M";
    private static final String T_DAY = "D";
    private static final String T_CURR = "ADY_CURR";
    private static final String T_PRW = "ADY_PRW";
    private static final String T_DIF = "DIF";
    private static final String T_PR = "PR";
    private static final String T_CURR_X = "ADY_CURR_X";
    private static final String T_PRW_X = "ADY_PRW_X";
    private static final String T_CURR_M_X = "ADY_CURR_M_X";


    String user_ = "";
    String pass_ = "";
    String name_ = "";

    public navi_menu() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_menu);

        user_ = getIntent().getStringExtra("sess_user");
        pass_ = getIntent().getStringExtra("sess_pass");
        name_ = getIntent().getStringExtra("sess_name");
        //final ArrayList<String> menular = getIntent().getStringArrayListExtra("menular");

        TextView tvw = (TextView) findViewById(R.id.txt_userfname);
        tvw.setText(name_);
        new JSONParse().execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        DBCreate();
        final Animation moveup = AnimationUtils.loadAnimation(navi_menu.this, R.anim.move_up);
        isPanelShown = false;
        ispanelshow2 = false;
        ispanelshow3 = false;
        ispanelshow4 = false;
        ispanelshow5 = false;
        rel_lay1 = (RelativeLayout) findViewById(R.id.relativeLayout);
        rel_lay2 = (RelativeLayout) findViewById(R.id.rel_lay2);
        rel_lay3 = (RelativeLayout) findViewById(R.id.rel_lay3);
        rel_lay4 = (RelativeLayout) findViewById(R.id.rel_lay4);
        rel_lay5 = (RelativeLayout) findViewById(R.id.rel_lay5);
        sldlay1 = (ViewGroup) findViewById(R.id.sldlay1);
        sldlay1.setVisibility(View.INVISIBLE);
        sldlay2 = (ViewGroup) findViewById(R.id.sldlay2);
        sldlay2.setVisibility(View.INVISIBLE);
        sldlay3 = (ViewGroup) findViewById(R.id.sldlay3);
        sldlay3.setVisibility(View.INVISIBLE);
        sldlay4 = (ViewGroup) findViewById(R.id.sldlay4);
        sldlay4.setVisibility(View.INVISIBLE);
        sldlay5 = (ViewGroup) findViewById(R.id.sldlay5);
        sldlay5.setVisibility(View.INVISIBLE);
        datestart = (Button) findViewById(R.id.dtstart);
        dateend = (Button) findViewById(R.id.dtend);
        sldbtn1 = (Button) findViewById(R.id.SLDBTN1);
        sldbtn2 = (Button) findViewById(R.id.sldbtn2);
        sldbtn3 = (Button) findViewById(R.id.sldbtn3);
        sldbtn4 = (Button) findViewById(R.id.sldbtn4);
        sldbtn5 = (Button) findViewById(R.id.sldbtn5);
        list1 = (ListView) findViewById(R.id.list1);
        //list1.setEnabled(false);
        list2 = (ListView) findViewById(R.id.list2);
        // list2.setEnabled(false);
        list3 = (ListView) findViewById(R.id.list3);
        // list3.setEnabled(false);
        list4 = (ListView) findViewById(R.id.list4);
        //  list4.setEnabled(false);
        list5 = (ListView) findViewById(R.id.list5);
        //  list5.setEnabled(false);
        list_main = (ListView) findViewById(R.id.list_main);
        list_main.setEnabled(false);

        txt_tr_date = (TextView) findViewById(R.id.txt_tr_date);
        txt_in_date = (TextView) findViewById(R.id.txt_in_date);
        txt_im_date = (TextView) findViewById(R.id.txt_im_date);
        txt_ex_date = (TextView) findViewById(R.id.txt_ex_date);
        txt_total_date = (TextView) findViewById(R.id.txt_total_date);

        lbl_im = (TextView) findViewById(R.id.lbl_im);
        lbl_ex = (TextView) findViewById(R.id.lbl_ex);
        lbl_tr = (TextView) findViewById(R.id.lbl_tr);
        lbl_in = (TextView) findViewById(R.id.lbl_in);
        lbl_total = (TextView) findViewById(R.id.lbl_total);


        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        final int mil = calendar.get(java.util.Calendar.YEAR);
        final int may = calendar.get(java.util.Calendar.MONTH);
        final int mgun = calendar.get(java.util.Calendar.DATE);

        final java.text.DecimalFormat formatter;
        formatter = new java.text.DecimalFormat("00");


        //  datestart.setText(formatter.format(Float.parseFloat(String.valueOf(mgun))) + "."
        //        + ( formatter.format(Float.parseFloat(String.valueOf(may+1))) ) + "." + mil);
        datestart.setText("01.01.2017");
        datestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int msaat = calendar.get(java.util.Calendar.HOUR);
                int mdeq = calendar.get(java.util.Calendar.MINUTE);
                int msan = calendar.get(java.util.Calendar.SECOND);
                int mSec = calendar.get(java.util.Calendar.MILLISECOND);
                datePickerDialog = new DatePickerDialog(navi_menu.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                // set day of month , month and year value in the edit text
                                datestart.setText(formatter.format(Float.parseFloat(String.valueOf(dayOfMonth))) + "."
                                        + (formatter.format(Float.parseFloat(String.valueOf(monthOfYear + 1)))) + "." + year);
                                txt_tr_date.setText(datestart.getText() + "-" + dateend.getText());
                                txt_in_date.setText(datestart.getText() + "-" + dateend.getText());
                                txt_im_date.setText(datestart.getText() + "-" + dateend.getText());
                                txt_ex_date.setText(datestart.getText() + "-" + dateend.getText());
                                txt_total_date.setText(datestart.getText() + "-" + dateend.getText());
                            }
                        }, mil, may, mgun);
                datePickerDialog.show();

                new JSONParse().execute();
                // date picker dialog
            }
        });

        final int mil_e = calendar.get(java.util.Calendar.YEAR);
        final int may_e = calendar.get(java.util.Calendar.MONTH);
        final int mgun_e = calendar.get(java.util.Calendar.DATE);

        dateend.setText(formatter.format(Float.parseFloat(String.valueOf(mgun_e))) + "."
                + (formatter.format(Float.parseFloat(String.valueOf(may_e + 1)))) + "." + mil_e);


        dateend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();

                int msaat = calendar.get(java.util.Calendar.HOUR);
                int mdeq = calendar.get(java.util.Calendar.MINUTE);
                int msan = calendar.get(java.util.Calendar.SECOND);
                int mSec = calendar.get(java.util.Calendar.MILLISECOND);
                datePickerDialog = new DatePickerDialog(navi_menu.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                // set day of month , month and year value in the edit text
                                dateend.setText(formatter.format(Float.parseFloat(String.valueOf(dayOfMonth))) + "."
                                        + (formatter.format(Float.parseFloat(String.valueOf(monthOfYear + 1)))) + "." + year);
                                txt_tr_date.setText(datestart.getText() + "-" + dateend.getText());
                                txt_in_date.setText(datestart.getText() + "-" + dateend.getText());
                                txt_im_date.setText(datestart.getText() + "-" + dateend.getText());
                                txt_ex_date.setText(datestart.getText() + "-" + dateend.getText());
                                txt_total_date.setText(datestart.getText() + "-" + dateend.getText());
                            }
                        }, mil_e, may_e, mgun_e);
                datePickerDialog.show();
                new JSONParse().execute();
                // date picker dialog
            }
        });

        txt_tr_date.setText(datestart.getText() + "-" + dateend.getText());
        txt_in_date.setText(datestart.getText() + "-" + dateend.getText());
        txt_im_date.setText(datestart.getText() + "-" + dateend.getText());
        txt_ex_date.setText(datestart.getText() + "-" + dateend.getText());
        txt_total_date.setText(datestart.getText() + "-" + dateend.getText());


        rel_lay1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld1();
            }
        });
        sldbtn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld1();
            }
        });
        rel_lay2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld2();
            }
        });
        sldbtn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld2();
            }
        });
        rel_lay3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld3();
            }
        });
        sldbtn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld3();
            }
        });
        rel_lay4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld4();
            }
        });
        sldbtn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld4();
            }
        });
        rel_lay5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld5();
            }
        });
        sldbtn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Sld5();
            }
        });
        GetSQliteQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id=1";
        cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);
        if (cursor.moveToFirst()) {

            do {
                langval = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Langitem));
                themeval = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Themeitem));

            } while (cursor.moveToNext());

        }
        cursor.close();


        Menu menu = navigationView.getMenu();
        //nav_prognose
        menu.findItem(R.id.nav_plan).setTitle(LLanguage._nav_plan[langval]);
        menu.findItem(R.id.nav_in_search).setTitle(LLanguage._nav_insearch[langval]);
        menu.findItem(R.id.nav_daily).setTitle(LLanguage._nav_daily[langval]);
        menu.findItem(R.id.nav_prognose).setTitle(LLanguage._nav_prognose[langval]);
        menu.findItem(R.id.nav_graf).setTitle(LLanguage._nav_gra[langval]);
        menu.findItem(R.id.nav_share).setTitle(LLanguage._nav_share[langval]);
        menu.findItem(R.id.nav_Setting).setTitle(LLanguage._nav_settings[langval]);
        final MenuItem calculator = menu.findItem(R.id.calculator);
        final MenuItem proqnoz = menu.findItem(R.id.nav_prognose);
        final MenuItem planMenu = menu.findItem(R.id.nav_plan);
        final MenuItem gunlukMenu = menu.findItem(R.id.nav_daily);
        final MenuItem searchMenu = menu.findItem(R.id.nav_in_search);
        final MenuItem graphMneu = menu.findItem(R.id.nav_graf);

       /* if (!menular.contains("Günlük Hesabat")) {
            gunlukMenu.setVisible(false);
        }

        if (!menular.contains("Kalkulyator")) {
            calculator.setVisible(false);
        }

        if (!menular.contains("Plan")) {
            planMenu.setVisible(false);
        }

        if (!menular.contains("Intellektual Axtaris")) {
            searchMenu.setVisible(false);
        }

        if (!menular.contains("Qrafik")) {
            graphMneu.setVisible(false);
        }

        if (!menular.contains("Proqnoz")) {
            proqnoz.setVisible(false);
        }*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home) {
                    Intent intent = new Intent(navi_menu.this, navi_menu.class);
                    intent.putExtra("sess_user", user_);
                    intent.putExtra("sess_pass", pass_);
                    intent.putExtra("sess_name", name_);
                    startActivity(intent);
                } else if (menuItem.getItemId() == R.id.nav_plan) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                    Plan pl = new Plan();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);

                    pl.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu, pl, "pbg");
                    fragmenttarnsc.commit();
                    // Handle the camera action
                } else if (menuItem.getItemId() == R.id.calculator) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                    CalculateFragment calculateFragment = new CalculateFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);

                    calculateFragment.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu, calculateFragment, "pbg");
                    fragmenttarnsc.commit();

                } else if (menuItem.getItemId() == R.id.nav_in_search) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                    InSearch insrch = new InSearch();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);

                    insrch.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu, insrch, "pbg");
                    fragmenttarnsc.commit();
                    // Handle the camera action
                } else if (menuItem.getItemId() == R.id.nav_prognose) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                    Prognose prg = new Prognose();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);

                    prg.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu, prg, "pbg");
                    fragmenttarnsc.commit();
                    // Handle the camera action
                } else if (menuItem.getItemId() == R.id.nav_daily) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                    Daily dly = new Daily();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);

                    dly.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu, dly, "pbg");
                    fragmenttarnsc.commit();
                    // Handle the camera action
                } else if (menuItem.getItemId() == R.id.nav_graf) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    TabFragment tbfr = new TabFragment();
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu, tbfr, "pbg");
                    fragmenttarnsc.commit();

                } else if (menuItem.getItemId() == R.id.nav_share) {

                } else if (menuItem.getItemId() == R.id.nav_feedback) {

                } else if (menuItem.getItemId() == R.id.nav_Setting) {
                    Intent intent = new Intent(navi_menu.this, Settings.class);
                    startActivity(intent);

                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            }
        });

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(navi_menu.this, Login.class);
            intent.putExtra("sess_user", "");
            intent.putExtra("sess_pass", "");
            intent.putExtra("sess_name", "");
            startActivity(intent);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void DBCreate() {

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_Langitem + " INTEGER, " + KEY_Themeitem + " INTEGER)");
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            // List params = new ArrayList();
            params.add(new BasicNameValuePair("year", "2017"));
            params.add(new BasicNameValuePair("month", "9"));
            params.add(new BasicNameValuePair("user", user_));
            params.add(new BasicNameValuePair("pass", pass_));


            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url, params);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            super.onPostExecute(json);
            ixraclist.clear();
            idxallist.clear();
            String substr = dateend.getText().toString().substring(0, 2);
            String substr2 = dateend.getText().toString().substring(3, 5);


            String month_w = "";
            String month_w3 = "";
            String month_w2 = "";

           // Toast.makeText(getApplicationContext(), "-" + substr2, Toast.LENGTH_SHORT).show();

            if (substr2.equals("01")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-Yanvar";
                month_w = "2017-Yanvar ayı ərzində";
                month_w3 = "2016-Yanvar ayı ərzində";
            } else if (substr2.equals("02")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-Yanvar";
                month_w = "2017-Fevral ayı ərzində";
                month_w3 = "2016-Fevral ayı ərzində";
            } else if (substr2.equals("03")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-Fevral";
                month_w = "2017-Mart ayı ərzində";
                month_w3 = "2016-Mart ayı ərzində";
            } else if (substr2.equals("04")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-Mart";
                month_w = "2017-Aprel ayı ərzində";
                month_w3 = "2016-Aprel ayı ərzində";
            } else if (substr2.equals("05")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-Aprel";
                month_w = "2017-May ayı ərzində";
                month_w3 = "2016-May ayı ərzində";
            } else if (substr2.equals("06")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-May";
                month_w = "2017-İyun ayı ərzində";
                month_w3 = "2016-İyun ayı ərzində";
            } else if (substr2.equals("07")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-İyun";
                month_w = "2017-İyul ayı ərzində";
                month_w3 = "2016-İyul ayı ərzində";
            } else if (substr2.equals("08")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-İyul";
                month_w = "2017-Avqust ayı ərzində";
                month_w3 = "2016-Avqust ayı ərzində";
            } else if (substr2.equals("09")) {
                Toast.makeText(getApplicationContext(), "tyupmk" + substr2, Toast.LENGTH_SHORT).show();

                month_w2 = "2017-Avqust";
                month_w = "2017-Sentyabr ayı ərzində";
                month_w3 = "2016-Sentyabr ayı ərzində";
            } else if (substr2.equals("10")) {
                month_w2 = "2017-Sentyabr";
                month_w = "2017-Oktyabr ayı ərzində";
                month_w3 = "2016-Oktyabr ayı ərzində";
            } else if (substr2.equals("11")) {
                month_w2 = "2017-Oktyabr";
                month_w = "2017-Noyabr ayı ərzində";
                month_w3 = "2016-Noyabr ayı ərzində";
            } else if (substr2.equals("12")) {
                month_w2 = "2017-Noyabr";
                month_w = "2017-Dekabr ayı ərzində";
                month_w3 = "2016-Dekabr ayı ərzində";
            }
            T_MONTHLY = month_w;
            T_2016_M = month_w3;

            T_DAILY = "Günlük";
            T_2016_M = "2016-Aylıq";
            T_2016 = "2016";
            T_2017 = "2017";


            try {
                // Getting JSON Array from URL
                Plan = json.getJSONArray(TAG_PLANTRACK);
                List<Daily_R> personList = new ArrayList<>();
                for (int i = 0; i < Plan.length(); i++) {

                    JSONObject c = Plan.getJSONObject(i);


                    String fcttype_ = c.getString(T_FCTTYPE);
                    String month_ = c.getString(T_MONTH);
                    String day_ = c.getString(T_DAY);
                    String curr_ = c.getString(T_CURR);
                    String prw_ = c.getString(T_PRW);
                    String dif_ = c.getString(T_DIF);
                    String pr_ = c.getString(T_PR);
                    String curr_x = c.getString(T_CURR_X);
                    String prw_x = c.getString(T_PRW_X);
                    String curr_m_x = c.getString(T_CURR_M_X);


                    java.text.DecimalFormat formatter, formatter2;
                    java.text.NumberFormat formatter1;
                    formatter = new java.text.DecimalFormat("#,###,###.00");
                    formatter2 = new java.text.DecimalFormat(" #,##0 %");
                    long time = 9;

                    String format = "%1$02d"; // two digits
                    day_ = String.format(format, Integer.parseInt(day_));

                    curr_ = formatter.format(Float.parseFloat(curr_));
                    prw_ = formatter.format(Float.parseFloat(prw_));

                    prw_x = formatter.format(Float.parseFloat(prw_x));
                    curr_m_x = formatter.format(Float.parseFloat(curr_m_x));
                    curr_x = formatter.format(Float.parseFloat(curr_x));
                    dif_ = formatter.format(Float.parseFloat(dif_));


                    // Toast.makeText(getApplicationContext(), "lhkhkh"+day_, Toast.LENGTH_SHORT).show();


                    if ((fcttype_.toString().equals("0")) && (day_.equals(substr)) && !(day_.equals("32"))) {

                        HashMap<String, String> map1 = new HashMap<String, String>();
                        map1.put(T_CURR, curr_);
                        map1.put(T_PRW_X, prw_x);
                        map1.put(T_CURR_M_X, curr_m_x);
                        map1.put(T_CURR_X, curr_x);
                        map1.put(T_DAILY, "Günlük");
                        map1.put(T_2016, "2016");
                        map1.put(T_2017, "2017");
                        map1.put(T_2017_PRW, month_w2);
                        ixraclist.add(map1);
                        ListAdapter adapter6 = new SimpleAdapter(navi_menu.this,
                                ixraclist, R.layout.main_menu_listdesg, new String[]{
                                T_DAILY, T_2016, T_2017, T_2017_PRW, T_CURR, T_PRW_X, T_CURR_M_X, T_CURR_X},
                                new int[]{R.id.lbl_daily, R.id.lbl_2016, R.id.lbl_2017, R.id.lbl_2016_prw, R.id.txt_daily, R.id.txt_2016, R.id.txt_2017, R.id.txt_2016_prw});
                        list_main.setAdapter(adapter6);
                    }

                    if ((fcttype_.toString().equals("1")) && (day_.equals("32"))) {

                        pr_ = formatter2.format(Float.parseFloat(pr_));
                       // Toast.makeText(getApplicationContext(), "vvvv" + day_, Toast.LENGTH_SHORT).show();

                        HashMap<String, String> map2 = new HashMap<String, String>();
                        map2.put(T_CURR, curr_);
                        map2.put(T_PRW_X, prw_);
                        map2.put(T_CURR_M_X, dif_);
                        map2.put(T_CURR_X, pr_);
                        map2.put(T_MONTHLY, month_w);
                        map2.put(T_2016_M, month_w3);
                        map2.put(T_2017, "Fərq");
                        map2.put(T_2017_PRW, "Fərq%");
                        idxallist.add(map2);
                        ListAdapter adapter6 = new SimpleAdapter(navi_menu.this,
                                idxallist, R.layout.main_menu_listdesg, new String[]{
                                T_MONTHLY, T_2016_M, T_2017, T_2017_PRW, T_CURR, T_PRW_X, T_CURR_M_X, T_CURR_X},
                                new int[]{R.id.lbl_daily, R.id.lbl_2016, R.id.lbl_2017, R.id.lbl_2016_prw, R.id.txt_daily, R.id.txt_2016, R.id.txt_2017, R.id.txt_2016_prw});
                        list1.setAdapter(adapter6);
                    }

                  /*  java.text.DecimalFormat formatter;
                    if (  !(gos1.toString().equals("Fərq %") || gos1.toString().equals("Fakt üzrə yaranacaq fərq %") || gos1.toString().equals("Fakt üzrə planın yerinə yetrilməsi") ||  gos1.toString().equals("Planın yerinə yetirilməsi %"))) {
                        formatter = new java.text.DecimalFormat("#,###,###.00");
                    }
                    else{
                     formatter = new java.text.DecimalFormat(" #,##0.00 %");
                    }
                    String yourFormattedString = formatter.format(Float.parseFloat(ton1));
                    String yourFormattedString2 = formatter.format(Float.parseFloat(ton2));
                    String yourFormattedString3 = formatter.format(Float.parseFloat(ton3));
                    String yourFormattedString4 = formatter.format(Float.parseFloat(ton4));
                    String yourFormattedString5 = formatter.format(Float.parseFloat(ton5));
// dblVariable is a number variable and not a String in this case
                  //  txtTextField.setText(precision.format(dblVariable));
                   // String.format("%.2f", ton1);
                    String a []={"sdsd,ff,frg"};
                        HashMap<String, String> map = new HashMap<String, String>();

                        map.put(T_GOS1,gos1);
                        map.put(TT_TON1,yourFormattedString);
                        map.put(T_GOS2,gos2);
                        map.put(TT_TON2,yourFormattedString2);
                        map.put(T_GOS3,gos3);
                        map.put(TT_TON3,yourFormattedString3);
                        map.put(T_GOS4,gos4);
                        map.put(TT_TON4,yourFormattedString4);
                        map.put(T_GOS5,gos5);
                        map.put(TT_TON5,yourFormattedString5);

                        idxallist.add(map);

                        ListAdapter adapter = new SimpleAdapter(navi_menu.this,
                                idxallist, R.layout.main_menu_listdesg, new String[] {
                                T_GOS1,TT_TON1},
                                new int[] { R.id.txt_gos, R.id.txt_ton});
                        list1.setAdapter(adapter);
                    ListAdapter adapter2 = new SimpleAdapter(navi_menu.this,
                            idxallist, R.layout.main_menu_listdesg, new String[] {
                            T_GOS2,TT_TON2},
                            new int[] { R.id.txt_gos, R.id.txt_ton});
                    list2.setAdapter(adapter2);
                    ListAdapter adapter3 = new SimpleAdapter(navi_menu.this,
                            idxallist, R.layout.main_menu_listdesg, new String[] {
                            T_GOS3,TT_TON3},
                            new int[] { R.id.txt_gos, R.id.txt_ton});
                    list3.setAdapter(adapter3);
                    ListAdapter adapter4 = new SimpleAdapter(navi_menu.this,
                            idxallist, R.layout.main_menu_listdesg, new String[] {
                            T_GOS4,TT_TON4},
                            new int[] { R.id.txt_gos, R.id.txt_ton});
                    list4.setAdapter(adapter4);
                    ListAdapter adapter5 = new SimpleAdapter(navi_menu.this,
                            idxallist, R.layout.main_menu_listdesg, new String[] {
                            T_GOS5,TT_TON5},
                            new int[] { R.id.txt_gos, R.id.txt_ton});
                    list5.setAdapter(adapter5);

                    if(gos5.toString().equals("Ümumi Plan")) {
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        map1.put(T_GOS5,"İllik planın həcmi");
                        map1.put(TT_TON5,yourFormattedString5);
                        ixraclist.add(map1);
                        ListAdapter adapter6 = new SimpleAdapter(navi_menu.this,
                                ixraclist, R.layout.main_menu_listdesg, new String[]{
                                T_GOS5, TT_TON5},
                                new int[]{R.id.txt_gos, R.id.txt_ton});
                        list_main.setAdapter(adapter6);
                    }
                    else if (gos5.toString().equals("Fakt üzrə"))
                    {
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        map1.put(T_GOS5,"İllik gözlənilən yük həcmi");
                        map1.put(TT_TON5,yourFormattedString5);
                        ixraclist.add(map1);
                        ListAdapter adapter6 = new SimpleAdapter(navi_menu.this,
                                ixraclist, R.layout.main_menu_listdesg, new String[]{
                                T_GOS5, TT_TON5},
                                new int[]{R.id.txt_gos, R.id.txt_ton});
                        list_main.setAdapter(adapter6);
                    }
                    else if (gos5.toString().equals("Fakt üzrə planın yerinə yetrilməsi"))
                    {
                        HashMap<String, String> map1 = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        map1.put(T_GOS5,"İllik planın icra faizi");
                        map1.put(TT_TON5,yourFormattedString5);
                        ixraclist.add(map1);
                        ListAdapter adapter6 = new SimpleAdapter(navi_menu.this,
                                ixraclist, R.layout.main_menu_listdesg, new String[]{
                                T_GOS5, TT_TON5},
                                new int[]{R.id.txt_gos, R.id.txt_ton});
                        list_main.setAdapter(adapter6);
                    }
                }
                list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                      //  Toast.makeText(getApplicationContext(), "jgjgjhg", Toast.LENGTH_SHORT).show();
                        Sld1();
                    }
                });


                list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Sld2();
                    }
                });
                list3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Sld3();
                    }
                });
                list4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Sld4();
                    }
                });
                list5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Sld5();
                    }
                });

*/
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "" + e.toString(), Toast.LENGTH_SHORT).show();
            }


        }

        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
        }

    }

    protected void Sld1() {
        if (!isPanelShown) {
            // Show the panel

            Animation bottomUp = AnimationUtils.loadAnimation(navi_menu.this, R.anim.bottom_up);
            Animation rotate_up = AnimationUtils.loadAnimation(navi_menu.this, R.anim.rotate_up);
            Animation move_up = AnimationUtils.loadAnimation(navi_menu.this, R.anim.move_up);
            Animation move_up1 = AnimationUtils.loadAnimation(navi_menu.this, R.anim.move_up1);
            Animation move_up2 = AnimationUtils.loadAnimation(navi_menu.this, R.anim.moveup2);
            Animation move_up3 = AnimationUtils.loadAnimation(navi_menu.this, R.anim.moveup3);

            rel_lay2.startAnimation(move_up);
            rel_lay3.startAnimation(move_up1);
            rel_lay4.startAnimation(move_up2);
            rel_lay5.startAnimation(move_up3);

            sldlay1.startAnimation(bottomUp);
            sldlay1.setVisibility(View.VISIBLE);
            sldbtn1.startAnimation(rotate_up);

           /* rel_lay2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay3.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay4.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay5.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));*/

/*
            sldbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));*/

            // txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
           /* txt_ex_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_tr_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_in_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_total_date.setTextColor(Color.parseColor("#cb9f5e"));*/

            //  lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            /*lbl_ex.setTextColor(Color.parseColor("#8f939b"));
            lbl_tr.setTextColor(Color.parseColor("#8f939b"));
            lbl_in.setTextColor(Color.parseColor("#8f939b"));
            lbl_total.setTextColor(Color.parseColor("#8f939b"));*/

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    1500);
            lp.setMargins(0, 30, 14, 0);
            list1.setLayoutParams(lp);
            // sldlay1.setLayoutParams(lp);


            /*sldbtn2.setEnabled(false);
            sldbtn3.setEnabled(false);
            sldbtn4.setEnabled(false);
            sldbtn5.setEnabled(false);*/

            rel_lay2.setEnabled(false);
            rel_lay3.setEnabled(false);
            rel_lay4.setEnabled(false);
            rel_lay5.setEnabled(false);

            isPanelShown = true;
        } else {
            // Hide the Panel
            Animation bottomUp = AnimationUtils.loadAnimation(navi_menu.this, R.anim.bottom_up);
            Animation bottomDown = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.bottom_down);
            Animation rotate_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_down);
            Animation move_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_down);
            rel_lay2.startAnimation(move_down);
            rel_lay3.startAnimation(move_down);
            rel_lay4.startAnimation(move_down);
            rel_lay5.startAnimation(move_down);

            sldlay1.startAnimation(bottomDown);
            sldlay1.setVisibility(View.INVISIBLE);
            sldbtn1.startAnimation(rotate_down);
            sldbtn2.startAnimation(rotate_down);
            sldbtn3.startAnimation(rotate_down);
            sldbtn4.startAnimation(rotate_down);
            sldbtn5.startAnimation(rotate_down);
           /* isPanelShown = false;
            ispanelshow2 = false;
            ispanelshow3 = false;
            ispanelshow4 = false;
            ispanelshow5 = false;*/

            rel_lay2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay3.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay4.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay5.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));

            sldbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));

            // txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_ex_date.setTextColor(Color.parseColor("#fca92c"));
            txt_tr_date.setTextColor(Color.parseColor("#fca92c"));
            txt_in_date.setTextColor(Color.parseColor("#fca92c"));
            txt_total_date.setTextColor(Color.parseColor("#fca92c"));

            //  lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            lbl_ex.setTextColor(Color.parseColor("#ffffff"));
            lbl_tr.setTextColor(Color.parseColor("#ffffff"));
            lbl_in.setTextColor(Color.parseColor("#ffffff"));
            lbl_total.setTextColor(Color.parseColor("#ffffff"));

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    0);
            lp.setMargins(0, 0, 0, 0);
            list1.setLayoutParams(lp);

            sldbtn2.setEnabled(true);
            sldbtn3.setEnabled(true);
            sldbtn4.setEnabled(true);
            sldbtn5.setEnabled(true);

            rel_lay2.setEnabled(true);
            rel_lay3.setEnabled(true);
            rel_lay4.setEnabled(true);
            rel_lay5.setEnabled(true);
        }
    }

    protected void Sld2() {
        if (!ispanelshow2) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(navi_menu.this, R.anim.bottom_up);
            Animation rotate_up = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_up);
            Animation move_up = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_up);
            Animation move_up1 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_up1);
            Animation move_up2 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.moveup2);
            Animation move_up3 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.moveup3);
            rel_lay3.startAnimation(move_up1);
            rel_lay4.startAnimation(move_up2);
            rel_lay5.startAnimation(move_up3);
            sldlay2.startAnimation(bottomUp);
            sldlay2.setVisibility(View.VISIBLE);
            sldbtn2.startAnimation(rotate_up);
            /*rel_lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay3.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay4.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay5.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));

            sldbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));*/

            // txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
           /* txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_tr_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_in_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_total_date.setTextColor(Color.parseColor("#cb9f5e"));

            //  lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            lbl_im.setTextColor(Color.parseColor("#8f939b"));
            lbl_tr.setTextColor(Color.parseColor("#8f939b"));
            lbl_in.setTextColor(Color.parseColor("#8f939b"));
            lbl_total.setTextColor(Color.parseColor("#8f939b"));*/

           /* sldbtn1.setEnabled(false);
            sldbtn3.setEnabled(false);
            sldbtn4.setEnabled(false);
            sldbtn5.setEnabled(false);

            rel_lay1.setEnabled(false);
            rel_lay3.setEnabled(false);
            rel_lay4.setEnabled(false);
            rel_lay5.setEnabled(false);*/

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    1800);
            lp.setMargins(0, 30, 14, 0);
            list2.setLayoutParams(lp);
            ispanelshow2 = true;
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.bottom_down);
            Animation rotate_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_down);
            Animation move_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_down);
            rel_lay3.startAnimation(move_down);
            rel_lay4.startAnimation(move_down);
            rel_lay5.startAnimation(move_down);
            sldlay2.startAnimation(bottomDown);
            sldlay2.setVisibility(View.INVISIBLE);
            sldbtn1.startAnimation(rotate_down);
            sldbtn2.startAnimation(rotate_down);
            sldbtn3.startAnimation(rotate_down);
            sldbtn4.startAnimation(rotate_down);
            sldbtn5.startAnimation(rotate_down);
            isPanelShown = false;
            ispanelshow2 = false;
            ispanelshow3 = false;
            ispanelshow4 = false;
            ispanelshow5 = false;
            rel_lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay3.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay4.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay5.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));

            sldbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));

            // txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_im_date.setTextColor(Color.parseColor("#fca92c"));
            txt_tr_date.setTextColor(Color.parseColor("#fca92c"));
            txt_in_date.setTextColor(Color.parseColor("#fca92c"));
            txt_total_date.setTextColor(Color.parseColor("#fca92c"));

            //  lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            lbl_im.setTextColor(Color.parseColor("#ffffff"));
            lbl_tr.setTextColor(Color.parseColor("#ffffff"));
            lbl_in.setTextColor(Color.parseColor("#ffffff"));
            lbl_total.setTextColor(Color.parseColor("#ffffff"));

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    0);
            lp.setMargins(0, 0, 0, 0);
            list2.setLayoutParams(lp);

            rel_lay1.setEnabled(true);
            rel_lay3.setEnabled(true);
            rel_lay4.setEnabled(true);
            rel_lay5.setEnabled(true);

            sldbtn1.setEnabled(true);
            sldbtn3.setEnabled(true);
            sldbtn4.setEnabled(true);
            sldbtn5.setEnabled(true);
        }
    }

    protected void Sld3() {
        if (!ispanelshow3) {
            Animation bottomUp = AnimationUtils.loadAnimation(navi_menu.this, R.anim.bottom_up);
            Animation rotate_up = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_up);
            Animation move_up = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_up);
            Animation move_up1 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_up1);
            Animation move_up2 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.moveup2);
            Animation move_up3 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.moveup3);
            rel_lay4.startAnimation(move_up2);
            rel_lay5.startAnimation(move_up3);
            sldlay3.startAnimation(bottomUp);
            sldlay3.setVisibility(View.VISIBLE);
            sldbtn3.startAnimation(rotate_up);
            rel_lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay4.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay5.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));

            sldbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));

            // txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_ex_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_in_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_total_date.setTextColor(Color.parseColor("#cb9f5e"));

            //  lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            lbl_im.setTextColor(Color.parseColor("#8f939b"));
            lbl_ex.setTextColor(Color.parseColor("#8f939b"));
            lbl_in.setTextColor(Color.parseColor("#8f939b"));
            lbl_total.setTextColor(Color.parseColor("#8f939b"));

            sldbtn1.setEnabled(false);
            sldbtn2.setEnabled(false);
            sldbtn4.setEnabled(false);
            sldbtn5.setEnabled(false);

            rel_lay1.setEnabled(false);
            rel_lay2.setEnabled(false);
            rel_lay4.setEnabled(false);
            rel_lay5.setEnabled(false);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    1600);
            lp.setMargins(0, 30, 14, 0);
            list3.setLayoutParams(lp);
            ispanelshow3 = true;
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.bottom_down);
            Animation rotate_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_down);
            Animation move_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_down);
            rel_lay4.startAnimation(move_down);
            rel_lay5.startAnimation(move_down);
            sldlay3.startAnimation(bottomDown);
            sldlay3.setVisibility(View.INVISIBLE);
            sldbtn1.startAnimation(rotate_down);
            sldbtn2.startAnimation(rotate_down);
            sldbtn3.startAnimation(rotate_down);
            sldbtn4.startAnimation(rotate_down);
            sldbtn5.startAnimation(rotate_down);
            isPanelShown = false;
            ispanelshow2 = false;
            ispanelshow3 = false;
            ispanelshow4 = false;
            ispanelshow5 = false;
            rel_lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay4.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay5.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));

            sldbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));

            // txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_im_date.setTextColor(Color.parseColor("#fca92c"));
            txt_ex_date.setTextColor(Color.parseColor("#fca92c"));
            txt_in_date.setTextColor(Color.parseColor("#fca92c"));
            txt_total_date.setTextColor(Color.parseColor("#fca92c"));

            //  lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            lbl_im.setTextColor(Color.parseColor("#ffffff"));
            lbl_ex.setTextColor(Color.parseColor("#ffffff"));
            lbl_in.setTextColor(Color.parseColor("#ffffff"));
            lbl_total.setTextColor(Color.parseColor("#ffffff"));

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    0);
            lp.setMargins(0, 0, 0, 0);
            list3.setLayoutParams(lp);


            sldbtn1.setEnabled(true);
            sldbtn2.setEnabled(true);
            sldbtn4.setEnabled(true);
            sldbtn5.setEnabled(true);

            rel_lay1.setEnabled(true);
            rel_lay2.setEnabled(true);
            rel_lay4.setEnabled(true);
            rel_lay5.setEnabled(true);
        }
    }

    protected void Sld4() {
        if (!ispanelshow4) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(navi_menu.this, R.anim.bottom_up);
            Animation rotate_up = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_up);
            Animation move_up = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_up);
            Animation move_up1 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_up1);
            Animation move_up2 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.moveup2);
            Animation move_up3 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.moveup3);
            rel_lay5.startAnimation(move_up3);
            sldlay4.startAnimation(bottomUp);
            sldlay4.setVisibility(View.VISIBLE);
            sldbtn4.startAnimation(rotate_up);
            rel_lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay3.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay5.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));

            sldbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));

            txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_ex_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_tr_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_total_date.setTextColor(Color.parseColor("#cb9f5e"));

            lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            lbl_im.setTextColor(Color.parseColor("#8f939b"));
            lbl_ex.setTextColor(Color.parseColor("#8f939b"));
            lbl_tr.setTextColor(Color.parseColor("#8f939b"));
            lbl_total.setTextColor(Color.parseColor("#8f939b"));


            sldbtn1.setEnabled(false);
            sldbtn2.setEnabled(false);
            sldbtn3.setEnabled(false);
            sldbtn5.setEnabled(false);

            rel_lay1.setEnabled(false);
            rel_lay2.setEnabled(false);
            rel_lay3.setEnabled(false);
            rel_lay5.setEnabled(false);

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    1400);
            lp.setMargins(0, 30, 14, 0);
            list4.setLayoutParams(lp);
            ispanelshow4 = true;
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.bottom_down);
            Animation rotate_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_down);
            Animation move_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_down);
            rel_lay5.startAnimation(move_down);
            sldlay4.startAnimation(bottomDown);
            sldlay4.setVisibility(View.INVISIBLE);
            sldbtn1.startAnimation(rotate_down);
            sldbtn2.startAnimation(rotate_down);
            sldbtn3.startAnimation(rotate_down);
            sldbtn4.startAnimation(rotate_down);
            sldbtn5.startAnimation(rotate_down);
            isPanelShown = false;
            ispanelshow2 = false;
            ispanelshow3 = false;
            ispanelshow4 = false;
            ispanelshow5 = false;
            rel_lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay3.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay5.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));

            sldbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn5.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));

            txt_im_date.setTextColor(Color.parseColor("#fca92c"));
            txt_ex_date.setTextColor(Color.parseColor("#fca92c"));
            txt_tr_date.setTextColor(Color.parseColor("#fca92c"));
            txt_total_date.setTextColor(Color.parseColor("#fca92c"));

            lbl_im.setTextColor(Color.parseColor("#ffffff"));
            lbl_ex.setTextColor(Color.parseColor("#ffffff"));
            lbl_tr.setTextColor(Color.parseColor("#ffffff"));
            lbl_total.setTextColor(Color.parseColor("#ffffff"));


            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    0);
            lp.setMargins(0, 0, 0, 0);
            list4.setLayoutParams(lp);

            rel_lay1.setEnabled(true);
            rel_lay2.setEnabled(true);
            rel_lay3.setEnabled(true);
            rel_lay5.setEnabled(true);


            sldbtn1.setEnabled(true);
            sldbtn2.setEnabled(true);
            sldbtn3.setEnabled(true);
            sldbtn5.setEnabled(true);
        }
    }

    protected void Sld5() {
        if (!ispanelshow5) {
            Animation bottomUp = AnimationUtils.loadAnimation(navi_menu.this, R.anim.bottom_up);
            Animation rotate_up = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_up);
            Animation move_up = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_up);
            Animation move_up1 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_up1);
            Animation move_up2 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.moveup2);
            Animation move_up3 = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.moveup3);
            sldlay5.startAnimation(bottomUp);
            sldlay5.setVisibility(View.VISIBLE);
            sldbtn5.startAnimation(rotate_up);
            rel_lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay3.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));
            rel_lay4.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck3));

            sldbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));
            sldbtn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn_blue));

            // txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_ex_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_tr_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_in_date.setTextColor(Color.parseColor("#cb9f5e"));

            //  lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            lbl_im.setTextColor(Color.parseColor("#8f939b"));
            lbl_ex.setTextColor(Color.parseColor("#8f939b"));
            lbl_tr.setTextColor(Color.parseColor("#8f939b"));
            lbl_in.setTextColor(Color.parseColor("#8f939b"));

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    800);
            lp.setMargins(0, 30, 14, 0);
            list5.setLayoutParams(lp);

            sldbtn1.setEnabled(false);
            sldbtn2.setEnabled(false);
            sldbtn3.setEnabled(false);
            sldbtn4.setEnabled(false);

            rel_lay1.setEnabled(false);
            rel_lay2.setEnabled(false);
            rel_lay3.setEnabled(false);
            rel_lay4.setEnabled(false);
            ispanelshow5 = true;
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.bottom_down);
            Animation rotate_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.rotate_down);
            Animation move_down = AnimationUtils.loadAnimation(navi_menu.this,
                    R.anim.move_down);
            sldlay5.startAnimation(bottomDown);
            sldlay5.setVisibility(View.INVISIBLE);
            sldbtn1.startAnimation(rotate_down);
            sldbtn2.startAnimation(rotate_down);
            sldbtn3.startAnimation(rotate_down);
            sldbtn4.startAnimation(rotate_down);
            sldbtn5.startAnimation(rotate_down);
            isPanelShown = false;
            ispanelshow2 = false;
            ispanelshow3 = false;
            ispanelshow4 = false;
            ispanelshow5 = false;
            rel_lay1.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay3.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));
            rel_lay4.setBackgroundDrawable(getResources().getDrawable(R.drawable.rlw_bck));

            sldbtn1.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn2.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn3.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));
            sldbtn4.setBackgroundDrawable(getResources().getDrawable(R.drawable.slide_btn));

            // txt_im_date.setTextColor(Color.parseColor("#cb9f5e"));
            txt_im_date.setTextColor(Color.parseColor("#fca92c"));
            txt_ex_date.setTextColor(Color.parseColor("#fca92c"));
            txt_tr_date.setTextColor(Color.parseColor("#fca92c"));
            txt_in_date.setTextColor(Color.parseColor("#fca92c"));

            //  lbl_im.setTextColor(Color.parseColor("#cb9f5e"));
            lbl_im.setTextColor(Color.parseColor("#ffffff"));
            lbl_ex.setTextColor(Color.parseColor("#ffffff"));
            lbl_tr.setTextColor(Color.parseColor("#ffffff"));
            lbl_in.setTextColor(Color.parseColor("#ffffff"));


            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    0);
            lp.setMargins(0, 0, 0, 0);
            list5.setLayoutParams(lp);

            rel_lay1.setEnabled(true);
            rel_lay2.setEnabled(true);
            rel_lay3.setEnabled(true);
            rel_lay4.setEnabled(true);

            sldbtn1.setEnabled(true);
            sldbtn2.setEnabled(true);
            sldbtn3.setEnabled(true);
            sldbtn4.setEnabled(true);

        }
    }

}
