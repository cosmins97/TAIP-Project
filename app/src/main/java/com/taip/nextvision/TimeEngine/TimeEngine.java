package com.taip.nextvision.TimeEngine;

import android.content.Context;

import com.taip.nextvision.CommandEngine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeEngine  implements CommandEngine {
    Context context;

    public TimeEngine(Context context) {
        this.context = context;
    }
    @Override
    public String execute(String cmd) {
        DateTime time = DateTime.getInstance();
        if (cmd == "timp") {
            return this.checkHourMin();
        }
        return "Nu e buna comanda de timp";
    }

    public String checkTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date currentTime = Calendar.getInstance().getTime();
        String getTime = "";
        getTime = dateFormat.format(currentTime);

        System.out.print(getTime);
        return getTime;
    }

    public String checkHourMin(){
        return checkTime();
    }
}
