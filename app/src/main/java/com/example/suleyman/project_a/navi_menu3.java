package com.example.suleyman.project_a;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.preference.DialogPreference;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suleyman.project_a.Adapter.PointsAdapter;
import com.example.suleyman.project_a.Common.DailyList;
import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Common.MothlyList;
import com.example.suleyman.project_a.Common.PlanList;
import com.example.suleyman.project_a.Model.Points;
import com.example.suleyman.project_a.Sqlite.QnqDbHelper;
import com.example.suleyman.project_a.Sqlite.UpdateQnq;
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
import com.itextpdf.text.pdf.XfaForm;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import layout.Plan;

public class navi_menu3 extends AppCompatActivity

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

    public static  String yukInfoQnq = "";

    public static  String intellektQnq = "";
    public  static String intellektM1 = "";
    public  static  String intellektM2 = "";
    public static   String graphQnq = "";
    public  static  String graphQnq2 = "";
    public  static  String graphIlQnq = "";
    public  static  String graphGelirQnq = "";

    SQLiteDatabase SQLITEDATABASE;
    String GetSQliteQuery;
    Cursor cursor;
    int langval, themeval;
    ListView list1, list2, list3, list4, list5, list_main;
    private RelativeLayout sldlay1, sldlay2, sldlay3, sldlay4, sldlay5, sldlay6, sldlay7, sldlay8;
    Button sldbtn1, sldbtn2, sldbtn3, sldbtn4, sldbtn5, sldbtn6, sldbtn7, sldbtn8;
    RelativeLayout rel_lay1, rel_lay2, rel_lay3, rel_lay4, rel_lay5, rel_lay6, rel_lay7, rel_lay8;
    TextView txt, txt_tr_date, txt_in_date, txt_im_date, txt_ex_date, txt_total_date, lbl_im, lbl_ex, lbl_tr, lbl_in, lbl_total;
    ListView eletList, bakiList, boyukList, qaradagList, yalamaList, astaraList, hovsanList, sanqaList;
    ProgressBar eletPrg, bakiPrg, boyukPrg, qaradagPrg, yalamaPrg, astaraPrg, hovsanPrg, sanqaPrg;
    ProgressBar prgbar1;
    TextView eletDate, bakiDate, boyukDate, qaradagDate, yalamaDate, astaraDate, hovsanDate, sanqaDate;
    JSONArray Plan = null;
    UserSessionManager sessionManager;
    RelativeLayout dateLyt;
    TextView dateTxt;
    private static String url = "https://ady.express/Plan.asmx/Daily_Report";
    private static String url2 = "https://ady.express/Plan.asmx/Daily_ReportType";
    private  static  String qnqurl = "https://ady.express/PLan.asmx/all_qnqs";
    private  static  String dailyUrl = "";
    QnqDbHelper qnqDbHelper;
    SQLiteDatabase sqLiteDatabase, sqLiteDatabase2;
    int rowCount;
    Spinner dateType;
    RelativeLayout dateLyt2;
    TextView dateTxt2;

    final ArrayList<String> all_qnqs = new ArrayList<>();



   EditText emailEdt;
   RelativeLayout gonderLyt;
   ImageView closeImg;
   ProgressBar progressBar;
   ImageView doneImd, errorImage;
   TextView sendingInformationTxt;
   RelativeLayout progressLyt;
   File pdfFolder;
   RelativeLayout okLyt;



    public static NavigationView navigationView;


    DatePickerDialog.OnDateSetListener dateListener;
    DatePickerDialog.OnDateSetListener dateListener2;



    int mil_e;
    int may_e;
    int mgun_e;
    int mil_e2;
    int may_e2;
    int mgun_e2;


    String user_ = "";
    String pass_ = "";
    String name_ = "";
    String userId_ = "";

    GestureDetector mGestureDetector;
    GestureDetector mGestureDetector2;
    GestureDetector mGestureDetector3;
    GestureDetector mGestureDetector4;
    GestureDetector mGestureDetector5;
    GestureDetector mGestureDetector6;
    GestureDetector mGestureDetector7;
    GestureDetector mGestureDetector8;


    public navi_menu3() {
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


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE){

            Log.i("XLARGE", "XLARGE");

        }

       /* user_ = getIntent().getStringExtra("sess_user");
        pass_ = getIntent().getStringExtra("sess_pass");
        name_ = getIntent().getStringExtra("sess_name");
        userId_ = getIntent().getStringExtra("sess_id");
        final ArrayList<String> menular = getIntent().getStringArrayListExtra("menular");*/

        //Toast.makeText(this, userId_, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, menular.toString(), Toast.LENGTH_LONG).show();

        TextView tvw = (TextView) findViewById(R.id.txt_userfname);
        tvw.setText(name_);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        prgbar1 = (ProgressBar) findViewById(R.id.prgbar1);
        setSupportActionBar(toolbar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        DBCreate();

        eletList = (ListView) findViewById(R.id.eletList);
        bakiList = (ListView) findViewById(R.id.bakiList);
        bakiPrg = (ProgressBar) findViewById(R.id.bakiPrg);
        boyukPrg = (ProgressBar) findViewById(R.id.boyukPrg);
        boyukList = (ListView) findViewById(R.id.boyukList);
        qaradagPrg = (ProgressBar) findViewById(R.id.qaradagPrg);
        qaradagList = (ListView) findViewById(R.id.qaradagList);
        eletPrg = (ProgressBar) findViewById(R.id.eletPrg);
        yalamaList = (ListView) findViewById(R.id.yalamaList);
        yalamaPrg = (ProgressBar) findViewById(R.id.yalamaPrg);
        astaraList = (ListView) findViewById(R.id.astaraList);
        astaraPrg = (ProgressBar) findViewById(R.id.astaraPrg);
        hovsanList = (ListView) findViewById(R.id.hovsanList);
        hovsanPrg = (ProgressBar) findViewById(R.id.hovsanPrg);
        sanqaList = (ListView) findViewById(R.id.sanqaList);
        sanqaPrg = (ProgressBar) findViewById(R.id.sanqaPrg);


        rel_lay1 = (RelativeLayout) findViewById(R.id.relativeLayout);
        rel_lay2 = (RelativeLayout) findViewById(R.id.rel_lay2);
        rel_lay3 = (RelativeLayout) findViewById(R.id.rel_lay3);
        rel_lay4 = (RelativeLayout) findViewById(R.id.rel_lay4);
        rel_lay5 = (RelativeLayout) findViewById(R.id.rel_lay5);
        rel_lay6 = (RelativeLayout) findViewById(R.id.rel_lay6);
        rel_lay7 = (RelativeLayout) findViewById(R.id.rel_lay7);
        rel_lay8 = (RelativeLayout) findViewById(R.id.rel_lay8);

        sldlay1 = (RelativeLayout) findViewById(R.id.eletLyt);
        sldlay2 = (RelativeLayout) findViewById(R.id.bakiLyt);
        sldlay3 = (RelativeLayout) findViewById(R.id.boyukLyt);
        sldlay4 = (RelativeLayout) findViewById(R.id.qaradagLyt);
        sldlay5 = (RelativeLayout) findViewById(R.id.yalamaLyt);
        sldlay6 = (RelativeLayout) findViewById(R.id.astaraLyt);
        sldlay7 = (RelativeLayout) findViewById(R.id.hovsanLyt);
        sldlay8 = (RelativeLayout) findViewById(R.id.sanqaLyt);

        sldbtn1 = (Button) findViewById(R.id.SLDBTN1);
        sldbtn2 = (Button) findViewById(R.id.sldbtn2);
        sldbtn3 = (Button) findViewById(R.id.sldbtn3);
        sldbtn4 = (Button) findViewById(R.id.sldbtn4);
        sldbtn5 = (Button) findViewById(R.id.sldbtn5);
        sldbtn6 = (Button) findViewById(R.id.sldbtn6);
        sldbtn7 = (Button) findViewById(R.id.sldbtn7);
        sldbtn8 = (Button) findViewById(R.id.sldbtn8);


        txt_tr_date = (TextView) findViewById(R.id.txt_tr_date);
        txt_in_date = (TextView) findViewById(R.id.txt_in_date);
        txt_im_date = (TextView) findViewById(R.id.txt_im_date);
        txt_ex_date = (TextView) findViewById(R.id.txt_ex_date);
        txt_total_date = (TextView) findViewById(R.id.txt_total_date);


        eletDate = (TextView) findViewById(R.id.eletDate);
        bakiDate = (TextView) findViewById(R.id.bakiDate);
        boyukDate = (TextView) findViewById(R.id.boyukDate);
        qaradagDate = (TextView) findViewById(R.id.qaradagDate);
        yalamaDate = (TextView) findViewById(R.id.yalamaDate);
        astaraDate = (TextView) findViewById(R.id.astaraDate);
        hovsanDate = (TextView) findViewById(R.id.hovsanDate);
        sanqaDate = (TextView) findViewById(R.id.sanqaDate);

        dateType = (Spinner) findViewById(R.id.dateType);
        dateLyt2 = (RelativeLayout) findViewById(R.id.dateLyt2);


        dateLyt = (RelativeLayout) findViewById(R.id.dateLyt);
        dateTxt = (TextView) findViewById(R.id.dateTxt);
        dateTxt2 = (TextView) findViewById(R.id.dateTxt2);

        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        final int mil = calendar.get(java.util.Calendar.YEAR);
        final int may = calendar.get(java.util.Calendar.MONTH);
        final int mgun = calendar.get(java.util.Calendar.DATE);

        final java.text.DecimalFormat formatter;
        formatter = new java.text.DecimalFormat("00");

        mil_e = calendar.get(java.util.Calendar.YEAR);
        may_e = calendar.get(java.util.Calendar.MONTH);
        mgun_e = calendar.get(java.util.Calendar.DATE);
        mil_e2 = calendar.get(java.util.Calendar.YEAR);
        may_e2= calendar.get(java.util.Calendar.MONTH);
        mgun_e2 = calendar.get(java.util.Calendar.DATE);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.date_type, R.layout.spinner_textview);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        dateType.setAdapter(adapter);

        dateType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0)
                {
                    dateLyt2.setVisibility(View.GONE);
                }
                else
                {
                    dateLyt2.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       // qnqDbHelper = new QnqDbHelper(getApplicationContext());
      // sqLiteDatabase = qnqDbHelper.getWritableDatabase();
       // sqLiteDatabase2 = qnqDbHelper.getReadableDatabase();

       // qnqDbHelper.createNewTable(sqLiteDatabase2);

       // cursor  = sqLiteDatabase2.query("AllQnqs", new String[]{"qnq_name"}, null, null, null, null, null);

      //  sqLiteDatabase2.execSQL("DROP TABLE IF EXISTS AllPoint");
       // sqLiteDatabase2.execSQL("DROP TABLE IF EXISTS AzPoints");

        final String date = String.valueOf(mil_e) + "-" + String.valueOf(may_e + 1) + "-" + String.valueOf(mgun_e);

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateTxt.setText(String.valueOf(i2) + "/" + String.valueOf(i1+1) + "/" + String.valueOf(i));
                mil_e = i;
                may_e = i1;
                mgun_e = i2;
                eletDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
                bakiDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
                boyukDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
                qaradagDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
                yalamaDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
                astaraDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
                hovsanDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
                sanqaDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
                String date2 = String.valueOf(mil_e) + "-" + String.valueOf(may_e + 1) + "-" + String.valueOf(mgun_e);
                new PointsTask(date2).execute();
            }
        };

        dateListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateTxt2.setText(String.valueOf(i2) + "/" + String.valueOf(i1+1) + "/" + String.valueOf(i));
                mil_e2 = i;
                may_e2 = i1;
                mgun_e2 = i2;

               // new PointsTask(date2).execute();
            }
        };

        dateLyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(navi_menu3.this,
                        R.style.datePickerStyle,
                        dateListener,
                        mil_e, may_e, mgun_e);

                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                //datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });


        dateLyt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(navi_menu3.this,
                        R.style.datePickerStyle,
                        dateListener2,
                        mil_e2, may_e2, mgun_e2);

                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                //datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        eletDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
        bakiDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
        boyukDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
        qaradagDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
        yalamaDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
        astaraDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
        hovsanDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
        sanqaDate.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));

        dateTxt.setText(String.valueOf(mgun_e) + "/" + String.valueOf(may_e+1) + "/" + String.valueOf(mil_e));
        dateTxt2.setText(String.valueOf(mgun_e2) + "/" + String.valueOf(may_e2+1) + "/" + String.valueOf(mil_e2));

        new PointsTask(date).execute();

       // updateQnq updateTask = new updateQnq();

   /*   if(cursor.getCount() == 0 && UpdateQnq.count == 0)
        {
          updateTask.execute();
          UpdateQnq.count = 1;
          Log.i("Melumat", "QNQ-leri yukleyir");
        }
        if(cursor.getCount()>0 && UpdateQnq.count == 0)
        {
          new checkQnqCount().execute();
          Log.i("Melumat", "Qnq-lerin sayini yoxlayir");
        }  */



        //cursor.close();


        CustomGestureDetector customGestureDetector = new CustomGestureDetector(rel_lay1, sldbtn1, sldlay1, rel_lay2, sldbtn2, sldlay2,
                rel_lay3, sldbtn3, sldlay3, rel_lay4, sldbtn4, sldlay4,  rel_lay5, sldbtn5, sldlay5, rel_lay6, sldbtn6, sldlay6,
                rel_lay7, sldbtn7, sldlay7, rel_lay8, sldbtn8, sldlay8);
        CustomGestureDetector customGestureDetector2 = new CustomGestureDetector(rel_lay2, sldbtn2, sldlay2, rel_lay1, sldbtn1, sldlay1,
                rel_lay3, sldbtn3, sldlay3, rel_lay4, sldbtn4, sldlay4,  rel_lay5, sldbtn5, sldlay5, rel_lay6, sldbtn6, sldlay6,
                rel_lay7, sldbtn7, sldlay7, rel_lay8, sldbtn8, sldlay8);
        CustomGestureDetector customGestureDetector3 = new CustomGestureDetector(rel_lay3, sldbtn3, sldlay3, rel_lay1, sldbtn1, sldlay1,
                rel_lay2, sldbtn2, sldlay2, rel_lay4, sldbtn4, sldlay4,  rel_lay5, sldbtn5, sldlay5, rel_lay6, sldbtn6, sldlay6,
                rel_lay7, sldbtn7, sldlay7, rel_lay8, sldbtn8, sldlay8);
        CustomGestureDetector customGestureDetector4 = new CustomGestureDetector(rel_lay4, sldbtn4, sldlay4, rel_lay1, sldbtn1, sldlay1,
                rel_lay2, sldbtn2, sldlay2, rel_lay3, sldbtn3, sldlay3,  rel_lay5, sldbtn5, sldlay5, rel_lay6, sldbtn6, sldlay6,
                rel_lay7, sldbtn7, sldlay7, rel_lay8, sldbtn8, sldlay8);
        CustomGestureDetector customGestureDetector5 = new CustomGestureDetector(rel_lay5, sldbtn5, sldlay5, rel_lay1, sldbtn1, sldlay1,
                rel_lay2, sldbtn2, sldlay2, rel_lay3, sldbtn3, sldlay3,  rel_lay4, sldbtn4, sldlay4, rel_lay6, sldbtn6, sldlay6,
                rel_lay7, sldbtn7, sldlay7, rel_lay8, sldbtn8, sldlay8);
        CustomGestureDetector customGestureDetector6 = new CustomGestureDetector(rel_lay6, sldbtn6, sldlay6, rel_lay1, sldbtn1, sldlay1,
                rel_lay2, sldbtn2, sldlay2, rel_lay3, sldbtn3, sldlay3,  rel_lay4, sldbtn4, sldlay4, rel_lay5, sldbtn5, sldlay5,
                rel_lay7, sldbtn7, sldlay7, rel_lay8, sldbtn8, sldlay8);
        CustomGestureDetector customGestureDetector7 = new CustomGestureDetector(rel_lay7, sldbtn7, sldlay7, rel_lay1, sldbtn1, sldlay1,
                rel_lay2, sldbtn2, sldlay2, rel_lay3, sldbtn3, sldlay3,  rel_lay4, sldbtn4, sldlay4, rel_lay5, sldbtn5, sldlay5,
                rel_lay6, sldbtn6, sldlay6, rel_lay8, sldbtn8, sldlay8);
        CustomGestureDetector customGestureDetector8 = new CustomGestureDetector(rel_lay8, sldbtn8, sldlay8, rel_lay1, sldbtn1, sldlay1,
                rel_lay2, sldbtn2, sldlay2, rel_lay3, sldbtn3, sldlay3,  rel_lay4, sldbtn4, sldlay4, rel_lay5, sldbtn5, sldlay5,
                rel_lay6, sldbtn6, sldlay6, rel_lay7, sldbtn7, sldlay7);

        mGestureDetector  = new GestureDetector(this, customGestureDetector);
        mGestureDetector2 = new GestureDetector(this, customGestureDetector2);
        mGestureDetector3 = new GestureDetector(this, customGestureDetector3);
        mGestureDetector4 = new GestureDetector(this, customGestureDetector4);
        mGestureDetector5 = new GestureDetector(this, customGestureDetector5);
        mGestureDetector6 = new GestureDetector(this, customGestureDetector6);
        mGestureDetector7 = new GestureDetector(this, customGestureDetector7);
        mGestureDetector8 = new GestureDetector(this, customGestureDetector8);

        mGestureDetector.setOnDoubleTapListener(customGestureDetector);
        mGestureDetector2.setOnDoubleTapListener(customGestureDetector2);
        mGestureDetector3.setOnDoubleTapListener(customGestureDetector3);
        mGestureDetector4.setOnDoubleTapListener(customGestureDetector4);
        mGestureDetector5.setOnDoubleTapListener(customGestureDetector5);
        mGestureDetector6.setOnDoubleTapListener(customGestureDetector6);
        mGestureDetector7.setOnDoubleTapListener(customGestureDetector7);
        mGestureDetector8.setOnDoubleTapListener(customGestureDetector8);


        rel_lay1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        rel_lay2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector2.onTouchEvent(motionEvent);
                return true;
            }
        });
        rel_lay3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector3.onTouchEvent(motionEvent);
                return true;
            }
        });

        rel_lay4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector4.onTouchEvent(motionEvent);
                return true;
            }
        });
        rel_lay5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector5.onTouchEvent(motionEvent);
                return true;
            }
        });

        rel_lay6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector6.onTouchEvent(motionEvent);
                return true;
            }
        });
        rel_lay7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector7.onTouchEvent(motionEvent);
                return true;
            }
        });

        rel_lay8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector8.onTouchEvent(motionEvent);
                return true;
            }
        });

        sldbtn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        sldbtn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector2.onTouchEvent(motionEvent);
                return true;
            }
        });
        sldbtn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector3.onTouchEvent(motionEvent);
                return true;
            }
        });

        sldbtn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector4.onTouchEvent(motionEvent);
                return true;
            }
        });
        sldbtn5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector5.onTouchEvent(motionEvent);
                return true;
            }
        });

        sldbtn6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector6.onTouchEvent(motionEvent);
                return true;
            }
        });
        sldbtn7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector7.onTouchEvent(motionEvent);
                return true;
            }
        });

        sldbtn8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mGestureDetector8.onTouchEvent(motionEvent);
                return true;
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
        final MenuItem yukMenu = menu.findItem(R.id.nav_yuk_info);

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

      // final boolean check =  navigationView.getMenu().getItem(2).isChecked();



      navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.home) {

                    if(yukMenu.isChecked())
                    {
                      if(!yukInfoQnq.equals(""))
                      {
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                        alertDialog.setMessage("Yük İnfo-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                          .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              dialogInterface.dismiss();
                              setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                              Intent intent = new Intent(navi_menu3.this, navi_menu3.class);
                              intent.putExtra("sess_user", user_);
                              intent.putExtra("sess_pass", pass_);
                              intent.putExtra("sess_name", name_);
                              intent.putExtra("sess_id", userId_);
                              intent.putStringArrayListExtra("menular", menular);
                              startActivity(intent);
                              finish();
                              DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                              drawer.closeDrawer(GravityCompat.START);
                            }
                          }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                            yukMenu.setChecked(true);
                          }
                        });

                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();
                      }
                      else
                      {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        Intent intent = new Intent(navi_menu3.this, navi_menu3.class);
                        intent.putExtra("sess_user", user_);
                        intent.putExtra("sess_pass", pass_);
                        intent.putExtra("sess_name", name_);
                        intent.putExtra("sess_id", userId_);
                        intent.putStringArrayListExtra("menular", menular);
                        startActivity(intent);
                        finish();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                      }

                    }

                    if(graphMneu.isChecked())
                    {

                      if(!graphGelirQnq.equals("") || !graphQnq.equals("") || !graphIlQnq.equals("") || !graphQnq2.equals(""))
                      {
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                        alertDialog.setMessage("Qrafik-dən çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                          .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              dialogInterface.dismiss();
                              setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                              Intent intent = new Intent(navi_menu3.this, navi_menu3.class);
                              intent.putExtra("sess_user", user_);
                              intent.putExtra("sess_pass", pass_);
                              intent.putExtra("sess_name", name_);
                              intent.putExtra("sess_id", userId_);
                              intent.putStringArrayListExtra("menular", menular);
                              startActivity(intent);
                              finish();
                              DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                              drawer.closeDrawer(GravityCompat.START);
                            }
                          }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                            graphMneu.setChecked(true);
                          }
                        });

                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();
                      }
                      else
                      {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        Intent intent = new Intent(navi_menu3.this, navi_menu3.class);
                        intent.putExtra("sess_user", user_);
                        intent.putExtra("sess_pass", pass_);
                        intent.putExtra("sess_name", name_);
                        intent.putExtra("sess_id", userId_);
                        intent.putStringArrayListExtra("menular", menular);
                        startActivity(intent);
                        finish();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                      }

                    }

                    if(searchMenu.isChecked()){


                      if(!intellektQnq.equals("") || !intellektM2.equals("") || !intellektM1.equals(""))
                      {
                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                        alertDialog.setMessage("Intellektual Axtarışdan-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                          .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              dialogInterface.dismiss();
                              setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                              Intent intent = new Intent(navi_menu3.this, navi_menu3.class);
                              intent.putExtra("sess_user", user_);
                              intent.putExtra("sess_pass", pass_);
                              intent.putExtra("sess_name", name_);
                              intent.putExtra("sess_id", userId_);
                              intent.putStringArrayListExtra("menular", menular);
                              startActivity(intent);
                              finish();
                              DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                              drawer.closeDrawer(GravityCompat.START);
                            }
                          }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                            searchMenu.setChecked(true);
                          }
                        });

                        AlertDialog alertDialog1 = alertDialog.create();
                        alertDialog1.show();
                      }
                      else
                      {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        Intent intent = new Intent(navi_menu3.this, navi_menu3.class);
                        intent.putExtra("sess_user", user_);
                        intent.putExtra("sess_pass", pass_);
                        intent.putExtra("sess_name", name_);
                        intent.putExtra("sess_id", userId_);
                        intent.putStringArrayListExtra("menular", menular);
                        startActivity(intent);
                        finish();
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                      }

                    }
                    if(!graphMneu.isChecked() && !yukMenu.isChecked() && !searchMenu.isChecked()){
                      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                      Intent intent = new Intent(navi_menu3.this, navi_menu3.class);
                      intent.putExtra("sess_user", user_);
                      intent.putExtra("sess_pass", pass_);
                      intent.putExtra("sess_name", name_);
                      intent.putExtra("sess_id", userId_);
                      intent.putStringArrayListExtra("menular", menular);
                      startActivity(intent);
                      finish();
                    }
                }



                else if (menuItem.getItemId() == R.id.calculator) {



                  if(yukMenu.isChecked())
                  {
                    if(!yukInfoQnq.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Yük İnfo-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          yukMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
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
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(graphMneu.isChecked())
                  {


                   // Toast.makeText(getApplicationContext(), GraphFragment.sQnq.toString(), Toast.LENGTH_SHORT).show();

                    if(!graphGelirQnq.equals("") || !graphQnq.equals("") || !graphIlQnq.equals("") || !graphQnq2.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Qrafik-dən çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          graphMneu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
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
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(searchMenu.isChecked()){


                    if(!intellektQnq.equals("") || !intellektM2.equals("") || !intellektM1.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Intellektual Axtarışdan-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          searchMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
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
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }
                  if(!graphMneu.isChecked() && !yukMenu.isChecked() && !searchMenu.isChecked()){
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
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                  }

                } else if (menuItem.getItemId() == R.id.nav_in_search) {


                  if(searchMenu.isChecked()){
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                  }

                 if(yukMenu.isChecked())
                  {
                    if(!yukInfoQnq.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Yük İnfo-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            InSearch2 insrch = new InSearch2();
                            Bundle bundle = new Bundle();
                            bundle.putString("sess_user", user_);
                            bundle.putString("sess_pass", pass_);
                            bundle.putString("sess_name", name_);
                            bundle.putString("sess_id", userId_);

                            insrch.setArguments(bundle);
                            FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                            fragmenttarnsc.replace(R.id.content_navi_menu2, insrch, "search");
                            fragmenttarnsc.commit();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          yukMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
                      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                      InSearch2 insrch = new InSearch2();
                      Bundle bundle = new Bundle();
                      bundle.putString("sess_user", user_);
                      bundle.putString("sess_pass", pass_);
                      bundle.putString("sess_name", name_);
                      bundle.putString("sess_id", userId_);

                      insrch.setArguments(bundle);
                      FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                      fragmenttarnsc.replace(R.id.content_navi_menu2, insrch, "search");
                      fragmenttarnsc.commit();
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }
                  }

                  if(graphMneu.isChecked())
                  {

                    if(!graphGelirQnq.equals("") || !graphQnq.equals("") || !graphIlQnq.equals("") || !graphQnq2.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Qrafik-dən çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            InSearch2 insrch = new InSearch2();
                            Bundle bundle = new Bundle();
                            bundle.putString("sess_user", user_);
                            bundle.putString("sess_pass", pass_);
                            bundle.putString("sess_name", name_);
                            bundle.putString("sess_id", userId_);

                            insrch.setArguments(bundle);
                            FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                            fragmenttarnsc.replace(R.id.content_navi_menu2, insrch, "search");
                            fragmenttarnsc.commit();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          graphMneu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
                      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                      InSearch2 insrch = new InSearch2();
                      Bundle bundle = new Bundle();
                      bundle.putString("sess_user", user_);
                      bundle.putString("sess_pass", pass_);
                      bundle.putString("sess_name", name_);
                      bundle.putString("sess_id", userId_);

                      insrch.setArguments(bundle);
                      FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                      fragmenttarnsc.replace(R.id.content_navi_menu2, insrch, "search");
                      fragmenttarnsc.commit();
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(!graphMneu.isChecked() && !yukMenu.isChecked() && !searchMenu.isChecked()){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    InSearch2 insrch = new InSearch2();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);

                    insrch.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, insrch, "search");
                    fragmenttarnsc.commit();
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                  }

                 /* setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                  InSearch2 insrch = new InSearch2();
                  Bundle bundle = new Bundle();
                  bundle.putString("sess_user", user_);
                  bundle.putString("sess_pass", pass_);
                  bundle.putString("sess_name", name_);
                  bundle.putString("sess_id", userId_);

                  insrch.setArguments(bundle);
                  FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                  fragmenttarnsc.replace(R.id.content_navi_menu2, insrch, "search");
                  fragmenttarnsc.commit();
                  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                  drawer.closeDrawer(GravityCompat.START);*/

                }

   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////////////////////////


                else if (menuItem.getItemId() == R.id.nav_daily) {


                  if(yukMenu.isChecked())
                  {
                    if(!yukInfoQnq.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Yük İnfo-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            Daily daily = new Daily();
                            Bundle bundle = new Bundle();
                            bundle.putString("sess_user", user_);
                            bundle.putString("sess_pass", pass_);
                            bundle.putString("sess_name", name_);
                            bundle.putString("sess_id", userId_);

                            daily.setArguments(bundle);
                            FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                            fragmenttarnsc.replace(R.id.content_navi_menu2, daily, "daily");
                            fragmenttarnsc.commit();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          yukMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
                      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                      Daily daily = new Daily();
                      Bundle bundle = new Bundle();
                      bundle.putString("sess_user", user_);
                      bundle.putString("sess_pass", pass_);
                      bundle.putString("sess_name", name_);
                      bundle.putString("sess_id", userId_);

                      daily.setArguments(bundle);
                      FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                      fragmenttarnsc.replace(R.id.content_navi_menu2, daily, "daily");
                      fragmenttarnsc.commit();
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(graphMneu.isChecked())
                  {

                    if(!graphGelirQnq.equals("") || !graphQnq.equals("") || !graphIlQnq.equals("") || !graphQnq2.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Qrafik-dən çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            Daily daily = new Daily();
                            Bundle bundle = new Bundle();
                            bundle.putString("sess_user", user_);
                            bundle.putString("sess_pass", pass_);
                            bundle.putString("sess_name", name_);
                            bundle.putString("sess_id", userId_);

                            daily.setArguments(bundle);
                            FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                            fragmenttarnsc.replace(R.id.content_navi_menu2, daily, "daily");
                            fragmenttarnsc.commit();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          graphMneu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
                      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                      Daily daily = new Daily();
                      Bundle bundle = new Bundle();
                      bundle.putString("sess_user", user_);
                      bundle.putString("sess_pass", pass_);
                      bundle.putString("sess_name", name_);
                      bundle.putString("sess_id", userId_);

                      daily.setArguments(bundle);
                      FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                      fragmenttarnsc.replace(R.id.content_navi_menu2, daily, "daily");
                      fragmenttarnsc.commit();
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(searchMenu.isChecked()){


                    if(!intellektQnq.equals("") || !intellektM2.equals("") || !intellektM1.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Intellektual Axtarışdan-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            Daily daily = new Daily();
                            Bundle bundle = new Bundle();
                            bundle.putString("sess_user", user_);
                            bundle.putString("sess_pass", pass_);
                            bundle.putString("sess_name", name_);
                            bundle.putString("sess_id", userId_);

                            daily.setArguments(bundle);
                            FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                            fragmenttarnsc.replace(R.id.content_navi_menu2, daily, "daily");
                            fragmenttarnsc.commit();
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          searchMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
                      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                      Daily daily = new Daily();
                      Bundle bundle = new Bundle();
                      bundle.putString("sess_user", user_);
                      bundle.putString("sess_pass", pass_);
                      bundle.putString("sess_name", name_);
                      bundle.putString("sess_id", userId_);

                      daily.setArguments(bundle);
                      FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                      fragmenttarnsc.replace(R.id.content_navi_menu2, daily, "daily");
                      fragmenttarnsc.commit();
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(!graphMneu.isChecked() && !yukMenu.isChecked() && !searchMenu.isChecked()) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    Daily daily = new Daily();
                    Bundle bundle = new Bundle();
                    bundle.putString("sess_user", user_);
                    bundle.putString("sess_pass", pass_);
                    bundle.putString("sess_name", name_);
                    bundle.putString("sess_id", userId_);

                    daily.setArguments(bundle);
                    FragmentTransaction fragmenttarnsc = getSupportFragmentManager().beginTransaction();
                    fragmenttarnsc.replace(R.id.content_navi_menu2, daily, "daily");
                    fragmenttarnsc.commit();
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                  }


                  /////////////////////////////////////////////////////////////////////////////////////////
                  ///////////////////////////////////////////////////////////////////////////////////////////
                  //////////////////////////////////////////////////////////////////////////////////////////
                  //////////////////////////////////////////////////////////////////////////////////


                } else if (menuItem.getItemId() == R.id.nav_graf) {

                    if(graphMneu.isChecked())
                    {
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  if(searchMenu.isChecked()){


                    if(!intellektQnq.equals("") || !intellektM2.equals("") || !intellektM1.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Intellektual Axtarışdan-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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

                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          searchMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
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

                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }
                  }

                  if(yukMenu.isChecked())
                  {
                    if(!yukInfoQnq.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Yük İnfo-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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

                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          yukMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
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

                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(!graphMneu.isChecked() && !yukMenu.isChecked() && !searchMenu.isChecked()) {
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

                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);

                  }
                }



                //////////////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////////////////



                else if(menuItem.getItemId() == R.id.nav_yuk_info)

                {

                  if (yukMenu.isChecked()) {
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                  }

                  if (searchMenu.isChecked()) {
                    if (!intellektQnq.equals("") || !intellektM2.equals("") || !intellektM1.equals("")) {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Intellektual Axtarışdan-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          searchMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    } else {
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
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }
                  }


                  if (graphMneu.isChecked()) {

                    if (!graphGelirQnq.equals("") || !graphQnq.equals("") || !graphIlQnq.equals("") || !graphQnq2.equals("")) {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Qrafik-dən çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          graphMneu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    } else {
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
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if (!graphMneu.isChecked() && !yukMenu.isChecked() && !searchMenu.isChecked()) {
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
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);


                  }

                }

                /*  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                  drawer.closeDrawer(GravityCompat.START);*/

      //////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////////////////////


                else if (menuItem.getItemId() == R.id.nav_monthly) {


                  if(yukMenu.isChecked())
                  {
                    if(!yukInfoQnq.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Yük İnfo-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          yukMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
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
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(graphMneu.isChecked())
                  {

                    if(!graphGelirQnq.equals("") || !graphQnq.equals("") || !graphIlQnq.equals("") || !graphQnq2.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Qrafik-dən çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          graphMneu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
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
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(searchMenu.isChecked()){


                    if(!intellektQnq.equals("") || !intellektM2.equals("") || !intellektM1.equals(""))
                    {
                      final AlertDialog.Builder alertDialog = new AlertDialog.Builder(navi_menu3.this);
                      alertDialog.setMessage("Intellektual Axtarışdan-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
                        .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
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
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);
                          }
                        }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.dismiss();
                          DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                          drawer.closeDrawer(GravityCompat.START);
                          searchMenu.setChecked(true);
                        }
                      });

                      AlertDialog alertDialog1 = alertDialog.create();
                      alertDialog1.show();
                    }
                    else
                    {
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
                      DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                      drawer.closeDrawer(GravityCompat.START);
                    }

                  }

                  if(!graphMneu.isChecked() && !yukMenu.isChecked() && !searchMenu.isChecked()) {
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
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                  }


                } else if (menuItem.getItemId() == R.id.nav_share) {

                } else if (menuItem.getItemId() == R.id.nav_feedback) {

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"rsiadyexpress@gmail.com"});

                    startActivity(Intent.createChooser(intent, "Select Action"));

                } else if (menuItem.getItemId() == R.id.nav_Setting) {
                    Intent intent = new Intent(navi_menu3.this, Settings.class);
                    startActivity(intent);

                }
               /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);*/
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

        if(id == R.id.action_send) { /*
            if (dailyFragment != null && dailyFragment.isVisible()) {

                final Dialog dialog = new Dialog(this);
                View child = this.getLayoutInflater().inflate(R.layout.custom_email_dialog, null);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(child);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                dialog.show();

                 emailEdt = (EditText) dialog.findViewById(R.id.emailEdt);
                 gonderLyt = (RelativeLayout) dialog.findViewById(R.id.gonderBtn);
                 closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
                 progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
                 doneImd = (ImageView) dialog.findViewById(R.id.doneImg);
                 sendingInformationTxt = (TextView) dialog.findViewById(R.id.sendingInformationTxt);
                 progressLyt = (RelativeLayout) dialog.findViewById(R.id.progressLyt);
                 okLyt = (RelativeLayout) dialog.findViewById(R.id.okBtn);
                 errorImage = (ImageView) dialog.findViewById(R.id.errorImg);

              pdfFolder = new File(Environment.getExternalStorageDirectory(), "ResultsPdf");


                 okLyt.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                     dialog.dismiss();
                   }
                 });


                 closeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                 gonderLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                      if(Build.VERSION.SDK_INT >=23)
                      {
                        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                          sendDailyPdf(pdfFolder, progressLyt, progressBar,sendingInformationTxt,doneImd, errorImage, emailEdt, okLyt, gonderLyt);

                        }
                        else
                        {
                          ActivityCompat.requestPermissions(navi_menu3.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                        }
                      }
                      else
                      {
                        sendDailyPdf(pdfFolder, progressLyt, progressBar,sendingInformationTxt,doneImd, errorImage, emailEdt, okLyt, gonderLyt);
                      }


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


                emailEdt = (EditText) dialog.findViewById(R.id.emailEdt);
                gonderLyt = (RelativeLayout) dialog.findViewById(R.id.gonderBtn);
                closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
                progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
                doneImd = (ImageView) dialog.findViewById(R.id.doneImg);
                sendingInformationTxt = (TextView) dialog.findViewById(R.id.sendingInformationTxt);
                progressLyt = (RelativeLayout) dialog.findViewById(R.id.progressLyt);
                okLyt = (RelativeLayout) dialog.findViewById(R.id.okBtn);
                errorImage = (ImageView) dialog.findViewById(R.id.errorImg);

              pdfFolder = new File(Environment.getExternalStorageDirectory(), "ResultsPdf");

               okLyt.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                   dialog.dismiss();
                 }
               });


                closeImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                gonderLyt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      if(Build.VERSION.SDK_INT >=23)
                      {
                        if(checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                          sendMonthlyPdf(pdfFolder, progressLyt, progressBar,sendingInformationTxt,doneImd, errorImage, emailEdt, okLyt, gonderLyt);

                        }
                        else
                        {
                          ActivityCompat.requestPermissions(navi_menu3.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);

                        }
                      }
                      else
                      {
                        sendMonthlyPdf(pdfFolder, progressLyt, progressBar,sendingInformationTxt,doneImd, errorImage, emailEdt, okLyt, gonderLyt);
                      }

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
         */}

        return super.onOptionsItemSelected(item);
    }

    public class PointsTask extends  AsyncTask<String, String, JSONObject>{


        String date;
        JSONParser jParser;

        public PointsTask(String date)
        {
            this.date = date;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            eletPrg.setVisibility(View.VISIBLE);
            bakiPrg.setVisibility(View.VISIBLE);
            hovsanPrg.setVisibility(View.VISIBLE);
            boyukPrg.setVisibility(View.VISIBLE);
            yalamaPrg.setVisibility(View.VISIBLE);
            astaraPrg.setVisibility(View.VISIBLE);
            sanqaPrg.setVisibility(View.VISIBLE);
            qaradagPrg.setVisibility(View.VISIBLE);

            eletList.setVisibility(View.INVISIBLE);
            bakiList.setVisibility(View.INVISIBLE);
            hovsanList.setVisibility(View.INVISIBLE);
            boyukList.setVisibility(View.INVISIBLE);
            yalamaList.setVisibility(View.INVISIBLE);
            astaraList.setVisibility(View.INVISIBLE);
            sanqaList.setVisibility(View.INVISIBLE);
            qaradagList.setVisibility(View.INVISIBLE);

        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            jParser = new JSONParser();

            String parameter = "{  \n" +
                    "   \"GET_BORDERDATA\":[  \n" +
                    "      {  \n" +
                    "         \"ApiKey\":\"afcc18159f6cc74b4f511b99806da59b3caf5a9c173c\",\n" +
                    "         \"POINTCODE\":\"54\",\n" +
                    "         \"DATE\":\"" + date + "\"\n" +
                    "      }\n" +
                    "   ]\n" +
                    "}";

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            // List params = new ArrayList();
            params.add(new BasicNameValuePair("Parameter", parameter));

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl("https://smart.ady.az/Service.asmx/Get_BORDERDATA", params);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
          super.onPostExecute(jsonObject);

          Log.i("hahah", String.valueOf(date));
          Log.i("hahah", String.valueOf(jsonObject));
          if (jParser.error.equals("")) {
            try {

              ArrayList<Points> eletPoints = new ArrayList<>();
              ArrayList<Points> bakiPoints = new ArrayList<>();
              ArrayList<Points> boyukPoints = new ArrayList<>();
              ArrayList<Points> qaradagPoints = new ArrayList<>();
              ArrayList<Points> yalamaPoints = new ArrayList<>();
              ArrayList<Points> hovsanPoints = new ArrayList<>();
              ArrayList<Points> astaraPoints = new ArrayList<>();
              ArrayList<Points> sanqacalPoints = new ArrayList<>();

              int eletIdxalWeight = 0;
              int eletIxracWeight = 0;
              int eletTranzitWeight = 0;
              int eletIdxalDolu = 0;
              int eletIdxalBos = 0;
              int eletIdxalKont = 0;
              int eletIxracDolu = 0;
              int eletIxracBos = 0;
              int eletIxracKont = 0;
              int eletTranzitDolu = 0;
              int eletTranzitBos = 0;
              int eletTranzitKont = 0;
              int eletIdxalCem = 0;
              int eletIxracCem = 0;
              int eletTranzitCem = 0;
              String eletDolu1 = "0/0";
              String eletDolu2 = "0/0";
              String eletDolu3 = "0/0";


              int bakiIdxalWeight = 0;
              int bakiIxracWeight = 0;
              int bakiTranzitWeight = 0;
              int bakiIdxalDolu = 0;
              int bakiIdxalBos = 0;
              int bakiIdxalKont = 0;
              int bakiIxracDolu = 0;
              int bakiIxracBos = 0;
              int bakiIxracKont = 0;
              int bakiTranzitDolu = 0;
              int bakiTranzitBos = 0;
              int bakiTranzitKont = 0;
              int bakiIdxalCem = 0;
              int bakiIxracCem = 0;
              int bakiTranzitCem = 0;
              String bakiDolu1 = "0/0";
              String bakiDolu2 = "0/0";
              String bakiDolu3 = "0/0";


              int boyukIdxalWeight = 0;
              int boyukIxracWeight = 0;
              int boyukTranzitWeight = 0;
              int boyukIdxalDolu = 0;
              int boyukIdxalBos = 0;
              int boyukIdxalKont = 0;
              int boyukIxracDolu = 0;
              int boyukIxracBos = 0;
              int boyukIxracKont = 0;
              int boyukTranzitDolu = 0;
              int boyukTranzitBos = 0;
              int boyukTranzitKont = 0;
              int boyukIdxalCem = 0;
              int boyukIxracCem = 0;
              int boyukTranzitCem = 0;
              String boyukDolu1 = "0/0";
              String boyukDolu2 = "0/0";
              String boyukDolu3 = "0/0";


              int qaradagIdxalWeight = 0;
              int qaradagIxracWeight = 0;
              int qaradagTranzitWeight = 0;
              int qaradagIdxalDolu = 0;
              int qaradagIdxalBos = 0;
              int qaradagIdxalKont = 0;
              int qaradagIxracDolu = 0;
              int qaradagIxracBos = 0;
              int qaradagIxracKont = 0;
              int qaradagTranzitDolu = 0;
              int qaradagTranzitBos = 0;
              int qaradagTranzitKont = 0;
              int qaradagIdxalCem = 0;
              int qaradagIxracCem = 0;
              int qaradagTranzitCem = 0;
              String qaradagDolu1 = "0/0";
              String qaradagDolu2 = "0/0";
              String qaradagDolu3 = "0/0";


              int yalamaIdxalWeight = 0;
              int yalamaIxracWeight = 0;
              int yalamaTranzitWeight = 0;
              int yalamaIdxalDolu = 0;
              int yalamaIdxalBos = 0;
              int yalamaIdxalKont = 0;
              int yalamaIxracDolu = 0;
              int yalamaIxracBos = 0;
              int yalamaIxracKont = 0;
              int yalamaTranzitDolu = 0;
              int yalamaTranzitBos = 0;
              int yalamaTranzitKont = 0;
              int yalamaIdxalCem = 0;
              int yalamaIxracCem = 0;
              int yalamaTranzitCem = 0;
              String yalamaDolu1 = "0/0";
              String yalamaDolu2 = "0/0";
              String yalamaDolu3 = "0/0";


              int astaraIdxalWeight = 0;
              int astaraIxracWeight = 0;
              int astaraTranzitWeight = 0;
              int astaraIdxalDolu = 0;
              int astaraIdxalBos = 0;
              int astaraIdxalKont = 0;
              int astaraIxracDolu = 0;
              int astaraIxracBos = 0;
              int astaraIxracKont = 0;
              int astaraTranzitDolu = 0;
              int astaraTranzitBos = 0;
              int astaraTranzitKont = 0;
              int astaraIdxalCem = 0;
              int astaraIxracCem = 0;
              int astaraTranzitCem = 0;
              String astaraDolu1 = "0/0";
              String astaraDolu2 = "0/0";
              String astaraDolu3 = "0/0";


              int hovsanIdxalWeight = 0;
              int hovsanIxracWeight = 0;
              int hovsanTranzitWeight = 0;
              int hovsanIdxalDolu = 0;
              int hovsanIdxalBos = 0;
              int hovsanIdxalKont = 0;
              int hovsanIxracDolu = 0;
              int hovsanIxracBos = 0;
              int hovsanIxracKont = 0;
              int hovsanTranzitDolu = 0;
              int hovsanTranzitBos = 0;
              int hovsanTranzitKont = 0;
              int hovsanIdxalCem = 0;
              int hovsanIxracCem = 0;
              int hovsanTranzitCem = 0;
              String hovsanDolu1 = "0/0";
              String hovsanDolu2 = "0/0";
              String hovsanDolu3 = "0/0";

              int sanqaIdxalWeight = 0;
              int sanqaIxracWeight = 0;
              int sanqaTranzitWeight = 0;
              int sanqaIdxalDolu = 0;
              int sanqaIdxalBos = 0;
              int sanqaIdxalKont = 0;
              int sanqaIxracDolu = 0;
              int sanqaIxracBos = 0;
              int sanqaIxracKont = 0;
              int sanqaTranzitDolu = 0;
              int sanqaTranzitBos = 0;
              int sanqaTranzitKont = 0;
              int sanqaIdxalCem = 0;
              int sanqaIxracCem = 0;
              int sanqaTranzitCem = 0;
              String sanqaDolu1 = "0/0";
              String sanqaDolu2 = "0/0";
              String sanqaDolu3 = "0/0";

              Log.i("Melumat", "Serhedleri getirir");
              JSONArray jsonArray = jsonObject.getJSONArray("WAGONS");

              for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String begPoint = jsonObject1.getString("BGPOINTCODE");
                String endPoint = jsonObject1.getString("ENPOINTCODE");
                String fPoint = jsonObject1.getString("FPOINTCODE");
                String tPoint = jsonObject1.getString("TPOINTCODE");
                String weight = jsonObject1.getString("_WEIGHT");
                JSONArray contArray = jsonObject1.getJSONArray("CONTAINER");
                String type = jsonObject1.getString("TRANSTYPE");

                if (begPoint.contains("547508") || endPoint.contains("547508")) {
                  if (contArray.length() == 0) {
                    if (!weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        Log.i("koko", weight);
                        eletIdxalWeight = eletIdxalWeight + Integer.parseInt(weight);
                        eletIdxalDolu = eletIdxalDolu + 1;
                      }
                      if (type.equals("İxrac")) {
                        eletIxracWeight = eletIxracWeight + Integer.parseInt(weight);
                        eletIxracDolu = eletIxracDolu + 1;
                      }
                      if (type.equals("Tranzit")) {

                        eletTranzitWeight = eletTranzitWeight + Integer.parseInt(weight);
                        eletTranzitDolu = eletTranzitDolu + 1;
                      }
                    }
                    if (weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        eletIdxalBos = eletIdxalBos + 1;
                      }
                      if (type.equals("İxrac")) {
                        eletIxracBos = eletIxracBos + 1;
                      }
                      if (type.equals("Tranzit")) {
                        eletTranzitBos = eletTranzitBos + 1;
                      }
                    }
                  }
                  if (contArray.length() != 0) {
                    if (type.equals("İdxal")) {
                     // eletIdxalKont = eletIdxalKont + 1;
                         eletIdxalKont = eletIdxalKont + contArray.length();

                    }
                    if (type.equals("İxrac")) {
                     // eletIxracKont = eletIxracKont + 1;
                        eletIxracKont = eletIxracKont + contArray.length();


                    }
                    if (type.equals("Tranzit")) {
                      //eletTranzitKont = eletTranzitKont + 1;
                        eletTranzitKont = eletTranzitKont + contArray.length();

                    }
                  }


                  //  Log.i("koko", String.valueOf(eletIdxalDolu));

                  eletIdxalCem = eletIdxalBos + eletIdxalDolu + eletIdxalKont;
                  eletIxracCem = eletIxracDolu + eletIxracBos + eletIxracKont;
                  eletTranzitCem = eletTranzitDolu + eletTranzitBos + eletTranzitKont;


                  eletDolu1 = String.valueOf(eletIdxalWeight) + "/" + String.valueOf(eletIdxalDolu);
                  eletDolu2 = String.valueOf(eletIxracWeight) + "/" + String.valueOf(eletIxracDolu);
                  eletDolu3 = String.valueOf(eletTranzitWeight) + "/" + String.valueOf(eletTranzitDolu);


                }
                ///////////////////////////// Boyuk Kesik////////////////////////////////////////////////////////
                if (begPoint.contains("558701") || endPoint.contains("558701")) {

                  if (contArray.length() == 0) {
                    if (!weight.equals("0")) {
                      if (type.equals("İdxal")) {

                        bakiIdxalWeight = bakiIdxalWeight + Integer.parseInt(weight);
                        bakiIdxalDolu = bakiIdxalDolu + 1;
                      }
                      if (type.equals("İxrac")) {
                        bakiIxracWeight = bakiIxracWeight + Integer.parseInt(weight);
                        bakiIxracDolu = bakiIxracDolu + 1;
                      }
                      if (type.equals("Tranzit")) {

                        bakiTranzitWeight = bakiTranzitWeight + Integer.parseInt(weight);
                        bakiTranzitDolu = bakiTranzitDolu + 1;
                      }
                    }
                    if (weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        bakiIdxalBos = bakiIdxalBos + 1;
                      }
                      if (type.equals("İxrac")) {
                        bakiIxracBos = bakiIxracBos + 1;
                      }
                      if (type.equals("Tranzit")) {
                        bakiTranzitBos = bakiTranzitBos + 1;
                      }
                    }
                  }
                    if (contArray.length() != 0) {
                        if (type.equals("İdxal")) {
                            // eletIdxalKont = eletIdxalKont + 1;
                            bakiIdxalKont = bakiIdxalKont + contArray.length();

                        }
                        if (type.equals("İxrac")) {
                            // eletIxracKont = eletIxracKont + 1;
                            bakiIxracKont = bakiIxracKont + contArray.length();


                        }
                        if (type.equals("Tranzit")) {
                            //eletTranzitKont = eletTranzitKont + 1;
                            bakiTranzitKont = bakiTranzitKont + contArray.length();

                        }
                    }

                  bakiIdxalCem = bakiIdxalBos + bakiIdxalDolu + bakiIdxalKont;
                  bakiIxracCem = bakiIxracDolu + bakiIxracBos + bakiIxracKont;
                  bakiTranzitCem = bakiTranzitDolu + bakiTranzitBos + bakiTranzitKont;


                  bakiDolu1 = String.valueOf(bakiIdxalWeight) + "/" + String.valueOf(bakiIdxalDolu);
                  bakiDolu2 = String.valueOf(bakiIxracWeight) + "/" + String.valueOf(bakiIxracDolu);
                  bakiDolu3 = String.valueOf(bakiTranzitWeight) + "/" + String.valueOf(bakiTranzitDolu);

                }
                ////////////////////////////////Elet ////////////////////////////////////////////
                if (begPoint.contains("549204") || endPoint.contains("549204") || begPoint.contains("548803") || endPoint.contains("548803")
                  || begPoint.contains("553002") || endPoint.contains("553002")) {

                  if (contArray.length() == 0) {
                    if (!weight.equals("0")) {
                      if (type.equals("İdxal")) {

                        boyukIdxalWeight = boyukIdxalWeight + Integer.parseInt(weight);
                        boyukIdxalDolu = boyukIdxalDolu + 1;
                      }
                      if (type.equals("İxrac")) {
                        boyukIxracWeight = boyukIxracWeight + Integer.parseInt(weight);
                        boyukIxracDolu = boyukIxracDolu + 1;
                      }
                      if (type.equals("Tranzit")) {

                        boyukTranzitWeight = boyukTranzitWeight + Integer.parseInt(weight);
                        boyukTranzitDolu = boyukTranzitDolu + 1;
                      }
                    }
                    if (weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        boyukIdxalBos = boyukIdxalBos + 1;
                      }
                      if (type.equals("İxrac")) {
                        boyukIxracBos = boyukIxracBos + 1;
                      }
                      if (type.equals("Tranzit")) {
                        boyukTranzitBos = boyukTranzitBos + 1;
                      }
                    }
                  }
                    if (contArray.length() != 0) {
                        if (type.equals("İdxal")) {
                            // eletIdxalKont = eletIdxalKont + 1;
                            boyukIdxalKont = boyukIdxalKont + contArray.length();

                        }
                        if (type.equals("İxrac")) {
                            // eletIxracKont = eletIxracKont + 1;
                            boyukIxracKont = boyukIxracKont + contArray.length();


                        }
                        if (type.equals("Tranzit")) {
                            //eletTranzitKont = eletTranzitKont + 1;
                            boyukTranzitKont = boyukTranzitKont + contArray.length();

                        }
                    }

                  boyukIdxalCem = boyukIdxalBos + boyukIdxalDolu + boyukIdxalKont;
                  boyukIxracCem = boyukIxracDolu + boyukIxracBos + boyukIxracKont;
                  boyukTranzitCem = boyukTranzitDolu + boyukTranzitBos + boyukTranzitKont;


                  boyukDolu1 = String.valueOf(boyukIdxalWeight) + "/" + String.valueOf(boyukIdxalDolu);
                  boyukDolu2 = String.valueOf(boyukIxracWeight) + "/" + String.valueOf(boyukIxracDolu);
                  boyukDolu3 = String.valueOf(boyukTranzitWeight) + "/" + String.valueOf(boyukTranzitDolu);

                }

                ///////////////////////////////////  BAKI ////////////////////////////////////////////////////////////
                if (begPoint.contains("547603") || endPoint.contains("547603") || begPoint.contains("547209") || endPoint.contains("547209")) {

                  if (contArray.length() == 0) {
                    if (!weight.equals("0")) {
                      if (type.equals("İdxal")) {

                        qaradagIdxalWeight = qaradagIdxalWeight + Integer.parseInt(weight);
                        qaradagIdxalDolu = qaradagIdxalDolu + 1;
                      }
                      if (type.equals("İxrac")) {
                        qaradagIxracWeight = qaradagIxracWeight + Integer.parseInt(weight);
                        qaradagIxracDolu = qaradagIxracDolu + 1;
                      }
                      if (type.equals("Tranzit")) {

                        qaradagTranzitWeight = qaradagTranzitWeight + Integer.parseInt(weight);
                        qaradagTranzitDolu = qaradagTranzitDolu + 1;
                      }
                    }
                    if (weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        qaradagIdxalBos = qaradagIdxalBos + 1;
                      }
                      if (type.equals("İxrac")) {
                        qaradagIxracBos = qaradagIxracBos + 1;
                      }
                      if (type.equals("Tranzit")) {
                        qaradagTranzitBos = qaradagTranzitBos + 1;
                      }
                    }
                  }
                    if (contArray.length() != 0) {
                        if (type.equals("İdxal")) {
                            // eletIdxalKont = eletIdxalKont + 1;
                            qaradagIdxalKont = qaradagIdxalKont + contArray.length();

                        }
                        if (type.equals("İxrac")) {
                            // eletIxracKont = eletIxracKont + 1;
                            qaradagIxracKont = qaradagIxracKont + contArray.length();


                        }
                        if (type.equals("Tranzit")) {
                            //eletTranzitKont = eletTranzitKont + 1;
                            qaradagTranzitKont = qaradagTranzitKont + contArray.length();

                        }
                    }

                  qaradagIdxalCem = qaradagIdxalBos + qaradagIdxalDolu + qaradagIdxalKont;
                  qaradagIxracCem = qaradagIxracDolu + qaradagIxracBos + qaradagIxracKont;
                  qaradagTranzitCem = qaradagTranzitDolu + qaradagTranzitBos + qaradagTranzitKont;


                  qaradagDolu1 = String.valueOf(qaradagIdxalWeight) + "/" + String.valueOf(qaradagIdxalDolu);
                  qaradagDolu2 = String.valueOf(qaradagIxracWeight) + "/" + String.valueOf(qaradagIxracDolu);
                  qaradagDolu3 = String.valueOf(qaradagTranzitWeight) + "/" + String.valueOf(qaradagTranzitDolu);

                }

                /////////////////////////////////////      ASTARA ////////////////////////////////////////////////
                if (begPoint.contains("554503") || endPoint.contains("554503")) {

                  if (contArray.length() == 0) {
                    if (!weight.equals("0")) {
                      if (type.equals("İdxal")) {

                        yalamaIdxalWeight = yalamaIdxalWeight + Integer.parseInt(weight);
                        yalamaIdxalDolu = yalamaIdxalDolu + 1;
                      }
                      if (type.equals("İxrac")) {
                        yalamaIxracWeight = yalamaIxracWeight + Integer.parseInt(weight);
                        yalamaIxracDolu = yalamaIxracDolu + 1;
                      }
                      if (type.equals("Tranzit")) {

                        yalamaTranzitWeight = yalamaTranzitWeight + Integer.parseInt(weight);
                        yalamaTranzitDolu = yalamaTranzitDolu + 1;
                      }
                    }
                    if (weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        yalamaIdxalBos = yalamaIdxalBos + 1;
                      }
                      if (type.equals("İxrac")) {
                        yalamaIxracBos = yalamaIxracBos + 1;
                      }
                      if (type.equals("Tranzit")) {
                        yalamaTranzitBos = yalamaTranzitBos + 1;
                      }
                    }
                  }
                    if (contArray.length() != 0) {
                        if (type.equals("İdxal")) {
                            // eletIdxalKont = eletIdxalKont + 1;
                            yalamaIdxalKont = yalamaIdxalKont + contArray.length();

                        }
                        if (type.equals("İxrac")) {
                            // eletIxracKont = eletIxracKont + 1;
                            yalamaIxracKont = yalamaIxracKont + contArray.length();


                        }
                        if (type.equals("Tranzit")) {
                            //eletTranzitKont = eletTranzitKont + 1;
                            yalamaTranzitKont = yalamaTranzitKont + contArray.length();

                        }
                    }

                  yalamaIdxalCem = yalamaIdxalBos + yalamaIdxalDolu + yalamaIdxalKont;
                  yalamaIxracCem = yalamaIxracDolu + yalamaIxracBos + yalamaIxracKont;
                  yalamaTranzitCem = yalamaTranzitDolu + yalamaTranzitBos + yalamaTranzitKont;


                  yalamaDolu1 = String.valueOf(yalamaIdxalWeight) + "/" + String.valueOf(yalamaIdxalDolu);
                  yalamaDolu2 = String.valueOf(yalamaIxracWeight) + "/" + String.valueOf(yalamaIxracDolu);
                  yalamaDolu3 = String.valueOf(yalamaTranzitWeight) + "/" + String.valueOf(yalamaTranzitDolu);

                }
                /////////////////////// /   HOVSAN ?/////////////////////////////////////
                if (begPoint.contains("547800") || endPoint.contains("547800")) {

                  if (contArray.length() == 0) {
                    if (!weight.equals("0")) {
                      if (type.equals("İdxal")) {

                        astaraIdxalWeight = astaraIdxalWeight + Integer.parseInt(weight);
                        astaraIdxalDolu = astaraIdxalDolu + 1;
                      }
                      if (type.equals("İxrac")) {
                        astaraIxracWeight = astaraIxracWeight + Integer.parseInt(weight);
                        astaraIxracDolu = astaraIxracDolu + 1;
                      }
                      if (type.equals("Tranzit")) {

                        astaraTranzitWeight = astaraTranzitWeight + Integer.parseInt(weight);
                        astaraTranzitDolu = astaraTranzitDolu + 1;
                      }
                    }
                    if (weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        astaraIdxalBos = astaraIdxalBos + 1;
                      }
                      if (type.equals("İxrac")) {
                        astaraIxracBos = astaraIxracBos + 1;
                      }
                      if (type.equals("Tranzit")) {
                        astaraTranzitBos = astaraTranzitBos + 1;
                      }
                    }
                  }
                    if (contArray.length() != 0) {
                        if (type.equals("İdxal")) {
                            // eletIdxalKont = eletIdxalKont + 1;
                            astaraIdxalKont = astaraIdxalKont + contArray.length();

                        }
                        if (type.equals("İxrac")) {
                            // eletIxracKont = eletIxracKont + 1;
                            astaraIxracKont = astaraIxracKont + contArray.length();


                        }
                        if (type.equals("Tranzit")) {
                            //eletTranzitKont = eletTranzitKont + 1;
                            astaraTranzitKont = astaraTranzitKont + contArray.length();

                        }
                    }

                  astaraIdxalCem = astaraIdxalBos + astaraIdxalDolu + astaraIdxalKont;
                  astaraIxracCem = astaraIxracDolu + astaraIxracBos + astaraIxracKont;
                  astaraTranzitCem = astaraTranzitDolu + astaraTranzitBos + astaraTranzitKont;


                  astaraDolu1 = String.valueOf(astaraIdxalWeight) + "/" + String.valueOf(astaraIdxalDolu);
                  astaraDolu2 = String.valueOf(astaraIxracWeight) + "/" + String.valueOf(astaraIxracDolu);
                  astaraDolu3 = String.valueOf(astaraTranzitWeight) + "/" + String.valueOf(astaraTranzitDolu);

                }

                ///////////////////////////////// SANQACAL ////////////////////////////////////////////
                if (begPoint.contains("548305") || endPoint.contains("548305")) {

                  if (contArray.length() == 0) {
                    if (!weight.equals("0")) {
                      if (type.equals("İdxal")) {

                        hovsanIdxalWeight = hovsanIdxalWeight + Integer.parseInt(weight);
                        hovsanIdxalDolu = hovsanIdxalDolu + 1;
                      }
                      if (type.equals("İxrac")) {
                        hovsanIxracWeight = hovsanIxracWeight + Integer.parseInt(weight);
                        hovsanIxracDolu = hovsanIxracDolu + 1;
                      }
                      if (type.equals("Tranzit")) {

                        hovsanTranzitWeight = hovsanTranzitWeight + Integer.parseInt(weight);
                        hovsanTranzitDolu = hovsanTranzitDolu + 1;
                      }
                    }
                    if (weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        hovsanIdxalBos = hovsanIdxalBos + 1;
                      }
                      if (type.equals("İxrac")) {
                        hovsanIxracBos = hovsanIxracBos + 1;
                      }
                      if (type.equals("Tranzit")) {
                        hovsanTranzitBos = hovsanTranzitBos + 1;
                      }
                    }
                  }
                    if (contArray.length() != 0) {
                        if (type.equals("İdxal")) {
                            // eletIdxalKont = eletIdxalKont + 1;
                            hovsanIdxalKont = hovsanIdxalKont + contArray.length();

                        }
                        if (type.equals("İxrac")) {
                            // eletIxracKont = eletIxracKont + 1;
                            hovsanIxracKont = hovsanIxracKont + contArray.length();


                        }
                        if (type.equals("Tranzit")) {
                            //eletTranzitKont = eletTranzitKont + 1;
                            hovsanTranzitKont = hovsanTranzitKont + contArray.length();

                        }
                    }

                  hovsanIdxalCem = hovsanIdxalBos + hovsanIdxalDolu + hovsanIdxalKont;
                  hovsanIxracCem = hovsanIxracDolu + hovsanIxracBos + hovsanIxracKont;
                  hovsanTranzitCem = hovsanTranzitDolu + hovsanTranzitBos + hovsanTranzitKont;


                  hovsanDolu1 = String.valueOf(hovsanIdxalWeight) + "/" + String.valueOf(hovsanIdxalDolu);
                  hovsanDolu2 = String.valueOf(hovsanIxracWeight) + "/" + String.valueOf(hovsanIxracDolu);
                  hovsanDolu3 = String.valueOf(hovsanTranzitWeight) + "/" + String.valueOf(hovsanTranzitDolu);

                }

//////////////////////////////////////////// QARADAG ////////////////////////////////////////////////////////////////////
                if (begPoint.contains("548201") || endPoint.contains("548201")) {

                  if (contArray.length() == 0) {
                    if (!weight.equals("0")) {
                      if (type.equals("İdxal")) {

                        sanqaIdxalWeight = sanqaIdxalWeight + Integer.parseInt(weight);
                        sanqaIdxalDolu = sanqaIdxalDolu + 1;
                      }
                      if (type.equals("İxrac")) {
                        sanqaIxracWeight = sanqaIxracWeight + Integer.parseInt(weight);
                        sanqaIxracDolu = sanqaIxracDolu + 1;
                      }
                      if (type.equals("Tranzit")) {

                        sanqaTranzitWeight = sanqaTranzitWeight + Integer.parseInt(weight);
                        sanqaTranzitDolu = sanqaTranzitDolu + 1;
                      }
                    }
                    if (weight.equals("0")) {
                      if (type.equals("İdxal")) {
                        sanqaIdxalBos = sanqaIdxalBos + 1;
                      }
                      if (type.equals("İxrac")) {
                        sanqaIxracBos = sanqaIxracBos + 1;
                      }
                      if (type.equals("Tranzit")) {
                        sanqaTranzitBos = sanqaTranzitBos + 1;
                      }
                    }
                  }
                    if (contArray.length() != 0) {
                        if (type.equals("İdxal")) {
                            // eletIdxalKont = eletIdxalKont + 1;
                            sanqaIdxalKont = sanqaIdxalKont + contArray.length();

                        }
                        if (type.equals("İxrac")) {
                            // eletIxracKont = eletIxracKont + 1;
                            sanqaIxracKont = sanqaIxracKont + contArray.length();


                        }
                        if (type.equals("Tranzit")) {
                            //eletTranzitKont = eletTranzitKont + 1;
                            sanqaIxracKont = sanqaIxracKont + contArray.length();

                        }
                    }

                  sanqaIdxalCem = sanqaIdxalBos + sanqaIdxalDolu + sanqaIdxalKont;
                  sanqaIxracCem = sanqaIxracDolu + sanqaIxracBos + sanqaIxracKont;
                  sanqaTranzitCem = sanqaTranzitDolu + sanqaTranzitBos + sanqaTranzitKont;


                  sanqaDolu1 = String.valueOf(sanqaIdxalWeight) + "/" + String.valueOf(sanqaIdxalDolu);
                  sanqaDolu2 = String.valueOf(sanqaIxracWeight) + "/" + String.valueOf(sanqaIxracDolu);
                  sanqaDolu3 = String.valueOf(sanqaTranzitWeight) + "/" + String.valueOf(sanqaTranzitDolu);

                }

              }

              Points eletPoint = new Points(eletDolu1, String.valueOf(eletIdxalBos), String.valueOf(eletIdxalKont), String.valueOf(eletIdxalCem),
                eletDolu2, String.valueOf(eletIxracBos), String.valueOf(eletIxracKont), String.valueOf(eletIxracCem),
                eletDolu3, String.valueOf(eletTranzitBos), String.valueOf(eletTranzitKont), String.valueOf(eletTranzitCem));

              eletPoints.add(eletPoint);

              PointsAdapter adapter = new PointsAdapter(eletPoints, getApplicationContext());

              eletList.setAdapter(adapter);

              eletPrg.setVisibility(View.INVISIBLE);


              Points bakiPoint = new Points(bakiDolu1, String.valueOf(bakiIdxalBos), String.valueOf(bakiIdxalKont), String.valueOf(bakiIdxalCem),
                bakiDolu2, String.valueOf(bakiIxracBos), String.valueOf(bakiIxracKont), String.valueOf(bakiIxracCem),
                bakiDolu3, String.valueOf(bakiTranzitBos), String.valueOf(bakiTranzitKont), String.valueOf(bakiTranzitCem));

              bakiPoints.add(bakiPoint);

              PointsAdapter adapter2 = new PointsAdapter(bakiPoints, getApplicationContext());

              bakiList.setAdapter(adapter2);

              bakiPrg.setVisibility(View.INVISIBLE);


              Points boyukPoint = new Points(boyukDolu1, String.valueOf(boyukIdxalBos), String.valueOf(boyukIdxalKont), String.valueOf(boyukIdxalCem),
                boyukDolu2, String.valueOf(boyukIxracBos), String.valueOf(boyukIxracKont), String.valueOf(boyukIxracCem),
                boyukDolu3, String.valueOf(boyukTranzitBos), String.valueOf(boyukTranzitKont), String.valueOf(boyukTranzitCem));

              boyukPoints.add(boyukPoint);

              PointsAdapter adapter3 = new PointsAdapter(boyukPoints, getApplicationContext());

              boyukList.setAdapter(adapter3);

              boyukPrg.setVisibility(View.INVISIBLE);


              Points qaradagPoint = new Points(qaradagDolu1, String.valueOf(qaradagIdxalBos), String.valueOf(qaradagIdxalKont), String.valueOf(qaradagIdxalCem),
                qaradagDolu2, String.valueOf(qaradagIxracBos), String.valueOf(qaradagIxracKont), String.valueOf(qaradagIxracCem),
                qaradagDolu3, String.valueOf(qaradagTranzitBos), String.valueOf(qaradagTranzitKont), String.valueOf(qaradagTranzitCem));

              qaradagPoints.add(qaradagPoint);

              PointsAdapter adapter4 = new PointsAdapter(qaradagPoints, getApplicationContext());

              qaradagList.setAdapter(adapter4);

              qaradagPrg.setVisibility(View.INVISIBLE);


              Points yalamaPoint = new Points(yalamaDolu1, String.valueOf(yalamaIdxalBos), String.valueOf(yalamaIdxalKont), String.valueOf(yalamaIdxalCem),
                yalamaDolu2, String.valueOf(yalamaIxracBos), String.valueOf(yalamaIxracKont), String.valueOf(yalamaIxracCem),
                yalamaDolu3, String.valueOf(yalamaTranzitBos), String.valueOf(yalamaTranzitKont), String.valueOf(yalamaTranzitCem));

              yalamaPoints.add(yalamaPoint);

              PointsAdapter adapter5 = new PointsAdapter(yalamaPoints, getApplicationContext());

              yalamaList.setAdapter(adapter5);

              yalamaPrg.setVisibility(View.INVISIBLE);


              Points astaraPoint = new Points(astaraDolu1, String.valueOf(astaraIdxalBos), String.valueOf(astaraIdxalKont), String.valueOf(astaraIdxalCem),
                astaraDolu2, String.valueOf(astaraIxracBos), String.valueOf(astaraIxracKont), String.valueOf(astaraIxracCem),
                astaraDolu3, String.valueOf(astaraTranzitBos), String.valueOf(astaraTranzitKont), String.valueOf(astaraTranzitCem));

              astaraPoints.add(astaraPoint);

              PointsAdapter adapter6 = new PointsAdapter(astaraPoints, getApplicationContext());

              astaraList.setAdapter(adapter6);

              astaraPrg.setVisibility(View.INVISIBLE);


              Points hovsanPoint = new Points(hovsanDolu1, String.valueOf(hovsanIdxalBos), String.valueOf(hovsanIdxalKont), String.valueOf(hovsanIdxalCem),
                hovsanDolu2, String.valueOf(hovsanIxracBos), String.valueOf(hovsanIxracKont), String.valueOf(hovsanIxracCem),
                hovsanDolu3, String.valueOf(hovsanTranzitBos), String.valueOf(hovsanTranzitKont), String.valueOf(hovsanTranzitCem));

              hovsanPoints.add(hovsanPoint);

              PointsAdapter adapter7 = new PointsAdapter(hovsanPoints, getApplicationContext());

              hovsanList.setAdapter(adapter7);

              hovsanPrg.setVisibility(View.INVISIBLE);


              Points sanqaPoint = new Points(sanqaDolu1, String.valueOf(sanqaIdxalBos), String.valueOf(sanqaIdxalKont), String.valueOf(sanqaIdxalCem),
                sanqaDolu2, String.valueOf(sanqaIxracBos), String.valueOf(sanqaIxracKont), String.valueOf(sanqaIxracCem),
                sanqaDolu3, String.valueOf(sanqaTranzitBos), String.valueOf(sanqaTranzitKont), String.valueOf(sanqaTranzitCem));

              sanqacalPoints.add(sanqaPoint);

              PointsAdapter adapter8 = new PointsAdapter(sanqacalPoints, getApplicationContext());

              sanqaList.setAdapter(adapter8);

              sanqaPrg.setVisibility(View.INVISIBLE);

              eletList.setVisibility(View.VISIBLE);
              bakiList.setVisibility(View.VISIBLE);
              hovsanList.setVisibility(View.VISIBLE);
              boyukList.setVisibility(View.VISIBLE);
              yalamaList.setVisibility(View.VISIBLE);
              astaraList.setVisibility(View.VISIBLE);
              sanqaList.setVisibility(View.VISIBLE);
              qaradagList.setVisibility(View.VISIBLE);


            } catch (JSONException e) {
              e.printStackTrace();
            }

          }
          else
          {
            Toast.makeText(getApplicationContext(), jParser.error.toString(), Toast.LENGTH_SHORT).show();
          }
        }

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


    class checkQnqCount extends AsyncTask<String, String, JSONObject>{

      @Override
      protected JSONObject doInBackground(String... strings) {
        JSONParser jParser = new JSONParser();

        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("userId", userId_));
        JSONObject json = jParser.getJSONFromUrl("https://ady.express/PLan.asmx/all_qnqscount", params);

        return json;
      }


      @Override
      protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);


        QnqDbHelper dbHelper = new QnqDbHelper(getApplicationContext());
        SQLiteDatabase db1 = dbHelper.getWritableDatabase();
        SQLiteDatabase db2 = dbHelper.getReadableDatabase();
        Cursor cursor  = db2.query("AllQnqs", new String[]{"qnq_name"}, null, null, null, null, null);
        Log.i("Melumat", "Qnq-lerin sayini yoxladi");

        try {
          if (jsonObject != null) {
            JSONArray dataArray = jsonObject.getJSONArray("data");

            JSONObject jsonObject1 = dataArray.getJSONObject(0);
            String count = jsonObject1.getString("count");
            int say = Integer.parseInt(count);

            if(say>cursor.getCount())
            {
              new updateQnq().execute();
            }

          }


        } catch (JSONException e) {
          e.printStackTrace();
        }

      }
    }


    class updateQnq extends  AsyncTask<String, String , JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {

            JSONParser jParser = new JSONParser();

            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("userId", userId_));
            JSONObject json = jParser.getJSONFromUrl(qnqurl, params);

            return json;

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            new addToDb(jsonObject).execute();
            Log.i("Melumat", "QNQ-leri yukledi");

        }

    }


    class  addToDb  extends AsyncTask<String, String, JSONObject> {

        JSONObject jsonObject;

        public addToDb(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            QnqDbHelper dbHelper = new QnqDbHelper(getApplicationContext());
            SQLiteDatabase db1 = dbHelper.getWritableDatabase();
            SQLiteDatabase db2 = dbHelper.getReadableDatabase();

           //updat dbHelper.clearQnqs(db2);

            try {
                if (jsonObject != null) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    // UpdateQnq updateQnq = new UpdateQnq();

                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject jsonObject1 = dataArray.getJSONObject(i);
                        String code = jsonObject1.getString("FCT_QNQ");
                        String name = jsonObject1.getString("Name");

                        dbHelper.addQnqName(code + "-" + name, db1);

                    }
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

      @Override
      protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        Log.i("Melumat", "Qnqleri DATBASEYE YAZDI");
      }
    }

    class CustomGestureDetector implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

        RelativeLayout Lyt1, Lyt2, Lyt3, Lyt4, Lyt5, Lyt6, Lyt7, Lyt8;
        Button img1,img2, img3, img4, img5, img6, img7, img8;
        RelativeLayout sld1, sld2, sld3, sld4, sld5, sld6,sld7, sld8;


        public CustomGestureDetector(RelativeLayout Lyt1,  Button img1, RelativeLayout sld1,
                                     RelativeLayout Lyt2, Button img2, RelativeLayout sld2,
                                     RelativeLayout Lyt3, Button img3, RelativeLayout sld3,
                                     RelativeLayout Lyt4, Button img4, RelativeLayout sld4,
                                     RelativeLayout Lyt5, Button img5, RelativeLayout sld5,
                                     RelativeLayout Lyt6, Button img6, RelativeLayout sld6,
                                     RelativeLayout Lyt7, Button img7, RelativeLayout sld7,
                                     RelativeLayout Lyt8, Button img8, RelativeLayout sld8){
            this.Lyt1 = Lyt1;
            this.img1 = img1;
            this.sld1 = sld1;
            this.Lyt2 = Lyt2;
            this.img2 = img2;
            this.sld2 = sld2;
            this.Lyt3 = Lyt3;
            this.img3 = img3;
            this.sld3 = sld3;
            this.Lyt4 = Lyt4;
            this.img4 = img4;
            this.sld4 = sld4;
            this.Lyt5 = Lyt5;
            this.img5 = img5;
            this.sld5 = sld5;
            this.Lyt6 = Lyt6;
            this.img6 = img6;
            this.sld6 = sld6;
            this.Lyt7 = Lyt7;
            this.img7 = img7;
            this.sld7 = sld7;
            this.Lyt8 = Lyt8;
            this.img8 = img8;
            this.sld8 = sld8;
            //this.secondLyt = secondLyt;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {


            if(sld1.getVisibility() == View.GONE){
                Animation bottomUp = AnimationUtils.loadAnimation(navi_menu3.this, R.anim.bottom_up);
                sld1.setVisibility(View.VISIBLE);
                sld1.startAnimation(bottomUp);
                img1.setBackgroundResource(R.drawable.slide_btn);


                sld2.setVisibility(View.GONE);
                sld2.clearAnimation();
                img2.setBackgroundResource(R.drawable.right_arrow2);

                sld3.setVisibility(View.GONE);
                sld3.clearAnimation();
                img3.setBackgroundResource(R.drawable.right_arrow2);

                sld4.setVisibility(View.GONE);
                sld4.clearAnimation();
                img4.setBackgroundResource(R.drawable.right_arrow2);

                sld5.setVisibility(View.GONE);
                sld5.clearAnimation();
                img5.setBackgroundResource(R.drawable.right_arrow2);

                sld6.setVisibility(View.GONE);
                sld6.clearAnimation();
                img6.setBackgroundResource(R.drawable.right_arrow2);

                sld7.setVisibility(View.GONE);
                sld7.clearAnimation();
                img7.setBackgroundResource(R.drawable.right_arrow2);

                sld8.setVisibility(View.GONE);
                sld8.clearAnimation();
                img8.setBackgroundResource(R.drawable.right_arrow2);
            }
            else{
                sld1.setVisibility(View.GONE);
                sld1.clearAnimation();
                img1.setBackgroundResource(R.drawable.right_arrow2);

            }


            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (sld1.getVisibility() == View.GONE) {
                            Animation bottomUp = AnimationUtils.loadAnimation(navi_menu3.this, R.anim.bottom_up);
                            sld1.setVisibility(View.VISIBLE);
                            sld1.startAnimation(bottomUp);
                            img1.setBackgroundResource(R.drawable.slide_btn);
                        }

            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    }


    public void sendDailyPdf(File file, RelativeLayout relativeLayout, ProgressBar progressBar, TextView textView1,
                              ImageView imageView, ImageView errorImage, EditText editText, RelativeLayout okLyt, RelativeLayout gonderLyt){

      if (!file.exists()) {
        file.mkdir();
      }

      final File myFile;

      String timeStamp = "Günlük Hesabat";

      myFile = new File("/" + file + "/" + timeStamp + ".pdf");
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
          " " + changeString("və ") + String.valueOf(Integer.parseInt(DailyList.personList.get(1).getSecondYear()))  + changeString("-ci illərdə ") + Daily.spinner.getSelectedItem().toString()+
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

      relativeLayout.setVisibility(View.VISIBLE);
      progressBar.setVisibility(View.VISIBLE);
      textView1.setVisibility(View.VISIBLE);
      textView1.setText("Göndərilir...");
      imageView.setVisibility(View.INVISIBLE);
      errorImage.setVisibility(View.INVISIBLE);
      textView1.setTextColor(Color.GRAY);

      SendMail sm = new SendMail(navi_menu3.this, editText.getText().toString(), myFile, progressBar, textView1, imageView, errorImage, okLyt, gonderLyt, "1");
      sm.execute();

    }


    public void sendMonthlyPdf(File file, RelativeLayout relativeLayout, ProgressBar progressBar, TextView textView1,
                               ImageView imageView, ImageView errorImage, EditText editText, RelativeLayout okLyt, RelativeLayout gonderLyt){

      if (!file.exists()) {
        file.mkdir();
      }

      final File myFile;
      String timeStamp = "Ayliq Hesabat";

      myFile = new File("/" + file + "/" + timeStamp + ".pdf");

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

        cell = new PdfPCell(new Phrase(changeString("Cəm"), font2));
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        inner = new PdfPTable(2);
        PdfPCell pdfPCell1 = new PdfPCell(new Phrase(String.valueOf(DailyList.sumFirstYear), font2));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(String.valueOf(DailyList.sumSecondYear), font2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(String.valueOf(DailyList.sumDiff), font2));

        pdfPCell1.setBackgroundColor(BaseColor.GRAY);
        if(DailyList.personList.get(0).getSecondYear().equals(String.valueOf(mil_e)))
        {
            Log.i("RENG", "Reng");
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

        cell = new PdfPCell(new Phrase(changeString("İllik"), font2));
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

      relativeLayout.setVisibility(View.VISIBLE);
      progressBar.setVisibility(View.VISIBLE);
      textView1.setVisibility(View.VISIBLE);
      textView1.setText("Göndərilir...");
      imageView.setVisibility(View.INVISIBLE);
      errorImage.setVisibility(View.INVISIBLE);

      SendMail sm = new SendMail(navi_menu3.this, editText.getText().toString(), myFile, progressBar, textView1, imageView, errorImage, okLyt, gonderLyt, "2");
      sm.execute();

    }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if(requestCode == 1)
    {
      if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
      {
        sendDailyPdf(pdfFolder, progressLyt, progressBar,sendingInformationTxt,doneImd, errorImage, emailEdt, okLyt, gonderLyt);

      }
      else
      {
        Toast.makeText(getApplicationContext(), "PDF-i gondermek ucun icaze vermelisiniz", Toast.LENGTH_SHORT).show();
      }
    }

    if(requestCode == 2)
    {
      if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
      {
        sendMonthlyPdf(pdfFolder, progressLyt, progressBar,sendingInformationTxt,doneImd, errorImage, emailEdt, okLyt, gonderLyt);

      }
      else
      {
        Toast.makeText(getApplicationContext(), "PDF-i gondermek ucun icaze vermelisiniz", Toast.LENGTH_SHORT).show();
      }
    }
  }

  @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        mGestureDetector2.onTouchEvent(event);
        mGestureDetector3.onTouchEvent(event);
        mGestureDetector4.onTouchEvent(event);
        mGestureDetector5.onTouchEvent(event);
        mGestureDetector6.onTouchEvent(event);
        mGestureDetector7.onTouchEvent(event);
        mGestureDetector8.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
