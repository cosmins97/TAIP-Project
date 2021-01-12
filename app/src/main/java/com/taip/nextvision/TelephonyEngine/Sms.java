package com.taip.nextvision.TelephonyEngine;

public class Sms {
    private String sender;
    private String smsBody;

    public Sms() {

    }

    public Sms(String sender, String smsBody){
        this.sender = sender;
        this.smsBody = smsBody;
    }

    public String getSender() {
        return sender;
    }

    public String getSmsBody() {
        return smsBody;
    }

}
