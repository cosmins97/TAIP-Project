package com.taip.nextvision.TelephonyEngine;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.taip.nextvision.CommandEngine;

import java.util.ArrayList;

public class CallEngine extends Activity implements CommandEngine {
    Context context;

    public CallEngine(Context context) {
        this.context = context;
    }

    @Override
    public String execute(String cmd) {
        if (cmd.startsWith("suna ") || cmd.startsWith("apeleaza ")) {
            String[] splited = cmd.split("\\s+");
            if (splited.length == 2) {
                String check = this.callContact(splited[1]);
                if (check.equals("no"))
                    return "Contactul nu a fost gasit!";
                else {
                    String[] arr = check.split("\\s+");
                    String result = "";
                    for (String s : arr)
                        result += s;
                    result = result.substring(0, result.length() - 1);
                    Uri number = Uri.parse("tel:" + result);
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    context.startActivity(callIntent);
                    return check;
                }
            }
        }
        if (cmd.startsWith("creeaza ") || cmd.startsWith("adauga ") || cmd.startsWith("salveaza ")) {
            String[] splited = cmd.split("\\s+");
            if (splited.length == 3)
                return this.createNewContact(splited[1], splited[2]);
        }
        return "Nu am inteles comanda";
    }

    private String callContact(String person) {
        if (person.startsWith("0"))
            return checkContactExistence(person, "call number");
        else
            return checkContactExistence(person, "call person");
    }

    private String checkContactExistence(String check, String mode) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = this.context.getContentResolver().query(uri, projection, null, null, null);
        int idxName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int idxNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        if (mode.equals("call person")) {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(idxName).equals(check)) {
                        String result = "0";
                        String number = cursor.getString(idxNumber);
                        for (int i = 1; i < number.length(); i++) {
                            result += " " + number.charAt(i);
                        }
                        return result;
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            return "no";
        }

        if (mode.equals("call number"))
            return callUnknownNumber(check);

        if (mode.equals("create name")) {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(idxName).equals(check)) {
                        return "yes";
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            return "no";
        }

        if (mode.equals("create number")) {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(idxNumber).equals(check)) {
                        return "yes";
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            return "no";
        }
        return "false";
    }

    private String createNewContact(String name, String number) {
        if (checkContactExistence(name, "create name").equals("no")) {
            if (checkContactExistence(number, "create number").equals("no")) {
                ArrayList<ContentProviderOperation> contact = new ArrayList<>();
                contact.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                        .build());

                contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.RawContacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name)
                        .build());

                contact.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        .build());

                try {
                    ContentProviderResult[] results = this.context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, contact);
                    return "Numarul a fost salvat";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Erorare";
                }
            }
            return "Contactul cu acest numar exista.";
        }
        return "Contactul cu acest nume exista.";
    }

    private String callUnknownNumber(String number) {
        String result = "0";
        for (int i = 1; i < number.length(); i++) {
            result += " " + number.charAt(i);
        }
        return result;
    }
}
