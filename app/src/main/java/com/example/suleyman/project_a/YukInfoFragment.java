package com.example.suleyman.project_a;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.suleyman.project_a.Adapter.FirmAdapter;
import com.example.suleyman.project_a.Adapter.MarsrutAdapter;
import com.example.suleyman.project_a.Adapter.NewYukInfoAdapter;
import com.example.suleyman.project_a.Adapter.QnqAdapter;
import com.example.suleyman.project_a.Adapter.YukInfoAdapter;
import com.example.suleyman.project_a.Common.Menular;
import com.example.suleyman.project_a.Model.QnqModel;
import com.example.suleyman.project_a.Sqlite.QnqDbHelper;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Art Servis on 12/2/2017.
 */

public class YukInfoFragment extends Fragment implements GestureDetector.OnGestureListener {

  Spinner spinner, yearSpinner;
  ArrayList<String> Marsrutlar;
  ArrayList<String> MarsrutVagon, MarsrutKont;
  ArrayList<String> aylar, vasiteler, vasiteVaqon, vasiteCont, musteriler, musteriDeyerler;
  ArrayList<String> ayDeyerler, vasiteDeyerler, emrler;
  YukInfoAdapter adapter;
  ListView marsrutList, aylarList, dasimaList, clientList, emrList, sirketList, rubList;
  TextView marsrutTxt, rubTxt, aylarTxt, dasimaTxt, musteriTxt, emrTxt, firstRubTxt, secondRubTxt, thirdRubTxt, fourthRubTxt;
  RelativeLayout rubLyt, searchLyt;
  RelativeLayout secondLyt, thirdLyt, forthLyt, fifthLyt, sixthLyt, seventhLyt, firstLyt, eksSpaceLyt;
  RelativeLayout masrutLyt, ayLyt, searchLyt2, relativeLayoutmain, umumLytLarge, sirketLyt, musteriLyt;
  ScrollView scrollView;
  TextView totalTxt, totalVaqon, totalKont, sirketTxt;
  EditText qnqEditText;
  ImageView qnqSearchImg;
  ProgressBar firmBar, marsrutBar, rubBar, aylarBar, dasimaBar, musteriBar;
  FrameLayout root1;
  String user_ = "";
  String pass_ = "";
  String name_ = "";
  String userId_ = "";
  boolean comp = false;
  boolean t = false;
  String selectedItem;
  int index;
  String urlQnq = "https://ady.express/PLan.asmx/all_qnqs";
  String urlFirm = "https://ady.express/PLan.asmx/sirket_adi";
  String urlFirmQrup = "https://ady.express/PLan.asmx/sirket_qrup";
  String urlMarsrutAdi = "https://ady.express/PLan.asmx/marsrut_adi";
  String urlMarsrutQrup = "https://ady.express/PLan.asmx/marsrut_qrup";
  String urlAylarAdi = "https://ady.express/PLan.asmx/aylar_adi";
  String urlAylarQrup = "https://ady.express/PLan.asmx/aylar_qrup";
  String urlDasimaAdi = "https://ady.express/PLan.asmx/dasima_adi";
  String urlDasimaQrup = "https://ady.express/PLan.asmx/dasima_qrup";
  String urlMusteriAdi = "https://ady.express/PLan.asmx/musteri_adi";
  String urlMusteriQrup = "https://ady.express/PLan.asmx/musteri_qrup";
  String urlRublerAdi = "https://ady.express/PLan.asmx/rubler_adi";
  String urlRublerQrup = "https://ady.express/PLan.asmx/rubler_qrup";
  final ArrayList<String> qnqCodes = new ArrayList<String>();
  final ArrayList<String> onlyCodes = new ArrayList<String>();
  String year;
  UserSessionManager sessionManager;
  GestureDetector detector;

  QnqDbHelper qnqDbHelper;
  SQLiteDatabase sqLiteDatabase, sqLiteDatabase2;

