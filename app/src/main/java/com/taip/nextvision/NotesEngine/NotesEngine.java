package com.taip.nextvision.NotesEngine;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.taip.nextvision.CommandEngine;
import com.taip.nextvision.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class NotesEngine implements CommandEngine {
    Context context;
    DatabaseHelper mDatabaseHelper;

    public NotesEngine(Context context){
        this.context = context;
        mDatabaseHelper = new DatabaseHelper(context);}

    @Override
    public String execute(String cmd) {
        if (cmd.contains("nota noua")) {
            String s = cmd.replace("nota noua ", "");
            return this.createNote(s);
        }
        if(cmd.contains("citeste note")){
            return this.readAllNotes();
        }
        if(cmd.contains("citeste ultima nota")){
            return this.readLastNote();
        }
        if(cmd.contains("sterge ultima nota")){
            return this.deleteLastNote();
        }

        return "Command not valid!";
    }


    public String createNote(String note) {
        Date date = Calendar.getInstance().getTime();
        Log.d("ADD Note", note + date);
        mDatabaseHelper.addNote(note, date);
        return "Done";
    }
    public String readAllNotes(){
        Cursor data = mDatabaseHelper.readAllnotes();
        ArrayList<String> listData = new ArrayList<>();
        // i == 2 --> second column and data.moveToNext() --> iterator through the result from the sql query
        while(data.moveToNext()){
            listData.add(data.getString(1));
            listData.add(data.getString(2));
            Log.d("Citeste note", data.getString(1));
            Log.d("Citeste note", data.getString(2));
        }

        // forming a returning string to be read by the speech API
        String listString = "";
        for(String s : listData){
            listString += s + '\t';
        }
        return listString;

    }

    public String readLastNote(){
        Cursor data = mDatabaseHelper.readLastNote();
        ArrayList<String> listData = new ArrayList<>();

        // i == 2 --> second column
        listData.add(data.getString(1));
        listData.add(data.getString(2));
        Log.d("Citeste ultima nota", data.getString(1));
        Log.d("Citeste ultima nota", data.getString(2));
        String listString = "";

        // forming a returning string to be read by the speech API
        for(String s : listData){
            listString += s + '\t';
        }
        return listString;

    }

    public String deleteLastNote(){
        mDatabaseHelper.deleteLastNote();
        return "Done";

    }

    public String readNoteByDate(Date date){
        String note = mDatabaseHelper.readNoteByDate(date);
        return note;
    }

    public void deleteNoteByDate(Date date) {
        mDatabaseHelper.deleteNoteByDate(date);
    }

    public void deleteNoteByNote(String note) {
        mDatabaseHelper.deleteNoteByNote(note);
    }

    public void updateNoteByDate(String note, Date oldDate){
        Date newDate = Calendar.getInstance().getTime();
        mDatabaseHelper.updateNoteByDate(note, oldDate, newDate);
    }

}
