package com.example.pk.contacts.fragments;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pk.contacts.ContactListAdapter;
import com.example.pk.contacts.MainActivity;
import com.example.pk.contacts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactListFragment extends Fragment {

    @BindView(R.id.clf_list_view)
    ListView contactsList;

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

        ButterKnife.bind(this, root);

        updateAdapter();
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                if (getContext().getResources().getConfiguration().orientation
                        == Configuration.ORIENTATION_PORTRAIT) {
                    fragmentManager
                            .beginTransaction()
                            .remove(fragmentManager.findFragmentByTag(
                                    ContactListFragment.class.getSimpleName()))
                            .replace(R.id.ma_portrait_acf_and_dcf_container
                                    , DetailContactFragment.newInstance(MainActivity.contacts.get(i))
                                    , DetailContactFragment.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                } else {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.ma_landscape_detail_contact_fragment_container
                                    , DetailContactFragment.newInstance(MainActivity.contacts.get(i))
                                    , DetailContactFragment.class.getSimpleName())
                            .commit();
                }
            }
        });

        return root;
    }

    public void updateAdapter() {
        contactsList.setAdapter(new ContactListAdapter(getContext()));
    }
}
