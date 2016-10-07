package com.example.pk.contacts.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pk.contacts.Contact;
import com.example.pk.contacts.ContactListAdapter;
import com.example.pk.contacts.ContactNameSorter;
import com.example.pk.contacts.MainActivity;
import com.example.pk.contacts.R;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddContactFragment extends Fragment {
    @BindView(R.id.acf_contact_name_label)
    EditText contactLabelName;
    @BindView(R.id.acf_contact_telephone_number_label)
    EditText contactLabelTelephoneNumber;
    @BindView(R.id.acf_contact_email_label)
    EditText contactLabelEmail;
    @BindView(R.id.acf_contact_address_label)
    EditText contactLabelAddress;

    public static AddContactFragment newInstance() {

        Bundle args = new Bundle();

        AddContactFragment fragment = new AddContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_contact, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @OnClick(R.id.adf_clear_all_label)
    public void clearAllLabel() {
        contactLabelName.setText("");
        contactLabelAddress.setText("");
        contactLabelTelephoneNumber.setText("");
        contactLabelEmail.setText("");
    }

    @OnClick(R.id.adf_create_contact)
    public void createContact() {
        if (checkedContactLabel()) {
            MainActivity.contacts.add(new Contact(contactLabelName.getText().toString()
                    , contactLabelEmail.getText().toString()
                    , contactLabelAddress.getText().toString()
                    , contactLabelTelephoneNumber.getText().toString()
                    , R.drawable.ic_settings_phone_black_48dp));

            Collections.sort(MainActivity.contacts, new ContactNameSorter());

            ContactListFragment contactListFragment = (ContactListFragment)
                    getActivity().getSupportFragmentManager()
                            .findFragmentByTag(ContactListFragment.class.getSimpleName());

            contactListFragment.updateAdapter();
        } else {
            Toast.makeText(getContext(), "Please, enter the correct labels!"
                    , Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkedContactLabel() {
        boolean result = true;

        //checked labels by empty text
        if (contactLabelName.getText().toString().isEmpty()
                || contactLabelEmail.getText().toString().isEmpty()
                || contactLabelAddress.getText().toString().isEmpty()
                || contactLabelTelephoneNumber.getText().toString().isEmpty()) {
            result = false;
        }

        //checked email
        if (!checkEmail()) {
            result = false;
        }

        //checked telephone number
        if (!checkTelephoneNumber()) {
            result = false;
        }

        return result;
    }

    /**
     * This method checked email by validate.
     *
     * @return - is validate
     */
    private boolean checkEmail() {
        boolean result = true;

        if (!contactLabelEmail.getText().toString().contains("@")) {
            result = false;
        }

        return result;
    }

    /**
     * This method checked telephone number by validate.
     *
     * @return - is validate
     */
    private boolean checkTelephoneNumber() {
        boolean result = true;

        String telephoneNumber = contactLabelTelephoneNumber.getText().toString();

        if (!telephoneNumber.startsWith("+380") || telephoneNumber.length() != 13) {
            result = false;
        }

        return result;
    }
}
