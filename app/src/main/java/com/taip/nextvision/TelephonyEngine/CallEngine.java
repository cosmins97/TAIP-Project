package com.taip.nextvision.TelephonyEngine;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.taip.nextvision.CommandEngine;

import java.util.ArrayList;

public class CallEngine implements CommandEngine {
    Context context;

    public CallEngine(Context context) {
        this.context = context;
    }

    @Override
    public String execute(String cmd) {
        Telephony telephony = Telephony.getInstance();
        if (cmd == "call") {
            return this.callContact(telephony, "vlad");
        }
        if (cmd == "create") {
            return this.createNewContact("vlad", "07777777");
        }
        return "Nu am inteles comanda";
    }

    private String callContact(Telephony telephony, String name) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = this.context.getContentResolver().query(uri, projection, null, null, null);

        int idxName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int idxNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(idxName).equals(name)) {
                    return cursor.getString(idxNumber);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return "Nu a fost gasit";
    }

    private String createNewContact(String name, String number) {
        ArrayList<ContentProviderOperation> contact = new ArrayList<ContentProviderOperation>();
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

    private String callUnknownNumber(String number) {
        return "";
    }
}
