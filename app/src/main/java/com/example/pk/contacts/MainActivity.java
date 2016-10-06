package com.example.pk.contacts;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Contact> contacts;

    public static final String CONTACT_KEY = "contact";
    public static final String CONTACT_LIST_KEY = "contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null && savedInstanceState.containsKey(CONTACT_LIST_KEY)) {
            contacts = savedInstanceState.getParcelableArrayList(CONTACT_LIST_KEY);
        } else {
            createAndFillingContactList();
        }

        createContactListFragment();
    }

    private void createAndFillingContactList() {
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

    private void createContactListFragment() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ma_portrait_fragments_container
                            , ContactListFragment.newInstance())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ma_landscape_contact_fragment_container
                            , ContactListFragment.newInstance())
                    .commit();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.ma_landscape_detail_contact_fragment_container
                            , DetailContactFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(CONTACT_LIST_KEY, contacts);
    }
}
