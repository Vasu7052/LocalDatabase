package com.codesimplifiedtutorials.localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Iron Man on 7/12/2016.
 */
public class MyDatabase {

    // Database constants

    public static final String DB_NAME = "Tasks.db";
    public static final int DB_VER = 2;
    public static final String DB_TABLE = "Info";
    public static final String C_TASK = "Task";
    public static final String C_DESC = "Desc";

    public static final String Q_CREATE = "CREATE TABLE " + DB_TABLE + "(" + C_TASK + " TEXT, " + C_DESC + " TEXT)";

    Context context;
    SQLiteDatabase database;

    public MyDatabase(Context c) {

        context = c;
    }


    public MyDatabase open() {

        DBHelper dbh =  new DBHelper(context);

        database = dbh.getWritableDatabase();

        return this;

    }

    public void write(String task, String desc) {


        ContentValues cv = new ContentValues();

        cv.put(C_TASK,task);
        cv.put(C_DESC,desc);

        database.insert(DB_TABLE,null,cv);


    }

    public void close() {

        database.close();

    }


    public String read() {

        String result = "";


        String[] colms = {C_TASK,C_DESC};
        Cursor cur = database.query(DB_TABLE,colms,null,null,null,null,null);

        int iTask = cur.getColumnIndex(C_TASK);
        int iDesc = cur.getColumnIndex(C_DESC);


        for(cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()){

                result = result + cur.getString(iTask) + "\t " + cur.getString(iDesc) + "\n";


        }



        return result;
    }


    private class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VER);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(Q_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }




}
