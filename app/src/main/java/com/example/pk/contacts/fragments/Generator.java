package com.example.pk.contacts.fragments;


import com.example.pk.contacts.Contact;
import com.example.pk.contacts.ContactNameSorter;
import com.example.pk.contacts.R;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.pk.contacts.MainActivity.contacts;

public class Generator {

    public static void createAndFillingContactList() {
        contacts = new ArrayList<>();

        contacts.add(new Contact("Dima", "dima@mail.ru", "Address1"
                , "+380993477584", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Kolya", "kolya@mail.ru", "Address2"
                , "+380993237584", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Vanya", "vanya@mail.ru", "Address3"
                , "+380993472954", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Sanya", "sanya@mail.ru", "Address4"
                , "+380993458494", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Petya", "petya@mail.ru", "Address5"
                , "+380993477114", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Olya", "olya@mail.ru", "Address6"
                , "+380929477584", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Sveta", "sveta@mail.ru", "Address7"
                , "+380999877584", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Valya", "valya@mail.ru", "Address8"
                , "+380993433384", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Alla", "dima@mail.ru", "Address9"
                , "+380993479184", R.drawable.ic_settings_phone_black_48dp));
        contacts.add(new Contact("Tanya", "dima@mail.ru", "Address10"
                , "+380993472464", R.drawable.ic_settings_phone_black_48dp));

        Collections.sort(contacts, new ContactNameSorter());
    }
}
