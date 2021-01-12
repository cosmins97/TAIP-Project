package com.taip.nextvision.TelephonyEngine;

import android.app.PendingIntent;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.taip.nextvision.CommandEngine;

import java.util.ArrayList;

public class SMSEngine implements CommandEngine {
    private final Context context;
    private Cursor cursor;

    public SMSEngine(Context context){
        this.context = context;
        getContacts();
    }

    @Override
    public String execute(String cmd) {
        String[] splitStr;
        splitStr = cmd.split(" ", 0);

        if (cmd.contains("gaseste sms")) { // gaseste sms nume
            return this.findSmsBySender(splitStr[2]);
        }
        else if (cmd.contains("citeste ultimul sms")) { // citeste ultimul sms
            return this.readLastSms();
        }
        else if (cmd.contains("sms nou")) { // sms nou nume text
            return this.newSms(splitStr[2], splitStr[3]);
        }
        return "Nu am inteles comanda";
    }

    private void getContacts() {
        //contacts
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
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
                if (address.contains(cursor.getString(idxNumber))) {
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

    private String findSmsBySender(String name) {
        String actionResult;
        ArrayList<Sms> smsList = new ArrayList<>();
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cursor = this.context.getContentResolver().query(uriSMSURI, null, null, null, null);

        String contactNumber = getContactAddressByName(name);


        if(contactNumber.equals("NotFound")) {
            actionResult = "Acest contact nu a fost gasit.";
        }
        else {
            while (cursor != null && cursor.moveToNext()) {
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));

                if(address.contains(contactNumber)) {
                    smsList.add(new Sms(address, body));
                }

            }

            if (cursor != null) {
                cursor.close();
            }

            if(smsList.size() == 0)
            {
                actionResult = "Nu ati primit niciun sms de la " + name + ".";
            }
            else
            {
                Sms lastSmsReceived = smsList.get(0);
                actionResult = "Ultimul mesaj primit de la " + name + " este: " + lastSmsReceived.getSmsBody();
            }
        }

        Toast.makeText(context, actionResult, Toast.LENGTH_SHORT).show();

        return actionResult;
    }

    private String readLastSms() {
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cursor = this.context.getContentResolver().query(uriSMSURI, null, null, null, null);

        ArrayList<Sms> smsList = new ArrayList<>();

        while (cursor != null && cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            smsList.add(new Sms(address, body));
        }

        if (cursor != null) {
            cursor.close();
        }

        String actionResult;

        if(smsList.size() == 0)
        {
            actionResult = "Nu exista niciun sms.";
        }
        else
        {
            Sms lastSmsReceived = smsList.get(0);
            String smsSender = getContactNameByAddress(lastSmsReceived.getSender());

            if(smsSender.equals("NotFound"))
            {
                smsSender = lastSmsReceived.getSender();
            }

            actionResult = "Ultimul sms a fost primit de la " + smsSender +
                    ". Acesta este: " + lastSmsReceived.getSmsBody();
        }

        Toast.makeText(context, actionResult, Toast.LENGTH_SHORT).show();
        return actionResult;
    }

    private String newSms(String contactName, String smsMessage) {
//        Sms newSms = new Sms("me", name, text);
//        smsList.add(newSms);

        String actionResult;

        String contactNumber = getContactAddressByName(contactName);

        //check if the contact was found
        if(contactNumber.equals("NotFound")) {
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

        Toast.makeText(context, actionResult, Toast.LENGTH_SHORT).show();
        return actionResult;
    }


}
