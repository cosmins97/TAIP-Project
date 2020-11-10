package com.taip.nextvision;

import android.content.Context;

import com.taip.nextvision.TelephonyEngine.CallEngine;
import com.taip.nextvision.TelephonyEngine.SMSEngine;
import com.taip.nextvision.directions.DirectionsEngine;

public class CommandDispatcher {
    public String dispatch(Context context, String cmd) {
        CommandEngine commandEngine;
        if (cmd == "suna") {
            commandEngine = new CallEngine(context);
        } else if (cmd == "sms") {
            commandEngine = new SMSEngine();
        } else if (cmd == "directii") {
            commandEngine = new DirectionsEngine().mapsConnect();
        } else if (cmd == "de la") {
            commandEngine = new DirectionsEngine().firstLocation();
        } else if (cmd == "pana la") {
            commandEngine = new DirectionsEngine().secondLocation();
        } else {
            return "Nu am inteles comanda";
        }

        return commandEngine.execute(cmd);
    }
}
