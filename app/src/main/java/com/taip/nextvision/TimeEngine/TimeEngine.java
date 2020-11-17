package com.taip.nextvision.TimeEngine;

import android.content.Context;

import com.taip.nextvision.CommandEngine;

public class TimeEngine  implements CommandEngine {
    Context context;

    public TimeEngine(Context context) {
        this.context = context;
    }
    @Override
    public String execute(String cmd) {
        DateTime time = DateTime.getInstance();
        if (cmd == "timp") {
            return this.checkTime();
        }
        return "Nu e buna comanda de timp";
    }

    public String checkTime(){ return "";}

    public String checkHourMinSec(){ return "";}

}
