package com.taip.nextvision;

import android.Manifest;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import android.widget.Toast;

import androidx.annotation.RequiresPermission;

import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

// https://github.com/vikramezhil/DroidSpeech/blob/master/app/src/main/java/com/vikramezhil/droidspeechexample/Activity_DroidSpeech.java
public class SpeechEngine {
    static MainActivity mainActivity;
    static TextToSpeech tts;
    static DroidSpeech droidSpeech;
    final static String locale = "ro-RO";


    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    public static void init(MainActivity mainActivityInput) {
        mainActivity = mainActivityInput;

        tts = new TextToSpeech(mainActivity, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale(locale));
                }
            }
        });

        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
            }

            @Override
            public void onDone(String utteranceId) {
                new Thread() {
                    public void run() {
                        mainActivity.runOnUiThread(() -> Buttons.restoreStateAfterDeactivation());
                    }
                }.start();
            }

            @Override
            public void onError(String utteranceId) {
                new Thread() {
                    public void run() {
                        mainActivity.runOnUiThread(() -> Toast.makeText(mainActivity.getBaseContext(), "TTS Error", Toast.LENGTH_SHORT).show());
                    }
                }.start();
            }
        });

        droidSpeech = new DroidSpeech(mainActivity, mainActivity.getFragmentManager());
        droidSpeech.setOnDroidSpeechListener(new OnDSListener() {
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
                droidSpeech.closeDroidSpeechOperations();
                mainActivity.commandDispatcher.dispatch(finalSpeechResult);
            }

            @Override
            public void onDroidSpeechClosedByUser() {

            }

            @Override
            public void onDroidSpeechError(String errorMsg) {
                Toast.makeText(mainActivity, errorMsg, Toast.LENGTH_LONG).show();
            }
        });
    }

    public static void startListening() {
        droidSpeech.startDroidSpeechRecognition();
    }

    public static void stopListening() {
        droidSpeech.closeDroidSpeechOperations();
    }

    public static void textToSpeech(String text) {
        HashMap<String, String> params = new HashMap<>();
        params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "text");
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, params);
    }
}
