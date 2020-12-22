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
//        Toast.makeText(context, cmd, Toast.LENGTH_SHORT).show();
//        SpeechEngine.textToSpeech(cmd);
        Toast.makeText(context, cmd, Toast.LENGTH_SHORT).show();

        CommandEngine commandEngine;
        String result = "";
        if (cmd.equals("suna")) {
            commandEngine = new CallEngine(context);
            result = commandEngine.execute(cmd);
        } else if (cmd.equals("sms")) {
            commandEngine = new SMSEngine(context);
            result = commandEngine.execute(cmd);
        } else if (cmd.equals("directii")) {
            commandEngine = new DirectionsEngine(context);
            result = commandEngine.execute(cmd);
        } else if (cmd.equals("de la")) {
            commandEngine = new DirectionsEngine(context);
            result = commandEngine.execute(cmd);
        } else if (cmd.equals("pana la")) {
            commandEngine = new DirectionsEngine(context);
            result = commandEngine.execute(cmd);
        } else if (cmd.equals("data")) {
            commandEngine = new DateEngine(context);
            result = commandEngine.execute(cmd);
        } else if (cmd.equals("timp") || cmd.equals(("ora"))) {
            commandEngine = new TimeEngine(context);
            result = commandEngine.execute(cmd);
        } else {
            result = "Nu am inteles comanda";
        }

        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
        SpeechEngine.textToSpeech(result);
        return "";
    }
}
