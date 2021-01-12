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

        // the input will be like : "<command> day month year hour minutes seconds"

        String parsedCmd = cmd.replaceAll("[a-zA-Z]+ alarma ", "");
        String [] arr = parsedCmd.split("\\s+");
        String dateString="";
        String hourString="";
        String finalDateAndHour="";

        // need to convert to specific format like: day/month/year hour:minutes:seconds to apply methods from Date library
        for(int wordIndex=0; wordIndex<6 ; wordIndex++){
            if(wordIndex<3){
                dateString = dateString + "/" + arr[wordIndex] ;}
            else{hourString = hourString + ":" + arr[wordIndex];}
        }
        dateString = dateString.substring(1);
        hourString = hourString.substring(1);
        finalDateAndHour = dateString + " " + hourString;

        if (cmd.contains("seteaza alarma")) {

            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date;
            try {
                date = format1.parse(finalDateAndHour);
                return this.setAlarmFromSpecificDate(date);
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }

        }

        if(cmd.contains("sterge alarma")){

            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date;
            try {
                date = format1.parse(finalDateAndHour);
                return this.deleteAlarmFromSpecificDate(date);
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        if(cmd.contains("exista alarma")){

            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date;
            try {
                date = format1.parse(finalDateAndHour);
                return this.verifyIfAlarmFromSpecificDateExists(date);
            }
            catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return "Command not valid!";
    }

    public String setAlarmFromSpecificDate(Date date){

        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date

        intent.putExtra(AlarmClock.EXTRA_HOUR, calendar.get(Calendar.HOUR_OF_DAY));
        intent.putExtra(AlarmClock.EXTRA_MINUTES, calendar.get(Calendar.MINUTE));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(intent);

        return "Created";
    }

    public String deleteAlarmFromSpecificDate(Date date) {

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

    public String verifyIfAlarmFromSpecificDateExists(Date date) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(PROX_ALERT_INTENT);
        intent.putExtra("ALERT_TIME", date);
        intent.putExtra("ID_ALERT", 0);
        intent.putExtra("TITLE", "title");
        intent.putExtra("GEO_LOC", 0);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        boolean response = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE) != null;
        if(response){
            return "Exista";
        }
        else{
            return "Nu exista";
        }
    }

    public void closeAlarm(Date date){}
    public void modifyAlarm(Date existingDate, Date newDate){}

}
