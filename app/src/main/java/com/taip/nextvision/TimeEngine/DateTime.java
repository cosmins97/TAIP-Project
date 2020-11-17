package com.taip.nextvision.TimeEngine;

public class DateTime {

    private static DateTime instance = null;
    public static DateTime getInstance() {
        if (instance == null)
            instance = new DateTime();
        return instance;
    }
    private void DateTime() {

    }
}
