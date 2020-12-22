 package com.taip.nextvision.TimeEngine;

import android.content.Context;

import com.taip.nextvision.CommandEngine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateEngine implements CommandEngine {
    Context context;

    public DateEngine(Context context) {
        this.context = context;
    }
    @Override
    public String execute(String cmd) {
        if (cmd == "data") {
            return this.checkDate();
        }
        return "Nu e buna comanda de data";
    }

    public String checkDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = Calendar.getInstance().getTime();
        String getDate = "";
        getDate = dateFormat.format(currentDate);

        System.out.print(getDate);
        return getDate;
    }
}
