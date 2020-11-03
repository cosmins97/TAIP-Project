package com.taip.nextvision.AlarmEngine;

import com.taip.nextvision.CommandEngine;

import java.util.Date;

public class AlarmEngine implements CommandEngine {
    @Override
    public String execute(String cmd) {
        return "";
    }

    public void setAlarm(Date date){}
    public void closeAlarm(Date date){}
    public void deleteAlarm(Date date) {}
    public void modifyAlarm(Date existingDate, Date newDate){}

}
