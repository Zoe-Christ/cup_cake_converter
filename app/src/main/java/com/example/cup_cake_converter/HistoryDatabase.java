package com.example.cup_cake_converter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HistoryDatabase extends SQLiteOpenHelper {
    public static final String TAG = "HistoryDatabase";
    public static final String TABLE_NAME = "berechnungen_table";
    public static final String COL1 = "ID";
    public static final String COL2 = "zutat";
    public static final String COL3 = "menge";
    public static final String COL4 = "ergebnis";



    public HistoryDatabase (Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = ("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT,"
                + COL3 + " REAL, " + COL4 + " REAL)");
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData (String zutat, double menge, double ergebnis) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, zutat);
        contentValues.put(COL3, menge);
        contentValues.put(COL4, ergebnis);

        Log.d(TAG, "addData: Adding " + zutat + ", " + menge + ", " + ergebnis + " to "+ TABLE_NAME );

        long result = db.insert(TABLE_NAME, null, contentValues);

        // gibt -1 zurück, wenn der Datensatz der Datenbank nicht richtig hinzugefügt werden konnte
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);
    }
}
