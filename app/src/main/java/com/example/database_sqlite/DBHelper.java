package com.example.database_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tanulok.db";
    private static final Integer DB_VERSION = 1;
    private static final String TABLE_NAME = "tanulok";
    private static final String COL_ID = "id";
    private static final String COL_FNAME = "Vezeteknev";
    private static final String COL_LNAME = "Keresztnev";
    private static final String COL_JEGY = "jegy";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FNAME + " TEXT NOT NULL, " +
                COL_LNAME + " TEXT NOT NULL, " +
                COL_JEGY + " INTEGER NOT NULL" + ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME
        );
        onCreate(sqLiteDatabase);
    }

    public boolean modositas(String id, String fname, String lname, Integer mark){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FNAME, fname);
        values.put(COL_LNAME, lname);
        values.put(COL_JEGY, mark);
        int results = database.update(TABLE_NAME, values, COL_ID + " = ?", new String[] {id});
        return results > 0;
    }

    public  boolean torles(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        int result = database.delete(TABLE_NAME, COL_ID + " = ?",new String[] {id});
        return  result > 0;
    }

    public boolean rogzites(String fname, String lname, Integer mark){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FNAME, fname);
        values.put(COL_LNAME, lname);
        values.put(COL_JEGY, mark);
        long results = database.insert(TABLE_NAME, null, values);
        if (results != -1){
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor lekerdezes(){
        SQLiteDatabase database = this.getReadableDatabase();
        return database.query(TABLE_NAME,
                new String[] {COL_ID, COL_FNAME, COL_LNAME, COL_JEGY},
                null, null, null, null, null);
    }
}
