package com.example.suleyman.project_a;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.CircularProgressButton;
import com.example.suleyman.project_a.Common.Menular;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Login extends AppCompatActivity {
    private static int splash_time_out = 900;
    EditText username, password;
    int strlangitem, strthemeitem, strtype;
    UserSessionManager sessionManager;
    static String DATABASE_NAME = "DemoDataBase";
    String user = "";
    String pass = "";
    JSONArray Plan = null;
    private static String url = "https://ady.express/PLan.asmx/Login";

    //JSON Node Names
    private static final String TAG_PLANTRACK = "data";
    private static final String T_ANS = "ANS";
    private static final String T_NAME = "NAME";


    public static final String KEY_ID = "id";

    public static final String TABLE_NAME = "demoTable";


    public static final String KEY_Langitem = "langval";

    public static final String KEY_Themeitem = "themeval";
    public static final String KEY_Type = "type";
    SQLiteDatabase SQLITEDATABASE;
    String GetSQliteQuery, UpdateRecordQuery, DeleteQuery;
    Cursor cursor;
    Boolean CheckEditTextEmpty;
    String SQLiteQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.circularButton1);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);

        TextView mBox = (TextView) findViewById(R.id.imageView3);
        mBox.setText(Html.fromHtml("RAILWAYS SYSTEMS <b>INTEGRATOR</b>"));
        //imageView4
        TextView mBox2 = (TextView) findViewById(R.id.imageView4);
        mBox2.setText(Html.fromHtml("© 2017  ALL RIGHTS RESERVED.Design by <b>Fuad H.Aslan</b>"));

        sessionManager = new UserSessionManager(getApplicationContext());

       if(sessionManager.isUserLoggedIn())
        {
            Intent intent = new Intent(Login.this, navi_menu3.class);
            startActivity(intent);
            finish();
        }

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    hideKeyboard(view);
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    hideKeyboard(view);
                }
            }
        });

        DBCreate();
        GetSQliteQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id=1";
        cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);
        if (cursor.moveToFirst() && cursor != null) {

            do {
                int id = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_ID));
                strlangitem = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Langitem));
                strthemeitem = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Themeitem));
                username.setHint(LLanguage._loginuserlang[strlangitem]);

                password.setHint(LLanguage._loginpasslang[strlangitem]);
                circularButton1.setText(LLanguage._loginbuttonlang[strlangitem]);
            } while (cursor.moveToNext());
            cursor.close();
        }
        circularButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = String.valueOf(username.getText());
                pass = String.valueOf(password.getText());
                new JSONParse().execute();


            }
        });
    }

    public void DBCreate() {

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);
        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_Langitem + " INTEGER, " + KEY_Themeitem + " INTEGER)");
    }

    private void simulateSuccessProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 100);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
            }
        });
        widthAnimation.start();
    }



    private void simulateErrorProgress(final CircularProgressButton button) {
        ValueAnimator widthAnimation = ValueAnimator.ofInt(1, 99);
        widthAnimation.setDuration(1500);
        widthAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                button.setProgress(value);
                if (value == 99) {
                    button.setProgress(-1);
                }
            }
        });
        widthAnimation.start();
    }

         private class JSONParse extends AsyncTask<String, String, JSONObject> {
         ProgressBar prg=(ProgressBar) findViewById(R.id.progressBar);

         @Override
         protected void onPreExecute() {
             super.onPreExecute();

            // prg.setVisibility(View.VISIBLE);
         }

         @Override
         protected JSONObject doInBackground(String... args) {
             JSONParser jParser = new JSONParser();


             List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
             // List params = new ArrayList();
             params.add(new BasicNameValuePair("user",user));
             params.add(new BasicNameValuePair("pass",pass));

             // Getting JSON from URL
             JSONObject json = jParser.getJSONFromUrl(url,params);
             return json;
         }
         @Override
         protected void onPostExecute(JSONObject json) {
             super.onPostExecute(json);

             //Toast.makeText(Login.this, json.toString(), Toast.LENGTH_LONG).show();
            // prg.setVisibility(View.INVISIBLE);
             try {
                 // Getting JSON Array from URL
                 if (json != null)
                 {
                     Plan = json.getJSONArray(TAG_PLANTRACK);
                 for (int i = 0; i < Plan.length(); i++) {
                     JSONObject c = Plan.getJSONObject(i);
                     final String gos1 = c.getString(T_ANS);
                     final String gos2 = c.getString(T_NAME);
                     final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.circularButton1);

                     if (!gos1.toString().equals("0")) {
                         if (circularButton1.getProgress() == 0) {

                             simulateSuccessProgress(circularButton1);
                             new Handler().postDelayed(new Runnable() {
                                 Intent intent = new Intent(Login.this, navi_menu3.class);
                                 getMenus getMenus = new getMenus();
                                 getTabs getTabs = new getTabs();

                                 @Override
                                 public void run() {
                                     // intent.putExtra("sess_user", user);
                                     // intent.putExtra("sess_pass", pass);
                                     try {

                                         Menular.menular = getMenus.execute(Integer.parseInt(gos1)).get();
                                         Menular.tabs = getTabs.execute(Integer.parseInt(gos1)).get();
                                         sessionManager.createUserLoginSession(gos2, user, pass, gos1, Menular.menular, Menular.tabs);
                                         intent.putStringArrayListExtra("menular", Menular.menular);
                                         intent.putExtra("sess_id", gos1);
                                         intent.putExtra("sess_name", gos2);
                                         intent.putExtra("sess_user", user);
                                         intent.putExtra("sess_pass", pass);
                                         // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                         //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                         startActivity(intent);
                                         finish();
                                     } catch (InterruptedException e) {
                                         e.printStackTrace();
                                     } catch (ExecutionException e) {
                                         e.printStackTrace();
                                     }
                                 }
                             }, splash_time_out);
                         } else {
                             circularButton1.setProgress(0);
                         }
                     } else {

                         Toast.makeText(Login.this, "Wrong password or username",
                                 Toast.LENGTH_LONG).show();
                     }

                 }
             }
             else
                 {
                     Toast.makeText(Login.this, "Interneti Yoxlayin",
                             Toast.LENGTH_LONG).show();
                 }



             } catch (JSONException e) {
                 e.printStackTrace();
                 Toast.makeText(getApplicationContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
             }


         }
         protected void onProgressUpdate(Integer... values) {
             // TODO Auto-generated method stub
         }

     }
    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

    }

    public class getMenus extends AsyncTask<Integer, Void, ArrayList<String>>
    {

        @Override
        protected ArrayList<String> doInBackground(Integer... integers) {

            String result = "";
            URL url;
            HttpURLConnection connection = null;
            ArrayList<String> menus = new ArrayList<>();

            try {
                url = new URL("https://ady.express/PLan.asmx/GetMenu?id=" + integers[0] + "&type=0");
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader ireader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(ireader);
                String line = reader.readLine();

                while (line != null)

                {
                    result = result + line;
                    line = reader.readLine();
                }

                Log.i("Menular", result);

                JSONObject jsonObject = new JSONObject(result);
                JSONArray dataArray = jsonObject.getJSONArray("data");

                for(int i=0; i<dataArray.length(); i++)

                {
                    JSONObject jsonObject2 = (JSONObject) dataArray.get(i);
                    String menu_name = jsonObject2.getString("NAME");
                    menus.add(menu_name);
                }


                return menus;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public class getTabs extends AsyncTask<Integer, Void, ArrayList<String>>
    {

        @Override
        protected ArrayList<String> doInBackground(Integer... integers) {

            String result = "";
            URL url;
            HttpURLConnection connection = null;
            ArrayList<String> tabs = new ArrayList<>();

            try {
                url = new URL("https://ady.express/PLan.asmx/GetMenu?id=" + integers[0] + "&type=1");
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader ireader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(ireader);
                String line = reader.readLine();

                while (line != null)

                {
                    result = result + line;
                    line = reader.readLine();
                }

                Log.i("Menular", result);

                JSONObject jsonObject = new JSONObject(result);
                JSONArray dataArray = jsonObject.getJSONArray("data");

                for(int i=0; i<dataArray.length(); i++)

                {
                    JSONObject jsonObject2 = (JSONObject) dataArray.get(i);
                    String tab_name = jsonObject2.getString("NAME");
                    tabs.add(tab_name);
                }


                return tabs;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

  /* public class JSONParse extends AsyncTask<String, Void, String> {

        String sess_name;

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection connection = null;

            try {
                url = new URL("http://192.168.133.1:50590/users/" + user);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader ireader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(ireader);
                String line = reader.readLine();


                while (line != null)

                {
                    result = result + line;
                    line = reader.readLine();
                }

                Log.i("hehehe", result);

                if (result.equals("[]"))

                {
                    return "Bele Istifadeci Yoxdur";
                }

                else {
                    JSONArray jsonArray = new JSONArray(result);

                    JSONObject jsonObject = jsonArray.getJSONObject(0);



                    if(pass.equals(jsonObject.getString("Password")))
                    {
                        sess_name = jsonObject.getString("Name");
                    return "True"; }
                    else
                    {
                        return "Parol Sehvdir";
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "1";
            } catch (IOException e) {
                e.printStackTrace();
                return "2";
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("JsonError", e.toString());
                return "3";
            }

            // return null;

        }


        @Override
        protected void onPostExecute(String s) {

            final CircularProgressButton circularButton1 = (CircularProgressButton) findViewById(R.id.circularButton1);

            if(s.equals("True"))
            {
                if (circularButton1.getProgress() == 0) {

                    simulateSuccessProgress(circularButton1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(Login.this, navi_menu2.class);
                            FindMenus findMenus = new FindMenus();
                            try {
                                Menular.menular = findMenus.execute().get();
                                intent.putStringArrayListExtra("menular", Menular.menular);
                                Log.i("menu22", Menular.menular.toString());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            intent.putExtra("sess_user", user);
                            intent.putExtra("sess_pass", pass);
                            intent.putExtra("sess_name", sess_name);

                            startActivity(intent);
                        }
                    },splash_time_out);
                } else {
                    circularButton1.setProgress(0);
                }
            }

            else {
                Toast.makeText(Login.this, s, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class FindMenus extends AsyncTask<String, Void, ArrayList<String>>
    {

        @Override
        protected ArrayList<String> doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection connection = null;
            ArrayList<String> menus = new ArrayList<>();


            try {
                url = new URL("http://192.168.133.1:50590/menu/" + user);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                InputStreamReader ireader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(ireader);
                String line = reader.readLine();

                while (line != null)

                {
                    result = result + line;
                    line = reader.readLine();
                }

                Log.i("Menular", result);

                JSONArray jsonArray = new JSONArray(result);

                for(int i=0;i<jsonArray.length();i++)

                {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    String menu_name = jsonObject.getString("Menu_NAME");
                    menus.add(menu_name);

                }

                return menus;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

    }*/

}
