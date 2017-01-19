package com.example.rizwan444.das;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rizwan444 on 1/12/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    SQLiteDatabase db;
    private static String DB_PATH = "/data/data/DAS/databases/";
    private static String DB_NAME = "DAS.db";
    public static final String TABLE_NAME="PATIENTS_ACCOUNT";

    public static final String COL_1="FIRSTNAME";
    public static final String COL_2="LASTNAME";
    public static final String COL_3="EMAIL";
    public static final String COL_4="PASSWORD";
    public static final String COL_5="CONTACT";



    public DataBase(Context context) {
        super(context, "DAS.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, EMAIL TEXT,PASSWORD TEXT, CONTACT TEXT );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PATIENTS_ACCOUNT;");
        onCreate(sqLiteDatabase);
    }

    public boolean insert_PATIENTS_ACCOUNT(String firstname, String lastname, String email,String password,String contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1 , firstname);
        contentValues.put(COL_2, lastname);
        contentValues.put(COL_3 , email);
        contentValues.put(COL_4, password);
        contentValues.put(COL_5 , contact);

        long result=db.insert(TABLE_NAME,null,contentValues);
        db.close();
        if(result==-1){
            return false;
        }
        else
        return true;

       // this.getWritableDatabase().insertOrThrow("PATIENTS_ACCOUNT", "", contentValues);
       // Log.i("TAG","Inserted");
    }


    public Cursor list_all(String userid, String password) {
        Cursor cursor=null;
        boolean check=db.isOpen();
        if(check==true) {
            Log.i("Database.java", userid);


            SQLiteDatabase db = this.getWritableDatabase();
             cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

            cursor.close();

        }
        return cursor;


    }
    public String searchpass(String uname){
        db=this.getReadableDatabase();
        String query="SELECT "+uname+" FROM PATIENTS_ACCOUNT ; ";
        Cursor cursor=db.rawQuery(query,null);
        String username,password;
        password="not found";
        if(cursor.moveToFirst()){
            do {
                username=cursor.getString(3);

                if(username.equals(uname)){

                    password=cursor.getString(4);
                    break;
                }

            }while(cursor.moveToNext());
        }
        return password;
    }

}
