package com.taip.nextvision;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    String[] permissions = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.CALL_PHONE
    };

    Button startButton, stopButton, runCommandButton;
    TextView commandInput;
    MediaPlayer testMedia;
    CommandDispatcher commandDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabaseHelper = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionManager.initInstance(this, permissions);
        commandDispatcher = new CommandDispatcher(this);
        try {
            PermissionManager.getInstance().checkPermission(Manifest.permission.RECORD_AUDIO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpeechEngine.init(this);

        startButton = (Button) findViewById(R.id.micOn);
        stopButton = (Button) findViewById(R.id.micOff);
        Buttons.init(this, startButton, stopButton);
        //testMedia = MediaPlayer.create(this, R.raw.sound);


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


//        testMedia.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            public void onCompletion(MediaPlayer mp) {
//                testMedia.reset();
//                testMedia.release();
//                testMedia = MediaPlayer.create(context, R.raw.sound);
//            }
//        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttons.activateListening(true);
                //testMedia.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttons.activateListening(false);
                //testMedia.start();
            }
        });

        commandInput = (TextView) findViewById(R.id.commandInputId);
        runCommandButton = (Button) findViewById(R.id.runCommandId);
        runCommandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandDispatcher.dispatch(commandInput.getText().toString());
            }
        });
    }
}