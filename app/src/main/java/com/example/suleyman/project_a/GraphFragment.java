package com.example.suleyman.project_a;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.example.suleyman.project_a.Adapter.QnqAdapter;
import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Model.QnqModel;
import com.example.suleyman.project_a.Sqlite.QnqDbHelper;
import com.example.suleyman.project_a.Sqlite.UpdateQnq;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.TokenParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Art Servis on 2/6/2018.
 */

public class GraphFragment extends Fragment {

  String user_ = "";
  String pass_ = "";
  String name_ = "";
  String userId_ = "";
  String urlQnq = "https://ady.express/PLan.asmx/all_qnqs";
  String urlUmumi = "https://ady.express/PLan.asmx/umumi_graph";
  String urlIdxal = "https://ady.express/PLan.asmx/idxal_graph";
  String urlIxrac = "https://ady.express/PLan.asmx/ixrac_graph";
  String urlTranzit = "https://ady.express/PLan.asmx/tranzit_graph";
  String urlDaxili = "https://ady.express/PLan.asmx/daxili_graph";
  String urlClients = "https://ady.express/PLan.asmx/all_clients";
  String urlUmumiClient = "https://ady.express/PLan.asmx/graph_client";
  String urlIdxalClient = "https://ady.express/PLan.asmx/graph_idxal_client";
  String urlIxracClient = "https://ady.express/PLan.asmx/graph_ixrac_client";
  String urlTranzitClient = "https://ady.express/PLan.asmx/graph_tranzit_client";
  String urlDaxiliClient = "https://ady.express/PLan.asmx/graph_daxili_client";
  String urlUmumiGelir = "https://ady.express/PLan.asmx/graph_gelir";
  String urlIdxalGelir = "https://ady.express/PLan.asmx/graph_idxal_gelir";
  String urlIxracGelir = "https://ady.express/PLan.asmx/graph_ixrac_gelir";
  String urlTranzitGelir = "https://ady.express/PLan.asmx/graph_tranzit_gelir";
  String urlDaxiliGelir = "https://ady.express/PLan.asmx/graph_daxili_gelir";
  ArrayList<QnqModel> qnqModelList = new ArrayList<>();
  StringRequest stringRequest;
  RequestQueue requestQueue;


  final ArrayList<String> qnqCodes = new ArrayList<String>();
  final ArrayList<String> onlyCodes = new ArrayList<String>();
  final ArrayList<String> allClients = new ArrayList<>();
  final ArrayList<String> allClients2 = new ArrayList<>();
  Spinner yearSpinner, qnqYearSpinner, ilYearSpinner1, ilYearSpinner2, musteri1Spinner, musteri2Spinner, musteriYearSpinner;
  Spinner gelirMusteriSpinner, gelirYearSpinner;
  String year = "";
  String qnqYear = "";
  String ilYear1 = "";
  String ilYear2 = "";
  RelativeLayout searchLyt;
  RelativeLayout qnqLyt, ilLyt, musteriLyt, gelirLyt, compareLyt, qnqSearchLyt, ilSearchLyt, musteriSearchLyt, gelirSearchLyt;
  RadioButton qnqBtn, ilBtn, musteriBtn, gelirBtn;
  EditText qnqQnqEdt1, qnqQnqEdt2, ilQnqEdt, gelirQnqEdt, qnqEditText;
  ImageView searchImage2, searchImage3, searchImage1, searchImage5;
  ProgressBar qnqPrgBar;

  //public static String sQnq = "";

  QnqDbHelper qnqDbHelper;
  SQLiteDatabase sqLiteDatabase, sqLiteDatabase2;
  int rowCount;

  //ArrayAdapter adapter;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

