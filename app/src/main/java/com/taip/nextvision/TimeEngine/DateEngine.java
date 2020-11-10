package com.taip.nextvision.TimeEngine;

import android.content.Context;

import com.taip.nextvision.CommandEngine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEngine implements CommandEngine {
    Context context;

    public DateEngine(Context context) {
        this.context = context;
    }
    @Override
    public String execute(String cmd) {
        DateTime dateTime = DateTime.getInstance();
        if (cmd == "data") {
            return this.checkDate(dateTime);
        }
        return "Nu e buna comanda de data";
    }
    public String checkDate(DateTime dateTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        //Date date = new Date();
        //String dateTime = dateFormat.format(date);
        //return dateTime;

        String test = "10/11/2020";

        Date date = null;
        try {
            date = dateFormat.parse(test);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateTimeStr = dateFormat.format(date);
        System.out.print(dateTimeStr);
        return dateTimeStr;

//        Date currentTime = Calendar.getInstance().getTime();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        String strDate = dateFormat.format(currentTime);
//        return strDate;
    }
}
