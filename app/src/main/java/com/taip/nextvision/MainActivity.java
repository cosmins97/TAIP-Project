package com.taip.nextvision;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.taip.nextvision.GoogleVoiceSpeech.GoogleVoiceSpeech;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech test;
    Button buttonTestOn, buttonTestOff;
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