package com.taip.nextvision;

import com.taip.nextvision.TelephonyEngine.CallEngine;
import com.taip.nextvision.TelephonyEngine.SMSEngine;

public class CommandDispatcher {
    public String dispatch(String cmd) {
        CommandEngine commandEngine;
        if (cmd == "suna") {
            commandEngine = new CallEngine();
        } else if (cmd == "sms") {
            commandEngine = new SMSEngine();
        } else {
            return "Nu am inteles comanda";
        }

        return commandEngine.execute(cmd);
    }
}
