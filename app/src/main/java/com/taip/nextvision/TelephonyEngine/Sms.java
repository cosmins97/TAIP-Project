package com.taip.nextvision.TelephonyEngine;

public class Sms {
    private String sender;
    private String receiver;
    private String smsBody;

    public Sms() {

    }

    public Sms(String sender, String receiver, String smsBody){
        this.receiver = receiver;
        this.sender = sender;
        this.smsBody = smsBody;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSmsBody() {
        return smsBody;
    }

    public void setSmsBody(String smsBody) {
        this.smsBody = smsBody;
    }
}
