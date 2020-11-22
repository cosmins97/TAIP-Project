package com.taip.nextvision;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.taip.nextvision.GoogleVoiceSpeech.GoogleVoiceSpeech;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    String[] permissions = {
            Manifest.permission.RECORD_AUDIO
    };

    TextToSpeech test;
    Button buttonTestOn, buttonTestOff;
    MediaPlayer testMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionManager.initInstance(this, permissions);
        CommandDispatcher commandDispatcher = new CommandDispatcher();
        try {
            PermissionManager.getInstance().checkPermission(Manifest.permission.RECORD_AUDIO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpeechEngine speech = new GoogleVoiceSpeech(this);

//        while (true) {
//            String cmd = speech.speechToText();
//            if (cmd == "") {
//                speech.textToSpeech("Incearca din nou");
//            }
//            String answer = commandDispatcher.dispatch(getApplicationContext(), cmd);
//            speech.textToSpeech(answer);
//        }

//        DirectionsEngine mGPS = new DirectionsEngine(this);
//        LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60 * 1, 10, mLocationListener);
//
//        TextView text = (TextView) findViewById(R.id.texts);
//        if(mGPS.canGetLocation ){
//            mGPS.getLocation();
//            text.setText("Lat"+mGPS.getLatitude()+"Lon"+mGPS.getLongitude());
//        }else{
//            text.setText("Unabletofind");
//            System.out.println("Unable");
//        }

        buttonTestOn = (Button) findViewById(R.id.micOn);
        buttonTestOff = (Button) findViewById(R.id.micOff);
        //testMedia = MediaPlayer.create(this, R.raw.sound);

//        testMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            public void onCompletion(MediaPlayer mp) {
//                testMedia.reset();
//                testMedia.release();
//                testMedia = MediaPlayer.create(context, R.raw.sound);
//            }
//        });

        buttonTestOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech.startListening();
                //testMedia.start();
                String toSpeak = "Mic is On";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
            }
        });

        buttonTestOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech.stopListening();
                //testMedia.start();
                String toSpeak = "Mic is Off";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
            }
        });
    }
}