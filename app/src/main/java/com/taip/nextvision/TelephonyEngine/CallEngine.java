package com.taip.nextvision.TelephonyEngine;

import com.taip.nextvision.CommandEngine;

public class CallEngine implements CommandEngine {
    @Override
    public String execute(String cmd) {
        Telephony telephony = Telephony.getInstance();
        if (cmd == "") {
            this.callContact(telephony, "vlad");
        }
        return "Nu am inteles comanda";
    }

    private String callContact(Telephony telephony, String name) {
        return "";
    }

    private String createNewContact(String name, String number) {
        return "";
    }

    private String callUnknownNumber(String number) {
        return "";
    }
}
