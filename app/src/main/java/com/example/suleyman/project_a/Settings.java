package com.example.suleyman.project_a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
Button lang,theme;
    int strlangitem,strthemeitem;
    String defvalue="";
    static String DATABASE_NAME="DemoDataBase";

    public static final String KEY_ID="id";

    public static final String TABLE_NAME="demoTable";

    public static final String KEY_Langitem="langval";

    public static final String KEY_Themeitem="themeval";
    public static final String KEY_Type="type";

    SQLiteDatabase SQLITEDATABASE;
    String langval,themeval;
    String GetSQliteQuery, UpdateRecordQuery, DeleteQuery ;
    Cursor cursor;
    Boolean CheckEditTextEmpty ;
    String SQLiteQuery ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DBCreate();
      //  final CharSequence[] items = {"English", "Russian","Turk","Azeri"};
        //final CharSequence[] items1 = {"White", "Black",};
        lang=(Button) findViewById(R.id.lang);
        theme=(Button) findViewById(R.id.theme);
        if (defvalue=="") {
            SQLiteQuery = "INSERT INTO demoTable ("+KEY_Langitem+","+KEY_Themeitem+") VALUES('0', '0');";
            SQLITEDATABASE.execSQL(SQLiteQuery);
        }
        else {
        }
            GetSQliteQuery = "SELECT * FROM "+TABLE_NAME+" WHERE id=1";
            cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);
            if (cursor.moveToFirst()) {

                do {
                    int id = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_ID));
                    strlangitem = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Langitem));
                    strthemeitem = cursor.getInt(cursor.getColumnIndex(SQLiteHelper.KEY_Themeitem));
                    lang.setText(LLanguage._setingbuttonlang[strlangitem]);
                    if (strlangitem == 0) {
                        theme.setText(LLanguage._setingthemeeng[strthemeitem]);
                    } else if (strlangitem == 1) {
                        theme.setText(LLanguage._setingthemerus[strthemeitem]);
                    } else if (strlangitem == 2) {
                        theme.setText(LLanguage._setingthemeturk[strthemeitem]);
                    } else if (strlangitem == 3) {
                        theme.setText(LLanguage._setingthemeaze[strthemeitem]);
                    }
                } while (cursor.moveToNext());
            }

        lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strlangitem==0)
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle(LLanguage._setingdialoglang[0]);
                    builder.setItems(LLanguage._setinglangeng, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            UpdateRecordQuery = "UPDATE "+TABLE_NAME+" SET langval='" + item + "' WHERE id=1";
                            SQLITEDATABASE.execSQL(UpdateRecordQuery);
                            Intent intent = new Intent(Settings.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else if (strlangitem==1)
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle(LLanguage._setingdialoglang[1]);
                    builder.setItems(LLanguage._setinglangrus, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            UpdateRecordQuery = "UPDATE "+TABLE_NAME+" SET langval='" + item + "' WHERE id=1";                            SQLITEDATABASE.execSQL(UpdateRecordQuery);
                            Intent intent = new Intent(Settings.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else if(strlangitem==2) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle(LLanguage._setingdialoglang[2]);
                    builder.setItems(LLanguage._setinglangtur, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            UpdateRecordQuery = "UPDATE "+TABLE_NAME+" SET langval='" + item + "' WHERE id=1";                            SQLITEDATABASE.execSQL(UpdateRecordQuery);
                            Intent intent = new Intent(Settings.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle(LLanguage._setingdialoglang[3]);
                    builder.setItems(LLanguage._setinglangaze, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            UpdateRecordQuery = "UPDATE "+TABLE_NAME+" SET langval='" + item + "' WHERE id=1";                            SQLITEDATABASE.execSQL(UpdateRecordQuery);
                            Intent intent = new Intent(Settings.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (strlangitem==0)
                {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle(LLanguage._setingdialogthemelang[0]);
                    builder.setItems(LLanguage._setingthemeeng, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            UpdateRecordQuery = "UPDATE "+TABLE_NAME+" SET themeval='" + item + "' WHERE id=1";
                            SQLITEDATABASE.execSQL(UpdateRecordQuery);
                            Intent intent = new Intent(Settings.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else if (strlangitem==1) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle(LLanguage._setingdialogthemelang[1]);
                    builder.setItems(LLanguage._setingthemerus, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            UpdateRecordQuery = "UPDATE "+TABLE_NAME+" SET themeval='" + item + "' WHERE id=1";
                            SQLITEDATABASE.execSQL(UpdateRecordQuery);
                            Intent intent = new Intent(Settings.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if (strlangitem==2) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle(LLanguage._setingdialogthemelang[2]);
                    builder.setItems(LLanguage._setingthemeturk, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            UpdateRecordQuery = "UPDATE "+TABLE_NAME+" SET themeval='" + item + "' WHERE id=1";
                            SQLITEDATABASE.execSQL(UpdateRecordQuery);
                            Intent intent = new Intent(Settings.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else  {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Settings.this);
                    builder.setTitle(LLanguage._setingdialogthemelang[3]);
                    builder.setItems(LLanguage._setingthemeaze, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            // Do something with the selection
                            UpdateRecordQuery = "UPDATE "+TABLE_NAME+" SET themeval='" + item + "' WHERE id=1";
                            SQLITEDATABASE.execSQL(UpdateRecordQuery);
                            Intent intent = new Intent(Settings.this, Login.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }


            }
        });
    }
    public void DBCreate(){

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

        SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_Langitem+" INTEGER, "+KEY_Themeitem+" INTEGER)");
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    }
