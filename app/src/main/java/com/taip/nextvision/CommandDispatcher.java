package com.taip.nextvision;

import android.content.Context;
import android.widget.Toast;

import com.taip.nextvision.TelephonyEngine.CallEngine;
import com.taip.nextvision.TelephonyEngine.SMSEngine;
import com.taip.nextvision.TimeEngine.DateEngine;
import com.taip.nextvision.TimeEngine.TimeEngine;
import com.taip.nextvision.directions.DirectionsEngine;

public class CommandDispatcher {
    public String dispatch(Context context, String cmd) {
        Toast.makeText(context, cmd, Toast.LENGTH_SHORT).show();
        SpeechEngine.textToSpeech(cmd);
        CommandEngine commandEngine;
        if (cmd.equals("suna")) {
            commandEngine = new CallEngine(context);
        } else if (cmd.equals("sms")) {
            commandEngine = new SMSEngine(context);
        } else if (cmd.equals("directii")) {
            commandEngine = new DirectionsEngine(context);
        } else if (cmd.equals("de la")) {
            commandEngine = new DirectionsEngine(context);
        } else if (cmd.equals("pana la")) {
            commandEngine = new DirectionsEngine(context);
        } else if (cmd.equals("data")) {
            commandEngine = new DateEngine(context);
        } else if (cmd.equals("timp")) {
            commandEngine = new TimeEngine(context);
        } else {
            return "Nu am inteles comanda";
        }

        return commandEngine.execute(cmd);
    }
}
