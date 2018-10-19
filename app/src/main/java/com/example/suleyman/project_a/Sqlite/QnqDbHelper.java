package com.example.suleyman.project_a.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Art Servis on 3/12/2018.
 */

public class QnqDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QNQS";
    private static final int DATABSE_VERSION = 1;
    public  static  final String CREATE_QUERY = "CREATE TABLE AllQnqs(qnq_name TEXT)";

    public QnqDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_QUERY);

    }

    public void createNewTable(SQLiteDatabase sqLiteDatabase)
    {
      sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS AzPointsNew (point_name TEXT)");
    }

    public void addPointName (String name, SQLiteDatabase db){
      ContentValues contentValues = new ContentValues();
      contentValues.put("point_name", name);
      db.insert("AzPointsNew", null, contentValues);
    }

    public  void addQnqName(String name, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("qnq_name", name);
        db.insert("AllQnqs", null, contentValues);
    }

    public void clearQnqs(SQLiteDatabase db){
        db.execSQL("DELETE from AllQnqs");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
