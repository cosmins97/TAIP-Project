package com.taip.nextvision;

import android.graphics.Color;
import android.widget.Button;

public class Buttons {
    static MainActivity mainActivity;
    static Button start, stop;
    static Boolean listening;

    public static void init(MainActivity mainActivityInput, Button startInput, Button stopInput) {
        mainActivity = mainActivityInput;
        start = startInput;
        stop = stopInput;

        activateListening(false);
    }

    public static void activateListening(Boolean mode) {
        listening = mode;
        int color = mainActivity.getColor(R.color.main);
        if (mode) {
            start.setClickable(false);
            stop.setClickable(true);
            start.setBackgroundColor(Color.GRAY);
            stop.setBackgroundColor(color);
            SpeechEngine.startListening();
        } else {
            start.setClickable(true);
            stop.setClickable(false);
            stop.setBackgroundColor(Color.GRAY);
            start.setBackgroundColor(color);
            SpeechEngine.stopListening();
        }
    }

    public static void deactivateAll() {
        start.setClickable(false);
        stop.setClickable(false);
        start.setBackgroundColor(Color.GRAY);
        stop.setBackgroundColor(Color.GRAY);
        SpeechEngine.stopListening();
    }

    public static void restoreStateAfterDeactivation() {
        int color = mainActivity.getColor(R.color.main);
        if (listening) {
            start.setClickable(false);
            stop.setClickable(true);
            start.setBackgroundColor(Color.GRAY);
            stop.setBackgroundColor(color);
            SpeechEngine.startListening();
        } else {
            start.setClickable(true);
            stop.setClickable(false);
            stop.setBackgroundColor(Color.GRAY);
            start.setBackgroundColor(color);
        }
    }
}
