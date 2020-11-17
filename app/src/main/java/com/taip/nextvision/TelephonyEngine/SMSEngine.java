package com.taip.nextvision.TelephonyEngine;

import android.app.PendingIntent;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.SmsManager;

import com.taip.nextvision.CommandEngine;

import java.util.ArrayList;
import java.util.List;

public class SMSEngine implements CommandEngine {
    private Context context;

    private Uri uri;
    private String[] projection;
    private Cursor cursor;

    public SMSEngine(Context context){
        this.context = context;
        getContacts();
    }

    @Override
    public String execute(String cmd) {
        Telephony telephony = Telephony.getInstance();
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
        String actionResult = null;
        ArrayList<Sms> smsList = new ArrayList<>();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cursor = this.context.getContentResolver().query(uriSMSURI, null, null, null, null);

        String contactNumber = getContactAddressByName(name);


        if(contactNumber == "NotFound") {
            actionResult = "Acest contact nu a fost gasit.";
        }
        else {
            while (cursor != null && cursor.moveToNext()) {
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));

                if(address == contactNumber) {
                    smsList.add(new Sms(address, "me", body));
                }

            }

            if (cursor != null) {
                cursor.close();
            }
        }

        return actionResult;
    }

    private String readLastSms() {
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cursor = this.context.getContentResolver().query(uriSMSURI, null, null, null, null);

        ArrayList<Sms> smsList = new ArrayList<>();

        while (cursor != null && cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            smsList.add(new Sms(address, "me", body));
        }

        if (cursor != null) {
            cursor.close();
        }

        Sms lastSmsReceived = smsList.get(smsList.size() - 1);
        String smsSender = getContactNameByAddress(lastSmsReceived.getSender());
        String actionResult = "Ultimul sms a fost primit de la" + smsSender +
                ". Acesta este: " + lastSmsReceived.getSmsBody();

        return actionResult;
    }

    private String newSms(String contactName, String smsMessage) {
//        Sms newSms = new Sms("me", name, text);
//        smsList.add(newSms);

        String actionResult;

        String contactNumber = getContactAddressByName(contactName);

        //check if the contact was found
        if(contactNumber == "NotFound") {
            actionResult = "Contactul " + contactName + " nu a fost gasit.";
        }
        else {
            //intent
            PendingIntent sentIntent = null, deliveryIntent = null;
            String scAddress = null;

            //sms manager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage
                    (contactNumber, scAddress, smsMessage,
                            sentIntent, deliveryIntent);

            actionResult = "Mesaj trimis.";
        }

        return actionResult;
    }

    private void getContacts() {
        //contacts
        uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        cursor = this.context.getContentResolver().query(uri, projection, null, null, null);
    }

    private String getContactNameByAddress(String address) {
        String contactName = null;

        int idxName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int idxNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        //find contact name by number
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(idxNumber).equals(address)) {
                    contactName = cursor.getString(idxName);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        if(contactName != null) {
            return contactName;
        }
        else {
            return "NotFound";
        }
    }

    private String getContactAddressByName(String name) {
        String contactNumber = null;

        int idxName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int idxNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        //find contact name by number
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(idxName).equals(name)) {
                    contactNumber = cursor.getString(idxNumber);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        if(contactNumber != null) {
            return contactNumber;
        }
        else {
            return "NotFound";
        }
    }
}
