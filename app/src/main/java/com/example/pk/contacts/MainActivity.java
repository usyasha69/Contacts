package com.example.pk.contacts;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.pk.contacts.fragments.AddContactFragment;
import com.example.pk.contacts.fragments.ContactListFragment;
import com.example.pk.contacts.fragments.DetailContactFragment;
import com.example.pk.contacts.fragments.Generator;

import java.util.ArrayList;

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
            Generator.createAndFillingContactList();
        }

        createFragments();
    }

    private void createFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.ma_portrait_acf_and_dcf_container
                            , AddContactFragment.newInstance()
                            , AddContactFragment.class.getSimpleName())
                    .replace(R.id.ma_portrait_clf_container
                            , ContactListFragment.newInstance()
                            , ContactListFragment.class.getSimpleName())
                    .commit();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.ma_landscape_contact_fragment_container
                            , ContactListFragment.newInstance()
                            , ContactListFragment.class.getSimpleName())
                    .replace(R.id.ma_landscape_detail_contact_fragment_container
                            , DetailContactFragment.newInstance()
                            , DetailContactFragment.class.getSimpleName())
                    .commit();
        }
    }

    public void replaceFragments(Contact item) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT) {
            fragmentManager
                    .beginTransaction()
                    .remove(fragmentManager.findFragmentByTag(
                            ContactListFragment.class.getSimpleName()))
                    .replace(R.id.ma_portrait_acf_and_dcf_container
                            , DetailContactFragment.newInstance(item)
                            , DetailContactFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.ma_landscape_detail_contact_fragment_container
                            , DetailContactFragment.newInstance(item)
                            , DetailContactFragment.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(CONTACT_LIST_KEY, contacts);
    }
}