    if (getArguments() != null) {
      user_ = getArguments().getString("sess_user");
      pass_ = getArguments().getString("sess_pass");
      name_ = getArguments().getString("sess_name");
      userId_ = getArguments().getString("sess_id");
    }

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.new_graph_fragment, container, false);

    TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
    tabLayout.addTab(tabLayout.newTab().setText("ÜMUMİ"));
    tabLayout.addTab(tabLayout.newTab().setText("İDXAL"));
    tabLayout.addTab(tabLayout.newTab().setText("İXRAC"));
    tabLayout.addTab(tabLayout.newTab().setText("TRANZİT"));
    tabLayout.addTab(tabLayout.newTab().setText("DAXİLİ"));
    ArrayAdapter<CharSequence> YearAdapter;
    searchLyt = (RelativeLayout) view.findViewById(R.id.searchLyt);
    ImageView searchQnq = (ImageView) view.findViewById(R.id.searchImg);
    qnqEditText = (EditText) view.findViewById(R.id.QnqEdt);

    ilBtn = (RadioButton) view.findViewById(R.id.ilRadioBtn);
    qnqBtn = (RadioButton) view.findViewById(R.id.qnqRadioBtn);
    musteriBtn = (RadioButton) view.findViewById(R.id.musteriRadioBtn);
    gelirBtn = (RadioButton) view.findViewById(R.id.gelirRadioBtn);

    ilLyt = (RelativeLayout) view.findViewById(R.id.ilLyt);
    qnqLyt = (RelativeLayout) view.findViewById(R.id.qnqLyt);
    musteriLyt = (RelativeLayout) view.findViewById(R.id.musteriLyt);
    gelirLyt = (RelativeLayout) view.findViewById(R.id.gelirLyt);

    compareLyt = (RelativeLayout) view.findViewById(R.id.compareLyt);
    final DrawerLayout drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer);

    qnqQnqEdt1 = (EditText) view.findViewById(R.id.qnqQnq1Edt);
    qnqQnqEdt2 = (EditText) view.findViewById(R.id.qnqQnq2Edt);
    searchImage2 = (ImageView) view.findViewById(R.id.searchImg2);
    searchImage3 = (ImageView) view.findViewById(R.id.searchImg3);
    qnqYearSpinner = (Spinner) view.findViewById(R.id.qnqYear);
    qnqSearchLyt = (RelativeLayout) view.findViewById(R.id.qnqSearchLyt);

    ilQnqEdt = (EditText) view.findViewById(R.id.ilQnqEdt);
    ilYearSpinner1 = (Spinner) view.findViewById(R.id.ilyear1);
    ilYearSpinner2 = (Spinner) view.findViewById(R.id.ilyear2);
    ilSearchLyt = (RelativeLayout) view.findViewById(R.id.ilSearchLyt);
    searchImage1 = (ImageView) view.findViewById(R.id.searchImg1);

    allClients.add(0, "Müştərilər");
    musteri1Spinner = (Spinner) view.findViewById(R.id.musteri1Spinner);
    musteri2Spinner = (Spinner) view.findViewById(R.id.musteri2Spinner);
    musteriYearSpinner = (Spinner) view.findViewById(R.id.musteriYear);
    musteriSearchLyt = (RelativeLayout) view.findViewById(R.id.musteriSearchLyt);


    gelirQnqEdt = (EditText) view.findViewById(R.id.gelirQnqEdt);
    searchImage5 = (ImageView) view.findViewById(R.id.searchImg5);
    gelirMusteriSpinner = (Spinner) view.findViewById(R.id.gelirMusteriSpinner);
    gelirYearSpinner = (Spinner) view.findViewById(R.id.gelirYear);
    gelirSearchLyt = (RelativeLayout) view.findViewById(R.id.gelirSearchLyt);
    allClients2.add(0, "Hamısı");

    qnqDbHelper = new QnqDbHelper(getContext());
    sqLiteDatabase = qnqDbHelper.getReadableDatabase();
    sqLiteDatabase2 = qnqDbHelper.getWritableDatabase();


    navi_menu3.graphQnq = "";
    navi_menu3.graphQnq2 = "";
    navi_menu3.graphIlQnq = "";
    navi_menu3.graphGelirQnq = "";


    onBackPressed(qnqQnqEdt1);
    onBackPressed(qnqQnqEdt2);
    onBackPressed(qnqEditText);
    onBackPressed(gelirQnqEdt);
    onBackPressed(ilQnqEdt);


    qnqQnqEdt1.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        navi_menu3.graphQnq = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    qnqQnqEdt2.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        navi_menu3.graphQnq2 = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    gelirQnqEdt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        navi_menu3.graphGelirQnq = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    ilQnqEdt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        navi_menu3.graphIlQnq = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    new getQnqsFromDb().execute();

    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


    qnqSearchLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (qnqQnqEdt1.getText().toString().equals("") || qnqQnqEdt2.getText().toString().equals("")) {
          Toast.makeText(getContext(), "QNQ-ləri daxil edin", Toast.LENGTH_SHORT).show();
        } else {
          String qnq1 = "";
          String qnq2 = "";
          if (qnqYearSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "İli seçin", Toast.LENGTH_SHORT).show();
          } else {
            if (qnqQnqEdt1.getText().toString().length() < 8) {
              qnq1 = qnqQnqEdt1.getText().toString();
            }
            if (qnqQnqEdt2.getText().toString().length() < 8) {
              qnq2 = qnqQnqEdt2.getText().toString();
            }
            if (qnqQnqEdt1.getText().toString().length() >= 8) {
              qnq1 = qnqQnqEdt1.getText().toString().substring(0, 8);
            }
            if (qnqQnqEdt2.getText().toString().length() >= 8) {
              qnq2 = qnqQnqEdt2.getText().toString().substring(0, 8);
            }
            new CompareQnqUmumi(qnq1, qnq2, qnqYearSpinner.getSelectedItem().toString()).execute();
            new CompareQnqIdxal(qnq1, qnq2, qnqYearSpinner.getSelectedItem().toString()).execute();
            new CompareQnqIxrac(qnq1, qnq2, qnqYearSpinner.getSelectedItem().toString()).execute();
            new CompareQnqTranzit(qnq1, qnq2, qnqYearSpinner.getSelectedItem().toString()).execute();
            new CompareQnqDaxili(qnq1, qnq2, qnqYearSpinner.getSelectedItem().toString()).execute();
            drawerLayout.closeDrawer(Gravity.RIGHT);
          }
        }
      }
    });

    ilSearchLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (ilQnqEdt.getText().toString().equals("")) {
          Toast.makeText(getContext(), "QNQ-ni daxil edin", Toast.LENGTH_SHORT).show();
        } else {
          if (ilYearSpinner1.getSelectedItemPosition() == 0 || ilYearSpinner2.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "İlləri seçin", Toast.LENGTH_SHORT).show();
          } else {
            if (ilQnqEdt.getText().toString().length() < 8) {
              new CompareIlUmumi(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString()).execute();
              new CompareIlİdxal(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString()).execute();
              new CompareIlIxrac(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString()).execute();
              new CompareIlTranzit(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString()).execute();
              new CompareIlDaxili(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString()).execute();

            } else {
              new CompareIlUmumi(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString().substring(0, 8)).execute();
              new CompareIlİdxal(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString().substring(0, 8)).execute();
              new CompareIlIxrac(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString().substring(0, 8)).execute();
              new CompareIlTranzit(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString().substring(0, 8)).execute();
              new CompareIlDaxili(ilYearSpinner1.getSelectedItem().toString(), ilYearSpinner2.getSelectedItem().toString(), ilQnqEdt.getText().toString().substring(0, 8)).execute();

            }

            drawerLayout.closeDrawer(Gravity.RIGHT);
          }
        }

      }
    });

    musteriSearchLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (musteri1Spinner.getSelectedItemPosition() == 0 || musteri2Spinner.getSelectedItemPosition() == 0) {
          Toast.makeText(getContext(), "Müştəriləri seçin", Toast.LENGTH_SHORT).show();
        } else {
          if (musteriYearSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "İli seçin", Toast.LENGTH_SHORT).show();

          } else {
            new CompareClientUmumi(musteri1Spinner.getSelectedItem().toString(), musteri2Spinner.getSelectedItem().toString(), musteriYearSpinner.getSelectedItem().toString()).execute();
            new CompareClientIdxal(musteri1Spinner.getSelectedItem().toString(), musteri2Spinner.getSelectedItem().toString(), musteriYearSpinner.getSelectedItem().toString()).execute();
            new CompareClientIxrac(musteri1Spinner.getSelectedItem().toString(), musteri2Spinner.getSelectedItem().toString(), musteriYearSpinner.getSelectedItem().toString()).execute();
            new CompareClientTranzit(musteri1Spinner.getSelectedItem().toString(), musteri2Spinner.getSelectedItem().toString(), musteriYearSpinner.getSelectedItem().toString()).execute();
            new CompareClientDaxili(musteri1Spinner.getSelectedItem().toString(), musteri2Spinner.getSelectedItem().toString(), musteriYearSpinner.getSelectedItem().toString()).execute();
            drawerLayout.closeDrawer(Gravity.RIGHT);
          }
        }
      }
    });

    gelirSearchLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (gelirQnqEdt.getText().toString().equals("")) {
          Toast.makeText(getContext(), "QNQ-ni daxil edin", Toast.LENGTH_SHORT).show();
        } else {
          if (gelirYearSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "İli seçin", Toast.LENGTH_SHORT).show();
          } else {
            if (gelirQnqEdt.getText().toString().length() < 8) {

              new CompareGelirUmumi(gelirQnqEdt.getText().toString(), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
              new CompareGelirIdxal(gelirQnqEdt.getText().toString(), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
              new CompareGelirIxrac(gelirQnqEdt.getText().toString(), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
              new CompareGelirTranzit(gelirQnqEdt.getText().toString(), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
              new CompareGelirDaxili(gelirQnqEdt.getText().toString(), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
            } else {

              new CompareGelirUmumi(gelirQnqEdt.getText().toString().substring(0, 8), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
              new CompareGelirIdxal(gelirQnqEdt.getText().toString().substring(0, 8), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
              new CompareGelirIxrac(gelirQnqEdt.getText().toString().substring(0, 8), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
              new CompareGelirTranzit(gelirQnqEdt.getText().toString().substring(0, 8), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();
              new CompareGelirDaxili(gelirQnqEdt.getText().toString().substring(0, 8), gelirMusteriSpinner.getSelectedItem().toString(), gelirYearSpinner.getSelectedItem().toString()).execute();

            }

            drawerLayout.closeDrawer(Gravity.RIGHT);
          }
        }
      }
    });


    qnqEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        navi_menu3.graphQnq = "";
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        qnqQnqEdt1.setText(charSequence);
        ilQnqEdt.setText(charSequence);
        gelirQnqEdt.setText(charSequence);
        navi_menu3.graphQnq = charSequence.toString();
        // sQnq = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {
        // navi_menu3.graphQnq = "";
      }
    });


    compareLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);

      }
    });


    // final Animation bottomUp = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up);

    ilBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
          animation(ilLyt, ilBtn);
        } else {
          ilLyt.setVisibility(View.GONE);
          ilBtn.setBackgroundColor(Color.WHITE);
          ilBtn.setTextColor(Color.BLACK);
        }
      }
    });

    qnqBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
          animation(qnqLyt, qnqBtn);
        } else {
          qnqLyt.setVisibility(View.GONE);
          qnqBtn.setBackgroundColor(Color.WHITE);
          qnqBtn.setTextColor(Color.BLACK);
          // Toast.makeText(getContext(), "ala", Toast.LENGTH_SHORT).show();
        }
      }
    });

    musteriBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
          animation(musteriLyt, musteriBtn);
        } else {
          musteriLyt.setVisibility(View.GONE);
          musteriBtn.setBackgroundColor(Color.WHITE);
          musteriBtn.setTextColor(Color.BLACK);
        }
      }
    });

    gelirBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
          animation(gelirLyt, gelirBtn);
        } else {
          gelirLyt.setVisibility(View.GONE);
          gelirBtn.setBackgroundColor(Color.WHITE);
          gelirBtn.setTextColor(Color.BLACK);
        }
      }
    });

    yearSpinner = (Spinner) view.findViewById(R.id.year);

    //YearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.array_year, R.layout.spinner_textview);

    YearAdapter = new ArrayAdapter<CharSequence>(getActivity(), R.layout.spinner_textview, getResources().getStringArray(R.array.array_year)) {
      @Override
      public boolean isEnabled(int position) {
        if (position == 0) {

          return false;
        } else {
          return true;
        }
      }

      @Override
      public View getDropDownView(int position, View convertView,
                                  ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if (position == 0) {
          tv.setTextColor(Color.GRAY);
        } else {
          tv.setTextColor(Color.WHITE);
        }
        return view;
      }
    };

    YearAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

    new JsonClient().execute();

    final ArrayAdapter<String> clientAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, allClients) {
      @Override
      public boolean isEnabled(int position) {
        if (position == 0) {

          return false;
        } else {
          return true;
        }
      }

      @Override
      public View getDropDownView(int position, View convertView,
                                  ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv = (TextView) view;
        if (position == 0) {
          tv.setTextColor(Color.GRAY);
        } else {
          tv.setTextColor(Color.WHITE);
        }
        return view;
      }

      @NonNull
      @Override
      public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        TextView tv = (TextView) view;

        return view;
      }
    };

    clientAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

    final ArrayAdapter<String> clientAdapter2 = new ArrayAdapter<String>(getActivity(), R.layout.spinner_textview, allClients2);
    clientAdapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
    gelirMusteriSpinner.setAdapter(clientAdapter2);

    musteri1Spinner.setAdapter(clientAdapter);
    musteri2Spinner.setAdapter(clientAdapter);

    yearSpinner.setAdapter(YearAdapter);

    gelirYearSpinner.setAdapter(YearAdapter);

    qnqYearSpinner.setAdapter(YearAdapter);

    ilYearSpinner1.setAdapter(YearAdapter);

    ilYearSpinner2.setAdapter(YearAdapter);

    musteriYearSpinner.setAdapter(YearAdapter);

    ilYearSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ilYear2 = adapterView.getItemAtPosition(i).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });


    ilYearSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ilYear1 = adapterView.getItemAtPosition(i).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        year = adapterView.getItemAtPosition(i).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    qnqYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        qnqYear = adapterView.getItemAtPosition(i).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    searchLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (qnqEditText.getText().toString().equals("")) {
          Toast.makeText(getContext(), "QNQ-ni daxil edin", Toast.LENGTH_SHORT).show();

        } else {
          if (yearSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "İli seçin", Toast.LENGTH_SHORT).show();
          } else {

            if (qnqEditText.getText().toString().length() < 8) {
              new JsonUmumi(qnqEditText.getText().toString(), yearSpinner.getSelectedItem().toString()).execute();
              new JsonIdxal(qnqEditText.getText().toString(), yearSpinner.getSelectedItem().toString()).execute();
              new JsonIxrac(qnqEditText.getText().toString(), yearSpinner.getSelectedItem().toString()).execute();
              new JsonTranzit(qnqEditText.getText().toString(), yearSpinner.getSelectedItem().toString()).execute();
              new JsonDaxili(qnqEditText.getText().toString(), yearSpinner.getSelectedItem().toString()).execute();

            } else {
              new JsonUmumi(qnqEditText.getText().toString().substring(0, 8), yearSpinner.getSelectedItem().toString()).execute();
              new JsonIdxal(qnqEditText.getText().toString().substring(0, 8), yearSpinner.getSelectedItem().toString()).execute();
              new JsonIxrac(qnqEditText.getText().toString().substring(0, 8), yearSpinner.getSelectedItem().toString()).execute();
              new JsonTranzit(qnqEditText.getText().toString().substring(0, 8), yearSpinner.getSelectedItem().toString()).execute();
              new JsonDaxili(qnqEditText.getText().toString().substring(0, 8), yearSpinner.getSelectedItem().toString()).execute();

            }

          }
        }
      }
    });


    // new JsonQnq().execute();


    searchQnq.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {
        searchDialog(1);

      }
    });

    searchImage5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        searchDialog(5);
      }
    });

    searchImage2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        searchDialog(3);
      }
    });
    searchImage3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        searchDialog(4);
      }
    });

    searchImage1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        searchDialog(2);
      }
    });


    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    final ViewPager viewPager = (ViewPager) view.findViewById(R.id.container);

    final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(5);
    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

    return view;
  }


  public class JsonQnq extends AsyncTask<String, String, JSONObject> {


    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));

      JSONObject json = jParser.getJSONFromUrl(urlQnq, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      //Log.i("Qnqler", jsonObject.toString());
      //   qnqPrgBar.setVisibility(View.INVISIBLE);
      try {
        if (jsonObject != null) {
          JSONArray dataArray = jsonObject.getJSONArray("data");
          //  if(dataArray.length()>rowCount) {
          for (int i = 0; i < dataArray.length(); i++) {
            JSONObject jsonObject1 = dataArray.getJSONObject(i);
            String code = jsonObject1.getString("FCT_QNQ");
            String name = jsonObject1.getString("Name");
            if (!code.equals("")) {
              onlyCodes.add(code);
              if (!name.equals("") && !name.equals("Digərləri")) {
                qnqCodes.add("(" + code + ")" + name);
              } else {
                qnqCodes.add(code);
              }
              //  qnqDbHelper.addQnqName("(" + code + ")" + name, sqLiteDatabase);
            }

            //  }
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
      // adapter.setNotifyOnChange(true);
    }
  }

  public void searchDialog(final int qnqType) {

    final Dialog dialog = new Dialog(getActivity());
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    dialog.setContentView(R.layout.custom_qnq_popup);
    dialog.show();

    DisplayMetrics metrics = getResources().getDisplayMetrics();
    int width = metrics.widthPixels;
    int height = metrics.heightPixels;

    final  EditText qnqEdt = (EditText) dialog.findViewById(R.id.edtTxt);
    final ListView myList = (ListView) dialog.findViewById(R.id.myList);
    final ProgressBar prgBar = (ProgressBar) dialog.findViewById(R.id.qnqPrg);
    final RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGrp);
    final RadioButton baslangicBtn = (RadioButton) dialog.findViewById(R.id.baslangicBtn);
    final RadioButton terkibBtn = (RadioButton) dialog.findViewById(R.id.terkibBtn);
    final TextView notFoundTxt = (TextView) dialog.findViewById(R.id.notFoundTxt);
    final RelativeLayout mainLyt = (RelativeLayout) dialog.findViewById(R.id.mainLyt);

    String searchedQnq = "";

    if(qnqType == 1) {
      qnqEdt.setText(qnqEditText.getText().toString());
    }
    if(qnqType == 2)
    {
      qnqEdt.setText(ilQnqEdt.getText().toString());
    }
    if(qnqType == 3)
    {
      qnqEdt.setText(qnqQnqEdt1.getText().toString());
    }
    if(qnqType == 4){
      qnqEdt.setText(qnqQnqEdt2.getText().toString());

    }
    if(qnqType == 5){
      qnqEdt.setText(gelirQnqEdt.getText().toString());
    }
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

        if(qnqType == 1) {
          qnqEditText.setText(qnqModelList.get(position).getQnqCode());
        }
        if(qnqType == 2) {
          ilQnqEdt.setText(qnqModelList.get(position).getQnqCode());
        }
        if(qnqType == 3) {
          qnqQnqEdt1.setText(qnqModelList.get(position).getQnqCode());
        }
        if(qnqType == 4) {
          qnqQnqEdt2.setText(qnqModelList.get(position).getQnqCode());
        }
        if(qnqType == 5) {
          gelirQnqEdt.setText(qnqModelList.get(position).getQnqCode());
        }

        myList.smoothScrollBy(0,0);
        qnqModelList.clear();

        if(requestQueue != null)
        {
          requestQueue.cancelAll("qnq");
        }

        final android.os.Handler handler = new android.os.Handler();
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

        final android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            dialog.dismiss();
          }
        }, 300);
        mainLyt.animate().scaleY(0).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(250);
      }
    });

    dialog.getWindow().setLayout(width, height);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    mainLyt.setLayoutParams(layoutParams);

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


  public class JsonClient extends AsyncTask<String, String, JSONObject> {


    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));

      JSONObject json = jParser.getJSONFromUrl(urlClients, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      Log.i("Clients", jsonObject.toString());
      try {
        if (jsonObject != null) {
          JSONArray dataArray = jsonObject.getJSONArray("data");
          for (int i = 0; i < dataArray.length(); i++) {
            JSONObject jsonObject1 = dataArray.getJSONObject(i);
            String name = jsonObject1.getString("clientName");
            allClients.add(name);
            allClients2.add(name);
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  public class JsonUmumi extends AsyncTask<String, String, JSONObject> {

    String qnq;
    String year;

    public JsonUmumi(String qnq, String year) {
      this.qnq = qnq;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      AllFragment.allPrg.setVisibility(View.VISIBLE);
      AllFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlUmumi, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      int[] values = new int[12];
      //llFragment.graphView.removeAllSeries();

      try {
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
          JSONObject jsonObject1 = dataArray.getJSONObject(i);
          String ton = jsonObject1.getString("Ton");
          values[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
        }


        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, values[0]));
        yValues.add(new Entry(1, values[1]));
        yValues.add(new Entry(2, values[2]));
        yValues.add(new Entry(3, values[3]));
        yValues.add(new Entry(4, values[4]));
        yValues.add(new Entry(5, values[5]));
        yValues.add(new Entry(6, values[6]));
        yValues.add(new Entry(7, values[7]));
        yValues.add(new Entry(8, values[8]));
        yValues.add(new Entry(9, values[9]));
        yValues.add(new Entry(10, values[10]));
        yValues.add(new Entry(11, values[11]));

        LineDataSet set1 = new LineDataSet(yValues, "2017");
        set1.setColor(Color.parseColor("#5b9bd5"));
        // set1.setColor(Color.argb(20, 284, 55, 32));
        set1.setValueTextColor(Color.parseColor("#bfbfbf"));
        set1.setValueTextSize(9f);
        set1.setLineWidth(2f);
        set1.setCircleColor(Color.parseColor("#5b9bd5"));
        set1.setCircleRadius(4f);
        set1.setCircleHoleRadius(2f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        AllFragment.graphView.setData(data);
        AllFragment.allPrg.setVisibility(View.INVISIBLE);
        AllFragment.graphView.getAxisLeft().setStartAtZero(true);
        AllFragment.graphView.setVisibility(View.VISIBLE);
        AllFragment.graphView.getLegend().setEnabled(false);


      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  public class JsonIdxal extends AsyncTask<String, String, JSONObject> {

    String qnq;
    String year;

    public JsonIdxal(String qnq, String year) {
      this.qnq = qnq;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IdxalFragment.idxalPrg.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlIdxal, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      //LineGraphSeries<DataPoint> series;
      // IdxalFragment.graphView.removeAllSeries();
      int[] values = new int[12];

      try {
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
          JSONObject jsonObject1 = dataArray.getJSONObject(i);
          String ton = jsonObject1.getString("Ton");
          values[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
        }

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, values[0]));
        yValues.add(new Entry(1, values[1]));
        yValues.add(new Entry(2, values[2]));
        yValues.add(new Entry(3, values[3]));
        yValues.add(new Entry(4, values[4]));
        yValues.add(new Entry(5, values[5]));
        yValues.add(new Entry(6, values[6]));
        yValues.add(new Entry(7, values[7]));
        yValues.add(new Entry(8, values[8]));
        yValues.add(new Entry(9, values[9]));
        yValues.add(new Entry(10, values[10]));
        yValues.add(new Entry(11, values[11]));

        LineDataSet set1 = new LineDataSet(yValues, "");
        set1.setColor(Color.parseColor("#5b9bd5"));
        set1.setValueTextColor(Color.parseColor("#bfbfbf"));
        set1.setValueTextSize(9f);
        set1.setLineWidth(2f);
        set1.setCircleColor(Color.parseColor("#5b9bd5"));
        set1.setCircleRadius(4f);
        set1.setCircleHoleRadius(2f);

        //set1.setFillAlpha(50);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        IdxalFragment.graphView.setData(data);
        IdxalFragment.idxalPrg.setVisibility(View.INVISIBLE);
        IdxalFragment.graphView.setVisibility(View.VISIBLE);
        IdxalFragment.graphView.getAxisLeft().setStartAtZero(true);
        IdxalFragment.graphView.getLegend().setEnabled(false);

      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  public class JsonIxrac extends AsyncTask<String, String, JSONObject> {

    String qnq;
    String year;

    public JsonIxrac(String qnq, String year) {
      this.qnq = qnq;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IxracFragment.graphView.setVisibility(View.INVISIBLE);
      IxracFragment.ixracPrg.setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlIxrac, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      int[] values = new int[12];
      //IxracFragment.graphView.removeAllSeries();

      try {
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
          JSONObject jsonObject1 = dataArray.getJSONObject(i);
          String ton = jsonObject1.getString("Ton");
          values[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
        }

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, values[0]));
        yValues.add(new Entry(1, values[1]));
        yValues.add(new Entry(2, values[2]));
        yValues.add(new Entry(3, values[3]));
        yValues.add(new Entry(4, values[4]));
        yValues.add(new Entry(5, values[5]));
        yValues.add(new Entry(6, values[6]));
        yValues.add(new Entry(7, values[7]));
        yValues.add(new Entry(8, values[8]));
        yValues.add(new Entry(9, values[9]));
        yValues.add(new Entry(10, values[10]));
        yValues.add(new Entry(11, values[11]));

        LineDataSet set1 = new LineDataSet(yValues, "");
        set1.setColor(Color.parseColor("#5b9bd5"));
        set1.setValueTextColor(Color.parseColor("#bfbfbf"));
        set1.setValueTextSize(9f);
        set1.setLineWidth(2f);
        set1.setCircleColor(Color.parseColor("#5b9bd5"));
        set1.setCircleRadius(4f);
        set1.setCircleHoleRadius(2f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        IxracFragment.graphView.setData(data);

        IxracFragment.graphView.setVisibility(View.VISIBLE);
        IxracFragment.ixracPrg.setVisibility(View.INVISIBLE);
        IxracFragment.graphView.getAxisLeft().setStartAtZero(true);
        IxracFragment.graphView.getLegend().setEnabled(false);

      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  public class JsonTranzit extends AsyncTask<String, String, JSONObject> {

    String qnq;
    String year;

    public JsonTranzit(String qnq, String year) {
      this.qnq = qnq;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      TranzitFragment.graphView.setVisibility(View.INVISIBLE);
      TranzitFragment.tranzitPrg.setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlTranzit, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      int[] values = new int[12];
      //TranzitFragment.graphView.removeAllSeries();

      try {
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
          JSONObject jsonObject1 = dataArray.getJSONObject(i);
          String ton = jsonObject1.getString("Ton");
          values[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
        }

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, values[0]));
        yValues.add(new Entry(1, values[1]));
        yValues.add(new Entry(2, values[2]));
        yValues.add(new Entry(3, values[3]));
        yValues.add(new Entry(4, values[4]));
        yValues.add(new Entry(5, values[5]));
        yValues.add(new Entry(6, values[6]));
        yValues.add(new Entry(7, values[7]));
        yValues.add(new Entry(8, values[8]));
        yValues.add(new Entry(9, values[9]));
        yValues.add(new Entry(10, values[10]));
        yValues.add(new Entry(11, values[11]));

        LineDataSet set1 = new LineDataSet(yValues, "");
        set1.setColor(Color.parseColor("#5b9bd5"));
        set1.setValueTextColor(Color.parseColor("#bfbfbf"));
        set1.setValueTextSize(9f);
        set1.setLineWidth(2f);
        set1.setCircleColor(Color.parseColor("#5b9bd5"));
        set1.setCircleRadius(4f);
        set1.setCircleHoleRadius(2f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        TranzitFragment.graphView.setData(data);


        TranzitFragment.graphView.setVisibility(View.VISIBLE);
        TranzitFragment.tranzitPrg.setVisibility(View.INVISIBLE);
        TranzitFragment.graphView.getAxisLeft().setStartAtZero(true);
        TranzitFragment.graphView.getLegend().setEnabled(false);

      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  public class JsonDaxili extends AsyncTask<String, String, JSONObject> {

    String qnq;
    String year;

    public JsonDaxili(String qnq, String year) {
      this.qnq = qnq;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      DaxiliFragment.daxiliPrg.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlDaxili, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);

      int[] values = new int[12];

      try {
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
          JSONObject jsonObject1 = dataArray.getJSONObject(i);
          String ton = jsonObject1.getString("Ton");
          values[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));

        }

        ArrayList<Entry> yValues = new ArrayList<>();

        yValues.add(new Entry(0, values[0]));
        yValues.add(new Entry(1, values[1]));
        yValues.add(new Entry(2, values[2]));
        yValues.add(new Entry(3, values[3]));
        yValues.add(new Entry(4, values[4]));
        yValues.add(new Entry(5, values[5]));
        yValues.add(new Entry(6, values[6]));
        yValues.add(new Entry(7, values[7]));
        yValues.add(new Entry(8, values[8]));
        yValues.add(new Entry(9, values[9]));
        yValues.add(new Entry(10, values[10]));
        yValues.add(new Entry(11, values[11]));

        LineDataSet set1 = new LineDataSet(yValues, "");
        set1.setColor(Color.parseColor("#5b9bd5"));
        set1.setValueTextColor(Color.parseColor("#bfbfbf"));
        set1.setValueTextSize(9f);
        set1.setLineWidth(2f);
        set1.setCircleColor(Color.parseColor("#5b9bd5"));
        set1.setCircleRadius(4f);
        set1.setCircleHoleRadius(2f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);
        DaxiliFragment.graphView.setData(data);


        DaxiliFragment.daxiliPrg.setVisibility(View.INVISIBLE);
        DaxiliFragment.graphView.setVisibility(View.VISIBLE);
        DaxiliFragment.graphView.getAxisLeft().setStartAtZero(true);
        DaxiliFragment.graphView.getLegend().setEnabled(false);

      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }


  public class CompareIlUmumi extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String year1, year2;
    String qnq;

    public CompareIlUmumi(String year1, String year2, String qnq) {
      this.year1 = year1;
      this.year2 = year2;
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      AllFragment.allPrg.setVisibility(View.VISIBLE);
      AllFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year1));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq));
      params2.add(new BasicNameValuePair("year", year2));

      JSONObject json = jParser.getJSONFromUrl(urlUmumi, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlUmumi, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(year1, year2, jsonObjects);

      AllFragment.graphView.setData(data);

      AllFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"QNQ: " + qnq});
      AllFragment.graphView.notifyDataSetChanged();

      AllFragment.allPrg.setVisibility(View.INVISIBLE);
      AllFragment.graphView.getAxisLeft().setStartAtZero(true);
      AllFragment.graphView.setVisibility(View.VISIBLE);
      AllFragment.graphView.getLegend().setEnabled(true);
      AllFragment.graphView.getLegend().setTextColor(Color.WHITE);
      AllFragment.graphView.getLegend().setForm(Legend.LegendForm.LINE);
      AllFragment.graphView.getLegend().setFormSize(20f);

    }
  }

  public class CompareIlİdxal extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String year1, year2;
    String qnq;

    public CompareIlİdxal(String year1, String year2, String qnq) {
      this.year1 = year1;
      this.year2 = year2;
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IdxalFragment.idxalPrg.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year1));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq));
      params2.add(new BasicNameValuePair("year", year2));

      JSONObject json = jParser.getJSONFromUrl(urlIdxal, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlIdxal, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(year1, year2, jsonObjects);

      IdxalFragment.graphView.setData(data);

      IdxalFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"QNQ: " + qnq});
      IdxalFragment.graphView.notifyDataSetChanged();

      IdxalFragment.idxalPrg.setVisibility(View.INVISIBLE);
      IdxalFragment.graphView.getAxisLeft().setStartAtZero(true);
      IdxalFragment.graphView.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.getLegend().setEnabled(true);

    }
  }


  public class CompareIlIxrac extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String year1, year2;
    String qnq;

    public CompareIlIxrac(String year1, String year2, String qnq) {
      this.year1 = year1;
      this.year2 = year2;
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IxracFragment.ixracPrg.setVisibility(View.VISIBLE);
      IxracFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year1));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq));
      params2.add(new BasicNameValuePair("year", year2));

      JSONObject json = jParser.getJSONFromUrl(urlIxrac, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlIxrac, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(year1, year2, jsonObjects);

      IxracFragment.graphView.setData(data);

      IxracFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"QNQ: " + qnq});
      IxracFragment.graphView.notifyDataSetChanged();

      IxracFragment.ixracPrg.setVisibility(View.INVISIBLE);
      IxracFragment.graphView.getAxisLeft().setStartAtZero(true);
      IxracFragment.graphView.setVisibility(View.VISIBLE);
      IxracFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareIlTranzit extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String year1, year2;
    String qnq;

    public CompareIlTranzit(String year1, String year2, String qnq) {
      this.year1 = year1;
      this.year2 = year2;
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      TranzitFragment.tranzitPrg.setVisibility(View.VISIBLE);
      TranzitFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year1));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq));
      params2.add(new BasicNameValuePair("year", year2));

      JSONObject json = jParser.getJSONFromUrl(urlTranzit, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlTranzit, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(year1, year2, jsonObjects);

      TranzitFragment.graphView.setData(data);

      TranzitFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"QNQ: " + qnq});
      TranzitFragment.graphView.notifyDataSetChanged();

      TranzitFragment.tranzitPrg.setVisibility(View.INVISIBLE);
      TranzitFragment.graphView.getAxisLeft().setStartAtZero(true);
      TranzitFragment.graphView.setVisibility(View.VISIBLE);
      TranzitFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareIlDaxili extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String year1, year2;
    String qnq;

    public CompareIlDaxili(String year1, String year2, String qnq) {
      this.year1 = year1;
      this.year2 = year2;
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      DaxiliFragment.daxiliPrg.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("year", year1));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq));
      params2.add(new BasicNameValuePair("year", year2));

      JSONObject json = jParser.getJSONFromUrl(urlDaxili, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlDaxili, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(year1, year2, jsonObjects);

      DaxiliFragment.graphView.setData(data);

      DaxiliFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"QNQ: " + qnq});
      DaxiliFragment.graphView.notifyDataSetChanged();

      DaxiliFragment.daxiliPrg.setVisibility(View.INVISIBLE);
      DaxiliFragment.graphView.getAxisLeft().setStartAtZero(true);
      DaxiliFragment.graphView.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.getLegend().setEnabled(true);

    }
  }


  public class CompareQnqUmumi extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String qnq1, qnq2;
    String year;

    public CompareQnqUmumi(String qnq1, String qnq2, String year) {
      this.qnq1 = qnq1;
      this.qnq2 = qnq2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      AllFragment.allPrg.setVisibility(View.VISIBLE);
      AllFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlUmumi, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlUmumi, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(qnq1, qnq2, jsonObjects);

      AllFragment.graphView.setData(data);

      AllFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + qnqYearSpinner.getSelectedItem().toString()});
      AllFragment.graphView.notifyDataSetChanged();

      AllFragment.allPrg.setVisibility(View.INVISIBLE);
      AllFragment.graphView.getAxisLeft().setStartAtZero(true);
      AllFragment.graphView.setVisibility(View.VISIBLE);
      AllFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareQnqIdxal extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String qnq1, qnq2;
    String year;

    public CompareQnqIdxal(String qnq1, String qnq2, String year) {
      this.qnq1 = qnq1;
      this.qnq2 = qnq2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IdxalFragment.idxalPrg.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlIdxal, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlIdxal, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(qnq1, qnq2, jsonObjects);

      IdxalFragment.graphView.setData(data);

      IdxalFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + qnqYearSpinner.getSelectedItem().toString()});
      IdxalFragment.graphView.notifyDataSetChanged();

      IdxalFragment.idxalPrg.setVisibility(View.INVISIBLE);
      IdxalFragment.graphView.getAxisLeft().setStartAtZero(true);
      IdxalFragment.graphView.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareQnqIxrac extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String qnq1, qnq2;
    String year;

    public CompareQnqIxrac(String qnq1, String qnq2, String year) {
      this.qnq1 = qnq1;
      this.qnq2 = qnq2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IxracFragment.ixracPrg.setVisibility(View.VISIBLE);
      IxracFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlIxrac, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlIxrac, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(qnq1, qnq2, jsonObjects);

      IxracFragment.graphView.setData(data);

      IxracFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + qnqYearSpinner.getSelectedItem().toString()});
      IxracFragment.graphView.notifyDataSetChanged();

      IxracFragment.ixracPrg.setVisibility(View.INVISIBLE);
      IxracFragment.graphView.getAxisLeft().setStartAtZero(true);
      IxracFragment.graphView.setVisibility(View.VISIBLE);
      IxracFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareQnqTranzit extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String qnq1, qnq2;
    String year;

    public CompareQnqTranzit(String qnq1, String qnq2, String year) {
      this.qnq1 = qnq1;
      this.qnq2 = qnq2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      TranzitFragment.tranzitPrg.setVisibility(View.VISIBLE);
      TranzitFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlTranzit, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlTranzit, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(qnq1, qnq2, jsonObjects);

      TranzitFragment.graphView.setData(data);

      TranzitFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + qnqYearSpinner.getSelectedItem().toString()});
      TranzitFragment.graphView.notifyDataSetChanged();

      TranzitFragment.tranzitPrg.setVisibility(View.INVISIBLE);
      TranzitFragment.graphView.getAxisLeft().setStartAtZero(true);
      TranzitFragment.graphView.setVisibility(View.VISIBLE);
      TranzitFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareQnqDaxili extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String qnq1, qnq2;
    String year;

    public CompareQnqDaxili(String qnq1, String qnq2, String year) {
      this.qnq1 = qnq1;
      this.qnq2 = qnq2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      DaxiliFragment.daxiliPrg.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("qnq", qnq1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("qnq", qnq2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlDaxili, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlDaxili, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = comparePostExecute(qnq1, qnq2, jsonObjects);

      DaxiliFragment.graphView.setData(data);

      DaxiliFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + qnqYearSpinner.getSelectedItem().toString()});
      DaxiliFragment.graphView.notifyDataSetChanged();

      DaxiliFragment.daxiliPrg.setVisibility(View.INVISIBLE);
      DaxiliFragment.graphView.getAxisLeft().setStartAtZero(true);
      DaxiliFragment.graphView.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareClientUmumi extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String client1, client2;
    String year;

    public CompareClientUmumi(String client1, String client2, String year) {
      this.client1 = client1;
      this.client2 = client2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      AllFragment.allPrg.setVisibility(View.VISIBLE);
      AllFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", client1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("client", client2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlUmumiClient, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlUmumiClient, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = compareClientPostExecute(client1, client2, jsonObjects);

      AllFragment.graphView.setData(data);

      AllFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + musteriYearSpinner.getSelectedItem().toString()});
      AllFragment.graphView.notifyDataSetChanged();

      AllFragment.allPrg.setVisibility(View.INVISIBLE);
      AllFragment.graphView.getAxisLeft().setStartAtZero(true);
      AllFragment.graphView.setVisibility(View.VISIBLE);
      AllFragment.graphView.getLegend().setEnabled(true);

    }
  }


  public class CompareClientIdxal extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String client1, client2;
    String year;

    public CompareClientIdxal(String client1, String client2, String year) {
      this.client1 = client1;
      this.client2 = client2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IdxalFragment.idxalPrg.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", client1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("client", client2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlIdxalClient, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlIdxalClient, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = compareClientPostExecute(client1, client2, jsonObjects);

      IdxalFragment.graphView.setData(data);

      IdxalFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + musteriYearSpinner.getSelectedItem().toString()});
      IdxalFragment.graphView.notifyDataSetChanged();
      IdxalFragment.idxalPrg.setVisibility(View.INVISIBLE);
      IdxalFragment.graphView.getAxisLeft().setStartAtZero(true);
      IdxalFragment.graphView.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.getLegend().setEnabled(true);

    }
  }


  public class CompareClientIxrac extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String client1, client2;
    String year;

    public CompareClientIxrac(String client1, String client2, String year) {
      this.client1 = client1;
      this.client2 = client2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IxracFragment.ixracPrg.setVisibility(View.VISIBLE);
      IxracFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", client1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("client", client2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlIxracClient, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlIxracClient, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = compareClientPostExecute(client1, client2, jsonObjects);

      IxracFragment.graphView.setData(data);

      IxracFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + musteriYearSpinner.getSelectedItem().toString()});
      IxracFragment.graphView.notifyDataSetChanged();

      IxracFragment.ixracPrg.setVisibility(View.INVISIBLE);
      IxracFragment.graphView.getAxisLeft().setStartAtZero(true);
      IxracFragment.graphView.setVisibility(View.VISIBLE);
      IxracFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareClientTranzit extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String client1, client2;
    String year;

    public CompareClientTranzit(String client1, String client2, String year) {
      this.client1 = client1;
      this.client2 = client2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      TranzitFragment.tranzitPrg.setVisibility(View.VISIBLE);
      TranzitFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", client1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("client", client2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlTranzitClient, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlTranzitClient, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = compareClientPostExecute(client1, client2, jsonObjects);

      TranzitFragment.graphView.setData(data);

      TranzitFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + musteriYearSpinner.getSelectedItem().toString()});
      TranzitFragment.graphView.notifyDataSetChanged();

      TranzitFragment.tranzitPrg.setVisibility(View.INVISIBLE);
      TranzitFragment.graphView.getAxisLeft().setStartAtZero(true);
      TranzitFragment.graphView.setVisibility(View.VISIBLE);
      TranzitFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareClientDaxili extends AsyncTask<String, String, ArrayList<JSONObject>> {

    String client1, client2;
    String year;

    public CompareClientDaxili(String client1, String client2, String year) {
      this.client1 = client1;
      this.client2 = client2;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      DaxiliFragment.daxiliPrg.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected ArrayList<JSONObject> doInBackground(String... strings) {
      ArrayList<JSONObject> qnqList = new ArrayList<>();
      JSONParser jParser = new JSONParser();
      JSONParser jParser2 = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      List<BasicNameValuePair> params2 = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", client1));
      params.add(new BasicNameValuePair("year", year));
      params2.add(new BasicNameValuePair("userId", userId_));
      params2.add(new BasicNameValuePair("client", client2));
      params2.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlDaxiliClient, params);
      JSONObject json2 = jParser2.getJSONFromUrl(urlDaxiliClient, params2);
      qnqList.add(json);
      qnqList.add(json2);
      return qnqList;
    }

    @Override
    protected void onPostExecute(ArrayList<JSONObject> jsonObjects) {
      super.onPostExecute(jsonObjects);
      LineData data = compareClientPostExecute(client1, client2, jsonObjects);

      DaxiliFragment.graphView.setData(data);

      DaxiliFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE}, new String[]{"İL: " + musteriYearSpinner.getSelectedItem().toString()});
      DaxiliFragment.graphView.notifyDataSetChanged();

      DaxiliFragment.daxiliPrg.setVisibility(View.INVISIBLE);
      DaxiliFragment.graphView.getAxisLeft().setStartAtZero(true);
      DaxiliFragment.graphView.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareGelirUmumi extends AsyncTask<String, String, JSONObject> {

    String qnq, client;
    String year;
    String clientType = "";

    public CompareGelirUmumi(String qnq, String client, String year) {
      this.qnq = qnq;
      this.client = client;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      AllFragment.allPrg.setVisibility(View.VISIBLE);
      AllFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      if (client.equals("Hamısı")) {
        clientType = "";
      } else {
        clientType = client;
      }
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", clientType));
      params.add(new BasicNameValuePair("year", year));
      params.add(new BasicNameValuePair("qnq", qnq));


      JSONObject json = jParser.getJSONFromUrl(urlUmumiGelir, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      LineData data = compareGelirPostExecute(qnq, client, jsonObject);

      AllFragment.graphView.setData(data);

      AllFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE, Color.WHITE}, new String[]{"MÜŞTƏRİ: " + gelirMusteriSpinner.getSelectedItem().toString(), "IL: " + gelirYearSpinner.getSelectedItem().toString()});
      AllFragment.graphView.notifyDataSetChanged();

      AllFragment.allPrg.setVisibility(View.INVISIBLE);
     // AllFragment.graphView.getAxisLeft().setStartAtZero(true);
      AllFragment.graphView.setVisibility(View.VISIBLE);
      AllFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareGelirIdxal extends AsyncTask<String, String, JSONObject> {

    String qnq, client;
    String year;
    String clientType = "";

    public CompareGelirIdxal(String qnq, String client, String year) {
      this.qnq = qnq;
      this.client = client;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IdxalFragment.idxalPrg.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      if (client.equals("Hamısı")) {
        clientType = "";
      } else {
        clientType = client;
      }
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", clientType));
      params.add(new BasicNameValuePair("year", year));
      params.add(new BasicNameValuePair("qnq", qnq));


      JSONObject json = jParser.getJSONFromUrl(urlIdxalGelir, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      LineData data = compareGelirPostExecute(qnq, client, jsonObject);

      IdxalFragment.graphView.setData(data);

      IdxalFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE, Color.WHITE}, new String[]{"MÜŞTƏRİ: " + gelirMusteriSpinner.getSelectedItem().toString(), "IL: " + gelirYearSpinner.getSelectedItem().toString()});
      IdxalFragment.graphView.notifyDataSetChanged();

      IdxalFragment.idxalPrg.setVisibility(View.INVISIBLE);
      //IdxalFragment.graphView.getAxisLeft().setStartAtZero(true);
      IdxalFragment.graphView.setVisibility(View.VISIBLE);
      IdxalFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareGelirIxrac extends AsyncTask<String, String, JSONObject> {

    String qnq, client;
    String year;
    String clientType = "";

    public CompareGelirIxrac(String qnq, String client, String year) {
      this.qnq = qnq;
      this.client = client;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      IxracFragment.ixracPrg.setVisibility(View.VISIBLE);
      IxracFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      if (client.equals("Hamısı")) {
        clientType = "";
      } else {
        clientType = client;
      }
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", clientType));
      params.add(new BasicNameValuePair("year", year));
      params.add(new BasicNameValuePair("qnq", qnq));


      JSONObject json = jParser.getJSONFromUrl(urlIxracGelir, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      LineData data = compareGelirPostExecute(qnq, client, jsonObject);

      IxracFragment.graphView.setData(data);

      IxracFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE, Color.WHITE}, new String[]{"MÜŞTƏRİ: " + gelirMusteriSpinner.getSelectedItem().toString(), "IL: " + gelirYearSpinner.getSelectedItem().toString()});
      IxracFragment.graphView.notifyDataSetChanged();

      IxracFragment.ixracPrg.setVisibility(View.INVISIBLE);
      //IxracFragment.graphView.getAxisLeft().setStartAtZero(true);
      IxracFragment.graphView.setVisibility(View.VISIBLE);
      IxracFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareGelirTranzit extends AsyncTask<String, String, JSONObject> {

    String qnq, client;
    String year;
    String clientType = "";

    public CompareGelirTranzit(String qnq, String client, String year) {
      this.qnq = qnq;
      this.client = client;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      TranzitFragment.tranzitPrg.setVisibility(View.VISIBLE);
      TranzitFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      if (client.equals("Hamısı")) {
        clientType = "";
      } else {
        clientType = client;
      }
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", clientType));
      params.add(new BasicNameValuePair("year", year));
      params.add(new BasicNameValuePair("qnq", qnq));


      JSONObject json = jParser.getJSONFromUrl(urlTranzitGelir, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      LineData data = compareGelirPostExecute(qnq, client, jsonObject);

      TranzitFragment.graphView.setData(data);

      TranzitFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE, Color.WHITE}, new String[]{"MÜŞTƏRİ: " + gelirMusteriSpinner.getSelectedItem().toString(), "IL: " + gelirYearSpinner.getSelectedItem().toString()});
      TranzitFragment.graphView.notifyDataSetChanged();

      TranzitFragment.tranzitPrg.setVisibility(View.INVISIBLE);
      //TranzitFragment.graphView.getAxisLeft().setStartAtZero(true);
      TranzitFragment.graphView.setVisibility(View.VISIBLE);
      TranzitFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public class CompareGelirDaxili extends AsyncTask<String, String, JSONObject> {

    String qnq, client;
    String year;
    String clientType = "";

    public CompareGelirDaxili(String qnq, String client, String year) {
      this.qnq = qnq;
      this.client = client;
      this.year = year;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      DaxiliFragment.daxiliPrg.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
      JSONParser jParser = new JSONParser();
      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      if (client.equals("Hamısı")) {
        clientType = "";
      } else {
        clientType = client;
      }
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("client", clientType));
      params.add(new BasicNameValuePair("year", year));
      params.add(new BasicNameValuePair("qnq", qnq));


      JSONObject json = jParser.getJSONFromUrl(urlDaxiliGelir, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      LineData data = compareGelirPostExecute(qnq, client, jsonObject);

      DaxiliFragment.graphView.setData(data);

      DaxiliFragment.graphView.getLegend().setExtra(new int[]{Color.WHITE, Color.WHITE}, new String[]{"MÜŞTƏRİ: " + gelirMusteriSpinner.getSelectedItem().toString(), "IL: " + gelirYearSpinner.getSelectedItem().toString()});
      DaxiliFragment.graphView.notifyDataSetChanged();

      DaxiliFragment.daxiliPrg.setVisibility(View.INVISIBLE);
      //DaxiliFragment.graphView.getAxisLeft().setStartAtZero(true);
      DaxiliFragment.graphView.setVisibility(View.VISIBLE);
      DaxiliFragment.graphView.getLegend().setEnabled(true);

    }
  }

  public LineData comparePostExecute(String qnq1, String qnq2, ArrayList<JSONObject> jsonObjects) {
    int[] values1 = new int[12];
    int[] values2 = new int[12];

    try {
      JSONArray dataArray1 = jsonObjects.get(0).getJSONArray("data");

      for (int i = 0; i < dataArray1.length(); i++) {
        JSONObject jsonObject1 = dataArray1.getJSONObject(i);
        String ton = jsonObject1.getString("Ton");
        values1[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
      }

      JSONArray dataArray2 = jsonObjects.get(1).getJSONArray("data");

      for (int i = 0; i < dataArray2.length(); i++) {
        JSONObject jsonObject2 = dataArray2.getJSONObject(i);
        String ton = jsonObject2.getString("Ton");
        values2[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
      }


      ArrayList<Entry> yValues1 = new ArrayList<>();

      yValues1.add(new Entry(0, values1[0]));
      yValues1.add(new Entry(1, values1[1]));
      yValues1.add(new Entry(2, values1[2]));
      yValues1.add(new Entry(3, values1[3]));
      yValues1.add(new Entry(4, values1[4]));
      yValues1.add(new Entry(5, values1[5]));
      yValues1.add(new Entry(6, values1[6]));
      yValues1.add(new Entry(7, values1[7]));
      yValues1.add(new Entry(8, values1[8]));
      yValues1.add(new Entry(9, values1[9]));
      yValues1.add(new Entry(10, values1[10]));
      yValues1.add(new Entry(11, values1[11]));

      LineDataSet set1 = new LineDataSet(yValues1, qnq1);
      set1.setColor(Color.parseColor("#ed7d31"));
      set1.setValueTextColor(Color.parseColor("#bfbfbf"));
      set1.setValueTextSize(9f);
      set1.setLineWidth(2f);
      set1.setCircleColor(Color.parseColor("#ed7d31"));
      set1.setCircleRadius(4f);
      set1.setCircleHoleRadius(2f);


      ArrayList<ILineDataSet> dataSets1 = new ArrayList<>();

      dataSets1.add(set1);

      ArrayList<Entry> yValues2 = new ArrayList<>();

      yValues2.add(new Entry(0, values2[0]));
      yValues2.add(new Entry(1, values2[1]));
      yValues2.add(new Entry(2, values2[2]));
      yValues2.add(new Entry(3, values2[3]));
      yValues2.add(new Entry(4, values2[4]));
      yValues2.add(new Entry(5, values2[5]));
      yValues2.add(new Entry(6, values2[6]));
      yValues2.add(new Entry(7, values2[7]));
      yValues2.add(new Entry(8, values2[8]));
      yValues2.add(new Entry(9, values2[9]));
      yValues2.add(new Entry(10, values2[10]));
      yValues2.add(new Entry(11, values2[11]));

      LineDataSet set2 = new LineDataSet(yValues2, qnq2);
      set2.setColor(Color.parseColor("#5b9bd5"));
      set2.setValueTextColor(Color.parseColor("#bfbfbf"));
      set2.setValueTextSize(9f);
      set2.setLineWidth(2f);
      set2.setCircleColor(Color.parseColor("#5b9bd5"));
      set2.setCircleRadius(4f);
      set2.setCircleHoleRadius(2f);

      dataSets1.add(set2);

      LineData data1 = new LineData(dataSets1);

      return data1;
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

  }

  public LineData compareClientPostExecute(String client1, String client2, ArrayList<JSONObject> jsonObjects) {
    int[] values1 = new int[12];
    int[] values2 = new int[12];

    try {
      JSONArray dataArray1 = jsonObjects.get(0).getJSONArray("data");

      for (int i = 0; i < dataArray1.length(); i++) {
        JSONObject jsonObject1 = dataArray1.getJSONObject(i);
        String ton = jsonObject1.getString("weight");
        values1[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
      }

      JSONArray dataArray2 = jsonObjects.get(1).getJSONArray("data");

      for (int i = 0; i < dataArray2.length(); i++) {
        JSONObject jsonObject2 = dataArray2.getJSONObject(i);
        String ton = jsonObject2.getString("weight");
        values2[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
      }


      ArrayList<Entry> yValues1 = new ArrayList<>();

      yValues1.add(new Entry(0, values1[0]));
      yValues1.add(new Entry(1, values1[1]));
      yValues1.add(new Entry(2, values1[2]));
      yValues1.add(new Entry(3, values1[3]));
      yValues1.add(new Entry(4, values1[4]));
      yValues1.add(new Entry(5, values1[5]));
      yValues1.add(new Entry(6, values1[6]));
      yValues1.add(new Entry(7, values1[7]));
      yValues1.add(new Entry(8, values1[8]));
      yValues1.add(new Entry(9, values1[9]));
      yValues1.add(new Entry(10, values1[10]));
      yValues1.add(new Entry(11, values1[11]));

      LineDataSet set1 = new LineDataSet(yValues1, client1);
      set1.setColor(Color.parseColor("#ed7d31"));
      set1.setValueTextColor(Color.parseColor("#bfbfbf"));
      set1.setValueTextSize(9f);
      set1.setLineWidth(2f);
      set1.setCircleColor(Color.parseColor("#ed7d31"));
      set1.setCircleRadius(4f);
      set1.setCircleHoleRadius(2f);


      ArrayList<ILineDataSet> dataSets1 = new ArrayList<>();

      dataSets1.add(set1);

      ArrayList<Entry> yValues2 = new ArrayList<>();

      yValues2.add(new Entry(0, values2[0]));
      yValues2.add(new Entry(1, values2[1]));
      yValues2.add(new Entry(2, values2[2]));
      yValues2.add(new Entry(3, values2[3]));
      yValues2.add(new Entry(4, values2[4]));
      yValues2.add(new Entry(5, values2[5]));
      yValues2.add(new Entry(6, values2[6]));
      yValues2.add(new Entry(7, values2[7]));
      yValues2.add(new Entry(8, values2[8]));
      yValues2.add(new Entry(9, values2[9]));
      yValues2.add(new Entry(10, values2[10]));
      yValues2.add(new Entry(11, values2[11]));

      LineDataSet set2 = new LineDataSet(yValues2, client2);
      set2.setColor(Color.parseColor("#5b9bd5"));
      set2.setValueTextColor(Color.parseColor("#bfbfbf"));
      set2.setValueTextSize(9f);
      set2.setLineWidth(2f);
      set2.setCircleColor(Color.parseColor("#5b9bd5"));
      set2.setCircleRadius(4f);
      set2.setCircleHoleRadius(2f);

      dataSets1.add(set2);

      LineData data1 = new LineData(dataSets1);

      return data1;
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
    }

  }

  public LineData compareGelirPostExecute(String qnq, String client, JSONObject jsonObject) {
    int[] values = new int[12];
    // int[] values2 = new int[12];
    String value = "";
    if (client.equals("Hamısı")) {
      value = "";
    } else {
      value = client;
    }

    try {
      JSONArray dataArray1 = jsonObject.getJSONArray("data");

      for (int i = 0; i < dataArray1.length(); i++) {
        JSONObject jsonObject1 = dataArray1.getJSONObject(i);
        String ton = jsonObject1.getString("Gelir");
        values[i] = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(ton))));
      }


      ArrayList<Entry> yValues1 = new ArrayList<>();

      yValues1.add(new Entry(0, values[0]));
      yValues1.add(new Entry(1, values[1]));
      yValues1.add(new Entry(2, values[2]));
      yValues1.add(new Entry(3, values[3]));
      yValues1.add(new Entry(4, values[4]));
      yValues1.add(new Entry(5, values[5]));
      yValues1.add(new Entry(6, values[6]));
      yValues1.add(new Entry(7, values[7]));
      yValues1.add(new Entry(8, values[8]));
      yValues1.add(new Entry(9, values[9]));
      yValues1.add(new Entry(10, values[10]));
      yValues1.add(new Entry(11, values[11]));

      LineDataSet set1 = new LineDataSet(yValues1, qnq);
      set1.setColor(Color.parseColor("#5b9bd5"));
      set1.setValueTextColor(Color.parseColor("#bfbfbf"));
      set1.setValueTextSize(9f);
      set1.setLineWidth(2f);
      set1.setCircleColor(Color.parseColor("#5b9bd5"));
      set1.setCircleRadius(4f);
      set1.setCircleHoleRadius(2f);


      ArrayList<ILineDataSet> dataSets1 = new ArrayList<>();
      dataSets1.add(set1);
      LineData data1 = new LineData(dataSets1);

      return data1;
    } catch (JSONException e) {
      e.printStackTrace();
      return null;
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
          Log.i("Yatir", "Yatir");
          Intent intent = new Intent(getActivity(), navi_menu3.class);
          intent.putExtra("sess_user", user_);
          intent.putExtra("sess_pass", pass_);
          intent.putExtra("sess_name", name_);
          intent.putExtra("sess_id", userId_);
          intent.putStringArrayListExtra("menular", Menular.menular);
          getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
          startActivity(intent);
          getActivity().finish();
          return true;
        }
        return false;
      }
    });

  }


  public void onBackPressed(EditText editText) {
    editText.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if (keyEvent.getAction() == KeyEvent.ACTION_UP && i == KeyEvent.KEYCODE_BACK) {

          final Intent intent = new Intent(getActivity(), navi_menu3.class);
          intent.putExtra("sess_user", user_);
          intent.putExtra("sess_pass", pass_);
          intent.putExtra("sess_name", name_);
          intent.putExtra("sess_id", userId_);
          intent.putStringArrayListExtra("menular", Menular.menular);


          if (!qnqQnqEdt1.getText().toString().equals("") || !qnqQnqEdt2.getText().toString().equals("")
            || !gelirQnqEdt.getText().toString().equals("") || !ilQnqEdt.getText().toString().equals("")
            || !qnqEditText.getText().toString().equals("")) {

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Qrafik-dən çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
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

          } else {
            startActivity(intent);
            getActivity().finish();

          }
          return true;

        }

        return false;
      }
    });
  }

  public class getQnqsFromDb extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String... strings) {

      Cursor cursor = sqLiteDatabase2.query("AllQnqs", new String[]{"qnq_name"}, null, null, null, null, null);
      if (cursor.moveToFirst()) {
        do {
          qnqCodes.add(cursor.getString(0));
          onlyCodes.add(cursor.getString(0).substring(0, 8));

        } while (cursor.moveToNext());

      }
      return null;
    }
  }

  public void animation(final View view1, final RadioButton view2) {
    view1.setVisibility(View.VISIBLE);
    view2.setBackgroundColor(Color.parseColor("#F5AA40"));
    view2.setTextColor(Color.WHITE);
    android.os.Handler handler = new android.os.Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        TranslateAnimation animate = new TranslateAnimation(0, 0, -view1.getHeight(), 0);
        animate.setDuration(500);
        view1.startAnimation(animate);
      }
    }, 1);

  }

}
