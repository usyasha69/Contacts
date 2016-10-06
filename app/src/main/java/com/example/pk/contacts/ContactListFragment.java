package com.example.pk.contacts;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListFragment extends Fragment {
    @BindView(R.id.clf_contact_name_label)
    EditText contactLabelName;
    @BindView(R.id.clf_contact_telephone_number_label)
    EditText contactLabelTelephoneNumber;
    @BindView(R.id.clf_contact_email_label)
    EditText contactLabelEmail;
    @BindView(R.id.clf_contact_address_label)
    EditText contactLabelAddress;

    private ListView contactsList;

    public static ContactListFragment newInstance() {

        Bundle args = new Bundle();

        ContactListFragment fragment = new ContactListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_contact_list, container, false);

        if (getContext().getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_PORTRAIT) {
            ButterKnife.bind(this, root);
        }

        contactsList = (ListView) root.findViewById(R.id.fcl_list_view);
        contactsList.setAdapter(new ContactListAdapter(getContext()));
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (getContext().getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_PORTRAIT) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.ma_portrait_fragments_container
                                    , DetailContactFragment.newInstance(MainActivity.contacts.get(i)))
                            .addToBackStack(null)
                            .commit();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.ma_landscape_detail_contact_fragment_container
                                    , DetailContactFragment.newInstance(MainActivity.contacts.get(i)))
                            .commit();
                }
            }
        });

        return root;
    }

    @OnClick(R.id.clf_create_contact)
    public void createContact() {
        if (checkedContactLabel()) {
            MainActivity.contacts.add(new Contact(contactLabelName.getText().toString()
                    , contactLabelEmail.getText().toString()
                    , contactLabelAddress.getText().toString()
                    , contactLabelTelephoneNumber.getText().toString()
                    , R.drawable.ic_settings_phone_black_48dp));

            Collections.sort(MainActivity.contacts, new ContactNameSorter());

            contactsList.setAdapter(new ContactListAdapter(getContext()));
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

    @OnClick(R.id.clf_clear_all_label)
    public void clearAllLabel() {
        contactLabelName.setText("");
        contactLabelAddress.setText("");
        contactLabelTelephoneNumber.setText("");
        contactLabelEmail.setText("");
    }
}
