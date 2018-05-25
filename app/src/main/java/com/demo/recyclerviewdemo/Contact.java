package com.demo.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private int mId;

    private String mName;
    private boolean mOnline;

    public Contact(int id, String name, boolean online) {
        mId = id;
        mName = name;
        mOnline = online;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    private static int lastContactId = 0;

    public static List<Contact> createContactsList(int numContacts) {
        List<Contact> contacts = new ArrayList<>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new Contact(i,"Person " + ++lastContactId, i <= numContacts / 2));
        }

        return contacts;
    }
}
