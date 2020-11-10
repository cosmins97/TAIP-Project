package com.taip.nextvision.TelephonyEngine;

import android.content.Context;

import com.taip.nextvision.CommandEngine;

import java.util.ArrayList;
import java.util.List;

public class SMSEngine implements CommandEngine {
    Context context;
    private ArrayList<Sms> smsList;

    public SMSEngine(Context context){
        this.context = context;
    }

    @Override
    public String execute(String cmd) {
        if (cmd == "find sms") {
            return this.findSmsBySender("cosmin");
        }
        else if (cmd == "read last sms") {
            return this.readLastSms();
        }
        else if (cmd == "new sms") {
            return this.newSms("cosmin", "salut");
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
        Sms newSms = new Sms("me", name, text);
        smsList.add(newSms);
        return String.valueOf(smsList.size());
    }
}
