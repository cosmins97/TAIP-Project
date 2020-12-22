package com.taip.nextvision;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "notes_table2";
    private static final String COL1 = "ID";
    private static final String COL2 = "note";
    private static final String COL3 = "date";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT," + COL3 +" TEXT)" ;
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addNote(String note, Date date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, note);
        contentValues.put(COL3, String.valueOf(date));

        Log.d(TAG, "addData: Adding " + note + " and:" + date + " to " + TABLE_NAME);

//        long result = db.insert(TABLE_NAME, null, contentValues);
        String query = "INSERT INTO " + TABLE_NAME + "(" + COL2 + "," +
                        COL3 + ")" + " VALUES(" + "'" + note + "'" +","+ "'"+ String.valueOf(date) +  "'"+")";
        db.execSQL(query);
        //if date as inserted incorrectly it will return -1
//        if (result == -1) {
//            return false;
//        } else {
//            return true;
//        }
        return true;
    }


    public void updateNoteByDate(String newNote, Date oldDate, Date newDate){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newNote + "'" + "," + COL3 + " = '" + String.valueOf(newDate) + "' WHERE " + COL2 + " = '" + oldDate + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting note and date to " + newNote + newDate);
        db.execSQL(query);
    }


    public void deleteNoteByNote(String note){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL2 + " = '" + note + "'";
        Log.d(TAG, "deleteNote: query: " + query);
        Log.d(TAG, "deleteNoteByNote: Deleting " + note + " from database.");
        db.execSQL(query);
    }

    public void deleteNoteByDate(Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL3 + " = '" + date + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteNoteByDate: Deleting " + date + " from database.");
        db.execSQL(query);
    }

    public void deleteLastNote(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = (SELECT MAX(id) FROM " + TABLE_NAME +")";
        Log.d(TAG, "delete" + query);
        db.execSQL(query);
    }

    public Cursor readAllnotes(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Log.d(TAG, "Read Notes: query: " + query);
        Cursor data = db.rawQuery(query, null);
        return data;

    }

    public Cursor readLastNote(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Log.d(TAG, "Read Notes: query: " + query);
        Cursor data = db.rawQuery(query, null);
        data.moveToLast();
        return data;

    }
    public String readNoteByDate(Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME +
                " WHERE " + COL3 + " = '" + date + "'";
        Cursor data = db.rawQuery(query, null);
        return String.valueOf(data);
    }


}
