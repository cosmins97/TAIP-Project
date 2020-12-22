package com.taip.nextvision.AlarmEngine;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.util.Log;

import com.taip.nextvision.CommandEngine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AlarmEngine implements CommandEngine {
    Context context;
    private static final String PROX_ALERT_INTENT = "com.androidmyway.demo.ProximityAlert";
    public AlarmEngine(Context context){ this.context = context;}

    @Override
    public String execute(String cmd) {
        if (cmd.contains("seteaza alarma")) {
            String s = cmd.replace("seteaza alarma ", "");

            String [] arr = s.split("\\s+");
            String dateString="";
            String hourString="";
            String finalDate="";
            for(int i=0; i<6 ; i++){
                if(i<3){
                dateString = dateString + "/" + arr[i] ;}
                else{hourString = hourString + ":" + arr[i];}
            }
            dateString = dateString.substring(1);
            hourString = hourString.substring(1);
            finalDate = dateString + " " + hourString;
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date;
            try {
                date = format1.parse(finalDate);
                return this.setAlarm(date);
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
//            return this.setAlarm(date);
        }

        if(cmd.contains("sterge alarma")){

            String s = cmd.replace("sterge alarma ", "");

            String [] arr = s.split("\\s+");
            String dateString="";
            String hourString="";
            String finalDate="";
            for(int i=0; i<6 ; i++){
                if(i<3){
                    dateString = dateString + "/" + arr[i] ;}
                else{hourString = hourString + ":" + arr[i];}
            }
            dateString = dateString.substring(1);
            hourString = hourString.substring(1);
            finalDate = dateString + " " + hourString;
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date;
            try {
                date = format1.parse(finalDate);
                return this.deleteAlarm(date);
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        if(cmd.contains("exista alarma")){

            String s = cmd.replace("exista alarma ", "");

            String [] arr = s.split("\\s+");
            String dateString="";
            String hourString="";
            String finalDate="";
            for(int i=0; i<6 ; i++){
                if(i<3){
                    dateString = dateString + "/" + arr[i] ;}
                else{hourString = hourString + ":" + arr[i];}
            }
            dateString = dateString.substring(1);
            hourString = hourString.substring(1);
            finalDate = dateString + " " + hourString;
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date;
            try {
                date = format1.parse(finalDate);
                return this.existAlarm(date);
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return "Command not valid!";
    }

    public String setAlarm(Date date){

//        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date

        intent.putExtra(AlarmClock.EXTRA_HOUR, calendar.get(Calendar.HOUR_OF_DAY));
        intent.putExtra(AlarmClock.EXTRA_MINUTES, calendar.get(Calendar.MINUTE));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(intent);
//        intent.putExtra("ALERT_TIME", date);
//        intent.putExtra("ID_ALERT", 0);
//        intent.putExtra("TITLE", "title");
//        intent.putExtra("GEO_LOC", 0);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
//                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//
//        Log.d("ADD ALERT", "DONE");

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

        Log.d("ADD ALERT", "DONE");
        return "Deleted";
    }

    public String existAlarm(Date date) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(PROX_ALERT_INTENT);
        intent.putExtra("ALERT_TIME", date);
        intent.putExtra("ID_ALERT", 0);
        intent.putExtra("TITLE", "title");
        intent.putExtra("GEO_LOC", 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        boolean rasp = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE) != null;
        if(rasp){
            return "Exista";
        }
        else{
            return "Nu exista";
        }
    }

    public void closeAlarm(Date date){}
    public void modifyAlarm(Date existingDate, Date newDate){}

}
