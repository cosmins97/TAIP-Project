package com.taip.nextvision.directions;

import com.taip.nextvision.CommandEngine;
import com.taip.nextvision.soundControl.SoundControl;

public class DirectionsEngine implements CommandEngine {
    SoundControl obj = new SoundControl();
    private static DirectionsEngine instance = null;
    private void DirectionsEngine(){}
    
    public static DirectionsEngine getInstance() { 
        instance = new DirectionsEngine();
        return instance;
    }
    public CommandEngine mapsConnect() {
//        boolean check = false;
//
//        //do connection
//        obj.checkResult(check);
        return null;
    }
    public CommandEngine firstLocation() {

        return null;
    }

    public CommandEngine secondLocation() {

        return null;
    }

    public void searchLocations(String startLocation, String endLocation) {
//        boolean check = false;
//
//        //do search
//        obj.checkResult(check);
    }

    public void getPath() {
//        boolean check = false;
//
//        //do path
//        obj.checkResult(check);
    }

    @Override
    public String execute(String cmd) {
        return null;
    }
}
