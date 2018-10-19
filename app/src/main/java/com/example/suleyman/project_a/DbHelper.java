package com.example.suleyman.project_a;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static String CREATE_TABLE1;

    //    Database name
    static String DATABASE_NAME = "grafikdata";

    //    Talble name
    public static final String TABLE1_NAME = "table1";

    //      Fields for table
    public static final String ID = "id";
    public static final String LANGITEM = "langitem";
    public static final String THEMEITEM = "themeitem";

    //      Required resorces to manage database
    private ContentValues cValues;
    private SQLiteDatabase dataBase = null;
    private Cursor cursor;

    public DbHelper(Context context) {
        super(context, context.getExternalFilesDir(null).getAbsolutePath()
                + "/" + DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CREATE_TABLE1 = "CREATE TABLE " + TABLE1_NAME + "(" + ID
                + " INTEGER PRIMARY KEY autoincrement, " + LANGITEM
                + " TEXT, " + THEMEITEM + " TEXT)";


        db.execSQL(CREATE_TABLE1);
        System.out
                .println("Table is created...........................!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);

        onCreate(db);
    }

    public void inserRecord(String langitem, String themeitem) {

        dataBase = getWritableDatabase();
        cValues = new ContentValues();

        cValues.put(LANGITEM, langitem);
        cValues.put(THEMEITEM, themeitem);

        // insert data into database
        dataBase.insert(TABLE1_NAME, null, cValues);

        dataBase.close();
    }

    public void updateRecord(String langitem, String themeitem,String id) {

        dataBase = getWritableDatabase();

        cValues = new ContentValues();

        cValues.put(LANGITEM, langitem);
        cValues.put(THEMEITEM, themeitem);
//    Update data from database table
        dataBase.update("UPDATE "+DbHelper.TABLE1_NAME+"SET "+LANGITEM+"='" + langitem + "', "+THEMEITEM+"='" + themeitem + "' WHERE "+ID+"=" + id + ";",null, null, null);

        dataBase.close();
    }

    public Cursor selectRecords() {

        dataBase = getReadableDatabase();

//    Getting data from database table
        cursor = dataBase.rawQuery("select * from " + TABLE1_NAME, null);
        return cursor;
    }

    public void deleteRecord() {

        dataBase = getWritableDatabase();

//    Deleting all records from database table
        dataBase.delete(TABLE1_NAME, null, null);

        dataBase.close();
    }

}