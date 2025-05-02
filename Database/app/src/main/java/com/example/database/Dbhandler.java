package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Dbhandler extends SQLiteOpenHelper {
    public Dbhandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE myemployee (sno INTEGER PRIMARY KEY, name TEXT, increment TEXT)";
        sqLiteDatabase.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop= String.valueOf("DROP TABLE IF EXISTS");
        sqLiteDatabase.execSQL(drop, new String[]{"myemployee"});
    }
    
    public void addEmployee(Employee emp){
        SQLiteDatabase db = this.getWritableDatabase(); //get writable database
        ContentValues values = new ContentValues();
        values.put("name", emp.getName());
        values.put("increment", emp.getIncrement());
        long k=db.insert("myemployee", null, values);
        Log.d("mytag",Long.toString(k));
        db.close();
    }

    public void readEmployee(int sno) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("myemployee", new String[]{"sno", "name", "increment"}, "sno=?", new String[]{String.valueOf(sno)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Log.d("mytag", cursor.getString(1));
            Log.d("mytag", cursor.getString(2));

        } else {
            Log.d("mytag", "some error occured!");
        }

    }
}