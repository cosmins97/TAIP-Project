package com.taip.nextvision;

import android.Manifest;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresPermission;

import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;

import java.util.List;
import java.util.Locale;

import hugo.weaving.DebugLog;

public class SpeechEngine implements OnDSListener {
    static MainActivity mainActivity;
    static TextToSpeech tts;
    static DroidSpeech droidSpeech;
    final static String locale = "ro-RO";
    static Button start, stop;

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    public static void init(MainActivity mainActivityInput, Button startInput, Button stopInput) {
        mainActivity = mainActivityInput;
        start = startInput;
        stop = stopInput;

        activateListening(false);

        tts = new TextToSpeech(mainActivity, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale(locale));
                }
            }
        });

        droidSpeech = new DroidSpeech(mainActivity, mainActivity.getFragmentManager());
        droidSpeech.setOnDroidSpeechListener(null);
    }

    @DebugLog
    public static void startListening() {
        activateListening(true);
        droidSpeech.startDroidSpeechRecognition();
    }

    public static void stopListening() {
        activateListening(false);
        droidSpeech.closeDroidSpeechOperations();
    }

    @DebugLog
    public static void textToSpeech(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onDroidSpeechSupportedLanguages(String currentSpeechLanguage, List<String> supportedSpeechLanguages) {
        if (supportedSpeechLanguages.contains(locale)) {
            droidSpeech.setPreferredLanguage(locale);
        }
    }

    @Override
    public void onDroidSpeechRmsChanged(float rmsChangedValue) {

    }

    @Override
    public void onDroidSpeechLiveResult(String liveSpeechResult) {

    }

    public void onDroidSpeechFinalResult(String finalSpeechResult) {
        Toast.makeText(mainActivity, finalSpeechResult, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDroidSpeechClosedByUser() {

    }

    @Override
    public void onDroidSpeechError(String errorMsg) {
        Toast.makeText(mainActivity, errorMsg, Toast.LENGTH_LONG).show();
    }

    public static void activateListening(Boolean mode) {
        int color = mainActivity.getColor(R.color.main);
        if (mode) {
            start.setClickable(false);
            stop.setClickable(true);
            start.setBackgroundColor(Color.GRAY);
            stop.setBackgroundColor(color);
        } else {
            start.setClickable(true);
            stop.setClickable(false);
            stop.setBackgroundColor(Color.GRAY);
            start.setBackgroundColor(color);
        }
    }
}
