package com.example.suleyman.project_a;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.DialogPreference;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.suleyman.project_a.Adapter.IntellektualAdapter;
import com.example.suleyman.project_a.Adapter.IntellektualAdapter2;
import com.example.suleyman.project_a.Adapter.QnqAdapter;
import com.example.suleyman.project_a.Common.AscyntaskState;
import com.example.suleyman.project_a.Common.DailyList;
import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Model.QnqModel;
import com.example.suleyman.project_a.Sqlite.QnqDbHelper;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InSearch2 extends Fragment {

  String user_="";
  String pass_="";
  String name_="";
  String userId_="";

  EditText qnqEdt, m1Edt, m2Edt, weight1Edt, weight2Edt;
  RelativeLayout filterLyt, prePriceLyt, reultLyt;
  Spinner rejimType, dasimaType, yukType, rubType, vagonType, ownerType, originType, alSatType, searchType;
  ImageView qnqSearchImg, m1SearchImg, m2SearchImg;
  ProgressBar resultPrg;
  RelativeLayout resultLyt;
  Button okButton;
  CheckBox yearCheck;
  DrawerLayout drawerLayout;
  ListView resultList;
  Dialog dialog;
  String toastMessage = "";
  HashMap<String, String> hashmap2 = new HashMap<>();
  ArrayList<HashMap<String, String>> browselist;
  TextView tonnajTxt;
  int checkPoint = 0;

  ArrayList<String> onlyCodes = new ArrayList<>();
  ArrayList<String> qnqCodes = new ArrayList<>();

  ArrayList<String> onlyPointCodes = new ArrayList<>();
  ArrayList<String> pointCodes = new ArrayList<>();
  ArrayList<String> serhedPointCodes = new ArrayList<>();
  ArrayList<String> daxiliPointCodes = new ArrayList<>();
  ArrayList<String> onlySerhedPointCodes = new ArrayList<>();
  ArrayList<String> onlyDaxiliPointCodes = new ArrayList<>();

  ArrayList<String> rejimler = new ArrayList<>();
  ArrayList<String> rubler = new ArrayList<>();
  ArrayList<String> dasimaNovu = new ArrayList<>();
  ArrayList<String> yukNovu = new ArrayList<>();
  ArrayList<String> dasimaVasitesi = new ArrayList<>();
  ArrayList<String> mensubiyyet = new ArrayList<>();
  ArrayList<String> menseyi = new ArrayList<>();
  ArrayList<String> qiymet = new ArrayList<>();
  ArrayList<String> axtaris = new ArrayList<>();
  HashMap<String, String>  hashMap = new HashMap<>();

  StringRequest stringRequest;
  RequestQueue requestQueue;
  ArrayList<QnqModel> qnqModelList = new ArrayList<>();


  String sRejim = "5";              // actype
  String sDasimaNovu = "1";         // vagonType
  String sYukNovu = "1";            // type
  String sRub = "0";                // quarter
  String sDasimaVasitesi = "0";     // rtype
  String sMensub = "0";             // owner
  String sMense = "0";              // origin
  String sQiymet = "2";             // ptype
  String sAxtaris = "1";            // searchtype
  String minWeight = "";            //
  String maxWeight = "";
  String mYear = "0";
  String qnq = "";
  String m1 = "";
  String m2 = "";


  EditText emailEdt;
  RelativeLayout gonderLyt;
  ImageView closeImg;
  ProgressBar progressBar;
  ImageView doneImd;
  TextView sendingInformationTxt;
  RelativeLayout progressLyt;
  File pdfFolder;




  QnqDbHelper qnqDbHelper;
  SQLiteDatabase sqLiteDatabase, sqLiteDatabase2;

  Cursor cursor2;


  String urlSearch =  "https://ady.express/Plan.asmx/Search";
  String urlResult = "https://ady.express/Plan.asmx/Result";

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    if (getArguments() != null) {

      setHasOptionsMenu(true);

      user_ = getArguments().getString("sess_user");
      pass_ = getArguments().getString("sess_pass");
      name_ = getArguments().getString("sess_name");
      userId_ = getArguments().getString("sess_id");
    }
    }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

    View view =  inflater.inflate(R.layout.intellektual_layout, container, false);

    qnqEdt = (EditText) view.findViewById(R.id.qnqEditText);
    m1Edt = (EditText) view.findViewById(R.id.m1EditText);
    m2Edt = (EditText) view.findViewById(R.id.m2EditText);
    weight1Edt = (EditText) view.findViewById(R.id.weight1Edt);
    weight2Edt = (EditText) view.findViewById(R.id.weight2Edt);

    filterLyt = (RelativeLayout) view.findViewById(R.id.filterLyt);
    prePriceLyt = (RelativeLayout) view.findViewById(R.id.prepriceLyt);
    reultLyt = (RelativeLayout) view.findViewById(R.id.resultLyt);

    rejimType = (Spinner) view.findViewById(R.id.rejimType);
    dasimaType = (Spinner) view.findViewById(R.id.cmb_dasimaType);
    yukType = (Spinner) view.findViewById(R.id.cmb_yukType);
    rubType = (Spinner) view.findViewById(R.id.cmb_rubType);
    vagonType = (Spinner) view.findViewById(R.id.cmb_vgType);
    ownerType = (Spinner) view.findViewById(R.id.cmb_ownerType);
    originType = (Spinner) view.findViewById(R.id.cmb_originType);
    alSatType = (Spinner) view.findViewById(R.id.cmb_selBuyType);
    searchType = (Spinner) view.findViewById(R.id.cmb_searchType);

    qnqSearchImg = (ImageView) view.findViewById(R.id.qnqSearchImg);
    m1SearchImg = (ImageView) view.findViewById(R.id.m1SearchImg);
    m2SearchImg = (ImageView) view.findViewById(R.id.m2SearchImg);

    yearCheck = (CheckBox) view.findViewById(R.id.yearCheck);
    drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerLyt);
    resultList = (ListView) view.findViewById(R.id.resultList);
    resultPrg = (ProgressBar) view.findViewById(R.id.resultPrg);
    tonnajTxt = (TextView) view.findViewById(R.id.tonnajTxt);

    resultLyt = (RelativeLayout) view.findViewById(R.id.resultLyt);


    okButton = (Button) view.findViewById(R.id.btn_ok);

    qnqDbHelper = new QnqDbHelper(getContext());
    sqLiteDatabase = qnqDbHelper.getReadableDatabase();
    sqLiteDatabase2 = qnqDbHelper.getWritableDatabase();

    qnqDbHelper.createNewTable(sqLiteDatabase2);

    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

    setupParent(drawerLayout);

    axtaris.add("Sifariş");
    axtaris.add("Fakt");

    navi_menu3.intellektM1="";
    navi_menu3.intellektM2 = "";
    navi_menu3.intellektQnq = "";


    qnqEdt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
       navi_menu3.intellektQnq = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    m1Edt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        navi_menu3.intellektM1 = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    m2Edt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        navi_menu3.intellektM2 = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, axtaris){

      @Override
      public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);

        if(position == searchType.getSelectedItemPosition())
        {
          view.setBackgroundColor(Color.parseColor("#3d4c68"));
        }
        return view;
      }
    };
    searchAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
    searchType.setAdapter(searchAdapter);
    searchType.setSelection(1);

    mensubiyyet.add(0, "Hamısı");

    rejimType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
       checkRejim(i);

      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });


    dasimaType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sDasimaNovu = hashMap.get(dasimaType.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    yukType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sYukNovu = hashMap.get(yukType.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    rubType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i==0)
        {
          sRub = "0";
        }
        else

        {
          sRub = hashMap.get(rubType.getSelectedItem().toString());
        }
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    vagonType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sDasimaVasitesi = hashMap.get(vagonType.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    ownerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sMensub = hashMap.get(ownerType.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    originType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sMense = hashMap.get(originType.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    alSatType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sQiymet = hashMap.get(alSatType.getSelectedItem().toString());
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    searchType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sAxtaris = String.valueOf(i);
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    new GetQnqsFromDb().execute();

    Cursor cursor3  = sqLiteDatabase2.query("AzPointsNew", new String[]{"point_name"}, null, null, null, null, null);

    if(cursor3.getCount() == 0) {
      new GetStations().execute();
    }

    onBackPressed(qnqEdt);
    onBackPressed(m1Edt);
    onBackPressed(m2Edt);

    okButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        drawerLayout.closeDrawer(Gravity.RIGHT);


        if((m1Edt.getText().toString().length()<6 && !m1Edt.getText().toString().equals(""))
          || (m2Edt.getText().toString().length()<6 && !m2Edt.getText().toString().equals("")))
        {
          checkPoint = 1;
        }

        if(qnqEdt.getText().toString().equals(""))
        {
          Toast.makeText(getContext(), "QNQ-ni daxil edin", Toast.LENGTH_SHORT).show();
        }
        if(yearCheck.isChecked())
        {
          mYear = "1";
        }
        if(!yearCheck.isChecked()){
          mYear = "0";
        }

        if(qnqEdt.getText().toString().length()<=8)
        {
          qnq = qnqEdt.getText().toString();
        }
        if(qnqEdt.getText().toString().length()>8)
        {
          qnq = qnqEdt.getText().toString().substring(0,8);
        }

        if(m1Edt.getText().toString().length()>=6)
        {
          m1 = m1Edt.getText().toString().substring(0,6);
        }
        if(m2Edt.getText().toString().length()>=6)
        {
          m2 = m2Edt.getText().toString().substring(0,6);
        }
        if(m1Edt.getText().toString().equals("")){
          m1="";
        }
        if(m2Edt.getText().toString().equals("")){
          m2="";
        }

        checkRejim(rejimType.getSelectedItemPosition());

        minWeight = weight1Edt.getText().toString();
        maxWeight = weight2Edt.getText().toString();

        if(!qnqEdt.getText().toString().equals("")){

          if(checkPoint==1)
          {
            Toast.makeText(getContext(), "Marşrutu düzgün daxil edin", Toast.LENGTH_SHORT).show();
            checkPoint = 0;
          }
          else
          {

            if(toastMessage == "")
            {
              new GetResult().execute();

              Log.i("fffptype", sQiymet);
              Log.i("fffqnqcode", qnq);
              Log.i("fffltype", sYukNovu);
              Log.i("fffvgtype", sDasimaNovu);
              Log.i("fffactype", sRejim);
              Log.i("fffbp", m1);
              Log.i("fffep", m2);
              Log.i("fffyear", mYear);
              Log.i("fffqt", sRub);
              Log.i("ffftype", "result");
              Log.i("fffrtype", sDasimaVasitesi);
              Log.i("fffowner", sMensub);
              Log.i("ffforigin", sMense);
              Log.i("fffsearchType", sAxtaris);
              Log.i("fffminWeight", minWeight);
              Log.i("fffmaxWeight", maxWeight);
              Log.i("fffuserId", userId_);

              if(sDasimaNovu.equals("1"))
              {
                tonnajTxt.setText("Cəmi Tonnaj");
              }
              else
              {
                tonnajTxt.setText("Kont. Sayı");

              }

            }

            else
            {
              Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
          }
        }

      }
    });


    prePriceLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if((m1Edt.getText().toString().length()<6 && !m1Edt.getText().toString().equals(""))
          || (m2Edt.getText().toString().length()<6 && !m2Edt.getText().toString().equals("")))
        {
          checkPoint = 1;
        }

        if(qnqEdt.getText().toString().equals(""))
        {
          Toast.makeText(getContext(), "QNQ-ni daxil edin", Toast.LENGTH_SHORT).show();
        }

        if(yearCheck.isChecked())
        {
          mYear = "1";
        }
        if(!yearCheck.isChecked()){
          mYear = "0";
        }

        if(qnqEdt.getText().toString().length()<=8)
        {
          qnq = qnqEdt.getText().toString();
        }
        if(qnqEdt.getText().toString().length()>8)
        {
          qnq = qnqEdt.getText().toString().substring(0,8);
        }

        if(m1Edt.getText().toString().length()>=6)
        {
          m1 = m1Edt.getText().toString().substring(0,6);
        }
        if(m2Edt.getText().toString().length()>=6)
        {
          m2 = m2Edt.getText().toString().substring(0,6);
        }
        if(m1Edt.getText().toString().equals("")){
          m1="";
        }
        if(m2Edt.getText().toString().equals("")){
          m2="";
        }

        checkRejim(rejimType.getSelectedItemPosition());

        minWeight = weight1Edt.getText().toString();
        maxWeight = weight2Edt.getText().toString();

        if(!qnqEdt.getText().toString().equals("")){

          if(checkPoint==1)
          {
            Toast.makeText(getContext(), "Marşrutu düzgün daxil edin", Toast.LENGTH_SHORT).show();
            checkPoint = 0;
          }
          else
          {

            if(toastMessage == "")
            {
              new GetPreprice().execute();

              Log.i("fffptype", sQiymet);
              Log.i("fffqnqcode", qnq);
              Log.i("fffltype", sYukNovu);
              Log.i("fffvgtype", sDasimaNovu);
              Log.i("fffactype", sRejim);
              Log.i("fffbp", m1);
              Log.i("fffep", m2);
              Log.i("fffyear", mYear);
              Log.i("fffqt", sRub);
              Log.i("ffftype", "result");
              Log.i("fffrtype", sDasimaVasitesi);
              Log.i("fffowner", sMensub);
              Log.i("ffforigin", sMense);
              Log.i("fffsearchType", sAxtaris);
              Log.i("fffminWeight", minWeight);
              Log.i("fffmaxWeight", maxWeight);
              Log.i("fffuserId", userId_);

            }

            else
            {
              Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
          }
        }
      }
    });

    reultLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if((m1Edt.getText().toString().length()<6 && !m1Edt.getText().toString().equals(""))
          || (m2Edt.getText().toString().length()<6 && !m2Edt.getText().toString().equals("")))
        {
          checkPoint = 1;
        }

        if(qnqEdt.getText().toString().equals(""))
        {
          Toast.makeText(getContext(), "QNQ-ni daxil edin", Toast.LENGTH_SHORT).show();
        }


        if(yearCheck.isChecked())
        {
          mYear = "1";
        }
        if(!yearCheck.isChecked()){
          mYear = "0";
        }

        if(qnqEdt.getText().toString().length()<=8)
        {
          qnq = qnqEdt.getText().toString();
        }
        if(qnqEdt.getText().toString().length()>8)
        {
          qnq = qnqEdt.getText().toString().substring(0,8);
        }

        if(m1Edt.getText().toString().length()>=6)
        {
          m1 = m1Edt.getText().toString().substring(0,6);
        }
        if(m2Edt.getText().toString().length()>=6)
        {
          m2 = m2Edt.getText().toString().substring(0,6);
        }
        if(m1Edt.getText().toString().equals("")){
          m1="";
        }
        if(m2Edt.getText().toString().equals("")){
          m2="";
        }

        checkRejim(rejimType.getSelectedItemPosition());

        minWeight = weight1Edt.getText().toString();
        maxWeight = weight2Edt.getText().toString();

        if(!qnqEdt.getText().toString().equals("")){

          if(checkPoint==1)
          {
            Toast.makeText(getContext(), "Marşrutu düzgün daxil edin", Toast.LENGTH_SHORT).show();
            checkPoint = 0;
          }
          else
          {

            if(toastMessage == "")
            {
              new GetResult().execute();

              Log.i("fffptype", sQiymet);
              Log.i("fffqnqcode", qnq);
              Log.i("fffltype", sYukNovu);
              Log.i("fffvgtype", sDasimaNovu);
              Log.i("fffactype", sRejim);
              Log.i("fffbp", m1);
              Log.i("fffep", m2);
              Log.i("fffyear", mYear);
              Log.i("fffqt", sRub);
              Log.i("ffftype", "result");
              Log.i("fffrtype", sDasimaVasitesi);
              Log.i("fffowner", sMensub);
              Log.i("ffforigin", sMense);
              Log.i("fffsearchType", sAxtaris);
              Log.i("fffminWeight", minWeight);
              Log.i("fffmaxWeight", maxWeight);
              Log.i("fffuserId", userId_);

              if(sDasimaNovu.equals("1"))
              {
                tonnajTxt.setText("Cəmi Tonnaj");
              }
              else
              {
                tonnajTxt.setText("Kont. Sayı");

              }

            }

            else
            {
              Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
          }
        }
      }
    });


    new GetParamters().execute();


    filterLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);
      }
    });


   // sqLiteDatabase.execSQL("DELETE FROM AllPoint");


    qnqSearchImg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       // searchDialog3(qnqEdt, qnqCodes, "qnq");
        qnqSearchDialog(qnqEdt);
      }
    });

    //getStations = new GetStations(m1Edt,pointCodes);

    m1SearchImg.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
     /*   if (rejimType.getSelectedItemPosition()==0)
        {
          searchDialog(m1Edt, pointCodes, "point");
        }
        else
        {
          if(rejimType.getSelectedItemPosition()==1 || rejimType.getSelectedItemPosition() == 4)
          {
            searchDialog(m1Edt, serhedPointCodes, "point");
          }
          else
          {
            searchDialog(m1Edt, daxiliPointCodes, "point");
          }
        } */


        pointSearchDialog("1");

      }
    });

    m2SearchImg.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
       /* if (rejimType.getSelectedItemPosition()==0)
        {
          searchDialog(m2Edt, pointCodes, "point");
        }
        else
        {
          if(rejimType.getSelectedItemPosition()==1 || rejimType.getSelectedItemPosition() == 3)
          {
            searchDialog(m2Edt, daxiliPointCodes, "point");
          }
          else
          {
            searchDialog(m2Edt, serhedPointCodes, "point");
          }
        }  */


       pointSearchDialog("2");
      }
    });

    return view;
  }


    class GetStations extends AsyncTask<String,String,JSONObject>{

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      Log.i("melumat2", "Stansiyalari Servisden yukleyir");

    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));


      JSONObject json = jParser.getJSONFromUrl("https://ady.express/Plan.asmx/GetStations", params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      new AddPointsToDb(jsonObject).execute();


      try {
        if (jsonObject != null) {
          JSONArray dataArray = jsonObject.getJSONArray("data");

          for (int i = 0; i < dataArray.length(); i++) {
            JSONObject jsonObject1 = dataArray.getJSONObject(i);
            String code = jsonObject1.getString("PNT_CODE");
            String name = jsonObject1.getString("PNT_NAME");

            //onlyPointCodes.add(code);
            pointCodes.add(code + "-" + name);
            onlyPointCodes.add(code);
            if(code.equals("554503") || code.equals("547603") || code.equals("549204") || code.equals("547209")
              || code.equals("558701") || code.equals("547800") || code.equals("550108") || code.equals("547904")
              || code.equals("548201") || code.equals("548305") || code.equals("547508") || code.equals("548803") || code.equals("553002"))
            {
              serhedPointCodes.add(code + "-" + name);
              onlySerhedPointCodes.add(code);
            }
            if(!code.equals("554503") && !code.equals("547603") && !code.equals("549204") && !code.equals("547209")
              && !code.equals("558701") && !code.equals("547800") && !code.equals("550108") && !code.equals("547904")
              && !code.equals("548201") && !code.equals("548305") && !code.equals("547508") && !code.equals("548803") && !code.equals("553002"))
            {
              daxiliPointCodes.add(code + "-" + name);
              onlyDaxiliPointCodes.add(code);
            }

          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }

    }

  }


  class AddPointsToDb extends  AsyncTask<String,String,JSONObject>{

     JSONObject jsonObject;

     public AddPointsToDb(JSONObject jsonObject) {
      this.jsonObject = jsonObject;
    }


    @Override
      protected JSONObject doInBackground(String... strings) {
        QnqDbHelper dbHelper = new QnqDbHelper(getContext());
        SQLiteDatabase db1 = dbHelper.getWritableDatabase();
        SQLiteDatabase db2 = dbHelper.getReadableDatabase();

        try {
          if (jsonObject != null) {
            JSONArray dataArray = jsonObject.getJSONArray("data");
            // UpdateQnq updateQnq = new UpdateQnq();

            for (int i = 0; i < dataArray.length(); i++) {
              JSONObject jsonObject1 = dataArray.getJSONObject(i);
              String code = jsonObject1.getString("PNT_CODE");
              String name = jsonObject1.getString("PNT_NAME");

              if(String.valueOf(code + "-" + name).length()>=7)
              {dbHelper.addPointName(code + "-" + name, db1);}

            }
          }


        } catch (JSONException e) {
          e.printStackTrace();
        }

        return null;
      }

    }


  class GetParamters extends AsyncTask<String, String, JSONObject>{

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("filter", "0"));
      params.add(new BasicNameValuePair("type", "combo"));
      params.add(new BasicNameValuePair("user", user_));
      params.add(new BasicNameValuePair("pass", pass_));

      JSONObject json = jParser.getJSONFromUrl(urlSearch, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      if(jsonObject!=null)
      {
        try {
          JSONArray jsonArray = jsonObject.getJSONArray("data");
          for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String scType = jsonObject1.getString("SC_TYPE");
            String name = jsonObject1.getString("NAME");
            String code = jsonObject1.getString("CODE");

            if(scType.equals("ORD_TYPEACT")){

              rejimler.add(name);
              hashMap.put(name, code);
            }
            if(scType.equals("ORD_TRANSTYPE")){

              dasimaNovu.add(name);
              hashMap.put(name, code);
            }
            if((scType.equals("EXP_TYPE"))){
              yukNovu.add(name);
              hashMap.put(name, code);
            }
            if(scType.equals("QUARTER")){
              if(name.equals("Hamısı"))
              {
                name = "Seç";
              }
              rubler.add(name);
              hashMap.put(name, code);
            }

            if(scType.equals("RT")){
              dasimaVasitesi.add(name);
              hashMap.put(name, code);
            }
            if(scType.equals("VGN_OWNERTYPE"))
            {

                mensubiyyet.add(name);
                hashMap.put(name, code);

            }
            if(scType.equals("CT")){
              menseyi.add(name);
              hashMap.put(name, code);
            }
            if(scType.equals("CT_TYPE")){
              if(!name.equals("İcarə") && !name.equals("Yuyulma")) {
                qiymet.add(name);
                hashMap.put(name, code);
              }
            }

          }
           if(getActivity()!=null) {
             final ArrayAdapter<String> rejimAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, rejimler) {
               @Override
               public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);

                 if (position == rejimType.getSelectedItemPosition()) {
                   view.setBackgroundColor(Color.parseColor("#3d4c68"));
                 } else {
                   view.setBackgroundColor(Color.parseColor("#1d2636"));

                 }
                 return view;
               }
             };
             rejimAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
             rejimType.setAdapter(rejimAdapter);

             final ArrayAdapter<String> dasimaAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, dasimaNovu) {
               @Override
               public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);

                 if (position == dasimaType.getSelectedItemPosition()) {
                   view.setBackgroundColor(Color.parseColor("#3d4c68"));
                 } else {
                   view.setBackgroundColor(Color.parseColor("#1d2636"));

                 }
                 return view;
               }
             };
             dasimaAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
             dasimaType.setAdapter(dasimaAdapter);

             final ArrayAdapter<String> yukAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, yukNovu) {
               @Override
               public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);

                 if (position == yukType.getSelectedItemPosition()) {
                   view.setBackgroundColor(Color.parseColor("#3d4c68"));
                 } else {
                   view.setBackgroundColor(Color.parseColor("#1d2636"));

                 }
                 return view;
               }
             };
             yukAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
             yukType.setAdapter(yukAdapter);


             final ArrayAdapter<String> rubAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, rubler) {
               @Override
               public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);

                 if (position == rubType.getSelectedItemPosition()) {
                   view.setBackgroundColor(Color.parseColor("#3d4c68"));
                 } else {
                   view.setBackgroundColor(Color.parseColor("#1d2636"));

                 }
                 return view;
               }
             };
             rubAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
             rubType.setAdapter(rubAdapter);

             final ArrayAdapter<String> dasimaVasAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, dasimaVasitesi) {
               @Override
               public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);

                 if (position == vagonType.getSelectedItemPosition()) {
                   view.setBackgroundColor(Color.parseColor("#3d4c68"));
                 } else {
                   view.setBackgroundColor(Color.parseColor("#1d2636"));

                 }
                 return view;
               }
             };
             dasimaVasAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
             vagonType.setAdapter(dasimaVasAdapter);

             final ArrayAdapter<String> mensAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, mensubiyyet) {
               @Override
               public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);

                 if (position == ownerType.getSelectedItemPosition()) {
                   view.setBackgroundColor(Color.parseColor("#3d4c68"));
                 } else {
                   view.setBackgroundColor(Color.parseColor("#1d2636"));

                 }
                 return view;
               }
             };
             mensAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
             ownerType.setAdapter(mensAdapter);

             final ArrayAdapter<String> menseAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, menseyi) {
               @Override
               public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);

                 if (position == originType.getSelectedItemPosition()) {
                   view.setBackgroundColor(Color.parseColor("#3d4c68"));
                 } else {
                   view.setBackgroundColor(Color.parseColor("#1d2636"));

                 }
                 return view;
               }
             };
             menseAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
             originType.setAdapter(menseAdapter);


             final ArrayAdapter<String> qiymetAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, qiymet) {
               @Override
               public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                 View view = super.getDropDownView(position, convertView, parent);

                 if (position == alSatType.getSelectedItemPosition()) {
                   view.setBackgroundColor(Color.parseColor("#3d4c68"));
                 } else {
                   view.setBackgroundColor(Color.parseColor("#1d2636"));

                 }
                 return view;
               }
             };
             qiymetAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
             alSatType.setAdapter(qiymetAdapter);
             alSatType.setSelection(1);
           }
         // Log.i("hashmap", hashMap.get("Hamısı"));

        } catch (JSONException e) {
          e.printStackTrace();
        }
      }

    }
  }

  class  GetResult extends AsyncTask<String, String, JSONObject>{

    @Override
    protected void onPreExecute() {
      super.onPreExecute();

      resultPrg.setVisibility(View.VISIBLE);
      resultList.setVisibility(View.INVISIBLE);

      if(sAxtaris.equals("1"))
      {
        sYukNovu = String.valueOf(Integer.parseInt(sYukNovu)-1);
      }
      Log.i("SyukEvvel", sYukNovu);

    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("ptype", sQiymet));
      params.add(new BasicNameValuePair("qnqcode", qnq));
      params.add(new BasicNameValuePair("ltype", sYukNovu));
      params.add(new BasicNameValuePair("vgtype", sDasimaNovu));
      params.add(new BasicNameValuePair("actype", sRejim));
      params.add(new BasicNameValuePair("bp", m1));
      params.add(new BasicNameValuePair("ep", m2));
      params.add(new BasicNameValuePair("year", mYear));
      params.add(new BasicNameValuePair("qt", sRub));
      params.add(new BasicNameValuePair("type", "result"));
      params.add(new BasicNameValuePair("rtype", sDasimaVasitesi));
      params.add(new BasicNameValuePair("owner", sMensub));
      params.add(new BasicNameValuePair("origin", sMense));
      params.add(new BasicNameValuePair("searchType", sAxtaris));
      params.add(new BasicNameValuePair("minWeight", minWeight));
      params.add(new BasicNameValuePair("maxWeight", maxWeight));
      params.add(new BasicNameValuePair("userId", userId_));

      JSONObject json = jParser.getJSONFromUrl(urlResult, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      browselist = new ArrayList<HashMap<String, String>>();

      String jsonQiymet = null;
      String jsonAlis=null;
      String jsonSatis=null;

      if(sAxtaris.equals("1"))
      {
        if(sQiymet.equals("1"))
        {
          jsonQiymet = "FEXP_PEXPENSE";
          jsonAlis = "FEXP_PAMOUNT";
          jsonSatis = "FEXP_AMOUNT";
        }
        else
        {
          jsonQiymet = "FEXP_EXPENSE";
          jsonAlis = "FEXP_PAMOUNT";
          jsonSatis = "FEXP_AMOUNT";
        }

      }

      if(sAxtaris.equals("0")){
        if(sQiymet.equals("1"))
        {
          jsonQiymet = "EXP_PEXPENSE";
          jsonAlis = "EXP_PAMOUNT";
          jsonSatis = "EXP_AMOUNT";
        }
        else
        {
          jsonQiymet = "EXP_EXPENSE";
          jsonAlis = "EXP_PAMOUNT";
          jsonSatis = "EXP_AMOUNT";
        }
      }

        try {

          if(jsonObject!=null) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
              JSONObject jsonObject1 = jsonArray.getJSONObject(i);
              String price = jsonObject1.getString(jsonQiymet);
              String sumBuy = jsonObject1.getString(jsonAlis);
              String sumSell = jsonObject1.getString(jsonSatis);
              String tonnaj = jsonObject1.getString("ORD_VGALLTONNAJ");



              hashmap2 = new HashMap<>();

              hashmap2.put(jsonQiymet, price);
              hashmap2.put(jsonAlis, sumBuy);
              hashmap2.put(jsonSatis, sumSell);
              hashmap2.put("tonnaj", tonnaj);

              browselist.add(hashmap2);

              IntellektualAdapter adapter_c = new IntellektualAdapter(getContext()
                , browselist, R.layout.result_list, new String[]{jsonQiymet,
                jsonAlis, jsonSatis, "tonnaj"}, new int[]{R.id.sNo, R.id.product,
                R.id.category, R.id.price});

              resultList.setAdapter(adapter_c);

            }
          }
              else
            {
              Toast.makeText(getContext(), "Seçilmiş filtre uyğun heç bir melumat tapılmadı", Toast.LENGTH_SHORT).show();
              resultList.setAdapter(null);
            }

        } catch (JSONException e) {
          e.printStackTrace();
        }

      resultList.setVisibility(View.VISIBLE);
      resultPrg.setVisibility(View.INVISIBLE);
      sYukNovu = hashMap.get(yukType.getSelectedItem().toString());
      Log.i("SyukSonra", sYukNovu);

    }
  }

   class GetQnqsFromDb extends AsyncTask<String, String, String>
   {

     @Override
     protected void onPreExecute() {
       super.onPreExecute();
       Log.i("melumat2", "QNQ-leri DB_dan yukleyir");
     }

     @Override
     protected String doInBackground(String... strings) {
   /*    Cursor cursor  = sqLiteDatabase2.query("AllQnqs", new String[]{"qnq_name"}, null, null, null, null, null);
       if(cursor.moveToFirst())
       {
         do{
           qnqCodes.add(cursor.getString(0));
           onlyCodes.add(cursor.getString(0).substring(0, 8));


         }while(cursor.moveToNext());

       }

       cursor.close(); */

       cursor2  = sqLiteDatabase2.query("AzPointsNew", new String[]{"point_name"}, null, null, null, null, null);

       // sqLiteDatabase.execSQL("DELETE FROM AllPoint");


       if(cursor2.getCount()!=0) {
         if(cursor2.moveToFirst())
         {
           do{
             pointCodes.add(cursor2.getString(0));
             onlyPointCodes.add(cursor2.getString(0).substring(0,6));
             if(cursor2.getString(0).contains("554503") || cursor2.getString(0).contains("547603") || cursor2.getString(0).contains("549204") ||
               cursor2.getString(0).contains("548803") || cursor2.getString(0).contains("550108") || cursor2.getString(0).contains("547209") ||
               cursor2.getString(0).contains("547508") || cursor2.getString(0).contains("547904") || cursor2.getString(0).contains("558701") ||
               cursor2.getString(0).contains("548305") || cursor2.getString(0).contains("548201") || cursor2.getString(0).contains("547800") ||
               cursor2.getString(0).contains("553002")){
               serhedPointCodes.add(cursor2.getString(0));
               onlySerhedPointCodes.add(cursor2.getString(0).substring(0,6));}
               else
               {
                 daxiliPointCodes.add(cursor2.getString(0));
                 onlyDaxiliPointCodes.add(cursor2.getString(0).substring(0,6));
               }



           }while(cursor2.moveToNext());

         }
       }

       cursor2.close();
       return null;
     }

     @Override
     protected void onPostExecute(String s) {
       super.onPostExecute(s);

       Log.i("melumat2", "yukledi");
     }
   }


   class GetPreprice extends  AsyncTask<String, String, JSONObject>{

     ListView listView;
     ProgressBar progress;
     Dialog dialog;

     @Override
     protected void onPreExecute() {
       super.onPreExecute();


       if(sAxtaris.equals("1"))
       {
         sYukNovu = String.valueOf(Integer.parseInt(sYukNovu)-1);
       }


       DisplayMetrics metrics = getResources().getDisplayMetrics();
       int width = metrics.widthPixels;
       int height = metrics.heightPixels;


       dialog =  new Dialog(getActivity());
       View child = getActivity().getLayoutInflater().inflate(R.layout.daily_listview_popup, null);
       dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
       dialog.setContentView(child);
       dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
       dialog.getWindow().setLayout(width, height);
       dialog.show();

       TextView dateTxt = (TextView) dialog.findViewById(R.id.dateTxt);
       TextView infText = (TextView) dialog.findViewById(R.id.informationTxt);
       TextView jjj = (TextView) dialog.findViewById(R.id.jjjj);
       ImageView imageViev = (ImageView) dialog.findViewById(R.id.closeImg);



       listView = (ListView) dialog.findViewById(R.id.popUpListview);
       progress = (ProgressBar) dialog.findViewById(R.id.testBar);

       listView.setDividerHeight(2);

       dateTxt.setVisibility(View.GONE);
       infText.setVisibility(View.GONE);
       jjj.setVisibility(View.GONE);

       listView.setVisibility(View.INVISIBLE);
       progress.setVisibility(View.VISIBLE);

       imageViev.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
           dialog.dismiss();
         }
       });

     }

     @Override
     protected JSONObject doInBackground(String... strings) {
       JSONParser jParser = new JSONParser();

       List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
       params.add(new BasicNameValuePair("ptype", sQiymet));
       params.add(new BasicNameValuePair("qnqcode", qnq));
       params.add(new BasicNameValuePair("ltype", sYukNovu));
       params.add(new BasicNameValuePair("vgtype", sDasimaNovu));
       params.add(new BasicNameValuePair("actype", sRejim));
       params.add(new BasicNameValuePair("bp", m1));
       params.add(new BasicNameValuePair("ep", m2));
       params.add(new BasicNameValuePair("year", mYear));
       params.add(new BasicNameValuePair("qt", sRub));
       params.add(new BasicNameValuePair("type", "preprice"));
       params.add(new BasicNameValuePair("rtype", sDasimaVasitesi));
       params.add(new BasicNameValuePair("owner", sMensub));
       params.add(new BasicNameValuePair("origin", sMense));
       params.add(new BasicNameValuePair("searchType", sAxtaris));
       params.add(new BasicNameValuePair("minWeight", minWeight));
       params.add(new BasicNameValuePair("maxWeight", maxWeight));
       params.add(new BasicNameValuePair("userId", userId_));

       JSONObject json = jParser.getJSONFromUrl(urlResult, params);
       return json;
     }

     @Override
     protected void onPostExecute(JSONObject jsonObject) {
       super.onPostExecute(jsonObject);

       ArrayList<HashMap<String, String>> browselist = new ArrayList<HashMap<String, String>>();

       String jsonQiymet = null;
       String jsonAlis=null;
       String jsonSatis=null;
       int type = 0;

       if(sAxtaris.equals("1"))
       {
         if(sQiymet.equals("1"))
         {
           jsonQiymet = "FEXP_PEXPENSE";
           jsonAlis = "FEXP_PAMOUNT";
           jsonSatis = "FEXP_AMOUNT";
         }
         else
         {
           jsonQiymet = "FEXP_EXPENSE";
           jsonAlis = "FEXP_PAMOUNT";
           jsonSatis = "FEXP_AMOUNT";
         }

       }

       if(sAxtaris.equals("0")){
         if(sQiymet.equals("1"))
         {
           jsonQiymet = "EXP_PEXPENSE";
           jsonAlis = "EXP_PAMOUNT";
           jsonSatis = "EXP_AMOUNT";
         }
         else
         {
           jsonQiymet = "EXP_EXPENSE";
           jsonAlis = "EXP_PAMOUNT";
           jsonSatis = "EXP_AMOUNT";
         }
       }
         try {

           if(jsonObject!=null) {
             JSONArray jsonArray = jsonObject.getJSONArray("data");

             for (int i = 0; i < jsonArray.length(); i++) {
               JSONObject jsonObject1 = jsonArray.getJSONObject(i);
               String price = jsonObject1.getString(jsonQiymet);
               String sumBuy = jsonObject1.getString(jsonAlis);
               String sumSell = jsonObject1.getString(jsonSatis);
               String tonnaj = jsonObject1.getString("ORD_VGALLTONNAJ");
               String musteri = jsonObject1.getString("CLC_ALLNAME");

               HashMap<String, String> hashmap = new HashMap<>();

               hashmap.put(jsonQiymet, price);
               hashmap.put(jsonAlis, sumBuy);
               hashmap.put(jsonSatis, sumSell);
               hashmap.put("tonnaj", tonnaj);
               hashmap.put("musteri", musteri);

               browselist.add(hashmap);

               if(sDasimaNovu.equals("1"))
               {
                 type = 1;
               }
               else
               {
                 type =2;
               }

               IntellektualAdapter2 adapter_c = new IntellektualAdapter2(getContext()
                 , browselist, R.layout.preprice_list_item, new String[]{jsonQiymet,
                 jsonAlis, jsonSatis, "tonnaj", "musteri"}, new int[]{R.id.sNo, R.id.product,
                 R.id.category, R.id.price, R.id.musteriNameTxt}, type);


               listView.setAdapter(adapter_c);
             }
           }

           else
           {
             dialog.dismiss();
             Toast.makeText(getContext(),"Seçilmiş filtre uyğun heç bir məlumat tapılmadı", Toast.LENGTH_SHORT).show();
           }

         } catch (JSONException e) {
           e.printStackTrace();
         }

       listView.setVisibility(View.VISIBLE);
       progress.setVisibility(View.INVISIBLE);
       sYukNovu = hashMap.get(yukType.getSelectedItem().toString());
     }
   }

  protected void setupParent(View view) {
    if(!(view instanceof EditText)) {
      view.setOnTouchListener(new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
          hideSoftKeyboard();
          return false;
        }
      });
    }
    if (view instanceof ViewGroup) {
      for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
        View innerView = ((ViewGroup) view).getChildAt(i);
        setupParent(innerView);
      }
    }
  }


  public  void  checkRejim(int i)
  {
    if(i==0)
    {
      sRejim = "5";
      if(!m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6)
      {
        if(!onlyPointCodes.contains(m1Edt.getText().toString().substring(0,6)))
        {
          toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
          Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }
        else
        {
          toastMessage = "";
        }
      }

      if(m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("") && m2Edt.getText().toString().length()>=6 ){
        if(!onlyPointCodes.contains(m2Edt.getText().toString().substring(0,6)))
        {
          toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
          Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }
        else {
          toastMessage = "";
        }
      }

      if(!m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6  && m2Edt.getText().toString().length()>=6){

        if(!onlyPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlyPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
          toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
          Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }
        if(onlyPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlyPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
          toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
          Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }
        if(!onlyPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlyPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
          toastMessage = "Marşrutların heç biri seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6) + " və " +  m2Edt.getText().toString().substring(0,6);
          Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }
        if(onlyPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlyPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
          toastMessage = "";
        }

      }
      if(m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals(""))
      {
        toastMessage ="";
      }

    }
    else {

      if(i==1)
      {
        if(!m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6)
        {
          if(!onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)))
          {
            toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          else
          {
            toastMessage = "";
          }
        }

        if(m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("") && m2Edt.getText().toString().length()>=6 ){
          if(!onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6)))
          {
            toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          else {
            toastMessage = "";
          }
        }

        if(!m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6  && m2Edt.getText().toString().length()>=6){

          if(!onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(!onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrutların heç biri seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6) + " və " +  m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "";
          }

        }

        if(m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals(""))
        {
          toastMessage ="";
        }

      }
      if(i==2)
      {
        if(!m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6)
        {
          if(!onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)))
          {
            toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
        }

        if(m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("")  && m2Edt.getText().toString().length()>=6){
          if(!onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6)))
          {
            toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
        }

        if(!m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6  && m2Edt.getText().toString().length()>=6){

          if(!onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(!onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrutların heç biri seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6) + " və " +  m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "";
          }
        }
        if(m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals(""))
        {
          toastMessage ="";
        }
      }

      if(i==3)
      {
        if(!m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6)
        {
          if(!onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)))
          {
            toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
        }

        if(m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("")  && m2Edt.getText().toString().length()>=6){
          if(!onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6)))
          {
            toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
        }

        if(!m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6  && m2Edt.getText().toString().length()>=6){

          if(!onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(!onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrutların heç biri seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6) + " və " +  m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(onlyDaxiliPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlyDaxiliPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "";
          }

        }
        if(m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals(""))
        {
          toastMessage ="";
        }
      }

      if(i==4){

        if(!m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals("")  && m1Edt.getText().toString().length()>=6)
        {
          if(!onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)))
          {
            toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
        }

        if(m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("") && m2Edt.getText().toString().length()>=6){
          if(!onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6)))
          {
            toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
        }

        if(!m1Edt.getText().toString().equals("") && !m2Edt.getText().toString().equals("") && m1Edt.getText().toString().length()>=6  && m2Edt.getText().toString().length()>=6){

          if(!onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrut 1 seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrut 2 seçilmiş rejimə uyğun deyil: " + m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(!onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && !onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "Marşrutların heç biri seçilmiş rejimə uyğun deyil: " + m1Edt.getText().toString().substring(0,6) + " və " +  m2Edt.getText().toString().substring(0,6);
            Toast.makeText(getContext(), toastMessage, Toast.LENGTH_SHORT).show();
          }
          if(onlySerhedPointCodes.contains(m1Edt.getText().toString().substring(0,6)) && onlySerhedPointCodes.contains(m2Edt.getText().toString().substring(0,6))){
            toastMessage = "";
          }
        }
        if(m1Edt.getText().toString().equals("") && m2Edt.getText().toString().equals(""))
        {
          toastMessage ="";
        }
      }

      sRejim = hashMap.get(rejimType.getSelectedItem().toString());
    }
  }


  public  void onBackPressed(EditText editText)
  {
    editText.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if(keyEvent.getAction() == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK){

          final Intent intent = new Intent(getActivity(), navi_menu3.class);
          intent.putExtra("sess_user", user_);
          intent.putExtra("sess_pass", pass_);
          intent.putExtra("sess_name", name_);
          intent.putExtra("sess_id", userId_);
          intent.putStringArrayListExtra("menular", Menular.menular);


          if(!qnqEdt.getText().toString().equals("") || !m1Edt.getText().toString().equals("") || !m2Edt.getText().toString().equals("")){

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Intellektual Axtarış-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
              .setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                  dialogInterface.dismiss();
                  startActivity(intent);
                  getActivity().finish();
                }
              }).setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
              }
            });

            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();

          }

          else
          {
            startActivity(intent);
            getActivity().finish();

          }
          return true;

        }

        return false;
      }
    });
  }


  private void hideSoftKeyboard() {
    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
    inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
  }


  @Override
  public void onPrepareOptionsMenu(Menu menu) {

    if(!hashmap2.isEmpty())
    {
      menu.findItem(R.id.action_send).setVisible(true);
    }

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

      emailEdt = (EditText) dialog.findViewById(R.id.emailEdt);
      gonderLyt = (RelativeLayout) dialog.findViewById(R.id.gonderBtn);
      closeImg = (ImageView) dialog.findViewById(R.id.closeImg);
      progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
      doneImd = (ImageView) dialog.findViewById(R.id.doneImg);
      sendingInformationTxt = (TextView) dialog.findViewById(R.id.sendingInformationTxt);
      progressLyt = (RelativeLayout) dialog.findViewById(R.id.progressLyt);

      pdfFolder = new File(Environment.getExternalStorageDirectory(), "ResultsPdf");


      closeImg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          dialog.dismiss();
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

      gonderLyt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          if(Build.VERSION.SDK_INT >=23)
          {
            if(getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
              sendIntellektualPdf(pdfFolder, progressLyt, progressBar,sendingInformationTxt,doneImd,emailEdt);

            }
            else
            {
              ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

            }
          }
          else
          {
            sendIntellektualPdf(pdfFolder, progressLyt, progressBar,sendingInformationTxt,doneImd,emailEdt);
          }


        }
      });

    return true;

    }

    return super.onOptionsItemSelected(item);
  }


  public void sendIntellektualPdf(File file, RelativeLayout relativeLayout, ProgressBar progressBar, TextView textView1, ImageView imageView, EditText editText){

    String jsonQiymet = null;
    String jsonAlis=null;
    String jsonSatis=null;

    if(sAxtaris.equals("1"))
    {
      if(sQiymet.equals("1"))
      {
        jsonQiymet = "FEXP_PEXPENSE";
        jsonAlis = "FEXP_PAMOUNT";
        jsonSatis = "FEXP_AMOUNT";
      }
      else
      {
        jsonQiymet = "FEXP_EXPENSE";
        jsonAlis = "FEXP_PAMOUNT";
        jsonSatis = "FEXP_AMOUNT";
      }

    }

    if(sAxtaris.equals("0")){
      if(sQiymet.equals("1"))
      {
        jsonQiymet = "EXP_PEXPENSE";
        jsonAlis = "EXP_PAMOUNT";
        jsonSatis = "EXP_AMOUNT";
      }
      else
      {
        jsonQiymet = "EXP_EXPENSE";
        jsonAlis = "EXP_PAMOUNT";
        jsonSatis = "EXP_AMOUNT";
      }
    }


    if (!file.exists()) {
      file.mkdir();
    }

    final File myFile;

    String timeStamp = "Qiymətlər";

    myFile = new File("/" + file + "/" + timeStamp + ".pdf");

    OutputStream outputStream = null;

    try {

      outputStream = new FileOutputStream(myFile);
      Document document = new Document();
      PdfWriter.getInstance(document, outputStream);

      BaseFont bf = BaseFont.createFont("assets/fonts/AZER_TM.ttf", "Cp1254", BaseFont.EMBEDDED);
      Font font1 = new Font(bf, 22);
      Font font2 = new Font(bf, 16);

      PdfPTable table = new PdfPTable(4);
      float[] columnWidths = new float[]{30f, 30f, 30f, 30f};
      table.setWidths(columnWidths);
      table.setSpacingBefore(20f);

      PdfPCell cell;
      PdfPTable inner;


      cell = new PdfPCell(new Phrase(changeString("1 Tonun qiyməti"), font2));
      cell.setBackgroundColor(BaseColor.GRAY);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(changeString("Cəmi alış"), font2));
      cell.setBackgroundColor(BaseColor.GRAY);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(changeString("Cəmi satış"), font2));
      cell.setBackgroundColor(BaseColor.GRAY);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);


      cell = new PdfPCell(new Phrase(changeString("Cəmi tonnaj"), font2));
      cell.setBackgroundColor(BaseColor.GRAY);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);

      for(int i=0; i<hashmap2.size(); i++)
      {
        cell = new PdfPCell(new Phrase(browselist.get(i).get(jsonQiymet), font2));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(browselist.get(i).get(jsonAlis), font2));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(browselist.get(i).get(jsonSatis), font2));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase(browselist.get(i).get("tonnaj"), font2));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);


      }

      PdfWriter.getInstance(document, new FileOutputStream(myFile));



      document.open();
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

    //SendMail sm = new SendMail(getActivity(), editText.getText().toString(), myFile, progressBar, textView1, imageView, "1");
   // sm.execute();


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

  public  String changeString2(String text)
  {

    String firstChar = text.substring(0 , 1).toUpperCase();

    String changedString = firstChar + text.substring(1, text.length()).toLowerCase();

    return  changedString;
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

  public void pointSearchDialog(final String whichStation) {

    final Dialog dialog = new Dialog(getActivity());
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.setContentView(R.layout.custom_qnq_popup);
    dialog.show();

    DisplayMetrics metrics = getResources().getDisplayMetrics();
    int width = metrics.widthPixels;

    final  EditText qnqEdt = (EditText) dialog.findViewById(R.id.edtTxt);
    final ListView myList = (ListView) dialog.findViewById(R.id.myList);
    final ProgressBar prgBar = (ProgressBar) dialog.findViewById(R.id.qnqPrg);
    final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGrp);
    final RadioButton baslangicBtn = (RadioButton) dialog.findViewById(R.id.baslangicBtn);
    final RadioButton terkibBtn = (RadioButton) dialog.findViewById(R.id.terkibBtn);
    final TextView notFoundTxt = (TextView) dialog.findViewById(R.id.notFoundTxt);
    final RelativeLayout mainLyt = (RelativeLayout) dialog.findViewById(R.id.mainLyt);

    if(whichStation.equals("1")) {
      qnqEdt.setText(this.m1Edt.getText().toString());
    }
    else
    {
      qnqEdt.setText(this.m2Edt.getText().toString());

    }

    myList.setVisibility(View.INVISIBLE);
    prgBar.setVisibility(View.VISIBLE);
    notFoundTxt.setVisibility(View.INVISIBLE);

    getPointData(buildUrl(whichStation, rejimType.getSelectedItemPosition(), "1", qnqEdt.getText().toString()), myList, prgBar, notFoundTxt);


    qnqEdt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

        if(baslangicBtn.isChecked())
        {

          qnqModelList.clear();

          if(requestQueue != null)
          {
            requestQueue.cancelAll("qnq");
          }

          getPointData(buildUrl(whichStation, rejimType.getSelectedItemPosition(), "1", qnqEdt.getText().toString()), myList, prgBar, notFoundTxt);

          prgBar.setVisibility(View.VISIBLE);
          myList.setVisibility(View.INVISIBLE);
          notFoundTxt.setVisibility(View.INVISIBLE);


        }

        else
        {

          qnqModelList.clear();

          if(requestQueue != null)
          {
            requestQueue.cancelAll("qnq");
          }

          getPointData(buildUrl(whichStation, rejimType.getSelectedItemPosition(), "2", qnqEdt.getText().toString()), myList, prgBar, notFoundTxt);

          prgBar.setVisibility(View.VISIBLE);
          myList.setVisibility(View.INVISIBLE);
          notFoundTxt.setVisibility(View.INVISIBLE);


        }


      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });


    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        if(checkedId == R.id.baslangicBtn)
        {
          qnqModelList.clear();


          if(requestQueue != null)
          {
            requestQueue.cancelAll("qnq");
          }

          getPointData(buildUrl(whichStation, rejimType.getSelectedItemPosition(), "1", qnqEdt.getText().toString()), myList, prgBar, notFoundTxt);

          prgBar.setVisibility(View.VISIBLE);
          notFoundTxt.setVisibility(View.INVISIBLE);
          myList.setVisibility(View.INVISIBLE);
        }
        else
        {
          qnqModelList.clear();

          if(requestQueue != null)
          {
            requestQueue.cancelAll("qnq");
          }

          getPointData(buildUrl(whichStation, rejimType.getSelectedItemPosition(), "2", qnqEdt.getText().toString()), myList, prgBar, notFoundTxt);

          prgBar.setVisibility(View.VISIBLE);
          myList.setVisibility(View.INVISIBLE);
          notFoundTxt.setVisibility(View.INVISIBLE);

        }

      }
    });


    myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // .setText(qnqModelList.get(position).getQnqCode());

        if (whichStation.equals("1")) {
          InSearch2.this.m1Edt.setText(qnqModelList.get(position).getQnqCode());
        }
        else
        {
          InSearch2.this.m2Edt.setText(qnqModelList.get(position).getQnqCode());

        }
        myList.smoothScrollBy(0,0);
        qnqModelList.clear();

        if(requestQueue != null)
        {
          requestQueue.cancelAll("qnq");
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            dialog.dismiss();
          }
        }, 300);
        mainLyt.animate().scaleY(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(250);

      }
    });



    Button closeBtn = (Button) dialog.findViewById(R.id.closeBtn);

    closeBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        myList.smoothScrollBy(0,0);
        qnqModelList.clear();

        if(requestQueue != null)
        {
          requestQueue.cancelAll("qnq");
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            dialog.dismiss();
          }
        }, 300);
        mainLyt.animate().scaleY(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(250);
      }
    });

    dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

  }


  public  String buildUrl(String whichStation, int rejimType, String searchType, String pointTxt)
  {

    final String url = "https://ady.express/Plan.asmx/Find_Stations";
    Uri uri = null;

    if(whichStation.equals("1"))
    {

      if (rejimType == 0) {
        uri = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("station", pointTxt)
                .appendQueryParameter("searchType", searchType).appendQueryParameter("pointType", "1").build();
      }
      if(rejimType == 1 || rejimType == 4)
      {
        uri = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("station", pointTxt)
                .appendQueryParameter("searchType", searchType).appendQueryParameter("pointType", "2").build();
      }
      if(rejimType == 2 || rejimType == 3)
      {
        uri = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("station", pointTxt)
                .appendQueryParameter("searchType", searchType).appendQueryParameter("pointType", "3").build();
      }
    }
    else
    {
      if(rejimType == 0) {
        uri = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("station", pointTxt)
                .appendQueryParameter("searchType", searchType).appendQueryParameter("pointType", "1").build();
      }
      if(rejimType== 2 || rejimType == 4)
      {
        uri = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("station", pointTxt)
                .appendQueryParameter("searchType", searchType).appendQueryParameter("pointType", "2").build();
      }
      if(rejimType == 1 || rejimType == 3)
      {
        uri = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("station", pointTxt)
                .appendQueryParameter("searchType", searchType).appendQueryParameter("pointType", "3").build();
      }
    }

    return  uri.toString();
  }

  public void qnqSearchDialog(final EditText editText) {

    final Dialog dialog = new Dialog(getActivity());
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.setContentView(R.layout.custom_qnq_popup);
    dialog.show();

    DisplayMetrics metrics = getResources().getDisplayMetrics();
    int width = metrics.widthPixels;

    final  EditText qnqEdt = (EditText) dialog.findViewById(R.id.edtTxt);
    final ListView myList = (ListView) dialog.findViewById(R.id.myList);
    final ProgressBar prgBar = (ProgressBar) dialog.findViewById(R.id.qnqPrg);
    final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGrp);
    final RadioButton baslangicBtn = (RadioButton) dialog.findViewById(R.id.baslangicBtn);
    final RadioButton terkibBtn = (RadioButton) dialog.findViewById(R.id.terkibBtn);
    final TextView notFoundTxt = (TextView) dialog.findViewById(R.id.notFoundTxt);
    final RelativeLayout mainLyt = (RelativeLayout) dialog.findViewById(R.id.mainLyt);

    qnqEdt.setText(this.qnqEdt.getText().toString());

    myList.setVisibility(View.INVISIBLE);
    prgBar.setVisibility(View.VISIBLE);
    notFoundTxt.setVisibility(View.INVISIBLE);


    final String url = "https://ady.express/Plan.asmx/Find_Qnqs";

    Uri uri = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("qnq", qnqEdt.getText().toString())
            .appendQueryParameter("searchType", "1").build();

    getQnqData(uri.toString(), myList, prgBar, notFoundTxt);


    qnqEdt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

        if(baslangicBtn.isChecked())
        {

          qnqModelList.clear();

          Uri uri1 = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("qnq", qnqEdt.getText().toString())
                  .appendQueryParameter("searchType", "1").build();

          if(requestQueue != null)
          {
            requestQueue.cancelAll("qnq");
          }

          getQnqData(uri1.toString(), myList, prgBar, notFoundTxt);

          prgBar.setVisibility(View.VISIBLE);
          myList.setVisibility(View.INVISIBLE);
          notFoundTxt.setVisibility(View.INVISIBLE);


        }

        else
        {

          qnqModelList.clear();

          Uri uri1 = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("qnq", qnqEdt.getText().toString())
                  .appendQueryParameter("searchType", "2").build();

          if(requestQueue != null)
          {
            requestQueue.cancelAll("qnq");
          }

          getQnqData(uri1.toString(), myList, prgBar, notFoundTxt);

          prgBar.setVisibility(View.VISIBLE);
          myList.setVisibility(View.INVISIBLE);
          notFoundTxt.setVisibility(View.INVISIBLE);


        }


      }

      @Override
      public void afterTextChanged(Editable s) {

      }
    });


    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        if(checkedId == R.id.baslangicBtn)
        {
          qnqModelList.clear();

          Uri uri1 = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("qnq", qnqEdt.getText().toString())
                  .appendQueryParameter("searchType", "1").build();

          if(requestQueue != null)
          {
            requestQueue.cancelAll("qnq");
          }

          getQnqData(uri1.toString(), myList, prgBar, notFoundTxt);

          prgBar.setVisibility(View.VISIBLE);
          notFoundTxt.setVisibility(View.INVISIBLE);
          myList.setVisibility(View.INVISIBLE);
        }
        else
        {
          qnqModelList.clear();

          Uri uri1 = Uri.parse(url).buildUpon().appendQueryParameter("userId", "1442").appendQueryParameter("qnq", qnqEdt.getText().toString())
                  .appendQueryParameter("searchType", "2").build();

          if(requestQueue != null)
          {
            requestQueue.cancelAll("qnq");
          }

          getQnqData(uri1.toString(), myList, prgBar, notFoundTxt);

          prgBar.setVisibility(View.VISIBLE);
          myList.setVisibility(View.INVISIBLE);
          notFoundTxt.setVisibility(View.INVISIBLE);

        }

      }
    });


    myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       // .setText(qnqModelList.get(position).getQnqCode());

        InSearch2.this.qnqEdt.setText(qnqModelList.get(position).getQnqCode());

        myList.smoothScrollBy(0,0);
        qnqModelList.clear();

        if(requestQueue != null)
        {
          requestQueue.cancelAll("qnq");
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            dialog.dismiss();
          }
        }, 300);
        mainLyt.animate().scaleY(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(250);

      }
    });



    Button closeBtn = (Button) dialog.findViewById(R.id.closeBtn);

    closeBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        myList.smoothScrollBy(0,0);
        qnqModelList.clear();

        if(requestQueue != null)
        {
          requestQueue.cancelAll("qnq");
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            dialog.dismiss();
          }
        }, 300);
        mainLyt.animate().scaleY(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(250);
      }
    });

    dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

  }


  public void getQnqData(String url, final ListView listView, final ProgressBar progressBar, final TextView textView)
  {

    stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {

                if(!response.equals("ï»¿")) {

                  try {

                    JSONObject jsonObject = new JSONObject(URLDecoder.decode(URLEncoder.encode(response, "iso8859-1"), "UTF-8"));
                    JSONArray dataArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < dataArray.length(); i++) {
                      JSONObject childJsonObject = dataArray.getJSONObject(i);
                      QnqModel model = new QnqModel(childJsonObject.getString("GNG_CODE"),
                              childJsonObject.getString("GNG_NAME"));

                      qnqModelList.add(model);
                    }

                    progressBar.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.INVISIBLE);


                    QnqAdapter adapter = new QnqAdapter(qnqModelList, getContext());
                    listView.setAdapter(adapter);

                  } catch (Exception e) {

                  }
                }
                else
                {
                  progressBar.setVisibility(View.INVISIBLE);
                  listView.setVisibility(View.INVISIBLE);
                  textView.setVisibility(View.VISIBLE);
                }

              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    stringRequest.setTag("qnq");
    requestQueue = Volley.newRequestQueue(getContext());
    requestQueue.add(stringRequest);
  }


  public void getPointData(String url, final ListView listView, final ProgressBar progressBar, final TextView textView)
  {

    stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {

                if(!response.equals("ï»¿")) {

                  try {

                    JSONObject jsonObject = new JSONObject(URLDecoder.decode(URLEncoder.encode(response, "iso8859-1"), "UTF-8"));
                    JSONArray dataArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < dataArray.length(); i++) {
                      JSONObject childJsonObject = dataArray.getJSONObject(i);
                      QnqModel model = new QnqModel(childJsonObject.getString("PNT_CODE"),
                              childJsonObject.getString("PNT_NAME"));

                      qnqModelList.add(model);
                    }

                    progressBar.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.INVISIBLE);


                    QnqAdapter adapter = new QnqAdapter(qnqModelList, getContext());
                    listView.setAdapter(adapter);

                  } catch (Exception e) {

                  }
                }
                else
                {
                  progressBar.setVisibility(View.INVISIBLE);
                  listView.setVisibility(View.INVISIBLE);
                  textView.setVisibility(View.VISIBLE);
                }

              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });

    stringRequest.setTag("qnq");
    requestQueue = Volley.newRequestQueue(getContext());
    requestQueue.add(stringRequest);
  }



}
