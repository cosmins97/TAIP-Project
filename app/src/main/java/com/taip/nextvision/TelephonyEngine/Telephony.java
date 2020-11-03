package com.taip.nextvision.TelephonyEngine;

public class Telephony {
    private static Telephony instance = null;

    private void Telephony() {
    }

    public static Telephony getInstance() {
        if (instance == null) {
            instance = new Telephony();
        }
        return instance;
    }
}
