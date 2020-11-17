package com.taip.nextvision;

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

    TextToSpeech test;
    Button buttonTestOn, buttonTestOff;
    MediaPlayer testMedia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        test = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    test.setLanguage(Locale.UK);
                }
            }
        });

        buttonTestOn = (Button)findViewById(R.id.micOn);
        buttonTestOff = (Button)findViewById(R.id.micOff);
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
                //testMedia.start();
                String toSpeak = "Mic is On";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
            }
        });

        buttonTestOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //testMedia.start();
                String toSpeak = "Mic is Off";
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
            }
        });



        SpeechEngine speech = new GoogleVoiceSpeech();
        CommandDispatcher commandDispatcher = new CommandDispatcher();


//        while (true) {
//            String cmd = speech.speechToText();
//            if (cmd == "") {
//                speech.textToSpeech("Incearca din nou");
//            }
//            String answer = commandDispatcher.dispatch(getApplicationContext(), cmd);
//            speech.textToSpeech(answer);
//        }

    }
}