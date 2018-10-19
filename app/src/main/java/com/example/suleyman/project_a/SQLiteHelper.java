package com.example.suleyman.project_a;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {


    static String DATABASE_NAME="DemoDataBase";

    public static final String KEY_ID="id";

    public static final String TABLE_NAME="demoTable";

    public static final String KEY_Langitem="langval";

    public static final String KEY_Themeitem="themeval";
    public static final String KEY_Type="type";
    SQLiteDatabase SQLITEDATABASE;
    String GetSQliteQuery, UpdateRecordQuery, DeleteQuery ;
    Cursor cursor;
    Boolean CheckEditTextEmpty ;
    String SQLiteQuery ;

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_Langitem+" VARCHAR, "+KEY_Themeitem+" VARCHAR)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

}