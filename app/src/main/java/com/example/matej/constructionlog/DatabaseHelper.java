package com.example.matej.constructionlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Matej on 22.8.2017..
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "projekti.db";
    public static final String TABLE_NAME = "projekti_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PREZIME";
    public static final String COL_3 = "TIP_PROJEKTA";
    public static final String COL_4 = "CIJENA_MATERIJALA";
    public static final String COL_5 = "CIJENA_POSLA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,PREZIME TEXT,TIP_PROJEKTA TEXT,CIJENA_MATERIJALA INTEGER,CIJENA_POSLA INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String prezime, String tip_projekta, String cijena_materijala, String cijena_posla) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,prezime);
        contentValues.put(COL_3,tip_projekta);
        contentValues.put(COL_4,cijena_materijala);
        contentValues.put(COL_5, cijena_posla);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String prezime, String tip_projekta, String cijena_materijala, String cijena_posla){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,prezime);
        contentValues.put(COL_3,tip_projekta);
        contentValues.put(COL_4,cijena_materijala);
        contentValues.put(COL_5, cijena_posla);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?", new String [] {id});
    }
}
