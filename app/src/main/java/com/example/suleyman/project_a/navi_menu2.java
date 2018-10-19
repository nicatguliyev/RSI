package com.example.suleyman.project_a;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suleyman.project_a.Common.DailyList;
import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Common.MothlyList;
import com.example.suleyman.project_a.Common.PlanList;
import com.itextpdf.awt.geom.CubicCurve2D;
import com.itextpdf.awt.geom.Line2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import layout.Plan;

public class navi_menu2 extends AppCompatActivity

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
    String GetSQliteQuery;
    Cursor cursor;
    int langval, themeval;
    ListView list1, list2, list3, list4, list5, list_main;
    private RelativeLayout sldlay1, sldlay2, sldlay3, sldlay4, sldlay5;
    Button sldbtn1, sldbtn2, sldbtn3, sldbtn4, sldbtn5;
    RelativeLayout rel_lay1, rel_lay2, rel_lay3, rel_lay4, rel_lay5;
    TextView txt, txt_tr_date, txt_in_date, txt_im_date, txt_ex_date, txt_total_date, lbl_im, lbl_ex, lbl_tr, lbl_in, lbl_total;
    Button datestart, dateend;
    TextView gunlukTxt, currentYearTxt, prwYearTxt, prwMonthTxt, gunlukTxtV, currentYearTxtV, prwYearTxtV, prwMonthTxtV;
    TextView prwIdxalTxt, prwIdxalTxtV, crrIdxalTxtV, crrIdxalTxt, idxalDiffV, idxalDiff, IdxalDiffPrV,idxalDiffPr;
    TextView prwIxracTxt, prwIxracTxtV, crrIxracTxtV, crrIxracTxt, ixracDiffV, ixracDiff, IxracDiffPrV,ixracDiffPr;
    TextView prwTranzitTxt, prwTranzitTxtV, crrTranzitTxtV, crrTranzitTxt, tranzitDiffV, tranzitDiff, tranzitDiffPrV, tranzitDiffPr;
    TextView prwDaxiliTxt, prwDaxiliTxtV, crrDaxiliTxtV, crrDaxiliTxt, daxiliDiffV, daxiliDiff, daxiliDiffPrV, daxiliDiffPr;
    TextView mainDiff, mainDiffPr, mainDiffV, mainDiffPrV;
    ImageView firstDiffImg, idxalDiffImg, ixracDiffImg, tranzitDiffImg, daxiliDiffImg;
    DatePickerDialog datePickerDialog;
    View view1, view2, view3, view4, view5, view6, view7, view8, view9, view10, view11, view12, view13, view14, view15, view16, view17, view18, view19, view20, view21;
    ProgressBar prgbar1;
    ProgressBar prgbar2, prgbar3, prgbar4, prgbar5;
    int mYear, mYear2;
    int mMonth, mMonth2;
    int mDay, mDay2;
    ArrayList<HashMap<String, String>> idxallist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> ixraclist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> tranzitllist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> daxililist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> umumilist = new ArrayList<HashMap<String, String>>();
    JSONArray Plan = null;
    UserSessionManager sessionManager;
    private static String url = "https://ady.express/Plan.asmx/Daily_Report";
    private static String url2 = "https://ady.express/Plan.asmx/Daily_ReportType";

    int clickedNumber;

    //JSON Node Names
    private static final String TAG_PLANTRACK = "data";


    private static final String T_CURR = "ADY_CURR";
    private static final String T_CURR_X = "ADY_CURR_X";
    private static final String T_PRW_X = "ADY_PRW_X";
    private static final String T_CURR_M_X = "ADY_CURR_M_X";

    int mil_e;
    int may_e;
    int mgun_e;


    String user_ = "";
    String pass_ = "";
    String name_ = "";
    String userId_ = "";

    public navi_menu2() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi_menu);

        sessionManager = new UserSessionManager(getApplicationContext());

        if(sessionManager.checkLogin())
        {
            finish();
        }


        user_ = sessionManager.getUserName();
        pass_ = sessionManager.getPassword();
        name_ = sessionManager.getName();
        userId_ = sessionManager.getUserId();
        final ArrayList<String> menular = new ArrayList<>(sessionManager.getMenus());


       /* user_ = getIntent().getStringExtra("sess_user");
        pass_ = getIntent().getStringExtra("sess_pass");
        name_ = getIntent().getStringExtra("sess_name");
        userId_ = getIntent().getStringExtra("sess_id");
        final ArrayList<String> menular = getIntent().getStringArrayListExtra("menular");*/

        //Toast.makeText(this, userId_, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, menular.toString(), Toast.LENGTH_LONG).show();

        TextView tvw = (TextView) findViewById(R.id.txt_userfname);
        tvw.setText(name_);



        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                    new JSONParse().execute();
            }
        },100);

        new Handler().postDelayed(new Runnable() {

           @Override
          public void run() {

                new JSONParse2().execute();
           }
         },100);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        prgbar1 = (ProgressBar) findViewById(R.id.prgbar1);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        DBCreate();

        firstDiffImg = (ImageView) findViewById(R.id.firstDiff);
        idxalDiffImg = (ImageView) findViewById(R.id.idxalDiffImg);
        ixracDiffImg = (ImageView) findViewById(R.id.ixracDiffImg);
        tranzitDiffImg = (ImageView) findViewById(R.id.tranzitDiffImg);
        daxiliDiffImg = (ImageView) findViewById(R.id.daxiliDiffImg);

        gunlukTxt = (TextView) findViewById(R.id.textView22);
        currentYearTxt = (TextView) findViewById(R.id.lbl_in1);
        prwYearTxt = (TextView) findViewById(R.id.lbl_in2);
        prwMonthTxt = (TextView) findViewById(R.id.lbl_in33);
        gunlukTxtV = (TextView) findViewById(R.id.lbl_in3);
        currentYearTxtV = (TextView) findViewById(R.id.lbl_in8);
        prwYearTxtV = (TextView) findViewById(R.id.lbl_in9);
        prwMonthTxtV = (TextView) findViewById(R.id.lbl_in99);

        prwIdxalTxt = (TextView) findViewById(R.id.prwIdxalTxt);
        prwIdxalTxtV = (TextView) findViewById(R.id.prwIdxalTxtV);
        crrIdxalTxtV = (TextView) findViewById(R.id.crrIdxalTxtV);
        crrIdxalTxt = (TextView) findViewById(R.id.crrIdxalTxt);
        idxalDiffV = (TextView) findViewById(R.id.idxalDiffV);
        idxalDiff = (TextView) findViewById(R.id.idxalDiff);
        IdxalDiffPrV = (TextView) findViewById(R.id.IdxalDiffPrV);
        idxalDiffPr = (TextView) findViewById(R.id.idxalDiffPr);
        prgbar2 = (ProgressBar) findViewById(R.id.prg2);

        prwIxracTxt = (TextView) findViewById(R.id.prwIxracTxt);
        prwIxracTxtV = (TextView) findViewById(R.id.prwIxracTxtV);
        crrIxracTxtV = (TextView) findViewById(R.id.crrIxracTxtV);
        crrIxracTxt = (TextView) findViewById(R.id.crrIxracTxt);
        ixracDiffV = (TextView) findViewById(R.id.ixracDiffV);
        ixracDiff = (TextView) findViewById(R.id.ixracDiff);
        IxracDiffPrV = (TextView) findViewById(R.id.IxracDiffPrV);
        ixracDiffPr = (TextView) findViewById(R.id.ixracDiffPr);
        prgbar3 = (ProgressBar) findViewById(R.id.prg3);

        prwTranzitTxt = (TextView) findViewById(R.id.prwTranzitTxt);
        prwTranzitTxtV = (TextView) findViewById(R.id.prwTranzitTxtV);
        crrTranzitTxtV = (TextView) findViewById(R.id.crrTranzitTxtV);
        crrTranzitTxt  = (TextView) findViewById(R.id.crrTranzitTxt);
        tranzitDiffV = (TextView) findViewById(R.id.tranzitDiffV);
        tranzitDiff  = (TextView) findViewById(R.id.tranzitDiff);
        tranzitDiffPrV = (TextView) findViewById(R.id.tranzitDiffPrV);
        tranzitDiffPr = (TextView) findViewById(R.id.tranzitDiffPr);
        prgbar4 = (ProgressBar) findViewById(R.id.prg4);


        prwDaxiliTxt = (TextView) findViewById(R.id.prwDaxiliTxt);
        prwDaxiliTxtV = (TextView) findViewById(R.id.prwDaxiliTxtV);
        crrDaxiliTxtV = (TextView) findViewById(R.id.crrDaxiliTxtV);
        crrDaxiliTxt  = (TextView) findViewById(R.id.crrDaxiliTxt);
        daxiliDiffV = (TextView) findViewById(R.id.daxiliDiffV);
        daxiliDiff  = (TextView) findViewById(R.id.daxiliDiff);
        daxiliDiffPrV = (TextView) findViewById(R.id.daxiliDiffPrV);
        daxiliDiffPr = (TextView) findViewById(R.id.daxiliDiffPr);
        prgbar5 = (ProgressBar) findViewById(R.id.prg5);

        //mainDiffPrV = (TextView) findViewById(R.id.lbl_in397);
        mainDiffV = (TextView) findViewById(R.id.lbl_in100);
        // mainDiffPr = (TextView) findViewById(R.id.lbl_in223);
        mainDiff = (TextView) findViewById(R.id.lbl_in222);


        rel_lay1 = (RelativeLayout) findViewById(R.id.relativeLayout);
        rel_lay2 = (RelativeLayout) findViewById(R.id.rel_lay2);
        rel_lay3 = (RelativeLayout) findViewById(R.id.rel_lay3);
        rel_lay4 = (RelativeLayout) findViewById(R.id.rel_lay4);
       // rel_lay5 = (RelativeLayout) findViewById(R.id.rel_lay5);
        sldlay1 = (RelativeLayout) findViewById(R.id.slideIdxal);
        sldlay2 = (RelativeLayout) findViewById(R.id.slideIxrac);
        sldlay3 = (RelativeLayout) findViewById(R.id.slideTranzit);
        sldlay4 = (RelativeLayout) findViewById(R.id.slideDaxili);

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

        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        view4 = findViewById(R.id.view4);
        view5 = findViewById(R.id.view5);

        view6 = findViewById(R.id.view6);
        view7 = findViewById(R.id.view7);
        view8 = findViewById(R.id.view8);
        view9 = findViewById(R.id.view9);
        view10 = findViewById(R.id.view10);

        view11 = findViewById(R.id.view11);
        view12 = findViewById(R.id.view12);
        view13 = findViewById(R.id.view13);
        view14 = findViewById(R.id.view14);
        view15 = findViewById(R.id.view15);

        view16 = findViewById(R.id.view16);
        view17 = findViewById(R.id.view17);
        view18 = findViewById(R.id.view18);
        view19 = findViewById(R.id.view19);

        view20 = findViewById(R.id.view20);
        view21 = findViewById(R.id.view21);

        txt_tr_date = (TextView) findViewById(R.id.txt_tr_date);
        txt_in_date = (TextView) findViewById(R.id.txt_in_date);
        txt_im_date = (TextView) findViewById(R.id.txt_im_date);
        txt_ex_date = (TextView) findViewById(R.id.txt_ex_date);
        txt_total_date = (TextView) findViewById(R.id.txt_total_date);


        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        final int mil = calendar.get(java.util.Calendar.YEAR);
        final int may = calendar.get(java.util.Calendar.MONTH);
        final int mgun = calendar.get(java.util.Calendar.DATE);

        final java.text.DecimalFormat formatter;
        formatter = new java.text.DecimalFormat("00");

        mil_e = calendar.get(java.util.Calendar.YEAR);
        may_e = calendar.get(java.util.Calendar.MONTH);
        mgun_e = calendar.get(java.util.Calendar.DATE);


        rel_lay1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (sldlay1.getVisibility() == View.GONE) {
                    clickedNumber++;
                    sldbtn1.setBackgroundResource(R.drawable.slide_btn);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (clickedNumber == 1) {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay1.setVisibility(View.VISIBLE);
                                sldlay1.startAnimation(bottomUp);

                                if (sldlay2.getVisibility() == View.VISIBLE) {
                                    sldlay2.setVisibility(View.GONE);
                                    sldlay2.clearAnimation();
                                    sldbtn2.setBackgroundResource(R.drawable.right_arrow2);
                                }
                                if (sldlay3.getVisibility() == View.VISIBLE) {
                                    sldlay3.setVisibility(View.GONE);
                                    sldlay3.clearAnimation();
                                    sldbtn3.setBackgroundResource(R.drawable.right_arrow2);
                                }
                                if (sldlay4.getVisibility() == View.VISIBLE) {
                                    sldlay4.setVisibility(View.GONE);
                                    sldlay4.clearAnimation();
                                    sldbtn4.setBackgroundResource(R.drawable.right_arrow2);
                                }
                            } else {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay1.setVisibility(View.VISIBLE);
                                sldlay1.startAnimation(bottomUp);
                            }

                            clickedNumber = 0;
                        }
                    }, 500);
                } else {
                    sldlay1.setVisibility(View.GONE);
                    sldlay1.clearAnimation();
                    sldbtn1.setBackgroundResource(R.drawable.right_arrow2);
                }

            }
        });

        sldbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (sldlay1.getVisibility() == View.GONE) {
                    clickedNumber++;
                    sldbtn1.setBackgroundResource(R.drawable.slide_btn);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (clickedNumber == 1) {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay1.setVisibility(View.VISIBLE);
                                sldlay1.startAnimation(bottomUp);

                                if (sldlay2.getVisibility() == View.VISIBLE) {
                                    sldlay2.setVisibility(View.GONE);
                                    sldlay2.clearAnimation();
                                    sldbtn2.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay3.getVisibility() == View.VISIBLE) {
                                    sldlay3.setVisibility(View.GONE);
                                    sldlay3.clearAnimation();
                                    sldbtn3.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay4.getVisibility() == View.VISIBLE) {
                                    sldlay4.setVisibility(View.GONE);
                                    sldlay4.clearAnimation();
                                    sldbtn4.setBackgroundResource(R.drawable.right_arrow2);

                                }
                            } else {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay1.setVisibility(View.VISIBLE);
                                sldlay1.startAnimation(bottomUp);
                            }

                            clickedNumber = 0;
                        }
                    }, 500);
                } else {
                    sldlay1.setVisibility(View.GONE);
                    sldlay1.clearAnimation();
                    sldbtn1.setBackgroundResource(R.drawable.right_arrow2);
                }
            }
        });
        rel_lay2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (sldlay2.getVisibility() == View.GONE) {
                    clickedNumber++;
                    sldbtn2.setBackgroundResource(R.drawable.slide_btn);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (clickedNumber == 1) {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay2.setVisibility(View.VISIBLE);
                                sldlay2.startAnimation(bottomUp);

                                if (sldlay1.getVisibility() == View.VISIBLE) {
                                    sldlay1.setVisibility(View.GONE);
                                    sldlay1.clearAnimation();
                                    sldbtn1.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay3.getVisibility() == View.VISIBLE) {
                                    sldlay3.setVisibility(View.GONE);
                                    sldlay3.clearAnimation();
                                    sldbtn3.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay4.getVisibility() == View.VISIBLE) {
                                    sldlay4.setVisibility(View.GONE);
                                    sldlay4.clearAnimation();
                                    sldbtn4.setBackgroundResource(R.drawable.right_arrow2);

                                }
                               /* if (sldlay5.getVisibility() == View.VISIBLE) {
                                    sldlay5.setVisibility(View.GONE);
                                    sldlay5.clearAnimation();
                                }*/
                            } else {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay2.setVisibility(View.VISIBLE);
                                sldlay2.startAnimation(bottomUp);
                            }

                            clickedNumber = 0;
                        }
                    }, 300);
                } else {
                    sldlay2.setVisibility(View.GONE);
                    sldlay2.clearAnimation();
                    sldbtn2.setBackgroundResource(R.drawable.right_arrow2);
                }
            }
        });

        sldbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sldlay2.getVisibility() == View.GONE) {
                    clickedNumber++;
                    sldbtn2.setBackgroundResource(R.drawable.slide_btn);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (clickedNumber == 1) {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay2.setVisibility(View.VISIBLE);
                                sldlay2.startAnimation(bottomUp);

                                if (sldlay1.getVisibility() == View.VISIBLE) {
                                    sldlay1.setVisibility(View.GONE);
                                    sldlay1.clearAnimation();
                                    sldbtn1.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay3.getVisibility() == View.VISIBLE) {
                                    sldlay3.setVisibility(View.GONE);
                                    sldlay3.clearAnimation();
                                    sldbtn3.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay4.getVisibility() == View.VISIBLE) {
                                    sldlay4.setVisibility(View.GONE);
                                    sldlay4.clearAnimation();
                                    sldbtn4.setBackgroundResource(R.drawable.right_arrow2);

                                }
                               /* if (sldlay5.getVisibility() == View.VISIBLE) {
                                    sldlay5.setVisibility(View.GONE);
                                    sldlay5.clearAnimation();
                                }*/
                            } else {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay2.setVisibility(View.VISIBLE);
                                sldlay2.startAnimation(bottomUp);
                            }

                            clickedNumber = 0;
                        }
                    }, 300);
                } else {
                    sldlay2.setVisibility(View.GONE);
                    sldlay2.clearAnimation();
                    sldbtn2.setBackgroundResource(R.drawable.right_arrow2);
                }
            }
        });

        rel_lay3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (sldlay3.getVisibility() == View.GONE) {
                    clickedNumber++;
                    sldbtn3.setBackgroundResource(R.drawable.slide_btn);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (clickedNumber == 1) {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay3.setVisibility(View.VISIBLE);
                                sldlay3.startAnimation(bottomUp);

                                if (sldlay1.getVisibility() == View.VISIBLE) {
                                    sldlay1.setVisibility(View.GONE);
                                    sldlay1.clearAnimation();
                                    sldbtn1.setBackgroundResource(R.drawable.right_arrow2);


                                }
                                if (sldlay2.getVisibility() == View.VISIBLE) {
                                    sldlay2.setVisibility(View.GONE);
                                    sldlay2.clearAnimation();
                                    sldbtn2.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay4.getVisibility() == View.VISIBLE) {
                                    sldlay4.setVisibility(View.GONE);
                                    sldlay4.clearAnimation();
                                    sldbtn4.setBackgroundResource(R.drawable.right_arrow2);

                                }
                            } else {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay3.setVisibility(View.VISIBLE);
                                sldlay3.startAnimation(bottomUp);
                            }

                            clickedNumber = 0;
                        }
                    }, 300);
                } else {
                    sldlay3.setVisibility(View.GONE);
                    sldlay3.clearAnimation();
                    sldbtn3.setBackgroundResource(R.drawable.right_arrow2);
                }
            }
        });

        sldbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sldlay3.getVisibility() == View.GONE) {
                    clickedNumber++;
                    sldbtn3.setBackgroundResource(R.drawable.slide_btn);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (clickedNumber == 1) {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay3.setVisibility(View.VISIBLE);
                                sldlay3.startAnimation(bottomUp);

                                if (sldlay1.getVisibility() == View.VISIBLE) {
                                    sldlay1.setVisibility(View.GONE);
                                    sldlay1.clearAnimation();
                                    sldbtn1.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay2.getVisibility() == View.VISIBLE) {
                                    sldlay2.setVisibility(View.GONE);
                                    sldlay2.clearAnimation();
                                    sldbtn2.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay4.getVisibility() == View.VISIBLE) {
                                    sldlay4.setVisibility(View.GONE);
                                    sldlay4.clearAnimation();
                                    sldbtn4.setBackgroundResource(R.drawable.right_arrow2);

                                }
                               /* if (sldlay5.getVisibility() == View.VISIBLE) {
                                    sldlay5.setVisibility(View.GONE);
                                    sldlay5.clearAnimation();
                                }*/
                            } else {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay3.setVisibility(View.VISIBLE);
                                sldlay3.startAnimation(bottomUp);
                            }

                            clickedNumber = 0;
                        }
                    }, 300);
                } else {
                    sldlay3.setVisibility(View.GONE);
                    sldlay3.clearAnimation();
                    sldbtn3.setBackgroundResource(R.drawable.right_arrow2);
                }
            }
        });
        rel_lay4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (sldlay4.getVisibility() == View.GONE) {
                    clickedNumber++;
                    sldbtn4.setBackgroundResource(R.drawable.slide_btn);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (clickedNumber == 1) {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay4.setVisibility(View.VISIBLE);
                                sldlay4.startAnimation(bottomUp);

                                if (sldlay1.getVisibility() == View.VISIBLE) {
                                    sldlay1.setVisibility(View.GONE);
                                    sldlay1.clearAnimation();
                                    sldbtn1.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay3.getVisibility() == View.VISIBLE) {
                                    sldlay3.setVisibility(View.GONE);
                                    sldlay3.clearAnimation();
                                    sldbtn3.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay2.getVisibility() == View.VISIBLE) {
                                    sldlay2.setVisibility(View.GONE);
                                    sldlay2.clearAnimation();
                                    sldbtn2.setBackgroundResource(R.drawable.right_arrow2);

                                }
                            } else {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay4.setVisibility(View.VISIBLE);
                                sldlay4.startAnimation(bottomUp);
                            }

                            clickedNumber = 0;
                        }
                    }, 300);
                } else {
                    sldlay4.setVisibility(View.GONE);
                    sldlay4.clearAnimation();
                    sldbtn4.setBackgroundResource(R.drawable.right_arrow2);
                }
            }
        });

        sldbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sldlay4.getVisibility() == View.GONE) {
                    clickedNumber++;
                    sldbtn4.setBackgroundResource(R.drawable.slide_btn);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (clickedNumber == 1) {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay4.setVisibility(View.VISIBLE);
                                sldlay4.startAnimation(bottomUp);

                                if (sldlay1.getVisibility() == View.VISIBLE) {
                                    sldlay1.setVisibility(View.GONE);
                                    sldlay1.clearAnimation();
                                    sldbtn1.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay3.getVisibility() == View.VISIBLE) {
                                    sldlay3.setVisibility(View.GONE);
                                    sldlay3.clearAnimation();
                                    sldbtn3.setBackgroundResource(R.drawable.right_arrow2);

                                }
                                if (sldlay2.getVisibility() == View.VISIBLE) {
                                    sldlay2.setVisibility(View.GONE);
                                    sldlay2.clearAnimation();
                                    sldbtn2.setBackgroundResource(R.drawable.right_arrow2);

                                }
                            } else {
                                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu2.this, R.anim.bottom_up);
                                sldlay4.setVisibility(View.VISIBLE);
                                sldlay4.startAnimation(bottomUp);
                            }

                            clickedNumber = 0;
                        }
                    }, 300);
                } else {
                    sldlay4.setVisibility(View.GONE);
                    sldlay4.clearAnimation();
                    sldbtn4.setBackgroundResource(R.drawable.right_arrow2);
                }
            }
        });


      /*  GetSQliteQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id=1";
        cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);
        if (cursor.moveToFirst()) {

            do {
                langval = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Langitem));
                themeval = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Themeitem));

            } while (cursor.moveToNext());

        }
        cursor.close();*/


        Menu menu = navigationView.getMenu();
        //nav_prognose
       /* menu.findItem(R.id.nav_plan).setTitle(LLanguage._nav_plan[langval]);
        menu.findItem(R.id.nav_in_search).setTitle(LLanguage._nav_insearch[langval]);
        menu.findItem(R.id.nav_daily).setTitle(LLanguage._nav_daily[langval]);
        menu.findItem(R.id.nav_prognose).setTitle(LLanguage._nav_prognose[langval]);
        menu.findItem(R.id.nav_graf).setTitle(LLanguage._nav_gra[langval]);
        menu.findItem(R.id.nav_share).setTitle(LLanguage._nav_share[langval]);
        menu.findItem(R.id.nav_Setting).setTitle(LLanguage._nav_settings[langval]);*/
        final MenuItem calculator = menu.findItem(R.id.calculator);
        final MenuItem proqnoz = menu.findItem(R.id.nav_prognose);
        final MenuItem planMenu = menu.findItem(R.id.nav_plan);
        final MenuItem gunlukMenu = menu.findItem(R.id.nav_daily);
        final MenuItem searchMenu = menu.findItem(R.id.nav_in_search);
        final MenuItem graphMneu = menu.findItem(R.id.nav_graf);
        final MenuItem monthlyMenu = menu.findItem(R.id.nav_monthly);

        if (!menular.contains("Günlük Hesabat")) {
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
        }
        /*if(!menular.contains("Ayliq Hesabat"))
        {
            monthlyMenu.setVisible(false);
        }*/

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.home) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Intent intent = new Intent(navi_menu2.this, navi_menu2.class);
                    intent.putExtra("sess_user", user_);
                    intent.putExtra("sess_pass", pass_);
                    intent.putExtra("sess_name", name_);
                    intent.putExtra("sess_id", userId_);
                    intent.putStringArrayListExtra("menular", menular);
                    startActivity(intent);
                    finish();
                } else if (menuItem.getItemId() == R.id.nav_plan) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Plan pl = new Plan();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);
                    pl.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, pl, "plan");
                    fragmenttarnsc.commit();
                    // Handle the camera action
                } else if (menuItem.getItemId() == R.id.calculator) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    CalculateFragment calculateFragment = new CalculateFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);

                    calculateFragment.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.add(R.id.content_navi_menu2, calculateFragment, "calculate");
                    fragmenttarnsc.commit();

                } else if (menuItem.getItemId() == R.id.nav_in_search) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    InSearch insrch = new InSearch();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);

                    insrch.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, insrch, "search");
                    fragmenttarnsc.commit();
                    // Handle the camera action
                } else if (menuItem.getItemId() == R.id.nav_prognose) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Prognose prg = new Prognose();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);


                    prg.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, prg, "prognose");
                    fragmenttarnsc.commit();
                    // Handle the camera action
                } else if (menuItem.getItemId() == R.id.nav_daily) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Daily dly = new Daily();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);


                    dly.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, dly, "daily");
                    fragmenttarnsc.commit();
                    // Handle the camera action
                } else if (menuItem.getItemId() == R.id.nav_graf) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    GraphFragment tbfr = new GraphFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);

                    tbfr.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, tbfr, "graph");
                    fragmenttarnsc.commit();

                }

                else if(menuItem.getItemId() == R.id.nav_yuk_info)

                {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    YukInfoFragment yukInfo = new YukInfoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);

                    yukInfo.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, yukInfo, "yukinfo");
                    fragmenttarnsc.commit();
                }

                else if (menuItem.getItemId() == R.id.nav_monthly) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Monthly monthly = new Monthly();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);
                    monthly.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, monthly, "monthly");
                    fragmenttarnsc.commit();
                } else if (menuItem.getItemId() == R.id.nav_share) {

                } else if (menuItem.getItemId() == R.id.nav_feedback) {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"rsiadyexpress@gmail.com"});

                    startActivity(Intent.createChooser(intent, "Select Action"));

                } else if (menuItem.getItemId() == R.id.nav_Setting) {
                    Intent intent = new Intent(navi_menu2.this, Settings.class);
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
            finish();
           /* Intent intent = new Intent(navi_menu2.this, Login.class);
            intent.putExtra("sess_user", "");
            intent.putExtra("sess_pass", "");
            intent.putExtra("sess_name", "");
            startActivity(intent);*/
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navi_menu, menu);
        menu.findItem(R.id.action_send).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment dailyFragment = getSupportFragmentManager().findFragmentByTag("daily");
        Fragment monthlyFragment = getSupportFragmentManager().findFragmentByTag("monthly");
        Fragment planFragment = getSupportFragmentManager().findFragmentByTag("plan");

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            sessionManager.logoutUser();
           return true;
        }

        if(id == R.id.action_send) {
            if (dailyFragment != null && dailyFragment.isVisible()) {

                final Dialog dialog = new Dialog(this);
                View child = this.getLayoutInflater().inflate(R.layout.custom_email_dialog, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(child);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                dialog.show();

                final EditText emailEdt = (EditText) dialog.findViewById(R.id.emailEdt);
                RelativeLayout gonderLyt = (RelativeLayout) dialog.findViewById(R.id.gonderBtn);
                ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
                final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
                final ImageView doneImd = (ImageView) dialog.findViewById(R.id.doneImg);
                final TextView sendingInformationTxt = (TextView) dialog.findViewById(R.id.sendingInformationTxt);
                final RelativeLayout progressLyt = (RelativeLayout) dialog.findViewById(R.id.progressLyt);

                final File pdfFolder = new File(Environment.getExternalStorageDirectory(), "ResultsPdf");

                closeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                gonderLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!pdfFolder.exists()) {
                            pdfFolder.mkdir();
                        }

                        final File myFile;
                        Date date = new Date();
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

                        myFile = new File("/" + pdfFolder + "/" + timeStamp + ".pdf");
                        //String image1 = "up_arrow.png";
                        Drawable drawable = getResources().getDrawable(R.drawable.up_arrow);
                        Drawable drawable2 = getResources().getDrawable(R.drawable.down_arrow);


                        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] bitmapData = stream.toByteArray();

                        Bitmap bitmap2 = ((BitmapDrawable)drawable2).getBitmap();
                        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                        byte[] bitmapData2 = stream2.toByteArray();

                        OutputStream outputStream = null;

                        try {

                            outputStream = new FileOutputStream(myFile);
                            Document document = new Document();
                            PdfWriter.getInstance(document, outputStream);

                            BaseFont bf = BaseFont.createFont("assets/fonts/AZER_TM.ttf", "Cp1254", BaseFont.EMBEDDED);
                            Font font1 = new Font(bf, 22);
                            Font font2 = new Font(bf, 16);

                            PdfPTable table = new PdfPTable(4);
                            float[] columnWidths = new float[]{10f, 30f, 20f, 2f};
                            table.setWidths(columnWidths);
                            table.setSpacingBefore(20f);

                            PdfPCell cell;
                            PdfPTable inner;


                            cell = new PdfPCell(new Phrase(changeString("Gün"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            inner = new PdfPTable(2);
                            PdfPCell cellDasima = new PdfPCell(new Phrase(("Daşıma"), font2));
                            cellDasima.setColspan(2);
                            cellDasima.setBackgroundColor(BaseColor.GRAY);
                            cellDasima.setHorizontalAlignment(Element.ALIGN_CENTER);
                            inner.addCell(cellDasima);
                            PdfPCell cell2016 = new PdfPCell(new Phrase(String.valueOf(Integer.parseInt(DailyList.personList.get(1).getSecondYear())-1), font2));
                            PdfPCell cell2017 = new PdfPCell(new Phrase(String.valueOf(Integer.parseInt(DailyList.personList.get(1).getSecondYear())), font2));
                            cell2016.setBackgroundColor(BaseColor.GRAY);
                            cell2016.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell2017.setBackgroundColor(BaseColor.GRAY);
                            cell2017.setHorizontalAlignment(Element.ALIGN_CENTER);
                            inner.addCell(cell2016);
                            inner.addCell(cell2017);
                            cell = new PdfPCell(inner);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(changeString("Müqayisə"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setColspan(2);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                           /* cell = new PdfPCell(new Phrase(changeString(""), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);*/

                            cell = new PdfPCell(new Phrase(changeString("Sutkaliq"), font2));
                            if(DailyList.diffSutkaliq.contains("-")) {
                                cell.setBackgroundColor(BaseColor.RED);
                            }
                            else
                            {
                                cell.setBackgroundColor(BaseColor.GREEN);
                            }
                            table.addCell(cell);

                            inner = new PdfPTable(2);
                            inner.addCell(new Phrase(DailyList.firstSutkaliq, font2));
                            inner.addCell(new Phrase(DailyList.secondSutkaliq, font2));
                            cell = new PdfPCell(inner);
                            if(DailyList.diffSutkaliq.contains("-")) {
                                cell.setBackgroundColor(BaseColor.RED);
                            }
                            else
                            {
                                cell.setBackgroundColor(BaseColor.GREEN);
                            }
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(changeString(DailyList.diffSutkaliq), font2));
                            if(DailyList.diffSutkaliq.contains("-")) {
                                cell.setBackgroundColor(BaseColor.RED);
                            }
                            else
                            {
                                cell.setBackgroundColor(BaseColor.GREEN);
                            }
                            table.addCell(cell);

                            if(DailyList.diffSutkaliq.contains("-")) {
                                cell = new PdfPCell(Image.getInstance(bitmapData2), true);
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            }
                            else
                            {
                                cell = new PdfPCell(Image.getInstance(bitmapData), true);
                            }
                            table.addCell(cell);

                            for (int i = 0; i < DailyList.personList.size(); i++) {

                                cell = new PdfPCell(new Phrase(changeString(DailyList.personList.get(i).getDay_().toString()), font2));
                                table.addCell(cell);

                                inner = new PdfPTable(2);
                                inner.addCell(new Phrase(DailyList.personList.get(i).getT2016_().toString(), font2));
                                inner.addCell(new Phrase(DailyList.personList.get(i).getT2017_().toString(), font2));
                                cell = new PdfPCell(inner);
                                table.addCell(cell);

                                cell = new PdfPCell(new Phrase(changeString(DailyList.personList.get(i).getDiff_().toString()), font2));
                                table.addCell(cell);

                                if(DailyList.personList.get(i).getDiff_().toString().contains("-")) {
                                    cell = new PdfPCell(Image.getInstance(bitmapData2), true);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                }
                                else
                                {
                                    cell = new PdfPCell(Image.getInstance(bitmapData), true);
                                }
                                table.addCell(cell);
                            }

                            PdfWriter.getInstance(document, new FileOutputStream(myFile));



                            document.open();
                            Paragraph tranportParagraph =new Paragraph(String.valueOf(Integer.parseInt(DailyList.personList.get(1).getSecondYear())-1) +
                                    " və " + String.valueOf(Integer.parseInt(DailyList.personList.get(1).getSecondYear()))  + changeString("-ci illərdə ") + Daily.spinner.getSelectedItem().toString()+
                             changeString(" ayında olan daşınmaların müqayisəli təhlili"), font1);
                            tranportParagraph.setAlignment(Element.ALIGN_CENTER);
                            document.add(tranportParagraph);
                            document.add(table);
                            document.close();


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }

                        progressLyt.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        sendingInformationTxt.setVisibility(View.VISIBLE);
                        sendingInformationTxt.setText("Göndərilir...");
                        doneImd.setVisibility(View.INVISIBLE);

                        //SendMail sm = new SendMail(navi_menu2.this, emailEdt.getText().toString(), myFile, progressBar, sendingInformationTxt, doneImd, "2");
                        //sm.execute();
                    }
                });

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        try {
                            FileUtils.deleteDirectory(pdfFolder);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                return true;
            }

            else if(monthlyFragment!=null && monthlyFragment.isVisible())
            {
                final Dialog dialog = new Dialog(this);
                View child = this.getLayoutInflater().inflate(R.layout.custom_email_dialog, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(child);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                dialog.show();

                final EditText emailEdt = (EditText) dialog.findViewById(R.id.emailEdt);
                RelativeLayout gonderLyt = (RelativeLayout) dialog.findViewById(R.id.gonderBtn);
                ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
                final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
                final ImageView doneImd = (ImageView) dialog.findViewById(R.id.doneImg);
                final TextView sendingInformationTxt = (TextView) dialog.findViewById(R.id.sendingInformationTxt);
                final RelativeLayout progressLyt = (RelativeLayout) dialog.findViewById(R.id.progressLyt);

                final File pdfFolder = new File(Environment.getExternalStorageDirectory(), "ResultsPdf");

                closeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                gonderLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (!pdfFolder.exists()) {
                            pdfFolder.mkdir();
                        }

                        final File myFile;
                        Date date = new Date();
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

                        myFile = new File("/" + pdfFolder + "/" + timeStamp + ".pdf");

                        Drawable drawable = getResources().getDrawable(R.drawable.up_arrow);
                        Drawable drawable2 = getResources().getDrawable(R.drawable.down_arrow);


                        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] bitmapData = stream.toByteArray();

                        Bitmap bitmap2 = ((BitmapDrawable)drawable2).getBitmap();
                        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                        bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
                        byte[] bitmapData2 = stream2.toByteArray();

                        OutputStream outputStream = null;

                        try {

                            outputStream = new FileOutputStream(myFile);
                            Document document = new Document();
                            PdfWriter.getInstance(document, outputStream);

                            BaseFont bf = BaseFont.createFont("assets/fonts/AZER_TM.ttf", "Cp1254", BaseFont.EMBEDDED);
                            Font font1 = new Font(bf, 22);
                            Font font2 = new Font(bf, 16);

                            PdfPTable table = new PdfPTable(4);
                            float[] columnWidths = new float[]{10f, 30f, 20f, 2f};
                            table.setWidths(columnWidths);
                            table.setSpacingBefore(20f);
                            table.setSpacingAfter(20f);

                            PdfPCell cell;
                            PdfPTable inner;


                            cell = new PdfPCell(new Phrase(changeString("Ay"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            inner = new PdfPTable(2);
                            PdfPCell cellDasima = new PdfPCell(new Phrase(("Daşıma"), font2));
                            cellDasima.setColspan(2);
                            cellDasima.setBackgroundColor(BaseColor.GRAY);
                            cellDasima.setHorizontalAlignment(Element.ALIGN_CENTER);
                            inner.addCell(cellDasima);
                            PdfPCell cell2016 = new PdfPCell(new Phrase(changeString(DailyList.personList.get(0).getFirstYear()), font2));
                            PdfPCell cell2017 = new PdfPCell(new Phrase(changeString(DailyList.personList.get(0).getSecondYear()), font2));

                            cell2016.setBackgroundColor(BaseColor.GRAY);
                            cell2016.setHorizontalAlignment(Element.ALIGN_CENTER);
                            cell2017.setBackgroundColor(BaseColor.GRAY);
                            cell2017.setHorizontalAlignment(Element.ALIGN_CENTER);
                            inner.addCell(cell2016);
                            inner.addCell(cell2017);
                            cell = new PdfPCell(inner);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(changeString("Müqayisə"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setColspan(2);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            String[] aylar = {"Yanvar", "Fevral", "Mart", "Aprel", "May", "Iyun", "Iyul", "Avqust", "Sentyabr",
                            "Oktyabr", "Noyabr", "Dekabr"};

                           // for (int i = 0; i < MothlyList.monthlyList.size(); i++)
                            for (int i = 0; i<DailyList.personList.size(); i++)
                            {

                                cell = new PdfPCell(new Phrase(changeString(aylar[i].toString()), font2));
                                table.addCell(cell);

                                inner = new PdfPTable(2);
                                PdfPCell pdfPCell1 = new PdfPCell(new Phrase(String.valueOf(DailyList.personList.get(i).getT2016_()), font2));
                                PdfPCell pdfPCell2 = new PdfPCell(new Phrase(String.valueOf(DailyList.personList.get(i).getT2017_()), font2));
                                PdfPCell pdfPCell3 = new PdfPCell(new Phrase(String.valueOf(DailyList.personList.get(i).getDiff_()), font2));

                                if(DailyList.personList.get(i).getMakeGreen().equals("ok1"))
                                {
                                    pdfPCell1.setBackgroundColor(BaseColor.GREEN);
                                    pdfPCell3.setBackgroundColor(BaseColor.GREEN);
                                }
                                if(DailyList.personList.get(i).getMakeGreen().equals("ok2"))
                                {
                                    pdfPCell2.setBackgroundColor(BaseColor.GREEN);
                                    pdfPCell3.setBackgroundColor(BaseColor.GREEN);
                                }
                                inner.addCell(pdfPCell1);
                                inner.addCell(pdfPCell2);
                                cell = new PdfPCell(inner);
                                table.addCell(cell);
                                table.addCell(pdfPCell3);

                                if(DailyList.personList.get(i).getDiff_().toString().contains("-")) {
                                    cell = new PdfPCell(Image.getInstance(bitmapData2), true);
                                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                }
                                else
                                {
                                    cell = new PdfPCell(Image.getInstance(bitmapData), true);
                                }
                                table.addCell(cell);

                            }

                            cell = new PdfPCell(new Phrase(changeString("Cem"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            table.addCell(cell);

                            inner = new PdfPTable(2);
                            PdfPCell pdfPCell1 = new PdfPCell(new Phrase(String.valueOf(DailyList.sumFirstYear), font2));
                            PdfPCell pdfPCell2 = new PdfPCell(new Phrase(String.valueOf(DailyList.sumSecondYear), font2));
                            PdfPCell pdfPCell3 = new PdfPCell(new Phrase(String.valueOf(DailyList.sumDiff), font2));

                            pdfPCell1.setBackgroundColor(BaseColor.GRAY);
                            if(DailyList.personList.get(0).getSecondYear().equals(String.valueOf(mil_e)))
                            {
                                pdfPCell2.setBackgroundColor(BaseColor.GREEN);
                                pdfPCell3.setBackgroundColor(BaseColor.GREEN);
                            }
                            else
                            {
                                pdfPCell2.setBackgroundColor(BaseColor.GRAY);
                                pdfPCell3.setBackgroundColor(BaseColor.GRAY);
                            }

                            inner.addCell(pdfPCell1);
                            inner.addCell(pdfPCell2);
                            cell = new PdfPCell(inner);
                            table.addCell(cell);
                            table.addCell(pdfPCell3);

                            if(DailyList.sumDiff.contains("-")) {
                                cell = new PdfPCell(Image.getInstance(bitmapData2), true);
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            }
                            else
                            {
                                cell = new PdfPCell(Image.getInstance(bitmapData), true);
                            }
                            cell.setVerticalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(changeString("Illik"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            table.addCell(cell);

                            inner = new PdfPTable(2);
                            PdfPCell pdfPCell4 = new PdfPCell(new Phrase(String.valueOf(DailyList.illikFirstYear), font2));
                            PdfPCell pdfPCell5 = new PdfPCell(new Phrase(String.valueOf(DailyList.illikSecondYear), font2));
                            PdfPCell pdfPCell6 = new PdfPCell(new Phrase(String.valueOf(DailyList.illikDiff), font2));

                            pdfPCell4.setBackgroundColor(BaseColor.GRAY);
                            if(DailyList.personList.get(0).getSecondYear().equals(String.valueOf(mil_e)))
                            {
                                pdfPCell5.setBackgroundColor(BaseColor.GREEN);
                                pdfPCell6.setBackgroundColor(BaseColor.GREEN);
                            }
                            else {
                                pdfPCell5.setBackgroundColor(BaseColor.GRAY);
                                pdfPCell6.setBackgroundColor(BaseColor.GRAY);
                            }
                            inner.addCell(pdfPCell4);
                            inner.addCell(pdfPCell5);
                            cell = new PdfPCell(inner);
                            table.addCell(cell);
                            table.addCell(pdfPCell6);

                            if(DailyList.illikDiff.contains("-")) {
                                cell = new PdfPCell(Image.getInstance(bitmapData2), true);
                                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            }
                            else
                            {
                                cell = new PdfPCell(Image.getInstance(bitmapData), true);
                            }
                            table.addCell(cell);

                            PdfWriter.getInstance(document, new FileOutputStream(myFile));

                            document.open();
                            Paragraph tranportParagraph = new Paragraph(changeString(DailyList.personList.get(0).getFirstYear()+  " və " + DailyList.personList.get(0).getSecondYear() + "-ci illərdə aylar  üzrə olan daşınmaların müqayisəsi"), font1);
                            tranportParagraph.setAlignment(Element.ALIGN_CENTER);
                            Paragraph noteParagraph = new Paragraph(changeString("Qeyd: Yaşıl fonda yazılan ədədlər proqnoz olaraq verilir."),font1);
                            document.add(tranportParagraph);
                            document.add(table);
                            document.add(noteParagraph);
                            document.close();


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }

                        progressLyt.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        sendingInformationTxt.setVisibility(View.VISIBLE);
                        sendingInformationTxt.setText("Göndərilir...");
                        doneImd.setVisibility(View.INVISIBLE);

                       // SendMail sm = new SendMail(navi_menu2.this, emailEdt.getText().toString(), myFile, progressBar, sendingInformationTxt, doneImd, "2");
                       // sm.execute();
                    }
                });

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        try {
                            FileUtils.deleteDirectory(pdfFolder);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                return true;

            }

            else if(planFragment!=null && planFragment.isVisible())
            {

                final Dialog dialog = new Dialog(this);
                View child = this.getLayoutInflater().inflate(R.layout.custom_email_dialog, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(child);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                dialog.show();

                final EditText emailEdt = (EditText) dialog.findViewById(R.id.emailEdt);
                RelativeLayout gonderLyt = (RelativeLayout) dialog.findViewById(R.id.gonderBtn);
                ImageView closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
                final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
                final ImageView doneImd = (ImageView) dialog.findViewById(R.id.doneImg);
                final TextView sendingInformationTxt = (TextView) dialog.findViewById(R.id.sendingInformationTxt);
                final RelativeLayout progressLyt = (RelativeLayout) dialog.findViewById(R.id.progressLyt);

                final File pdfFolder = new File(Environment.getExternalStorageDirectory(), "ResultsPdf");

                closeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                gonderLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!pdfFolder.exists()) {
                            pdfFolder.mkdir();
                        }

                        final File myFile;
                        Date date = new Date();
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

                        myFile = new File("/" + pdfFolder + "/" + timeStamp + ".pdf");

                        OutputStream outputStream = null;

                        try {

                            outputStream = new FileOutputStream(myFile);
                            Document document = new Document();
                            PdfWriter.getInstance(document, outputStream);

                            BaseFont bf = BaseFont.createFont("assets/fonts/AZER_TM.ttf", "Cp1254", BaseFont.EMBEDDED);
                            Font font1 = new Font(bf, 22);
                            Font font2 = new Font(bf, 14);

                            PdfPTable table = new PdfPTable(3);
                            float[] columnWidths = new float[]{50f, 21f, 21f};
                            table.setWidths(columnWidths);
                            table.setSpacingBefore(20f);

                            PdfPCell cell;
                            //PdfPTable inner;


                            cell = new PdfPCell(new Phrase(changeString("MƏHSUL"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(changeString("2016"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(changeString("2017"), font2));
                            cell.setBackgroundColor(BaseColor.GRAY);
                            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(cell);

                            for(int i = 0; i< PlanList.idxallist.size(); i++)
                            {
                                cell = new PdfPCell(new Phrase(changeString(PlanList.idxallist.get(i).get("GR").toString()), font2));
                                //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                                if(i == PlanList.idxallist.size()-1)
                                {
                                    cell.setBackgroundColor(BaseColor.GRAY);
                                }
                                table.addCell(cell);

                                cell = new PdfPCell(new Phrase(changeString(PlanList.idxallist.get(i).get("TOTAL_F_16").toString()), font2));
                                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                if(i == PlanList.idxallist.size()-1)
                                {
                                    cell.setBackgroundColor(BaseColor.GRAY);
                                }
                                table.addCell(cell);

                                cell = new PdfPCell(new Phrase(changeString(PlanList.idxallist.get(i).get("TOTAL_F").toString()), font2));
                                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                if(i == PlanList.idxallist.size()-1)
                                {
                                    cell.setBackgroundColor(BaseColor.GRAY);
                                }
                                table.addCell(cell);
                            }

                            PdfWriter.getInstance(document, new FileOutputStream(myFile));

                            document.open();
                            Paragraph tranportParagraph = new Paragraph(changeString("Yük daşıma üzrə Plan və faktın ") +
                                    changeString("müqayisəli təhlili"), font1);
                            tranportParagraph.setAlignment(Element.ALIGN_CENTER);
                            document.add(tranportParagraph);
                            document.add(table);
                            document.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (DocumentException e) {
                            e.printStackTrace();
                        }


                        progressLyt.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        sendingInformationTxt.setVisibility(View.VISIBLE);
                        sendingInformationTxt.setText("Göndərilir...");
                        doneImd.setVisibility(View.INVISIBLE);

                        //SendMail sm = new SendMail(navi_menu2.this, emailEdt.getText().toString(), myFile, progressBar, sendingInformationTxt, doneImd, "2");
                       // sm.execute();
                    }
                });

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        try {
                            FileUtils.deleteDirectory(pdfFolder);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                return true;
            }
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


    private class JSONParse extends AsyncTask<String, String, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {

            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            // List params = new ArrayList();
            params.add(new BasicNameValuePair("year", String.valueOf(mil_e)));
            params.add(new BasicNameValuePair("month", String.valueOf(may_e+1)));
            params.add(new BasicNameValuePair("userid", userId_));
            params.add(new BasicNameValuePair("type", "0"));

            //params.add(new BasicNameValuePair("pass", pass_));


            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url, params);
            return json;

        }

        @Override
        protected void onPostExecute(JSONObject json) {

            super.onPostExecute(json);

            try {
                if(json != null) {

                    Log.i("mmmmsss", json.toString());

                    String eveelkiAy = null;

                    if (may_e == 0) {
                        eveelkiAy = "Dekabr";
                    } else if (may_e == 1) {
                        eveelkiAy = "Yanvar";
                    } else if (may_e == 2) {
                        eveelkiAy = "Fevral";
                    } else if (may_e == 3) {
                        eveelkiAy = "Mart";
                    } else if (may_e == 4) {
                        eveelkiAy = "aprel";
                    } else if (may_e == 5) {
                        eveelkiAy = "May";
                    } else if (may_e == 6) {
                        eveelkiAy = "Iyun";
                    } else if (may_e == 7) {
                        eveelkiAy = "Iyul";
                    } else if (may_e == 8) {
                        eveelkiAy = "Avqust";
                    } else if (may_e == 9) {
                        eveelkiAy = "Sentyabr";
                    } else if (may_e == 10) {
                        eveelkiAy = "Oktyabr";
                    } else if (may_e == 11) {
                        eveelkiAy = "Noyabr";
                    }


                    JSONArray dataArray = json.getJSONArray("data");
                    JSONObject currentDayObject = dataArray.getJSONObject(0);
                    String currDay = currentDayObject.getString(T_CURR);
                    String currX = currentDayObject.getString(T_CURR_X);
                    String prwD = currentDayObject.getString(T_PRW_X);
                    String currMX = currentDayObject.getString(T_CURR_M_X);

                    java.text.DecimalFormat formatter;
                    formatter = new java.text.DecimalFormat("#,###,###");


                    int diff = Integer.parseInt(currX) - Integer.parseInt(prwD);

                    //String formatDiff = formatter.format(Float.parseFloat(String.valueOf(diff)));

                    // int y = (diff/(Integer.parseInt(prwD)))*100;
                    // mainDiffPrV.setText(String.valueOf(y) + "%");

                    Double y = ((Double.parseDouble(currX) - Double.parseDouble(prwD)) / Integer.parseInt(prwD)) * 100;
                    int y2 = (int) Math.round(y);

                    if(y2<0)
                    {
                        firstDiffImg.setImageResource(R.drawable.down_arrow);
                    }
                    //mainDiffPrV.setText(String.valueOf(y2) + "%");

                    firstDiffImg.setVisibility(View.VISIBLE);

                    mainDiffV.setText(changeForma(Float.parseFloat(String.valueOf(diff))) + "/" + String.valueOf(y2) + " %");
                    mainDiff.setVisibility(View.VISIBLE);
                    mainDiffV.setVisibility(View.VISIBLE);
                    // mainDiffPr.setVisibility(View.VISIBLE);
                    //mainDiffPrV.setVisibility(View.VISIBLE);

                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.VISIBLE);
                    view4.setVisibility(View.VISIBLE);
                    view5.setVisibility(View.VISIBLE);


                    gunlukTxt.setVisibility(View.VISIBLE);
                    gunlukTxtV.setVisibility(View.VISIBLE);
                    gunlukTxtV.setText(changeForma(Float.parseFloat(currDay)));

                    currentYearTxt.setVisibility(View.VISIBLE);
                    currentYearTxt.setText(String.valueOf(mil_e));
                    currentYearTxtV.setVisibility(View.VISIBLE);
                    currentYearTxtV.setText(changeForma(Float.parseFloat(currX)));

                    prwYearTxt.setVisibility(View.VISIBLE);
                    prwYearTxt.setText(String.valueOf(mil_e - 1));
                    prwYearTxtV.setVisibility(View.VISIBLE);
                    prwYearTxtV.setText(changeForma(Float.parseFloat(prwD)));

                    prwMonthTxt.setVisibility(View.VISIBLE);
                    if (eveelkiAy.equals("Dekabr")) {
                        prwMonthTxt.setText(String.valueOf(mil_e - 1) + "-" + eveelkiAy);
                    } else {
                        prwMonthTxt.setText(String.valueOf(mil_e) + "-" + eveelkiAy);
                    }
                    prwMonthTxtV.setVisibility(View.VISIBLE);
                    prwMonthTxtV.setText(changeForma(Float.parseFloat(currMX)));

                    prgbar1.setVisibility(View.INVISIBLE);

                }
                else
                {
                    Toast.makeText(navi_menu2.this, "Interneti Yoxlayin",Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


    private class JSONParse2 extends AsyncTask<String, String, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {

            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            // List params = new ArrayList();
            params.add(new BasicNameValuePair("year", String.valueOf(mil_e)));
            params.add(new BasicNameValuePair("month", String.valueOf(may_e+1)));
            params.add(new BasicNameValuePair("userid", userId_));

            //params.add(new BasicNameValuePair("pass", pass_));
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url2, params);
            return json;

        }

        @Override
        protected void onPostExecute(JSONObject json) {

            super.onPostExecute(json);

           // Log.i("zzzz", json.toString());
            String ay = null;

            if(may_e==0)
            {
                ay = "Yanvar";
            }
            else if(may_e==1)
            {
                ay = "Fevral";
            }
            else if(may_e==2)
            {
                ay = "Mart";
            }
            else if(may_e==3)
            {
                ay = "Aprel";
            }
            else if(may_e==4)
            {
                ay = "May";
            }
            else if(may_e==5)
            {
                ay = "Iyun";
            }
            else if(may_e==6)
            {
                ay = "Iyul";
            }
            else if(may_e==7)
            {
                ay = "Avqust";
            }
            else if(may_e==8)
            {
                ay = "Sentyabr";
            }
            else if(may_e==9)
            {
                ay = "Oktyabr";
            }
            else if(may_e==10)
            {
                ay = "Noyabr";
            }
            else
            {
                ay = "Dekabr";
            }

            try {

                JSONArray dataArray = json.getJSONArray("data");
                JSONObject currentDayObject = dataArray.getJSONObject(3);
                JSONObject currentDayObject2 = dataArray.getJSONObject(2);
                JSONObject currentDayObject3 = dataArray.getJSONObject(1);
                JSONObject currentDayObject4 = dataArray.getJSONObject(0);
                String curryear = currentDayObject.getString("ADY_CURR");
                String prwYear = currentDayObject.getString("ADY_PRW");
                String dif = currentDayObject.getString("DIF");
                String difPr = currentDayObject.getString("PR");

                String curryear2 = currentDayObject2.getString("ADY_CURR");
                String prwYear2 = currentDayObject2.getString("ADY_PRW");
                String dif2 = currentDayObject2.getString("DIF");
                String difPr2 = currentDayObject2.getString("PR");

                String curryear3 = currentDayObject3.getString("ADY_CURR");
                String prwYear3 = currentDayObject3.getString("ADY_PRW");
                String dif3 = currentDayObject3.getString("DIF");
                String difPr3 = currentDayObject3.getString("PR");

                String curryear4 = currentDayObject4.getString("ADY_CURR");
                String prwYear4 = currentDayObject4.getString("ADY_PRW");
                String dif4 = currentDayObject4.getString("DIF");
                String difPr4 = currentDayObject4.getString("PR");

                view6.setVisibility(View.VISIBLE);
                view7.setVisibility(View.VISIBLE);
                view8.setVisibility(View.VISIBLE);
                view9.setVisibility(View.VISIBLE);
                view10.setVisibility(View.VISIBLE);

                view11.setVisibility(View.VISIBLE);
                view12.setVisibility(View.VISIBLE);
                view13.setVisibility(View.VISIBLE);
                view14.setVisibility(View.VISIBLE);
                view15.setVisibility(View.VISIBLE);

                view16.setVisibility(View.VISIBLE);
                view17.setVisibility(View.VISIBLE);
                view18.setVisibility(View.VISIBLE);
                view19.setVisibility(View.VISIBLE);

                view20.setVisibility(View.VISIBLE);
                view21.setVisibility(View.VISIBLE);


                prwIdxalTxt.setVisibility(View.VISIBLE);
                prwIdxalTxt.setText(String.valueOf(mil_e-1)+"-" + ay);
                prwIdxalTxtV.setVisibility(View.VISIBLE);
                prwIdxalTxtV.setText(changeForma(Float.parseFloat(prwYear)));

                crrIdxalTxt.setVisibility(View.VISIBLE);
                crrIdxalTxt.setText(String.valueOf(mil_e)+"-" +ay);
                crrIdxalTxtV.setVisibility(View.VISIBLE);
                crrIdxalTxtV.setText(changeForma(Float.parseFloat(curryear)));

                idxalDiff.setVisibility(View.VISIBLE);
                idxalDiffV.setVisibility(View.VISIBLE);
                idxalDiffV.setText(changeForma(Float.parseFloat(dif)));

                idxalDiffPr.setVisibility(View.VISIBLE);
                IdxalDiffPrV.setVisibility(View.VISIBLE);
                Double x = Double.parseDouble(difPr)*100;
                IdxalDiffPrV.setText(String.valueOf(x.intValue())+"%");

                if(x<0)
                {
                    idxalDiffImg.setImageResource(R.drawable.down_arrow);
                }

                idxalDiffImg.setVisibility(View.VISIBLE);

                prgbar2.setVisibility(View.INVISIBLE);


                prwIxracTxt.setVisibility(View.VISIBLE);
                prwIxracTxt.setText(String.valueOf(mil_e-1)+"-" + ay);
                prwIxracTxtV.setVisibility(View.VISIBLE);
                prwIxracTxtV.setText(changeForma(Float.parseFloat(prwYear2)));

                crrIxracTxt.setVisibility(View.VISIBLE);
                crrIxracTxt.setText(String.valueOf(mil_e)+"-" +ay);
                crrIxracTxtV.setVisibility(View.VISIBLE);
                crrIxracTxtV.setText(changeForma(Float.parseFloat(curryear2)));

                ixracDiff.setVisibility(View.VISIBLE);
                ixracDiffV.setVisibility(View.VISIBLE);
                ixracDiffV.setText(changeForma(Float.parseFloat(dif2)));

                ixracDiffPr.setVisibility(View.VISIBLE);
                IxracDiffPrV.setVisibility(View.VISIBLE);
                Double x2 = Double.parseDouble(difPr2)*100;
                IxracDiffPrV.setText(String.valueOf(x2.intValue())+"%");

                if(x2<0){
                    ixracDiffImg.setImageResource(R.drawable.down_arrow);
                }
                ixracDiffImg.setVisibility(View.VISIBLE);

                prgbar3.setVisibility(View.INVISIBLE);

                prwTranzitTxt.setVisibility(View.VISIBLE);
                prwTranzitTxt.setText(String.valueOf(mil_e-1)+"-" + ay);
                prwTranzitTxtV.setVisibility(View.VISIBLE);
                prwTranzitTxtV.setText(changeForma(Float.parseFloat(prwYear3)));

                crrTranzitTxt.setVisibility(View.VISIBLE);
                crrTranzitTxt.setText(String.valueOf(mil_e)+"-" +ay);
                crrTranzitTxtV.setVisibility(View.VISIBLE);
                crrTranzitTxtV.setText(changeForma(Float.parseFloat(curryear3)));

                tranzitDiff.setVisibility(View.VISIBLE);
                tranzitDiffV.setVisibility(View.VISIBLE);
                tranzitDiffV.setText(changeForma(Float.parseFloat(dif3)));

                tranzitDiffPr.setVisibility(View.VISIBLE);
                tranzitDiffPrV.setVisibility(View.VISIBLE);
                Double x3 = Double.parseDouble(difPr3)*100;
                tranzitDiffPrV.setText(String.valueOf(x3.intValue())+"%");

                if(x3<0)
                {
                    tranzitDiffImg.setImageResource(R.drawable.down_arrow);
                }
                tranzitDiffImg.setVisibility(View.VISIBLE);

                prgbar4.setVisibility(View.INVISIBLE);



                prwDaxiliTxt.setVisibility(View.VISIBLE);
                prwDaxiliTxt.setText(String.valueOf(mil_e-1)+"-" + ay);
                prwDaxiliTxtV.setVisibility(View.VISIBLE);
                prwDaxiliTxtV.setText(changeForma(Float.parseFloat(prwYear4)));

                crrDaxiliTxt.setVisibility(View.VISIBLE);
                crrDaxiliTxt.setText(String.valueOf(mil_e)+"-" +ay);
                crrDaxiliTxtV.setVisibility(View.VISIBLE);
                crrDaxiliTxtV.setText(changeForma(Float.parseFloat(curryear4)));

                daxiliDiff.setVisibility(View.VISIBLE);
                daxiliDiffV.setVisibility(View.VISIBLE);
                daxiliDiffV.setText(changeForma(Float.parseFloat(dif4)));

                if(difPr4.equals(""))
                {
                    difPr4 = "0";
                }

                daxiliDiffPr.setVisibility(View.VISIBLE);
                daxiliDiffPrV.setVisibility(View.VISIBLE);
                Double x4 = Double.parseDouble(difPr4)*100;
                daxiliDiffPrV.setText(String.valueOf(x4.intValue())+"%");

                if(x4<0){
                    daxiliDiffImg.setImageResource(R.drawable.down_arrow);
                }
                daxiliDiffImg.setVisibility(View.VISIBLE);

                prgbar5.setVisibility(View.INVISIBLE);




            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public String changeString(String string) {

        char[] stringChar = string.toCharArray();

        for (int i = 0; i < stringChar.length; i++)

        {
            if (stringChar[i] == 'Ə') {
                stringChar[i] = 'W';
            } else if (stringChar[i] == 'ə') {
                stringChar[i] = 'w';
            }

        }

        return String.valueOf(stringChar);

    }

    public  String changeForma(Float f){

        java.text.DecimalFormat formatter;
        formatter = new java.text.DecimalFormat("#,###,###");

        String formattedNumber = formatter.format(f);
        String newFormat = formattedNumber.replaceAll("," , " ");

        return newFormat;

    }


}
