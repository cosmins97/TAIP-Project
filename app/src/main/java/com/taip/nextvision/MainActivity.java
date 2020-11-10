package com.taip.nextvision;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.taip.nextvision.GoogleVoiceSpeech.GoogleVoiceSpeech;

public class MainActivity extends AppCompatActivity {

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