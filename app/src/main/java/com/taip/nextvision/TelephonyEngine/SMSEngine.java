package com.taip.nextvision.TelephonyEngine;

import com.taip.nextvision.CommandEngine;

public class SMSEngine implements CommandEngine {
    @Override
    public String execute(String cmd) {
        if (cmd == "") {
            this.findSmsBySender("vlad");
        }
        return "Nu am inteles comanda";
    }

    private String findSmsBySender(String name) {
        return "";
    }

    private String readLastSms() {
        return "";
    }

    private String newSms(String name, String text) {
        return "";
    }
}
