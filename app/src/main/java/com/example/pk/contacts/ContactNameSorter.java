package com.example.pk.contacts;

import java.util.Comparator;

public class ContactNameSorter implements Comparator<Contact> {

    @Override
    public int compare(Contact contact, Contact t1) {
        return contact.getName().compareToIgnoreCase(t1.getName());
    }
}
