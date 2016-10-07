package com.example.pk.contacts.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pk.contacts.Contact;
import com.example.pk.contacts.MainActivity;
import com.example.pk.contacts.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailContactFragment extends Fragment {
    @BindView(R.id.dcf_address)
    TextView address;
    @BindView(R.id.dcf_email)
    TextView email;
    @BindView(R.id.dcf_name)
    TextView name;
    @BindView(R.id.dcf_phone_number)
    TextView phoneNumber;
    @BindView(R.id.dcf_contact_image)
    ImageView image;

    private Contact contact;

    public static DetailContactFragment newInstance() {

        Bundle args = new Bundle();

        DetailContactFragment fragment = new DetailContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DetailContactFragment newInstance(Contact contact) {

        Bundle args = new Bundle();
        args.putParcelable(MainActivity.CONTACT_KEY, contact);

        DetailContactFragment fragment = new DetailContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(MainActivity.CONTACT_KEY)) {
            contact = getArguments().getParcelable(MainActivity.CONTACT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_contact, container, false);
        ButterKnife.bind(this, root);

        if (contact != null) {
            address.setText(contact.getAddress());
            name.setText(contact.getName());
            email.setText(contact.getEmail());
            phoneNumber.setText(contact.getTelephoneNumber());
            image.setImageDrawable(ContextCompat.getDrawable(getContext(), contact.getImage()));
        }

        return root;
    }

}
