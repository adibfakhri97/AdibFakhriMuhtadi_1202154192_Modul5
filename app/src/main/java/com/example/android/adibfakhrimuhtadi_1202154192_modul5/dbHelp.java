package com.example.android.adibfakhrimuhtadi_1202154192_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbHelp extends SQLiteOpenHelper {
    Context context;  //variable yang diperlukan
    SQLiteDatabase mydb;

    public static final String DB_NAMA = "todolist.db";
    public static final String TABLE_NAME = "listtodo";
    public static final String COL_1 = "judul";
    public static final String COL_2 = "deskripsi";
    public static final String COL_3 = "priority";


    public dbHelp(Context context) { //konstruktor class dbHelp
        super(context, DB_NAMA, null, 1);
        this.context = context;
        mydb = this.getWritableDatabase();
        mydb.execSQL("create table if not exists " +TABLE_NAME+ " (judul varchar(20) primary key, deskripsi varchar(40), priority varchar(3))");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " +TABLE_NAME+ " (judul varchar(20) primary key, deskripsi varchar(40), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean inputdata(methodData list) {
        ContentValues values = new ContentValues();
        values.put(COL_1, list.getJudul());
        values.put(COL_2, list.getDeskripsi());
        values.put(COL_3, list.getPriority());
        long hasil = mydb.insert(TABLE_NAME, null, values);
        if (hasil==-1) {
            return false;
        }else {
            return true;
        }
    }

    public void getData(ArrayList<methodData> data){  //method untuk membaca database yang ada
        Cursor c = this.getReadableDatabase().rawQuery("select judul, deskripsi, priority from "+TABLE_NAME, null);
        while (c.moveToNext()){
            data.add(new methodData(c.getString(0), c.getString(1), c.getString(2)));
        }
    }

    public boolean hapus(String toDo) {  //method untuk menghapus data
        return mydb.delete(TABLE_NAME, COL_1+"=\""+toDo+"\"", null)>0;
    }



}
