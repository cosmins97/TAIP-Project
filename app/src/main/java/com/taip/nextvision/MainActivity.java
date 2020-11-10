package com.taip.nextvision;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.taip.nextvision.GoogleVoiceSpeech.GoogleVoiceSpeech;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpeechEngine speech = new GoogleVoiceSpeech();
        CommandDispatcher commandDispatcher = new CommandDispatcher();

        while (true) {
            String cmd = speech.speechToText();
            if (cmd.isEmpty()) {
                speech.textToSpeech("Incearca din nou");
            }
            String answer = commandDispatcher.dispatch(getApplicationContext(), cmd);
            speech.textToSpeech(answer);
        }
    }

}