  StringRequest stringRequest;
  RequestQueue requestQueue;
  ArrayList<QnqModel> qnqModelList = new ArrayList<>();


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
  public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_yukinfo_test2, container, false);

    spinner = (Spinner) view.findViewById(R.id.infoType);
    ArrayAdapter<CharSequence> SpinerAdapter;
    ArrayAdapter<CharSequence> YearAdapter;
    String[] arrayItems_month = {"Adi", "Qrup"};
    marsrutList = (ListView) view.findViewById(R.id.masrutList);
    marsrutTxt = (TextView) view.findViewById(R.id.masrutTxt);
    rubTxt = (TextView) view.findViewById(R.id.rubTxt);
    aylarTxt = (TextView) view.findViewById(R.id.aylarTxt);
    dasimaTxt = (TextView) view.findViewById(R.id.dasimaTxt);
    musteriTxt = (TextView) view.findViewById(R.id.clientTxt);
    emrTxt = (TextView) view.findViewById(R.id.emrTxt);
    dasimaList = (ListView) view.findViewById(R.id.dasimaList);
    aylarList = (ListView) view.findViewById(R.id.aylarList);
    clientList = (ListView) view.findViewById(R.id.clientList);
    emrList = (ListView) view.findViewById(R.id.emrList);
    sirketList = (ListView) view.findViewById(R.id.sirketList);
    searchLyt = (RelativeLayout) view.findViewById(R.id.searchLyt);
    firstRubTxt = (TextView) view.findViewById(R.id.firstRubTxt);
    secondRubTxt = (TextView) view.findViewById(R.id.secondRubTxt);
    thirdRubTxt = (TextView) view.findViewById(R.id.thirdRubTxt);
    fourthRubTxt = (TextView) view.findViewById(R.id.forthRubTxt);
    // totalTxt = (TextView) view.findViewById(R.id.totalTxt);
    yearSpinner = (Spinner) view.findViewById(R.id.year);
    //eksSpaceLyt = (RelativeLayout) view.findViewById(R.id.ekspSpaceLyt);
    firstLyt = (RelativeLayout) view.findViewById(R.id.firstLyt);
    secondLyt = (RelativeLayout) view.findViewById(R.id.secondLyt);
    thirdLyt = (RelativeLayout) view.findViewById(R.id.thirdLyt);
    forthLyt = (RelativeLayout) view.findViewById(R.id.forthLyt);
    fifthLyt = (RelativeLayout) view.findViewById(R.id.fifthLyt);
    sixthLyt = (RelativeLayout) view.findViewById(R.id.sixthLyt);
    seventhLyt = (RelativeLayout) view.findViewById(R.id.seventhLyt);
    firmBar = (ProgressBar) view.findViewById(R.id.firmBar);
    marsrutBar = (ProgressBar) view.findViewById(R.id.marsrutBar);
    rubList = (ListView) view.findViewById(R.id.rubList);
    rubBar = (ProgressBar) view.findViewById(R.id.rubBar);
    aylarBar = (ProgressBar) view.findViewById(R.id.aylarBar);
    dasimaBar = (ProgressBar) view.findViewById(R.id.dasimaBar);
    musteriBar = (ProgressBar) view.findViewById(R.id.musteriBar);
    root1 = (FrameLayout) view.findViewById(R.id.root1);
    masrutLyt = (RelativeLayout) view.findViewById(R.id.masrutLyt);
    ayLyt = (RelativeLayout) view.findViewById(R.id.ayLyt);
    scrollView = (ScrollView) view.findViewById(R.id.infoScroll);
    searchLyt2 = (RelativeLayout) view.findViewById(R.id.searchLyt2);
    relativeLayoutmain = (RelativeLayout) view.findViewById(R.id.relativeLayoutmain);
    umumLytLarge = (RelativeLayout) view.findViewById(R.id.umumLytLarge);
    totalKont = (TextView) view.findViewById(R.id.kont2);
    totalVaqon = (TextView) view.findViewById(R.id.vaqon2);
    sirketLyt = (RelativeLayout) view.findViewById(R.id.sirketLyt);
    sirketTxt = (TextView) view.findViewById(R.id.ekspeditorTxt);
    musteriLyt = (RelativeLayout) view.findViewById(R.id.musteriLyt);


    qnqDbHelper = new QnqDbHelper(getContext());
    sqLiteDatabase = qnqDbHelper.getReadableDatabase();
    sqLiteDatabase2 = qnqDbHelper.getWritableDatabase();

    detector = new GestureDetector(getContext(), this);


    sessionManager = new UserSessionManager(getActivity());

    Menular.tabs = new ArrayList<>(sessionManager.getTabs());

    setupParent(root1);

    if (!Menular.tabs.contains("Marsrut")) {
      secondLyt.setVisibility(View.GONE);
    }
    if (!Menular.tabs.contains("Rubluk")) {
      thirdLyt.setVisibility(View.GONE);
    }
    if (!Menular.tabs.contains("Ayliq")) {
      forthLyt.setVisibility(View.GONE);
    }
    if (!Menular.tabs.contains("Dasima vasiteleri")) {
      fifthLyt.setVisibility(View.GONE);
    }
    if (!Menular.tabs.contains("Musteriler")) {
      sixthLyt.setVisibility(View.GONE);
    }
    if (!Menular.tabs.contains("Emrler")) {
      seventhLyt.setVisibility(View.GONE);
    }

    navi_menu3.yukInfoQnq = "";

    new GetAllQnqsFromDb().execute();

    rubLyt = (RelativeLayout) view.findViewById(R.id.rubLyt);

    qnqEditText = (EditText) view.findViewById(R.id.qnqEditText);
    qnqSearchImg = (ImageView) view.findViewById(R.id.qnqSearchImg);


    qnqEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        navi_menu3.yukInfoQnq = charSequence.toString();
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });

    emrTxt.setEnabled(false);

    final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, getActivity().getResources().getDisplayMetrics());

    SpinerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.array_name, R.layout.spinner_textview);

    SpinerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

    spinner.setAdapter(SpinerAdapter);

    YearAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.array_year, R.layout.spinner_textview);

    YearAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

    yearSpinner.setAdapter(YearAdapter);

    yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        year = adapterView.getItemAtPosition(i).toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> adapterView) {

      }
    });

    final Animation bottomUp = AnimationUtils.loadAnimation(getActivity(), R.anim.bottom_up);

    //new JsonQnq().execute();


    onBackPressed(qnqEditText);


    scrollView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        if (umumLytLarge.getVisibility() == View.GONE) {
          detector.onTouchEvent(motionEvent);
          return true;
        }
        return false;
      }
    });

    searchLyt2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        qnqEditText.setVisibility(View.VISIBLE);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        int px = Math.round(180 * displayMetrics.density);

        scrollView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, relativeLayoutmain.getHeight() - px));

        ObjectAnimator animator = ObjectAnimator.ofFloat(scrollView, "y", px);
        animator.setDuration(500);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator);

        // Toast.makeText(getContext(), String.valueOf(params1.height), Toast.LENGTH_SHORT).show();


        umumLytLarge.setVisibility(View.GONE);
        qnqSearchImg.setVisibility(View.VISIBLE);
        animatorSet.start();

      }
    });

    searchLyt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {




        final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, getActivity().getResources().getDisplayMetrics());


        if (qnqEditText.getText().toString().equals("")) {
          Toast.makeText(getContext(), "QNQ-ni daxil edin", Toast.LENGTH_SHORT).show();
        } else {
          if (yearSpinner.getSelectedItemPosition() == 0) {
            Toast.makeText(getContext(), "İli seçin", Toast.LENGTH_SHORT).show();
          } else {


            qnqEditText.setVisibility(View.GONE);

            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

            int px = Math.round(60 * displayMetrics.density);

            scrollView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, relativeLayoutmain.getHeight() - px));

            ObjectAnimator animator = ObjectAnimator.ofFloat(scrollView, "y", px);
            animator.setDuration(500);
            final AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(animator);

            // Toast.makeText(getContext(), String.valueOf(params1.height), Toast.LENGTH_SHORT).show();


            umumLytLarge.setVisibility(View.VISIBLE);
            qnqSearchImg.setVisibility(View.GONE);


            new Handler().postDelayed(new Runnable() {

              @Override
              public void run() {

                animatorSet.start();
              }
            },10);


            if (qnqEditText.getText().toString().length() < 8) {

              FirmTaskAdi firmTask = new FirmTaskAdi(qnqEditText.getText().toString());
              firmTask.execute();
              MarsrutTaskAdi marsrutTaskAdi = new MarsrutTaskAdi(qnqEditText.getText().toString());
              marsrutTaskAdi.execute();
              AylarAdi aylarAdi = new AylarAdi(qnqEditText.getText().toString());
              aylarAdi.execute();
              DasimaAdi dasimaAdi = new DasimaAdi(qnqEditText.getText().toString());
              dasimaAdi.execute();
              MusteriAdi musteriAdi = new MusteriAdi(qnqEditText.getText().toString());
              musteriAdi.execute();
              RublerAdi rublerAdi = new RublerAdi(qnqEditText.getText().toString());
              rublerAdi.execute();
            } else {
              if (spinner.getSelectedItemPosition() == 0) {

                FirmTaskAdi firmTask = new FirmTaskAdi(qnqEditText.getText().toString().substring(0, 8));
                firmTask.execute();
                MarsrutTaskAdi marsrutTaskAdi = new MarsrutTaskAdi(qnqEditText.getText().toString().substring(0, 8));
                marsrutTaskAdi.execute();
                AylarAdi aylarAdi = new AylarAdi(qnqEditText.getText().toString().substring(0, 8));
                aylarAdi.execute();
                DasimaAdi dasimaAdi = new DasimaAdi(qnqEditText.getText().toString().substring(0, 8));
                dasimaAdi.execute();
                MusteriAdi musteriAdi = new MusteriAdi(qnqEditText.getText().toString().substring(0, 8));
                musteriAdi.execute();
                RublerAdi rublerAdi = new RublerAdi(qnqEditText.getText().toString().substring(0, 8));
                rublerAdi.execute();
              } else {
                FirmTaskAdi firmTask = new FirmTaskAdi(qnqEditText.getText().toString().substring(0, 4));
                firmTask.execute();
                MarsrutTaskAdi marsrutTaskAdi = new MarsrutTaskAdi(qnqEditText.getText().toString().substring(0, 4));
                marsrutTaskAdi.execute();
                AylarAdi aylarAdi = new AylarAdi(qnqEditText.getText().toString().substring(0, 4));
                aylarAdi.execute();
                DasimaAdi dasimaAdi = new DasimaAdi(qnqEditText.getText().toString().substring(0, 4));
                dasimaAdi.execute();
                MusteriAdi musteriAdi = new MusteriAdi(qnqEditText.getText().toString().substring(0, 4));
                musteriAdi.execute();
                RublerAdi rublerAdi = new RublerAdi(qnqEditText.getText().toString().substring(0, 4));
                rublerAdi.execute();
              }

            }
            t = true;
          }
        }

      }
    });
    qnqSearchImg.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        searchDialog(qnqEditText);

      }
    });


    sirketTxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (t) {
          if (sirketLyt.getVisibility() == View.GONE) {
            if (sirketList.getAdapter() != null) {
              sirketLyt.setVisibility(View.VISIBLE);
              sirketLyt.startAnimation(bottomUp);
            } else {
              firmBar.setVisibility(View.VISIBLE);
              sirketLyt.setVisibility(View.VISIBLE);
              sirketLyt.startAnimation(bottomUp);
            }
          }
          /////////////////////////////////////////////////////////////////////////
          else {
            sirketLyt.setVisibility(View.GONE);
            firmBar.setVisibility(View.INVISIBLE);
          }
        }

      }
    });


    marsrutTxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        //////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////

        if (t) {
          if (masrutLyt.getVisibility() == View.GONE) {
            if (marsrutList.getAdapter() != null) {
              masrutLyt.setVisibility(View.VISIBLE);
              masrutLyt.startAnimation(bottomUp);
            } else {
              marsrutBar.setVisibility(View.VISIBLE);
              masrutLyt.setVisibility(View.VISIBLE);
              masrutLyt.startAnimation(bottomUp);
            }
          }
          /////////////////////////////////////////////////////////////////////////
          else {
            masrutLyt.setVisibility(View.GONE);
            marsrutBar.setVisibility(View.INVISIBLE);
          }
        }

      }
    });


    rubTxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (t) {
          if (rubLyt.getVisibility() == View.GONE) {
            if (rubList.getAdapter() != null) {
              rubLyt.setVisibility(View.VISIBLE);
              rubLyt.startAnimation(bottomUp);
            } else {
              rubBar.setVisibility(View.VISIBLE);
              rubLyt.setVisibility(View.VISIBLE);
              rubLyt.startAnimation(bottomUp);
            }
          } else {
            rubLyt.setVisibility(View.GONE);
            rubBar.setVisibility(View.INVISIBLE);
          }
        }
      }
    });

    aylarTxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (t) {
          if (ayLyt.getVisibility() == View.GONE) {
            if (aylarList.getAdapter() != null) {
              ayLyt.setVisibility(View.VISIBLE);
              aylarList.startAnimation(bottomUp);
            } else {
              aylarBar.setVisibility(View.VISIBLE);
              ayLyt.setVisibility(View.VISIBLE);
              ayLyt.startAnimation(bottomUp);
            }
          } else {
            ayLyt.setVisibility(View.GONE);
            aylarBar.setVisibility(View.INVISIBLE);
          }
        }

      }
    });

    dasimaTxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (t) {
          if (dasimaList.getVisibility() == View.GONE) {
            if (dasimaList.getAdapter() != null)

            {
              dasimaList.setVisibility(View.VISIBLE);
              dasimaList.startAnimation(bottomUp);
            } else {
              dasimaBar.setVisibility(View.VISIBLE);
              dasimaList.setVisibility(View.VISIBLE);
              dasimaList.startAnimation(bottomUp);
            }
          } else {
            dasimaList.setVisibility(View.GONE);
            dasimaBar.setVisibility(View.INVISIBLE);
          }
        }

      }
    });

    musteriTxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        if (t) {
          if (musteriLyt.getVisibility() == View.GONE) {
            if (clientList.getAdapter() != null) {
              musteriLyt.setVisibility(View.VISIBLE);
              musteriLyt.startAnimation(bottomUp);
            } else {
              musteriBar.setVisibility(View.VISIBLE);
              musteriLyt.setVisibility(View.VISIBLE);
              musteriLyt.startAnimation(bottomUp);
            }
          } else {
            musteriLyt.setVisibility(View.GONE);
            musteriBar.setVisibility(View.INVISIBLE);
          }
        }
      }
    });

    emrTxt.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        emrler = new ArrayList<String>();
        emrler.add("1 nomreli emr");
        emrler.add("2 nomreli emr");
        emrler.add("3 nomreli emr");
        emrler.add("4 nomreli emr");
        final float pixels2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 41, getActivity().getResources().getDisplayMetrics());

        ViewGroup.LayoutParams params = emrList.getLayoutParams();
        params.height = (int) (pixels2 * emrler.size());
        emrList.setLayoutParams(params);
        emrList.requestLayout();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.emr_listview_item, R.id.text, emrler);
        emrList.setAdapter(adapter);

        if (emrList.getVisibility() == View.GONE) {
          emrList.setVisibility(View.VISIBLE);
          emrList.startAnimation(bottomUp);
        } else {
          emrList.setVisibility(View.GONE);
        }

      }
    });


    return view;

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
    if (v1 > 0) {

      qnqEditText.setVisibility(View.GONE);
      DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

      int px = Math.round(60 * displayMetrics.density);

      scrollView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, relativeLayoutmain.getHeight()-px));

      ObjectAnimator animator = ObjectAnimator.ofFloat(scrollView, "y", px);
      animator.setDuration(500);
      final AnimatorSet animatorSet = new AnimatorSet();
      animatorSet.playTogether(animator);

      umumLytLarge.setVisibility(View.VISIBLE);
      qnqSearchImg.setVisibility(View.GONE);

      new Handler().postDelayed(new Runnable() {

        @Override
        public void run() {

          animatorSet.start();
        }
      },10);


      return true;
    }
    return false;
  }

  @Override
  public void onLongPress(MotionEvent motionEvent) {

  }

  @Override
  public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
    return false;
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

      try {
        if (jsonObject != null) {
          JSONArray dataArray = jsonObject.getJSONArray("data");
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
            }
          }
        }
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }

  public class FirmTaskAdi extends AsyncTask<String, String, JSONObject> {

    String qnq;

    public FirmTaskAdi(String qnq) {
      this.qnq = qnq;
    }


    @Override
    protected void onPreExecute() {
      super.onPreExecute();

      totalKont.setText("");
      totalVaqon.setText("");

      if (sirketLyt.getVisibility() == View.VISIBLE) {
        firmBar.setVisibility(View.VISIBLE);
        sirketList.setAdapter(null);
      }
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlFirm, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      if (getActivity() != null) {
        JSONArray dataArray = null;
        vasiteler = new ArrayList<>();
        vasiteDeyerler = new ArrayList<>();
        vasiteCont = new ArrayList<>();
        int totalTonnaj = 0;
        int totalVaqonCount = 0;
        int totalKontCount = 0;
        try {
          if (jsonObject != null) {
            dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
              JSONObject jsonObject1 = dataArray.getJSONObject(i);
              String firmName = jsonObject1.getString("FIRM_NAME");
              String firmValue = jsonObject1.getString("TONNAJ");
              String firmVagonCount = jsonObject1.getString("VAGON_COUNT");
              String firmKontCount = jsonObject1.getString("CONT_COUNT");
             /* if ((!firmName.equals("") && (firmName.equals("ADY EXPRESS") || firmName.equals("Trans-Torq") || firmName.equals("Təyin edilməyib")) && (year.equals("2016") || year.equals("2017")))
                || (!firmName.equals("") && (firmName.equals("ADY EXPRESS") || firmName.equals("Təyin edilməyib")) && year.equals("2018"))) {*/
                vasiteler.add(firmName);
                vasiteDeyerler.add(changeFormat(firmValue) + "/" + firmVagonCount);
                vasiteCont.add(firmKontCount);


                totalKontCount = totalKontCount + Integer.parseInt(firmKontCount);
                totalVaqonCount = totalVaqonCount + Integer.parseInt(firmVagonCount);
                totalTonnaj = totalTonnaj + Integer.parseInt(firmValue);
            //  }

            }

            totalVaqon.setText(changeFormat(String.valueOf(totalTonnaj)) + " ton / " + changeFormat(String.valueOf(totalVaqonCount)) + " ədəd");
            totalKont.setText(changeFormat(String.valueOf(totalKontCount)) + " ədəd");

            final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 33, getActivity().getResources().getDisplayMetrics());
            ViewGroup.LayoutParams params2 = sirketList.getLayoutParams();
            params2.height = (int) (pixels * vasiteler.size());
            sirketList.setLayoutParams(params2);
            sirketList.requestLayout();

            NewYukInfoAdapter adapter2 = new NewYukInfoAdapter(getActivity(), R.layout.yukinfo_list_item, vasiteler, vasiteDeyerler, vasiteCont);
            sirketList.setAdapter(adapter2);
            firmBar.setVisibility(View.INVISIBLE);
            sirketTxt.setEnabled(true);
          } else {
            Toast.makeText(getActivity(), "Seçilmiş fitre uyğun heç bir nəticə tapılmadı", Toast.LENGTH_SHORT).show();
            sirketLyt.setVisibility(View.GONE);
            sirketTxt.setEnabled(false);
            totalKont.setText("0 ədəd");
            totalVaqon.setText("0 ton / 0 ədəd");
          }
          firmBar.setVisibility(View.INVISIBLE);
        } catch (JSONException e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  public class MarsrutTaskAdi extends AsyncTask<String, String, JSONObject> {

    String qnq;

    public MarsrutTaskAdi(String qnq) {
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();

      if (masrutLyt.getVisibility() == View.VISIBLE) {
        marsrutBar.setVisibility(View.VISIBLE);
        marsrutList.setAdapter(null);
      }
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlMarsrutAdi, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      if (getActivity() != null) {
        JSONArray dataArray = null;
        Marsrutlar = new ArrayList<>();
        MarsrutVagon = new ArrayList<>();
        MarsrutKont = new ArrayList<>();
        // int total = 0;
        try {
          if (jsonObject != null) {
            dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
              JSONObject jsonObject1 = dataArray.getJSONObject(i);
              String firmName1 = jsonObject1.getString("birinci");
              String firmName2 = jsonObject1.getString("ikinci");
              String firmValue = jsonObject1.getString("TONNAJ");
              String marsrutVagon = jsonObject1.getString("VAGON_COUNT");
              String marsrutKont = jsonObject1.getString("CONT_COUNT");
              Marsrutlar.add(firmName1 + "--" + firmName2);
              MarsrutVagon.add(changeFormat(firmValue) + "/" + marsrutVagon);
              MarsrutKont.add(marsrutKont);
              //total = total + Integer.parseInt(firmValue);

            }

            //totalTxt.setText(changeFormat(String.valueOf(total)) + " ton");
            final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, getActivity().getResources().getDisplayMetrics());

            ViewGroup.LayoutParams params = marsrutList.getLayoutParams();
            params.height = (int) (pixels * MarsrutVagon.size());
            marsrutList.setLayoutParams(params);
            marsrutList.requestLayout();

            NewYukInfoAdapter adapter = new NewYukInfoAdapter(getActivity(), R.layout.yukinfo_list_item, Marsrutlar, MarsrutVagon, MarsrutKont);

            marsrutList.setAdapter(adapter);
            marsrutTxt.setEnabled(true);

            comp = true;

            marsrutBar.setVisibility(View.INVISIBLE);
          } else {
            masrutLyt.setVisibility(View.GONE);
            marsrutTxt.setEnabled(false);
          }
          marsrutBar.setVisibility(View.INVISIBLE);

        } catch (JSONException e1) {
          e1.printStackTrace();
        }
      }
    }
  }


  public class AylarAdi extends AsyncTask<String, String, JSONObject> {

    String qnq;

    public AylarAdi(String qnq) {
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      if (ayLyt.getVisibility() == View.VISIBLE) {
        aylarBar.setVisibility(View.VISIBLE);
        aylarList.setAdapter(null);
      }

    }

    @Override
    protected JSONObject doInBackground(String... strings) {

      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlAylarAdi, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      if (getActivity() != null) {
        JSONArray dataArray = null;
        aylar = new ArrayList<>();
        ayDeyerler = new ArrayList<>();
        ArrayList<String> ayCont = new ArrayList<>();
        try {
          if (jsonObject != null) {
            dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
              JSONObject jsonObject1 = dataArray.getJSONObject(i);
              String monthName = jsonObject1.getString("MONTH");
              String monthValue = jsonObject1.getString("TONNAJ");
              String wagonCount = jsonObject1.getString("VAGON_COUNT");
              String contCount = jsonObject1.getString("CONT_COUNT");
             /* if (!monthValue.equals("")) {
                monthValue = "0";
                aylar.add(monthName);
                ayDeyerler.add(changeFormat(monthValue) + "/" + wagonCount);
                ayCont.add(contCount);
              }*/

             if(monthValue.equals("")){
               monthValue = "0";
             }
              aylar.add(monthName);
              ayDeyerler.add(changeFormat(monthValue) + "/" + wagonCount);
              ayCont.add(contCount);


            }
            final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, getActivity().getResources().getDisplayMetrics());

            ViewGroup.LayoutParams params2 = aylarList.getLayoutParams();
            params2.height = (int) (pixels * aylar.size());
            aylarList.setLayoutParams(params2);
            aylarList.requestLayout();

            NewYukInfoAdapter adapter = new NewYukInfoAdapter(getActivity(), R.layout.yukinfo_list_item, aylar, ayDeyerler, ayCont);
            aylarList.setAdapter(adapter);
            aylarTxt.setEnabled(true);
            aylarBar.setVisibility(View.INVISIBLE);
          } else {
            ayLyt.setVisibility(View.GONE);
            aylarBar.setVisibility(View.INVISIBLE);
            aylarTxt.setEnabled(false);
          }
        } catch (JSONException e1) {
          e1.printStackTrace();
        }
      }
    }

  }


  public class DasimaAdi extends AsyncTask<String, String, JSONObject> {

    String qnq;

    public DasimaAdi(String qnq) {
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();

      if (dasimaList.getVisibility() == View.VISIBLE) {
        dasimaBar.setVisibility(View.VISIBLE);
        dasimaList.setAdapter(null);
      }
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlDasimaAdi, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      if (getActivity() != null) {
        JSONArray dataArray = null;
        vasiteler = new ArrayList<>();
        vasiteDeyerler = new ArrayList<>();
        try {
          if (jsonObject != null) {
            dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
              JSONObject jsonObject1 = dataArray.getJSONObject(i);
              String name = jsonObject1.getString("RT_NAME");
              String count = jsonObject1.getString("VAGON_COUNT");
              String weight = jsonObject1.getString("TONNAJ");


              if (weight.equals("K")) {
                vasiteler.add(name);
                vasiteDeyerler.add(count + " ədəd");
              } else {
                vasiteler.add(name);
                vasiteDeyerler.add(changeFormat(weight) + " ton/" + count + " ədəd");
              }

            }
            final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, getActivity().getResources().getDisplayMetrics());

            ViewGroup.LayoutParams params2 = dasimaList.getLayoutParams();
            params2.height = (int) (pixels * vasiteler.size());
            dasimaList.setLayoutParams(params2);
            dasimaList.requestLayout();

            adapter = new YukInfoAdapter(getActivity(), R.layout.yukinfo_listview_item, vasiteler, vasiteDeyerler);
            dasimaList.setAdapter(adapter);
            dasimaBar.setVisibility(View.INVISIBLE);
            dasimaTxt.setEnabled(true);
          } else {
            dasimaList.setVisibility(View.GONE);
            dasimaBar.setVisibility(View.INVISIBLE);
            dasimaTxt.setEnabled(false);
          }
        } catch (JSONException e1) {
          e1.printStackTrace();
        }
      }
    }
  }


  public class MusteriAdi extends AsyncTask<String, String, JSONObject> {

    String qnq;

    public MusteriAdi(String qnq) {
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      if (musteriLyt.getVisibility() == View.VISIBLE) {
        musteriBar.setVisibility(View.VISIBLE);
        clientList.setAdapter(null);
      }
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlMusteriAdi, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      if (getActivity() != null) {
        JSONArray dataArray = null;
        musteriler = new ArrayList<>();
        musteriDeyerler = new ArrayList<>();
        ArrayList<String> musteriKont = new ArrayList<>();
        try {
          if (jsonObject != null) {
            dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
              JSONObject jsonObject1 = dataArray.getJSONObject(i);
              String name = jsonObject1.getString("CLIENT");
              String weight = jsonObject1.getString("TONNAJ");
              String vaqonCount = jsonObject1.getString("VAGON_COUNT");
              String kontCount = jsonObject1.getString("CONT_COUNT");

                musteriler.add(name);
                musteriDeyerler.add(changeFormat(weight) + " /" + changeFormat(vaqonCount));
                musteriKont.add(changeFormat(kontCount));
            }

            Log.i("kkkk", musteriKont.toString());
            final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, getActivity().getResources().getDisplayMetrics());

            ViewGroup.LayoutParams params2 = clientList.getLayoutParams();
            params2.height = (int) (pixels * musteriler.size());
            clientList.setLayoutParams(params2);
            clientList.requestLayout();

            NewYukInfoAdapter adapter = new NewYukInfoAdapter(getActivity(), R.layout.yukinfo_list_item, musteriler, musteriDeyerler, musteriKont);
            clientList.setAdapter(adapter);
            musteriBar.setVisibility(View.INVISIBLE);
            musteriTxt.setEnabled(true);
          } else {
            musteriLyt.setVisibility(View.GONE);
            musteriBar.setVisibility(View.INVISIBLE);
            musteriTxt.setEnabled(false);
          }
        } catch (JSONException e1) {
          e1.printStackTrace();
        }
      }
    }
  }


  public class RublerAdi extends AsyncTask<String, String, JSONObject> {

    String qnq;

    public RublerAdi(String qnq) {
      this.qnq = qnq;
    }

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      if (rubLyt.getVisibility() == View.VISIBLE) {
        rubBar.setVisibility(View.VISIBLE);
        rubList.setAdapter(null);
      }
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

      JSONParser jParser = new JSONParser();

      List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
      params.add(new BasicNameValuePair("qnq", qnq));
      params.add(new BasicNameValuePair("userId", userId_));
      params.add(new BasicNameValuePair("year", year));

      JSONObject json = jParser.getJSONFromUrl(urlRublerAdi, params);
      return json;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
      super.onPostExecute(jsonObject);
      if (getActivity() != null) {
        JSONArray dataArray = null;
        ArrayList<String> rubler = new ArrayList<>();
        ArrayList<String> rubDeyerler = new ArrayList<>();
        ArrayList<String> runCont = new ArrayList<>();
        try {
          if (jsonObject != null) {
            dataArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
              JSONObject jsonObject1 = dataArray.getJSONObject(i);
              String name = jsonObject1.getString("QUARTERS");
              String weight = jsonObject1.getString("TONNAJ");
              String wagonCount = jsonObject1.getString("VAGON_COUNT");
              String KontCount = jsonObject1.getString("CONT_COUNT");


              if (!weight.equals("0") && !weight.equals("")) {
                rubler.add(name);
                rubDeyerler.add(changeFormat(weight) + "/" + wagonCount);
                runCont.add(KontCount);
              }

            }
            final float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 32, getActivity().getResources().getDisplayMetrics());

            ViewGroup.LayoutParams params2 = rubList.getLayoutParams();
            params2.height = (int) (pixels * rubler.size());
            rubList.setLayoutParams(params2);
            rubList.requestLayout();

            NewYukInfoAdapter adapter = new NewYukInfoAdapter(getActivity(), R.layout.yukinfo_list_item, rubler, rubDeyerler, runCont);
            rubList.setAdapter(adapter);
            rubBar.setVisibility(View.INVISIBLE);
            rubTxt.setEnabled(true);
          }
          /////////////////////////////////////////////////////////
          else {
            rubLyt.setVisibility(View.GONE);
            rubBar.setVisibility(View.INVISIBLE);
            rubTxt.setEnabled(false);
          }

        } catch (JSONException e1) {
          e1.printStackTrace();
        }
      }
    }
  }


  public class GetAllQnqsFromDb extends AsyncTask<String, String, String> {

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


          if (!qnqEditText.getText().toString().equals("")) {

            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setMessage("Yük İnfo-dan çıxmaq istədiyinizə əminsiniz?").setCancelable(false)
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

  public String changeFormat(String value) {
    java.text.DecimalFormat formatter;
    formatter = new java.text.DecimalFormat("#,###,###");

    String formattedNumber = formatter.format(Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(value)))));
    String newFormat = formattedNumber.replaceAll(",", " ");

    return newFormat;
  }

  protected void setupParent(View view) {
    //Set up touch listener for non-text box views to hide keyboard.
    if (!(view instanceof EditText)) {
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


  public void searchDialog(final EditText editText) {

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

    qnqEdt.setText(qnqEditText.getText().toString());

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

         qnqEditText.setText(qnqModelList.get(position).getQnqCode());

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



}



