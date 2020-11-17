package com.taip.nextvision.AlarmEngine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.taip.nextvision.CommandEngine;

import java.util.Calendar;
import java.util.Date;

public class AlarmEngine implements CommandEngine {
    Context context;
    private static final String PROX_ALERT_INTENT = "com.androidmyway.demo.ProximityAlert";
    public AlarmEngine(Context context){ this.context = context;}

    @Override
    public String execute(String cmd) {
        return "Command not valid!";
    }

    public String execute(String cmd, Date date) {
        if (cmd == "set alarm") {
            return this.setAlarm(date);
        }
        if(cmd == "delete alarm"){
            return this.deleteAlarm(date);
        }

        return "Command not valid!";
    }


    public String setAlarm(Date date){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(PROX_ALERT_INTENT);
        intent.putExtra("ALERT_TIME", date);
        intent.putExtra("ID_ALERT", 0);
        intent.putExtra("TITLE", "title");
        intent.putExtra("GEO_LOC", 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Log.d("ADD ALERT", "DONE");

        return "Created";
    }

    public String deleteAlarm(Date date) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(PROX_ALERT_INTENT);
        intent.putExtra("ALERT_TIME", date);
        intent.putExtra("ID_ALERT", 0);
        intent.putExtra("TITLE", "title");
        intent.putExtra("GEO_LOC", 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager.cancel(pendingIntent);

        return "Deleted";
    }

    public void closeAlarm(Date date){}
    public void modifyAlarm(Date existingDate, Date newDate){}

}
