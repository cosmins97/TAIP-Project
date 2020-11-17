package com.taip.nextvision.GoogleVoiceSpeech;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import com.taip.nextvision.MainActivity;
import com.taip.nextvision.SpeechEngine;

import java.util.ArrayList;
import java.util.function.Function;

import hugo.weaving.DebugLog;

public class GoogleVoiceSpeech implements SpeechEngine {
    MainActivity mainActivity;
    SpeechRecognizer speechRecognizer;
    final Intent speechRecognizerIntent;

    public GoogleVoiceSpeech(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
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
    public void startListening() {
        speechRecognizer.startListening(speechRecognizerIntent);
    }

    @Override
    public void stopListening() {
        speechRecognizer.stopListening();
    }

    @Override
    @DebugLog
    public void textToSpeech(String text) {

    }
}
