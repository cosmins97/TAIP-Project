package com.taip.nextvision.BatteryEngine;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;

import com.taip.nextvision.CommandEngine;

import static android.content.Context.BATTERY_SERVICE;

public class BatteryEngine implements CommandEngine {
    Context context;

    public BatteryEngine(Context context){this.context = context;}

    @Override
    public String execute(String cmd) { return ""; }

    public int exec(String cmd) {
        if (cmd == "battery") {
            return this.checkBatteryPercentage();
        }
        if( cmd == "charge"){
            return this.isCharging();
        }
        return 0;
    }

    public int checkBatteryPercentage(){

        if (Build.VERSION.SDK_INT >= 21) {

            BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
            return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        } else {

            IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, iFilter);

            int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
            int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;

            double batteryPct = level / (double) scale;

            return (int) (batteryPct * 100);
        }
    }

    public int isCharging(){
        boolean isCharging;
        if (Build.VERSION.SDK_INT >= 21) {

            BatteryManager bm = (BatteryManager) context.getSystemService(BATTERY_SERVICE);
            return bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        } else {
            IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, iFilter);

            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
        }
        if(isCharging == true){
            return 1;
        }
        else{
            return 0;
        }
    }
    public void checkBatteryMode(){}
    public void setEconomicMode(){}
    public void setNormalMode(){}

}
