package com.taip.nextvision.GoogleVoiceSpeech;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.annotation.RequiresPermission;

import com.taip.nextvision.MainActivity;
import com.taip.nextvision.SpeechEngine;

import java.util.ArrayList;
import java.util.Locale;

import hugo.weaving.DebugLog;

public class GoogleVoiceSpeech implements SpeechEngine {
    static GoogleVoiceSpeech instance;
    MainActivity mainActivity;
    SpeechRecognizer speechRecognizer;
    final Intent speechRecognizerIntent;
    static TextToSpeech tts;

    public static GoogleVoiceSpeech getInstance() {
        return instance;
    }

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    public GoogleVoiceSpeech(MainActivity mainActivity) {
        instance = this;
        this.mainActivity = mainActivity;

        tts = new TextToSpeech(mainActivity, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    tts.setLanguage(new Locale("ro-RO"));
                }
            }
        });

        this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(mainActivity);
        this.speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ro-RO");

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Toast.makeText(mainActivity, "Listening", Toast.LENGTH_SHORT);
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int error) {
                Toast.makeText(mainActivity, "Error", Toast.LENGTH_SHORT);
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                Toast.makeText(mainActivity, data.get(0), Toast.LENGTH_LONG);
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
    }

    @Override
    @DebugLog
    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    public void startListening() {
        speechRecognizer.startListening(speechRecognizerIntent);
    }

    @Override
    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    public void stopListening() {
        speechRecognizer.stopListening();
    }

    @Override
    @DebugLog
    public void textToSpeech(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
